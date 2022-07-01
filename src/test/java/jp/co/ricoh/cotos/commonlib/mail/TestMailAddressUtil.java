package jp.co.ricoh.cotos.commonlib.mail;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.ricoh.cotos.commonlib.DBConfig;
import jp.co.ricoh.cotos.commonlib.dto.result.MailInfoDto;
import jp.co.ricoh.cotos.commonlib.entity.arrangement.ArrangementWork;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract;
import jp.co.ricoh.cotos.commonlib.entity.estimation.Estimation;
import jp.co.ricoh.cotos.commonlib.entity.license.LicenseInfo;
import jp.co.ricoh.cotos.commonlib.entity.master.MailAddressMaster.ServiceCategory;
import jp.co.ricoh.cotos.commonlib.entity.master.MailMaster;
import jp.co.ricoh.cotos.commonlib.exception.ErrorCheckException;
import jp.co.ricoh.cotos.commonlib.exception.ErrorInfo;
import jp.co.ricoh.cotos.commonlib.logic.mail.MailAddressUtil;
import jp.co.ricoh.cotos.commonlib.repository.arrangement.ArrangementWorkRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.ContractRepository;
import jp.co.ricoh.cotos.commonlib.repository.estimation.EstimationRepository;
import jp.co.ricoh.cotos.commonlib.repository.license.LicenseInfoRepository;
import jp.co.ricoh.cotos.commonlib.repository.master.MailMasterRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMailAddressUtil {

	static ConfigurableApplicationContext context;

	@Autowired
	MailAddressUtil mailAddressUtil;

	@Autowired
	MailMasterRepository mailMasterRepository;

	@Autowired
	EstimationRepository estimationRepository;

	@Autowired
	ContractRepository contractRepository;

	@Autowired
	ArrangementWorkRepository arrangementWorkRepository;

	@Autowired
	LicenseInfoRepository licenseInfoRepository;

	public final static String AUDIT_TRAIL_MAIL_ADDRESS = "customer_send_history@cotos.ricoh.co.jp";

	@Autowired
	public void injectContext(ConfigurableApplicationContext injectContext) {
		context = injectContext;
		context.getBean(DBConfig.class).clearData();
		context.getBean(DBConfig.class).initTargetTestData("mail/mailAddress.sql");
		context.getBean(DBConfig.class).initTargetTestData("mail/estimation.sql");
		context.getBean(DBConfig.class).initTargetTestData("mail/contract.sql");
		context.getBean(DBConfig.class).initTargetTestData("mail/arrangement.sql");
		context.getBean(DBConfig.class).initTargetTestData("mail/licenseInfo.sql");
	}

	@AfterClass
	public static void stopAPServer() throws InterruptedException {
		if (null != context) {
			context.getBean(DBConfig.class).clearData();
			context.stop();
		}
	}

	@Test
	public void 正常系() {
		// 各ドメインのID
		long estimationId = 100L;
		long contractId = 100000000001L;
		long arrangementWorkId = 401L;
		long licenseInfoId = 1L;
		long mailMasterId = 1L;
		// HashMap
		HashMap<ServiceCategory, Long> map = new HashMap<>();
		// 値を設定
		map.put(ServiceCategory.見積, estimationId);
		map.put(ServiceCategory.契約, contractId);
		map.put(ServiceCategory.手配, arrangementWorkId);
		map.put(ServiceCategory.ライセンス, licenseInfoId);
		// メール情報DTOを取得
		MailInfoDto mailInfoDto = mailAddressUtil.getMailInfo(map, mailMasterId);
		// チェック用メールマスタ取得
		MailMaster mailMaster = mailMasterRepository.findOne(mailMasterId);
		Estimation estimation = estimationRepository.findOne(estimationId);
		Contract contract = contractRepository.findOne(contractId);
		ArrangementWork arrangementWork = arrangementWorkRepository.findOne(arrangementWorkId);
		LicenseInfo licenseInfo = licenseInfoRepository.findOne(licenseInfoId);
		// チェック
		Assert.assertNotNull("メール情報DTOが取得できること", mailInfoDto);
		Assert.assertEquals("メールテンプレートマスタIDが一致すること", mailMaster.getMailTemplateMasterId(), (Long) mailInfoDto.getMailTemplateMasterId());
		Assert.assertEquals("メールタイプ区分が一致すること", mailMaster.getMailTypeDiv(), mailInfoDto.getMailTypeDiv());
		Assert.assertEquals("TOメールアドレスが一致すること", estimation.getCustomerEstimation().getPicMailAddress(), mailInfoDto.getToMailAddressList().get(0));
		Assert.assertEquals("CCメールアドレスの１件目が一致すること", contract.getContractPicSaEmp().getMailAddress(), mailInfoDto.getCcMailAddressList().get(0));
		Assert.assertEquals("CCメールアドレスの２件目が一致すること", arrangementWork.getArrangementPicWorkerEmp().getMailAddress(), mailInfoDto.getCcMailAddressList().get(1));
		Assert.assertEquals("BCCメールアドレスの１件目が一致すること", licenseInfo.getMailAddress(), mailInfoDto.getBccMailAddressList().get(0));
		Assert.assertEquals("BCCメールアドレスの２件目が一致すること", AUDIT_TRAIL_MAIL_ADDRESS, mailInfoDto.getBccMailAddressList().get(1));
	}

	@Test
	public void 異常系_引数なし() {
		// 引数にNullを設定した場合
		try {
			// メール情報DTOを取得
			mailAddressUtil.getMailInfo(null, 999L);
			fail("正常終了した");
		} catch (ErrorCheckException e) {
			List<ErrorInfo> errorList = e.getErrorInfoList();
			// チェック
			Assert.assertEquals("エラー件数が一致すること", 1, errorList.size());
			Assert.assertEquals("エラーIDが一致すること", "ROT00013", e.getErrorInfoList().get(0).getErrorId());
			Assert.assertEquals("エラーメッセージが一致すること", "各ドメインの対象テーブルIDマップが設定されていません。", e.getErrorInfoList().get(0).getErrorMessage());
		}
		// 引数にNweしたHashMapを設定した場合
		try {
			// メール情報DTOを取得
			mailAddressUtil.getMailInfo(new HashMap<ServiceCategory, Long>(), 999L);
			fail("正常終了した");
		} catch (ErrorCheckException e) {
			List<ErrorInfo> errorList = e.getErrorInfoList();
			// チェック
			Assert.assertEquals("エラー件数が一致すること", 1, errorList.size());
			Assert.assertEquals("エラーIDが一致すること", "ROT00013", e.getErrorInfoList().get(0).getErrorId());
			Assert.assertEquals("エラーメッセージが一致すること", "各ドメインの対象テーブルIDマップが設定されていません。", e.getErrorInfoList().get(0).getErrorMessage());
		}
	}

	@Test
	public void 異常系_メールマスタなし() {
		// HashMap
		HashMap<ServiceCategory, Long> map = new HashMap<>();
		// 値を設定
		map.put(ServiceCategory.見積, 1L);

		try {
			// メール情報DTOを取得
			mailAddressUtil.getMailInfo(map, 999L);
			fail("正常終了した");
		} catch (ErrorCheckException e) {
			List<ErrorInfo> errorList = e.getErrorInfoList();
			// チェック
			Assert.assertEquals("エラー件数が一致すること", 1, errorList.size());
			Assert.assertEquals("エラーIDが一致すること", "ROT00016", e.getErrorInfoList().get(0).getErrorId());
			Assert.assertEquals("エラーメッセージが一致すること", "メールマスタにデータが存在しません。", e.getErrorInfoList().get(0).getErrorMessage());
		}
	}

	@Test
	public void 異常系_メールアドレスマスタなし() {
		// HashMap
		HashMap<ServiceCategory, Long> map = new HashMap<>();
		// 値を設定
		map.put(ServiceCategory.見積, 1L);

		try {
			// メール情報DTOを取得
			mailAddressUtil.getMailInfo(map, 900L);
			fail("正常終了した");
		} catch (ErrorCheckException e) {
			List<ErrorInfo> errorList = e.getErrorInfoList();
			// チェック
			Assert.assertEquals("エラー件数が一致すること", 1, errorList.size());
			Assert.assertEquals("エラーIDが一致すること", "ROT00016", e.getErrorInfoList().get(0).getErrorId());
			Assert.assertEquals("エラーメッセージが一致すること", "メールアドレスマスタにデータが存在しません。", e.getErrorInfoList().get(0).getErrorMessage());
		}
	}

	@Test
	public void 正常系_対象エンティティが存在しない場合() {
		// 各ドメインのID
		long estimationId = 100L;
		long contractId = 100000000001L;
		long arrangementWorkId = 401L;
		long licenseInfoId = 1L;
		long mailMasterId = 100L;
		// HashMap
		HashMap<ServiceCategory, Long> map = new HashMap<>();
		// 値を設定
		map.put(ServiceCategory.見積, estimationId);
		map.put(ServiceCategory.契約, contractId);
		map.put(ServiceCategory.手配, arrangementWorkId);
		map.put(ServiceCategory.ライセンス, licenseInfoId);

		// メール情報DTOを取得
		MailInfoDto mailInfoDto = mailAddressUtil.getMailInfo(map, mailMasterId);
		// チェック用メールマスタ取得
		MailMaster mailMaster = mailMasterRepository.findOne(mailMasterId);
		Contract contract = contractRepository.findOne(contractId);
		ArrangementWork arrangementWork = arrangementWorkRepository.findOne(arrangementWorkId);
		LicenseInfo licenseInfo = licenseInfoRepository.findOne(licenseInfoId);
		// チェック
		Assert.assertEquals("メールテンプレートマスタIDが一致すること", mailMaster.getMailTemplateMasterId(), (Long) mailInfoDto.getMailTemplateMasterId());
		Assert.assertEquals("メールタイプ区分が一致すること", mailMaster.getMailTypeDiv(), mailInfoDto.getMailTypeDiv());
		Assert.assertEquals("TOメールアドレスが１件であること（２件の内、１件はエンティティが存在しない）", 1, mailInfoDto.getToMailAddressList().size());
		Assert.assertEquals("CCメールアドレスが１件であること（２件の内、１件はエンティティが存在しない）", 1, mailInfoDto.getCcMailAddressList().size());
		Assert.assertEquals("BCCメールアドレスが２件であること（３件の内、１件はエンティティが存在しない）", 2, mailInfoDto.getBccMailAddressList().size());
		Assert.assertEquals("TOメールアドレスの１件目が一致すること", licenseInfo.getMailAddress(), mailInfoDto.getToMailAddressList().get(0));
		Assert.assertEquals("CCメールアドレスの１件目が一致すること", arrangementWork.getArrangementPicWorkerEmp().getMailAddress(), mailInfoDto.getCcMailAddressList().get(0));
		Assert.assertEquals("BCCメールアドレスの１件目が一致すること", contract.getContractPicSaEmp().getMailAddress(), mailInfoDto.getBccMailAddressList().get(0));
		Assert.assertEquals("BCCメールアドレスの２件目が一致すること", AUDIT_TRAIL_MAIL_ADDRESS, mailInfoDto.getBccMailAddressList().get(1));
	}

	@Test
	public void 異常系_対象フィールドが存在しない場合() {
		// 各ドメインのID
		long arrangementWorkId = 401L;
		long mailMasterId = 200L;
		// HashMap
		HashMap<ServiceCategory, Long> map = new HashMap<>();
		// 値を設定
		map.put(ServiceCategory.手配, arrangementWorkId);
		try {
			// メール情報DTOを取得
			mailAddressUtil.getMailInfo(map, mailMasterId);
		} catch (ErrorCheckException e) {
			List<ErrorInfo> errorList = e.getErrorInfoList();
			// チェック
			Assert.assertEquals("エラー件数が一致すること", 1, errorList.size());
			Assert.assertEquals("エラーIDが一致すること", "ROT00003", e.getErrorInfoList().get(0).getErrorId());
			Assert.assertEquals("エラーメッセージが一致すること", "対象フィールドが特定できません。", e.getErrorInfoList().get(0).getErrorMessage());
		}
	}
}