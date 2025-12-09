package jp.co.ricoh.cotos.commonlib.repository.accounting;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.accounting.OsoRequestPlanData;

@Repository
public interface OsoRequestPlanDataRepository extends CrudRepository<OsoRequestPlanData, Long> {

}
