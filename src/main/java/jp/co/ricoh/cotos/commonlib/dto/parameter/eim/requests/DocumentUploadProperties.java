package jp.co.ricoh.cotos.commonlib.dto.parameter.eim.requests;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jp.co.ricoh.cotos.commonlib.entity.EnumType.EimLinkedStatus;
import lombok.Data;

/**
 * DocumentUploadProperties
 */

@Data
public class DocumentUploadProperties {

	/**
	 * systemName
	 */
	private String systemName;

	/**
	 * fileBody
	 */
	private List<String> fileBody;

	//以下EIM書誌情報※ただし、「最終連携実施時刻」およびEntityBaseのカラムは不要
	/**
	 * 文書ユニークキー
	 */
	private String documentUniqueID;

	/**
	 * 文書ID
	 */
	private String documentId;

	/**
	 * 文書キー
	 */
	private String documentKey;

	/**
	 * EIM連携済状態
	 */
	private EimLinkedStatus eimLinkedStatus;

	/**
	 * 文書タイトル
	 */
	private String title;

	/**
	 * 契約No
	 */
	private String keiyakNo;

	/**
	 * 担当販社CD
	 */
	private String hnsyCd;

	/**
	 * 従業員番号
	 */
	private String syainCd;

	/**
	 * 担当者名
	 */
	private String syainNm;

	/**
	 * 課所CD
	 */
	private String kssCd;

	/**
	 * 課所名
	 */
	private String kssName;

	/**
	 * 販社CD（CE/SE）
	 */
	private String cshnsyCd;

	/**
	 * 従業員番号（CE/SE）
	 */
	private String cssyainCd;

	/**
	 * 担当者名（CE/SE）
	 */
	private String cssyainNm;

	/**
	 * 課所CD（CE/SE）
	 */
	private String cskssCd;

	/**
	 * 課所名（CE/SE）
	 */
	private String cskssName;

	/**
	 * 企業ID(E/U)
	 */
	private String ekigyoId;

	/**
	 * 事業所ID(E/U)
	 */
	private String ejgsId;

	/**
	 * 企事部ID(E/U)
	 */
	private String ekijibId;

	/**
	 * 企業名(E/U)
	 */
	private String ekigyoNm;

	/**
	 * 企業名(事業所)(E/U)
	 */
	private String ejgsNm;

	/**
	 * 企業ID(売上)
	 */
	private String ukigyoId;

	/**
	 * 事業所ID(売上)
	 */
	private String ujgsId;

	/**
	 * 企業名(事業所)(売上)
	 */
	private String ujgsNm;

	/**
	 * 企事部ID(売上)
	 */
	private String ukijibId;

	/**
	 * 企業名(売上)
	 */
	private String ukigyoNm;

	/**
	 * 企事部名(売上)
	 */
	private String ubmnNm;

	/**
	 * 得意先CD
	 */
	private String tokuCd;

	/**
	 * 得意先名
	 */
	private String tokuNm;

	/**
	 * 機種略号・機番
	 */
	@JsonProperty("kisyu_kiban")
	private String kisyuKiban;

	/**
	 * 支社CD（CE/SE）受付
	 */
	private String csrhnsyCd;

	/**
	 * 従業員番号（CE/SE）受付
	 */
	private String csrsyainCd;

	/**
	 * 担当者名（CE/SE）受付
	 */
	private String csrsyainNm;

	/**
	 * 課所CD（CE/SE）受付
	 */
	private String csrkssCd;

	/**
	 * 課所名（CE/SE）受付
	 */
	private String csrkssName;

	/**
	 * 支社CD（CE/SE）導入
	 */
	private String csihnsyCd;

	/**
	 * 従業員番号（CE/SE）導入
	 */
	private String csisyainCd;

	/**
	 * 担当者名（CE/SE）導入
	 */
	private String csisyainNm;

	/**
	 * 課所CD（CE/SE）導入
	 */
	private String csikssCd;

	/**
	 * 課所名（CE/SE）導入
	 */
	private String csikssName;

	/**
	 * 添付ファイル数
	 */
	private long fileCount;

	/**
	 * 削除フラグ
	 */
	private int delFlg;

	/**
	* 担当販社名
	*/
	private String hnsyNm;

	/**
	* 部門階層名1
	*/
	@JsonProperty("bmnName_1")
	private String bmnName1;

	/**
	* 部門階層名2
	*/
	@JsonProperty("bmnName_2")
	private String bmnName2;

	/**
	* 部門階層名3
	*/
	@JsonProperty("bmnName_3")
	private String bmnName3;

	/**
	* 部門階層名4
	*/
	@JsonProperty("bmnName_4")
	private String bmnName4;

	/**
	* 販社名（CE/SE）
	*/
	private String cshnsyNm;

	/**
	* 部門階層名1(CE/SE)
	*/
	@JsonProperty("csbmnName_1")
	private String csbmnName1;

	/**
	* 部門階層名2(CE/SE)
	*/
	@JsonProperty("csbmnName_2")
	private String csbmnName2;

	/**
	* 部門階層名3(CE/SE)
	*/
	@JsonProperty("csbmnName_3")
	private String csbmnName3;

	/**
	* 部門階層名4(CE/SE)
	*/
	@JsonProperty("csbmnName_4")
	private String csbmnName4;

	/**
	* 支社名（CE/SE）受付
	*/
	private String csrhnsyNm;

	/**
	* 部門階層名1(CE/SE) 受付
	*/
	@JsonProperty("csrbmnName_1")
	private String csrbmnName1;

	/**
	* 部門階層名2(CE/SE) 受付
	*/
	@JsonProperty("csrbmnName_2")
	private String csrbmnName2;

	/**
	* 部門階層名3(CE/SE) 受付
	*/
	@JsonProperty("csrbmnName_3")
	private String csrbmnName3;

	/**
	* 部門階層名4(CE/SE) 受付
	*/
	@JsonProperty("csrbmnName_4")
	private String csrbmnName4;

	/**
	* 支社名（CE/SE）導入
	*/
	private String csihnsyNm;

	/**
	* 部門階層名1(CE/SE) 導入
	*/
	@JsonProperty("csibmnName_1")
	private String csibmnName1;

	/**
	* 部門階層名2(CE/SE) 導入
	*/
	@JsonProperty("csibmnName_2")
	private String csibmnName2;

	/**
	* 部門階層名3(CE/SE) 導入
	*/
	@JsonProperty("csibmnName_3")
	private String csibmnName3;

	/**
	* 部門階層名4(CE/SE) 導入
	*/
	@JsonProperty("csibmnName_4")
	private String csibmnName4;

	/**
	* 正式帳票名
	*/
	@JsonProperty("sisk_thNm")
	private List<String> siskThNm;

	/**
	* 識別区分
	*/
	@JsonProperty("skbt_Kbn")
	private String skbtKbn;

	/**
	* 契約金額
	*/
	@JsonProperty("kiyk_Kngk")
	private Long kiykKngk;

	/**
	* 契約締結日
	*/
	private String kiykTiktHi;

	/**
	* 契約種類区分
	*/
	private String keiyakType;

	/**
	* FFM発注問い合わせ番号
	*/
	@JsonProperty("FFM_httiawsNo")
	private String ffmHttiawsNo;

	/**
	* V-UP見積番号
	*/
	@JsonProperty("V-UP_mtmriNo")
	private String vupMtmriNo;

	/**
	* V-UP案件番号
	*/
	@JsonProperty("V-UP_anknNo")
	private String vupAnknNo;

	/**
	* サービス開始日
	*/
	private String serKisHi;

	/**
	* サービス利用希望日
	*/
	private String serRyuKbuHi;
}