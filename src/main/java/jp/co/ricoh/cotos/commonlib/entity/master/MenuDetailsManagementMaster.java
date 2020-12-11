package jp.co.ricoh.cotos.commonlib.entity.master;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	 * メニュー明細管理マスタID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menu_details_management_master_seq")
	@SequenceGenerator(name = "menu_details_management_master_seq", sequenceName = "menu_details_management_master_seq", allocationSize = 1)
	@ApiModelProperty(value = "メニュー明細管理マスタID(作成時不要)", required = true, position = 1, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * メニュー管理マスタ
	 */
	@NotNull
	@ManyToOne
	@JoinColumn(name = "menu_management_master_id", referencedColumnName = "id")
	@JsonIgnore
	@ApiModelProperty(value = "メニュー管理マスタ", required = true, position = 2)
	private MenuManagementMaster menuManagementMaster;

	/**
	 * メニュー明細名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "メニュー明細名", required = false, position = 3, allowableValues = "range[0,255]")
	private String menuDetailsName;

	/**
	 * 順序
	 */
	@Column(nullable = false)
	@Max(999)
	@Min(0)
	@ApiModelProperty(value = "順序", required = true, position = 4, allowableValues = "range[0,999]")
	private int orderNumber;

	/**
	 * URL
	 */
	@NotNull
	@Column(nullable = false)
	@Size(max = 255)
	@ApiModelProperty(value = "URL", required = true, position = 5, allowableValues = "range[0,255]")
	private String url;

}
