package jp.co.ricoh.cotos.commonlib.check;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.ricoh.cotos.commonlib.WithMockCustomUser;
import jp.co.ricoh.cotos.commonlib.entity.master.UrlAuthMaster.ActionDiv;
import jp.co.ricoh.cotos.commonlib.entity.master.UrlAuthMaster.AuthDiv;
import jp.co.ricoh.cotos.commonlib.logic.check.BusinessCheck;
import jp.co.ricoh.cotos.commonlib.security.mom.MomAuthorityService;
import jp.co.ricoh.cotos.commonlib.security.mom.MomAuthorityService.AuthLevel;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestBusinessCheck {

	@Autowired
	BusinessCheck businessCheck;

	@SpyBean
	MomAuthorityService momAuthorityService;

	static ConfigurableApplicationContext context;

	@Autowired
	public void injectContext(ConfigurableApplicationContext injectContext) {
		context = injectContext;
	}

	@AfterClass
	public static void stopAPServer() throws InterruptedException {
		if (null != context) {
			context.stop();
		}
	}

	@Test
	@WithMockCustomUser(actionDiv = ActionDiv.更新, authDiv = AuthDiv.見積_契約_手配)
	public void 代理承認者の承認権限チェック_配下() throws Exception {
		Mockito.doReturn(AuthLevel.配下).when(momAuthorityService).searchMomAuthority(Mockito.anyString(), Mockito.any(), Mockito.any());
		Assert.assertTrue("「配下(30)」代理承認者の承認権限があること", businessCheck.existsSubApproverEmployeeAuthority("00230148", "00231268"));
	}

	@Test
	@WithMockCustomUser(actionDiv = ActionDiv.更新, authDiv = AuthDiv.見積_契約_手配)
	public void 代理承認者の承認権限チェック_自社() throws Exception {
		Mockito.doReturn(AuthLevel.自社).when(momAuthorityService).searchMomAuthority(Mockito.anyString(), Mockito.any(), Mockito.any());
		Assert.assertTrue("「自社(50)」代理承認者の承認権限があること", businessCheck.existsSubApproverEmployeeAuthority("00229746", "00231268"));
		Assert.assertFalse("「自社(50)」代理承認者の承認権限がないこと", businessCheck.existsSubApproverEmployeeAuthority("00229746", "00469821"));
	}

	@Test
	@WithMockCustomUser(actionDiv = ActionDiv.更新, authDiv = AuthDiv.見積_契約_手配)
	public void 代理承認者の承認権限チェック_地域() throws Exception {
		Mockito.doReturn(AuthLevel.地域).when(momAuthorityService).searchMomAuthority(Mockito.anyString(), Mockito.any(), Mockito.any());
		Assert.assertTrue("「地域(70)」代理承認者の承認権限があること", businessCheck.existsSubApproverEmployeeAuthority("00469821", "00673662"));
		Assert.assertFalse("「地域(70)」代理承認者の承認権限がないこと", businessCheck.existsSubApproverEmployeeAuthority("00469821", "00231268"));
	}

	@Test
	@WithMockCustomUser(actionDiv = ActionDiv.更新, authDiv = AuthDiv.見積_契約_手配)
	public void 代理承認者の承認権限チェック_東西() throws Exception {
		Mockito.doReturn(AuthLevel.東西).when(momAuthorityService).searchMomAuthority(Mockito.anyString(), Mockito.any(), Mockito.any());
		Assert.assertTrue("「東西(80)」代理承認者の承認権限があること", businessCheck.existsSubApproverEmployeeAuthority("00469821", "00231268"));
	}

	@Test
	@WithMockCustomUser(actionDiv = ActionDiv.更新, authDiv = AuthDiv.見積_契約_手配)
	public void 代理承認者の承認権限チェック_すべて() throws Exception {
		Mockito.doReturn(AuthLevel.すべて).when(momAuthorityService).searchMomAuthority(Mockito.anyString(), Mockito.any(), Mockito.any());
		Assert.assertTrue("「すべて(90)」代理承認者の承認権限があること", businessCheck.existsSubApproverEmployeeAuthority("00469821", "00231268"));
	}

	@Test
	@WithMockCustomUser(actionDiv = ActionDiv.更新, authDiv = AuthDiv.見積_契約_手配)
	public void 代理承認者の承認権限チェック_承認依頼者代理承認者チェックNG() throws Exception {
		Mockito.doReturn(AuthLevel.配下).when(momAuthorityService).searchMomAuthority(Mockito.anyString(), Mockito.any(), Mockito.any());
		Assert.assertFalse("代理承認者の承認権限がないこと", businessCheck.existsSubApproverEmployeeAuthority("00230148", "00230148"));
	}
}
