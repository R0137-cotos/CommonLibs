package jp.co.ricoh.cotos.commonlib.entity.master;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 品種コード差し替えマスタを表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "itemcode_change_master")
public class ItemcodeChangeMaster extends EntityBaseMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "itemcode_change_master_seq")
	@SequenceGenerator(name = "itemcode_change_master_seq", sequenceName = "itemcode_change_master_seq", allocationSize = 1)
	@ApiModelProperty(value = "品種コード差し替えマスタID", required = true, position = 1, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 旧品種マスタID
	 */
	@Column(name = "old_master_id", nullable = false)
	@ApiModelProperty(value = "旧品種マスタID", required = true)
	private Long oldMasterId;

	/**
	 * 新品種マスタID
	 */
	@Column(name = "new_master_id", nullable = false)
	@ApiModelProperty(value = "新品種マスタID", required = true)
	private Long newMasterId;
}
