package jp.co.ricoh.cotos.commonlib.entity.master;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * メニュー明細管理マスタ
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "menu_details_management_master")
public class MenuDetailsManagementMaster extends EntityBaseMaster {

	/**
	 * メニュー明細管理ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menu_details_management_master_seq")
	@SequenceGenerator(name = "menu_details_management_master_seq", sequenceName = "menu_details_management_master_seq", allocationSize = 1)
	@ApiModelProperty(value = "メニュー明細管理ID(作成時不要)", required = true, position = 1, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * メニュー管理ID
	 */
	@NotNull
	@Column(nullable = false)
	@Min(0)
	@ApiModelProperty(value = "メニュー管理ID", required = true, position = 2, allowableValues = "range[0,9223372036854775807]")
	private Long menuManagementId;

	/**
	 * メニュー明細名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "メニュー明細名", required = false, position = 3, allowableValues = "range[0,255]")
	private String menuDetailsName;

	/**
	 * 順序
	 */
	@NotNull
	@Column(nullable = false)
	@ApiModelProperty(value = "順序", required = true, position = 4, allowableValues = "range[0,999]")
	private int orderNumber;

	/**
	 * URL
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "URL", required = false, position = 5, allowableValues = "range[0,255]")
	private String url;

}
