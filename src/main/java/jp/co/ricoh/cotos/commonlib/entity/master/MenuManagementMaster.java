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

import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(description = "メニュー管理マスタID(作成時不要)", allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * メニュー名
	 */
	@Size(max = 255)
	@Schema(description = "メニュー名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String menuName;

	/**
	 * 権限
	 */
	@Schema(description = "権限", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Lob
	private String auth;

	/**
	 * 順序
	 */
	@Column(nullable = false)
	@Max(999)
	@Min(0)
	@Schema(description = "順序", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,999]")
	private int orderNumber;

	/**
	 * URL
	 */
	@Size(max = 255)
	@Schema(description = "URL", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String url;

	/**
	 * メニュー明細管理マスタリスト
	 */
	@OneToMany(mappedBy = "menuManagementMaster")
	@Schema(description = "メニュー明細管理マスタ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private List<MenuDetailsManagementMaster> menuDetailsManagementMasterList;
}