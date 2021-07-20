package jp.co.ricoh.cotos.commonlib.listener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.ricoh.cotos.commonlib.DBConfig;
import jp.co.ricoh.cotos.commonlib.WithMockCustomUser;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.DealerFlowOrder;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractInstallationLocation;
import jp.co.ricoh.cotos.commonlib.entity.contract.CustomerContract;
import jp.co.ricoh.cotos.commonlib.entity.contract.DealerContract;
import jp.co.ricoh.cotos.commonlib.entity.contract.ShippingAddress;
import jp.co.ricoh.cotos.commonlib.entity.estimation.CustomerEstimation;
import jp.co.ricoh.cotos.commonlib.entity.estimation.DealerEstimation;
import jp.co.ricoh.cotos.commonlib.entity.estimation.Estimation;
import jp.co.ricoh.cotos.commonlib.entity.master.DummyUserMaster;
import jp.co.ricoh.cotos.commonlib.entity.master.MvEmployeeMaster;
import jp.co.ricoh.cotos.commonlib.entity.master.VKjbMaster.DepartmentDiv;
import jp.co.ricoh.cotos.commonlib.exception.ErrorCheckException;
import jp.co.ricoh.cotos.commonlib.exception.ErrorInfo;
import jp.co.ricoh.cotos.commonlib.logic.check.CheckUtil;
import jp.co.ricoh.cotos.commonlib.repository.contract.ContractInstallationLocationRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.CustomerContractRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.DealerContractRepository;
import jp.co.ricoh.cotos.commonlib.repository.contract.ShippingAddressRepository;
import jp.co.ricoh.cotos.commonlib.repository.estimation.CustomerEstimationRepository;
import jp.co.ricoh.cotos.commonlib.repository.estimation.DealerEstimationRepository;
import jp.co.ricoh.cotos.commonlib.repository.estimation.EstimationRepository;
import jp.co.ricoh.cotos.commonlib.repository.master.DummyUserMasterRepository;
import jp.co.ricoh.cotos.commonlib.repository.master.MvEmployeeMasterRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TestListener {

	static ConfigurableApplicationContext context;

	@Autowired
	CustomerEstimationRepository customerEstimationRepository;

	@Autowired
	CustomerContractRepository customerContractRepository;

	@Autowired
	DealerEstimationRepository dealerEstimationRespository;

	@Autowired
	DealerContractRepository dealerContractRepository;

	@Autowired
	EstimationRepository estimationRepository;

	@Autowired
	ShippingAddressRepository shippingAddressRepository;

	@Autowired
	MvEmployeeMasterRepository mvEmployeeMasterRepository;

	@Autowired
	DummyUserMasterRepository dummyUserMasterRepository;

	@Autowired
	ContractInstallationLocationRepository contractInstallationLocationRepository;

	@Autowired
	CheckUtil checkUtil;

	// システム連携ID
	static final String MOM_KJB_SYSTEM_ID = "000000000433091";
	// 企事部ID(上記、システム連携IDのレコードが持つ企事部IDと同じ)
	static final String MOM_CUST_ID = "000000007309661";
	// システム連携ID(ダミー)
	static final String DUMMY_MOM_KJB_SYSTEM_ID = "dummyMomKjbSystemId";
	// 企事部ID(ダミー)
	static final String DUMMY_MOM_CUST_ID = "dummyMomCustId";

	@Autowired
	public void injectContext(ConfigurableApplicationContext injectContext) {
		context = injectContext;
		context.getBean(DBConfig.class).clearData();
		context.getBean(DBConfig.class).initTargetTestData("listener/listener.sql");
	}

	@AfterClass
	public static void stopAPServer() throws InterruptedException {
		if (null != context) {
			context.getBean(DBConfig.class).clearData();
			context.stop();
		}
	}

	@Test
	@WithMockCustomUser
	public void CustomerEstimationListenerのテスト() throws Exception {
		CustomerEstimation customerEstimation = new CustomerEstimation();
		customerEstimation.setMomKjbSystemId("000000000433091");
		Estimation estimation = new Estimation();
		estimation.setId(1L);
		customerEstimation.setEstimation(estimation);
		customerEstimationRepository.save(customerEstimation);
		customerEstimation = customerEstimationRepository.findOne(customerEstimation.getId());
		Assert.assertEquals("MoM企事部IDが正しく取得されること", "000000007309661", customerEstimation.getMomCustId());
		Assert.assertEquals("MoM企業IDが正しく取得されること", "000000000348689", customerEstimation.getCompanyId());
		Assert.assertEquals("MoM事業所IDが正しく取得されること", "000000000255394", customerEstimation.getOfficeId());
		Assert.assertEquals("企事部設定区分が正しく取得されること", DepartmentDiv.企事部, customerEstimation.getDepartmentDiv());
		Assert.assertEquals("顧客名が正しく取得されること", "株式会社ティーガイア＊", customerEstimation.getCustomerName());
		Assert.assertEquals("企業名が正しく取得されること", "株式会社ティーガイア", customerEstimation.getCompanyName());
		Assert.assertEquals("事業所名が正しく取得されること", "＊", customerEstimation.getOfficeName());
		Assert.assertEquals("部門名が正しく取得されること", null, customerEstimation.getDepartmentName());
		Assert.assertEquals("郵便番号が正しく取得されること", "1660002", customerEstimation.getPostNumber());
		Assert.assertEquals("住所が正しく取得されること", "東京都杉並区高円寺北２丁目２２－０６", customerEstimation.getAddress());
		Assert.assertEquals("電話番号が正しく取得されること", "0353273907", customerEstimation.getPhoneNumber());
		Assert.assertEquals("FAX番号が正しく取得されること", null, customerEstimation.getFaxNumber());

		customerEstimation.setCompanyName("有＊ト＊タルハウジング");
		customerEstimation.setOfficeName("＊＊＊");
		customerEstimation.setDepartmentName("＊＊");
		customerEstimationRepository.save(customerEstimation);
		customerEstimation = customerEstimationRepository.findOne(customerEstimation.getId());
		Assert.assertEquals("顧客名が正しく取得されること", "有＊ト＊タルハウジング＊＊＊＊＊", customerEstimation.getCustomerName());
		Assert.assertEquals("企業名が正しく取得されること", "有＊ト＊タルハウジング", customerEstimation.getCompanyName());
		Assert.assertEquals("事業所名が正しく取得されること", "＊＊＊", customerEstimation.getOfficeName());
		Assert.assertEquals("部門名が正しく取得されること", "＊＊", customerEstimation.getDepartmentName());
	}

	@Test
	@WithMockCustomUser
	public void CustomerEstimationListenerのテスト_企事部IDで企事部マスタを検索する場合() {
		CustomerEstimation customerEstimation = new CustomerEstimation();
		customerEstimation.setMomKjbSystemId(DUMMY_MOM_KJB_SYSTEM_ID);
		customerEstimation.setMomCustId(MOM_CUST_ID);
		Estimation estimation = new Estimation();
		estimation.setId(1L);
		customerEstimation.setEstimation(estimation);
		customerEstimationRepository.save(customerEstimation);
		customerEstimation = customerEstimationRepository.findOne(customerEstimation.getId());
		Assert.assertEquals("システム連携IDが正しく取得されること", MOM_KJB_SYSTEM_ID, customerEstimation.getMomKjbSystemId());
	}

	@Test
	@WithMockCustomUser
	public void 異常系_CustomerEstimationListenerのテスト_企事部マスタが存在しない場合() {
		CustomerEstimation customerEstimation = new CustomerEstimation();
		customerEstimation.setMomKjbSystemId("dummyMomKjbSystemId");
		customerEstimation.setMomCustId("dummyMomCustId");
		Estimation estimation = new Estimation();
		estimation.setId(1L);
		customerEstimation.setEstimation(estimation);
		try {
			customerEstimationRepository.save(customerEstimation);
			Assert.fail("正常に終了した");
		} catch (ErrorCheckException e) {
			List<ErrorInfo> messageInfo = e.getErrorInfoList();
			Assert.assertEquals(1, messageInfo.size());
			Assert.assertEquals(messageInfo.get(0).getErrorId(), "ROT00012");
			Assert.assertEquals(messageInfo.get(0).getErrorMessage(), "顧客（見積用）に存在しないMoM企事部システム連携IDが設定されています。");
		}
	}

	@Test
	@WithMockCustomUser
	public void CustomerContractListenerのテスト() throws Exception {
		CustomerContract customerContract = new CustomerContract();
		customerContract.setMomKjbSystemId("000000000433091");
		customerContract.setMomCustId("000000007309661");
		customerContract.setCompanyId("000000000348689");
		customerContract.setOfficeId("000000000255394");
		customerContract.setDepartmentDiv(DepartmentDiv.企事部);
		customerContract.setCustomerName("株式会社テ＊ー＊イア＊＊＊＊＊");
		customerContract.setCompanyName("株式会社テ＊ー＊イア");
		customerContract.setOfficeName("＊＊＊");
		customerContract.setDepartmentName("＊＊");
		Contract contract = new Contract();
		contract.setId(1L);
		customerContract.setContract(contract);
		customerContractRepository.save(customerContract);
		customerContract = customerContractRepository.findOne(customerContract.getId());
		Assert.assertEquals("MoM企事部IDが正しく取得されること", "000000007309661", customerContract.getMomCustId());
		Assert.assertEquals("MoM企業IDが正しく取得されること", "000000000348689", customerContract.getCompanyId());
		Assert.assertEquals("MoM事業所IDが正しく取得されること", "000000000255394", customerContract.getOfficeId());
		Assert.assertEquals("企事部設定区分が正しく取得されること", DepartmentDiv.企事部, customerContract.getDepartmentDiv());
		Assert.assertEquals("顧客名が正しく取得されること", "株式会社テ＊ー＊イア＊＊＊＊＊", customerContract.getCustomerName());
		Assert.assertEquals("企業名が正しく取得されること", "株式会社テ＊ー＊イア", customerContract.getCompanyName());
		Assert.assertEquals("事業所名が正しく取得されること", "＊＊＊", customerContract.getOfficeName());
		Assert.assertEquals("部門名が正しく取得されること", "＊＊", customerContract.getDepartmentName());

		customerContract.setCompanyName("有＊ト＊タルハウジング");
		customerContract.setOfficeName("＊＊＊");
		customerContract.setDepartmentName("＊＊");
		customerContractRepository.save(customerContract);
		customerContract = customerContractRepository.findOne(customerContract.getId());
		Assert.assertEquals("顧客名が正しく取得されること", "有＊ト＊タルハウジング＊＊＊＊＊", customerContract.getCustomerName());
		Assert.assertEquals("企業名が正しく取得されること", "有＊ト＊タルハウジング", customerContract.getCompanyName());
		Assert.assertEquals("事業所名が正しく取得されること", "＊＊＊", customerContract.getOfficeName());
		Assert.assertEquals("部門名が正しく取得されること", "＊＊", customerContract.getDepartmentName());
	}

	@Test
	@WithMockCustomUser
	public void 異常系_CustomerContractListenerのテスト_企事部マスタが存在しない場合() {
		CustomerContract customerContract = new CustomerContract();
		customerContract.setMomKjbSystemId(DUMMY_MOM_KJB_SYSTEM_ID);
		customerContract.setMomCustId(DUMMY_MOM_CUST_ID);
		Contract contract = new Contract();
		contract.setId(1L);
		customerContract.setContract(contract);
		try {
			customerContractRepository.save(customerContract);
			Assert.fail("正常に終了した");
		} catch (ErrorCheckException e) {
			List<ErrorInfo> messageInfo = e.getErrorInfoList();
			Assert.assertEquals(1, messageInfo.size());
			Assert.assertEquals(messageInfo.get(0).getErrorId(), "ROT00012");
			Assert.assertEquals(messageInfo.get(0).getErrorMessage(), "顧客（契約用）に存在しないMoM企事部システム連携IDが設定されています。");
		}
	}

	@Test
	@WithMockCustomUser
	public void DealerEstimationListenerのテスト() throws Exception {
		DealerEstimation dealerEstimation = new DealerEstimation();
		dealerEstimation.setMomKjbSystemId("000000000433091");
		dealerEstimation.setDealerFlowOrder(DealerFlowOrder.販売店);
		Estimation estimation = new Estimation();
		estimation.setId(1L);
		dealerEstimation.setEstimation(estimation);
		dealerEstimationRespository.save(dealerEstimation);
		dealerEstimation = dealerEstimationRespository.findOne(dealerEstimation.getId());
		Assert.assertEquals("販売店名が正しく取得されること", "株式会社ティーガイア", dealerEstimation.getDealerName());
		Assert.assertEquals("郵便番号が正しく取得されること", "1660002", dealerEstimation.getPostNumber());
		Assert.assertEquals("住所が正しく取得されること", "東京都杉並区高円寺北２丁目２２－０６", dealerEstimation.getAddress());
		Assert.assertEquals("電話番号が正しく取得されること", "0353273907", dealerEstimation.getOrgPhoneNumber());
		Assert.assertEquals("MoM会社IDが正しく取得されること", "999999", dealerEstimation.getDistributorMomCmpId());

		dealerEstimation.setDistributorMomCmpId(null);
		dealerEstimationRespository.save(dealerEstimation);
		dealerEstimation = dealerEstimationRespository.findOne(dealerEstimation.getId());
		Assert.assertEquals("MoM会社IDが正しく取得されること", "999999", dealerEstimation.getDistributorMomCmpId());
	}

	@Test
	@WithMockCustomUser
	public void DealerEstimationListenerのテスト_企事部IDで企事部マスタを検索する場合() {
		DealerEstimation dealerEstimation = new DealerEstimation();
		dealerEstimation.setMomKjbSystemId(DUMMY_MOM_KJB_SYSTEM_ID);
		dealerEstimation.setMomCustId(MOM_CUST_ID);
		dealerEstimation.setDealerFlowOrder(DealerFlowOrder.販売店);
		Estimation estimation = new Estimation();
		estimation.setId(1L);
		dealerEstimation.setEstimation(estimation);
		dealerEstimationRespository.save(dealerEstimation);
		dealerEstimation = dealerEstimationRespository.findOne(dealerEstimation.getId());
		Assert.assertEquals("システム連携IDが正しく取得されること", MOM_KJB_SYSTEM_ID, dealerEstimation.getMomKjbSystemId());
	}

	@Test
	@WithMockCustomUser
	public void 異常系_DealerEstimationListenerのテスト_企事部マスタが存在しない場合() {
		DealerEstimation dealerEstimation = new DealerEstimation();
		dealerEstimation.setMomKjbSystemId(DUMMY_MOM_KJB_SYSTEM_ID);
		dealerEstimation.setMomCustId(DUMMY_MOM_CUST_ID);
		dealerEstimation.setDealerFlowOrder(DealerFlowOrder.販売店);
		Estimation estimation = new Estimation();
		estimation.setId(1L);
		dealerEstimation.setEstimation(estimation);
		try {
			dealerEstimationRespository.save(dealerEstimation);
			Assert.fail("正常に終了した");
		} catch (ErrorCheckException e) {
			List<ErrorInfo> messageInfo = e.getErrorInfoList();
			Assert.assertEquals(1, messageInfo.size());
			Assert.assertEquals(messageInfo.get(0).getErrorId(), "ROT00012");
			Assert.assertEquals(messageInfo.get(0).getErrorMessage(), "販売店（見積用）に存在しないMoM企事部システム連携IDが設定されています。");
		}
	}

	@Test
	@WithMockCustomUser
	public void DealerContractListenerのテスト() throws Exception {
		DealerContract dealerContract = new DealerContract();
		dealerContract.setMomKjbSystemId("000000000433091");
		dealerContract.setDealerFlowOrder(DealerFlowOrder.販売店);
		Contract contract = new Contract();
		contract.setId(1L);
		dealerContract.setContract(contract);
		dealerContractRepository.save(dealerContract);
		dealerContract = dealerContractRepository.findOne(dealerContract.getId());
		Assert.assertEquals("販売店名が正しく取得されること", "株式会社ティーガイア", dealerContract.getDealerName());
		Assert.assertEquals("郵便番号が正しく取得されること", "1660002", dealerContract.getPostNumber());
		Assert.assertEquals("住所が正しく取得されること", "東京都杉並区高円寺北２丁目２２－０６", dealerContract.getAddress());
		Assert.assertEquals("電話番号が正しく取得されること", "0353273907", dealerContract.getOrgPhoneNumber());
		Assert.assertEquals("MoM会社IDが正しく取得されること", "999999", dealerContract.getDistributorMomCmpId());

		dealerContract.setDistributorMomCmpId(null);
		dealerContractRepository.save(dealerContract);
		dealerContract = dealerContractRepository.findOne(dealerContract.getId());
		Assert.assertEquals("MoM会社IDが正しく取得されること", "999999", dealerContract.getDistributorMomCmpId());
	}

	@Test
	@WithMockCustomUser
	public void DealerContractListenerのテスト_企事部IDで企事部マスタを検索する場合() {
		DealerContract dealerContract = new DealerContract();
		dealerContract.setMomKjbSystemId(DUMMY_MOM_KJB_SYSTEM_ID);
		dealerContract.setMomCustId(MOM_CUST_ID);
		dealerContract.setDealerFlowOrder(DealerFlowOrder.販売店);
		Contract contract = new Contract();
		contract.setId(1L);
		dealerContract.setContract(contract);
		dealerContractRepository.save(dealerContract);
		dealerContract = dealerContractRepository.findOne(dealerContract.getId());
		Assert.assertEquals("システム連携IDが正しく取得されること", MOM_KJB_SYSTEM_ID, dealerContract.getMomKjbSystemId());
	}

	@Test
	@WithMockCustomUser
	public void 異常系_DealerContractListenerのテスト_企事部マスタが存在しない場合() {
		DealerContract dealerContract = new DealerContract();
		dealerContract.setMomKjbSystemId(DUMMY_MOM_KJB_SYSTEM_ID);
		dealerContract.setMomCustId(DUMMY_MOM_CUST_ID);
		dealerContract.setDealerFlowOrder(DealerFlowOrder.販売店);
		Contract contract = new Contract();
		contract.setId(1L);
		dealerContract.setContract(contract);
		try {
			dealerContractRepository.save(dealerContract);
			Assert.fail("正常に終了した");
		} catch (ErrorCheckException e) {
			List<ErrorInfo> messageInfo = e.getErrorInfoList();
			Assert.assertEquals(1, messageInfo.size());
			Assert.assertEquals(messageInfo.get(0).getErrorId(), "ROT00012");
			Assert.assertEquals(messageInfo.get(0).getErrorMessage(), "販売店（契約用）に存在しないMoM企事部システム連携IDが設定されています。");
		}
	}

	@Test
	@WithMockCustomUser
	public void ContractInstallationLocationListenerのテスト() {
		ContractInstallationLocation contractInstallationLocation = new ContractInstallationLocation();
		contractInstallationLocation.setMomKjbSystemId(MOM_KJB_SYSTEM_ID);
		Contract contract = new Contract();
		contract.setId(1L);
		contractInstallationLocation.setContract(contract);
		contractInstallationLocationRepository.save(contractInstallationLocation);
		contractInstallationLocation = contractInstallationLocationRepository.findOne(contractInstallationLocation.getId());

		Assert.assertEquals("顧客名が正しく取得されること", "ティーガイア＊", contractInstallationLocation.getCustomerName());
		Assert.assertEquals("住所が正しく取得されること", "東京都渋谷区恵比寿４丁目１－１８　恵比寿ネオナート　１４Ｆ〜１８Ｆ", contractInstallationLocation.getAddress());
		Assert.assertEquals("電話番号が正しく取得されること", null, contractInstallationLocation.getPhoneNumber());
		Assert.assertEquals("FAX番号が正しく取得されること", null, contractInstallationLocation.getFaxNumber());
		Assert.assertEquals("企事部設定区分が正しく取得されること", DepartmentDiv.企事部, contractInstallationLocation.getDepartmentDiv());
		Assert.assertEquals("MoM企業IDが正しく取得されること", "000000000348689", contractInstallationLocation.getCompanyId());
		Assert.assertEquals("MoM事業所IDが正しく取得されること", "000000000255394", contractInstallationLocation.getOfficeId());
		Assert.assertEquals("事業所名が正しく取得されること", "＊", contractInstallationLocation.getOfficeName());
		Assert.assertEquals("MoM企事部IDが正しく取得されること", "000000007309661", contractInstallationLocation.getMomCustId());
		Assert.assertEquals("郵便番号が正しく取得されること", "1660002", contractInstallationLocation.getPostNumber());
		Assert.assertEquals("企業名が正しく取得されること", "ティーガイア", contractInstallationLocation.getCompanyName());
		Assert.assertEquals("部門名が正しく取得されること", null, contractInstallationLocation.getDepartmentName());
	}

	@Test
	@WithMockCustomUser
	public void ContractInstallationLocationListenerのテスト_企事部IDで企事部マスタを検索する場合() {
		ContractInstallationLocation contractInstallationLocation = new ContractInstallationLocation();
		contractInstallationLocation.setMomKjbSystemId(DUMMY_MOM_KJB_SYSTEM_ID);
		contractInstallationLocation.setMomCustId(MOM_CUST_ID);
		Contract contract = new Contract();
		contract.setId(1L);
		contractInstallationLocation.setContract(contract);
		contractInstallationLocationRepository.save(contractInstallationLocation);
		contractInstallationLocation = contractInstallationLocationRepository.findOne(contractInstallationLocation.getId());
		Assert.assertEquals("システム連携IDが正しく取得されること", MOM_KJB_SYSTEM_ID, contractInstallationLocation.getMomKjbSystemId());
	}

	@Test
	@WithMockCustomUser
	public void 異常系_ContractInstallationLocationListenerのテスト_企事部マスタが存在しない場合() {
		ContractInstallationLocation contractInstallationLocation = new ContractInstallationLocation();
		contractInstallationLocation.setMomKjbSystemId(DUMMY_MOM_KJB_SYSTEM_ID);
		contractInstallationLocation.setMomCustId(DUMMY_MOM_CUST_ID);
		Contract contract = new Contract();
		contract.setId(1L);
		contractInstallationLocation.setContract(contract);
		try {
			contractInstallationLocationRepository.save(contractInstallationLocation);
			Assert.fail("正常に終了した");
		} catch (ErrorCheckException e) {
			List<ErrorInfo> messageInfo = e.getErrorInfoList();
			Assert.assertEquals(1, messageInfo.size());
			Assert.assertEquals(messageInfo.get(0).getErrorId(), "ROT00012");
			Assert.assertEquals(messageInfo.get(0).getErrorMessage(), "設置先(契約用)に存在しないMoM企事部システム連携IDが設定されています。");
		}
	}

	@Test
	@WithMockCustomUser
	public void EstimationListenerのテスト_番号未付与() throws Exception {
		context.getBean(DBConfig.class).initTargetTestData("listener/sequence.sql");

		Estimation estimation = estimationRepository.findOne(1L);
		estimation.setId(0);
		estimation.setEstimationNumber(null);
		estimation.setEstimationBranchNumber(2);
		estimation.setProductGrpMasterId(1L);
		estimation.setRjManageNumber(null);
		estimationRepository.save(estimation);
		Estimation result = estimationRepository.findOne(estimation.getId());

		String expectEstimationNumber = "CE" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + "00001";
		Assert.assertEquals("見積番号が正しく取得されること", expectEstimationNumber, result.getEstimationNumber());
		Assert.assertEquals("RJ管理番号が正しく取得されること", "1230000001", result.getRjManageNumber());

		// シーケンスのDROPではIF EXISTSが使用できないため、ここで削除
		context.getBean(DBConfig.class).initTargetTestData("listener/clearSequence.sql");
	}

	@Test
	@WithMockCustomUser
	public void EstimationListenerのテスト_番号付与済() throws Exception {
		Estimation estimation = estimationRepository.findOne(1L);
		estimation.setId(0);
		estimation.setEstimationNumber("CE000000000001");
		estimation.setEstimationBranchNumber(2);
		estimation.setProductGrpMasterId(1L);
		estimation.setRjManageNumber("1230000001");
		estimationRepository.save(estimation);
		Estimation result = estimationRepository.findOne(estimation.getId());

		Assert.assertEquals("見積番号が正しく取得されること", "CE000000000001", result.getEstimationNumber());
		Assert.assertEquals("RJ管理番号が正しく取得されること", "1230000001", result.getRjManageNumber());
	}

	@Test
	@WithMockCustomUser
	public void ShippingAddressListenerのテスト() throws Exception {
		Contract contract = new Contract();

		// 新規登録時の対象項目を未設定にした場合のテスト
		contract.setId(1L);
		ShippingAddress shippingAddress = new ShippingAddress();
		shippingAddress.setMomEmployeeId("00445702");
		shippingAddress.setContract(contract);
		MvEmployeeMaster employeeMaster = mvEmployeeMasterRepository.findByMomEmployeeId(shippingAddress.getMomEmployeeId());
		shippingAddressRepository.save(shippingAddress);
		shippingAddress = shippingAddressRepository.findOne(shippingAddress.getId());
		Assert.assertEquals("所属組織名が正しく取得されること", employeeMaster.getOrgName(), shippingAddress.getOrgName());
		Assert.assertEquals("販売会社名が正しく取得されること", employeeMaster.getHanshSeiskNm(), shippingAddress.getSalesCompanyName());
		Assert.assertEquals("会社代表電話番号が正しく取得されること", employeeMaster.getOrgPhoneNumber(), shippingAddress.getOrgPhoneNumber());
		Assert.assertEquals("部署名が正しく取得されること", employeeMaster.getSalesDepartmentName(), shippingAddress.getSalesDepartmentName());
		Assert.assertEquals("社員名が正しく取得されること", employeeMaster.getJobname1() + employeeMaster.getJobname2(), shippingAddress.getEmployeeName());
		Assert.assertNull("郵便番号がNullであること", shippingAddress.getPostNumber());
		Assert.assertNull("電話番号がNullであること", shippingAddress.getPhoneNumber());
		Assert.assertNull("都道府県がNullであること", shippingAddress.getPrefectures());
		Assert.assertNull("市区町村番地がNullであること", shippingAddress.getCityStreet());
		Assert.assertNull("建物名がNullであること", shippingAddress.getBuildingName());

		// 新規登録時の対象項目を設定した場合のテスト
		contract.setId(2L);
		shippingAddress = new ShippingAddress();
		shippingAddress.setMomEmployeeId("00445702");
		shippingAddress.setOrgName("テスト 所属組織名");
		shippingAddress.setSalesCompanyName("テスト 販売会社名");
		shippingAddress.setOrgPhoneNumber("000-111-222");
		shippingAddress.setSalesDepartmentName("テスト 部署名");
		shippingAddress.setEmployeeName("テスト 社員名");
		shippingAddress.setContract(contract);
		shippingAddressRepository.save(shippingAddress);
		shippingAddress = shippingAddressRepository.findOne(shippingAddress.getId());
		Assert.assertEquals("所属組織名が正しく取得されること", "テスト 所属組織名", shippingAddress.getOrgName());
		Assert.assertEquals("販売会社名が正しく取得されること", "テスト 販売会社名", shippingAddress.getSalesCompanyName());
		Assert.assertEquals("会社代表電話番号が正しく取得されること", "000-111-222", shippingAddress.getOrgPhoneNumber());
		Assert.assertEquals("部署名が正しく取得されること", "テスト 部署名", shippingAddress.getSalesDepartmentName());
		Assert.assertEquals("社員名が正しく取得されること", "テスト 社員名", shippingAddress.getEmployeeName());
		Assert.assertNull("郵便番号がNullであること", shippingAddress.getPostNumber());
		Assert.assertNull("電話番号がNullであること", shippingAddress.getPhoneNumber());
		Assert.assertNull("都道府県がNullであること", shippingAddress.getPrefectures());
		Assert.assertNull("市区町村番地がNullであること", shippingAddress.getCityStreet());
		Assert.assertNull("建物名がNullであること", shippingAddress.getBuildingName());

		context.getBean(DBConfig.class).initTargetTestData("listener/shippingAddressListener.sql");

		//ダミー社員名を設定した場合のテスト
		contract.setId(3L);
		shippingAddress = new ShippingAddress();
		shippingAddress.setMomEmployeeId("COTOS_BATCH_USER");
		shippingAddress.setContract(contract);
		shippingAddressRepository.save(shippingAddress);
		DummyUserMaster dummyUserMaster = dummyUserMasterRepository.findByUserId(shippingAddress.getMomEmployeeId());
		shippingAddress = shippingAddressRepository.findOne(shippingAddress.getId());
		Assert.assertEquals("ダミー社員名が取得されていること", dummyUserMaster.getEmpName(), shippingAddress.getEmployeeName());

		//RJ社員情報マスタがNULLの場合エラーが発生すること
		shippingAddress = new ShippingAddress();
		shippingAddress.setMomEmployeeId(null);
		try {
			shippingAddressRepository.save(shippingAddress);
		} catch (ErrorCheckException e) {
			// 返却されるエラーを確認
			List<ErrorInfo> messageInfo = e.getErrorInfoList();
			Assert.assertEquals(1, messageInfo.size());
			Assert.assertEquals(messageInfo.get(0).getErrorId(), "ROT00008");
			Assert.assertEquals(messageInfo.get(0).getErrorMessage(), "配送先に存在しないMoM社員が設定されています。");
		}
	}
}
