package jp.co.ricoh.cotos.commonlib.entity.master;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 直送販売店情報マスタ
 */
@Entity
@Data
@Table(name = "v_direct_delivery_dealer_info")
public class VDirectDeliveryDealerInfoMaster {

	@Embeddable
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Id implements Serializable {

		/**
		 * シリアルバージョンID
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * 販売店識別子
		 */
		@Schema(description = "販売店識別子", requiredMode = Schema.RequiredMode.REQUIRED)
		private String dealerDiscrimCd;

		/**
		 * URLパターン
		 */
		@Schema(description = "デポコード", requiredMode = Schema.RequiredMode.REQUIRED)
		private String dpCd;

	}

	@EmbeddedId
	private Id id;

	@Column
	@Schema(description = "販売店名(漢字)")
	private String dlPrCommDistDealerName;

	@Column
	@Schema(description = "販売店名(カナ)")
	private String dlPrCommDistDealerNameKana;

	@Column
	@Schema(description = "デポ名(漢字)")
	private String dlDpName;

	@Column
	@Schema(description = "デポ名(カナ)")
	private String dlDpNameKana;

	@Column
	@Schema(description = "売上情報送信年月日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String adSalesSendDate; //yyyyMMddのフォーマットの文字列で設定されている

}
