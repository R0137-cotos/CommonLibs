package jp.co.ricoh.cotos.commonlib.entity.master;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.DetailStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 帳票ページ管理マスタ
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "report_page_master")
public class ReportPageMaster extends EntityBaseMaster {

	/**
	 * ページID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "report_page_master_seq")
	@SequenceGenerator(name = "report_page_master_seq", sequenceName = "report_page_master_seq", allocationSize = 1)
	@Schema(description = "商品マスタID", required = true, allowableValues = "range[0,9999999999999999999999999999]")
	private long id;

	/**
	 * 帳票テンプレート管理マスタ
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "template_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "帳票テンプレート管理マスタ", required = true)
	private ReportTemplateMaster reportTemplateMaster;

	/**
	 * ページ名
	 */
	@Size(max = 255)
	@Schema(description = "ページ名", required = false, allowableValues = "range[0,255]")
	private String pageName;

	/**
	 * 常時出力フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "常時出力フラグ", required = false, allowableValues = "range[0,9]")
	private Integer alwaysOutputFlg;

	/**
	 * 状態
	 */
	@Schema(description = "状態", required = false, allowableValues = "NOUPDATE(\"1\"), ADD(\"2\"), DELETE(\"3\"), UPDATE(\"4\")")
	private DetailStatus status;

	/**
	 * リコー品種コード
	 */
	@Size(max = 255)
	@Schema(description = "リコー品種コード", required = false, allowableValues = "range[0,255]")
	private String ricohItemCode;

	/**
	 * ページ数
	 */
	@Max(99999)
	@Min(0)
	@Schema(description = "ページ数", required = false, allowableValues = "range[0,99999]")
	private Integer pageNumber;

	/**
	 * 拡張項目
	 * {@link jp.co.ricoh.cotos.commonlib.dto.json.master.ReportPageMasterExtendsParameterDto}
	 */
	@Schema(description = "拡張項目", required = false)
	@Lob
	private String extendsParameter;

	/**
	 * ジョブユニットID
	 */
	@Max(99999)
	@Min(0)
	@Schema(description = "ジョブユニットID", required = false, allowableValues = "range[0,99999]")
	private Integer jobUnitId;
}
