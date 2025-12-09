package jp.co.ricoh.cotos.commonlib.entity.externallinkage;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.entity.master.SpecificControlMaster.ItemValueDiv;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商材固有項目情報を表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "specific_info")
public class SpecificInfo extends EntityBase {

	/**
	 * 商材固有項目情報ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "specific_info_seq")
	@SequenceGenerator(name = "specific_info_seq", sequenceName = "specific_info_seq", allocationSize = 1)
	@Schema(description = "商材固有項目情報ID(作成時不要)", allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 契約ID
	 */
	@Column(nullable = false)
	@Min(0)
	@Schema(description = "契約ID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private Long contractId;

	/**
	 * RJ管理番号
	 */
	@Size(max = 255)
	@Schema(description = "RJ管理番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String rjManageNumber;

	/**
	 * 商材固有項目マスタID
	 */
	@Column(nullable = false)
	@Min(0)
	@Schema(description = "商材固有項目マスタID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private Long specificControlMasterId;

	/**
	 * 商材固有項目区分
	 */
	@Size(max = 255)
	@Schema(description = "商材固有項目区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String specificControlDiv;

	/**
	 * 項目値区分
	 */
	@Column(nullable = false)
	@Schema(description = "項目値区分", requiredMode = Schema.RequiredMode.REQUIRED)
	private ItemValueDiv itemValueDiv;

	/**
	 * 値
	 */
	@Size(max = 4000)
	@Schema(description = "値", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,4000]")
	private String itemValue;

	/**
	 * 拡張項目
	 */
	@Schema(description = "拡張項目", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Lob
	private String extendsParameter;
}
