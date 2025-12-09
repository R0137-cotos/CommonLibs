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
	@Schema(description = "ID(作成時不要)", allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 文書ユニークキー
	 */
	@Schema(description = "文書ユニークキー", requiredMode = Schema.RequiredMode.REQUIRED)
	private String documentUniqueId;

	/**
	 * 文書ID
	 */
	@Schema(description = "文書ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String documentId;

	/**
	 * 文書キー
	 */
	@Schema(description = "文書キー", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String documentKey;

	/**
	 * EIM連携済状態
	 */
	@Schema(description = "EIM連携済状態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未連携(\"0\"), 連携済(\"1\"), 対象外(\"9\")")
	private EimLinkedStatus eimLinkedStatus;

	/**
	 * 最終連携実施時刻
	 */
	@Schema(description = "最終連携実施時刻", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date lastLinkedAt;

	/**
	 * 文書タイトル
	 */
	@Schema(description = "文書タイトル", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String title;

	/**
	 * 契約No
	 */
	@Schema(description = "契約No", requiredMode = Schema.RequiredMode.REQUIRED)
	private String keiyakNo;

	/**
	 * 担当販社CD
	 */
	@Schema(description = "担当販社CD", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String hnsyCd;

	/**
	 * 従業員番号
	 */
	@Schema(description = "従業員番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String syainCd;

	/**
	 * 担当者名
	 */
	@Schema(description = "担当者名", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String syainNm;

	/**
	 * 課所CD
	 */
	@Schema(description = "課所CD", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String kssCd;

	/**
	 * 課所名
	 */
	@Schema(description = "課所名", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String kssName;

	/**
	 * 販社CD（CE/SE）
	 */
	@Schema(description = "販社CD（CE/SE）", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String cshnsyCd;

	/**
	 * 従業員番号（CE/SE）
	 */
	@Schema(description = "従業員番号（CE/SE）", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String cssyainCd;

	/**
	 * 担当者名（CE/SE）
	 */
	@Schema(description = "担当者名（CE/SE）", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String cssyainNm;

	/**
	 * 課所CD（CE/SE）
	 */
	@Schema(description = "課所CD（CE/SE）", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String cskssCd;

	/**
	 * 課所名（CE/SE）
	 */
	@Schema(description = "課所名（CE/SE）", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String cskssName;

	/**
	 * 企業ID(E/U)
	 */
	@Schema(description = "企業ID(E/U)")
	private String ekigyoId;

	/**
	 * 事業所ID(E/U)
	 */
	@Schema(description = "事業所ID(E/U)")
	private String ejgsId;

	/**
	 * 企事部ID(E/U)
	 */
	@Schema(description = "企事部ID(E/U)")
	private String ekijibId;

	/**
	 * 企業名(E/U)
	 */
	@Schema(description = "企業名(E/U)")
	private String ekigyoNm;

	/**
	 * 企業名(事業所)(E/U)
	 */
	@Schema(description = "企業名(事業所)(E/U)")
	private String ejgsNm;

	/**
	 * 企業ID(売上)
	 */
	@Schema(description = "企業ID(売上)")
	private String ukigyoId;

	/**
	 * 事業所ID(売上)
	 */
	@Schema(description = "事業所ID(売上)")
	private String ujgsId;

	/**
	 * 企事部ID(売上)
	 */
	@Schema(description = "企事部ID(売上)")
	private String ukijibId;

	/**
	 * 企業名(売上)
	 */
	@Schema(description = "企業名(売上)")
	private String ukigyoNm;

	/**
	 * 企業名(事業所)(売上)
	 */
	@Schema(description = "企業名(事業所)(売上)")
	private String ujgsNm;

	/**
	 * 企事部名(売上)
	 */
	@Schema(description = "企事部名(売上)")
	private String ubmnNm;

	/**
	 * 得意先CD
	 */
	@Schema(description = "得意先CD", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String tokuCd;

	/**
	 * 得意先名
	 */
	@Schema(description = "得意先名", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String tokuNm;

	/**
	 * 機種略号・機番
	 */
	@Schema(description = "機種略号・機番", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String kisyuKiban;

	/**
	 * 支社CD（CE/SE）受付
	 */
	@Schema(description = "支社CD（CE/SE）受付", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String csrhnsyCd;

	/**
	 * 従業員番号（CE/SE）受付
	 */
	@Schema(description = "従業員番号（CE/SE）受付", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String csrsyainCd;

	/**
	 * 担当者名（CE/SE）受付
	 */
	@Schema(description = "担当者名（CE/SE）受付", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String csrsyainNm;

	/**
	 * 課所CD（CE/SE）受付
	 */
	@Schema(description = "課所CD（CE/SE）受付", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String csrkssCd;

	/**
	 * 課所名（CE/SE）受付
	 */
	@Schema(description = "課所名（CE/SE）受付", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String csrkssName;

	/**
	 * 支社CD（CE/SE）導入
	 */
	@Schema(description = "支社CD（CE/SE）導入", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String csihnsyCd;

	/**
	 * 従業員番号（CE/SE）導入
	 */
	@Schema(description = "従業員番号（CE/SE）導入", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String csisyainCd;

	/**
	 * 担当者名（CE/SE）導入
	 */
	@Schema(description = "担当者名（CE/SE）導入", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String csisyainNm;

	/**
	 * 課所CD（CE/SE）導入
	 */
	@Schema(description = "課所CD（CE/SE）導入", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String csikssCd;

	/**
	 * 課所名（CE/SE）導入
	 */
	@Schema(description = "課所名（CE/SE）導入", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String csikssName;

	/**
	 * 契約ID
	 */
	@Schema(description = "契約ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Long contractId;

	/**
	 * 旧文書フラグ
	 */
	@Schema(description = "旧文書フラグ", requiredMode = Schema.RequiredMode.REQUIRED)
	private boolean oldDocumentFlg;
	
	/**
	* 担当販社名
	*/
	@Schema(description = "担当販社名", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String hnsyNm;

	/**
	* 部門階層名1
	*/
	@Schema(description = "部門階層名1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String bmnName1;

	/**
	* 部門階層名2
	*/
	@Schema(description = "部門階層名2", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String bmnName2;

	/**
	* 部門階層名3
	*/
	@Schema(description = "部門階層名3", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String bmnName3;

	/**
	* 部門階層名4
	*/
	@Schema(description = "部門階層名4", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String bmnName4;

	/**
	* 販社名（CE/SE）
	*/
	@Schema(description = "販社名（CE/SE）", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String cshnsyNm;

	/**
	* 部門階層名1(CE/SE)
	*/
	@Schema(description = "部門階層名1(CE/SE)")
	private String csbmnName1;

	/**
	* 部門階層名2(CE/SE)
	*/
	@Schema(description = "部門階層名2(CE/SE)")
	private String csbmnName2;

	/**
	* 部門階層名3(CE/SE)
	*/
	@Schema(description = "部門階層名3(CE/SE)")
	private String csbmnName3;

	/**
	* 部門階層名4(CE/SE)
	*/
	@Schema(description = "部門階層名4(CE/SE)")
	private String csbmnName4;

	/**
	* 支社名（CE/SE）受付
	*/
	@Schema(description = "支社名（CE/SE）受付", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String csrhnsyNm;

	/**
	* 部門階層名1(CE/SE) 受付
	*/
	@Schema(description = "部門階層名1(CE/SE) 受付")
	private String csrbmnName1;

	/**
	* 部門階層名2(CE/SE) 受付
	*/
	@Schema(description = "部門階層名2(CE/SE) 受付")
	private String csrbmnName2;

	/**
	* 部門階層名3(CE/SE) 受付
	*/
	@Schema(description = "部門階層名3(CE/SE) 受付")
	private String csrbmnName3;

	/**
	* 部門階層名4(CE/SE) 受付
	*/
	@Schema(description = "部門階層名4(CE/SE) 受付")
	private String csrbmnName4;

	/**
	* 支社名（CE/SE）導入
	*/
	@Schema(description = "支社名（CE/SE）導入", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String csihnsyNm;

	/**
	* 部門階層名1(CE/SE) 導入
	*/
	@Schema(description = "部門階層名1(CE/SE) 導入")
	private String csibmnName1;

	/**
	* 部門階層名2(CE/SE) 導入
	*/
	@Schema(description = "部門階層名2(CE/SE) 導入")
	private String csibmnName2;

	/**
	* 部門階層名3(CE/SE) 導入
	*/
	@Schema(description = "部門階層名3(CE/SE) 導入")
	private String csibmnName3;

	/**
	* 部門階層名4(CE/SE) 導入
	*/
	@Schema(description = "部門階層名4(CE/SE) 導入")
	private String csibmnName4;

	/**
	* 正式帳票名
	*/
	@Schema(description = "正式帳票名", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String siskThNm;

	/**
	* 識別区分
	*/
	@Schema(description = "識別区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Integer skbtKbn;

	/**
	* 契約金額
	*/
	@Schema(description = "契約金額", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private BigDecimal kiykKngk;

	/**
	* 契約締結日
	*/
	@Schema(description = "契約締結日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Temporal(TemporalType.DATE)
	private Date kiykTiktHi;

	/**
	* 契約種類区分
	*/
	@Schema(description = "契約種類区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String keiyakType;

	/**
	* FFM発注問い合わせ番号
	*/
	@Schema(description = "FFM発注問い合わせ番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String ffmHttiawsNo;

	/**
	* V-UP見積番号
	*/
	@Schema(description = "V-UP見積番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String vupMtmriNo;

	/**
	* V-UP案件番号
	*/
	@Schema(description = "V-UP案件番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String vupAnknNo;

	/**
	* サービス開始日
	*/
	@Schema(description = "サービス開始日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Temporal(TemporalType.DATE)
	private Date serKisHi;

	/**
	* サービス利用希望日
	*/
	@Schema(description = "サービス利用希望日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Temporal(TemporalType.DATE)
	private Date serRyuKbuHi;
}
