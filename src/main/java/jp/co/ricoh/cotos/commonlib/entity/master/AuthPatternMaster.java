package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.Arrays;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import jp.co.ricoh.cotos.commonlib.entity.master.UrlAuthMaster.AccessType;
import jp.co.ricoh.cotos.commonlib.entity.master.UrlAuthMaster.ActionDiv;
import jp.co.ricoh.cotos.commonlib.entity.master.UrlAuthMaster.AuthDiv;
import jp.co.ricoh.cotos.commonlib.security.mom.MomAuthorityService.AuthLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 権限パターンマスタ
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "auth_pattern_master")
public class AuthPatternMaster extends EntityBaseMaster {

	@Description(value = "権限判定方式区分")
	public enum AuthJudgeDiv {
		COTOS認可("01"), MoM権限("02");

		private final String text;

		private AuthJudgeDiv(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static AuthJudgeDiv fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Id
	@Schema(description = "権限パターンID", required = true, allowableValues = "range[0,9999999999999999999]")
	private long authPatternId;

	/**
	 * アクション区分
	 */
	@Column(nullable = false)
	@Schema(description = "アクション区分", required = true, allowableValues = "なし(\"00\"), 照会(\"01\"), 登録(\"02\"), 更新(\"03\"), 削除(\"04\"), 印刷(\"05\"), ダウンロード(\"06\"), 集計(\"07\")", example = "00")
	private ActionDiv actionDiv;

	/**
	 * 権限区分
	 */
	@Column(nullable = false)
	@Schema(description = "権限区分", required = true, allowableValues = "なし(\"0\"), 見積_契約_手配(\"2200\"), 請求_計上_本部(\"2210\"), システム管理(\"2220\"), 見積_契約_業務用検索(\"2230\"), 業務管理(\"2240\")", example = "0")
	private AuthDiv authDiv;

	/**
	 * 参照種別
	 */
	@Column(nullable = false)
	@Schema(description = "参照種別", required = true, allowableValues = "なし(\"0\"), 参照(\"1\"), 編集(\"2\"), 承認(\"3\")", example = "1")
	private AccessType accessType;

	/**
	 * 権限判定方式区分
	 */
	@Column(nullable = false)
	@Schema(description = "権限判定方式区分", required = true, allowableValues = "COTOS認可(\"01\"), MoM権限(\"02\")", example = "01")
	private AuthJudgeDiv authJudgeDiv;

	/**
	 * MoM権限閾値
	 */
	@Column(nullable = true)
	@Schema(description = "MoM権限閾値", required = false, allowableValues = "不可(\"00\"), 自顧客(\"10\"), 配下(\"30\"), 自社(\"50\"), 地域(\"70\"), 東西(\"80\"), すべて(\"90\")", example = "90")
	private AuthLevel momThresholdLevel;

	/**
	 * 画面URL権限マスタ
	 */
	@JsonIgnore
	@OneToMany(mappedBy = "authPatternMaster")
	@Schema(description = "画面URL権限マスタ", required = false)
	private List<DispUrlAuthMaster> authPatternMasterList;

	/**
	 * 手配業務権限制御マスタ
	 */
	@JsonIgnore
	@OneToMany(mappedBy = "authPatternMaster")
	@Schema(description = "手配業務権限制御マスタ", required = false)
	private List<ArrangementWorkAuthControlMaster> arrangementWorkAuthControlMasterList;
}
