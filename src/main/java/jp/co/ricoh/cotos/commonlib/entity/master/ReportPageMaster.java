package jp.co.ricoh.cotos.commonlib.entity.master;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(value = "商品マスタID", required = true, position = 1, allowableValues = "range[0,9999999999999999999999999999]")
	private long id;

	/**
	 * 帳票テンプレート管理マスタ
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "template_id", referencedColumnName = "id")
	@JsonIgnore
	@ApiModelProperty(value = "帳票テンプレート管理マスタ", required = true, position = 2)
	private ReportTemplateMaster reportTemplateMaster;

	/**
	 * ページ名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "ページ名", required = false, position = 3, allowableValues = "range[0,255]")
	private String pageName;

	/**
	 * 常時出力フラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "常時出力フラグ", required = false, position = 4, allowableValues = "range[0,9]")
	private Integer alwaysOutputFlg;

	/**
	 * 状態
	 */
	@ApiModelProperty(value = "状態", required = false, allowableValues = "NOUPDATE(\"1\"), ADD(\"2\"), DELETE(\"3\"), UPDATE(\"4\")", position = 5)
	private DetailStatus status;

	/**
	 * リコー品種コード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "リコー品種コード", required = false, position = 6, allowableValues = "range[0,255]")
	private String ricohItemCode;

	/**
	 * ページ数
	 */
	@Max(99999)
	@Min(0)
	@ApiModelProperty(value = "ページ数", required = false, position = 7, allowableValues = "range[0,99999]")
	private Integer pageNumber;

	/**
	 * 拡張項目
	 * {@link jp.co.ricoh.cotos.commonlib.dto.json.master.ReportPageMasterExtendsParameterDto}
	 */
	@ApiModelProperty(value = "拡張項目", required = false, position = 8)
	@Lob
	private String extendsParameter;

	/**
	 * ジョブユニットID
	 */
	@Max(99999)
	@Min(0)
	@ApiModelProperty(value = "ジョブユニットID", required = false, position = 9, allowableValues = "range[0,99999]")
	private Integer jobUnitId;
}
