package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * MoM汎用マスタ
 */
@Entity
@Data
@Table(name = "mv_tjmmb010_utl_item")
public class MvTjmmb010UtlItem {

	/**
	 * マスタID
	 */
	@Id
	@Column(length = 40)
	@Schema(description = "マスタID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,40]")
	private String itemId;

	/**
	 * マスタ名称
	 */
	@Column(length = 256)
	@Schema(description = "マスタ名称", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,256]")
	private String itemName;

	/**
	 * マスタ説明
	 */
	@Column(length = 256)
	@Schema(description = "マスタ説明", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,256]")
	private String itemNote;

	/**
	 * マスタ主管区システムID
	 */
	@Column(length = 4)
	@Schema(description = "マスタ主管区システムID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,4]")
	private String controlSystemId;

	/**
	 * コード値最大桁数
	 */
	@Schema(description = "コード値最大桁数", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Integer cdValMaxlength;

	/**
	 * 削除フラグ
	 */
	@Column(length = 1)
	@Schema(description = "削除フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,1]")
	private String delFlg;

	/**
	 * 更新MOM会社ID
	 */
	@Column(length = 6)
	@Schema(description = "更新MOM会社ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,6]")
	private String updateCorpId;

	/**
	 * 登録者ID
	 */
	@Column(length = 8)
	@Schema(description = "更新MOM会社ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,8]")
	private String registerUserId;

	/**
	 * 登録者名
	 */
	@Column(length = 100)
	@Schema(description = "登録者名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,100]")
	private String registerUserName;

	/**
	 * 更新者ID
	 */
	@Column(length = 8)
	@Schema(description = "更新者ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,8]")
	private String updatingUserId;

	/**
	 * 更新者名
	 */
	@Column(length = 100)
	@Schema(description = "更新者名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,100]")
	private String updatingUserName;

	/**
	 * 登録日時
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Schema(description = "登録日時", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date registerDate;

	/**
	 * 更新日時
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Schema(description = "更新日時", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date updatingDate;

	/**
	 * 登録プログラムID
	 */
	@Column(length = 30)
	@Schema(description = "登録プログラムID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,30]")
	private String registerProgramId;

	/**
	 * 更新プログラムID
	 */
	@Column(length = 30)
	@Schema(description = "更新プログラムID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,30]")
	private String updatingProgramId;

	/**
	 * MoM汎用マスタ明細
	 */
	@Transient
	@Schema(hidden = true)
	private List<MvTjmmb020UtlCd> momCommonDetailMasterList;
}
