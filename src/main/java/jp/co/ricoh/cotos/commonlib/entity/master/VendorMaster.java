package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ベンダーマスタ
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "vendor_master")
public class VendorMaster extends EntityBaseMaster {

	/**
	 * ベンダーマスタID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vendor_master_seq")
	@SequenceGenerator(name = "vendor_master_seq", sequenceName = "vendor_master_seq", allocationSize = 1)
	@ApiModelProperty(value = "ベンダーマスタID", required = true, position = 1, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 仕入先コード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "仕入先コード", required = true, position = 2, allowableValues = "range[0,255]")
	private String vendorCode;

	/**
	 * ベンダー名称
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "ベンダー名称", required = false, position = 3, allowableValues = "range[0,255]")
	private String vendorName;

	/**
	 * メールアドレス
	 */
	@Size(max = 1000)
	@ApiModelProperty(value = "メールアドレス", required = false, position = 4, allowableValues = "range[0,1000]")
	private String mailAddress;

	/**
	 * ベンダー商品マスタ
	 */
	@OneToMany(mappedBy = "vendorMaster")
	@JsonIgnore
	@ApiModelProperty(value = "ベンダー商品マスタ", required = true, position = 5)
	private List<VendorProductMaster> vendorProductMasterList;

	/**
	 * 添付ファイルパスワード不要
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "添付ファイルパスワード不要", required = false, position = 6, allowableValues = "range[0,9]")
	private int attachedFilePasswordUnrequired;
}
