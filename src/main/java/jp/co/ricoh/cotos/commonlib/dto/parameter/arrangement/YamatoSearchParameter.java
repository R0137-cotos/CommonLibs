package jp.co.ricoh.cotos.commonlib.dto.parameter.arrangement;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.reflect.FieldUtils;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * ヤマト便作業対象を検索するためのキー項目クラスを表します。
 */
@Data
public class YamatoSearchParameter {

	/**
	 * 商品マスタID
	 */
	@Parameter(description = "商品マスタID", required = false)
	@Schema(description = "商品マスタID", required = false)
	private Long productMasterId;

	/**
	 * お問い合わせ番号
	 */
	@Parameter(description = "お問い合わせ番号", required = false)
	@Schema(description = "お問い合わせ番号", required = false, allowableValues = "range[0,255]")
	private String contactNo;

	/**
	 * 契約番号
	 */
	@Parameter(description = "契約番号", required = false)
	@Schema(description = "契約番号", required = false, allowableValues = "range[0,15]")
	private String contractNumber;

	/**
	 * 契約番号枝番
	 */
	@Parameter(description = "契約番号枝番", required = false)
	@Schema(description = "契約番号枝番", required = false, allowableValues = "range[0,2]")
	private String contractBranchNumber;

	/**
	 * 業務受付日時
	 */
	@Parameter(description = "業務受付日時", required = false)
	@Schema(description = "業務受付日時<br />" //
			+ "日付フォーマット:yyyy/MM/dd", //
			required = false) //
	private Date businessAcceptDateTime;

	/**
	 * アプリケーションIDリスト
	 */
	@Parameter(description = "アプリケーションIDリスト", required = false)
	@Schema(description = "アプリケーションIDリスト", required = false)
	private List<String> appId;

	/**
	 * 他システムデータ排他フラグ
	 */
	@Parameter(description = "他システムデータ排他フラグ", required = false)
	@Schema(description = "他システムデータ排他フラグ", required = false)
	private int otherSysDataExcludeFlg;

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