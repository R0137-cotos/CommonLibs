package jp.co.ricoh.cotos.commonlib.dto.parameter.contract.intramart;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * 登録情報DTO
 */
@Data
public class RegisterInfoDto {

	/**
	 * 作成者
	 */
	@JsonProperty("create_user_cd")
	private String createUserCd;

	/**
	 * 起票者
	 */
	@JsonProperty("kihyousha")
	private String kihyousha;

	/**
	 * 承認者
	 */
	@JsonProperty("shouninsha")
	private String shouninsha;

	/**
	 * 文書番号
	 */
	@JsonProperty("documentId")
	private String documentId;

	/**
	 * 案件名
	 */
	@JsonProperty("anken_nm")
	private String ankenNm;

	/**
	 * 設置先会社名
	 */
	@JsonProperty("anken_okyaku_nm")
	private String ankenOkyakuNm;

	/**
	 * 設置先事業所
	 */
	@JsonProperty("anken_jigyosyo_nm")
	private String ankenJigyosyoNm;

	/**
	 * 設置先住所
	 */
	@JsonProperty("ankenjouhou_add")
	private String ankenjouhouAdd;

	/**
	 * 設置先TEL
	 */
	@JsonProperty("anken_tel")
	private String ankenTel;

	/**
	 * 部署
	 */
	@JsonProperty("bushomei")
	private String bushomei;

	/**
	 * 設置先担当者
	 */
	@JsonProperty("anken_tanto_user_nm")
	private String ankenTantoUserNm;

	/**
	 * 課所コード（SSコード）
	 */
	@JsonProperty("irai_busyo_code")
	private String iraiBusyoCode;

	/**
	 * 作業依頼先部署全員
	 */
	@JsonProperty("irai_busho_all")
	private String iraiBushoAll;

	/**
	 * 依頼作業日
	 */
	@JsonProperty("sagyo_date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd", timezone = "JST")
	private String sagyoDate;

	/**
	 * 作業依頼項目
	 */
	@JsonProperty("sagyo_irai")
	private String sagyoIrai;

	/**
	 * 機器情報
	 */
	@JsonProperty("kiki_joho")
	private String kikiJoho;

	/**
	 * 訪問CE決定連絡納期
	 */
	@JsonProperty("ce_renraku_noki")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd", timezone = "JST")
	private String ceRenrakuNoki;

	/**
	 * 作業時間
	 */
	@JsonProperty("sagyo_jikan_kbn")
	private String sagyoJikanKbn;

	/**
	 * 作業時間2
	 */
	@JsonProperty("sagyou_jikan2")
	private String sagyouJikan2;

	/**
	 * 案件補足情報・手順書
	 */
	@JsonProperty("tejunsyo_kbn")
	private String tejunsyoKbn;

	/**
	 * データバックアップ確認書の現地での取得
	 */
	@JsonProperty("backup_kbn")
	private String backupKbn;

	/**
	 * 治工具
	 */
	@JsonProperty("jikogu")
	private String jikogu;

	/**
	 * 送付物
	 */
	@JsonProperty("sofubutu")
	private String sofubutu;

	/**
	 * 返却物
	 */
	@JsonProperty("henkyaku_butu")
	private String henkyakuButu;

	/**
	 * 返却管理
	 */
	@JsonProperty("henkyaku_kanri")
	private String henkyakuKanri;

	/**
	 * 保守レポートの提出
	 */
	@JsonProperty("report_teisyutu")
	private String reportTeisyutu;

	/**
	 * Isys-One機器登録
	 */
	@JsonProperty("sysone_kiki_reg")
	private String sysoneKikiReg;

	/**
	 * Isys-OneCE登録
	 */
	@JsonProperty("sysone_ce_reg")
	private String sysoneCeReg;

	/**
	 * 車両情報
	 */
	@JsonProperty("syaro_joho")
	private String syaroJoho;

	/**
	 * 保守契約依頼
	 */
	@JsonProperty("hosyu_keiyaku_irai")
	private String hosyuKeiyakuIrai;

	/**
	 * 完了報告
	 */
	@JsonProperty("kanryo_hokoku")
	private String kanryoHokoku;

	/**
	 * 到着連絡
	 */
	@JsonProperty("totyaku_renraku")
	private String totyakuRenraku;

	/**
	 * 作業開始連絡
	 */
	@JsonProperty("sagyo_start_renraku")
	private String sagyoStartRenraku;

	/**
	 * 作業完了連絡
	 */
	@JsonProperty("sagyo_kanryo_renraku")
	private String sagyoKanryoRenraku;

	/**
	 * 退出連絡
	 */
	@JsonProperty("taisyutu_renraku")
	private String taisyutuRenraku;

	/**
	 * FFM連携
	 */
	@JsonProperty("ffm_renkei")
	private String ffmRenkei;

	/**
	 * 督促
	 */
	@JsonProperty("tokusoku")
	private String tokusoku;

	/**
	 * 作業員報告
	 */
	@JsonProperty("sagyoin_hokoku")
	private String sagyoinHokoku;

	/**
	 * アサイン期日
	 */
	@JsonProperty("hakoumoto_renraku")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd", timezone = "JST")
	private String hakoumotoRenraku;

	/**
	 * TeMS受注№
	 */
	@JsonProperty("toiawse_no")
	private String toiawseNo;

	/**
	 * 入室約束時間
	 */
	@JsonProperty("in_yakusoku_time")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:MI", timezone = "JST")
	private String inYakusokuTime;

	/**
	 * 作業開始時間
	 */
	@JsonProperty("sagyo_start_time")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:MI", timezone = "JST")
	private String sagyoStartTime;

	/**
	 * 作業拘束時間
	 */
	@JsonProperty("kousoku_jikan")
	private Integer kousokuJikan;

	/**
	 * CEアサイン数
	 */
	@JsonProperty("ce_assign_su")
	private Integer ceAssignSu;

	/**
	 * 作業料金
	 */
	@JsonProperty("sagyo_kingaku")
	private Integer sagyoKingaku;

	/**
	 * 個別内容
	 */
	@JsonProperty("kobetu_naiyo")
	private String kobetuNaiyo;

	/**
	 * 緊急依頼承認者
	 */
	@JsonProperty("ritos_kinkyu_user")
	private String ritosKinkyuUser;

	/**
	 * 承認日
	 */
	@JsonProperty("shounin_date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd", timezone = "JST")
	private String shouninDate;

	/**
	 * WWFリンク
	 */
	@JsonProperty("ritos_link")
	private String ritosLink;

	/**
	 * 共同編集者
	 */
	@JsonProperty("khs_data")
	private KhsData[] khsData;

	@Data
	public static class KhsData {

		/**
		 * ユーザーコード
		 */
		@JsonProperty("khs_mail")
		private String khsMail;
	}

	/**
	 * メーカー/機種名
	 */
	@JsonProperty("marker_data")
	private MarkerData[] markerData;

	@Data
	public static class MarkerData {

		/**
		 * メーカー/機種名
		 */
		@JsonProperty("marker_detail_nm")
		private String markerDetailNm;

		/**
		 * 設置台数
		 */
		@JsonProperty("marker_detail_daisu")
		private Integer markerDetailDaisu;
	}

	/**
	 * 送付物
	 */
	@JsonProperty("sofu_data")
	private SofuData[] sofuData;

	@Data
	public static class SofuData {

		/**
		 * 何を
		 */
		@JsonProperty("sofu_detail_what")
		private String sofuDetailWhat;

		/**
		 * いつまでに
		 */
		@JsonProperty("sofu_detail_when")
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd", timezone = "JST")
		private String sofuDetailWhen;

		/**
		 * どこに
		 */
		@JsonProperty("sofu_detail_to")
		private String sofuDetailTo;

		/**
		 * どこから
		 */
		@JsonProperty("sofu_detail_from")
		private String sofuDetailFrom;

		/**
		 * コメント（便名、伝票No等)
		 */
		@JsonProperty("sofu_comment")
		private String sofuComment;

		/**
		 * 出荷日
		 */
		@JsonProperty("sofu_deli_date")
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd", timezone = "JST")
		private String sofuDeliDate;

		/**
		 * 確認日
		 */
		@JsonProperty("sofu_kakunin_date")
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd", timezone = "JST")
		private String sofuKakuninDate;
	}

}
