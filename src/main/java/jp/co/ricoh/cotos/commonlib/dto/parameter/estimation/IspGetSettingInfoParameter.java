package jp.co.ricoh.cotos.commonlib.dto.parameter.estimation;

import lombok.Data;

@Data
public class IspGetSettingInfoParameter {

	/**
	 * RJ管理番号
	 */
	private String rjManageNumber;

	/**
	 * 契約番号
	 */
	private String contractNumber;

	/**
	 * 契約番号枝番
	 */
	private int contractBranchNumber;

	/**
	 * 全体ステータス（０なら契約中（処理済）のみ、１なら全件　ステータス関係なく）
	 */
	private int wholeStatus;
}
