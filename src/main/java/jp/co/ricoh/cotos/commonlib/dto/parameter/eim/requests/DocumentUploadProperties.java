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
}
