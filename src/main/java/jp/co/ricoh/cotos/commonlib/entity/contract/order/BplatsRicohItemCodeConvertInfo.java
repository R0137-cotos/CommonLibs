package jp.co.ricoh.cotos.commonlib.entity.contract.order;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Bplatsリコー品種コード変換情報
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "bplats_ricoh_item_code_convert_info")
public class BplatsRicohItemCodeConvertInfo extends EntityBase {

	/**
	 * ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bplats_ricoh_item_code_convert_info_seq")
	@SequenceGenerator(name = "bplats_ricoh_item_code_convert_info_seq", sequenceName = "bplats_ricoh_item_code_convert_info_seq", allocationSize = 1)
	@Schema(description = "ID(作成時不要)", allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * RJ管理番号
	 */
	@Size(max = 255)
	@Schema(description = "RJ管理番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String rjManageNumber;

	/**
	 * Bplats用ダミーリコー品種コード
	 */
	@Size(max = 255)
	@Schema(description = "Bplats用ダミーリコー品種コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String bplatsDummyRicohItemCode;

	/**
	 * 正規リコー品種コード
	 */
	@Size(max = 255)
	@Schema(description = "正規リコー品種コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String regularRicohItemCode;

}
