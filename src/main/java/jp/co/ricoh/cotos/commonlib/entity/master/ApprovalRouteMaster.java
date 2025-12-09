package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.Arrays;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

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
 * 承認ルートマスタ
 */
@Entity
@Data
@ToString(exclude = { "approvalRouteGrpMaster" })
@EqualsAndHashCode(callSuper = true)
@Table(name = "approval_route_master")
public class ApprovalRouteMaster extends EntityBaseMaster {

	@Description(value = "契約承認依頼者区分")
	public enum ContractApprovalRequesterDiv {
		担当SA("1"), 受付担当CE("2"), 導入担当CE("3"), 保守担当CE("4");

		private final String text;

		private ContractApprovalRequesterDiv(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static ContractApprovalRequesterDiv fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "approval_route_master_seq")
	@SequenceGenerator(name = "approval_route_master_seq", sequenceName = "approval_route_master_seq", allocationSize = 1)
	@Schema(description = "承認ルートマスタID", required = true, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 承認ルートグループマスタ
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "approval_route_grp_id", referencedColumnName = "id")
	@Schema(description = "承認ルートグループマスタ", required = true)
	@JsonIgnore
	private ApprovalRouteGrpMaster approvalRouteGrpMaster;

	/**
	 * 承認ルート名
	 */
	@Column(nullable = false)
	@Schema(description = "承認ルート名", required = true, allowableValues = "range[0,255]")
	private String approvalRouteName;

	/**
	 * 説明
	 */
	@Schema(description = "説明", required = false, allowableValues = "range[0,255]")
	private String description;

	/**
	 * 特価承認対象フラグ
	 */
	@Column(nullable = false)
	@Schema(description = "特価承認対象フラグ", required = true, allowableValues = "range[0,9]")
	private int specialPriceApprovalFlg;

	/**
	 * ルート条件式
	 */
	@Schema(description = "ルート条件式", required = false)
	private String routeConditionFormula;

	/**
	 * 条件判定順
	 */
	@Column(nullable = false)
	@Schema(description = "条件判定順 ", required = true, allowableValues = "range[0,999]")
	private int condDetermineOrder;

	/**
	 * 承認ルートノードマスタ
	 */
	@OneToMany(mappedBy = "approvalRouteMaster")
	@Schema(description = "承認ルートノードマスタ", required = true)
	private List<ApprovalRouteNodeMaster> approvalRouteNodeMasterList;

	/**
	 * 契約承認依頼者区分
	 */
	@Schema(description = "契約承認依頼者区分", required = false, allowableValues = "担当SA(\"1\"), 受付担当CE(\"2\"), 導入担当CE(\"3\"), 保守担当CE(\"4\")", example = "1")
	private ContractApprovalRequesterDiv contractApprovalRequesterDiv;

}
