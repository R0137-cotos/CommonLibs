package jp.co.ricoh.cotos.commonlib.entity.common;

import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.OsoDealerFlow;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.OsoProcessingStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * OSO申込データエンティティー共通項目
 */
@EqualsAndHashCode(callSuper = false)
@MappedSuperclass
@Data
public class OsoRequestDataAbstractEntity extends EntityBase {

	@Description(value = "OSO管理番号採番状態")
	public enum OsoManageNumberStatus {

		未処理("0"), 処理済("1"), 処理対象外("9"), 処理エラー("E");

		private final String text;

		private OsoManageNumberStatus(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static OsoManageNumberStatus fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "データ区分")
	public enum DataDiv {

		新規("1"), 変更("2");

		private final String text;

		private DataDiv(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static DataDiv fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	/**
	 * RJ管理番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "RJ管理番号", required = false, position = 2, allowableValues = "range[0,255]")
	private String rjManageNumber;

	/**
	 * 契約ID
	 */
	@Column(nullable = false)
	@ApiModelProperty(value = "契約ID", required = true, position = 3, allowableValues = "range[0,9999999999999999999]")
	private long contractId;

	/**
	 * 契約明細ID
	 */
	@Column(nullable = false)
	@ApiModelProperty(value = "契約明細ID", required = true, position = 4, allowableValues = "range[0,9999999999999999999]")
	private long contractDetailId;

	/**
	 * 処理状態
	 */
	@ApiModelProperty(value = "処理状態", required = false, allowableValues = "未処理(\"0\"), 処理済(\"1\"), 処理対象外(\"9\"), 処理エラー(\"E\")", example = "0", position = 5)
	private OsoProcessingStatus processingStatus;

	/**
	 * 処理日時
	 */
	@ApiModelProperty(value = "処理日時", required = false, position = 6)
	@Temporal(TemporalType.TIMESTAMP)
	private Date processingAt;

	/**
	 * メッセージ
	 */
	@Column(name = "message")
	@Size(max = 4000)
	@ApiModelProperty(value = "メッセージ", required = false, position = 7, allowableValues = "range[0,4000]")
	private String osoMessage;

	/**
	 * 申込書管理NO
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "申込書管理NO", required = false, position = 8, allowableValues = "range[0,255]")
	private String requestManageNumber;

	/**
	 * RTS管理番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "RTS管理番号", required = false, position = 9, allowableValues = "range[0,255]")
	private String rtsManageNumber;

	/**
	 * RTS管理番号枝番
	 */
	@Max(999)
	@Min(0)
	@ApiModelProperty(value = "RTS管理番号枝番", required = false, position = 10, allowableValues = "range[1,999]")
	private Integer rtsManageBranchNumber;

	/**
	 * OSO管理番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "OSO管理番号", required = false, position = 11, allowableValues = "range[0,255]")
	private String osoManageNumber;

	/**
	 * OSO管理番号採番状態
	 */
	@ApiModelProperty(value = "OSO管理番号採番状態", required = false, allowableValues = "未処理(\"0\"), 処理済(\"1\"), 処理対象外(\"9\"), 処理エラー(\"E\")", example = "0", position = 12)
	private OsoManageNumberStatus osoManageNumberStatus;

	/**
	 * OSO管理番号登録日時
	 */
	@ApiModelProperty(value = "OSO管理番号登録日時", required = false, position = 13)
	@Temporal(TemporalType.TIMESTAMP)
	private Date osoManageNumberCreatedAt;

	/**
	 * データ区分
	 */
	@ApiModelProperty(value = "データ区分", required = false, allowableValues = "新規(\"1\"), 変更(\"2\")", example = "1", position = 14)
	private DataDiv dataDiv;

	/**
	 * 仕入先コード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "仕入先コード", required = false, position = 15, allowableValues = "range[0,255]")
	private String vendorCode;

	/**
	 * OSO商流区分
	 */
	@ApiModelProperty(value = "OSO商流区分", required = false, allowableValues = "販社売上(\"1\"), 販売店売上(\"2\"), リコー売上(\"3\")", example = "1", position = 16)
	private OsoDealerFlow osoDealerFlow;

	/**
	 * 届先コード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "届先コード", required = false, position = 17, allowableValues = "range[0,255]")
	private String deliveryCode;

	/**
	 * 販社コード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "販社コード", required = false, position = 18, allowableValues = "range[0,255]")
	private String salesCompanyCode;

	/**
	 * 販社得意先コード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "販社得意先コード", required = false, position = 19, allowableValues = "range[0,255]")
	private String salesCompanyCustomerCode;

	/**
	 * 販社届先コード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "販社届先コード", required = false, position = 20, allowableValues = "range[0,255]")
	private String salesCompanyDeliveryCode;

	/**
	 * 月額回収期間（開始）
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "月額回収期間（開始）", required = false, position = 21, allowableValues = "range[0,255]")
	private String collectionPeriodStart;

	/**
	 * 月額回収期間（終了）
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "月額回収期間（終了）", required = false, position = 22, allowableValues = "range[0,255]")
	private String collectionPeriodEnd;

	/**
	 * 申込者：会社名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "申込者：会社名", required = false, position = 23, allowableValues = "range[0,255]")
	private String applicantCompanyName;

	/**
	 * 申込者：代表者名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "申込者：代表者名", required = false, position = 24, allowableValues = "range[0,255]")
	private String applicantRepresentativeName;

	/**
	 * 申込者：住所
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "申込者：住所", required = false, position = 25, allowableValues = "range[0,255]")
	private String applicantAddress;

	/**
	 * 申込者：郵便番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "申込者：郵便番号", required = false, position = 26, allowableValues = "range[0,255]")
	private String applicantPostNumber;

	/**
	 * 申込者：担当部課名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "申込者：担当部課名", required = false, position = 27, allowableValues = "range[0,255]")
	private String applicantPicDepartmentName;

	/**
	 * 申込者：担当電話番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "申込者：担当電話番号", required = false, position = 28, allowableValues = "range[0,255]")
	private String applicantPicPhoneNumber;

	/**
	 * 申込者：担当FAX番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "申込者：担当FAX番号", required = false, position = 29, allowableValues = "range[0,255]")
	private String applicantPicFaxNumber;

	/**
	 * 申込者：担当者名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "申込者：担当者名", required = false, position = 30, allowableValues = "range[0,255]")
	private String applicantPicName;

	/**
	 * 担当販売店：販売店名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "担当販売店：販売店名", required = false, position = 31, allowableValues = "range[0,255]")
	private String resellerDealerName;

	/**
	 * 担当販売店：営業所
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "担当販売店：営業所", required = false, position = 32, allowableValues = "range[0,255]")
	private String resellerServiceOfficeName;

	/**
	 * 担当販売店：契約管理NO
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "担当販売店：契約管理NO", required = false, position = 33, allowableValues = "range[0,255]")
	private String resellerContractMamageNumber;

	/**
	 * 担当販売店：電話番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "担当販売店：電話番号", required = false, position = 34, allowableValues = "range[0,255]")
	private String resellerPhoneNumber;

	/**
	 * 担当販売店：FAX
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "担当販売店：FAX", required = false, position = 35, allowableValues = "range[0,255]")
	private String resellerFaxNumber;

	/**
	 * 担当販売店：担当者
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "担当販売店：担当者", required = false, position = 36, allowableValues = "range[0,255]")
	private String resellerPicName;

	/**
	 * 担当販社：販売店名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "担当販社：販売店名", required = false, position = 37, allowableValues = "range[0,255]")
	private String salesCompanyDealerName;

	/**
	 * 担当販社：営業所
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "担当販社：営業所", required = false, position = 38, allowableValues = "range[0,255]")
	private String salesCompanyServiceOfficeName;

	/**
	 * 担当販社：仕入用管理NO
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "担当販社：仕入用管理NO", required = false, position = 39, allowableValues = "range[0,255]")
	private String salesCompanyPurchaseMamageNumber;

	/**
	 * 担当販社：電話番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "担当販社：電話番号", required = false, position = 40, allowableValues = "range[0,255]")
	private String salesCompanyPhoneNumber;

	/**
	 * 担当販社：FAX
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "担当販社：FAX", required = false, position = 41, allowableValues = "range[0,255]")
	private String salesCompanyFaxNumber;

	/**
	 * 担当販社：担当者
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "担当販社：担当者", required = false, position = 42, allowableValues = "range[0,255]")
	private String salesCompanyPicName;

	/**
	 * 担当販社：担当者
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "受付区：営業所名", required = false, position = 43, allowableValues = "range[0,255]")
	private String receptionWardServiceOfficeName;

	/**
	 * 受付区：課所コード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "受付区：課所コード", required = false, position = 44, allowableValues = "range[0,255]")
	private String receptionWardLocationCode;

	/**
	 * 受付区：電話番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "受付区：電話番号", required = false, position = 45, allowableValues = "range[0,255]")
	private String receptionWardPhoneNumber;

	/**
	 * 受付区：FAX
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "受付区：FAX", required = false, position = 46, allowableValues = "range[0,255]")
	private String receptionWardFaxNumber;

	/**
	 * 受付区：担当者
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "受付区：担当者", required = false, position = 47, allowableValues = "range[0,255]")
	private String receptionWardPicName;

	/**
	 * 担当区：営業所名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "担当区：営業所名", required = false, position = 48, allowableValues = "range[0,255]")
	private String chargeWardServiceOfficeName;

	/**
	 * 担当区：課所コード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "担当区：課所コード", required = false, position = 49, allowableValues = "range[0,255]")
	private String chargeWardLocationCode;

	/**
	 * 担当区：電話番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "担当区：電話番号", required = false, position = 50, allowableValues = "range[0,255]")
	private String chargeWardPhoneNumber;

	/**
	 * 担当区：特記事項
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "担当区：特記事項", required = false, position = 51, allowableValues = "range[0,255]")
	private String chargeWardNotices;

	/**
	 * 備考
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "備考", required = false, position = 52, allowableValues = "range[0,255]")
	private String remarks;

	/**
	 * COTOS商流区分
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "COTOS商流区分", required = false, position = 53, allowableValues = "range[0,255]")
	private String cotosDealerFlow;
}
