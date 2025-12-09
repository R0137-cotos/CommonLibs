package jp.co.ricoh.cotos.commonlib.migrate.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.migrate.entity.ParentDistributorInfo;

@Repository
public interface ParentDistributorInfoRepository extends CrudRepository<ParentDistributorInfo, Long> {

}
