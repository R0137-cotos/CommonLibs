package jp.co.ricoh.cotos.commonlib.entity.common;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.DealerFlowOrder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * COTOS販売店エンティティー共通項目 COTOSの販売店情報を管理するエンティティーはこのクラスのサブクラスとしてください。
 * 当クラスに項目追加する場合は、以下のクラスにも同様の項目を追加してください。
 * jp.co.ricoh.cotos.commonlib.dto.parameter.common.DealerAbstractDto
 */
@EqualsAndHashCode(callSuper = false)
@MappedSuperclass
@Data
public class DealerAbstractEntity extends EntityBase {

	/**
	 * MoM企事部システム連携ID
	 */
	@Size(max = 255)
	@Schema(description = "MoM企事部システム連携ID<br/>※POST時「企事部マスタ」存在チェック実施", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String momKjbSystemId;

	/**
	 * 販売店名
	 */
	@Size(max = 255)
	@Schema(description = "販売店名(作成時不要)", required = false, allowableValues = "range[0,255]", readOnly = true)
	private String dealerName;

	/**
	 * 郵便番号
	 */
	@Size(max = 255)
	@Schema(description = "郵便番号(作成時不要)", required = false, allowableValues = "range[0,255]", readOnly = true)
	private String postNumber;

	/**
	 * 住所
	 */
	@Size(max = 1000)
	@Schema(description = "住所(作成時不要)", required = false, allowableValues = "range[0,1000]", readOnly = true)
	private String address;

	/**
	 * 会社代表電話番号
	 */
	@Size(max = 255)
	@Schema(description = "会社代表電話番号(作成時不要)", required = false, allowableValues = "range[0,255]", readOnly = true)
	private String orgPhoneNumber;

	/**
	 * 担当者名
	 */
	@Size(max = 255)
	@Schema(description = "担当者名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String picName;

	/**
	 * 担当者部署名
	 */
	@Size(max = 255)
	@Schema(description = "担当者部署名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String picDeptName;

	/**
	 * 担当者電話番号
	 */
	@Size(max = 255)
	@Schema(description = "担当者電話番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String picPhoneNumber;

	/**
	 * 担当者FAX番号
	 */
	@Size(max = 255)
	@Schema(description = "担当者FAX番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String picFaxNumber;

	/**
	 * 販売店商流順
	 */
	@Column(nullable = false)
	@NotNull
	@Schema(description = "販売店商流順", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "販売店(\"1\"), 母店(\"2\")", example = "1")
	private DealerFlowOrder dealerFlowOrder;

	/**
	 * MoM会社ID
	 */
	@Size(max = 255)
	@Schema(description = "MoM会社ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String distributorMomCmpId;

	/**
	 * MoM販売店識別コード
	 */
	@Size(max = 255)
	@Schema(description = "MoM販売店識別コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String distributorMomShikiCd;

	/**
	 * MoM組織ID
	 */
	@Size(max = 255)
	@Schema(description = "MoM組織ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String distributorMomSoshikiId;

	/**
	 * MoMデポコード
	 */
	@Size(max = 255)
	@Schema(description = "MoMデポコード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String distributorMomDepoCd;

	/**
	 * MoM設置届先サイトID
	 */
	@Size(max = 255)
	@Schema(description = "MoM設置届先サイトID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String orbSendSiteId;

	/**
	 * 担当者名（カナ）
	 */
	@Size(max = 255)
	@Schema(description = "担当者名（カナ）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String picNameKana;

	/**
	 * 販売店名（カナ）
	 */
	@Size(max = 255)
	@Schema(description = "販売店名（カナ）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String dealerNameKana;

	/**
	 * MoM非連携_企業代表者名
	 */
	@Size(max = 255)
	@Schema(description = "MoM非連携_企業代表者名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String companyRepresentativeName;

	/**
	 * MoM非連携_企業代表者名（カナ）
	 */
	@Size(max = 255)
	@Schema(description = "MoM非連携_企業代表者名（カナ）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String companyRepresentativeNameKana;

	/**
	 * MoM企事部ID
	 */
	@Size(max = 255)
	@Schema(description = "MoM企事部ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String momCustId;

	/**
	 * MoM企業ID
	 */
	@Size(max = 255)
	@Schema(description = "MoM企業ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String momCompanyId;
}
