package jp.co.ricoh.cotos.commonlib.repository.contract.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.contract.order.BplatsRicohItemCodeConvertInfo;
@Repository
public interface BplatsRicohItemCodeConvertInfoRepository extends JpaRepository<BplatsRicohItemCodeConvertInfo, Long> {

	public List<BplatsRicohItemCodeConvertInfo> findByRjManageNumber(String rjManageNumber);
}