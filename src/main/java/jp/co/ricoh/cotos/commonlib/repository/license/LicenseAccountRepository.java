package jp.co.ricoh.cotos.commonlib.repository.license;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.license.LicenseAccount;

@Repository
public interface LicenseAccountRepository extends CrudRepository<LicenseAccount, Long> {

	public List<LicenseAccount> findByCustomerId(String customerId);

	public List<LicenseAccount> findByAccount(String account);
}
