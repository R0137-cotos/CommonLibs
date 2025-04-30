package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

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
	@ApiModelProperty(value = "メニュー管理マスタID(作成時不要)", required = true, position = 1, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * メニュー名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "メニュー名", required = false, position = 2, allowableValues = "range[0,255]")
	private String menuName;

	/**
	 * 権限
	 */
	@ApiModelProperty(value = "権限", required = false, position = 3)
	@Lob
	private String auth;

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
	@Size(max = 255)
	@ApiModelProperty(value = "URL", required = false, position = 5, allowableValues = "range[0,255]")
	private String url;

	/**
	 * メニュー明細管理マスタリスト
	 */
	@OneToMany(mappedBy = "menuManagementMaster")
	@ApiModelProperty(value = "メニュー明細管理マスタ", required = false, position = 6)
	private List<MenuDetailsManagementMaster> menuDetailsManagementMasterList;
}