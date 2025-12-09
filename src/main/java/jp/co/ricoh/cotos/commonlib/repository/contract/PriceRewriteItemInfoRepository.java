package jp.co.ricoh.cotos.commonlib.repository.contract;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.contract.PriceRewriteItemInfo;
import jp.co.ricoh.cotos.commonlib.entity.contract.PriceRewriteItemInfo.Status;

@Repository
public interface PriceRewriteItemInfoRepository extends CrudRepository<PriceRewriteItemInfo, Long> {

	/**
	 * ステータスから取得
	 */
	public List<PriceRewriteItemInfo> findByStatus(Status status);
	
	/**
	 * 品種コードから取得
	 */
	public List<PriceRewriteItemInfo> findByRicohItemCode(String code);

	/**
	 * ファイル取込管理IDから取得
	 */
	public List<PriceRewriteItemInfo> findByFileImportManagement_Id(Long fileImportManagementId);
}
