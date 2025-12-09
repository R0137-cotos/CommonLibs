package jp.co.ricoh.cotos.commonlib.entity.master;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(description = "メニュー明細管理マスタID(作成時不要)", allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * メニュー管理マスタ
	 */
	@NotNull
	@ManyToOne
	@JoinColumn(name = "menu_management_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "メニュー管理マスタ", requiredMode = Schema.RequiredMode.REQUIRED)
	private MenuManagementMaster menuManagementMaster;

	/**
	 * メニュー明細名
	 */
	@Size(max = 255)
	@Schema(description = "メニュー明細名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String menuDetailsName;

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
	@NotNull
	@Column(nullable = false)
	@Size(max = 255)
	@Schema(description = "URL", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String url;

}