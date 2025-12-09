package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.Arrays;

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

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 添付ファイル商品毎チェックマスタ
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "attached_file_product_grp_check_master")
public class AttachedFileProductGrpCheckMaster extends EntityBaseMaster {

	@Description(value = "チェックタイミング区分")
	public enum CheckTimingDiv {

		常時("0"), 承認のみ("1"), 常時しない("2");

		private final String text;

		private CheckTimingDiv(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static CheckTimingDiv fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	/**
	 * 添付ファイル商品毎チェックマスタID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "attached_file_product_grp_check_master_seq")
	@SequenceGenerator(name = "attached_file_product_grp_check_master_seq", sequenceName = "attached_file_product_grp_check_master_seq", allocationSize = 1)
	@Schema(description = "添付ファイル商品毎チェックマスタID", required = true, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 商品グループマスタID
	 */
	@Column(nullable = false)
	@Schema(description = "商品グループマスタID", required = true, allowableValues = "range[0,9999999999999999999]")
	private Long productGrpMasterId;

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
	 * 品種マスタID
	 */
	@Min(0)
	@Schema(description = "品種マスタID", required = false, allowableValues = "range[0,9999999999999999999]")
	private Long itemMasterId;

	/**
	 * 手配業務タイプマスタID
	 */
	@Min(0)
	@Schema(description = "手配業務タイプマスタID", required = false, allowableValues = "range[0,9999999999999999999]")
	private Long arrangementWorkTypeMasterId;

	/**
	 * 拡張子
	 */
	@Size(max = 255)
	@Schema(description = "拡張子", required = false, allowableValues = "range[0,255]")
	private String extension;

	/**
	 * チェックタイミング区分
	 */
	@Schema(description = "チェックタイミング区分", required = false, allowableValues = "常時(\"0\"), 承認のみ(\"1\"), 常時しない(\"2\")")
	private CheckTimingDiv checkTimingDiv;

	/**
	 * V-UP連携チェック除外フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "V-UP連携チェック除外フラグ", required = false, allowableValues = "range[0,9]")
	private Integer vupLinkageCheckExcludeFlg;
}
