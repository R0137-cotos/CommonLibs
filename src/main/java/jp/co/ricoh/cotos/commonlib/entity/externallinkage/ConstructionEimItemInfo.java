package jp.co.ricoh.cotos.commonlib.entity.externallinkage;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(description = "工事調整EIM申込品種情報ID(作成時不要)", required = true, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 工事調整EIM申込情報
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "construction_eim_apply_info_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "工事調整EIM申込情報", required = true)
	private ConstructionEimApplyInfo constructionEimApplyInfo;

	/**
	 * リコー品種コード
	 */
	@Size(max = 255)
	@Schema(description = "リコー品種コード", required = false, allowableValues = "range[0,255]")
	private String ricohItemCode;

	/**
	 * 数量
	 */
	@Column(nullable = false)
	@Max(99999)
	@Min(-99999)
	@Schema(description = "数量", required = true, allowableValues = "range[-99999,99999]")
	private int quantity;
}
