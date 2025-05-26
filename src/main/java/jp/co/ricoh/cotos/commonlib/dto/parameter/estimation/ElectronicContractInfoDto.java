package jp.co.ricoh.cotos.commonlib.dto.parameter.estimation;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(value = "イントラマート電子契約事前確認申請管理番号", required = false, position = 3, allowableValues = "range[0,255]")
	private String imfrSdInsertId;

	/**
	 * イントラマート電子契約事前確認申請行番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "イントラマート電子契約事前確認申請行番号", required = false, position = 4, allowableValues = "range[0,255]")
	private String imfrSdRowNo;

	/**
	 * 企業ID
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "企業ID", required = false, position = 5, allowableValues = "range[0,255]")
	private String companyId;

	/**
	 * 企業名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "企業名", required = false, position = 6, allowableValues = "range[0,255]")
	private String companyName;

	/**
	 * 事業所ID
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "事業所ID", required = false, position = 7, allowableValues = "range[0,255]")
	private String officeId;

	/**
	 * 事業所名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "事業所名", required = false, position = 8, allowableValues = "range[0,255]")
	private String officeName;

	/**
	 * 一次承認者名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "一次承認者名", required = false, position = 9, allowableValues = "range[0,255]")
	private String primaryApprover;

	/**
	 * 一次承認者所属
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "一次承認者所属", required = false, position = 10, allowableValues = "range[0,255]")
	private String primaryDep;

	/**
	 * 一次承認者役職
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "一次承認者役職", required = false, position = 11, allowableValues = "range[0,255]")
	private String primaryPostname;

	/**
	 * 一次承認者メールアドレス
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "一次承認者メールアドレス", required = false, position = 12, allowableValues = "range[0,255]")
	private String primaryEmail;

	/**
	 * 決裁者名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "決裁者名", required = false, position = 13, allowableValues = "range[0,255]")
	private String finalApprover;

	/**
	 * 決裁者所属
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "決裁者所属", required = false, position = 15, allowableValues = "range[0,255]")
	private String finalDep;

	/**
	 * 決裁者役職
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "決裁者役職", required = false, position = 16, allowableValues = "range[0,255]")
	private String finalPostname;

	/**
	 * 決裁者メールアドレス
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "決裁者メールアドレス", required = false, position = 17, allowableValues = "range[0,255]")
	private String finalEmail;

	/**
	 * お客様ご担当者区分
	 */
	@ApiModelProperty(value = "お客様ご担当者区分", required = false, position = 18, allowableValues = "お客様情報を使用する(\"1\"), 決裁権限者をお客様担当者に設定する(\"2\"), 一次承認者をお客様担当者に設定する(\"3\"), お客様にクラウドサイン上で入力いただく(\"4\")")
	private CustomerPicDiv customerPicDiv;

	/**
	 * 利用開始希望日区分
	 */
	@ApiModelProperty(value = "利用開始希望日区分", required = false, position = 19, allowableValues = "SA入力(\"1\"), お客様に入力いただく(\"2\")")
	private DesiredStartDateDiv desiredStartDateDiv;

	/**
	 * サービス利用希望日
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "サービス利用希望日", required = false, position = 20)
	private Date conclusionPreferredDate;

	/**
	 * 支払条件
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "支払条件", required = false, position = 21, allowableValues = "range[0,255]")
	private String paymentTerms;

	/**
	 * 特記事項
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "特記事項", required = false, position = 22, allowableValues = "range[0,255]")
	private String specialNotes;

	/**
	 * 電子契約ドキュメントキー
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "電子契約ドキュメントキー", required = false, position = 23, allowableValues = "range[0,255]")
	private String electronicContractDocumentKey;

	/**
	 * 電子契約ドキュメントID
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "電子契約ドキュメントID", required = false, position = 24, allowableValues = "range[0,255]")
	private String electronicContractDocumentId;

	/**
	 * 電子契約文書登録日時
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@ApiModelProperty(value = "電子契約文書登録日時", required = false, position = 25)
	private Date electronicContractDocumentCreatedAt;
}
