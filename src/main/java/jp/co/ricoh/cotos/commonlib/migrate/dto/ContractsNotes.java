package jp.co.ricoh.cotos.commonlib.migrate.dto;

import java.util.List;

import lombok.Data;

/**
 * 中継モジュールからのJsonマッピング用DTO
 *
 */

@Data
public class ContractsNotes {

	/**
	 * 契約情報リスト
	 */
	private List<ContractsForContractsNotes> ContractsForContractsNotesList;

}
