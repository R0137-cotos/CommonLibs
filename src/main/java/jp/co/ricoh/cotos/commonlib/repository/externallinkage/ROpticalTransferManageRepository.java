package jp.co.ricoh.cotos.commonlib.repository.externallinkage;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.externallinkage.ROpticalTransferManage;

@Repository
public interface ROpticalTransferManageRepository extends CrudRepository<ROpticalTransferManage, Long> {

	public List<ROpticalTransferManage> findByEstimationIdAndDisengagementFlg(Long estimationId, Integer disengagementFlg);
}
