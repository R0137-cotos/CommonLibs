package jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas;

import java.util.HashMap;
import java.util.Map;

import jakarta.validation.constraints.NotNull;

import org.apache.commons.lang3.reflect.FieldUtils;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * MVBアカウントを検索するためのキー項目クラスを表します。
 */
@Data
public class CasLicenseSearchParameter {

	/**
	 * MVBアカウント
	 */
	@Parameter(description = "MVBアカウント", required = false)
	@Schema(description = "MVBアカウント", required = false, allowableValues = "range[0,18]")
	private String mvbAccount;

	/**
	 * 担当者メールアドレス
	 */
	@Parameter(description = "担当者メールアドレス", required = false)
	@Schema(description = "担当者メールアドレス", required = false, allowableValues = "range[0,255]")
	private String picMailAddress;

	/**
	 * MoM非連携_担当者電話番号
	 */
	@Parameter(description = "MoM非連携_担当者電話番号:前方一致", required = false)
	@Schema(description = "MoM非連携_担当者電話番号:前方一致", required = false, allowableValues = "range[0,255]")
	private String picPhoneNumber;

	/**
	 * 企業名（カナ）
	 */
	@Parameter(description = "企業名（カナ）:前方一致", required = false)
	@Schema(description = "企業名（カナ）:前方一致", required = false, allowableValues = "range[0,255]")
	private String companyNameKana;

	/**
	 * 企業名
	 */
	@Parameter(description = "企業名:前方一致", required = false)
	@Schema(description = "企業名:前方一致", required = false, allowableValues = "range[0,255]")
	private String companyName;

	/**
	 * MoM企事部ID
	 */
	@Parameter(description = "MoM企事部ID", required = false)
	@Schema(description = "MoM企事部ID", required = false, allowableValues = "range[0,255]")
	private String momCustId;

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
	 * MoM企業ID
	 */
	@NotNull
	@Parameter(description = "MoM企業ID", required = true)
	@Schema(description = "MoM企業ID", required = true, allowableValues = "range[0,255]")
	private String momCompanyId;

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