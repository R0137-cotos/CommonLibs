package jp.co.ricoh.cotos.commonlib.repository.master;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.master.MvRjShohinInfoMaster;

@Repository
public interface MvRjShohinInfoMasterRepository extends JpaRepository<MvRjShohinInfoMaster, String> {

}
