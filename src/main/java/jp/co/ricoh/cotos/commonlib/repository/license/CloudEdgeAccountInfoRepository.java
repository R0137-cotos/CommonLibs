package jp.co.ricoh.cotos.commonlib.repository.license;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.license.CloudEdgeAccountInfo;

@Repository
public interface CloudEdgeAccountInfoRepository extends CrudRepository<CloudEdgeAccountInfo, Long> {

}
