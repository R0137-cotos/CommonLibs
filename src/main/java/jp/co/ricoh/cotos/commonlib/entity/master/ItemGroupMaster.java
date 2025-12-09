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
	@Schema(description = "品種グループマスタID(作成時不要)", allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 品種グループ名
	 */
	@Column(nullable = false)
	@Size(max = 255)
	@Schema(description = "品種グループ名", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String itemGroupName;

	/**
	 * 品種グループ区分
	 */
	@Size(max = 255)
	@Schema(description = "品種グループ区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String itemGroupDiv;

	/**
	 * カテゴリ
	 */
	@Size(max = 255)
	@Schema(description = "カテゴリ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String category;

	/**
	 * 品種マスタリスト
	 */
	@OneToMany(mappedBy = "itemGroupMaster")
	@JsonIgnore
	@Schema(description = "品種マスタ", requiredMode = Schema.RequiredMode.REQUIRED)
	private List<ItemMaster> itemMasterList;
}
