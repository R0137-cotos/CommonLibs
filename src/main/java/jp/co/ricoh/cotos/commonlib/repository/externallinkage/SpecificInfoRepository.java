package jp.co.ricoh.cotos.commonlib.repository.externallinkage;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.externallinkage.SpecificInfo;

@Repository
public interface SpecificInfoRepository extends CrudRepository<SpecificInfo, Long> {

	public List<SpecificInfo> findByContractId(long contractId);

	public List<SpecificInfo> findBySpecificControlDivAndItemValue(String specificControlDiv, String itemValue);
}
