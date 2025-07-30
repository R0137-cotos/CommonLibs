package jp.co.ricoh.cotos.commonlib.entity.contract.order;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(value = "ID(作成時不要)", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * RJ管理番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "RJ管理番号", required = false, position = 2, allowableValues = "range[0,255]")
	private String rjManageNumber;

	/**
	 * Bplats用ダミーリコー品種コード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "Bplats用ダミーリコー品種コード", required = false, position = 3, allowableValues = "range[0,255]")
	private String bplatsDummyRicohItemCode;

	/**
	 * 正規リコー品種コード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "正規リコー品種コード", required = false, position = 4, allowableValues = "range[0,255]")
	private String regularRicohItemCode;

}
