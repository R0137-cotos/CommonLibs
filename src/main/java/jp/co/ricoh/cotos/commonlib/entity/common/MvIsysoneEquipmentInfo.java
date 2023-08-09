package jp.co.ricoh.cotos.commonlib.entity.common;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
	 * 保守担当CEコード
	 */
	private String hstan_cecd;

	/**
	 * 保守担当課所MoM組織CD
	 */
	private String MOMSKID;
}
