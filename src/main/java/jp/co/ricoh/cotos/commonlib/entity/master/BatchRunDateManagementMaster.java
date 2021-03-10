package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * バッチ実行日管理マスタを表すEntity
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "batch_run_date_management_master")
public class BatchRunDateManagementMaster extends EntityBaseMaster {

	/**
	 * バッチ実行日管理マスタID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "batch_run_date_management_master_seq")
	@SequenceGenerator(name = "batch_run_date_management_master_seq", sequenceName = "batch_run_date_management_master_seq", allocationSize = 1)
	@ApiModelProperty(value = "バッチ実行日管理マスタID", required = true, position = 1, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 対象バッチ処理ID
	 */
	@ApiModelProperty(value = "対象バッチ処理ID", required = false, position = 2, allowableValues = "range[0,255]")
	private String targetBatchId;

	/**
	 * 商品種類区分
	 */
	@ApiModelProperty(value = "商品種類区分", required = false, position = 3, allowableValues = "range[0,255]")
	private String productClassDiv;

	/**
	 * 商品マスタID
	 */
	@ApiModelProperty(value = "商品マスタID", required = false, position = 4, allowableValues = "range[0,9999999999999999999]")
	private Long productMasterId;

	/**
	 * バッチ実行日
	 */
	@ApiModelProperty(value = "バッチ実行日", required = false, position = 5)
	@Temporal(TemporalType.DATE)
	private Date batchRunDate;
}
