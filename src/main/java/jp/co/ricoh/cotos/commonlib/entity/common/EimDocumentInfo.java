package jp.co.ricoh.cotos.commonlib.entity.common;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.EimLinkedStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * EIM書誌情報
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "eim_document_info")
public class EimDocumentInfo extends EntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "eim_document_info_seq")
	@SequenceGenerator(name = "eim_document_info_seq", sequenceName = "eim_document_info_seq", allocationSize = 1)
	@ApiModelProperty(value = "ID(作成時不要)", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 文書ユニークキー
	 */
	@ApiModelProperty(value = "文書ユニークキー", required = true, position = 2)
	private String documentUniqueId;

	/**
	 * 文書ID
	 */
	@ApiModelProperty(value = "文書ID", required = false, position = 3)
	private String documentId;

	/**
	 * 文書キー
	 */
	@ApiModelProperty(value = "文書キー", required = false, position = 4)
	private String documentKey;

	/**
	 * EIM連携済状態
	 */
	@ApiModelProperty(value = "EIM連携済状態", required = false, allowableValues = "未連携(\"0\"), 連携済(\"1\"), 対象外(\"9\")", position = 5)
	private EimLinkedStatus eimLinkedStatus;

	/**
	 * 最終連携実施時刻
	 */
	@ApiModelProperty(value = "最終連携実施時刻", required = false, position = 6)
	private Date lastLinkedAt;

	/**
	 * 文書タイトル
	 */
	@ApiModelProperty(value = "文書タイトル", required = false, position = 7)
	private String title;

	/**
	 * 契約No
	 */
	@ApiModelProperty(value = "契約No", required = true, position = 8)
	private String keiyakNo;

	/**
	 * 担当販社CD
	 */
	@ApiModelProperty(value = "担当販社CD", required = false, position = 9)
	private String hnsyCd;

	/**
	 * 従業員番号
	 */
	@ApiModelProperty(value = "従業員番号", required = false, position = 10)
	private String syainCd;

	/**
	 * 担当者名
	 */
	@ApiModelProperty(value = "担当者名", required = false, position = 11)
	private String syainNm;

	/**
	 * 課所CD
	 */
	@ApiModelProperty(value = "課所CD", required = false, position = 12)
	private String kssCd;

	/**
	 * 課所名
	 */
	@ApiModelProperty(value = "課所名", required = false, position = 13)
	private String kssName;

	/**
	 * 販社CD（CE/SE）
	 */
	@ApiModelProperty(value = "販社CD（CE/SE）", required = false, position = 14)
	private String cshnsyCd;

	/**
	 * 従業員番号（CE/SE）
	 */
	@ApiModelProperty(value = "従業員番号（CE/SE）", required = false, position = 15)
	private String cssyainCd;

	/**
	 * 担当者名（CE/SE）
	 */
	@ApiModelProperty(value = "担当者名（CE/SE）", required = false, position = 16)
	private String cssyainNm;

	/**
	 * 課所CD（CE/SE）
	 */
	@ApiModelProperty(value = "課所CD（CE/SE）", required = false, position = 17)
	private String cskssCd;

	/**
	 * 課所名（CE/SE）
	 */
	@ApiModelProperty(value = "課所名（CE/SE）", required = false, position = 18)
	private String cskssName;

	/**
	 * 企業ID(E/U)
	 */
	@ApiModelProperty(value = "企業ID(E/U)", required = false, position = 19)
	private String ekigyoId;

	/**
	 * 事業所ID(E/U)
	 */
	@ApiModelProperty(value = "事業所ID(E/U)", required = false, position = 20)
	private String ejgsId;

	/**
	 * 企事部ID(E/U)
	 */
	@ApiModelProperty(value = "企事部ID(E/U)", required = false, position = 21)
	private String ekijibId;

	/**
	 * 企業名(E/U)
	 */
	@ApiModelProperty(value = "企業名(E/U)", required = false, position = 22)
	private String ekigyoNm;

	/**
	 * 企業名(事業所)(E/U)
	 */
	@ApiModelProperty(value = "企業名(事業所)(E/U)", required = false, position = 23)
	private String ejgsNm;

	/**
	 * 企業ID(売上)
	 */
	@ApiModelProperty(value = "企業ID(売上)", required = false, position = 24)
	private String ukigyoId;

	/**
	 * 事業所ID(売上)
	 */
	@ApiModelProperty(value = "事業所ID(売上)", required = false, position = 25)
	private String ujgsId;

	/**
	 * 企事部ID(売上)
	 */
	@ApiModelProperty(value = "企事部ID(売上)", required = false, position = 26)
	private String ukijibId;

	/**
	 * 企業名(売上)
	 */
	@ApiModelProperty(value = "企業名(売上)", required = false, position = 27)
	private String ukigyoNm;

	/**
	 * 企業名(事業所)(売上)
	 */
	@ApiModelProperty(value = "企業名(事業所)(売上)", required = false, position = 28)
	private String ujgsNm;

	/**
	 * 企事部名(売上)
	 */
	@ApiModelProperty(value = "企事部名(売上)", required = false, position = 29)
	private String ubmnNm;

	/**
	 * 得意先CD
	 */
	@ApiModelProperty(value = "得意先CD", required = false, position = 30)
	private String tokuCd;

	/**
	 * 得意先名
	 */
	@ApiModelProperty(value = "得意先名", required = false, position = 31)
	private String tokuNm;

	/**
	 * 機種略号・機番
	 */
	@ApiModelProperty(value = "機種略号・機番", required = false, position = 32)
	private String kisyuKiban;

	/**
	 * 支社CD（CE/SE）受付
	 */
	@ApiModelProperty(value = "支社CD（CE/SE）受付", required = false, position = 33)
	private String csrhnsyCd;

	/**
	 * 従業員番号（CE/SE）受付
	 */
	@ApiModelProperty(value = "従業員番号（CE/SE）受付", required = false, position = 33)
	private String csrsyainCd;

	/**
	 * 担当者名（CE/SE）受付
	 */
	@ApiModelProperty(value = "担当者名（CE/SE）受付", required = false, position = 34)
	private String csrsyainNm;

	/**
	 * 課所CD（CE/SE）受付
	 */
	@ApiModelProperty(value = "課所CD（CE/SE）受付", required = false, position = 35)
	private String csrkssCd;

	/**
	 * 課所名（CE/SE）受付
	 */
	@ApiModelProperty(value = "課所名（CE/SE）受付", required = false, position = 36)
	private String csrkssName;

	/**
	 * 支社CD（CE/SE）導入
	 */
	@ApiModelProperty(value = "支社CD（CE/SE）導入", required = false, position = 37)
	private String csihnsyCd;

	/**
	 * 従業員番号（CE/SE）導入
	 */
	@ApiModelProperty(value = "従業員番号（CE/SE）導入", required = false, position = 38)
	private String csisyainCd;

	/**
	 * 担当者名（CE/SE）導入
	 */
	@ApiModelProperty(value = "担当者名（CE/SE）導入", required = false, position = 39)
	private String csisyainNm;

	/**
	 * 課所CD（CE/SE）導入
	 */
	@ApiModelProperty(value = "課所CD（CE/SE）導入", required = false, position = 40)
	private String csikssCd;

	/**
	 * 課所名（CE/SE）導入
	 */
	@ApiModelProperty(value = "課所名（CE/SE）導入", required = false, position = 41)
	private String csikssName;

	/**
	 * 契約ID
	 */
	@ApiModelProperty(value = "契約ID", required = false, position = 42)
	private Long contractId;

	/**
	 * 旧文書フラグ
	 */
	@ApiModelProperty(value = "旧文書フラグ", required = true, position = 43)
	private boolean oldDocumentFlg;
	
	/**
	* 担当販社名
	*/
	@ApiModelProperty(value = "担当販社名", required = false, position = 44)
	private String hnsyNm;

	/**
	* 部門階層名1
	*/
	@ApiModelProperty(value = "部門階層名1", required = false, position = 45)
	private String bmnName1;

	/**
	* 部門階層名2
	*/
	@ApiModelProperty(value = "部門階層名2", required = false, position = 46)
	private String bmnName2;

	/**
	* 部門階層名3
	*/
	@ApiModelProperty(value = "部門階層名3", required = false, position = 47)
	private String bmnName3;

	/**
	* 部門階層名4
	*/
	@ApiModelProperty(value = "部門階層名4", required = false, position = 48)
	private String bmnName4;

	/**
	* 販社名（CE/SE）
	*/
	@ApiModelProperty(value = "販社名（CE/SE）", required = false, position = 49)
	private String cshnsyNm;

	/**
	* 部門階層名1(CE/SE)
	*/
	@ApiModelProperty(value = "部門階層名1(CE/SE)", required = false, position = 50)
	private String csbmnName1;

	/**
	* 部門階層名2(CE/SE)
	*/
	@ApiModelProperty(value = "部門階層名2(CE/SE)", required = false, position = 51)
	private String csbmnName2;

	/**
	* 部門階層名3(CE/SE)
	*/
	@ApiModelProperty(value = "部門階層名3(CE/SE)", required = false, position = 52)
	private String csbmnName3;

	/**
	* 部門階層名4(CE/SE)
	*/
	@ApiModelProperty(value = "部門階層名4(CE/SE)", required = false, position = 53)
	private String csbmnName4;

	/**
	* 支社名（CE/SE）受付
	*/
	@ApiModelProperty(value = "支社名（CE/SE）受付", required = false, position = 54)
	private String csrhnsyNm;

	/**
	* 部門階層名1(CE/SE) 受付
	*/
	@ApiModelProperty(value = "部門階層名1(CE/SE) 受付", required = false, position = 55)
	private String csrbmnName1;

	/**
	* 部門階層名2(CE/SE) 受付
	*/
	@ApiModelProperty(value = "部門階層名2(CE/SE) 受付", required = false, position = 56)
	private String csrbmnName2;

	/**
	* 部門階層名3(CE/SE) 受付
	*/
	@ApiModelProperty(value = "部門階層名3(CE/SE) 受付", required = false, position = 57)
	private String csrbmnName3;

	/**
	* 部門階層名4(CE/SE) 受付
	*/
	@ApiModelProperty(value = "部門階層名4(CE/SE) 受付", required = false, position = 58)
	private String csrbmnName4;

	/**
	* 支社名（CE/SE）導入
	*/
	@ApiModelProperty(value = "支社名（CE/SE）導入", required = false, position = 59)
	private String csihnsyNm;

	/**
	* 部門階層名1(CE/SE) 導入
	*/
	@ApiModelProperty(value = "部門階層名1(CE/SE) 導入", required = false, position = 60)
	private String csibmnName1;

	/**
	* 部門階層名2(CE/SE) 導入
	*/
	@ApiModelProperty(value = "部門階層名2(CE/SE) 導入", required = false, position = 61)
	private String csibmnName2;

	/**
	* 部門階層名3(CE/SE) 導入
	*/
	@ApiModelProperty(value = "部門階層名3(CE/SE) 導入", required = false, position = 62)
	private String csibmnName3;

	/**
	* 部門階層名4(CE/SE) 導入
	*/
	@ApiModelProperty(value = "部門階層名4(CE/SE) 導入", required = false, position = 63)
	private String csibmnName4;

	/**
	* 正式帳票名
	*/
	@ApiModelProperty(value = "正式帳票名", required = false, position = 64)
	private String siskThNm;

	/**
	* 識別区分
	*/
	@ApiModelProperty(value = "識別区分", required = false, position = 65)
	private Integer skbtKbn;

	/**
	* 契約金額
	*/
	@ApiModelProperty(value = "契約金額", required = false, position = 66)
	private BigDecimal kiykKngk;

	/**
	* 契約締結日
	*/
	@ApiModelProperty(value = "契約締結日", required = false, position = 67)
	@Temporal(TemporalType.DATE)
	private Date kiykTiktHi;

	/**
	* 契約種類区分
	*/
	@ApiModelProperty(value = "契約種類区分", required = false, position = 68)
	private String keiyakType;

	/**
	* FFM発注問い合わせ番号
	*/
	@ApiModelProperty(value = "FFM発注問い合わせ番号", required = false, position = 69)
	private String ffmHttiawsNo;

	/**
	* V-UP見積番号
	*/
	@ApiModelProperty(value = "V-UP見積番号", required = false, position = 70)
	private String vupMtmriNo;

	/**
	* V-UP案件番号
	*/
	@ApiModelProperty(value = "V-UP案件番号", required = false, position = 71)
	private String vupAnknNo;

	/**
	* サービス開始日
	*/
	@ApiModelProperty(value = "サービス開始日", required = false, position = 72)
	@Temporal(TemporalType.DATE)
	private Date serKisHi;

	/**
	* サービス利用希望日
	*/
	@ApiModelProperty(value = "サービス利用希望日", required = false, position = 73)
	@Temporal(TemporalType.DATE)
	private Date serRyuKbuHi;
}
