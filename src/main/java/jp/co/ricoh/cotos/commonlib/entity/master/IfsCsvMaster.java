package jp.co.ricoh.cotos.commonlib.entity.master;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * IFSその他機器情報管理マスタを表すEntity
 */
@Entity
@Data
@ToString(exclude = { "productMaster" })
@EqualsAndHashCode(callSuper = true)
@Table(name = "ifs_csv_master")
public class IfsCsvMaster extends EntityBaseMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ifs_csv_master_seq")
	@SequenceGenerator(name = "ifs_csv_master_seq", sequenceName = "ifs_csv_master_seq", allocationSize = 1)
	@Schema(description = "IFSその他機器情報管理マスタID", required = true, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 商品マスタ
	 */
	@OneToOne(optional = false)
	@JoinColumn(name = "product_master_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "商品マスタ", required = true)
	private ProductMaster productMaster;

	/**
	 * 契約Noヘッダ
	 */
	@Schema(description = "契約Noヘッダ", required = false, allowableValues = "range[0,255]")
	private String contractNoHeader;

	/**
	 * 売上先コード
	 */
	@Schema(description = "売上先コード", required = false, allowableValues = "range[0,255]")
	private String nBillAcountCode;

	/**
	 * DM送付不可区分
	 */
	@Schema(description = "DM送付不可区分", required = false, allowableValues = "range[0,255]")
	private String nNotTransDmFlg;

	/**
	 * 移行用キーワード１
	 */
	@Schema(description = "移行用キーワード１", required = false, allowableValues = "range[0,255]")
	private String nMigarateKeyword1;

	/**
	 * サービス購入フラグ
	 */
	@Schema(description = "サービス購入フラグ", required = false, allowableValues = "range[0,255]")
	private String nServicePurchFlg;

	/**
	 * オンサイト保守フラグ
	 */
	@Schema(description = "オンサイト保守フラグ", required = false, allowableValues = "range[0,255]")
	private String nOnSiteMainteFlg;

	/**
	 * インシデント管理フラグ
	 */
	@Schema(description = "インシデント管理フラグ", required = false, allowableValues = "range[0,255]")
	private String nIncidentManageFlg;

	/**
	 * CE手配完了報告
	 */
	@Schema(description = "CE手配完了報告", required = false, allowableValues = "range[0,255]")
	private String nCeOrderdFlg;

	/**
	 * CE確定報告
	 */
	@Schema(description = "CE確定報告", required = false, allowableValues = "range[0,255]")
	private String nCeFixedFlg;

	/**
	 * CE到着予定報告
	 */
	@Schema(description = "CE到着予定報告", required = false, allowableValues = "range[0,255]")
	private String nCePlannedArriveFlg;

	/**
	 * CE到着報告
	 */
	@Schema(description = "CE到着報告", required = false, allowableValues = "range[0,255]")
	private String nCeArrivedFlg;

	/**
	 * 機器前到着及び作業開始報告
	 */
	@Schema(description = "機器前到着及び作業開始報告", required = false, allowableValues = "range[0,255]")
	private String nWorkStartFlg;

	/**
	 * チェックポイント１
	 */
	@Column(name ="n_check_point1_flg")
	@Schema(description = "チェックポイント１", required = false, allowableValues = "range[0,255]")
	private String nCheckPoint1Flg;

	/**
	 * チェックポイント２
	 */
	@Column(name ="n_check_point2_flg")
	@Schema(description = "チェックポイント２", required = false, allowableValues = "range[0,255]")
	private String nCheckPoint2Flg;

	/**
	 * チェックポイント３
	 */
	@Column(name ="n_check_point3_flg")
	@Schema(description = "チェックポイント３", required = false, allowableValues = "range[0,255]")
	private String nCheckPoint3Flg;

	/**
	 * 作業完了報告
	 */
	@Schema(description = "作業完了報告", required = false, allowableValues = "range[0,255]")
	private String nWorkEndFlg;

	/**
	 * 退出報告
	 */
	@Schema(description = "退出報告", required = false, allowableValues = "range[0,255]")
	private String nCeCheckOutFlg;

	/**
	 * 部材到着予定報告
	 */
	@Schema(description = "部材到着予定報告", required = false, allowableValues = "range[0,255]")
	private String nPartsToArriveFlg;

	/**
	 * 部材到着報告
	 */
	@Schema(description = "部材到着報告", required = false, allowableValues = "range[0,255]")
	private String nPartsArrivedFlg;

	/**
	 * 成果物回収報告
	 */
	@Schema(description = "成果物回収報告", required = false, allowableValues = "range[0,255]")
	private String nOutComeFlg;

	/**
	 * リモート可不可フラグ
	 */
	@Schema(description = "リモート可不可フラグ", required = false, allowableValues = "range[0,255]")
	private String nRemoteAllowedFlg;

	/**
	 * 月報作成要否
	 */
	@Schema(description = "月報作成要否", required = false, allowableValues = "range[0,255]")
	private String nMonthryReportFlg;
}
