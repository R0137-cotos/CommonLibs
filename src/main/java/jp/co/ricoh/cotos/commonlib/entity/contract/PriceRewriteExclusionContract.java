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

import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(description = "価格書換除外契約", requiredMode = Schema.RequiredMode.REQUIRED, readOnly = true)
	private long id;

	/**
	 * ファイル取込管理ID
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "file_import_management_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "ファイル取込管理", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private FileImportManagement fileImportManagement;

	/**
	 * RJ管理番号
	 */
	@Column
	@Size(max = 255)
	@Schema(description = "RJ管理番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String rjManageNumber;

	/**
	 * リコー品種コード
	 */
	@Column
	@Size(max = 255)
	@Schema(description = "リコー品種コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ricohItemCode;

}
