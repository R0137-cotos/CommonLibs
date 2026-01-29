package jp.co.ricoh.cotos.commonlib.dto.parameter.estimation;

import java.util.Date;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.dto.parameter.common.DtoBase;
import jp.co.ricoh.cotos.commonlib.entity.estimation.ElectronicContractInfo.CustomerPicDiv;
import jp.co.ricoh.cotos.commonlib.entity.estimation.ElectronicContractInfo.DesiredStartDateDiv;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 電子契約情報を表すDto
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class ElectronicContractInfoDto extends DtoBase {

	/**
	 * イントラマート電子契約事前確認申請管理番号
	 */
	@Size(max = 255)
	@Schema(description = "イントラマート電子契約事前確認申請管理番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String imfrSdInsertId;

	/**
	 * イントラマート電子契約事前確認申請行番号
	 */
	@Size(max = 255)
	@Schema(description = "イントラマート電子契約事前確認申請行番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String imfrSdRowNo;

	/**
	 * 企業ID
	 */
	@Size(max = 255)
	@Schema(description = "企業ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String companyId;

	/**
	 * 企業名
	 */
	@Size(max = 255)
	@Schema(description = "企業名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String companyName;

	/**
	 * 事業所ID
	 */
	@Size(max = 255)
	@Schema(description = "事業所ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String officeId;

	/**
	 * 事業所名
	 */
	@Size(max = 255)
	@Schema(description = "事業所名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String officeName;

	/**
	 * 一次承認者名
	 */
	@Size(max = 255)
	@Schema(description = "一次承認者名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String primaryApprover;

	/**
	 * 一次承認者所属
	 */
	@Size(max = 255)
	@Schema(description = "一次承認者所属", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String primaryDep;

	/**
	 * 一次承認者役職
	 */
	@Size(max = 255)
	@Schema(description = "一次承認者役職", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String primaryPostname;

	/**
	 * 一次承認者メールアドレス
	 */
	@Size(max = 255)
	@Schema(description = "一次承認者メールアドレス", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String primaryEmail;

	/**
	 * 決裁者名
	 */
	@Size(max = 255)
	@Schema(description = "決裁者名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String finalApprover;

	/**
	 * 決裁者所属
	 */
	@Size(max = 255)
	@Schema(description = "決裁者所属", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String finalDep;

	/**
	 * 決裁者役職
	 */
	@Size(max = 255)
	@Schema(description = "決裁者役職", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String finalPostname;

	/**
	 * 決裁者メールアドレス
	 */
	@Size(max = 255)
	@Schema(description = "決裁者メールアドレス", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String finalEmail;

	/**
	 * お客様ご担当者区分
	 */
	@Schema(description = "お客様ご担当者区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "お客様情報を使用する(\"1\"), 決裁権限者をお客様担当者に設定する(\"2\"), 一次承認者をお客様担当者に設定する(\"3\"), お客様にクラウドサイン上で入力いただく(\"4\")")
	private CustomerPicDiv customerPicDiv;

	/**
	 * 利用開始希望日区分
	 */
	@Schema(description = "利用開始希望日区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "SA入力(\"1\"), お客様に入力いただく(\"2\")")
	private DesiredStartDateDiv desiredStartDateDiv;

	/**
	 * サービス利用希望日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "サービス利用希望日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date conclusionPreferredDate;

	/**
	 * 支払条件
	 */
	@Size(max = 255)
	@Schema(description = "支払条件", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String paymentTerms;

	/**
	 * 特記事項
	 */
	@Size(max = 255)
	@Schema(description = "特記事項", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String specialNotes;

	/**
	 * 電子契約ドキュメントキー
	 */
	@Size(max = 255)
	@Schema(description = "電子契約ドキュメントキー", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String electronicContractDocumentKey;

	/**
	 * 電子契約ドキュメントID
	 */
	@Size(max = 255)
	@Schema(description = "電子契約ドキュメントID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String electronicContractDocumentId;

	/**
	 * 電子契約文書登録日時
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Schema(description = "電子契約文書登録日時", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date electronicContractDocumentCreatedAt;
}
