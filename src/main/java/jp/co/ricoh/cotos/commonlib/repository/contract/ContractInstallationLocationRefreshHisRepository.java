package jp.co.ricoh.cotos.commonlib.repository.contract;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.contract.ContractInstallationLocationRefreshHis;

@Repository
public interface ContractInstallationLocationRefreshHisRepository extends CrudRepository<ContractInstallationLocationRefreshHis, Long> {

	public ContractInstallationLocationRefreshHis findByContractId(Long id);
}
