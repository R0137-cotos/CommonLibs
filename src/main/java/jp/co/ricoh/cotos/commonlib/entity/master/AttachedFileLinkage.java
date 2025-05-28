package jp.co.ricoh.cotos.commonlib.entity.master;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ファイル連携先
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "attached_file_linkage")
public class AttachedFileLinkage extends EntityBaseMaster {

	/**
	 * ファイル連携先ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "attached_file_linkage_seq")
	@SequenceGenerator(name = "attached_file_linkage_seq", sequenceName = "attached_file_linkage_seq", allocationSize = 1)
	@ApiModelProperty(value = "ファイル連携先ID", required = true, position = 1, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 商品マスタID
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "product_master_id", referencedColumnName = "id")
	@JsonIgnore
	@ApiModelProperty(value = "商品マスタID", required = true, position = 2)
	private ProductMaster productMaster;

	/**
	 * ファイル連携先
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "ファイル連携先", required = true, position = 3, allowableValues = "range[0,255]")
	private String attachedFileLinkageName;

	/**
	 * 連携フラグ初期値
	 */
	@Max(9)
	@ApiModelProperty(value = "連携フラグ初期値", required = true, position = 4, allowableValues = "range[0,9]")
	private Integer linkageFlgDefault;

	/**
	 * 汎用マスタ明細コード値
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "汎用マスタ明細コード値", required = false, position = 5, allowableValues = "range[0,255]")
	private String codeValue;
}
