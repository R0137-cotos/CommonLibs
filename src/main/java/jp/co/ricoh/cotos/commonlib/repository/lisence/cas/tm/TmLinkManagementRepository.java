package jp.co.ricoh.cotos.commonlib.repository.lisence.cas.tm;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.license.cas.tm.TmLinkManagement;

@Repository
public interface TmLinkManagementRepository extends CrudRepository<TmLinkManagement, Long> {
	public List<TmLinkManagement> findByIdBetween(long from, long to);
}
