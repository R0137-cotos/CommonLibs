package jp.co.ricoh.cotos.commonlib.entity.common;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

/**
 * IsysOne機器情報MVIEW
 */
@Entity
@Data
@Table(name = "mv_isysone_equipment_info")
public class MvIsysoneEquipmentInfo {

	/**
	 * 機器管理番号
	 */
	@Id
	private String kikikrno;

	/**
	 * 機種略号コード
	 */
	private String ksyurgcd;

	/**
	 * 機器番号
	 */
	private String kikino;

	/**
	 * 保守担当課所コード
	 */
	private String hstan_kscd;

	/**
	 * 保守担当CEコード
	 */
	private String hstan_cecd;

	/**
	 * ＭＯＭ顧客システム連携ＩＤ
	 */
	private String momkksysrkid;

	/**
	 * 事業所カナ名
	 */
	private String jgykm;

	/**
	 * 事業所代表電話番号
	 */
	private String jgydh_telno;

	/**
	 * 事業所住所コード
	 */
	private String jgy_addcd;

	/**
	 * 事業所住所ビル名
	 */
	private String jgyadd_blme;

	/**
	 * 事業所住所フロア名
	 */
	private String jgyaddflme;

	/**
	 * 事業所住所号名
	 */
	private String jgy_addgome;

	/**
	 * 事業所住所字通称名
	 */
	private String jgy_addaztsyme;

	/**
	 * 事業所住所番地名
	 */
	private String jgy_addbcme;

	/**
	 * 事業所漢字名
	 */
	private String jgykjm;

	/**
	 * 企業カナ名
	 */
	private String kgykm;

	/**
	 * 企業法人格コード
	 */
	private String kgy_hjkcd;

	/**
	 * 企業法人格前後区分
	 */
	private String kgy_hjkzgkbn;

	/**
	 * 企業漢字名
	 */
	private String kgykjm;

	/**
	 * 部門漢字名
	 */
	private String bmnkjm;

	/**
	 * 顧客番号
	 */
	private String kkno;

	/**
	 * 設置先部課漢字名
	 */
	private String scsbkakjm;

	/**
	 * 設置先担当者漢字名
	 */
	private String scstan_kjsm;

	/**
	 * 設置先部課カナ名
	 */
	private String scsbkakm;

	/**
	 * 設置先担当者カナ名
	 */
	private String scstankm;
}
