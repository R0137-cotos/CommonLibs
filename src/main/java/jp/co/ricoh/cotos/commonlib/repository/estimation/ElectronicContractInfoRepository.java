package jp.co.ricoh.cotos.commonlib.repository.estimation;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.estimation.ElectronicContractInfo;

@Repository
public interface ElectronicContractInfoRepository extends CrudRepository<ElectronicContractInfo, Long> {
	public ElectronicContractInfo findByEstimationId(long estimationId);
}