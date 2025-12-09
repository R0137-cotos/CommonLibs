package jp.co.ricoh.cotos.commonlib.dto.parameter.contract.order;

import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 注文者情報DTO
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class OrdererInfoDto {

	/**
	 * 注文者情報 会社名
	 */
	@Size(max = 255)
	@Schema(description = "注文者情報　会社名", required = false, allowableValues = "range[0,255]")
	private String ordererCompanyName;

	/**
	 * 注文者情報 漢字（姓＋名）
	 */
	@Size(max = 255)
	@Schema(description = "注文者情報　漢字（姓＋名）", required = false, allowableValues = "range[0,255]")
	private String ordererNameKanji;

	/**
	 * 注文者情報 カナ（姓＋名）
	 */
	@Size(max = 255)
	@Schema(description = "注文者情報　カナ（姓＋名）", required = false, allowableValues = "range[0,255]")
	private String ordererNameKana;

	/**
	 * 注文者情報 メールアドレス
	 */
	@Size(max = 255)
	@Schema(description = "注文者情報　メールアドレス", required = false, allowableValues = "range[0,255]")
	private String ordererMailAddress;

	/**
	 * 注文者情報 郵便番号
	 */
	@Size(max = 255)
	@Schema(description = "注文者情報　郵便番号", required = false, allowableValues = "range[0,255]")
	private String ordererPostNumber;

	/**
	 * 注文者情報 事業所名
	 */
	@Size(max = 255)
	@Schema(description = "注文者情報　事業所名", required = false, allowableValues = "range[0,255]")
	private String ordererOfficeName;

	/**
	 * 注文者情報 住所
	 */
	@Size(max = 255)
	@Schema(description = "注文者情報　住所", required = false, allowableValues = "range[0,255]")
	private String ordererAddress;

	/**
	 * 注文者情報 電話番号
	 */
	@Size(max = 255)
	@Schema(description = "注文者情報　電話番号", required = false, allowableValues = "range[0,255]")
	private String ordererPhoneNumber;

}
