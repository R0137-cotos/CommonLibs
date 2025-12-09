package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.Arrays;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * バッチ実行日管理マスタを表すEntity
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "batch_run_date_management_master")
public class BatchRunDateManagementMaster extends EntityBaseMaster {

	@Description(value = "FFM計上連携区分")
	public enum FfmAccountingLinkageDiv {

		売上振替全連携("0"), 売上全社勘定連携("1"), 振替連携("2");

		private final String text;

		private FfmAccountingLinkageDiv(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static FfmAccountingLinkageDiv fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	/**
	 * バッチ実行日管理マスタID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "batch_run_date_management_master_seq")
	@SequenceGenerator(name = "batch_run_date_management_master_seq", sequenceName = "batch_run_date_management_master_seq", allocationSize = 1)
	@Schema(description = "バッチ実行日管理マスタID", required = true, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 対象バッチ処理ID
	 */
	@Schema(description = "対象バッチ処理ID", required = false, allowableValues = "range[0,255]")
	private String targetBatchId;

	/**
	 * 商品種類区分
	 */
	@Schema(description = "商品種類区分", required = false, allowableValues = "range[0,255]")
	private String productClassDiv;

	/**
	 * 商品マスタID
	 */
	@Schema(description = "商品マスタID", required = false, allowableValues = "range[0,9999999999999999999]")
	private Long productMasterId;

	/**
	 * バッチ実行日
	 */
	@Schema(description = "バッチ実行日", required = false)
	@Temporal(TemporalType.DATE)
	private Date batchRunDate;

	/**
	 * FFM計上連携区分
	 */
	@Schema(description = "FFM計上連携区分", required = false, allowableValues = "売上振替全連携(\"0\"), 売上全社勘定連携(\"1\"), 振替連携(\"2\")")
	private FfmAccountingLinkageDiv ffmAccountingLinkageDiv;
}
