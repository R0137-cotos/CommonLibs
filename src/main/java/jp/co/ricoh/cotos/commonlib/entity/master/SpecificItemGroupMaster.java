package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(description = "商材固有品種グループマスタID(作成時不要)", required = true, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 商材固有品種グループ名
	 */
	@Column(nullable = false)
	@Size(max = 255)
	@Schema(description = "商材固有品種グループ名", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String specificItemGroupName;

	/**
	 * 商材固有品種グループ区分
	 */
	@Size(max = 255)
	@Schema(description = "商材固有品種グループ区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String specificItemGroupDiv;

	/**
	 * 商材固有項目マスタリスト
	 */
	@OneToMany(mappedBy = "specificItemGroupMaster")
	@JsonIgnore
	@Schema(description = "商材固有項目マスタ", requiredMode = Schema.RequiredMode.REQUIRED)
	private List<SpecificControlMaster> specificControlMaster;
}
