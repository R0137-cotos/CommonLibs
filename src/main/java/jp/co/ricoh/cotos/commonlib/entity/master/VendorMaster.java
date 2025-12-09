package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.Arrays;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ベンダーマスタ
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "vendor_master")
public class VendorMaster extends EntityBaseMaster {

	@Description(value = "eTransporter連携区分")
	public enum EtransporterLinkageDiv {

		連携しない("0"), 連携する_メール送信しない("1"), 連携する_メール送信する("2");

		private final String text;

		private EtransporterLinkageDiv(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static EtransporterLinkageDiv fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	/**
	 * ベンダーマスタID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vendor_master_seq")
	@SequenceGenerator(name = "vendor_master_seq", sequenceName = "vendor_master_seq", allocationSize = 1)
	@Schema(description = "ベンダーマスタID", required = true, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 仕入先コード
	 */
	@Size(max = 255)
	@Schema(description = "仕入先コード", required = true, allowableValues = "range[0,255]")
	private String vendorCode;

	/**
	 * ベンダー名称
	 */
	@Size(max = 255)
	@Schema(description = "ベンダー名称", required = false, allowableValues = "range[0,255]")
	private String vendorName;

	/**
	 * メールアドレス
	 */
	@Size(max = 1000)
	@Schema(description = "メールアドレス", required = false, allowableValues = "range[0,1000]")
	private String mailAddress;

	/**
	 * ベンダー商品マスタ
	 */
	@OneToMany(mappedBy = "vendorMaster")
	@JsonIgnore
	@Schema(description = "ベンダー商品マスタ", required = true)
	private List<VendorProductMaster> vendorProductMasterList;

	/**
	 * 添付ファイルパスワード不要
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "添付ファイルパスワード不要", required = false, allowableValues = "range[0,9]")
	private Integer attachedFilePasswordUnrequired;

	/**
	 * eTransporter連携区分
	 */
	@Schema(description = "eTransporter連携区分", required = false, allowableValues = "連携しない(\"0\"), 連携する_メール送信しない(\"1\"), 連携する_メール送信する(\"2\")")
	private EtransporterLinkageDiv etransporterLinkageDiv;

	/**
	 * メールアドレス(CC)
	 */
	@Size(max = 1000)
	@Schema(description = "メールアドレス(CC)", required = false, allowableValues = "range[0,1000]")
	private String mailAddressCc;
}
