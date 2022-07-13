package jp.co.ricoh.cotos.commonlib.db;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Ignore
public class RefreshMaterializedViewTest {


	@Autowired
	RefreshMaterializedViewUtil refreshMaterializedViewUtil;

	static ConfigurableApplicationContext context;

	private static final String SYNONYM_NAME = "V_VALID_LICENSE_ACCOUNT_INFO";

	@Autowired
	public void injectContext(ConfigurableApplicationContext injectContext) {
		context = injectContext;
		refreshMaterializedViewUtil.initStoredProcedure(context);
	}

	@Test
	public void シノニム変更確認() {
		String test = refreshMaterializedViewUtil.getSynonymNameOfStandby("V_VALID_LICENSE_ACCOUNT_INFO");
		//Assert.assertEquals("V_VALID_LICENSE_ACCOUNT_INFO_A", test);
		Assert.assertEquals("V_VALID_LICENSE_ACCOUNT_INFO_B", test);
		refreshMaterializedViewUtil.switchSynonym("V_VALID_LICENSE_ACCOUNT_INFO", test);
		test = refreshMaterializedViewUtil.getSynonymNameOfStandby("V_VALID_LICENSE_ACCOUNT_INFO");
		Assert.assertEquals("V_VALID_LICENSE_ACCOUNT_INFO_A", test);
		//Assert.assertEquals("V_VALID_LICENSE_ACCOUNT_INFO_B", test);
	}

	@Test
	public void 全処理確認() {
		refreshMaterializedViewUtil.refreshMViewAndSwitchOfLicenseAccountInfo(SYNONYM_NAME);
	}
}
