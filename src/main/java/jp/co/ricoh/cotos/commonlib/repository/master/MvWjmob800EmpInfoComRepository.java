package jp.co.ricoh.cotos.commonlib.repository.master;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.master.MvWjmob800EmpInfoCom;

@Repository
public interface MvWjmob800EmpInfoComRepository extends CrudRepository<MvWjmob800EmpInfoCom, String> {

	public List<MvWjmob800EmpInfoCom> findByRingsHanshCdAndRingsEmpCd(String RingsHanshCd, String RingsEmpCd);

	public List<MvWjmob800EmpInfoCom> findByEmail(String email);

}
