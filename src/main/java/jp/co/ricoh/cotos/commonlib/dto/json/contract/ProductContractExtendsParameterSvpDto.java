package jp.co.ricoh.cotos.commonlib.dto.json.contract;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumType.ContractTypeDetails;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeSvp.BillingTypeSvp;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeSvp.HarddiskTypeSvp;
import lombok.Data;

/**
 * 商品（契約用）拡張項目DTO（SVP）
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductContractExtendsParameterSvpDto {
	/**
	 * 契約種別詳細
	 */
	private ContractTypeDetails contractTypeDetails;

	/**
	 * 請求方法区分
	 */
	private BillingTypeSvp billingType;

	/**
	 * 監視運用オプション品種コード
	 */
	private String monitoringOpeItemCode;

	/**
	 * 監視運用オプション導入希望日
	 */
	private String monitoringOpeIntPreferredDate;

	/**
	 * 担当者部署名（担当者1）
	 */
	private String picDeptName1;

	/**
	 * 担当者名（担当者1）
	 */
	private String picName1;

	/**
	 * 担当者電話番号（担当者1）
	 */
	private String picPhoneNumber1;

	/**
	 * 担当者メールアドレス（担当者1）
	 */
	private String picMailAddress1;

	/**
	 * 担当者部署名（担当者2）
	 */
	private String picDeptName2;

	/**
	 * 担当者名（担当者2）
	 */
	private String picName2;

	/**
	 * 担当者電話番号（担当者2）
	 */
	private String picPhoneNumber2;

	/**
	 * 担当者メールアドレス（担当者2）
	 */
	private String picMailAddress2;

	/**
	 * 担当者部署名（担当者3）
	 */
	private String picDeptName3;

	/**
	 * 担当者名（担当者3）
	 */
	private String picName3;

	/**
	 * 担当者電話番号（担当者3）
	 */
	private String picPhoneNumber3;

	/**
	 * 担当者メールアドレス（担当者3）
	 */
	private String picMailAddress3;

	/**
	 * 保証開始日
	 */
	private String maintenanceStartDate;

	/**
	 * ハードディスク
	 */
	private HarddiskTypeSvp harddiskType;

	/**
	 * 使用OS
	 */
	private String useOs;
}
