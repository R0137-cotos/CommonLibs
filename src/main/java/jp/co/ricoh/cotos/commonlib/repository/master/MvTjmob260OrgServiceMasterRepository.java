package jp.co.ricoh.cotos.commonlib.repository.master;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.master.MvTjmob260OrgServiceMaster;
import jp.co.ricoh.cotos.commonlib.entity.master.MvTjmob260OrgServiceMaster.Id;

@Repository
public interface MvTjmob260OrgServiceMasterRepository extends CrudRepository<MvTjmob260OrgServiceMaster, Id> {

	public List<MvTjmob260OrgServiceMaster> findByIdOrsCubicOrgId(String orsCubicOrgId);

	public List<MvTjmob260OrgServiceMaster> findByOrsServiceOrgId(String orsServiceOrgId);
}
