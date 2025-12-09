package jp.co.ricoh.cotos.commonlib.entity.license;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(description = "クラウドエッジアカウント情報ID(作成時不要)", required = true, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 契約ID
	 */
	@Min(0)
	@Schema(description = "契約ID", required = false, allowableValues = "range[0,9223372036854775807]")
	private Long contractId;

	/**
	 * RJ管理番号
	 */
	@Size(max = 255)
	@Schema(description = "RJ管理番号", required = false, allowableValues = "range[0,255]")
	private String rjManageNumber;

	/**
	 * アカウント
	 */
	@Size(max = 255)
	@Schema(description = "アカウント", required = false, allowableValues = "range[0,255]")
	private String account;

	/**
	 * 反映希望日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "反映希望日", required = false)
	private Date reflectRequestDate;

	/**
	 * 売上日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "売上日", required = false)
	private Date salesDate;

	/**
	 * 企業名
	 */
	@Size(max = 255)
	@Schema(description = "企業名", required = false, allowableValues = "range[0,255]")
	private String companyName;

	/**
	 * 都道府県
	 */
	@Size(max = 255)
	@Schema(description = "都道府県", required = false, allowableValues = "range[0,255]")
	private String prefectures;

	/**
	 * 市区町村
	 */
	@Size(max = 255)
	@Schema(description = "市区町村", required = false, allowableValues = "range[0,255]")
	private String municipality;

	/**
	 * 担当者メールアドレス
	 */
	@Size(max = 255)
	@Schema(description = "担当者メールアドレス", required = false, allowableValues = "range[0,255]")
	private String picMailAddress;

}
