package jp.co.ricoh.cotos.commonlib.entity.common;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(description = "ID(作成時不要)", required = true, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 文書ユニークキー
	 */
	@Schema(description = "文書ユニークキー", required = true)
	private String documentUniqueId;

	/**
	 * 文書ID
	 */
	@Schema(description = "文書ID", required = false)
	private String documentId;

	/**
	 * 文書キー
	 */
	@Schema(description = "文書キー", required = false)
	private String documentKey;

	/**
	 * EIM連携済状態
	 */
	@Schema(description = "EIM連携済状態", required = false, allowableValues = "未連携(\"0\"), 連携済(\"1\"), 対象外(\"9\")")
	private EimLinkedStatus eimLinkedStatus;

	/**
	 * 最終連携実施時刻
	 */
	@Schema(description = "最終連携実施時刻", required = false)
	private Date lastLinkedAt;

	/**
	 * 文書タイトル
	 */
	@Schema(description = "文書タイトル", required = false)
	private String title;

	/**
	 * 契約No
	 */
	@Schema(description = "契約No", required = true)
	private String keiyakNo;

	/**
	 * 担当販社CD
	 */
	@Schema(description = "担当販社CD", required = false)
	private String hnsyCd;

	/**
	 * 従業員番号
	 */
	@Schema(description = "従業員番号", required = false)
	private String syainCd;

	/**
	 * 担当者名
	 */
	@Schema(description = "担当者名", required = false)
	private String syainNm;

	/**
	 * 課所CD
	 */
	@Schema(description = "課所CD", required = false)
	private String kssCd;

	/**
	 * 課所名
	 */
	@Schema(description = "課所名", required = false)
	private String kssName;

	/**
	 * 販社CD（CE/SE）
	 */
	@Schema(description = "販社CD（CE/SE）", required = false)
	private String cshnsyCd;

	/**
	 * 従業員番号（CE/SE）
	 */
	@Schema(description = "従業員番号（CE/SE）", required = false)
	private String cssyainCd;

	/**
	 * 担当者名（CE/SE）
	 */
	@Schema(description = "担当者名（CE/SE）", required = false)
	private String cssyainNm;

	/**
	 * 課所CD（CE/SE）
	 */
	@Schema(description = "課所CD（CE/SE）", required = false)
	private String cskssCd;

	/**
	 * 課所名（CE/SE）
	 */
	@Schema(description = "課所名（CE/SE）", required = false)
	private String cskssName;

	/**
	 * 企業ID(E/U)
	 */
	@Schema(description = "企業ID(E/U)", required = false)
	private String ekigyoId;

	/**
	 * 事業所ID(E/U)
	 */
	@Schema(description = "事業所ID(E/U)", required = false)
	private String ejgsId;

	/**
	 * 企事部ID(E/U)
	 */
	@Schema(description = "企事部ID(E/U)", required = false)
	private String ekijibId;

	/**
	 * 企業名(E/U)
	 */
	@Schema(description = "企業名(E/U)", required = false)
	private String ekigyoNm;

	/**
	 * 企業名(事業所)(E/U)
	 */
	@Schema(description = "企業名(事業所)(E/U)", required = false)
	private String ejgsNm;

	/**
	 * 企業ID(売上)
	 */
	@Schema(description = "企業ID(売上)", required = false)
	private String ukigyoId;

	/**
	 * 事業所ID(売上)
	 */
	@Schema(description = "事業所ID(売上)", required = false)
	private String ujgsId;

	/**
	 * 企事部ID(売上)
	 */
	@Schema(description = "企事部ID(売上)", required = false)
	private String ukijibId;

	/**
	 * 企業名(売上)
	 */
	@Schema(description = "企業名(売上)", required = false)
	private String ukigyoNm;

	/**
	 * 企業名(事業所)(売上)
	 */
	@Schema(description = "企業名(事業所)(売上)", required = false)
	private String ujgsNm;

	/**
	 * 企事部名(売上)
	 */
	@Schema(description = "企事部名(売上)", required = false)
	private String ubmnNm;

	/**
	 * 得意先CD
	 */
	@Schema(description = "得意先CD", required = false)
	private String tokuCd;

	/**
	 * 得意先名
	 */
	@Schema(description = "得意先名", required = false)
	private String tokuNm;

	/**
	 * 機種略号・機番
	 */
	@Schema(description = "機種略号・機番", required = false)
	private String kisyuKiban;

	/**
	 * 支社CD（CE/SE）受付
	 */
	@Schema(description = "支社CD（CE/SE）受付", required = false)
	private String csrhnsyCd;

	/**
	 * 従業員番号（CE/SE）受付
	 */
	@Schema(description = "従業員番号（CE/SE）受付", required = false)
	private String csrsyainCd;

	/**
	 * 担当者名（CE/SE）受付
	 */
	@Schema(description = "担当者名（CE/SE）受付", required = false)
	private String csrsyainNm;

	/**
	 * 課所CD（CE/SE）受付
	 */
	@Schema(description = "課所CD（CE/SE）受付", required = false)
	private String csrkssCd;

	/**
	 * 課所名（CE/SE）受付
	 */
	@Schema(description = "課所名（CE/SE）受付", required = false)
	private String csrkssName;

	/**
	 * 支社CD（CE/SE）導入
	 */
	@Schema(description = "支社CD（CE/SE）導入", required = false)
	private String csihnsyCd;

	/**
	 * 従業員番号（CE/SE）導入
	 */
	@Schema(description = "従業員番号（CE/SE）導入", required = false)
	private String csisyainCd;

	/**
	 * 担当者名（CE/SE）導入
	 */
	@Schema(description = "担当者名（CE/SE）導入", required = false)
	private String csisyainNm;

	/**
	 * 課所CD（CE/SE）導入
	 */
	@Schema(description = "課所CD（CE/SE）導入", required = false)
	private String csikssCd;

	/**
	 * 課所名（CE/SE）導入
	 */
	@Schema(description = "課所名（CE/SE）導入", required = false)
	private String csikssName;

	/**
	 * 契約ID
	 */
	@Schema(description = "契約ID", required = false)
	private Long contractId;

	/**
	 * 旧文書フラグ
	 */
	@Schema(description = "旧文書フラグ", required = true)
	private boolean oldDocumentFlg;
	
	/**
	* 担当販社名
	*/
	@Schema(description = "担当販社名", required = false)
	private String hnsyNm;

	/**
	* 部門階層名1
	*/
	@Schema(description = "部門階層名1", required = false)
	private String bmnName1;

	/**
	* 部門階層名2
	*/
	@Schema(description = "部門階層名2", required = false)
	private String bmnName2;

	/**
	* 部門階層名3
	*/
	@Schema(description = "部門階層名3", required = false)
	private String bmnName3;

	/**
	* 部門階層名4
	*/
	@Schema(description = "部門階層名4", required = false)
	private String bmnName4;

	/**
	* 販社名（CE/SE）
	*/
	@Schema(description = "販社名（CE/SE）", required = false)
	private String cshnsyNm;

	/**
	* 部門階層名1(CE/SE)
	*/
	@Schema(description = "部門階層名1(CE/SE)", required = false)
	private String csbmnName1;

	/**
	* 部門階層名2(CE/SE)
	*/
	@Schema(description = "部門階層名2(CE/SE)", required = false)
	private String csbmnName2;

	/**
	* 部門階層名3(CE/SE)
	*/
	@Schema(description = "部門階層名3(CE/SE)", required = false)
	private String csbmnName3;

	/**
	* 部門階層名4(CE/SE)
	*/
	@Schema(description = "部門階層名4(CE/SE)", required = false)
	private String csbmnName4;

	/**
	* 支社名（CE/SE）受付
	*/
	@Schema(description = "支社名（CE/SE）受付", required = false)
	private String csrhnsyNm;

	/**
	* 部門階層名1(CE/SE) 受付
	*/
	@Schema(description = "部門階層名1(CE/SE) 受付", required = false)
	private String csrbmnName1;

	/**
	* 部門階層名2(CE/SE) 受付
	*/
	@Schema(description = "部門階層名2(CE/SE) 受付", required = false)
	private String csrbmnName2;

	/**
	* 部門階層名3(CE/SE) 受付
	*/
	@Schema(description = "部門階層名3(CE/SE) 受付", required = false)
	private String csrbmnName3;

	/**
	* 部門階層名4(CE/SE) 受付
	*/
	@Schema(description = "部門階層名4(CE/SE) 受付", required = false)
	private String csrbmnName4;

	/**
	* 支社名（CE/SE）導入
	*/
	@Schema(description = "支社名（CE/SE）導入", required = false)
	private String csihnsyNm;

	/**
	* 部門階層名1(CE/SE) 導入
	*/
	@Schema(description = "部門階層名1(CE/SE) 導入", required = false)
	private String csibmnName1;

	/**
	* 部門階層名2(CE/SE) 導入
	*/
	@Schema(description = "部門階層名2(CE/SE) 導入", required = false)
	private String csibmnName2;

	/**
	* 部門階層名3(CE/SE) 導入
	*/
	@Schema(description = "部門階層名3(CE/SE) 導入", required = false)
	private String csibmnName3;

	/**
	* 部門階層名4(CE/SE) 導入
	*/
	@Schema(description = "部門階層名4(CE/SE) 導入", required = false)
	private String csibmnName4;

	/**
	* 正式帳票名
	*/
	@Schema(description = "正式帳票名", required = false)
	private String siskThNm;

	/**
	* 識別区分
	*/
	@Schema(description = "識別区分", required = false)
	private Integer skbtKbn;

	/**
	* 契約金額
	*/
	@Schema(description = "契約金額", required = false)
	private BigDecimal kiykKngk;

	/**
	* 契約締結日
	*/
	@Schema(description = "契約締結日", required = false)
	@Temporal(TemporalType.DATE)
	private Date kiykTiktHi;

	/**
	* 契約種類区分
	*/
	@Schema(description = "契約種類区分", required = false)
	private String keiyakType;

	/**
	* FFM発注問い合わせ番号
	*/
	@Schema(description = "FFM発注問い合わせ番号", required = false)
	private String ffmHttiawsNo;

	/**
	* V-UP見積番号
	*/
	@Schema(description = "V-UP見積番号", required = false)
	private String vupMtmriNo;

	/**
	* V-UP案件番号
	*/
	@Schema(description = "V-UP案件番号", required = false)
	private String vupAnknNo;

	/**
	* サービス開始日
	*/
	@Schema(description = "サービス開始日", required = false)
	@Temporal(TemporalType.DATE)
	private Date serKisHi;

	/**
	* サービス利用希望日
	*/
	@Schema(description = "サービス利用希望日", required = false)
	@Temporal(TemporalType.DATE)
	private Date serRyuKbuHi;
}
