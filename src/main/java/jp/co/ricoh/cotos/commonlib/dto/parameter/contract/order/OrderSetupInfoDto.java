package jp.co.ricoh.cotos.commonlib.dto.parameter.contract.order;

import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 注文セットアップ先情報DTO
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class OrderSetupInfoDto {

	/**
	 * セットアップ先情報 会社名
	 */
	@Size(max = 255)
	@Schema(description = "セットアップ先情報　会社名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String setupCompanyName;

	/**
	 * セットアップ先情報 漢字（姓＋名）
	 */
	@Size(max = 255)
	@Schema(description = "セットアップ先情報　漢字（姓＋名）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String setupNameKanji;

	/**
	 * セットアップ先情報 カナ（姓＋名）
	 */
	@Size(max = 255)
	@Schema(description = "セットアップ先情報　カナ（姓＋名）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String setupNameKana;

	/**
	 * セットアップ先情報 メールアドレス
	 */
	@Size(max = 255)
	@Schema(description = "セットアップ先情報　メールアドレス", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String setupMailAddress;

	/**
	 * セットアップ先情報 郵便番号
	 */
	@Size(max = 255)
	@Schema(description = "セットアップ先情報　郵便番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String setupPostNumber;

	/**
	 * セットアップ先情報 事業所名
	 */
	@Size(max = 255)
	@Schema(description = "セットアップ先情報　事業所名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String setupOfficeName;

	/**
	 * セットアップ先情報 住所
	 */
	@Size(max = 255)
	@Schema(description = "セットアップ先情報　住所", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String setupAddress;

	/**
	 * セットアップ先情報 電話番号
	 */
	@Size(max = 255)
	@Schema(description = "セットアップ先情報　電話番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String setupPhoneNumber;

}
