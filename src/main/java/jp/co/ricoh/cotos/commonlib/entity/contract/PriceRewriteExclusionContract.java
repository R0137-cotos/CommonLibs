package jp.co.ricoh.cotos.commonlib.entity.contract;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.entity.common.FileImportManagement;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 価格書換除外契約を表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@EntityListeners(PriceRewriteExclusionContract.class)
@Data
@Table(name = "price_rewrite_exclusion_contract")
public class PriceRewriteExclusionContract extends EntityBase {


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "price_rewrite_exclusion_contract_seq")
	@SequenceGenerator(name = "price_rewrite_exclusion_contract_seq", sequenceName = "price_rewrite_exclusion_contract_seq", allocationSize = 1)
	@ApiModelProperty(value = "価格書換除外契約", required = true, position = 1, readOnly = true)
	private long id;

	/**
	 * ファイル取込管理ID
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "file_import_management_id", referencedColumnName = "id")
	@JsonIgnore
	@ApiModelProperty(value = "ファイル取込管理", required = false, position = 2)
	private FileImportManagement fileImportManagement;

	/**
	 * RJ管理番号
	 */
	@Column
	@Size(max = 255)
	@ApiModelProperty(value = "RJ管理番号", required = false, position = 3, allowableValues = "range[0,255]")
	private String rjManageNumber;

	/**
	 * リコー品種コード
	 */
	@Column
	@Size(max = 255)
	@ApiModelProperty(value = "リコー品種コード", required = false, position = 4, allowableValues = "range[0,255]")
	private String ricohItemCode;

}
