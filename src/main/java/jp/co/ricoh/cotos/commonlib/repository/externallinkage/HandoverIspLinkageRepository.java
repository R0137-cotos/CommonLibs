package jp.co.ricoh.cotos.commonlib.repository.externallinkage;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.externallinkage.HandoverIspLinkage;

@Repository
public interface HandoverIspLinkageRepository extends CrudRepository<HandoverIspLinkage, Long> {

	public HandoverIspLinkage findByContractId(long contractId);

	public List<HandoverIspLinkage> findByMailHandoverContractId(long mailHandoverContractId);

	public List<HandoverIspLinkage> findByDomainHandoverContractId(long domainHandoverContractId);

	public List<HandoverIspLinkage> findByAccountHandoverContractId(long accountHandoverContractId);

	public List<HandoverIspLinkage> findByConnectHandoverContractId(long connectHandoverContractId);
}
