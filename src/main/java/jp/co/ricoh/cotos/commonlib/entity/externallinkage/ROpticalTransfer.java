package jp.co.ricoh.cotos.commonlib.entity.externallinkage;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.Valid;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * リコーひかり異動情報を表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "r_optical_transfer")
public class ROpticalTransfer extends EntityBase {

	@Description(value = "異動情報種別")
	public enum OpticalTransferDiv {

		ひかり回線("1"), 電話回線("2"), リモートサポート("3");

		private final String text;

		private OpticalTransferDiv(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static OpticalTransferDiv fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "更新フラグ")
	public enum OpticalUpdateFlg {

		新設("1"), 変更("2"), 削除("3");

		private final String text;

		private OpticalUpdateFlg(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static OpticalUpdateFlg fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	/**
	 * リコーひかり異動情報ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "r_optical_transfer_seq")
	@SequenceGenerator(name = "r_optical_transfer_seq", sequenceName = "r_optical_transfer_seq", allocationSize = 1)
	@Schema(description = "リコーひかり異動情報ID(作成時不要)", required = true, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * リコーひかり異動情報取込管理ID
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "r_optical_transfer_manage_id", referencedColumnName = "id")
	@Schema(description = "リコーひかり異動情報取込管理", required = true)
	@JsonIgnore
	private ROpticalTransferManage rOpticalTransferManage;

	/**
	 * 異動情報種別
	 */
	@Schema(description = "異動情報種別", required = false, allowableValues = "ひかり回線(\"1\"), 電話回線(\"2\"), リモートサポート(\"3\")", example = "1")
	private OpticalTransferDiv transferDiv;

	/**
	 * ステータス
	 */
	@Schema(description = "ステータス", required = false, allowableValues = "range[0,255]")
	private String status;

	/**
	 * 異動情報契約開始日
	 */
	@Schema(description = "異動情報契約開始日", required = false)
	@Temporal(TemporalType.DATE)
	private Date transferContractTermStart;

	/**
	 * 異動情報契約廃止日
	 */
	@Schema(description = "異動情報契約廃止日", required = false)
	@Temporal(TemporalType.DATE)
	private Date transferContractTermEnd;

	/**
	 * 契約電話番号
	 */
	@Schema(description = "契約電話番号", required = false, allowableValues = "range[0,255]")
	private String contractPhoneNumber;

	/**
	 * サービス名
	 */
	@Schema(description = "サービス名", required = false, allowableValues = "range[0,255]")
	private String serviceName;

	/**
	 * 更新フラグ
	 */
	@Schema(description = "更新フラグ", required = false, allowableValues = "新設(\"1\"), 変更(\"2\"), 削除(\"3\")", example = "1")
	private OpticalUpdateFlg updateFlg;

	/**
	 * リコーひかり異動情報NWサービス
	 */
	@Valid
	@OneToMany(mappedBy = "rOpticalTransfer")
	@Schema(description = "リコーひかり異動情報NWサービス", required = false, readOnly = true)
	private List<ROpticalTransferNwservice> rOpticalTransferNwserviceList;

	/**
	 * NTT名義
	 */
	@Schema(description = "NTT名義", required = false, allowableValues = "range[0,255]")
	private String contractorName;

	/**
	 * 設置先住所
	 */
	@Schema(description = "設置先住所", required = false, allowableValues = "range[0,255]")
	private String curuserAddrCode;

	/**
	 * 回線種別
	 */
	@Schema(description = "回線種別", required = false, allowableValues = "range[0,255]")
	private String serviceItem;

	/**
	 * 電話番号
	 */
	@Schema(description = "電話番号", required = false, allowableValues = "range[0,255]")
	private String contractTelNo;

	/**
	 * 追加電話番号1
	 */
	@Schema(description = "追加電話番号1", required = false, allowableValues = "range[0,255]")
	private String addContractTelNo1;

	/**
	 * 追加電話番号2
	 */
	@Schema(description = "追加電話番号2", required = false, allowableValues = "range[0,255]")
	private String addContractTelNo2;

	/**
	 * 追加電話番号3
	 */
	@Schema(description = "追加電話番号3", required = false, allowableValues = "range[0,255]")
	private String addContractTelNo3;

	/**
	 * 追加電話番号4
	 */
	@Schema(description = "追加電話番号4", required = false, allowableValues = "range[0,255]")
	private String addContractTelNo4;

	/**
	 * 追加電話番号5
	 */
	@Schema(description = "追加電話番号5", required = false, allowableValues = "range[0,255]")
	private String addContractTelNo5;

}
