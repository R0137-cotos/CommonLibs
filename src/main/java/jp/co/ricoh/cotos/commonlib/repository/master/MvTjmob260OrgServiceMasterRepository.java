package jp.co.ricoh.cotos.commonlib.repository.master;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.master.MvTjmob260OrgServiceMaster;

@Repository
public interface MvTjmob260OrgServiceMasterRepository extends JpaRepository<MvTjmob260OrgServiceMaster, String> {

	public List<MvTjmob260OrgServiceMaster> findByOrsServiceOrgId(String orsServiceOrgId);
}