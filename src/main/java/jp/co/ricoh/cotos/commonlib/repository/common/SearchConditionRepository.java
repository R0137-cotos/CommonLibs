package jp.co.ricoh.cotos.commonlib.repository.common;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.common.SearchCondition;

@Repository
public interface SearchConditionRepository extends CrudRepository<SearchCondition, Long> {
	public List<SearchCondition> findByMomEmployeeIdAndDomain(String momEmployeeId, String domain);
}
