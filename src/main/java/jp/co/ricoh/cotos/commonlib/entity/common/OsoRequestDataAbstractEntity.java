package jp.co.ricoh.cotos.commonlib.entity.common;

import java.util.Arrays;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(description = "RJ管理番号", required = false, allowableValues = "range[0,255]")
	private String rjManageNumber;

	/**
	 * 契約ID
	 */
	@Column(nullable = false)
	@Schema(description = "契約ID", required = true, allowableValues = "range[0,9999999999999999999]")
	private long contractId;

	/**
	 * 契約明細ID
	 */
	@Column(nullable = false)
	@Schema(description = "契約明細ID", required = true, allowableValues = "range[0,9999999999999999999]")
	private long contractDetailId;

	/**
	 * 処理状態
	 */
	@Schema(description = "処理状態", required = false, allowableValues = "未処理(\"0\"), 処理済(\"1\"), 処理対象外(\"9\"), 処理エラー(\"E\")", example = "0")
	private OsoProcessingStatus processingStatus;

	/**
	 * 処理日時
	 */
	@Schema(description = "処理日時", required = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date processingAt;

	/**
	 * メッセージ
	 */
	@Column(name = "message")
	@Size(max = 4000)
	@Schema(description = "メッセージ", required = false, allowableValues = "range[0,4000]")
	private String osoMessage;

	/**
	 * 申込書管理NO
	 */
	@Size(max = 255)
	@Schema(description = "申込書管理NO", required = false, allowableValues = "range[0,255]")
	private String requestManageNumber;

	/**
	 * RTS管理番号
	 */
	@Size(max = 255)
	@Schema(description = "RTS管理番号", required = false, allowableValues = "range[0,255]")
	private String rtsManageNumber;

	/**
	 * RTS管理番号枝番
	 */
	@Max(999)
	@Min(0)
	@Schema(description = "RTS管理番号枝番", required = false, allowableValues = "range[1,999]")
	private Integer rtsManageBranchNumber;

	/**
	 * OSO管理番号
	 */
	@Size(max = 255)
	@Schema(description = "OSO管理番号", required = false, allowableValues = "range[0,255]")
	private String osoManageNumber;

	/**
	 * OSO管理番号採番状態
	 */
	@Schema(description = "OSO管理番号採番状態", required = false, allowableValues = "未処理(\"0\"), 処理済(\"1\"), 処理対象外(\"9\"), 処理エラー(\"E\")", example = "0")
	private OsoManageNumberStatus osoManageNumberStatus;

	/**
	 * OSO管理番号登録日時
	 */
	@Schema(description = "OSO管理番号登録日時", required = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date osoManageNumberCreatedAt;

	/**
	 * データ区分
	 */
	@Schema(description = "データ区分", required = false, allowableValues = "新規(\"1\"), 変更(\"2\")", example = "1")
	private DataDiv dataDiv;

	/**
	 * 仕入先コード
	 */
	@Size(max = 255)
	@Schema(description = "仕入先コード", required = false, allowableValues = "range[0,255]")
	private String vendorCode;

	/**
	 * OSO商流区分
	 */
	@Schema(description = "OSO商流区分", required = false, allowableValues = "販社売上(\"1\"), 販売店売上(\"2\"), リコー売上(\"3\")", example = "1")
	private OsoDealerFlow osoDealerFlow;

	/**
	 * 届先コード
	 */
	@Size(max = 255)
	@Schema(description = "届先コード", required = false, allowableValues = "range[0,255]")
	private String deliveryCode;

	/**
	 * 販社コード
	 */
	@Size(max = 255)
	@Schema(description = "販社コード", required = false, allowableValues = "range[0,255]")
	private String salesCompanyCode;

	/**
	 * 販社得意先コード
	 */
	@Size(max = 255)
	@Schema(description = "販社得意先コード", required = false, allowableValues = "range[0,255]")
	private String salesCompanyCustomerCode;

	/**
	 * 販社届先コード
	 */
	@Size(max = 255)
	@Schema(description = "販社届先コード", required = false, allowableValues = "range[0,255]")
	private String salesCompanyDeliveryCode;

	/**
	 * 月額回収期間（開始）
	 */
	@Size(max = 255)
	@Schema(description = "月額回収期間（開始）", required = false, allowableValues = "range[0,255]")
	private String collectionPeriodStart;

	/**
	 * 月額回収期間（終了）
	 */
	@Size(max = 255)
	@Schema(description = "月額回収期間（終了）", required = false, allowableValues = "range[0,255]")
	private String collectionPeriodEnd;

	/**
	 * 申込者：会社名
	 */
	@Size(max = 255)
	@Schema(description = "申込者：会社名", required = false, allowableValues = "range[0,255]")
	private String applicantCompanyName;

	/**
	 * 申込者：代表者名
	 */
	@Size(max = 255)
	@Schema(description = "申込者：代表者名", required = false, allowableValues = "range[0,255]")
	private String applicantRepresentativeName;

	/**
	 * 申込者：住所
	 */
	@Size(max = 255)
	@Schema(description = "申込者：住所", required = false, allowableValues = "range[0,255]")
	private String applicantAddress;

	/**
	 * 申込者：郵便番号
	 */
	@Size(max = 255)
	@Schema(description = "申込者：郵便番号", required = false, allowableValues = "range[0,255]")
	private String applicantPostNumber;

	/**
	 * 申込者：担当部課名
	 */
	@Size(max = 255)
	@Schema(description = "申込者：担当部課名", required = false, allowableValues = "range[0,255]")
	private String applicantPicDepartmentName;

	/**
	 * 申込者：担当電話番号
	 */
	@Size(max = 255)
	@Schema(description = "申込者：担当電話番号", required = false, allowableValues = "range[0,255]")
	private String applicantPicPhoneNumber;

	/**
	 * 申込者：担当FAX番号
	 */
	@Size(max = 255)
	@Schema(description = "申込者：担当FAX番号", required = false, allowableValues = "range[0,255]")
	private String applicantPicFaxNumber;

	/**
	 * 申込者：担当者名
	 */
	@Size(max = 255)
	@Schema(description = "申込者：担当者名", required = false, allowableValues = "range[0,255]")
	private String applicantPicName;

	/**
	 * 担当販売店：販売店名
	 */
	@Size(max = 255)
	@Schema(description = "担当販売店：販売店名", required = false, allowableValues = "range[0,255]")
	private String resellerDealerName;

	/**
	 * 担当販売店：営業所
	 */
	@Size(max = 255)
	@Schema(description = "担当販売店：営業所", required = false, allowableValues = "range[0,255]")
	private String resellerServiceOfficeName;

	/**
	 * 担当販売店：契約管理NO
	 */
	@Size(max = 255)
	@Schema(description = "担当販売店：契約管理NO", required = false, allowableValues = "range[0,255]")
	private String resellerContractMamageNumber;

	/**
	 * 担当販売店：電話番号
	 */
	@Size(max = 255)
	@Schema(description = "担当販売店：電話番号", required = false, allowableValues = "range[0,255]")
	private String resellerPhoneNumber;

	/**
	 * 担当販売店：FAX
	 */
	@Size(max = 255)
	@Schema(description = "担当販売店：FAX", required = false, allowableValues = "range[0,255]")
	private String resellerFaxNumber;

	/**
	 * 担当販売店：担当者
	 */
	@Size(max = 255)
	@Schema(description = "担当販売店：担当者", required = false, allowableValues = "range[0,255]")
	private String resellerPicName;

	/**
	 * 担当販社：販売店名
	 */
	@Size(max = 255)
	@Schema(description = "担当販社：販売店名", required = false, allowableValues = "range[0,255]")
	private String salesCompanyDealerName;

	/**
	 * 担当販社：営業所
	 */
	@Size(max = 255)
	@Schema(description = "担当販社：営業所", required = false, allowableValues = "range[0,255]")
	private String salesCompanyServiceOfficeName;

	/**
	 * 担当販社：仕入用管理NO
	 */
	@Size(max = 255)
	@Schema(description = "担当販社：仕入用管理NO", required = false, allowableValues = "range[0,255]")
	private String salesCompanyPurchaseMamageNumber;

	/**
	 * 担当販社：電話番号
	 */
	@Size(max = 255)
	@Schema(description = "担当販社：電話番号", required = false, allowableValues = "range[0,255]")
	private String salesCompanyPhoneNumber;

	/**
	 * 担当販社：FAX
	 */
	@Size(max = 255)
	@Schema(description = "担当販社：FAX", required = false, allowableValues = "range[0,255]")
	private String salesCompanyFaxNumber;

	/**
	 * 担当販社：担当者
	 */
	@Size(max = 255)
	@Schema(description = "担当販社：担当者", required = false, allowableValues = "range[0,255]")
	private String salesCompanyPicName;

	/**
	 * 担当販社：担当者
	 */
	@Size(max = 255)
	@Schema(description = "受付区：営業所名", required = false, allowableValues = "range[0,255]")
	private String receptionWardServiceOfficeName;

	/**
	 * 受付区：課所コード
	 */
	@Size(max = 255)
	@Schema(description = "受付区：課所コード", required = false, allowableValues = "range[0,255]")
	private String receptionWardLocationCode;

	/**
	 * 受付区：電話番号
	 */
	@Size(max = 255)
	@Schema(description = "受付区：電話番号", required = false, allowableValues = "range[0,255]")
	private String receptionWardPhoneNumber;

	/**
	 * 受付区：FAX
	 */
	@Size(max = 255)
	@Schema(description = "受付区：FAX", required = false, allowableValues = "range[0,255]")
	private String receptionWardFaxNumber;

	/**
	 * 受付区：担当者
	 */
	@Size(max = 255)
	@Schema(description = "受付区：担当者", required = false, allowableValues = "range[0,255]")
	private String receptionWardPicName;

	/**
	 * 担当区：営業所名
	 */
	@Size(max = 255)
	@Schema(description = "担当区：営業所名", required = false, allowableValues = "range[0,255]")
	private String chargeWardServiceOfficeName;

	/**
	 * 担当区：課所コード
	 */
	@Size(max = 255)
	@Schema(description = "担当区：課所コード", required = false, allowableValues = "range[0,255]")
	private String chargeWardLocationCode;

	/**
	 * 担当区：電話番号
	 */
	@Size(max = 255)
	@Schema(description = "担当区：電話番号", required = false, allowableValues = "range[0,255]")
	private String chargeWardPhoneNumber;

	/**
	 * 担当区：特記事項
	 */
	@Size(max = 255)
	@Schema(description = "担当区：特記事項", required = false, allowableValues = "range[0,255]")
	private String chargeWardNotices;

	/**
	 * 備考
	 */
	@Size(max = 255)
	@Schema(description = "備考", required = false, allowableValues = "range[0,255]")
	private String remarks;

	/**
	 * COTOS商流区分
	 */
	@Size(max = 255)
	@Schema(description = "COTOS商流区分", required = false, allowableValues = "range[0,255]")
	private String cotosDealerFlow;
}
