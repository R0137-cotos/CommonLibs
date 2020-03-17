package jp.co.ricoh.cotos.commonlib.repository.common;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.common.EimLinkageManagementInfo;

@Repository
public interface EimLinkageManagementInfoRepository extends CrudRepository<EimLinkageManagementInfo, Long> {

}
