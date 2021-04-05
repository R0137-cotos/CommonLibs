package jp.co.ricoh.cotos.commonlib.repository.license.cas.tm;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.license.cas.tm.AbstractTmResponseWork.TmLicenceMappedStatus;
import jp.co.ricoh.cotos.commonlib.entity.license.cas.tm.TmUpdateUserResponseWork;

@Repository
public interface TmUpdateUserResponseWorkRepository extends CrudRepository<TmUpdateUserResponseWork, Long> {

	public List<TmUpdateUserResponseWork> findByLicenceMappedStatus(TmLicenceMappedStatus licenceMappedStatus);

	public List<TmUpdateUserResponseWork> findByLicenceMappedStatusAndIdBetween(TmLicenceMappedStatus licenceMappedStatus, long from, long to);
}
