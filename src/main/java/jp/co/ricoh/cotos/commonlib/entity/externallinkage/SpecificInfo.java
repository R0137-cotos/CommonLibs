package jp.co.ricoh.cotos.commonlib.entity.externallinkage;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.entity.master.SpecificControlMaster.ItemValueDiv;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商材固有項目情報を表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "specific_info")
public class SpecificInfo extends EntityBase {

	/**
	 * 商材固有項目情報ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "specific_info_seq")
	@SequenceGenerator(name = "specific_info_seq", sequenceName = "specific_info_seq", allocationSize = 1)
	@ApiModelProperty(value = "商材固有項目情報ID(作成時不要)", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 契約ID
	 */
	@Column(nullable = false)
	@Min(0)
	@ApiModelProperty(value = "契約ID", required = true, position = 2, allowableValues = "range[0,9223372036854775807]")
	private Long contractId;

	/**
	 * RJ管理番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "RJ管理番号", required = false, position = 3, allowableValues = "range[0,255]")
	private String rjManageNumber;

	/**
	 * 商材固有項目マスタID
	 */
	@Column(nullable = false)
	@Min(0)
	@ApiModelProperty(value = "商材固有項目マスタID", required = true, position = 4, allowableValues = "range[0,9223372036854775807]")
	private Long specificControlMasterId;

	/**
	 * 商材固有項目区分
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "商材固有項目区分", required = false, position = 5, allowableValues = "range[0,255]")
	private String specificControlDiv;

	/**
	 * 項目値区分
	 */
	@Column(nullable = false)
	@ApiModelProperty(value = "項目値区分", required = true, position = 6)
	private ItemValueDiv itemValueDiv;

	/**
	 * 値
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "値", required = false, position = 7, allowableValues = "range[0,255]")
	private String itemValue;

	/**
	 * 拡張項目
	 */
	@ApiModelProperty(value = "拡張項目", required = false, position = 8)
	@Lob
	private String extendsParameter;
}
