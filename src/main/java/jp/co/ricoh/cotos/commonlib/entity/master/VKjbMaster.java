package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.Arrays;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 企事部マスタ
 */
@Entity
@Data
@Table(name = "v_kjb_master")
public class VKjbMaster {

	@Description(value = "企事部設定区分")
	public enum DepartmentDiv {

		企事("1"), 企事部("2");

		private final String text;

		private DepartmentDiv(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static DepartmentDiv fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	/**
	 * MoM顧客システム連携ID
	 */
	@Id
	@Column(length = 15)
	@Schema(description = "MoM顧客システム連携ID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,15]")
	private String mclMomRelId;

	/**
	 * MoM顧客企事部ID
	 */
	@Column(length = 15)
	@Schema(description = "MoM顧客企事部ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,15]")
	private String mclMomKjbId;

	/**
	 * 販社ｺｰﾄﾞ
	 */
	@Column(length = 3)
	@Schema(description = "販社ｺｰﾄﾞ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String mclHanshCd;

	/**
	 * 最終更新日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "最終更新日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date mclUpdtDt;

	/**
	 * ﾊﾞｯﾁ処理見做し日
	 */
	@Column(length = 8)
	@Schema(description = "ﾊﾞｯﾁ処理見做し日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String mclBtPrcsAsofDt;

	/**
	 * ﾊﾞｯﾁ処理日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "ﾊﾞｯﾁ処理日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date mclBtPrcsDt;

	/**
	 * 無効フラグ
	 */
	@Column(length = 1)
	@Schema(description = "無効フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,1]")
	private String mclDltFlg;

	/**
	 * 更新ﾕｰｻﾞID
	 */
	@Column(length = 64)
	@Schema(description = "更新ﾕｰｻﾞID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,64]")
	private String mclUpdtUserId;

	/**
	 * 担当者ID
	 */
	@Column(length = 15)
	@Schema(description = "担当者ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,15]")
	private String mclTntoshId;

	/**
	 * 作成元システム
	 */
	@Column(length = 2)
	@Schema(description = "作成元システム", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,2]")
	private String mclCrtSysCd;

	/**
	 * MoM顧客ID
	 */
	@Column(length = 15)
	@Schema(description = "MoM顧客ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,15]")
	private String prflMomCstId;

	/**
	 * MoM企業ID
	 */
	@Column(length = 15)
	@Schema(description = "MoM企業ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,15]")
	private String prflMomKgyId;

	/**
	 * MoM事業所ID
	 */
	@Column(length = 15)
	@Schema(description = "MoM事業所ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,15]")
	private String prflMomJgsId;

	/**
	 * MoM部門ID
	 */
	@Column(length = 15)
	@Schema(description = "MoM部門ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,15]")
	private String prflMomBmnId;

	/**
	 * 名寄せ親MoM顧客ID
	 */
	@Column(length = 15)
	@Schema(description = "名寄せ親MoM顧客ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,15]")
	private String prflNyoOyaMomCstId;

	/**
	 * 企事部設定区分
	 */
	@Column(length = 1)
	@Schema(description = "企事部設定区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "企事(\"1\"), 企事部(\"2\")", example = "1")
	private DepartmentDiv prflKjbSetKbn;

	/**
	 * 無効フラグ
	 */
	@Column(length = 1)
	@Schema(description = "無効フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,1]")
	private String prflDltFlg;

	/**
	 * WEB作成日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "WEB作成日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date prflScWebCrtDt;

	/**
	 * WEB作成担当者ユーザID
	 */
	@Column(length = 64)
	@Schema(description = "WEB作成担当者ユーザID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,64]")
	private String prflScWebCrtUserId;

	/**
	 * WEB作成担当者販社コード
	 */
	@Column(length = 3)
	@Schema(description = "WEB作成担当者販社コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,64]")
	private String prflScWebCrtHanshCd;

	/**
	 * WEB作成機能コード
	 */
	@Column(length = 3)
	@Schema(description = "WEB作成機能コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String prflScWebCrtKinoCd;

	/**
	 * WEB更新日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "WEB更新日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date prflScWebUpdtDt;

	/**
	 * WEB更新担当者ユーザID
	 */
	@Column(length = 64)
	@Schema(description = "WEB更新担当者ユーザID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,64]")
	private String prflScWebUpdtUserId;

	/**
	 * WEB更新担当者販社コード
	 */
	@Column(length = 3)
	@Schema(description = "WEB更新担当者販社コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String prflScWebUpdtHanshCd;

	/**
	 * WEB更新機能コード
	 */
	@Column(length = 3)
	@Schema(description = "WEB更新機能コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String prflScWebUpdtKinoCd;

	/**
	 * バッチ作成機能コード
	 */
	@Column(length = 3)
	@Schema(description = "バッチ作成機能コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String prflScBtCrtKinoCd;

	/**
	 * バッチ作成処理見做し日
	 */
	@Column(length = 8)
	@Schema(description = "バッチ作成処理見做し日", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,8]")
	private String prflScBtCrtAsofDt;

	/**
	 * バッチ作成日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "バッチ作成日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date prflScBtCrtDt;

	/**
	 * バッチ更新機能コード
	 */
	@Column(length = 3)
	@Schema(description = "バッチ更新機能コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String prflScBtUpdtKinoCd;

	/**
	 * バッチ更新処理見做し日
	 */
	@Column(length = 8)
	@Schema(description = "バッチ更新処理見做し日", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,8]")
	private String prflScBtUpdtAsofDt;

	/**
	 * バッチ更新日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "バッチ更新日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date prflScBtUpdtDt;

	/**
	 * システム管理対応日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "システム管理対応日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date prflScSysKnrDt;

	/**
	 * システム管理NO
	 */
	@Column(length = 5)
	@Schema(description = "システム管理NO", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,5]")
	private String prflScSysKnrNo;

	/**
	 * MoM最終更新機能コード
	 */
	@Column(length = 3)
	@Schema(description = "MoM最終更新機能コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String prflScMomUpdtKinoCd;

	/**
	 * MoM最終更新日時時刻
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Schema(description = "MoM最終更新日時時刻", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date prflScMomUpdtDtTm;

	/**
	 * 企業名_漢字
	 */
	@Column(length = 202)
	@Schema(description = "企業名_漢字", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,202]")
	private String kgyKgyNmKnji;

	/**
	 * 企業名_カナ
	 */
	@Column(length = 202)
	@Schema(description = "企業名_カナ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,202]")
	private String kgyKgyNmKana;

	/**
	 * 企業法人格区分
	 */
	@Column(length = 2)
	@Schema(description = "企業法人格区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,2]")
	private String kgyHjnKakuCd;

	/**
	 * 企業法人格前後区分
	 */
	@Column(length = 1)
	@Schema(description = "企業法人格前後区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,1]")
	private String kgyHjnKakuZengoCd;

	/**
	 * 企業郵便番号
	 */
	@Column(length = 50)
	@Schema(description = "企業郵便番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,50]")
	private String kgyKgyPostNum;

	/**
	 * 企業住所コード
	 */
	@Column(length = 11)
	@Schema(description = "企業住所コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,11]")
	private String kgyKgyAdsCd;

	/**
	 * 企業住所字通称名
	 */
	@Column(length = 500)
	@Schema(description = "企業住所字通称名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,500]")
	private String kgyKgyAdsAzatusyoNm;

	/**
	 * 企業住所番地名
	 */
	@Column(length = 20)
	@Schema(description = "企業住所番地名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,20]")
	private String kgyKgyAdsBantiNm;

	/**
	 * 企業住所号名
	 */
	@Column(length = 20)
	@Schema(description = "企業住所号名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,20]")
	private String kgyKgyAdsGoNm;

	/**
	 * 企業住所ビル名
	 */
	@Column(length = 100)
	@Schema(description = "企業住所ビル名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,100]")
	private String kgyKgyAdsBldgNm;

	/**
	 * 企業住所フロア名
	 */
	@Column(length = 100)
	@Schema(description = "企業住所フロア名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,100]")
	private String kgyKgyAdsFlorNm;

	/**
	 * 企業代表TEL
	 */
	@Column(length = 50)
	@Schema(description = "企業代表TEL", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,50]")
	private String kgyKgyTelNum;

	/**
	 * 企業代表FAX
	 */
	@Column(length = 40)
	@Schema(description = "企業代表FAX", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,40]")
	private String kgyKgyFaxNum;

	/**
	 * 企業ホームページURL
	 */
	@Column(length = 128)
	@Schema(description = "企業ホームページURL", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,128]")
	private String kgyKgyHpUrl;

	/**
	 * 企業業種コード
	 */
	@Column(length = 5)
	@Schema(description = "企業業種コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,5]")
	private String kgyKgyGyshCd;

	/**
	 * 企業従業員規模コード
	 */
	@Column(length = 1)
	@Schema(description = "企業従業員規模コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,1]")
	private String kgyKgyJgyinKboCd;

	/**
	 * 企業従業員数
	 */
	@Schema(description = "企業従業員数", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Integer kgyKgyJgyinQty;

	/**
	 * 企業代表者氏名
	 */
	@Column(length = 42)
	@Schema(description = "企業代表者氏名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,42]")
	private String kgyKgyDhyshNmKnji;

	/**
	 * 企業代表者役職名
	 */
	@Column(length = 22)
	@Schema(description = "企業代表者役職名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,22]")
	private String kgyKgyDhyshYksykNmKnji;

	/**
	 * 企業COSMOSコード
	 */
	@Column(length = 9)
	@Schema(description = "企業代表者役職名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private String kgyKgyNumCsmsCd;

	/**
	 * 企業名略称_1
	 */
	@Column(length = 40)
	@Schema(description = "企業名略称_1", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,40]")
	private String kgyKgySplNm1;

	/**
	 * 企業名略称_2
	 */
	@Column(length = 40)
	@Schema(description = "企業名略称_2", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,40]")
	private String kgyKgySplNm2;

	/**
	 * 企業資本金_千円
	 */
	@Schema(description = "企業資本金_千円", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Long kgyKgyShnkn;

	/**
	 * 企業仕入先メモ
	 */
	@Column(length = 62)
	@Schema(description = "企業仕入先メモ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,62]")
	private String kgyKgySirsk;

	/**
	 * 企業販売先メモ
	 */
	@Column(length = 62)
	@Schema(description = "企業販売先メモ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,62]")
	private String kgyKgyHnbisk;

	/**
	 * 企業事業所数
	 */
	@Schema(description = "企業事業所数", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Integer kgyKgyJgsQty;

	/**
	 * 企業倒産閉鎖状態区分
	 */
	@Column(length = 1)
	@Schema(description = "企業倒産閉鎖状態区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,1]")
	private String kgyKgyTousanHeisaInfoKbn;

	/**
	 * 企業設立年月
	 */
	@Column(length = 6)
	@Schema(description = "企業設立年月", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,6]")
	private String kgyStrtsYearMs;

	/**
	 * 企業決算月
	 */
	@Column(length = 2)
	@Schema(description = "企業決算月", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,2]")
	private String kgyKsnkiMs;

	/**
	 * 名寄せ用企業名_漢字
	 */
	@Column(length = 202)
	@Schema(description = "名寄せ用企業名_漢字", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,202]")
	private String kgyNyoKgyNmKnji;

	/**
	 * 名寄せ用企業名_カナ
	 */
	@Column(length = 202)
	@Schema(description = "名寄せ用企業名_カナ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,202]")
	private String kgyNyoKgyNmKana;

	/**
	 * 名寄せ用企業名略称_1
	 */
	@Column(length = 40)
	@Schema(description = "名寄せ用企業名略称_1", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,40]")
	private String kgyNyoKgySplNm1;

	/**
	 * 名寄せ用企業名略称_2
	 */
	@Column(length = 40)
	@Schema(description = "名寄せ用企業名略称_2", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,40]")
	private String kgyNyoKgySplNm2;

	/**
	 * 与信限度額
	 */
	@Schema(description = "与信限度額", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Long kgyYoshinLimit;

	/**
	 * MA用民間顧客識別区分
	 */
	@Column(length = 1)
	@Schema(description = "MA用民間顧客識別区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,1]")
	private String kgyMknKokykSkbtKbn;

	/**
	 * MA対象フラグ
	 */
	@Column(length = 1)
	@Schema(description = "MA対象フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,1]")
	private String kgyMaTishFlg;

	/**
	 * 名寄せ親MoM企業ID
	 */
	@Column(length = 15)
	@Schema(description = "名寄せ親MoM企業ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,15]")
	private String kgyNyoOyaMomKgyId;

	/**
	 * 企業MoM顧客フラグ
	 */
	@Column(length = 1)
	@Schema(description = "企業MoM顧客フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,1]")
	private String kgyKgyMomKokykFlg;

	/**
	 * 無効フラグ
	 */
	@Column(length = 1)
	@Schema(description = "無効フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,1]")
	private String kgyDltFlg;

	/**
	 * WEB作成日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "WEB作成日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date kgyScWebCrtDt;

	/**
	 * WEB作成担当者ユーザID
	 */
	@Column(length = 64)
	@Schema(description = "WEB作成担当者ユーザID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,64]")
	private String kgyScWebCrtUserId;

	/**
	 * WEB作成担当者販社コード
	 */
	@Column(length = 3)
	@Schema(description = "WEB作成担当者販社コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String kgyScWebCrtHanshCd;

	/**
	 * WEB作成機能コード
	 */
	@Column(length = 3)
	@Schema(description = "WEB作成機能コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String kgyScWebCrtKinoCd;

	/**
	 * WEB更新日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "WEB更新日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date kgyScWebUpdtDt;

	/**
	 * WEB更新担当者ユーザID
	 */
	@Column(length = 64)
	@Schema(description = "WEB更新担当者ユーザID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,64]")
	private String kgyScWebUpdtUserId;

	/**
	 * WEB更新担当者販社コード
	 */
	@Column(length = 3)
	@Schema(description = "WEB更新担当者販社コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String kgyScWebUpdtHanshCd;

	/**
	 * WEB更新機能コード
	 */
	@Column(length = 3)
	@Schema(description = "WEB更新機能コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String kgyScWebUpdtKinoCd;

	/**
	 * バッチ作成機能コード
	 */
	@Column(length = 3)
	@Schema(description = "バッチ作成機能コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String kgyScBtCrtKinoCd;

	/**
	 * バッチ作成処理見做し日
	 */
	@Column(length = 8)
	@Schema(description = "バッチ作成処理見做し日", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,8]")
	private String kgyScBtCrtAsofDt;

	/**
	 * バッチ作成日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "バッチ作成日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date kgyScBtCrtDt;

	/**
	 * バッチ更新機能コード
	 */
	@Column(length = 3)
	@Schema(description = "バッチ更新機能コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String kgyScBtUpdtKinoCd;

	/**
	 * バッチ更新処理見做し日
	 */
	@Column(length = 8)
	@Schema(description = "バッチ更新処理見做し日", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,8]")
	private String kgyScBtUpdtAsofDt;

	/**
	 * バッチ更新日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "バッチ更新日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date kgyScBtUpdtDt;

	/**
	 * システム管理対応日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "システム管理対応日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date kgyScSysKnrDt;

	/**
	 * システム管理NO
	 */
	@Column(length = 5)
	@Schema(description = "システム管理NO", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,5]")
	private String kgyScSysKnrNo;

	/**
	 * MoM最終更新機能コード
	 */
	@Column(length = 3)
	@Schema(description = "MoM最終更新機能コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String kgyScMomUpdtKinoCd;

	/**
	 * MoM最終更新日時時刻
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Schema(description = "MoM最終更新日時時刻", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date kgyScMomUpdtDtTm;

	/**
	 * CUIC法人格名
	 */
	@Column(length = 100)
	@Schema(description = "CUIC法人格名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,100]")
	private String kgyCuicHjnKaku;

	/**
	 * CUIC業種_企業
	 */
	@Column(length = 100)
	@Schema(description = "CUIC業種_企業", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,100]")
	private String kgyCuicGyshKgy;

	/**
	 * CUICクレンジング前_企業名_漢字
	 */
	@Column(length = 202)
	@Schema(description = "CUICクレンジング前_企業名_漢字", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,202]")
	private String kgyCuicClnMaeKgyKnji;

	/**
	 * CUICクレンジング前_企業名_カナ
	 */
	@Column(length = 202)
	@Schema(description = "CUICクレンジング前_企業名_カナ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,202]")
	private String kgyCuicClnMaeKgyKana;

	/**
	 * CUICクレンジング前_郵便番号
	 */
	@Column(length = 30)
	@Schema(description = "CUICクレンジング前_郵便番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,30]")
	private String kgyCuicClnMaePostNum;

	/**
	 * CUICクレンジング前_住所
	 */
	@Column(length = 460)
	@Schema(description = "CUICクレンジング前_住所", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,460]")
	private String kgyCuicClnMaeAds;

	/**
	 * CUIC住所コード住所
	 */
	@Column(length = 460)
	@Schema(description = "CUIC住所コード住所", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,460]")
	private String kgyCuicAds;

	/**
	 * CUIC番地_数字
	 */
	@Schema(description = "CUIC番地_数字", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Long kgyCuicBantiNum;

	/**
	 * CUIC号_数字
	 */
	@Schema(description = "CUIC号_数字", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Long kgyCuicGoNum;

	/**
	 * CUIC住所取得エラｰフラグ
	 */
	@Column(length = 1)
	@Schema(description = "CUIC住所取得エラｰフラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,1]")
	private String kgyCuicAdsErrFlg;

	/**
	 * CUICクレンジング前_電話番号
	 */
	@Column(length = 40)
	@Schema(description = "CUICクレンジング前_電話番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,40]")
	private String kgyCuicClnMaeTelNum;

	/**
	 * CUICクレンジング前_FAX番号
	 */
	@Column(length = 40)
	@Schema(description = "CUICクレンジング前_FAX番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,40]")
	private String kgyCuicClnMaeFaxNum;

	/**
	 * CUIC従業員規模_企業
	 */
	@Column(length = 100)
	@Schema(description = "CUICクレンジング前_FAX番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,100]")
	private String kgyCuicJgyinKboKgy;

	/**
	 * CUIC業種_帝国コード
	 */
	@Column(length = 5)
	@Schema(description = "CUIC業種_帝国コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,5]")
	private String kgyCuicJgyshTikkCd;

	/**
	 * CUIC販社コード
	 */
	@Column(length = 3)
	@Schema(description = "CUIC販社コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String kgyCuicHanshCd;

	/**
	 * CUIC更新担当者コード
	 */
	@Column(length = 6)
	@Schema(description = "CUIC更新担当者コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,6]")
	private String kgyCuicUpdtTntoshCd;

	/**
	 * CUIC更新端末コード
	 */
	@Column(length = 15)
	@Schema(description = "CUIC更新端末コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,15]")
	private String kgyCuicUpdtTermCd;

	/**
	 * CUICMCDB更新日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "CUICMCDB更新日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date kgyCuicMcdbUpdtDt;

	/**
	 * CUIC作成元システムコード
	 */
	@Column(length = 2)
	@Schema(description = "CUIC作成元システムコード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,2]")
	private String kgyCuicCrtSysCd;

	/**
	 * CUIC更新元システムコード
	 */
	@Column(length = 2)
	@Schema(description = "CUIC更新元システムコード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,2]")
	private String kgyCuicUpdtSysCd;

	/**
	 * CUIC元システム作成日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "CUIC元システム作成日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date kgyCuicMotoSysCrtdDt;

	/**
	 * CUIC元システム更新日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "CUIC元システム更新日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date kgyCuicMotoSysUpdtDt;

	/**
	 * CUICバッチ処理做し日
	 */
	@Column(length = 8)
	@Schema(description = "CUICバッチ処理做し日", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,8]")
	private String kgyCuicBtPrcsAsofDt;

	/**
	 * CUICバッチ処理日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "CUICバッチ処理日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date kgyCuicBtPrcsDt;

	/**
	 * CUICMCDB削除フラグ
	 */
	@Column(length = 1)
	@Schema(description = "CUICMCDB削除フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,1]")
	private String kgyCuicMcdbDltFlg;

	/**
	 * CUIC更新担当者販社コード
	 */
	@Column(length = 3)
	@Schema(description = "CUIC更新担当者販社コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String kgyCuicUpdtTntoshHanshCd;

	/**
	 * CUICMCDB作成手段
	 */
	@Column(length = 2)
	@Schema(description = "CUICMCDB作成手段", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,2]")
	private String kgyCuicMcdbCrtdMtd;

	/**
	 * CUICMCDB更新手段
	 */
	@Column(length = 2)
	@Schema(description = "CUICMCDB更新手段", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,2]")
	private String kgyCuicMcdbUpdtMtd;

	/**
	 * CUICMCDB作成日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "CUICMCDB作成日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date kgyCuicMcdbCrtdDt;

	/**
	 * CUIC更新ユーザID
	 */
	@Column(length = 64)
	@Schema(description = "CUIC更新ユーザID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,64]")
	private String kgyCuicUpdtUserId;

	/**
	 * 市場層コード
	 */
	@Column(length = 2)
	@Schema(description = "市場層コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,2]")
	private String kgySijosoCd;

	/**
	 * MoM会社ID
	 */
	@Column(length = 6)
	@Schema(description = "MoM会社ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,6]")
	private String kgyMomKishId;

	/**
	 * LBCコード
	 */
	@Column(length = 11)
	@Schema(description = "LBCコード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,11]")
	private String kgyLbcCd;

	/**
	 * 法人マイナンバー
	 */
	@Column(length = 13)
	@Schema(description = "法人マイナンバー", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,13]")
	private String kgyCorporateNumber;

	/**
	 * 住所郵便番号
	 */
	@Column(length = 50)
	@Schema(description = "住所郵便番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,50]")
	private String adsKadsPostNum;

	/**
	 * 住所都道府県名_漢字
	 */
	@Column(length = 10)
	@Schema(description = "住所都道府県名_漢字", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,10]")
	private String adsKtdhknNmKnji;

	/**
	 * 住所市区郡町村名_漢字
	 */
	@Column(length = 26)
	@Schema(description = "住所市区郡町村名_漢字", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,26]")
	private String adsKskugnchosnKnji;

	/**
	 * 住所大字・通称名_漢字
	 */
	@Column(length = 38)
	@Schema(description = "住所大字・通称名_漢字", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,38]")
	private String adsKowaTusyoKnji;

	/**
	 * 住所字名・丁目_漢字
	 */
	@Column(length = 26)
	@Schema(description = "住所字名・丁目_漢字", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,26]")
	private String adsKkowChomeKnji;

	/**
	 * 住所都道府県名_カナ
	 */
	@Column(length = 8)
	@Schema(description = "住所都道府県名_カナ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,8]")
	private String adsKtdhknNmKana;

	/**
	 * 住所市区郡町村名_カナ
	 */
	@Column(length = 24)
	@Schema(description = "住所市区郡町村名_カナ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,24]")
	private String adsKskugnchosnKana;

	/**
	 * 住所大字・通称名_カナ
	 */
	@Column(length = 36)
	@Schema(description = "住所大字・通称名_カナ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,36]")
	private String adsKowaTusyoKana;

	/**
	 * 住所字名・丁目_カナ
	 */
	@Column(length = 24)
	@Schema(description = "住所字名・丁目_カナ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,24]")
	private String adsKkowChomeKana;

	/**
	 * 住所コード大（都道府県）
	 */
	@Column(name = "ADS_KADS_CD_L", length = 2)
	@Schema(description = "住所コード大（都道府県）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,2]")
	private String adsKadsCdL;

	/**
	 * 住所コード大中（市・群）
	 */
	@Column(length = 5)
	@Schema(description = "住所コード大中（市・群）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,5]")
	private String adsKadsCdLm;

	/**
	 * 住所コード大中小（町・村・大字）
	 */
	@Column(length = 8)
	@Schema(description = "住所コード大中小（町・村・大字）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,8]")
	private String adsKadsCdLms;

	/**
	 * 無効フラグ
	 */
	@Column(length = 1)
	@Schema(description = "無効フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,1]")
	private String adsKdltFlg;

	/**
	 * WEB作成日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "無効フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date adsKscWebCrtDt;

	/**
	 * WEB作成担当者ユーザID
	 */
	@Column(length = 64)
	@Schema(description = "WEB作成担当者ユーザID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,64]")
	private String adsKscWebCrtUserId;

	/**
	 * WEB作成担当者販社コード
	 */
	@Column(length = 3)
	@Schema(description = "WEB作成担当者販社コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String adsKscWebCrtHanshCd;

	/**
	 * WEB作成機能コード
	 */
	@Column(length = 3)
	@Schema(description = "WEB作成機能コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String adsKscWebCrtKinoCd;

	/**
	 * WEB更新日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "WEB更新日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date adsKscWebUpdtDt;

	/**
	 * WEB更新担当者ユーザID
	 */
	@Column(length = 64)
	@Schema(description = "WEB更新担当者ユーザID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String adsKscWebUpdtUserId;

	/**
	 * WEB更新担当者販社コード
	 */
	@Column(length = 3)
	@Schema(description = "WEB更新担当者販社コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String adsKscWebUpdtHanshCd;

	/**
	 * WEB更新機能コード
	 */
	@Column(length = 3)
	@Schema(description = "WEB更新機能コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String adsKscWebUpdtKinoCd;

	/**
	 * バッチ作成機能コード
	 */
	@Column(length = 3)
	@Schema(description = "バッチ作成機能コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String adsKscBtCrtKinoCd;

	/**
	 * バッチ作成処理見做し日
	 */
	@Column(length = 8)
	@Schema(description = "バッチ作成処理見做し日", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,8]")
	private String adsKscBtCrtAsofDt;

	/**
	 * バッチ作成日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "バッチ作成日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date adsKscBtCrtDt;

	/**
	 * バッチ更新機能コード
	 */
	@Column(length = 3)
	@Schema(description = "バッチ更新機能コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String adsKscBtUpdtKinoCd;

	/**
	 * バッチ更新処理見做し日
	 */
	@Column(length = 8)
	@Schema(description = "バッチ更新処理見做し日", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,8]")
	private String adsKscBtUpdtAsofDt;

	/**
	 * バッチ更新日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "バッチ更新日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date adsKscBtUpdtDt;

	/**
	 * システム管理対応日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "システム管理対応日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date adsKscSysKnrDt;

	/**
	 * システム管理NO
	 */
	@Column(length = 5)
	@Schema(description = "システム管理NO", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,5]")
	private String adsKscSysKnrNo;

	/**
	 * MoM最終更新機能コード
	 */
	@Column(length = 3)
	@Schema(description = "MoM最終更新機能コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String adsKscMomUpdtKinoCd;

	/**
	 * MoM最終更新日時時刻
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Schema(description = "MoM最終更新日時時刻", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date adsKscMomUpdtDtTm;

	/**
	 * 郵便番号関連１
	 */
	@Column(name = "ADS_KPOST_NUM_INFO_1", length = 1)
	@Schema(description = "郵便番号関連１", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,1]")
	private String adsKpostNumInfo1;

	/**
	 * 郵便番号関連２
	 */
	@Column(name = "ADS_KPOST_NUM_INFO_2", length = 1)
	@Schema(description = "郵便番号関連２", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,1]")
	private String adsKpostNumInfo2;

	/**
	 * 登録年月
	 */
	@Column(length = 6)
	@Schema(description = "登録年月", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,6]")
	private String adsKtourokuYearMs;

	/**
	 * 変更年月
	 */
	@Column(length = 6)
	@Schema(description = "変更年月", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,6]")
	private String adsKhenkouYearMs;

	/**
	 * 更新区分
	 */
	@Column(length = 1)
	@Schema(description = "更新区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,1]")
	private String adsKkoushinKbn;

	/**
	 * 業種名_漢字
	 */
	@Column(length = 62)
	@Schema(description = "業種名_漢字", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,62]")
	private String gyshKgyshNmKnji;

	/**
	 * 業種名_カナ
	 */
	@Column(length = 35)
	@Schema(description = "業種名_カナ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,35]")
	private String gyshKgyshNmKana;

	/**
	 * 無効フラグ
	 */
	@Column(length = 1)
	@Schema(description = "無効フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,1]")
	private String gyshKdltFlg;

	/**
	 * WEB作成日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "WEB作成日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date gyshKscWebCrtDt;

	/**
	 * WEB作成担当者ユーザID
	 */
	@Column(length = 64)
	@Schema(description = "WEB作成担当者ユーザID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,64]")
	private String gyshKscWebCrtUserId;

	/**
	 * WEB作成担当者販社コード
	 */
	@Column(length = 3)
	@Schema(description = "WEB作成担当者販社コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String gyshKscWebCrtHanshCd;

	/**
	 * WEB作成機能コード
	 */
	@Column(length = 3)
	@Schema(description = "WEB作成機能コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String gyshKscWebCrtKinoCd;

	/**
	 * WEB更新日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "WEB更新日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date gyshKscWebUpdtDt;

	/**
	 * WEB更新担当者ユーザID
	 */
	@Column(length = 64)
	@Schema(description = "WEB更新担当者ユーザID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,64]")
	private String gyshKscWebUpdtUserId;

	/**
	 * WEB更新担当者販社コード
	 */
	@Column(length = 3)
	@Schema(description = "WEB更新担当者販社コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String gyshKscWebUpdtHanshCd;

	/**
	 * WEB更新機能コード
	 */
	@Column(length = 3)
	@Schema(description = "WEB更新機能コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String gyshKscWebUpdtKinoCd;

	/**
	 * バッチ作成機能コード
	 */
	@Column(length = 3)
	@Schema(description = "バッチ作成機能コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String gyshKscBtCrtKinoCd;

	/**
	 * バッチ作成処理見做し日
	 */
	@Column(length = 8)
	@Schema(description = "バッチ作成処理見做し日", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,8]")
	private String gyshKscBtCrtAsofDt;

	/**
	 * バッチ作成日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "バッチ作成日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date gyshKscBtCrtDt;

	/**
	 * バッチ更新機能コード
	 */
	@Column(length = 3)
	@Schema(description = "バッチ更新機能コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String gyshKscBtUpdtKinoCd;

	/**
	 * バッチ更新処理見做し日
	 */
	@Column(length = 8)
	@Schema(description = "バッチ更新処理見做し日", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,8]")
	private String gyshKscBtUpdtAsofDt;

	/**
	 * バッチ更新日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "バッチ更新日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date gyshKscBtUpdtDt;

	/**
	 * システム管理対応日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "システム管理対応日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date gyshKscSysKnrDt;

	/**
	 * システム管理NO
	 */
	@Column(length = 5)
	@Schema(description = "システム管理NO", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,5]")
	private String gyshKscSysKnrNo;

	/**
	 * MoM最終更新機能コード
	 */
	@Column(length = 3)
	@Schema(description = "MoM最終更新機能コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String gyshKscMomUpdtKinoCd;

	/**
	 * MoM最終更新日時時刻
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Schema(description = "MoM最終更新日時時刻", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date gyshKscMomUpdtDtTm;

	/**
	 * 事業所名_漢字
	 */
	@Column(length = 100)
	@Schema(description = "事業所名_漢字", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,100]")
	private String jgsJgsNmKnji;

	/**
	 * 事業所名_カナ
	 */
	@Column(length = 200)
	@Schema(description = "事業所名_カナ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,200]")
	private String jgsJgsNmKana;

	/**
	 * 事業所郵便番号
	 */
	@Column(length = 50)
	@Schema(description = "事業所郵便番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,50]")
	private String jgsJgsPostNum;

	/**
	 * 事業所住所コード
	 */
	@Column(length = 11)
	@Schema(description = "事業所住所コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,11]")
	private String jgsJgsAdsCd;

	/**
	 * 事業所住所字通称名
	 */
	@Column(length = 500)
	@Schema(description = "事業所住所字通称名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,500]")
	private String jgsJgsAdsAzatusyoNm;

	/**
	 * 事業所住所番地名
	 */
	@Column(length = 20)
	@Schema(description = "事業所住所番地名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,20]")
	private String jgsJgsAdsBantiNm;

	/**
	 * 事業所住所号名
	 */
	@Column(length = 20)
	@Schema(description = "事業所住所号名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,20]")
	private String jgsJgsAdsGoNm;

	/**
	 * 事業所住所ビル名
	 */
	@Column(length = 100)
	@Schema(description = "事業所住所ビル名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,100]")
	private String jgsJgsAdsBldgNm;

	/**
	 * 事業所住所フロア名
	 */
	@Column(length = 100)
	@Schema(description = "事業所住所フロア名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,100]")
	private String jgsJgsAdsFlorNm;

	/**
	 * 事業所業種コード
	 */
	@Column(length = 5)
	@Schema(description = "事業所業種コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,5]")
	private String jgsJgsGyshCd;

	/**
	 * 事業所従業員規模コード
	 */
	@Column(length = 1)
	@Schema(description = "事業所従業員規模コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,1]")
	private String jgsJgsJgyinKboCd;

	/**
	 * 事業所従業員数
	 */
	@Schema(description = "事業所従業員数", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Integer jgsJgsJgyinQty;

	/**
	 * 企業COSMOSコード
	 */
	@Column(length = 9)
	@Schema(description = "企業COSMOSコード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private String jgsKgyNumCsmsCd;

	/**
	 * 事業所COSMOSコード
	 */
	@Column(length = 5)
	@Schema(description = "事業所COSMOSコード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,5]")
	private String jgsJgsNumCsmsCd;

	/**
	 * 重点事業所コード
	 */
	@Column(length = 1)
	@Schema(description = "重点事業所コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,1]")
	private String jgsJtnJgsCd;

	/**
	 * 事業所市場分類1_コード
	 */
	@Column(name = "JGS_SIJO_BUNRUI_1_CD", length = 2)
	@Schema(description = "事業所市場分類1_コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,2]")
	private String jgsSijoBunrui1Cd;

	/**
	 * 事業所市場分類2_コード
	 */
	@Column(name = "JGS_SIJO_BUNRUI_2_CD", length = 2)
	@Schema(description = "事業所市場分類2_コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,2]")
	private String jgsSijoBunrui2Cd;

	/**
	 * 事業所主力商品メモ
	 */
	@Column(length = 32)
	@Schema(description = "事業所主力商品メモ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,32]")
	private String jgsShrykShhnKnji;

	/**
	 * 事業所情報化進展ステージコード
	 */
	@Column(length = 2)
	@Schema(description = "事業所情報化進展ステージコード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,2]")
	private String jgsSysInfnStgCd;

	/**
	 * 事業所画像高度化進展ステージメモ
	 */
	@Column(length = 100)
	@Schema(description = "事業所画像高度化進展ステージメモ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,100]")
	private String jgsPpcInfnStg;

	/**
	 * 事業所状態区分
	 */
	@Column(length = 1)
	@Schema(description = "事業所状態区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,1]")
	private String jgsJgsInfoKbn;

	/**
	 * 事業所業務名メモ
	 */
	@Column(length = 100)
	@Schema(description = "事業所業務名メモ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,100]")
	private String jgsJgsGyomNmKnji;

	/**
	 * 事業所利用グループウェアコード
	 */
	@Column(length = 2)
	@Schema(description = "事業所利用グループウェアコード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,2]")
	private String jgsJgsGrpwareCd;

	/**
	 * 名寄せ用事業所名_漢字
	 */
	@Column(length = 200)
	@Schema(description = "名寄せ用事業所名_漢字", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,200]")
	private String jgsNyoJgsNmKnji;

	/**
	 * 名寄せ用事業所名_カナ
	 */
	@Column(length = 200)
	@Schema(description = "名寄せ用事業所名_カナ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,200]")
	private String jgsNyoJgsNmKana;

	/**
	 * 名寄せ親MoM事業所ID
	 */
	@Column(length = 15)
	@Schema(description = "名寄せ親MoM事業所ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,15]")
	private String jgsNyoOyaMomJgsId;

	/**
	 * 事業所代表TEL
	 */
	@Column(length = 50)
	@Schema(description = "事業所代表TEL", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,50]")
	private String jgsJgsTelNum;

	/**
	 * 事業所代表FAX
	 */
	@Column(length = 40)
	@Schema(description = "事業所代表FAX", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,40]")
	private String jgsJgsFaxNum;

	/**
	 * 事業所MoM顧客フラグ
	 */
	@Column(length = 1)
	@Schema(description = "事業所MoM顧客フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,1]")
	private String jgsJgsMomKokykFlg;

	/**
	 * DMC担当顧客フラグ
	 */
	@Column(length = 1)
	@Schema(description = "DMC担当顧客フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,1]")
	private String jgsDmcTntoCstFlg;

	/**
	 * 無効フラグ
	 */
	@Column(length = 1)
	@Schema(description = "無効フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,1]")
	private String jgsDltFlg;

	/**
	 * WEB作成日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "WEB作成日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date jgsScWebCrtDt;

	/**
	 * WEB作成担当者ユーザID
	 */
	@Column(length = 64)
	@Schema(description = "WEB作成担当者ユーザID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,64]")
	private String jgsScWebCrtUserId;

	/**
	 * WEB作成担当者販社コード
	 */
	@Column(length = 3)
	@Schema(description = "WEB作成担当者販社コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String jgsScWebCrtHanshCd;

	/**
	 * WEB作成機能コード
	 */
	@Column(length = 3)
	@Schema(description = "WEB作成機能コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String jgsScWebCrtKinoCd;

	/**
	 * WEB更新日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "WEB更新日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date jgsScWebUpdtDt;

	/**
	 * WEB更新担当者ユーザID
	 */
	@Column(length = 64)
	@Schema(description = "WEB更新担当者ユーザID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,64]")
	private String jgsScWebUpdtUserId;

	/**
	 * WEB更新担当者販社コード
	 */
	@Column(length = 3)
	@Schema(description = "WEB更新担当者販社コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String jgsScWebUpdtHanshCd;

	/**
	 * WEB更新機能コード
	 */
	@Column(length = 3)
	@Schema(description = "WEB更新担当者ユーザID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String jgsScWebUpdtKinoCd;

	/**
	 * バッチ作成機能コード
	 */
	@Column(length = 3)
	@Schema(description = "バッチ作成機能コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String jgsScBtCrtKinoCd;

	/**
	 * バッチ作成処理見做し日
	 */
	@Column(length = 8)
	@Schema(description = "バッチ作成処理見做し日", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,8]")
	private String jgsScBtCrtAsofDt;

	/**
	 * バッチ作成日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "バッチ作成日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date jgsScBtCrtDt;

	/**
	 * バッチ更新機能コード
	 */
	@Column(length = 3)
	@Schema(description = "バッチ更新機能コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String jgsScBtUpdtKinoCd;

	/**
	 * バッチ更新処理見做し日
	 */
	@Column(length = 8)
	@Schema(description = "バッチ更新処理見做し日", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,8]")
	private String jgsScBtUpdtAsofDt;

	/**
	 * バッチ更新日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "バッチ更新日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date jgsScBtUpdtDt;

	/**
	 * システム管理対応日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "システム管理対応日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date jgsScSysKnrDt;

	/**
	 * システム管理NO
	 */
	@Column(length = 5)
	@Schema(description = "システム管理NO", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,5]")
	private String jgsScSysKnrNo;

	/**
	 * MoM最終更新機能コード
	 */
	@Column(length = 3)
	@Schema(description = "MoM最終更新機能コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String jgsScMomUpdtKinoCd;

	/**
	 * MoM最終更新日時時刻
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Schema(description = "MoM最終更新日時時刻", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date jgsScMomUpdtDtTm;

	/**
	 * 市場層コード
	 */
	@Column(length = 2)
	@Schema(description = "市場層コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,2]")
	private String jgsSijosoCd;

	/**
	 * 顧客関係力ｺｰﾄﾞ
	 */
	@Column(length = 1)
	@Schema(description = "顧客関係力ｺｰﾄﾞ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,1]")
	private String jgsCstKnkrykCd;

	/**
	 * LBCコード
	 */
	@Column(length = 11)
	@Schema(description = "LBCコード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,11]")
	private String jgsLbcCd;

	/**
	 * 住所郵便番号
	 */
	@Column(length = 50)
	@Schema(description = "住所郵便番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,50]")
	private String adsJadsPostNum;

	/**
	 * 住所都道府県名_漢字
	 */
	@Column(length = 10)
	@Schema(description = "住所都道府県名_漢字", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,10]")
	private String adsJtdhknNmKnji;

	/**
	 * 住所市区郡町村名_漢字
	 */
	@Column(length = 26)
	@Schema(description = "住所市区郡町村名_漢字", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,26]")
	private String adsJskugnchosnKnji;

	/**
	 * 住所大字・通称名_漢字
	 */
	@Column(length = 38)
	@Schema(description = "住所大字・通称名_漢字", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,38]")
	private String adsJowaTusyoKnji;

	/**
	 * 住所字名・丁目_漢字
	 */
	@Column(length = 26)
	@Schema(description = "住所字名・丁目_漢字", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,26]")
	private String adsJkowChomeKnji;

	/**
	 * 住所都道府県名_カナ
	 */
	@Column(length = 8)
	@Schema(description = "住所都道府県名_カナ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,8]")
	private String adsJtdhknNmKana;

	/**
	 * 住所市区郡町村名_カナ
	 */
	@Column(length = 24)
	@Schema(description = "住所市区郡町村名_カナ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,24]")
	private String adsJskugnchosnKana;

	/**
	 * 住所大字・通称名_カナ
	 */
	@Column(length = 36)
	@Schema(description = "住所市区郡町村名_カナ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,24]")
	private String adsJowaTusyoKana;

	/**
	 * 住所字名・丁目_カナ
	 */
	@Column(length = 24)
	@Schema(description = "住所市区郡町村名_カナ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,24]")
	private String adsJkowChomeKana;

	/**
	 * 住所コード大（都道府県）
	 */
	@Column(name = "ADS_JADS_CD_L", length = 2)
	@Schema(description = "住所コード大（都道府県）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,2]")
	private String adsJadsCdL;

	/**
	 * 住所コード大中（市・群）
	 */
	@Column(length = 5)
	@Schema(description = "住所コード大中（市・群）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,5]")
	private String adsJadsCdLm;

	/**
	 * 住所コード大中小（町・村・大字）
	 */
	@Column(length = 8)
	@Schema(description = "住所コード大中小（町・村・大字）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,8]")
	private String adsJadsCdLms;

	/**
	 * 無効フラグ
	 */
	@Column(length = 1)
	@Schema(description = "無効フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,1]")
	private String adsJdltFlg;

	/**
	 * WEB作成日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "WEB作成日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date adsJscWebCrtDt;

	/**
	 * WEB作成担当者ユーザID
	 */
	@Column(length = 64)
	@Schema(description = "WEB作成担当者ユーザID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,64]")
	private String adsJscWebCrtUserId;

	/**
	 * WEB作成担当者販社コード
	 */
	@Column(length = 3)
	@Schema(description = "WEB作成担当者販社コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String adsJscWebCrtHanshCd;

	/**
	 * WEB作成機能コード
	 */
	@Column(length = 3)
	@Schema(description = "WEB作成機能コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String adsJscWebCrtKinoCd;

	/**
	 * WEB更新日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "WEB更新日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date adsJscWebUpdtDt;

	/**
	 * WEB更新担当者ユーザID
	 */
	@Column(length = 64)
	@Schema(description = "WEB更新担当者ユーザID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,64]")
	private String adsJscWebUpdtUserId;

	/**
	 * WEB更新担当者販社コード
	 */
	@Column(length = 3)
	@Schema(description = "WEB更新担当者販社コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String adsJscWebUpdtHanshCd;

	/**
	 * WEB更新機能コード
	 */
	@Column(length = 3)
	@Schema(description = "WEB更新機能コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String adsJscWebUpdtKinoCd;

	/**
	 * バッチ作成機能コード
	 */
	@Column(length = 3)
	@Schema(description = "バッチ作成機能コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String adsJscBtCrtKinoCd;

	/**
	 * バッチ作成処理見做し日
	 */
	@Column(length = 8)
	@Schema(description = "バッチ作成処理見做し日", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,8]")
	private String adsJscBtCrtAsofDt;

	/**
	 * バッチ作成日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "バッチ作成日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date adsJscBtCrtDt;

	/**
	 * バッチ更新機能コード
	 */
	@Column(length = 3)
	@Schema(description = "バッチ更新機能コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String adsJscBtUpdtKinoCd;

	/**
	 * バッチ更新処理見做し日
	 */
	@Column(length = 8)
	@Schema(description = "バッチ更新処理見做し日", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,8]")
	private String adsJscBtUpdtAsofDt;

	/**
	 * バッチ更新日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "バッチ更新日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date adsJscBtUpdtDt;

	/**
	 * システム管理対応日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "システム管理対応日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date adsJscSysKnrDt;

	/**
	 * システム管理NO
	 */
	@Column(length = 5)
	@Schema(description = "システム管理NO", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,5]")
	private String adsJscSysKnrNo;

	/**
	 * MoM最終更新機能コード
	 */
	@Column(length = 3)
	@Schema(description = "MoM最終更新機能コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String adsJscMomUpdtKinoCd;

	/**
	 * MoM最終更新日時時刻
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Schema(description = "MoM最終更新日時時刻", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date adsJscMomUpdtDtTm;

	/**
	 * 郵便番号関連１
	 */
	@Column(name = "ADS_JPOST_NUM_INFO_1", length = 1)
	@Schema(description = "郵便番号関連１", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,1]")
	private String adsJpostNumInfo1;

	/**
	 * 郵便番号関連２
	 */
	@Column(name = "ADS_JPOST_NUM_INFO_2", length = 1)
	@Schema(description = "郵郵便番号関連２", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,1]")
	private String adsJpostNumInfo2;

	/**
	 * 登録年月
	 */
	@Column(length = 6)
	@Schema(description = "登録年月", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,6]")
	private String adsJtourokuYearMs;

	/**
	 * 変更年月
	 */
	@Column(length = 6)
	@Schema(description = "変更年月", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,6]")
	private String adsJhenkouYearMs;

	/**
	 * 更新区分
	 */
	@Column(length = 1)
	@Schema(description = "更新区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,1]")
	private String adsJkoushinKbn;

	/**
	 * 業種名_漢字
	 */
	@Column(length = 62)
	@Schema(description = "業種名_漢字", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,62]")
	private String gyshJgyshNmKnji;

	/**
	 * 業種名_カナ
	 */
	@Column(length = 35)
	@Schema(description = "業種名_カナ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,35)]")
	private String gyshJgyshNmKana;

	/**
	 * 無効フラグ
	 */
	@Column(length = 1)
	@Schema(description = "無効フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,1]")
	private String gyshJdltFlg;

	/**
	 * WEB作成日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "WEB作成日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date gyshJscWebCrtDt;

	/**
	 * WEB作成担当者ユーザID
	 */
	@Column(length = 64)
	@Schema(description = "WEB作成担当者ユーザID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,64]")
	private String gyshJscWebCrtUserId;

	/**
	 * WEB作成担当者販社コード
	 */
	@Column(length = 3)
	@Schema(description = "WEB作成担当者販社コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,1]")
	private String gyshJscWebCrtHanshCd;

	/**
	 * WEB作成機能コード
	 */
	@Column(length = 3)
	@Schema(description = "WEB作成機能コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String gyshJscWebCrtKinoCd;

	/**
	 * WEB更新日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "WEB更新日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date gyshJscWebUpdtDt;

	/**
	 * WEB更新担当者ユーザID
	 */
	@Column(length = 64)
	@Schema(description = "WEB更新担当者ユーザID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,64]")
	private String gyshJscWebUpdtUserId;

	/**
	 * WEB更新担当者販社コード
	 */
	@Column(length = 3)
	@Schema(description = "WEB更新担当者販社コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String gyshJscWebUpdtHanshCd;

	/**
	 * WEB更新機能コード
	 */
	@Column(length = 3)
	@Schema(description = "WEB更新機能コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String gyshJscWebUpdtKinoCd;

	/**
	 * バッチ作成機能コード
	 */
	@Column(length = 3)
	@Schema(description = "バッチ作成機能コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String gyshJscBtCrtKinoCd;

	/**
	 * バッチ作成処理見做し日
	 */
	@Column(length = 8)
	@Schema(description = "バッチ作成処理見做し日", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,8]")
	private String gyshJscBtCrtAsofDt;

	/**
	 * バッチ作成日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "バッチ作成日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date gyshJscBtCrtDt;

	/**
	 * バッチ更新機能コード
	 */
	@Column(length = 3)
	@Schema(description = "バッチ更新機能コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String gyshJscBtUpdtKinoCd;

	/**
	 * バッチ更新処理見做し日
	 */
	@Column(length = 8)
	@Schema(description = "バッチ更新処理見做し日", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,8]")
	private String gyshJscBtUpdtAsofDt;

	/**
	 * バッチ更新日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "バッチ更新日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date gyshJscBtUpdtDt;

	/**
	 * システム管理対応日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "システム管理対応日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date gyshJscSysKnrDt;

	/**
	 * システム管理NO
	 */
	@Column(length = 5)
	@Schema(description = "システム管理NO", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,5]")
	private String gyshJscSysKnrNo;

	/**
	 * MoM最終更新機能コード
	 */
	@Column(length = 3)
	@Schema(description = "MoM最終更新機能コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String gyshJscMomUpdtKinoCd;

	/**
	 * MoM最終更新日時時刻
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Schema(description = "MoM最終更新日時時刻", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date gyshJscMomUpdtDtTm;

	/**
	 * 部門名_漢字
	 */
	@Column(length = 200)
	@Schema(description = "部門名_漢字", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,200]")
	private String bmnBmnNmKnji;

	/**
	 * 部門名_カナ
	 */
	@Column(length = 200)
	@Schema(description = "部門名_カナ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,200]")
	private String bmnBmnNmKana;

	/**
	 * MoM企業ID
	 */
	@Column(length = 15)
	@Schema(description = "MoM企業ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,15]")
	private String bmnMomKgyId;

	/**
	 * 部門代表TEL
	 */
	@Column(length = 50)
	@Schema(description = "部門代表TEL", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,50]")
	private String bmnBmnTelNum;

	/**
	 * 部門代表FAX
	 */
	@Column(length = 40)
	@Schema(description = "部門代表FAX", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,40]")
	private String bmnBmnFaxNum;

	/**
	 * 親MoM部門ID
	 */
	@Column(length = 15)
	@Schema(description = "親MoM部門ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,15]")
	private String bmnOyaMomBmnId;

	/**
	 * 部門簡略名1
	 */
	@Column(length = 40)
	@Schema(description = "部門簡略名1", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,40]")
	private String bmnBmnSplNm1;

	/**
	 * 部門簡略名2
	 */
	@Column(length = 40)
	@Schema(description = "部門簡略名2", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,40]")
	private String bmnBmnSplNm2;

	/**
	 * 名寄せ用部門名漢字
	 */
	@Column(length = 200)
	@Schema(description = "名寄せ用部門名漢字", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,200]")
	private String bmnNyoBmnNmKnji;

	/**
	 * 名寄せ用部門名カナ
	 */
	@Column(length = 200)
	@Schema(description = "名寄せ用部門名カナ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,200]")
	private String bmnNyoBmnNmKana;

	/**
	 * 名寄せ用部門簡略名1
	 */
	@Column(length = 40)
	@Schema(description = "名寄せ用部門簡略名1", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,40]")
	private String bmnNyoBmnSplNm1;

	/**
	 * 名寄せ用部門簡略名2
	 */
	@Column(length = 40)
	@Schema(description = "名寄せ用部門簡略名2", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,40]")
	private String bmnNyoBmnSplNm2;

	/**
	 * 名寄せ親MoM部門ID
	 */
	@Column(length = 15)
	@Schema(description = "名寄せ親MoM部門ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,15]")
	private String bmnNyoOyaMomBmnId;

	/**
	 * 無効フラグ
	 */
	@Column(length = 1)
	@Schema(description = "無効フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,1]")
	private String bmnDltFlg;

	/**
	 * WEB作成日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "WEB作成日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date bmnScWebCrtDt;

	/**
	 * WEB作成担当者ユーザID
	 */
	@Column(length = 64)
	@Schema(description = "無効フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,1]")
	private String bmnScWebCrtUserId;

	/**
	 * WEB作成担当者販社コード
	 */
	@Column(length = 3)
	@Schema(description = "WEB作成担当者販社コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String bmnScWebCrtHanshCd;

	/**
	 * WEB作成機能コード
	 */
	@Column(length = 3)
	@Schema(description = "WEB作成機能コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String bmnScWebCrtKinoCd;

	/**
	 * WEB更新日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "WEB更新日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date bmnScWebUpdtDt;

	/**
	 * WEB更新担当者ユーザID
	 */
	@Column(length = 64)
	@Schema(description = "WEB更新担当者ユーザID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,64]")
	private String bmnScWebUpdtUserId;

	/**
	 * WEB更新担当者販社コード
	 */
	@Column(length = 3)
	@Schema(description = "WEB更新担当者販社コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String bmnScWebUpdtHanshCd;

	/**
	 * WEB更新機能コード
	 */
	@Column(length = 3)
	@Schema(description = "WEB更新機能コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String bmnScWebUpdtKinoCd;

	/**
	 * バッチ作成機能コード
	 */
	@Column(length = 3)
	@Schema(description = "バッチ作成機能コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String bmnScBtCrtKinoCd;

	/**
	 * バッチ作成処理見做し日
	 */
	@Column(length = 8)
	@Schema(description = "バッチ作成処理見做し日", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,8]")
	private String bmnScBtCrtAsofDt;

	/**
	 * バッチ作成日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "バッチ作成日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date bmnScBtCrtDt;

	/**
	 * バッチ更新機能コード
	 */
	@Column(length = 3)
	@Schema(description = "バッチ更新機能コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String bmnScBtUpdtKinoCd;

	/**
	 * バッチ更新処理見做し日
	 */
	@Column(length = 8)
	@Schema(description = "バッチ更新処理見做し日", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,8]")
	private String bmnScBtUpdtAsofDt;

	/**
	 * バッチ更新日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "バッチ更新日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date bmnScBtUpdtDt;

	/**
	 * システム管理対応日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "システム管理対応日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date bmnScSysKnrDt;

	/**
	 * システム管理NO
	 */
	@Column(length = 5)
	@Schema(description = "システム管理NO", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,5]")
	private String bmnScSysKnrNo;

	/**
	 * MoM最終更新機能コード
	 */
	@Column(length = 3)
	@Schema(description = "MoM最終更新機能コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,3]")
	private String bmnScMomUpdtKinoCd;

	/**
	 * MoM最終更新日時時刻
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Schema(description = "MoM最終更新日時時刻", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date bmnScMomUpdtDtTm;

	/**
	 * KG営業セグメント区分
	 */
	@Column(length = 4)
	@Schema(description = "KG営業セグメント区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,4]")
	private String bmnKgeigySgmtKbn;

	/**
	 * 部門種別区分
	 */
	@Column(length = 1)
	@Schema(description = "部門種別区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,1]")
	private String bmnBmnShubtKbn;

}
