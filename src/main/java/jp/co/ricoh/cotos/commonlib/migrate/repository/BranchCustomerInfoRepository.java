package jp.co.ricoh.cotos.commonlib.migrate.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.migrate.entity.BranchCustomerInfo;

@Repository
public interface BranchCustomerInfoRepository extends CrudRepository<BranchCustomerInfo, Long> {

}
