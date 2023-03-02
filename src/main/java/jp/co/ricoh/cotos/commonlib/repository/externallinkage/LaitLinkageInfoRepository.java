package jp.co.ricoh.cotos.commonlib.repository.externallinkage;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.externallinkage.LaitLinkageInfo;
import jp.co.ricoh.cotos.commonlib.entity.externallinkage.LaitLinkageInfo.InfoDiv;
import jp.co.ricoh.cotos.commonlib.entity.externallinkage.LaitLinkageInfo.RouterApDiv;

@Repository
public interface LaitLinkageInfoRepository extends CrudRepository<LaitLinkageInfo, Long> {

	public List<LaitLinkageInfo> findBySaLabel(String saLabel);

	public List<LaitLinkageInfo> findBySaCode(String saCode);

	public List<LaitLinkageInfo> findByContractId(long contractId);

	public List<LaitLinkageInfo> findByRjManageNumberAndDeleteFlgAndInfoDivIn(String rjManageNumber, Integer deleteFlg, List<InfoDiv> infoDivList);

	public List<LaitLinkageInfo> findByBaseRjManageNumberAndDeleteFlgAndInfoDivIn(String baseRjManageNumber, Integer deleteFlg, List<InfoDiv> infoDivList);

	public LaitLinkageInfo findByRjManageNumberAndInfoDivAndRouterApDivAndLaitId(String rjManageNumber, InfoDiv infoDiv, RouterApDiv routerApDiv, String laitId);

	public LaitLinkageInfo findByRjManageNumberAndRouterApDivAndDeleteFlgAndInfoDivIn(String rjManageNumber, RouterApDiv routerApDiv, Integer deleteFlg, List<InfoDiv> infoDivList);
}
