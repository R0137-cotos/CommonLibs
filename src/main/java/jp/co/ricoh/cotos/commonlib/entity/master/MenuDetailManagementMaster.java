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
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "menu_detail_management_master")
public class MenuDetailManagementMaster extends EntityBaseMaster {

	/**
	 * メニュー明細管理マスタID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menu_detail_management_master_seq")
	@SequenceGenerator(name = "menu_detail_management_master_seq", sequenceName = "menu_detail_management_master_seq", allocationSize = 1)
	@ApiModelProperty(value = "メニュー明細管理マスタID", required = true, position = 1, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * メニュー管理マスタ
	 */
	@ManyToOne
	@JoinColumn(name = "menu_management_master_id", referencedColumnName = "id")
	@JsonIgnore
	@ApiModelProperty(value = "メニュー管理マスタ", required = true, position = 2)
	private MenuManagementMaster menuManagementMaster;

	/**
	 * メニュー明細名
	 */
	@Column(nullable = false)
	@Size(max = 255)
	@ApiModelProperty(value = "メニュー明細名", required = true, position = 3, allowableValues = "range[0,255]")
	private String menuDetailName;

	/**
	 * 表示順
	 */
	@Column(nullable = false)
	@ApiModelProperty(value = "表示順", required = true, position = 4, allowableValues = "range[0,999]")
	private Integer displayOrder;

	/**
	 * URL
	 */
	@Column(nullable = false)
	@Size(max = 255)
	@ApiModelProperty(value = "URL", required = true, position = 4, allowableValues = "range[0,255]")
	private String url;
}
