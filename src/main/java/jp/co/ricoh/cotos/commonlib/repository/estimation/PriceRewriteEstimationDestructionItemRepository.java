package jp.co.ricoh.cotos.commonlib.repository.estimation;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.estimation.PriceRewriteEstimationDestructionItem;
import jp.co.ricoh.cotos.commonlib.entity.estimation.PriceRewriteEstimationDestructionItem.Status;

@Repository
public interface PriceRewriteEstimationDestructionItemRepository extends CrudRepository<PriceRewriteEstimationDestructionItem, Long> {

	/**
	 * ステータスから取得
	 */
	public List<PriceRewriteEstimationDestructionItem> findByStatus(Status status);
}
