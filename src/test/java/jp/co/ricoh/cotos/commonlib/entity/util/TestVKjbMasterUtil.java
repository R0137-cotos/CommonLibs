package jp.co.ricoh.cotos.commonlib.entity.util;

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
import jp.co.ricoh.cotos.commonlib.WithMockCustomUser;
import jp.co.ricoh.cotos.commonlib.entity.contract.DealerContract;
import jp.co.ricoh.cotos.commonlib.entity.estimation.CustomerEstimation;
import jp.co.ricoh.cotos.commonlib.entity.master.VKjbMaster;
import jp.co.ricoh.cotos.commonlib.exception.ErrorCheckException;
import jp.co.ricoh.cotos.commonlib.exception.ErrorInfo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestVKjbMasterUtil {

	static ConfigurableApplicationContext context;

	// システム連携ID
	static final String MOM_KJB_SYSTEM_ID = "000000000433091";
	// 企事部ID(上記、システム連携IDのレコードが持つ企事部IDと同じ)
	static final String MOM_CUST_ID = "000000007309661";
	// システム連携ID(ダミー)
	static final String DUMMY_MOM_KJB_SYSTEM_ID = "dummyMomKjbSystemId";
	// 企事部ID(ダミー)
	static final String DUMMY_MOM_CUST_ID = "dummyMomCustId";

	@Autowired
	VKjbMasterUtil vKjbMasterUtil;

	@Autowired
	public void injectContext(ConfigurableApplicationContext injectContext) {
		context = injectContext;
		context.getBean(DBConfig.class).clearData();
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
	public void specifyVKjbMasterのテスト_顧客からマスタを所得_MoMシステム連携IDで検索() {
		CustomerEstimation customerEstimation = new CustomerEstimation();
		customerEstimation.setMomKjbSystemId(MOM_KJB_SYSTEM_ID);
		try {
			VKjbMaster vKjbMaster = vKjbMasterUtil.specifyVKjbMaster(customerEstimation, "顧客（契約用）");
			Assert.assertEquals("企事部マスタが正しく取得されること", MOM_KJB_SYSTEM_ID, vKjbMaster.getMclMomRelId());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("予期せぬエラーが発生");
		}
	}

	@Test
	@WithMockCustomUser
	public void specifyVKjbMasterのテスト_顧客からマスタを所得_企事部IDで検索() {
		CustomerEstimation customerEstimation = new CustomerEstimation();
		customerEstimation.setMomKjbSystemId(DUMMY_MOM_KJB_SYSTEM_ID);
		customerEstimation.setMomCustId(MOM_CUST_ID);
		try {
			VKjbMaster vKjbMaster = vKjbMasterUtil.specifyVKjbMaster(customerEstimation, "顧客（契約用）");
			Assert.assertEquals("企事部マスタが正しく取得されること", MOM_KJB_SYSTEM_ID, vKjbMaster.getMclMomRelId());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("予期せぬエラーが発生");
		}
	}

	@Test
	@WithMockCustomUser
	public void specifyVKjbMasterのテスト_顧客からマスタを所得_マスタが見つからない場合() {
		CustomerEstimation customerEstimation = new CustomerEstimation();
		customerEstimation.setMomKjbSystemId(DUMMY_MOM_KJB_SYSTEM_ID);
		customerEstimation.setMomCustId(DUMMY_MOM_CUST_ID);
		try {
			vKjbMasterUtil.specifyVKjbMaster(customerEstimation, "顧客（契約用）");
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
	public void specifyVKjbMasterのテスト_販売店からマスタを所得() {
		DealerContract dealerContract = new DealerContract();
		dealerContract.setMomKjbSystemId(MOM_KJB_SYSTEM_ID);
		try {
			VKjbMaster vKjbMaster = vKjbMasterUtil.specifyVKjbMaster(dealerContract, "販売店（契約用）");
			Assert.assertEquals("企事部マスタが正しく取得されること", MOM_KJB_SYSTEM_ID, vKjbMaster.getMclMomRelId());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("予期せぬエラーが発生");
		}
	}
}
