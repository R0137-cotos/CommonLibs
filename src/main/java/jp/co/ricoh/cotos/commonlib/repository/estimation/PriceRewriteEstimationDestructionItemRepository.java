package jp.co.ricoh.cotos.commonlib.repository.estimation;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.estimation.PriceRewriteEstimationDestructionItem;

@Repository
public interface PriceRewriteEstimationDestructionItemRepository extends CrudRepository<PriceRewriteEstimationDestructionItem, Long> {
}
