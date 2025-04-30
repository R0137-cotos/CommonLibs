package jp.co.ricoh.cotos.commonlib.entity.estimation;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * V-UP案件ワークテーブルを表すEntity
 */
@Entity
@Data
@Table(name = "vup_case_work")
public class VupCaseWork {

	/**
	 * ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vup_case_work_seq")
	@SequenceGenerator(name = "vup_case_work_seq", sequenceName = "vup_case_work_seq", allocationSize = 1)
	@ApiModelProperty(value = "V-UP案件ワークID", required = true, position = 1, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 案件名
	 */
	@ApiModelProperty(value = "案件名", required = false, position = 2, allowableValues = "range[0,40]")
	private String anknNm;

	/**
	 * 案件ID
	 */
	@ApiModelProperty(value = "案件ID", required = false, position = 3, allowableValues = "range[0,15]")
	private String anknId;

	/**
	 * 見積ID
	 */
	@ApiModelProperty(value = "見積ID", required = false, position = 4, allowableValues = "range[0,11]")
	private String estId;

	/**
	 * 汎用見積番号
	 */
	@ApiModelProperty(value = "汎用見積番号", required = false, position = 5, allowableValues = "range[0,25]")
	private String rtsEstId;

}
