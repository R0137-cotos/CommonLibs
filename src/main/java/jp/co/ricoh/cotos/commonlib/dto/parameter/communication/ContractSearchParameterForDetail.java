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
public class ContractSearchParameterForDetail {

	/**
	 * 商品種別
	 */
	@Parameter(description = "商品種別")
	@Schema(description = "商品種別", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,15]")
	private List<String> productType;

	/**
	 * COTOS契約ライフサイクル状態
	 */
	@Parameter(description = "COTOS契約ライフサイクル状態")
	@Schema(description = "COTOS契約ライフサイクル状態", requiredMode = Schema.RequiredMode.REQUIRED)
	private List<String> status;

	/**
	 * 契約情報更新日From
	 */
	@Parameter(description = "契約情報更新日From")
	@Schema(description = "契約情報更新日From", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,15]")
	private String updateFrom;

	/**
	 * 契約情報更新日To
	 */
	@Parameter(description = "契約情報更新日To")
	@Schema(description = "契約情報更新日To", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,15]")
	private String updateTo;

	/**
	 * RJ管理番号
	 */
	@Parameter(description = "RJ管理番号")
	@Schema(description = "RJ管理番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,15]")
	private String rjManageNumber;

	/**
	 * アプリケーションIDリスト
	 */
	@Parameter(description = "アプリケーションIDリスト")
	@Schema(description = "アプリケーションIDリスト", requiredMode = Schema.RequiredMode.REQUIRED)
	private List<String> appId;

	/**
	 * 他システムデータ排他フラグ
	 */
	@Parameter(description = "他システムデータ排他フラグ")
	@Schema(description = "他システムデータ排他フラグ", requiredMode = Schema.RequiredMode.REQUIRED)
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
