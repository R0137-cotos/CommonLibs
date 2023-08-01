package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(value = "添付ファイル商品毎チェックマスタID", required = true, position = 1, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 商品グループマスタID
	 */
	@Column(nullable = false)
	@ApiModelProperty(value = "商品グループマスタID", required = true, position = 2, allowableValues = "range[0,9999999999999999999]")
	private Long productGrpMasterId;

	/**
	 * ドメイン
	 */
	@Column(nullable = false)
	@Size(max = 255)
	@ApiModelProperty(value = "ドメイン", required = true, position = 3, allowableValues = "range[0,255]")
	private String domain;

	/**
	 * 見積/契約種別
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "見積/契約種別", required = false, position = 4, allowableValues = "range[0,255]")
	private String estimationContractType;

	/**
	 * ライフサイクル状態
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "ライフサイクル状態", required = false, position = 5, allowableValues = "range[0,255]")
	private String lifecycleStatus;

	/**
	 * ファイル種類
	 */
	@Column(nullable = false)
	@Size(max = 255)
	@ApiModelProperty(value = "ファイル種類", required = true, position = 6, allowableValues = "range[0,255]")
	private String fileKind;

	/**
	 * 品種マスタID
	 */
	@Min(0)
	@ApiModelProperty(value = "品種マスタID", required = false, position = 7, allowableValues = "range[0,9999999999999999999]")
	private Long itemMasterId;

	/**
	 * 手配業務タイプマスタID
	 */
	@Min(0)
	@ApiModelProperty(value = "手配業務タイプマスタID", required = false, position = 8, allowableValues = "range[0,9999999999999999999]")
	private Long arrangementWorkTypeMasterId;

	/**
	 * 拡張子
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "拡張子", required = false, position = 9, allowableValues = "range[0,255]")
	private String extension;

	/**
	 * チェックタイミング区分
	 */
	@ApiModelProperty(value = "チェックタイミング区分", required = false, allowableValues = "常時(\"0\"), 承認のみ(\"1\"), 常時しない(\"2\")", position = 10)
	private CheckTimingDiv checkTimingDiv;

}
