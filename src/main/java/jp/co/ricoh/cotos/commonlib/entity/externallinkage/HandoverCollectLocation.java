package jp.co.ricoh.cotos.commonlib.entity.externallinkage;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.entity.contract.CollectLocation.LinkageState;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 引継ぎ用回収先を表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "handover_collect_location")
public class HandoverCollectLocation extends EntityBase {

	/**
	 * 回収先ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "handover_collect_location_seq")
	@SequenceGenerator(name = "handover_collect_location_seq", sequenceName = "handover_collect_location_seq", allocationSize = 1)
	@Schema(description = "回収先ID(作成時不要)", required = true, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 契約ID
	 */
	@Min(0)
	@Schema(description = "契約ID", required = false, allowableValues = "range[0,9223372036854775807]")
	private Long contractId;

	/**
	 * 希望回収日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "希望回収日", required = false)
	private Date collectPreferredDate;

	/**
	 * 担当者氏名
	 */
	@Size(max = 255)
	@Schema(description = "担当者氏名", required = false, allowableValues = "range[0,255]")
	private String picName;

	/**
	 * 郵便番号
	 */
	@Size(max = 255)
	@Schema(description = "郵便番号", required = false, allowableValues = "range[0,255]")
	private String postNumber;

	/**
	 * 住所
	 */
	@Size(max = 255)
	@Schema(description = "住所", required = false, allowableValues = "range[0,255]")
	private String address;

	/**
	 * 企業名
	 */
	@Size(max = 255)
	@Schema(description = "企業名", required = false, allowableValues = "range[0,255]")
	private String companyName;

	/**
	 * 事業所名
	 */
	@Size(max = 255)
	@Schema(description = "事業所名", required = false, allowableValues = "range[0,255]")
	private String officeName;

	/**
	 * 担当者部署
	 */
	@Size(max = 255)
	@Schema(description = "担当者部署", required = false, allowableValues = "range[0,255]")
	private String picDeptName;

	/**
	 * 電話番号
	 */
	@Size(max = 255)
	@Schema(description = "電話番号", required = false, allowableValues = "range[0,255]")
	private String phoneNumber;

	/**
	 * 回収不要フラグ
	 * 【商品種類区分がBPSMの場合】保存時に商材固有項目情報更新APIで値が自動設定される
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "回収不要フラグ", required = false, allowableValues = "range[0,9]")
	private Integer collectUnnecessaryFlg;

	/**
	 * アップグレードRJ管理番号
	 */
	@Size(max = 255)
	@Schema(description = "アップグレードRJ管理番号", required = false, allowableValues = "range[0,255]")
	private String upgradeRjManageNumber;

	/**
	 * 連携状態
	 */
	@Schema(description = "連携状態", required = false, allowableValues = "AP削除連携済(\"1\"), 解約連携済(\"2\")")
	private LinkageState linkageState;

	/**
	 * 引継ぎ元契約ID
	 */
	@Min(0)
	@Schema(description = "引継ぎ元契約ID", required = false, allowableValues = "range[0,9223372036854775807]")
	private Long handoverContractId;

	/**
	 * 引継ぎ反映フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "引継ぎ反映フラグ", required = false, allowableValues = "range[0,9]")
	private Integer handoverMappedFlg;
}
