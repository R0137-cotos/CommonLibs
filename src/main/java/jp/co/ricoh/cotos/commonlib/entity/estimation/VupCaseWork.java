package jp.co.ricoh.cotos.commonlib.entity.estimation;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(description = "V-UP案件ワークID", required = true, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 案件名
	 */
	@Schema(description = "案件名", required = false, allowableValues = "range[0,40]")
	private String anknNm;

	/**
	 * 案件ID
	 */
	@Schema(description = "案件ID", required = false, allowableValues = "range[0,15]")
	private String anknId;

	/**
	 * 見積ID
	 */
	@Schema(description = "見積ID", required = false, allowableValues = "range[0,11]")
	private String estId;

	/**
	 * 汎用見積番号
	 */
	@Schema(description = "汎用見積番号", required = false, allowableValues = "range[0,25]")
	private String rtsEstId;

}
