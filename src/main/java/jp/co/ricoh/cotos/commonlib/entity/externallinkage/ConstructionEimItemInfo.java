package jp.co.ricoh.cotos.commonlib.entity.externallinkage;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 工事調整EIM申込品種情報を表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "construction_eim_item_info")
public class ConstructionEimItemInfo extends EntityBase {

	/**
	 * 工事調整EIM申込品種情報ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "construction_eim_item_info_seq")
	@SequenceGenerator(name = "construction_eim_item_info_seq", sequenceName = "construction_eim_item_info_seq", allocationSize = 1)
	@ApiModelProperty(value = "工事調整EIM申込品種情報ID(作成時不要)", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 工事調整EIM申込情報
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "construction_eim_apply_info_id", referencedColumnName = "id")
	@JsonIgnore
	@ApiModelProperty(value = "工事調整EIM申込情報", required = true, position = 2)
	private ConstructionEimApplyInfo constructionEimApplyInfo;

	/**
	 * リコー品種コード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "リコー品種コード", required = false, position = 3, allowableValues = "range[0,255]")
	private String ricohItemCode;

	/**
	 * 数量
	 */
	@Column(nullable = false)
	@Max(99999)
	@Min(-99999)
	@ApiModelProperty(value = "数量", required = true, position = 4, allowableValues = "range[-99999,99999]")
	private int quantity;
}
