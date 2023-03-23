package jp.co.ricoh.cotos.commonlib.entity.license;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * クラウドエッジアカウント情報を表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "cloud_edge_account_info")
public class CloudEdgeAccountInfo extends EntityBase {

	/**
	 * クラウドエッジアカウント情報ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cloud_edge_account_info_seq")
	@SequenceGenerator(name = "cloud_edge_account_info_seq", sequenceName = "cloud_edge_account_info_seq", allocationSize = 1)
	@ApiModelProperty(value = "クラウドエッジアカウント情報ID(作成時不要)", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 契約ID
	 */
	@Min(0)
	@ApiModelProperty(value = "契約ID", required = false, position = 2, allowableValues = "range[0,9223372036854775807]")
	private Long contractId;

	/**
	 * RJ管理番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "RJ管理番号", required = false, position = 3, allowableValues = "range[0,255]")
	private String rjManageNumber;

	/**
	 * アカウント
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "アカウント", required = false, position = 4, allowableValues = "range[0,255]")
	private String account;

	/**
	 * 反映希望日
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "反映希望日", required = false, position = 5)
	private Date reflectRequestDate;

	/**
	 * 売上日
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "売上日", required = false, position = 6)
	private Date salesDate;

	/**
	 * 企業名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "企業名", required = false, position = 7, allowableValues = "range[0,255]")
	private String companyName;

	/**
	 * 都道府県
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "都道府県", required = false, position = 8, allowableValues = "range[0,255]")
	private String prefectures;

	/**
	 * 市区町村
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "市区町村", required = false, position = 9, allowableValues = "range[0,255]")
	private String municipality;

	/**
	 * 担当者メールアドレス
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "担当者メールアドレス", required = false, position = 10, allowableValues = "range[0,255]")
	private String picMailAddress;

}
