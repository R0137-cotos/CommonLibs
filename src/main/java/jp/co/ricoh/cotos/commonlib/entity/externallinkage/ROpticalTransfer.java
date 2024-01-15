package jp.co.ricoh.cotos.commonlib.entity.externallinkage;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(value = "リコーひかり異動情報ID(作成時不要)", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * リコーひかり異動情報取込管理ID
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "r_optical_transfer_manage_id", referencedColumnName = "id")
	@ApiModelProperty(value = "リコーひかり異動情報取込管理", required = true, position = 2)
	@JsonIgnore
	private ROpticalTransferManage rOpticalTransferManage;

	/**
	 * 異動情報種別
	 */
	@ApiModelProperty(value = "異動情報種別", required = false, allowableValues = "ひかり回線(\"1\"), 電話回線(\"2\"), リモートサポート(\"3\")", example = "1", position = 3)
	private OpticalTransferDiv transferDiv;

	/**
	 * ステータス
	 */
	@ApiModelProperty(value = "ステータス", required = false, position = 4, allowableValues = "range[0,255]")
	private String status;

	/**
	 * 異動情報契約開始日
	 */
	@ApiModelProperty(value = "異動情報契約開始日", required = false, position = 5)
	@Temporal(TemporalType.DATE)
	private Date transferContractTermStart;

	/**
	 * 異動情報契約廃止日
	 */
	@ApiModelProperty(value = "異動情報契約廃止日", required = false, position = 6)
	@Temporal(TemporalType.DATE)
	private Date transferContractTermEnd;

	/**
	 * 契約電話番号
	 */
	@ApiModelProperty(value = "契約電話番号", required = false, position = 7, allowableValues = "range[0,255]")
	private String contractPhoneNumber;

	/**
	 * サービス名
	 */
	@ApiModelProperty(value = "サービス名", required = false, position = 8, allowableValues = "range[0,255]")
	private String serviceName;

	/**
	 * 更新フラグ
	 */
	@ApiModelProperty(value = "更新フラグ", required = false, allowableValues = "新設(\"1\"), 変更(\"2\"), 削除(\"3\")", example = "1", position = 9)
	private OpticalUpdateFlg updateFlg;

	/**
	 * リコーひかり異動情報NWサービス
	 */
	@Valid
	@OneToMany(mappedBy = "rOpticalTransfer")
	@ApiModelProperty(value = "リコーひかり異動情報NWサービス", required = false, position = 10, readOnly = true)
	private List<ROpticalTransferNwservice> rOpticalTransferNwserviceList;

	/**
	 * NTT名義
	 */
	@ApiModelProperty(value = "NTT名義", required = false, position = 11, allowableValues = "range[0,255]")
	private String contractorName;

	/**
	 * 設置先住所
	 */
	@ApiModelProperty(value = "設置先住所", required = false, position = 12, allowableValues = "range[0,255]")
	private String curuserAddrCode;

	/**
	 * 回線種別
	 */
	@ApiModelProperty(value = "回線種別", required = false, position = 13, allowableValues = "range[0,255]")
	private String serviceItem;

	/**
	 * 電話番号
	 */
	@ApiModelProperty(value = "電話番号", required = false, position = 14, allowableValues = "range[0,255]")
	private String contractTelNo;

	/**
	 * 追加電話番号1
	 */
	@ApiModelProperty(value = "追加電話番号1", required = false, position = 15, allowableValues = "range[0,255]")
	private String addContractTelNo1;

	/**
	 * 追加電話番号2
	 */
	@ApiModelProperty(value = "追加電話番号2", required = false, position = 16, allowableValues = "range[0,255]")
	private String addContractTelNo2;

	/**
	 * 追加電話番号3
	 */
	@ApiModelProperty(value = "追加電話番号3", required = false, position = 16, allowableValues = "range[0,255]")
	private String addContractTelNo3;

	/**
	 * 追加電話番号4
	 */
	@ApiModelProperty(value = "追加電話番号4", required = false, position = 16, allowableValues = "range[0,255]")
	private String addContractTelNo4;

	/**
	 * 追加電話番号5
	 */
	@ApiModelProperty(value = "追加電話番号5", required = false, position = 17, allowableValues = "range[0,255]")
	private String addContractTelNo5;

}
