package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import lombok.Data;

/**
 * SSテリトリマスタ
 */
@Entity
@Data
@Table(name = "mv_tjmoc290_ss_mst")
public class MvTjmoc290SsMaster {

	@Id
	private long id;

	/** Seq */
	private String sequenceNo;

	/** ﾒﾝﾃ区 */
	private String ssMaintenanceKbn;

	/** Zip */
	private String ssZipCd;

	/** rito */
	private String outlierKbn;

	/** 島の名称 */
	private String insularName;

	/** 都道府県No */
	private String prefecturalNo;

	/** Add1 */
	private String ssAdd1;

	/** Add1_k */
	private String ssAddKana1;

	/** Add2 */
	private String ssAdd2;

	/** Add2_k */
	private String ssAddKana2;

	/** Add3 */
	private String ssAdd3;

	/** Add3_k */
	private String ssAddKana3;

	/** SSCD */
	private String ssCd;

	/** P1一般①SSCD */
	@Column(name = "p1_general1_ss_cd")
	private String p1General1SsCd;

	/** P1一般①備考 */
	@Column(name = "p1_general1_remark")
	private String p1General1Remark;

	/** P1一般②SSCD */
	@Column(name = "p1_general2_ss_cd")
	private String p1General2SsCd;

	/** P1一般②備考 */
	@Column(name = "p1_general2_remark")
	private String p1General2Remark;

	/** P1MA①SSCD */
	@Column(name = "p1_ma1_ss_cd")
	private String p1Ma1SsCd;

	/** P1MA①備考 */
	@Column(name = "p1_ma1_remark")
	private String p1Ma1Remark;

	/** P1MA②SSCD */
	@Column(name = "p1_ma2_ss_cd")
	private String p1Ma2SsCd;

	/** P1MA②備考 */
	@Column(name = "p1_ma2_remark")
	private String p1Ma2Remark;

	/** P2一般①SSCD */
	@Column(name = "p2_general1_ss_cd")
	private String p2General1SsCd;

	/** P2一般①備考 */
	@Column(name = "p2_general1_remark")
	private String p2General1Remark;

	/** P2一般②SSCD */
	@Column(name = "p2_general2_ss_cd")
	private String p2General2SsCd;

	/** P2一般②備考 */
	@Column(name = "p2_general2_remark")
	private String p2General2Remark;

	/** P2MA①SSCD */
	@Column(name = "p2_ma1_ss_cd")
	private String p2Ma1SsCd;

	/** P2MA①備考 */
	@Column(name = "p2_ma1_remark")
	private String p2Ma1Remark;

	/** P2MA②SSCD */
	@Column(name = "p2_ma2_ss_cd")
	private String p2Ma2SsCd;

	/** P2MA②備考 */
	@Column(name = "p2_ma2_remark")
	private String p2Ma2Remark;

	/** FW一般①SSCD */
	@Column(name = "fw_general1_ss_cd")
	private String fwGeneral1SsCd;

	/** FW一般①備考 */
	@Column(name = "fw_general1_remark")
	private String fwGeneral1Remark;

	/** FW一般②SSCD */
	@Column(name = "fw_general2_ss_cd")
	private String fwGeneral2SsCd;

	/** FW一般②備考 */
	@Column(name = "fw_general2_remark")
	private String fwGeneral2Remark;

	/** FWMA①SSCD */
	@Column(name = "fw_ma1_ss_cd")
	private String fwMa1SsCd;

	/** FWMA①備考 */
	@Column(name = "fw_ma1_remark")
	private String fwMa1Remark;

	/** FWMA②SSCD */
	@Column(name = "fw_ma2_ss_cd")
	private String fwMa2SsCd;

	/** FWMA②備考 */
	@Column(name = "fw_ma2_remark")
	private String fwMa2Remark;

	/** L一般①SSCD */
	@Column(name = "l_general1_ss_cd")
	private String lGeneral1SsCd;

	/** L一般①備考 */
	@Column(name = "l_general1_remark")
	private String lGeneral1Remark;

	/** L一般②SSCD */
	@Column(name = "l_general2_ss_cd")
	private String lGeneral2SsCd;

	/** L一般②備考 */
	@Column(name = "l_general2_remark")
	private String lGeneral2Remark;

	/** LMA①SSCD */
	@Column(name = "l_ma1_ss_cd")
	private String lMa1SsCd;

	/** LMA①備考 */
	@Column(name = "l_ma1_remark")
	private String lMa1Remark;

	/** LMA②SSCD */
	@Column(name = "l_ma2_ss_cd")
	private String lMa2SsCd;

	/** LMA②備考 */
	@Column(name = "l_ma2_remark")
	private String lMa2Remark;

	/** F一般①SSCD */
	@Column(name = "f_general1_ss_cd")
	private String fGeneral1SsCd;

	/** F一般①備考 */
	@Column(name = "f_general1_remark")
	private String fGeneral1Remark;

	/** F一般②SSCD */
	@Column(name = "f_general2_ss_cd")
	private String fGeneral2SsCd;

	/** F一般②備考 */
	@Column(name = "f_general2_remark")
	private String fGeneral2Remark;

	/** FMA①SSCD */
	@Column(name = "f_ma1_ss_cd")
	private String fMa1SsCd;

	/** FMA①備考 */
	@Column(name = "f_ma1_remark")
	private String fMa1Remark;

	/** FMA②SSCD */
	@Column(name = "f_ma2_ss_cd")
	private String fMa2SsCd;

	/** FMA②備考 */
	@Column(name = "f_ma2_remark")
	private String fMa2Remark;

	/** A一般①SSCD */
	@Column(name = "a_general1_ss_cd")
	private String aGeneral1SsCd;

	/** A一般①備考 */
	@Column(name = "a_general1_remark")
	private String aGeneral1Remark;

	/** A一般②SSCD */
	@Column(name = "a_general2_ss_cd")
	private String aGeneral2SsCd;

	/** A一般②備考 */
	@Column(name = "a_general2_remark")
	private String aGeneral2Remark;

	/** AMA①SSCD */
	@Column(name = "a_ma1_ss_cd")
	private String aMa1SsCd;

	/** AMA①備考 */
	@Column(name = "a_ma1_remark")
	private String aMa1Remark;

	/** AMA②SSCD */
	@Column(name = "a_ma2_ss_cd")
	private String aMa2SsCd;

	/** AMA②備考 */
	@Column(name = "a_ma2_remark")
	private String aMa2Remark;

	/** C一般①SSCD */
	@Column(name = "c_general1_ss_cd")
	private String cGeneral1SsCd;

	/** C一般①備考 */
	@Column(name = "c_general1_remark")
	private String cGeneral1Remark;

	/** C一般②SSCD */
	@Column(name = "c_general2_ss_cd")
	private String cGeneral2SsCd;

	/** C一般②備考 */
	@Column(name = "c_general2_remark")
	private String cGeneral2Remark;

	/** CMA①SSCD */
	@Column(name = "c_ma1_ss_cd")
	private String cMa1SsCd;

	/** CMA①備考 */
	@Column(name = "c_ma1_remark")
	private String cMa1Remark;

	/** CMA②SSCD */
	@Column(name = "c_ma2_ss_cd")
	private String cMa2SsCd;

	/** CMA②備考 */
	@Column(name = "c_ma2_remark")
	private String cMa2Remark;

	/** N一般①SSCD */
	@Column(name = "n_general1_ss_cd")
	private String nGeneral1SsCd;

	/** N一般①備考 */
	@Column(name = "n_general1_remark")
	private String nGeneral1Remark;

	/** N一般②SSCD */
	@Column(name = "n_general2_ss_cd")
	private String nGeneral2SsCd;

	/** N一般②備考 */
	@Column(name = "n_general2_remark")
	private String nGeneral2Remark;

	/** NMA①SSCD */
	@Column(name = "n_ma1_ss_cd")
	private String nMa1SsCd;

	/** NMA①備考 */
	@Column(name = "n_ma1_remark")
	private String nMa1Remark;

	/** NMA②SSCD */
	@Column(name = "n_ma2_ss_cd")
	private String nMa2SsCd;

	/** NMA②備考 */
	@Column(name = "n_ma2_remark")
	private String nMa2Remark;

	/** T一般①SSCD */
	@Column(name = "t_general1_ss_cd")
	private String tGeneral1SsCd;

	/** T一般①備考 */
	@Column(name = "t_general1_remark")
	private String tGeneral1Remark;

	/** T一般②SSCD */
	@Column(name = "t_general2_ss_cd")
	private String tGeneral2SsCd;

	/** T一般②備考 */
	@Column(name = "t_general2_remark")
	private String tGeneral2Remark;

	/** TMA①SSCD */
	@Column(name = "t_ma1_ss_cd")
	private String tMa1SsCd;

	/** TMA①備考 */
	@Column(name = "t_ma1_remark")
	private String tMa1Remark;

	/** TMA②SSCD */
	@Column(name = "t_ma2_ss_cd")
	private String tMa2SsCd;

	/** TMA②備考 */
	@Column(name = "t_ma2_remark")
	private String tMa2Remark;

	/** ZR一般①SSCD *
	 * @Column(name = "z_general1_ss_cd")/
	private String zGeneral1SsCd;
	
	/** ZR一般①備考 */
	@Column(name = "z_general1_remark")
	private String zGeneral1Remark;

	/** ZR一般②SSCD *
	 * @Column(name = "z_general2_ss_cd")/
	private String zGeneral2SsCd;
	
	/** ZR一般②備考 */
	@Column(name = "z_general2_remark")
	private String zGeneral2Remark;

	/** ZRMA①SSCD */
	@Column(name = "z_ma1_ss_cd")
	private String zMa1SsCd;

	/** ZRMA①備考 */
	@Column(name = "z_ma1_remark")
	private String zMa1Remark;

	/** ZRMA②SSCD */
	@Column(name = "z_ma2_ss_cd")
	private String zMa2SsCd;

	/** ZRMA②備考 */
	@Column(name = "z_ma2_remark")
	private String zMa2Remark;

	/** ZS一般①SSCD */
	@Column(name = "zs_general1_ss_cd")
	private String zsGeneral1SsCd;

	/** ZS一般①備考 */
	@Column(name = "zs_general1_remark")
	private String zsGeneral1Remark;

	/** ZS一般②SSCD */
	@Column(name = "zs_general2_ss_cd")
	private String zsGeneral2SsCd;

	/** ZS一般②備考 */
	@Column(name = "zs_general2_remark")
	private String zsGeneral2Remark;

	/** ZSMA①SSCD */
	@Column(name = "zs_ma1_ss_cd")
	private String zsMa1SsCd;

	/** ZSMA①備考 */
	@Column(name = "zs_ma1_remark")
	private String zsMa1Remark;

	/** ZSMA②SSCD */
	@Column(name = "zs_ma2_ss_cd")
	private String zsMa2SsCd;

	/** ZSMA②備考 */
	@Column(name = "zs_ma2_remark")
	private String zsMa2Remark;

	/** PC一般①SSCD */
	@Column(name = "pc_general1_ss_cd")
	private String pcGeneral1SsCd;

	/** PC一般①備考 */
	@Column(name = "pc_general1_remark")
	private String pcGeneral1Remark;

	/** PC一般②SSCD */
	@Column(name = "pc_general2_ss_cd")
	private String pcGeneral2SsCd;

	/** PC一般②備考 */
	@Column(name = "pc_general2_remark")
	private String pcGeneral2Remark;

	/** PCMA①SSCD */
	@Column(name = "pc_ma1_ss_cd")
	private String pcMa1SsCd;

	/** PCMA①備考 */
	@Column(name = "pc_ma1_remark")
	private String pcMa1Remark;

	/** PCMA②SSCD */
	@Column(name = "pc_ma2_ss_cd")
	private String pcMa2SsCd;

	/** PCMA②備考 */
	@Column(name = "pc_ma2_remark")
	private String pcMa2Remark;

	/** NW1一般①SSCD */
	@Column(name = "nw1_general1_ss_cd")
	private String nw1General1SsCd;

	/** NW1一般①備考 */
	@Column(name = "nw1_general1_remark")
	private String nw1General1Remark;

	/** NW1一般②SSCD */
	@Column(name = "nw1_general2_ss_cd")
	private String nw1General2SsCd;

	/** NW1一般②備考 */
	@Column(name = "nw1_general2_remark")
	private String nw1General2Remark;

	/** NW1MA①SSCD */
	@Column(name = "nw1_ma1_ss_cd")
	private String nw1Ma1SsCd;

	/** NW1MA①備考 */
	@Column(name = "nw1_ma1_remark")
	private String nw1Ma1Remark;

	/** NW1MA②SSCD */
	@Column(name = "nw1_ma2_ss_cd")
	private String nw1Ma2SsCd;

	/** NW1MA②備考 */
	@Column(name = "nw1_ma2_remark")
	private String nw1Ma2Remark;

	/** DS1一般①SSCD */
	@Column(name = "ds1_general1_ss_cd")
	private String ds1General1SsCd;

	/** DS1一般①備考 */
	@Column(name = "ds1_general1_remark")
	private String ds1General1Remark;

	/** DS1一般②SSCD */
	@Column(name = "ds1_general2_ss_cd")
	private String ds1General2SsCd;

	/** DS1一般②備考 */
	@Column(name = "ds1_general2_remark")
	private String ds1General2Remark;

	/** DS1MA①SSCD */
	@Column(name = "ds1_ma1_ss_cd")
	private String ds1Ma1SsCd;

	/** DS1MA①備考 */
	@Column(name = "ds1_ma1_remark")
	private String ds1Ma1Remark;

	/** DS1MA②SSCD */
	@Column(name = "ds1_ma2_ss_cd")
	private String ds1Ma2SsCd;

	/** DS1MA②備考 */
	@Column(name = "ds1_ma2_remark")
	private String ds1Ma2Remark;

	/** DS2一般①SSCD */
	@Column(name = "ds2_general1_ss_cd")
	private String ds2General1SsCd;

	/** DS2一般①備考 */
	@Column(name = "ds2_general1_remark")
	private String ds2General1Remark;

	/** DS2一般②SSCD */
	@Column(name = "ds2_general2_ss_cd")
	private String ds2General2SsCd;

	/** DS2一般②備考 */
	@Column(name = "ds2_general2_remark")
	private String ds2General2Remark;

	/** DS2MA①SSCD */
	@Column(name = "ds2_ma1_ss_cd")
	private String ds2Ma1SsCd;

	/** DS2MA①備考 */
	@Column(name = "ds2_ma1_remark")
	private String ds2Ma1Remark;

	/** DS2MA②SSCD */
	@Column(name = "ds2_ma2_ss_cd")
	private String ds2Ma2SsCd;

	/** DS2MA②備考 */
	@Column(name = "ds2_ma2_remark")
	private String ds2Ma2Remark;

	/** OF一般①SSCD */
	@Column(name = "of_general1_ss_cd")
	private String ofGeneral1SsCd;

	/** OF一般①備考 */
	@Column(name = "of_general1_remark")
	private String ofGeneral1Remark;

	/** OF一般②SSCD */
	@Column(name = "of_general2_ss_cd")
	private String ofGeneral2SsCd;

	/** OF一般②備考 */
	@Column(name = "of_general2_remark")
	private String ofGeneral2Remark;

	/** OFMA①SSCD */
	@Column(name = "of_ma1_ss_cd")
	private String ofMa1SsCd;

	/** OFMA①備考 */
	@Column(name = "of_ma1_remark")
	private String ofMa1Remark;

	/** OFMA②SSCD */
	@Column(name = "of_ma2_ss_cd")
	private String ofMa2SsCd;

	/** OFMA②備考 */
	@Column(name = "of_ma2_remark")
	private String ofMa2Remark;

	/** SV一般①SSCD */
	@Column(name = "sv_general1_ss_cd")
	private String svGeneral1SsCd;

	/** SV一般①備考 */
	@Column(name = "sv_general1_remark")
	private String svGeneral1Remark;

	/** SV一般②SSCD */
	@Column(name = "sv_general2_ss_cd")
	private String svGeneral2SsCd;

	/** SV一般②備考 */
	@Column(name = "sv_general2_remark")
	private String svGeneral2Remark;

	/** SVMA①SSCD */
	@Column(name = "sv_ma1_ss_cd")
	private String svMa1SsCd;

	/** SVMA①備考 */
	@Column(name = "sv_ma1_remark")
	private String svMa1Remark;

	/** SVMA②SSCD */
	@Column(name = "sv_ma2_ss_cd")
	private String svMa2SsCd;

	/** SVMA②備考 */
	@Column(name = "sv_ma2_remark")
	private String svMa2Remark;

	/** NW2一般①SSCD */
	@Column(name = "nw2_general1_ss_cd")
	private String nw2General1SsCd;

	/** NW2一般①備考 */
	@Column(name = "nw2_general1_remark")
	private String nw2General1Remark;

	/** NW2一般②SSCD */
	@Column(name = "nw2_general2_ss_cd")
	private String nw2General2SsCd;

	/** NW2一般②備考 */
	@Column(name = "nw2_general2_remark")
	private String nw2General2Remark;

	/** NW2MA①SSCD */
	@Column(name = "nw2_ma1_ss_cd")
	private String nw2Ma1SsCd;

	/** NW2MA①備考 */
	@Column(name = "nw2_ma1_remark")
	private String nw2Ma1Remark;

	/** NW2MA②SSCD */
	@Column(name = "nw2_ma2_ss_cd")
	private String nw2Ma2SsCd;

	/** NW2MA②備考 */
	@Column(name = "nw2_ma2_remark")
	private String nw2Ma2Remark;

	/** MOM会社ID */
	private String corpId;

	/** 作成元区分 */
	private String ssCreateKbn;

	/** 更新MOM会社ID */
	private String updateCorpId;

	/** 登録者ID */
	private String registerUserId;

	/** 登録者名 */
	private String registerUserName;

	/** 更新者ID */
	private String updatingUserId;

	/** 更新者名 */
	private String updatingUserName;

	/** 登録日時 */
	@Temporal(TemporalType.DATE)
	private Date registerDate;

	/** 更新日時 */
	@Temporal(TemporalType.DATE)
	private Date updatingDate;

	/** 登録プログラムID */
	private String registerProgramId;

	/** 更新プログラムID */
	private String updatingProgramId;

	/** SVコード */
	private String svCode;
}
