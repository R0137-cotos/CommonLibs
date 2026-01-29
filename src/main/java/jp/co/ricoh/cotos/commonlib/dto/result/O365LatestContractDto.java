package jp.co.ricoh.cotos.commonlib.dto.result;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class O365LatestContractDto {

	/**
	 * RJ管理番号
	 */
	@Schema(description = "RJ管理番号", requiredMode = Schema.RequiredMode.REQUIRED)
	private String rjManageNumber;

	/**
	 * サブドメイン名
	 */
	@Schema(description = "サブドメイン名", requiredMode = Schema.RequiredMode.REQUIRED)
	private String subDomainName;

	/**
	 * MoM非連携_担当者氏名
	 */
	@Schema(description = "MoM非連携_担当者氏名", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String picName;

	/**
	 * MoM非連携_担当者氏名（カナ）
	 */
	@Schema(description = "MoM非連携_担当者氏名（カナ）", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String picNameKana;

	/**
	 * MoM非連携_担当者電話番号
	 */
	@Schema(description = "MoM非連携_担当者電話番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String picPhoneNumber;

	/**
	 * MoM非連携_担当者電話番号
	 */
	@Schema(description = "MoM非連携_担当者メールアドレス", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String picMailAddress;

	/**
	 * 申込日
	 */
	@Schema(description = "申込日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String applicationDate;

	/**
	 * キャンセル可能フラグ
	 */
	@Schema(description = "キャンセル可能フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String cancellationFlg;

	/**
	 * キャンセル可能期日
	 */
	@Schema(description = "キャンセル可能期日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String cancellationDate;

	/**
	 * 得意先コード
	 */
	@Schema(description = "得意先コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String customerCd;

	/**
	 * 会社名
	 */
	@Schema(description = "会社名", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String companyName;

	/**
	 * 事業所名
	 */
	@Schema(description = "事業所名", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String officeName;

	/**
	 * 住所
	 */
	@Schema(description = "住所", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String address;

	/**
	 * 電話番号
	 */
	@Schema(description = "電話番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String phoneNumber;

	/**
	 * 変更申込用ID
	 */
	@Schema(description = "変更申込用ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String changeApplyId;

	/**
	 * 契約明細情報リスト
	 */
	@Schema(description = "契約明細情報リスト", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private List<O365LatestContractDetailDto> contractDetailList;

}
