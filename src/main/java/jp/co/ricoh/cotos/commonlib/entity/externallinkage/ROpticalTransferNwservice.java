package jp.co.ricoh.cotos.commonlib.entity.externallinkage;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * リコーひかり異動情報NWサービスを表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "r_optical_transfer_nwservice")
public class ROpticalTransferNwservice extends EntityBase {

	/**
	 * リコーひかり異動情報NWサービスID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "r_optical_transfer_nwservice_seq")
	@SequenceGenerator(name = "r_optical_transfer_nwservice_seq", sequenceName = "r_optical_transfer_nwservice_seq", allocationSize = 1)
	@ApiModelProperty(value = "リコーひかり異動情報NWサービスID(作成時不要)", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * リコーひかり異動情報
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "r_optical_transfer_id", referencedColumnName = "id")
	@ApiModelProperty(value = "リコーひかり異動情報", required = true, position = 2)
	@JsonIgnore
	private ROpticalTransfer rOpticalTransfer;

	/**
	 * サービス名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "サービス名", required = false, position = 3, allowableValues = "range[0,255]")
	private String serviceName;

}
