package jp.co.ricoh.cotos.commonlib.dto.json.license;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * ライセンス残数拡張項目DTO（SVP）
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LicenseRemainingNumberExtendsParameterSvpDto {

	/**
	 * 新規分RSSP顧客管理DB連携状況（受注時）
	 */
	private String newOrdersRSSPCustomerManagementDBCooperationStatus;

	/**
	 * 新規分RSSP顧客管理DB出力日（受注時）
	 */
	private String newOrdersRSSPCustomerManagementDBDate;

	/**
	 * 新規分RSSP顧客管理DB連携状況（売上時）
	 */
	private String newSalesRSSPCustomerManagementDBCooperationStatus;

	/**
	 * 新規分RSSP顧客管理DB出力日（売上時）
	 */
	private String newSalesRSSPCustomerManagementDBDate;

	/**
	 * 更新分RSSP顧客管理DB連携状況（受注時）
	 */
	private String updateOrdersRSSPCustomerManagementDBCooperationStatus;

	/**
	 * 更新分RSSP顧客管理DB出力日（受注時）
	 */
	private String updateOrdersRSSPCustomerManagementDBDate;

	/**
	 * 更新分RSSP顧客管理DB連携状況（売上時）
	 */
	private String updateSalesRSSPCustomerManagementDBCooperationStatus;

	/**
	 * 更新分RSSP顧客管理DB出力日（売上時）
	 */
	private String updateSalesRSSPCustomerManagementDBDate;

	/**
	 * 2回目更新分RSSP顧客管理DB連携契約ID
	 */
	private String secondUpdateRSSPCustomerManagementDBCooperationContractId;

	/**
	 * 2回目更新分RSSP顧客管理DB連携状況（受注時）
	 */
	private String secondUpdateOrdersRSSPCustomerManagementDBCooperationStatus;

	/**
	 * 2回目更新分RSSP顧客管理DB出力日（受注時）
	 */
	private String secondUpdateOrdersRSSPCustomerManagementDBDate;

	/**
	 * 2回目更新分RSSP顧客管理DB連携状況（売上時）
	 */
	private String secondUpdateSalesRSSPCustomerManagementDBCooperationStatus;

	/**
	 * 2回目更新分RSSP顧客管理DB出力日（売上時）
	 */
	private String secondUpdateSalesRSSPCustomerManagementDBDate;
}
