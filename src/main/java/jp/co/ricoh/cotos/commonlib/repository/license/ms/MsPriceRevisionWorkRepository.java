package jp.co.ricoh.cotos.commonlib.repository.license.ms;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.license.ms.MsPriceRevisionWork;

@Repository
public interface MsPriceRevisionWorkRepository extends CrudRepository<MsPriceRevisionWork, Long> {

	public List<MsPriceRevisionWork> findByRjManageNumber(String rjManageNumber);
}
