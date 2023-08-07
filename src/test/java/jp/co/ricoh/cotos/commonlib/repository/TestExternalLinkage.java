package jp.co.ricoh.cotos.commonlib.repository;

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
import jp.co.ricoh.cotos.commonlib.TestTools;
import jp.co.ricoh.cotos.commonlib.entity.externallinkage.ConfigInfo;
import jp.co.ricoh.cotos.commonlib.entity.externallinkage.ConstructionEimApplyInfo;
import jp.co.ricoh.cotos.commonlib.entity.externallinkage.ConstructionEimItemInfo;
import jp.co.ricoh.cotos.commonlib.entity.externallinkage.ElementInfo;
import jp.co.ricoh.cotos.commonlib.entity.externallinkage.ElementInfoDetail;
import jp.co.ricoh.cotos.commonlib.entity.externallinkage.HandoverCollectLocation;
import jp.co.ricoh.cotos.commonlib.entity.externallinkage.HandoverContractAttachedFile;
import jp.co.ricoh.cotos.commonlib.entity.externallinkage.HandoverIspLinkage;
import jp.co.ricoh.cotos.commonlib.entity.externallinkage.HandoverMailAddress;
import jp.co.ricoh.cotos.commonlib.entity.externallinkage.HandoverMobileEquipment;
import jp.co.ricoh.cotos.commonlib.entity.externallinkage.HandoverPenaltyDetailContract;
import jp.co.ricoh.cotos.commonlib.entity.externallinkage.ROpticalTransfer;
import jp.co.ricoh.cotos.commonlib.entity.externallinkage.ROpticalTransferManage;
import jp.co.ricoh.cotos.commonlib.entity.externallinkage.ROpticalTransferNwservice;
import jp.co.ricoh.cotos.commonlib.entity.externallinkage.SpecificInfo;
import jp.co.ricoh.cotos.commonlib.repository.externallinkage.ConfigInfoRepository;
import jp.co.ricoh.cotos.commonlib.repository.externallinkage.ConstructionEimApplyInfoRepository;
import jp.co.ricoh.cotos.commonlib.repository.externallinkage.ConstructionEimItemInfoRepository;
import jp.co.ricoh.cotos.commonlib.repository.externallinkage.ElementInfoDetailRepository;
import jp.co.ricoh.cotos.commonlib.repository.externallinkage.ElementInfoRepository;
import jp.co.ricoh.cotos.commonlib.repository.externallinkage.HandoverCollectLocationRepository;
import jp.co.ricoh.cotos.commonlib.repository.externallinkage.HandoverContractAttachedFileRepository;
import jp.co.ricoh.cotos.commonlib.repository.externallinkage.HandoverIspLinkageRepository;
import jp.co.ricoh.cotos.commonlib.repository.externallinkage.HandoverMailAddressRepository;
import jp.co.ricoh.cotos.commonlib.repository.externallinkage.HandoverMobileEquipmentRepository;
import jp.co.ricoh.cotos.commonlib.repository.externallinkage.HandoverPenaltyDetailContractRepository;
import jp.co.ricoh.cotos.commonlib.repository.externallinkage.ROpticalTransferManageRepository;
import jp.co.ricoh.cotos.commonlib.repository.externallinkage.ROpticalTransferNwserviceRepository;
import jp.co.ricoh.cotos.commonlib.repository.externallinkage.ROpticalTransferRepository;
import jp.co.ricoh.cotos.commonlib.repository.externallinkage.SpecificInfoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TestExternalLinkage {

	@Autowired
	SpecificInfoRepository specificInfoRepository;

	@Autowired
	ElementInfoRepository elementInfoRepository;

	@Autowired
	ElementInfoDetailRepository elementInfoDetailRepository;

	@Autowired
	HandoverPenaltyDetailContractRepository handoverPenaltyDetailContractRepository;

	@Autowired
	HandoverMobileEquipmentRepository handoverMobileEquipmentRepository;

	@Autowired
	HandoverCollectLocationRepository handoverCollectLocationRepository;

	@Autowired
	ConstructionEimApplyInfoRepository constructionEimApplyInfoRepository;

	@Autowired
	HandoverContractAttachedFileRepository handoverContractAttachedFileRepository;

	@Autowired
	HandoverIspLinkageRepository handoverIspLinkageRepository;

	@Autowired
	ROpticalTransferManageRepository rOpticalTransferManageRepository;

	@Autowired
	ROpticalTransferRepository rOpticalTransferRepository;

	@Autowired
	ROpticalTransferNwserviceRepository rOpticalTransferNwserviceRepository;

	@Autowired
	ConstructionEimItemInfoRepository constructionEimItemInfoRepository;

	@Autowired
	ConfigInfoRepository configInfoRepository;

	@Autowired
	HandoverMailAddressRepository handoverMailAddressRepository;

	@Autowired
	TestTools testTool;

	static ConfigurableApplicationContext context;

	@Autowired
	public void injectContext(ConfigurableApplicationContext injectContext) {
		context = injectContext;
		context.getBean(DBConfig.class).clearData();
		context.getBean(DBConfig.class).initTargetTestData("repository/externalLinkage.sql");
	}

	@AfterClass
	public static void stopAPServer() throws InterruptedException {
		if (null != context) {
			context.getBean(DBConfig.class).clearData();
			context.stop();
		}
	}

	@Test
	public void SpecificInfoRepositoryのテスト() throws Exception {

		SpecificInfo found = specificInfoRepository.findOne(1L);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void ElementInfoRepositoryのテスト() throws Exception {

		ElementInfo found = elementInfoRepository.findOne(1L);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void ElementInfoDetailRepositoryのテスト() throws Exception {

		ElementInfoDetail found = elementInfoDetailRepository.findOne(1L);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void HandoverPenaltyDetailContractRepositoryのテスト() throws Exception {

		HandoverPenaltyDetailContract found = handoverPenaltyDetailContractRepository.findOne(1L);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void HandoverMobileEquipmentRepositoryのテスト() throws Exception {

		HandoverMobileEquipment found = handoverMobileEquipmentRepository.findOne(1L);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void HandoverCollectLocationRepositoryのテスト() throws Exception {

		HandoverCollectLocation found = handoverCollectLocationRepository.findOne(1L);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void ConstructionEimApplyInfoRepositoryのテスト() throws Exception {

		ConstructionEimApplyInfo found = constructionEimApplyInfoRepository.findOne(1L);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);

		List<ConstructionEimApplyInfo> foundListByContractId = constructionEimApplyInfoRepository.findByContractId(2L);

		// Entity が null ではないことを確認
		Assert.assertNotNull(foundListByContractId);

		// Entity 1件以上取得できていることを確認
		Assert.assertNotEquals(foundListByContractId.size(), 0);

		// Entity 2件取得できていることを確認
		Assert.assertEquals(foundListByContractId.size(), 2);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);

		List<ConstructionEimApplyInfo> foundListByEstimationId = constructionEimApplyInfoRepository.findByEstimationId(1L);

		// Entity が null ではないことを確認
		Assert.assertNotNull(foundListByEstimationId);

		// Entity 1件以上取得できていることを確認
		Assert.assertNotEquals(foundListByEstimationId.size(), 0);

		// Entity 2件取得できていることを確認
		Assert.assertEquals(foundListByEstimationId.size(), 3);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(foundListByEstimationId.get(0));
		testTool.assertColumnsNotNull(foundListByEstimationId.get(1));
		testTool.assertColumnsNotNull(foundListByEstimationId.get(2));

	}

	@Test
	public void HandoverContractAttachedFileRepositoryのテスト() throws Exception {

		HandoverContractAttachedFile found = handoverContractAttachedFileRepository.findOne(1L);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void HandoverIspLinkageRepositoryのテスト() throws Exception {

		HandoverIspLinkage found = handoverIspLinkageRepository.findOne(1L);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void ROpticalTransferManageRepositoryのテスト() throws Exception {

		ROpticalTransferManage found = rOpticalTransferManageRepository.findOne(1L);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void ROpticalTransferRepositoryのテスト() throws Exception {

		ROpticalTransfer found = rOpticalTransferRepository.findOne(1L);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void ROpticalTransferNwserviceRepositoryのテスト() throws Exception {

		ROpticalTransferNwservice found = rOpticalTransferNwserviceRepository.findOne(1L);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void ConstructionEimItemInfoRepositoryのテスト() throws Exception {

		ConstructionEimItemInfo found = constructionEimItemInfoRepository.findOne(1L);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void ConfigInfoRepositoryのテスト() throws Exception {

		ConfigInfo found = configInfoRepository.findOne(1L);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}

	@Test
	public void HandoverMailAddressRepositoryのテスト() throws Exception {

		HandoverMailAddress found = handoverMailAddressRepository.findOne(1L);

		// Entity が null ではないことを確認
		Assert.assertNotNull(found);

		// Entity の各項目の値が null ではないことを確認
		testTool.assertColumnsNotNull(found);
	}
}
