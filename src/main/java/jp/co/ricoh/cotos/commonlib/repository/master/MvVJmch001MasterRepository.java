package jp.co.ricoh.cotos.commonlib.repository.master;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.master.MvVJmch001Master;

@Repository
public interface MvVJmch001MasterRepository extends CrudRepository<MvVJmch001Master, String> {

}
