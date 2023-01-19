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
 * 商材固有品種グループマスタを表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "specific_item_group_master")
public class SpecificItemGroupMaster extends EntityBase {

	/**
	 * 商材固有品種グループマスタID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "specific_item_group_master_seq")
	@SequenceGenerator(name = "specific_item_group_master_seq", sequenceName = "specific_item_group_master_seq", allocationSize = 1)
	@ApiModelProperty(value = "商材固有品種グループマスタID(作成時不要)", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 商材固有品種グループ名
	 */
	@Column(nullable = false)
	@Size(max = 255)
	@ApiModelProperty(value = "商材固有品種グループ名", required = true, position = 2, allowableValues = "range[0,255]")
	private String specificItemGroupName;

	/**
	 * 商材固有品種グループ区分
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "商材固有品種グループ区分", required = false, position = 3, allowableValues = "range[0,255]")
	private String specificItemGroupDiv;

	/**
	 * 商材固有項目マスタリスト
	 */
	@OneToMany(mappedBy = "specificItemGroupMaster")
	@JsonIgnore
	@ApiModelProperty(value = "商材固有項目マスタ", required = true, position = 4)
	private List<SpecificControlMaster> specificControlMaster;
}
