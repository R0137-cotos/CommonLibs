package jp.co.ricoh.cotos.commonlib.entity.license.cas;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

/**
 * RITOS文書情報MVIEW
 */
@Entity
@Data
@Table(name = "mv_ritos_contract_info")
public class MvRitosContractInfo {

	/** RITOS文書情報 */
	@Id
	private String ritos_contract_number;

	/** MVBアカウント */
	private String mvb_account;

	/** 担当者電話番号 */
	private String pic_phone_number;

	/** 企業名（カナ） */
	private String company_name_kana;

	/** 企業名 */
	private String company_name;

	/** MoM企事部ID */
	private String mom_cust_id;
}