package jp.co.ricoh.cotos.commonlib.dto.result;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class O365LatestContractDto {

	/**
	 * RJ管理番号
	 */
	@Schema(description = "RJ管理番号", required = true)
	private String rjManageNumber;

	/**
	 * サブドメイン名
	 */
	@Schema(description = "サブドメイン名", required = true)
	private String subDomainName;

	/**
	 * MoM非連携_担当者氏名
	 */
	@Schema(description = "MoM非連携_担当者氏名", required = false)
	private String picName;

	/**
	 * MoM非連携_担当者氏名（カナ）
	 */
	@Schema(description = "MoM非連携_担当者氏名（カナ）", required = false)
	private String picNameKana;

	/**
	 * MoM非連携_担当者電話番号
	 */
	@Schema(description = "MoM非連携_担当者電話番号", required = false)
	private String picPhoneNumber;

	/**
	 * MoM非連携_担当者電話番号
	 */
	@Schema(description = "MoM非連携_担当者メールアドレス", required = false)
	private String picMailAddress;

	/**
	 * 申込日
	 */
	@Schema(description = "申込日", required = false)
	private String applicationDate;

	/**
	 * キャンセル可能フラグ
	 */
	@Schema(description = "キャンセル可能フラグ", required = false)
	private String cancellationFlg;

	/**
	 * キャンセル可能期日
	 */
	@Schema(description = "キャンセル可能期日", required = false)
	private String cancellationDate;

	/**
	 * 得意先コード
	 */
	@Schema(description = "得意先コード", required = false)
	private String customerCd;

	/**
	 * 会社名
	 */
	@Schema(description = "会社名", required = false)
	private String companyName;

	/**
	 * 事業所名
	 */
	@Schema(description = "事業所名", required = false)
	private String officeName;

	/**
	 * 住所
	 */
	@Schema(description = "住所", required = false)
	private String address;

	/**
	 * 電話番号
	 */
	@Schema(description = "電話番号", required = false)
	private String phoneNumber;

	/**
	 * 変更申込用ID
	 */
	@Schema(description = "変更申込用ID", required = false)
	private String changeApplyId;

	/**
	 * 契約明細情報リスト
	 */
	@Schema(description = "契約明細情報リスト", required = false)
	private List<O365LatestContractDetailDto> contractDetailList;

}
