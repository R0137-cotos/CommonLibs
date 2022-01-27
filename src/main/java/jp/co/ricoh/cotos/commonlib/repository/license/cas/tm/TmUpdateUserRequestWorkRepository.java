package jp.co.ricoh.cotos.commonlib.repository.license.cas.tm;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmUpdateUserRequestWork;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.AbstractTmRequestWork.TmRequestStatus;

@Repository
public interface TmUpdateUserRequestWorkRepository extends CrudRepository<TmUpdateUserRequestWork, Long> {

	public List<TmUpdateUserRequestWork> findByRequestStatus(TmRequestStatus requestStatus);

	public List<TmUpdateUserRequestWork> findByRequestStatusAndIdBetween(TmRequestStatus requestStatus, long from, long to);

}
