package jp.co.ricoh.cotos.commonlib.repository.lisence.cas.tm;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.license.cas.tm.AbstractTmRequestWork.TmRequestStatus;
import jp.co.ricoh.cotos.commonlib.entity.license.cas.tm.TmUpdateUserRequestWork;

@Repository
public interface TmUpdateUserRequestWorkRepository extends CrudRepository<TmUpdateUserRequestWork, Long> {

	public List<TmUpdateUserRequestWork> findByRequestStatus(TmRequestStatus requestStatus);

	public List<TmUpdateUserRequestWork> findByRequestStatusAndIdBetween(TmRequestStatus requestStatus, long from, long to);

}
