package jp.co.ricoh.cotos.commonlib.entity.communication;

import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * バウンスメール宛先を表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "boumce_mail_destination")
public class BounceMailDestination extends EntityBase {

	@Description(value = "宛先区分")
	public enum DestinationDiv {

		TO("1"), CC("2");

		private final String text;

		private DestinationDiv(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static DestinationDiv fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst()
					.orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bounce_mail_destination_seq")
	@SequenceGenerator(name = "bounce_mail_destination_seq", sequenceName = "bounce_mail_destination_seq", allocationSize = 1)
	@ApiModelProperty(value = "バウンスメール宛先ID (作成時不要)", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * バウンスメール記録
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "bounce_mail_record_id", referencedColumnName = "id")
	@JsonIgnore
	@ApiModelProperty(value = "バウンスメール記録", required = true, position = 2)
	private BounceMailRecord bounceMailRecord;

	/**
	 * 宛先区分
	 */
	@ApiModelProperty(value = "宛先区分", required = false, allowableValues = "TO(\"1\"), CC(\"2\")", example = "1", position = 3)
	private DestinationDiv destinationDiv;

	/**
	 * メールアドレス
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "メールアドレス", required = false, position = 4, allowableValues = "range[0,255]")
	private String mailAddress;
}
