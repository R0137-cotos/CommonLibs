package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 品種グループマスタを表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "item_group_master")
public class ItemGroupMaster extends EntityBase {

	/**
	 * 品種グループマスタID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_group_master_seq")
	@SequenceGenerator(name = "item_group_master_seq", sequenceName = "item_group_master_seq", allocationSize = 1)
	@ApiModelProperty(value = "品種グループマスタID(作成時不要)", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 品種グループ名
	 */
	@Column(nullable = false)
	@Size(max = 255)
	@ApiModelProperty(value = "品種グループ名", required = true, position = 2, allowableValues = "range[0,255]")
	private String itemGroupName;

	/**
	 * 品種グループ区分
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "品種グループ区分", required = false, position = 3, allowableValues = "range[0,255]")
	private String itemGroupDiv;

	/**
	 * カテゴリ
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "カテゴリ", required = false, position = 4, allowableValues = "range[0,255]")
	private String category;

	/**
	 * 品種マスタリスト
	 */
	@OneToMany(mappedBy = "itemGroupMaster")
	@JsonIgnore
	@ApiModelProperty(value = "品種マスタ", required = true, position = 5)
	private List<ItemMaster> itemMasterList;
}
