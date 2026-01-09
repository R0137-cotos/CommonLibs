package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * システムマスタ
 */
@Entity
@Data
@ToString(exclude = { "appMasterList" })
@EqualsAndHashCode(callSuper = true)
@Table(name = "system_master")
public class SystemMaster extends EntityBaseMaster {

	@Id
	@Schema(description = "システムID", requiredMode = Schema.RequiredMode.REQUIRED)
	private String systemId;

	@Column(nullable = false)
	@Schema(description = "他システムデータ排他フラグ", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9]")
	private int otherSysDataExcludeFlg;

	@OneToMany(mappedBy = "systemMaster")
	@Schema(description = "アプリケーションマスタ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private List<AppMaster> appMasterList;
}
