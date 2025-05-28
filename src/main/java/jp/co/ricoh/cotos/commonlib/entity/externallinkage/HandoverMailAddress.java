package jp.co.ricoh.cotos.commonlib.entity.externallinkage;

import java.util.Arrays;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 引継ぎ用メールアドレスを表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "handover_mail_address")
public class HandoverMailAddress extends EntityBase {

	@Description(value = "引継ぎ区分")
	public enum HandoverDiv {

		無償("1"), 有償("2");

		private final String text;

		private HandoverDiv(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static HandoverDiv fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	/**
	 * 引継ぎ用メールアドレスID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "handover_mail_address_seq")
	@SequenceGenerator(name = "handover_mail_address_seq", sequenceName = "handover_mail_address_seq", allocationSize = 1)
	@ApiModelProperty(value = "引継ぎ用メールアドレスID(作成時不要)", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 契約ID
	 */
	@Min(0)
	@ApiModelProperty(value = "契約ID", required = false, position = 2, allowableValues = "range[0,9223372036854775807]")
	private Long contractId;

	/**
	 * 引継ぎ区分
	 */
	@ApiModelProperty(value = " 引継ぎ区分", required = false, position = 3, allowableValues = "無償(\"1\"), 有償(\"2\")")
	private HandoverDiv handoverDiv;

	/**
	 * メールアドレス
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "メールアドレス", required = false, position = 4, allowableValues = "range[0,255]")
	private String mailAddress;

	/**
	 * 引継ぎ元契約ID
	 */
	@Min(0)
	@ApiModelProperty(value = "引継ぎ元契約ID", required = false, position = 5, allowableValues = "range[0,9223372036854775807]")
	private Long handoverContractId;

	/**
	 * 引継ぎ元RJ管理番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "引継ぎ元RJ管理番号", required = false, position = 6, allowableValues = "range[0,255]")
	private String handoverRjManageNumber;
}
