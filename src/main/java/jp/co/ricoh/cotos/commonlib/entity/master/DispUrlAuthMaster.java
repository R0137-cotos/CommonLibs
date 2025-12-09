package jp.co.ricoh.cotos.commonlib.entity.master;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 画面URL権限マスタ
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "disp_url_auth_master")
public class DispUrlAuthMaster extends EntityBaseMaster {

	@Embeddable
	@Data
	public static class Id implements Serializable {

		/**
		 * シリアルバージョンID
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * システムドメイン
		 */
		@Column(nullable = false)
		@Schema(description = "システムドメイン", required = true)
		private String systemDomain;

		/**
		 * URLパターン
		 */
		@Column(nullable = false)
		@Schema(description = "URLパターン", required = true)
		private String urlPattern;

		/**
		 * アクションID
		 */
		@Column(nullable = false)
		@Schema(description = "アクションID", required = true)
		private String actionId;
	}

	@EmbeddedId
	private Id id;

	/**
	 * アクション名
	 */
	@Schema(description = "アクション名", required = false)
	private String actionName;

	/**
	 * 権限パターンマスタ
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "auth_pattern_id", referencedColumnName = "authPatternId")
	@Schema(description = "権限パターンマスタ", required = true)
	private AuthPatternMaster authPatternMaster;
}
