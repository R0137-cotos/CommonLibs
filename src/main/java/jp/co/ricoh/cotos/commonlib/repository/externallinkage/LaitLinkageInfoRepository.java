package jp.co.ricoh.cotos.commonlib.repository.externallinkage;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.externallinkage.LaitLinkageInfo;

@Repository
public interface LaitLinkageInfoRepository extends CrudRepository<LaitLinkageInfo, Long> {

	public List<LaitLinkageInfo> findBySaLabel(String saLabel);

	public List<LaitLinkageInfo> findBySaCode(String saCode);

	public List<LaitLinkageInfo> findByContractId(long contractId);
}
