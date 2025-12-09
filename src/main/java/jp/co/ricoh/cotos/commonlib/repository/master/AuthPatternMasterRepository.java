package jp.co.ricoh.cotos.commonlib.repository.master;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.master.AuthPatternMaster;
import jp.co.ricoh.cotos.commonlib.entity.master.UrlAuthMaster.ActionDiv;
import jp.co.ricoh.cotos.commonlib.entity.master.UrlAuthMaster.AuthDiv;

@Repository
public interface AuthPatternMasterRepository extends CrudRepository<AuthPatternMaster, Long> {
	public List<AuthPatternMaster> findByActionDivAndAuthDivIn(ActionDiv actionDiv, List<AuthDiv> authDivList);
}
