package jp.co.ricoh.cotos.commonlib.repository.contract;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.contract.NextUpdateDetailInfo;

@Repository
public interface NextUpdateDetailInfoRepository extends CrudRepository<NextUpdateDetailInfo, Long> {

	public List<NextUpdateDetailInfo> findByContractIdAndDeleteFlg(long contractId, Integer deleteFlg);
}