package jp.co.ricoh.cotos.commonlib.entity.master;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
 * 添付ファイル商品種類区分毎チェックマスタ
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "attached_file_product_class_check_master")
public class AttachedFileProductClassCheckMaster extends EntityBaseMaster {

	/**
	 * 添付ファイル商品種類毎チェックマスタID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "attached_file_product_class_check_master_seq")
	@SequenceGenerator(name = "attached_file_product_class_check_master_seq", sequenceName = "attached_file_product_class_check_master_seq", allocationSize = 1)
	@Schema(description = "添付ファイル商品種類毎チェックマスタID", required = true, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 商品種類区分
	 */
	@Column(nullable = false)
	@Size(max = 255)
	@Schema(description = "商品種類区分", required = true, allowableValues = "range[0,255]")
	private String productClassDiv;

	/**
	 * ドメイン
	 */
	@Column(nullable = false)
	@Size(max = 255)
	@Schema(description = "ドメイン", required = true, allowableValues = "range[0,255]")
	private String domain;

	/**
	 * 見積/契約種別
	 */
	@Size(max = 255)
	@Schema(description = "見積/契約種別", required = false, allowableValues = "range[0,255]")
	private String estimationContractType;

	/**
	 * ライフサイクル状態
	 */
	@Size(max = 255)
	@Schema(description = "ライフサイクル状態", required = false, allowableValues = "range[0,255]")
	private String lifecycleStatus;

	/**
	 * ファイル種類
	 */
	@Column(nullable = false)
	@Size(max = 255)
	@Schema(description = "ファイル種類", required = true, allowableValues = "range[0,255]")
	private String fileKind;

	/**
	 * チェック除外商品グループマスタID
	 */
	@Schema(description = "チェック除外商品グループマスタID", required = false, allowableValues = "range[0,255]")
	private String excludeProductGrpMasterId;

	/**
	 * 手配業務タイプマスタID
	 */
	@Min(0)
	@Schema(description = "手配業務タイプマスタID", required = false, allowableValues = "range[0,9223372036854775807]")
	private Long arrangementWorkTypeMasterId;

	/**
	 * 拡張子
	 */
	@Size(max = 255)
	@Schema(description = "拡張子", required = false, allowableValues = "range[0,255]")
	private String extension;

	/**
	 * V-UP連携チェック除外フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "V-UP連携チェック除外フラグ", required = false, allowableValues = "range[0,9]")
	private Integer vupLinkageCheckExcludeFlg;
}
