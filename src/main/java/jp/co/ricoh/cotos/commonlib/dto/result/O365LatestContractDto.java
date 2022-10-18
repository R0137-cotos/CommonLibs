package jp.co.ricoh.cotos.commonlib.dto.result;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class O365LatestContractDto {

	/**
	 * RJ管理番号
	 */
	@ApiModelProperty(value = "RJ管理番号", required = true, position = 1)
	private String rjManageNumber;

	/**
	 * サブドメイン名
	 */
	@ApiModelProperty(value = "サブドメイン名", required = true, position = 2)
	private String subDomainName;

	/**
	 * MoM非連携_担当者氏名
	 */
	@ApiModelProperty(value = "MoM非連携_担当者氏名", required = false, position = 3)
	private String picName;

	/**
	 * MoM非連携_担当者氏名（カナ）
	 */
	@ApiModelProperty(value = "MoM非連携_担当者氏名（カナ）", required = false, position = 4)
	private String picNameKana;

	/**
	 * MoM非連携_担当者電話番号
	 */
	@ApiModelProperty(value = "MoM非連携_担当者電話番号", required = false, position = 5)
	private String picPhoneNumber;

	/**
	 * MoM非連携_担当者電話番号
	 */
	@ApiModelProperty(value = "MoM非連携_担当者メールアドレス", required = false, position = 5)
	private String picMailAddress;

	/**
	 * 申込日
	 */
	@ApiModelProperty(value = "申込日", required = false, position = 6)
	private String applicationDate;

	/**
	 * キャンセル可能フラグ
	 */
	@ApiModelProperty(value = "キャンセル可能フラグ", required = false, position = 7)
	private String cancellationFlg;

	/**
	 * キャンセル可能期日
	 */
	@ApiModelProperty(value = "キャンセル可能期日", required = false, position = 8)
	private String cancellationDate;

	/**
	 * 契約明細情報リスト
	 */
	@ApiModelProperty(value = "契約明細情報リスト", required = false, position = 9)
	private List<O365LatestContractDetailDto> contractDetailList;

}
