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
	@Schema(description = "顧客番号", requiredMode = Schema.RequiredMode.REQUIRED)
	private String customerNumber;

	/** MOM企業ID */
	@Schema(description = "MOM企業ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String sortCompanyId;

	/** 顧客タイプ */
	@Schema(description = "顧客タイプ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String cstTyp;

	/** 顧客有効フラグ */
	@Schema(description = "顧客有効フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String customerEnabledFlag;

	/** 企業名_漢字 */
	@Schema(description = "企業名_漢字", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String enterpriseName;

	/** 企業名_ｶﾅ */
	@Schema(description = "企業名_ｶﾅ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String enterprisePhonetic;

	/** 名寄せ用_企業名_漢字 */
	@Schema(description = "名寄せ用_企業名_漢字", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String enterpriseNameFormal;

	/** 名寄せ用_企業名_ｶﾅ */
	@Schema(description = "名寄せ用_企業名_ｶﾅ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String enterprisePhoneticFormal;

	/** 法人格付企業名_漢字 */
	@Schema(description = "法人格付企業名_漢字", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String cstKnjiNm;

	/** 法人格付企業名_ｶﾅ */
	@Schema(description = "法人格付企業名_ｶﾅ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String cstKanaNm;

	/** 関係会社区分 */
	@Schema(description = "関係会社区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String kanCorpKb;

	/** 関係会社コード */
	@Schema(description = "関係会社コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String kanCorpCd;

	/** 系列会社区分 */
	@Schema(description = "系列会社区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String affiliatedCompanyCode;

	/** 連結会社区分 */
	@Schema(description = "連結会社区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String connectCompanyCls;

	/** ICI更新日時 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "ICI更新日時", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date iciUpdtDt;

	/** MoM最終更新日時時刻 */
	@Temporal(TemporalType.TIMESTAMP)
	@Schema(description = "MoM最終更新日時時刻", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date scMomUpdtDtTm;
}