package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

/**
 * MOM顧客仕入先サイト情報
 */
@Entity
@Data
@Table(name = "mv_v_jmch001")
public class MvVJmch001Master {

	/** サイト番号 */
	@Id
	private String supplyCustomerId;

	/** 営業単位コード */
	private String salesUnitCode;

	/** 販社コード */
	private String hanshCd;

	/** 管理情報ID */
	private String systemSeqNumber;

	/** 顧客（企業）ID */
	private String customerNumber;

	/** MoM企業ID */
	private String sortCompanyId;

	/** MoM事業所ID */
	private String businessPlaceId;

	/** MoM部門ID */
	private String bmnId;

	/** 上流取引先コード */
	private String originalTorihikiCd;

	/** コピー識別 */
	private String copyClassification;

	/** 名寄せ親MoM企業ID */
	private String linkCompanyCode;

	/** 名寄せ親MoM事業所ID */
	private String linkBusinessPlaceCode;

	/** 名寄せ親MoM部門ID */
	private String linkPostCode;

	/** システム連携ID */
	private String cuicCompanyId;

	/** 名寄せ親MOM顧客企事部ID */
	private String linkMomCstId;

	/** 名寄せ区分 */
	private String identityCls;

	/** 仕入先名カナ */
	private String supplyCustomerNamePhonetic;

	/** 仕入先サイト名 */
	private String siteName;

	/** 仕入先サイトカナ */
	private String siteNamePhonetic;

	/** 仕入先サイト名略称 */
	private String siteBrevityName;

	/** 国コード */
	private String territoryCode;

	/** 郵便番号 */
	private String postalCode;

	/** 所在地行１ */
	private String address1;

	/** 所在地行２ */
	private String address2;

	/** 所在地行３ */
	private String address3;

	/** カナ所在地 */
	private String addressPhonetic;

	/** 言語 */
	private String language;

	/** 無効日 */
	@Temporal(TemporalType.DATE)
	private Date inactiveDate;

	/** ボイス－市外局番 */
	private String voiceAreaCode;

	/** ボイス－番号 */
	private String voicePhoneNumber;

	/** FAX－市外局番 */
	private String faxAreaCode;

	/** FAX－番号 */
	private String faxPhoneNumber;

	/** Eメール */
	private String emailAddress;

	/** 親取引先コード */
	private String parentCustomerCode;

	/** 取引先名称カナ */
	private String customerNamePhonetic;

	/** 仕入先経理担当者名 */
	private String accountRepresentative;

	/** 仕入先経理担当者名部署名 */
	private String accountRepresentativePost;

	/** 税務署所在地コード */
	private String taxOfficeCode;

	/** 処理フラグ */
	private String insertUpdateFlag;

	/** 相殺用コード */
	private String offsetCd;

	/** 持株会加入区分 */
	private String mtikbukiKanyukbn;

	/** 持株会株式購入口数 */
	private String mtikbukiKbuknyusu;

	/** 支払分割基準 */
	private String shriDividedKijun;

	/** 支払区分 */
	private String shriKbn;

	/** 振込外支払基準額 */
	private String frkmgaiShriKijunGaku;

	/** 最小振込金額 */
	private String minFrkmAmt;

	/** 振込割合 */
	private String frkmWri;

	/** 支払サイト日 */
	private String shriSiteDt;

	/** 支払通知書発行区分 */
	private String shriTtshHakkoFlg;

	/** 支払場所 */
	private String shriPlace;

	/** 支払手形受取人名称 */
	private String shriTgtaTrhkskNm;

	/** 下請区分 */
	private String stukKbn;

	/** 業種区分 */
	private String shkshKbn;

	/** サイト管理名称 */
	private String siteKanriKbn;

	/** 仕入先区分 */
	private String sirskKbn;

	/** 代表支払仕入先サイトコード */
	private String dhyshShriSirskSiteCd;

	/** 代表支払仕入先サイト適用日 */
	private String dhyshShriSirskSiteTekiYmd;

	/** 支払保留フラグ */
	private String holdbackFlag;

	/** 主支払フラグ */
	private String primaryFlag;

	/** 支払条件 */
	private String paymentTerm;

	/** 支払グループ */
	private String paymentGroup;

	/** 請求書通貨 */
	private String invoiceCurrency;

	/** 支払方法 */
	private String paymentMethod;

	/** 銀行手数料負担者 */
	private String bankChargeSponsor;

	/** 請求書税金コード */
	private String invoiceTaxCode;

	/** 源泉徴収税の許可 */
	private String amountTaxesWithheldPermit;

	/** 源泉徴収税グループ */
	private String amountTaxesWithheldGroup;

	/** 取引停止フラグ */
	private String delTeisiFlg;

	/** 他社発注可能フラグ */
	private String tasyaOrderFlg;

	/** 仕入差異対象フラグ */
	private String stockDiffFlg;

	/** 税込税抜区分 */
	private String taxInOutKb;

	/** 取引先_担当者名 */
	private String contactPersonNm;

	/** 取引先_担当者名カナ */
	private String contactPersonNmPhonetic;

	/** 取引先_注文書発行区分 */
	private String contactOrderHakkoKbn;

	/** 発注区分 */
	private String orderKbn;

	/** 分納区分 */
	private String bunnouKbn;

	/** 部分キャンセル区分 */
	private String cpPartsCancelKbn;

	/** 部分返品区分 */
	private String cpPartsReturnKbn;

	/** 特価受付区分 */
	private String tokkaReceptionistKbn;

	/** 基本契約追加区分 */
	private String kihonKykAddKbn;

	/** 顧客（企業）情報更新日 */
	@Temporal(TemporalType.DATE)
	private Date cciUpdtDt;

	/** 仕入先情報更新日 */
	@Temporal(TemporalType.DATE)
	private Date suiUpdtDt;

	/** MoM最終更新日時時刻 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date scMomUpdtDtTm;

	/** 統括購買仕入先サイトコード */
	private String tokatsuKoubaiSirskCd;

	/** クレンジング仕入先サイト名 */
	private String clnSiteName;

	/** MoM会社ID */
	private String momKishId;

	/** 建設業取引フラグ */
	private String knstgyTrhkFlg;

	/** 入荷自動計上フラグ */
	private String nyukaAutoKeijoFlg;

	/** 支社/本部発注フラグ */
	private String shishHnbOrderFlg;

	/** 適格請求書登録事業者番号 */
	private String invoiceNo;

	/** 手入力情報有効フラグ */
	private String manualEntryFlag;

	/** 更新区分 */
	private String updtKbn;

	/** LBCコード */
	private String lbcCd;
}
