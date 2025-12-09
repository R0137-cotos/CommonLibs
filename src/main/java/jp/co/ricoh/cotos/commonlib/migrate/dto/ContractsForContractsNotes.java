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
	private BasicContentsDto basicContentsDto;

	/**
	 * 契約情報
	 */
	private List<ContractInfoDto> contractInfoDtoList;

	/**
	 * 契約者情報
	 */
	private List<ContractorInfoDto> contractorInfoDtoList;

	/**
	 *
	 */
	private List<CustomItemDto> customItemDtoList;

	/**
	 *
	 */
	private List<SetupInfoDto> setupInfoDtoList;

	/**
	 * 契約担当者情報
	 */
	private List<BranchCustomerInfo> branchCustoemrInfoDtoList;

	/**
	 * 販売店情報
	 */
	private List<DistributorInfo> distributorInfoDtoList;

	/**
	 * 母店情報
	 */
	private List<ParentDistributorInfo> parentDistributorInfoDtoList;
}
