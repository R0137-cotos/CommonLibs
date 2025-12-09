package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 承認ルートグループマスタ
 */
@Entity
@Data
@ToString(exclude = { "approvalRouteMasterList" })
@EqualsAndHashCode(callSuper = true)
@Table(name = "approval_route_grp_master")
public class ApprovalRouteGrpMaster extends EntityBaseMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "approval_route_grp_master_seq")
	@SequenceGenerator(name = "approval_route_grp_master_seq", sequenceName = "approval_route_grp_master_seq", allocationSize = 1)
	@Schema(description = "承認ルートグループマスタID", required = true, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 承認ルートグループ名
	 */
	@Column(nullable = false)
	@Schema(description = "承認ルートグループ名", required = true, allowableValues = "range[0,255]")
	private String approvalRouteGrpName;

	/**
	 * 説明
	 */
	@Schema(description = "説明", required = false, allowableValues = "range[0,255]")
	private String description;

	/**
	 * 承認ルートマスタ
	 */
	@OrderBy("condDetermineOrder ASC")
	@OneToMany(mappedBy = "approvalRouteGrpMaster")
	@Schema(description = "承認ルートマスタ", required = true)
	private List<ApprovalRouteMaster> approvalRouteMasterList;

	/**
	 * 商品グループマスタ（見積承認）
	 */
	@OneToMany(mappedBy = "estimationApprovalRouteGrpMaster")
	@JsonIgnore
	@Schema(description = "商品グループマスタ（見積承認）", required = false)
	private List<ProductGrpMaster> estimationProductGrpMasterList;

	/**
	 * 商品グループマスタ（契約承認）
	 */
	@OneToMany(mappedBy = "contractApprovalRouteGrpMaster")
	@JsonIgnore
	@Schema(description = "商品グループマスタ（契約承認）", required = false)
	private List<ProductGrpMaster> contractProductGrpMasterList;

	/**
	 * 手配業務タイプマスタ
	 */
	@OneToMany(mappedBy = "approvalRouteGrpMaster")
	@JsonIgnore
	@Schema(description = "手配業務タイプマスタ", required = false)
	private List<ArrangementWorkTypeMaster> arrangementWorkTypeMasterList;

}
