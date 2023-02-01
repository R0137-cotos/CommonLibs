package jp.co.ricoh.cotos.commonlib.repository.externallinkage;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.externallinkage.ConstructionEimItemInfo;

@Repository
public interface ConstructionEimItemInfoRepository extends CrudRepository<ConstructionEimItemInfo, Long> {

}
