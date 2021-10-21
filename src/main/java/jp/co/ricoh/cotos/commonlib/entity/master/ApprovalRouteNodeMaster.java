package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;
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
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst()
					.orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
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
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst()
					.orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "approval_route_node_master_seq")
	@SequenceGenerator(name = "approval_route_node_master_seq", sequenceName = "approval_route_node_master_seq", allocationSize = 1)
	@ApiModelProperty(value = "承認ルートノードマスタID", required = true, position = 1, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 承認ルートマスタ
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "approval_route_id", referencedColumnName = "id")
	@JsonIgnore
	@ApiModelProperty(value = "承認ルートマスタ", required = true, position = 2)
	private ApprovalRouteMaster approvalRouteMaster;

	/**
	 * 承認順
	 */
	@Column(nullable = false)
	@ApiModelProperty(value = "承認順", required = true, position = 3, allowableValues = "range[0,999]")
	private int approvalOrder;

	/**
	 * 承認者種別
	 */
	@Column(nullable = false)
	@ApiModelProperty(value = "承認者種別", required = true, allowableValues = "メイン承認者(\"1\"), 代理承認者(\"2\")", example = "1", position = 4)
	private ApproverClass approverClass;

	/**
	 * 承認者導出方式区分
	 */
	@Column(nullable = false)
	@ApiModelProperty(value = "承認者導出方式区分", required = true, allowableValues = "直属上司指定(\"1\"), 組織絶対階層指定(\"2\"), 組織直接指定(\"3\"), ユーザー直接指定(\"4\"), 自己承認(\"5\"), 受付担当CE指定(\"6\"), グループ承認(\"7\")", example = "1", position = 5)
	private ApproverDeriveMethodDiv approverDeriveMethodDiv;

	/**
	 * 組織階層レベル
	 */
	@ApiModelProperty(value = "組織階層レベル", required = false, position = 6, allowableValues = "range[0,9]")
	private Integer hierarchyLevel;

	/**
	 * MoM組織ID
	 */
	@ApiModelProperty(value = "MoM組織ID", required = false, position = 7, allowableValues = "range[0,255]")
	private String momOrgId;

	/**
	 * MoM社員ID
	 */
	@ApiModelProperty(value = "MoM社員ID", required = false, position = 8, allowableValues = "range[0,255]")
	private String momEmpId;

	/**
	 * グループコード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "グループコード", required = false, position = 9, allowableValues = "range[0,255]")
	private String groupCode;

}
