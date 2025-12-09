package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * ICI顧客情報マスタ
 */
@Entity
@Data
@Table(name = "mv_t_jmci105")
public class MvTJmci105Master {

	/** 顧客番号 */
	@Id
	@Schema(description = "顧客番号", required = true)
	private String customerNumber;

	/** MOM企業ID */
	@Schema(description = "MOM企業ID", required = false)
	private String sortCompanyId;

	/** 顧客タイプ */
	@Schema(description = "顧客タイプ", required = false)
	private String cstTyp;

	/** 顧客有効フラグ */
	@Schema(description = "顧客有効フラグ", required = false)
	private String customerEnabledFlag;

	/** 企業名_漢字 */
	@Schema(description = "企業名_漢字", required = false)
	private String enterpriseName;

	/** 企業名_ｶﾅ */
	@Schema(description = "企業名_ｶﾅ", required = false)
	private String enterprisePhonetic;

	/** 名寄せ用_企業名_漢字 */
	@Schema(description = "名寄せ用_企業名_漢字", required = false)
	private String enterpriseNameFormal;

	/** 名寄せ用_企業名_ｶﾅ */
	@Schema(description = "名寄せ用_企業名_ｶﾅ", required = false)
	private String enterprisePhoneticFormal;

	/** 法人格付企業名_漢字 */
	@Schema(description = "法人格付企業名_漢字", required = false)
	private String cstKnjiNm;

	/** 法人格付企業名_ｶﾅ */
	@Schema(description = "法人格付企業名_ｶﾅ", required = false)
	private String cstKanaNm;

	/** 関係会社区分 */
	@Schema(description = "関係会社区分", required = false)
	private String kanCorpKb;

	/** 関係会社コード */
	@Schema(description = "関係会社コード", required = false)
	private String kanCorpCd;

	/** 系列会社区分 */
	@Schema(description = "系列会社区分", required = false)
	private String affiliatedCompanyCode;

	/** 連結会社区分 */
	@Schema(description = "連結会社区分", required = false)
	private String connectCompanyCls;

	/** ICI更新日時 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "ICI更新日時", required = false)
	private Date iciUpdtDt;

	/** MoM最終更新日時時刻 */
	@Temporal(TemporalType.TIMESTAMP)
	@Schema(description = "MoM最終更新日時時刻", required = false)
	private Date scMomUpdtDtTm;
}