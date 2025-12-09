package jp.co.ricoh.cotos.commonlib.migrate.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.migrate.entity.DistributorInfo;

@Repository
public interface DistributorInfoRepository extends CrudRepository<DistributorInfo, Long> {

}
