package jp.co.ricoh.cotos.commonlib.repository.contract;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.contract.CollectLocation;

@Repository
public interface CollectLocationRepository extends CrudRepository<CollectLocation, Long> {

	public List<CollectLocation> findByContractId(long contractId);

}
