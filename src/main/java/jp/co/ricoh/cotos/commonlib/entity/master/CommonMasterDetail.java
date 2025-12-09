package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 汎用マスタ明細
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "common_master_detail")
public class CommonMasterDetail extends EntityBaseMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "common_master_detail_seq")
	@SequenceGenerator(name = "common_master_detail_seq", sequenceName = "common_master_detail_seq", allocationSize = 1)
	@Schema(description = "汎用マスタ明細ID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 汎用マスタ
	 */
	@ManyToOne
	@JoinColumn(name = "common_master_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "汎用マスタ", requiredMode = Schema.RequiredMode.REQUIRED)
	private CommonMaster commonMaster;

	/**
	 * コード値
	 */
	@Column(nullable = false)
	@Schema(description = "コード値", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String codeValue;

	/**
	 * コード表示値
	 */
	@Schema(description = "コード表示値", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String displayValue;

	/**
	 * コード内容値
	 */
	@Schema(description = "コード内容値", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String description;

	/**
	 * 設定値1
	 */
	@Column(name = "DATA_AREA_1")
	@Schema(description = "設定値1", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String dataArea1;

	/**
	 * 設定値2
	 */
	@Column(name = "DATA_AREA_2")
	@Schema(description = "設定値2", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String dataArea2;

	/**
	 * 設定値3
	 */
	@Column(name = "DATA_AREA_3")
	@Schema(description = "設定値3", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String dataArea3;

	/**
	 * 設定値4
	 */
	@Column(name = "DATA_AREA_4")
	@Schema(description = "設定値4", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String dataArea4;

	/**
	 * 設定値5
	 */
	@Column(name = "DATA_AREA_5")
	@Schema(description = "設定値5", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String dataArea5;

	/**
	 * 有効期間From
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "有効期間From", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date availablePeriodFrom;

	/**
	 * 有効期間To
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "有効期間To", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date availablePeriodTo;

	/**
	 * 表示順
	 */
	@Column(nullable = false)
	@Schema(description = "表示順", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,999]")
	private Integer displayOrder;

	/**
	 * 削除フラグ
	 */
	@Column(nullable = false)
	@Schema(description = "削除フラグ", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,1]")
	private String deleteFlg;
}
