package jp.co.ricoh.cotos.commonlib.entity.master;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * oM汎用マスタ明細
 */
@Entity
@Data
@Table(name = "mv_tjmmb020_utl_cd")
public class MvTjmmb020UtlCd {

	@Embeddable
	@Data
	public static class Id implements Serializable {
		/**
		 * シリアルバージョンID
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * マスタID
		 */
		@Column(name = "item_id", length = 40, nullable = false)
		@Schema(description = "マスタID", required = true, allowableValues = "range[0,40]")
		private String itemId;

		/**
		 * コード値
		 */
		@Column(name = "cd_val", length = 100, nullable = false)
		@Schema(description = "コード値", required = true, allowableValues = "range[0,100]")
		private String cdVal;

	}

	@EmbeddedId
	private Id id;

	/**
	 * コード内容
	 */
	@Column(length = 256)
	@Schema(description = "コード内容", required = false, allowableValues = "range[0,256]")
	private String decdVal;

	/**
	 * コード説明
	 */
	@Column(length = 256)
	@Schema(description = "コード説明", required = false, allowableValues = "range[0,256]")
	private String cdNote;

	/**
	 * データエリア1
	 */
	@Column(length = 256)
	@Schema(description = "データエリア1", required = false, allowableValues = "range[0,256]")
	private String dataArea1;

	/**
	 * データエリア2
	 */
	@Column(length = 256)
	@Schema(description = "データエリア2", required = false, allowableValues = "range[0,256]")
	private String dataArea2;

	/**
	 * データエリア3
	 */
	@Column(length = 256)
	@Schema(description = "データエリア3", required = false, allowableValues = "range[0,256]")
	private String dataArea3;

	/**
	 * データエリア4
	 */
	@Column(length = 256)
	@Schema(description = "データエリア4", required = false, allowableValues = "range[0,256]")
	private String dataArea4;

	/**
	 * データエリア5
	 */
	@Column(length = 256)
	@Schema(description = "データエリア5", required = false, allowableValues = "range[0,256]")
	private String dataArea5;

	/**
	 * ソート順
	 */
	@Schema(description = "ソート順", required = false)
	private Integer sortNumber;

	/**
	 * 削除フラグ
	 */
	@Column(length = 1)
	@Schema(description = "削除フラグ", required = false, allowableValues = "range[0,1]")
	private String delFlg;

	/**
	 * 更新MOM会社ID
	 */
	@Column(length = 6)
	@Schema(description = "更新MOM会社ID", required = false, allowableValues = "range[0,6]")
	private String updateCorpId;

	/**
	 * 登録者ID
	 */
	@Column(length = 8)
	@Schema(description = "更新MOM会社ID", required = false, allowableValues = "range[0,8]")
	private String registerUserId;

	/**
	 * 登録者名
	 */
	@Column(length = 100)
	@Schema(description = "登録者名", required = false, allowableValues = "range[0,100]")
	private String registerUserName;

	/**
	 * 更新者ID
	 */
	@Column(length = 8)
	@Schema(description = "更新者ID", required = false, allowableValues = "range[0,8]")
	private String updatingUserId;

	/**
	 * 更新者名
	 */
	@Column(length = 100)
	@Schema(description = "更新者名", required = false, allowableValues = "range[0,100]")
	private String updatingUserName;

	/**
	 * 登録日時
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Schema(description = "登録日時", required = false)
	private Date registerDate;

	/**
	 * 更新日時
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Schema(description = "更新日時", required = false)
	private Date updatingDate;

	/**
	 * 登録プログラムID
	 */
	@Column(length = 30)
	@Schema(description = "登録プログラムID", required = false, allowableValues = "range[0,30]")
	private String registerProgramId;

	/**
	 * 更新プログラムID
	 */
	@Column(length = 30)
	@Schema(description = "更新プログラムID", required = false, allowableValues = "range[0,30]")
	private String updatingProgramId;
}
