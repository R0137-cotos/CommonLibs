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
	@Schema(description = "ID", required = true, allowableValues = "range[0,9999999999999999999999999999]")
	private long id;

	/**
	 * CEコード
	 */
	@Size(max = 255)
	@Schema(description = "CEコード", required = false, allowableValues = "range[0,255]")
	private String nCeCode;

	/**
	 * CE名(漢字)
	 */
	@Size(max = 255)
	@Schema(description = "CE名(漢字)", required = false, allowableValues = "range[0,255]")
	private String nCeDesc;

	/**
	 * CE名(カナ)
	 */
	@Size(max = 255)
	@Schema(description = "CE名(カナ)", required = false, allowableValues = "range[0,255]")
	private String nCeDescKana;

	/**
	 * 帰属課所コード
	 */
	@Size(max = 255)
	@Schema(description = "帰属課所コード", required = false, allowableValues = "range[0,255]")
	private String nBelongOrgCode;

	/**
	 * 社員コード
	 */
	@Size(max = 255)
	@Schema(description = "社員コード", required = false, allowableValues = "range[0,255]")
	private String nEmpCode;

	/**
	 * グループコード
	 */
	@Size(max = 255)
	@Schema(description = "グループコード", required = false, allowableValues = "range[0,255]")
	private String nGroupCode;

	/**
	 * 業務区分
	 */
	@Size(max = 255)
	@Schema(description = "業務区分", required = false, allowableValues = "range[0,255]")
	private String nBusinessType;

	/**
	 * クラス
	 */
	@Size(max = 255)
	@Schema(description = "クラス", required = false, allowableValues = "range[0,255]")
	private String nClass;

	/**
	 * 入社年月日
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Schema(description = "入社年月日", required = false)
	private Date nEmpDate;

	/**
	 * 更新区分
	 */
	@Size(max = 255)
	@Schema(description = "更新区分", required = false, allowableValues = "range[0,255]")
	private String nModifyType;

	/**
	 * 更新年月日
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Schema(description = "更新年月日", required = false)
	private Date nModifyDate;

	/**
	 * 登録年月日
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Schema(description = "登録年月日", required = false)
	private Date nNewDate;

	/**
	 * 退職年月日
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Schema(description = "退職年月日", required = false)
	private Date nRetireDate;

	/**
	 * TeMS参照区分
	 */
	@Size(max = 255)
	@Schema(description = "TeMS参照区分", required = false, allowableValues = "range[0,255]")
	private String nTemsRefType;

	/**
	 * TeMS権限区分
	 */
	@Size(max = 255)
	@Schema(description = "TeMS権限区分", required = false, allowableValues = "range[0,255]")
	private String nTemsAuthType;

	/**
	 * MA区分
	 */
	@Size(max = 255)
	@Schema(description = "MA区分", required = false, allowableValues = "range[0,255]")
	private String nMaType;

	/**
	 * SI区分
	 */
	@Size(max = 255)
	@Schema(description = "SI区分", required = false, allowableValues = "range[0,255]")
	private String nSiType;
}
