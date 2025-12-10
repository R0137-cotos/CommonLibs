package jp.co.ricoh.cotos.commonlib.dto.parameter.communication;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.reflect.FieldUtils;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 契約詳細を検索するためのキー項目クラスを表します。
 */
@Data
public class ContractSearchParameterForDetailOutsideCotos {

	/**
	 * 商品種別
	 */
	@Parameter(description = "商品種別", required = false)
	@Schema(description = "商品種別", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,15]")
	private List<String> productType;

	/**
	 * COTOS契約ライフサイクル状態
	 */
	@Parameter(description = "COTOS契約ライフサイクル状態", required = true)
	@Schema(description = "COTOS契約ライフサイクル状態", requiredMode = Schema.RequiredMode.REQUIRED)
	private List<String> status;

	/**
	 * 契約情報更新日From
	 */
	@Parameter(description = "契約情報更新日From", required = false)
	@Schema(description = "契約情報更新日From", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,15]")
	private String updateFrom;

	/**
	 * 契約情報更新日To
	 */
	@Parameter(description = "契約情報更新日To", required = false)
	@Schema(description = "契約情報更新日To", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,15]")
	private String updateTo;

	/**
	 * RJ管理番号
	 */
	@Parameter(description = "RJ管理番号", required = false)
	@Schema(description = "RJ管理番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,15]")
	private String rjManageNumber;

	/**
	 * アプリケーションIDリスト
	 */
	@Parameter(description = "アプリケーションIDリスト", required = true)
	@Schema(description = "アプリケーションIDリスト", requiredMode = Schema.RequiredMode.REQUIRED)
	private List<String> appId;

	/**
	 * 他システムデータ排他フラグ
	 */
	@Parameter(description = "他システムデータ排他フラグ", required = true)
	@Schema(description = "他システムデータ排他フラグ", requiredMode = Schema.RequiredMode.REQUIRED)
	private int otherSysDataExcludeFlg;

	/**
	 * 取得開始行
	 */
	@Parameter(description = "取得開始行", required = false)
	@Schema(description = "取得開始行", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String startLine;

	/**
	 * 取得行数
	 */
	@Parameter(description = "取得行数", required = false)
	@Schema(description = "取得行数", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String offset;

	/**
	 * システム区分
	 * 1：Bplats
	 * 2：ecbeing
	 */
	@Parameter(description = "システム区分", required = false)
	@Schema(description = "システム区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String systemDiv;

	/**
	 * パラメータをMapにする。
	 */
	public Map<String, Object> createParamaterMap() {
		Map<String, Object> retMap = new HashMap<>();

		FieldUtils.getAllFieldsList(this.getClass()).stream().filter(putField -> !putField.getName().startsWith("$")).forEach(field -> {
			try {
				retMap.put(field.getName(), field.get(this));
			} catch (IllegalAccessException e) {
				retMap.put(field.getName(), null);
			}
		});

		return retMap;
	}
}
