package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * メニュー管理マスタ
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "menu_management_master")
public class MenuManagementMaster extends EntityBaseMaster {

	/**
	 * メニュー管理マスタID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menu_management_master_seq")
	@SequenceGenerator(name = "menu_management_master_seq", sequenceName = "menu_management_master_seq", allocationSize = 1)
	@ApiModelProperty(value = "メニュー管理マスタID", required = true, position = 1, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * メニュー名
	 */
	@Column(nullable = false)
	@Size(max = 255)
	@ApiModelProperty(value = "メニュー名", required = true, position = 2, allowableValues = "range[0,255]")
	private String menuName;

	/**
	 * 権限
	 */
	@Column(nullable = false)
	@ApiModelProperty(value = "権限", required = true, position = 3)
	@Lob
	private String auth;

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

	/**
	 * メニュー明細管理マスタリスト
	 */
	@OneToMany(mappedBy = "menuManagementMaster")
	@ApiModelProperty(value = "メニュー明細管理マスタ", required = false, position = 5)
	private List<MenuDetailManagementMaster> menuDetailManagementMasterList;
}
