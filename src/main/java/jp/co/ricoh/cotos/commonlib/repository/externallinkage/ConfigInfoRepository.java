package jp.co.ricoh.cotos.commonlib.repository.externallinkage;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.externallinkage.ConfigInfo;

@Repository
public interface ConfigInfoRepository extends CrudRepository<ConfigInfo, Long> {

	public List<ConfigInfo> findByContractId(long contractId);

}
