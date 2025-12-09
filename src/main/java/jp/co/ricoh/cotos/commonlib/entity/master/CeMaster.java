package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * CEマスタ
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "ce_master")
public class CeMaster extends EntityBaseMaster {

	/**
	 * ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ce_master_seq")
	@SequenceGenerator(name = "ce_master_seq", sequenceName = "ce_master_seq", allocationSize = 1)
	@Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9999999999999999999999999999]")
	private long id;

	/**
	 * CEコード
	 */
	@Size(max = 255)
	@Schema(description = "CEコード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String nCeCode;

	/**
	 * CE名(漢字)
	 */
	@Size(max = 255)
	@Schema(description = "CE名(漢字)", allowableValues = "range[0,255]")
	private String nCeDesc;

	/**
	 * CE名(カナ)
	 */
	@Size(max = 255)
	@Schema(description = "CE名(カナ)", allowableValues = "range[0,255]")
	private String nCeDescKana;

	/**
	 * 帰属課所コード
	 */
	@Size(max = 255)
	@Schema(description = "帰属課所コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String nBelongOrgCode;

	/**
	 * 社員コード
	 */
	@Size(max = 255)
	@Schema(description = "社員コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String nEmpCode;

	/**
	 * グループコード
	 */
	@Size(max = 255)
	@Schema(description = "グループコード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String nGroupCode;

	/**
	 * 業務区分
	 */
	@Size(max = 255)
	@Schema(description = "業務区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String nBusinessType;

	/**
	 * クラス
	 */
	@Size(max = 255)
	@Schema(description = "クラス", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String nClass;

	/**
	 * 入社年月日
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Schema(description = "入社年月日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date nEmpDate;

	/**
	 * 更新区分
	 */
	@Size(max = 255)
	@Schema(description = "更新区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String nModifyType;

	/**
	 * 更新年月日
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Schema(description = "更新年月日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date nModifyDate;

	/**
	 * 登録年月日
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Schema(description = "登録年月日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date nNewDate;

	/**
	 * 退職年月日
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Schema(description = "退職年月日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date nRetireDate;

	/**
	 * TeMS参照区分
	 */
	@Size(max = 255)
	@Schema(description = "TeMS参照区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String nTemsRefType;

	/**
	 * TeMS権限区分
	 */
	@Size(max = 255)
	@Schema(description = "TeMS権限区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String nTemsAuthType;

	/**
	 * MA区分
	 */
	@Size(max = 255)
	@Schema(description = "MA区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String nMaType;

	/**
	 * SI区分
	 */
	@Size(max = 255)
	@Schema(description = "SI区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String nSiType;
}
