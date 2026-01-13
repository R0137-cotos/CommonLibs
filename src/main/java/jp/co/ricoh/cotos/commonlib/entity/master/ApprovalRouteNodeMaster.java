package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.Arrays;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 承認ルートノードマスタ
 */
@Entity
@Data
@ToString(exclude = { "approvalRouteMaster" })
@EqualsAndHashCode(callSuper = true)
@Table(name = "approval_route_node_master")
public class ApprovalRouteNodeMaster extends EntityBaseMaster {

	@Description(value = "承認者種別")
	public enum ApproverClass {

		メイン承認者("1"), 代理承認者("2");

		private final String text;

		private ApproverClass(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static ApproverClass fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "承認者導出方式区分")
	public enum ApproverDeriveMethodDiv {

		直属上司指定("1"), 組織絶対階層指定("2"), 組織直接指定("3"), ユーザー直接指定("4"), 自己承認("5"), 受付担当CE指定("6"), グループ承認("7");

		private final String text;

		private ApproverDeriveMethodDiv(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static ApproverDeriveMethodDiv fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "approval_route_node_master_seq")
	@SequenceGenerator(name = "approval_route_node_master_seq", sequenceName = "approval_route_node_master_seq", allocationSize = 1)
	@Schema(description = "承認ルートノードマスタID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 承認ルートマスタ
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "approval_route_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "承認ルートマスタ", requiredMode = Schema.RequiredMode.REQUIRED)
	private ApprovalRouteMaster approvalRouteMaster;

	/**
	 * 承認順
	 */
	@Column(nullable = false)
	@Schema(description = "承認順", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,999]")
	private int approvalOrder;

	/**
	 * 承認者種別
	 */
	@Column(nullable = false)
	@Schema(description = "承認者種別", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "メイン承認者(\"1\"), 代理承認者(\"2\")", example = "1")
	private ApproverClass approverClass;

	/**
	 * 承認者導出方式区分
	 */
	@Column(nullable = false)
	@Schema(description = "承認者導出方式区分", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "直属上司指定(\"1\"), 組織絶対階層指定(\"2\"), 組織直接指定(\"3\"), ユーザー直接指定(\"4\"), 自己承認(\"5\"), 受付担当CE指定(\"6\"), グループ承認(\"7\")", example = "1")
	private ApproverDeriveMethodDiv approverDeriveMethodDiv;

	/**
	 * 組織階層レベル
	 */
	@Schema(description = "組織階層レベル", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer hierarchyLevel;

	/**
	 * MoM組織ID
	 */
	@Schema(description = "MoM組織ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String momOrgId;

	/**
	 * MoM社員ID
	 */
	@Schema(description = "MoM社員ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String momEmpId;

	/**
	 * グループコード
	 */
	@Size(max = 255)
	@Schema(description = "グループコード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String groupCode;

}
