package jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas;

import java.util.HashMap;
import java.util.Map;

import jakarta.validation.constraints.NotNull;

import org.apache.commons.lang3.reflect.FieldUtils;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

/**
 * MVBアカウントを検索するためのキー項目クラスを表します。
 */
@Data
public class CasLicenseSearchParameter {

	/**
	 * MVBアカウント
	 */
	@ApiParam(value = "MVBアカウント", required = false)
	@ApiModelProperty(value = "MVBアカウント", required = false, position = 1, allowableValues = "range[0,18]")
	private String mvbAccount;

	/**
	 * 担当者メールアドレス
	 */
	@ApiParam(value = "担当者メールアドレス", required = false)
	@ApiModelProperty(value = "担当者メールアドレス", required = false, position = 2, allowableValues = "range[0,255]")
	private String picMailAddress;

	/**
	 * MoM非連携_担当者電話番号
	 */
	@ApiParam(value = "MoM非連携_担当者電話番号:前方一致", required = false)
	@ApiModelProperty(value = "MoM非連携_担当者電話番号:前方一致", required = false, position = 3, allowableValues = "range[0,255]")
	private String picPhoneNumber;

	/**
	 * 企業名（カナ）
	 */
	@ApiParam(value = "企業名（カナ）:前方一致", required = false)
	@ApiModelProperty(value = "企業名（カナ）:前方一致", required = false, position = 4, allowableValues = "range[0,255]")
	private String companyNameKana;

	/**
	 * 企業名
	 */
	@ApiParam(value = "企業名:前方一致", required = false)
	@ApiModelProperty(value = "企業名:前方一致", required = false, position = 5, allowableValues = "range[0,255]")
	private String companyName;

	/**
	 * MoM企事部ID
	 */
	@ApiParam(value = "MoM企事部ID", required = false)
	@ApiModelProperty(value = "MoM企事部ID", required = false, position = 6, allowableValues = "range[0,255]")
	private String momCustId;

	/**
	 * 契約番号
	 */
	@ApiParam(value = "契約番号", required = false)
	@ApiModelProperty(value = "契約番号", required = false, position = 7, allowableValues = "range[0,15]")
	private String contractNumber;

	/**
	 * 契約番号枝番
	 */
	@ApiParam(value = "契約番号枝番", required = false)
	@ApiModelProperty(value = "契約番号枝番", required = false, position = 8, allowableValues = "range[0,2]")
	private String contractBranchNumber;

	/**
	 * MoM企業ID
	 */
	@NotNull
	@ApiParam(value = "MoM企業ID", required = true)
	@ApiModelProperty(value = "MoM企業ID", required = true, position = 9, allowableValues = "range[0,255]")
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