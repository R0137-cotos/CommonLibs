package jp.co.ricoh.cotos.commonlib.migrate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * COTOS管理情報を表すエンティティ
 * 
 */

@Data
@Entity
@Table(name = "cotos_management_info")
@EqualsAndHashCode(callSuper = true)
public class CotosManagementInfo extends EntityBase {

	/**COTOS管理情報ID*/
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cotos_management_info_seq")
	@SequenceGenerator(name = "cotos_management_info_seq", sequenceName = "cotos_management_info_seq", allocationSize = 1)
	private long id;

	/**基本情報*/
	@OneToOne(optional = false)
	@JoinColumn(name = "basic_contents_id", referencedColumnName = "id")
	@JsonIgnore
	private BasicContents basicContents;

	/**契約種別*/
	private String contractType;

	/**ライフサイクル状態*/
	private String lifecycleStatus;

	/**ワークフロー状態*/
	private String workflowStatus;

	/**商品グループマスタID*/
	private Long productGrpMasterId;

	/**顧客企事部システム連携ID*/
	private String custKjbSystemId;

	/**顧客企事部設定区分*/
	private String custDepartmentDiv;

	/**担当社員ID*/
	private String contractPicEmployeeId;

	/**販売店企事部システム連携ID*/
	@Column(name = "dealer_flow1_kjb_system_id")
	private String dealerFlow1KjbSystemId;

	/**母店企事部システム連携ID*/
	@Column(name = "dealer_flow2_kjb_system_id")
	private String dealerFlow2KjbSystemId;

	/**COTOS処理フラグ*/
	private String cotosProcessingFlg;

	/**処理不可事由*/
	private String unprocessedReason;

	/**イニシャル計上処理フラグ*/
	private String initialCostProcessingFlg;

	/**商流区分*/
	private String commercialFlowDiv;

	/**顧客_事業所ID*/
	private String officeId;

	/**商品マスタID*/
	private Long productMasterId;

	/**商品名*/
	private String productName;

}