package jp.co.ricoh.cotos.commonlib.repository.externallinkage;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.externallinkage.McafeeLinkageInfo;

@Repository
public interface McafeeLinkageInfoRepository extends CrudRepository<McafeeLinkageInfo, Long> {

	public List<McafeeLinkageInfo> findByContractId(long contractId);
}
