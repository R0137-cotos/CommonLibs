package jp.co.ricoh.cotos.commonlib.repository.common;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.common.EimDocumentInfo;

@Repository
public interface EimDocumentInfoRepository extends CrudRepository<EimDocumentInfo, Long> {

}
