package jp.co.ricoh.cotos.commonlib.dto.result;

import java.util.Date;

import jp.co.ricoh.cotos.commonlib.entity.master.ItemMaster;
import lombok.Data;

/**
 * 違約金チェック結果を格納するDTO
 */
@Data
public class PenaltyCheckResultDTO {

	/**
	 * 品種マスタ
	 */
	private ItemMaster itemMaster;

	/**
	 * 違約金発生解約最終日
	 */
	private Date penaltyOccurCacnlLastDate;
}
