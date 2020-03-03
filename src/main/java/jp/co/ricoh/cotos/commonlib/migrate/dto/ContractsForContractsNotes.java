package jp.co.ricoh.cotos.commonlib.migrate.dto;

import java.util.List;

import jp.co.ricoh.cotos.commonlib.migrate.entity.BranchCustomerInfo;
import jp.co.ricoh.cotos.commonlib.migrate.entity.DistributorInfo;
import jp.co.ricoh.cotos.commonlib.migrate.entity.ParentDistributorInfo;
import lombok.Data;

/**
 * 中継モジュールからのJsonマッピング用DTO
 *
 */

@Data
public class ContractsForContractsNotes {

	/**
	 * 基本情報
	 */
	private BasicContentsDto basicContents;

	/**
	 * 契約情報
	 */
	private List<ContractInfoDto> contractInfo;

	/**
	 * 契約者情報
	 */
	private List<ContractorInfoDto> contractorInfo;

	/**
	 * カスタムアイテム
	 */
	//	private List<CustomItemDto> customItem;

	/**
	 * セットアップ情報
	 */
	private List<SetupInfoDto> setupInfo;

	/**
	 * 契約担当者情報
	 */
	private List<BranchCustomerInfo> branchCustoemrInfo;

	/**
	 * 販売店情報
	 */
	private List<DistributorInfo> distributorInfo;

	/**
	 * 母店情報
	 */
	private List<ParentDistributorInfo> parentDistributorInfo;
}
