package jp.co.ricoh.cotos.commonlib.entity.contract;

import java.util.Arrays;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 契約機種(Isys-Oneへの連携なし)を表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "contract_equipment_no_isysone")
public class ContractEquipmentNoIsysone extends EntityBase {

	@Description(value = "機器区分（Isys-Oneへの連携なし）")
	public enum MachineTypeNoIsysone {
		ハードディスク("1"), 内蔵オプション("2"), 外付オプション("3"), 導入ソフトウェア("4");

		private final String text;

		private MachineTypeNoIsysone(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static MachineTypeNoIsysone fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contract_equipment_no_isysone_seq")
	@SequenceGenerator(name = "contract_equipment_no_isysone_seq", sequenceName = "contract_equipment_no_isysone_seq", allocationSize = 1)
	@Schema(description = "ID(作成時不要)", required = true, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 機器区分
	 */
	@Schema(description = "機器区分（Isys-Oneへの連携なし）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "ハードディスク(\"1\"), 内蔵オプション(\"2\"), 外付オプション(\"3\"), 導入ソフトウェア(\"4\")", example = "1")
	private MachineTypeNoIsysone machineTypeNoIsysone;

	/**
	 * 製品番号
	 */
	@Size(max = 255)
	@Schema(description = "製品番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String itemNo;

	/**
	 * 製品名
	 */
	@Size(max = 255)
	@Schema(description = "製品名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String goodsName;

	/**
	 * 機番
	 */
	@Size(max = 255)
	@Schema(description = "機番", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String equipmentNo;

	/**
	 * オプション名
	 */
	@Size(max = 255)
	@Schema(description = "オプション名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String optionName;

	/**
	 * 契約
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "contract_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "契約", requiredMode = Schema.RequiredMode.REQUIRED)
	private Contract contract;

}
