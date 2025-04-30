package jp.co.ricoh.cotos.commonlib.db;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import jp.co.ricoh.cotos.commonlib.DBConfig;
import jp.co.ricoh.cotos.commonlib.db.dto.AnalyzedDateDto;
import jp.co.ricoh.cotos.commonlib.db.dto.InputStoreProcedureDto;
import jp.co.ricoh.cotos.commonlib.db.dto.SynonymNameDto;

@Component
public class RefreshMaterializedViewUtil {

	@Autowired
	DBUtil dbUtil;

	@Autowired
	EntityManager entityManager;

	@Value("${spring.datasource.username}")
	private String dbUserName;

	/**
	 * ストアド作成用のSQLを実行します。実行の度、ストアドは再作成されます<br>
	 * 対象テストの最初（injectContextメソッド）に記載することを想定しています。
	 * @param injectContext
	 */
	public void initStoredProcedure(ConfigurableApplicationContext injectContext) {
		// ストアドなので;区切りではないことからinitTargetTestClobDataメソッドを使用する

		// 統計情報取得用ストアドを作成
		injectContext.getBean(DBConfig.class).initTargetTestClobData("db/getMViewStats.sql");
		// マテビューリフレッシュ用ストアドを作成
		injectContext.getBean(DBConfig.class).initTargetTestClobData("db/refreshMView.sql");
	}

	/**
	 *  RefreshJobs/job/refresh/JBCOCM016.shの内容を元にJava化し、汎用化したもの
	 * @param synonymName
	 */
	// switchSynonymメソッドでエラーするため、Transactionalをこちらにもつけた
	@Transactional
	public void refreshMViewAndSwitchOfLicenseAccountInfo(String synonymName) {
		String materializedViewName = getSynonymNameOfStandby(synonymName);
		refreshMView(materializedViewName);
		switchSynonym(synonymName, materializedViewName);
	}

	/**
	 * RefreshJobs/util/BTCOCM003.shの内容をJava化したもの
	 * @param synonymName
	 * @return
	 */
	public String getSynonymNameOfStandby(String synonymName) {
		Map<String, Object> params = new HashMap<>();
		params.put("synonymName", synonymName);
		// 大文字で検索する
		params.put("owner", dbUserName.toUpperCase());
		List<SynonymNameDto> synonymNameDtoList = dbUtil.loadFromSQLFile("db/selectMViewNameFromSynonym.sql", SynonymNameDto.class, params);

		if (CollectionUtils.isEmpty(synonymNameDtoList)) {
			throw new RuntimeException("対象のシノニムが存在しません。データベースを確認してください。");
		}

		String name = synonymNameDtoList.get(0).getTableName();
		System.out.println("シノニムを張っているMV名：" + name);
		String nameAfter = null;
		if (name.endsWith("_A")) {
			nameAfter = "_B";
		} else if (name.endsWith("_B")) {
			nameAfter = "_A";
		}

		System.out.println("シノニムを張り替えるMV名：" + synonymName + nameAfter);
		return synonymName + nameAfter;
	}

	/**
	 * RefreshJobs/util/BTCOCM001.shの内容をJava化したもの
	 * @param tableName
	 * @return
	 */
	public Date refreshMView(String materializedViewName) {
		// マテビューリフレッシュをストアドを使用して実行する
		List<InputStoreProcedureDto> dtoList = new ArrayList<>();
		InputStoreProcedureDto dto;
		dto = new InputStoreProcedureDto("MATERIALIZED_VIEW_NAME_LIST", String.class, materializedViewName, ParameterMode.IN);
		dtoList.add(dto);

		execStoredProcedure("REFRESH_M_VIEW", dtoList);

		// 統計情報取得をストアドを使用して実行する
		dtoList = new ArrayList<>();
		dto = new InputStoreProcedureDto("TABLE_NAME", String.class, materializedViewName, ParameterMode.IN);
		dtoList.add(dto);

		execStoredProcedure("GET_M_VIEW_STATS", dtoList);

		// 統計情報取得結果確認
		Map<String, Object> params = new HashMap<>();
		params.put("tableName", materializedViewName);

		List<AnalyzedDateDto> analyzedDateDtoList = dbUtil.loadFromSQLFile("db/selectStatsInfo.sql", AnalyzedDateDto.class, params);

		if (CollectionUtils.isEmpty(analyzedDateDtoList) || analyzedDateDtoList.size() != 1) {
			throw new RuntimeException("対象のテーブルがが存在しません。データベースを確認してください。");
		}

		Date result = analyzedDateDtoList.get(0).getLastAnalyzed();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh24:mm:ss");
		System.out.println("テーブル名：" + materializedViewName + " 統計情報取得日時：" + sdf.format(result));

		return result;
	}

	/**
	 * シノニムの向き先を切り替える<br>
	 * <b>Transactionalを付けないと以下例外が発生するため注意</b>
	 * <pre>{@code TransactionRequiredException: Executing an update/delete query}</pre>
	 * @param synonymName
	 * @param materializedViewName
	 */
	@Transactional
	public void switchSynonym(String synonymName, String materializedViewName) {
		// 下記理由からJavaでSQLを作成して、実行している
		// １．createのため、dbUtil.loadFromSQLFileは実行不可
		// ２．dbUtil.loadSQLFromClasspathでSQLを作成したが、変数部分の変換ができなかった。DBConfig.initTargetTestClobDataも変数の指定がないため実行不可
		String sql = "CREATE OR REPLACE SYNONYM " + synonymName + " FOR " + materializedViewName;
		entityManager.createNativeQuery(sql).executeUpdate();
	}

	/**
	 * ストアドプロシージャを実行します。戻り値の取得がないストアドを実行することを想定しています。
	 * @param storedName 実行対象のストアド名
	 * @param dtoList ストアドに渡す引数
	 * @return 戻り値がなく、Updateも実行しない場合、falseが返却される。
	 */
	private boolean execStoredProcedure(String storedName, List<InputStoreProcedureDto> dtoList) {
		// クエリー準備
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(storedName);

		dtoList.forEach(dto -> {
			// パラメーターの型設定
			query.registerStoredProcedureParameter(dto.getName(), dto.getClazz(), dto.getMode());

			// パラメーター値設定
			query.setParameter(dto.getName(), dto.getValue());
		});

		return query.execute();
	}
}
