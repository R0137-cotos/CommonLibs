package jp.co.ricoh.cotos.commonlib.repository.externallinkage;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.externallinkage.ConstructionEimApplyInfo;

@Repository
public interface ConstructionEimApplyInfoRepository extends CrudRepository<ConstructionEimApplyInfo, Long> {

	public List<ConstructionEimApplyInfo> findByContractIdAndDisengagementFlg(long contractId, int disengagementFlg);
}
