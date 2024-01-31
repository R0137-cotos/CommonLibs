package jp.co.ricoh.cotos.commonlib.repository.externallinkage;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.externallinkage.ConstructionEimApplyInfo;
import jp.co.ricoh.cotos.commonlib.entity.externallinkage.ConstructionEimApplyInfo.OrderType;
import jp.co.ricoh.cotos.commonlib.entity.externallinkage.ConstructionEimApplyInfo.Status;

@Repository
public interface ConstructionEimApplyInfoRepository extends CrudRepository<ConstructionEimApplyInfo, Long> {

	public List<ConstructionEimApplyInfo> findByContractIdAndDisengagementFlg(long contractId, int disengagementFlg);

	public List<ConstructionEimApplyInfo> findByEstimationIdAndDisengagementFlg(long estimationId, int disengagementFlg);

	public List<ConstructionEimApplyInfo> findByContractIdAndStatusAndOrderTypeNotIn(long contractId, Status status, List<OrderType> orderTypeList);

	public List<ConstructionEimApplyInfo> findByEstimationIdAndStatusAndOrderTypeNotIn(long estimationId, Status status, List<OrderType> orderTypeList);

	public List<ConstructionEimApplyInfo> findByContractId(long contractId);

	public List<ConstructionEimApplyInfo> findByEstimationId(long estimationId);

	public List<ConstructionEimApplyInfo> findByEstimationIdAndDisengagementFlgAndStatus(long estimaionId, int disengagementFlg, Status status);
}
