package jp.co.ricoh.cotos.commonlib.security;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.ricoh.cotos.commonlib.DBConfig;
import jp.co.ricoh.cotos.commonlib.entity.master.MvEmployeeMaster;
import jp.co.ricoh.cotos.commonlib.repository.master.MvEmployeeMasterRepository;
import jp.co.ricoh.cotos.commonlib.security.mom.MomAuthorityService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestFindEmployeeFromEmployeeMasterRepository {

	@Autowired
	MomAuthorityService momAuthorityService;

	@Autowired
	MvEmployeeMasterRepository mvEmployeeMasterRepository;

	static ConfigurableApplicationContext context;

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
	public void 正常_mom社員IDが存在する時正常に社員情報が取得できること() {

		MvEmployeeMaster expectEmployee = new MvEmployeeMaster();
		expectEmployee.setMomEmployeeId("00500784");
		expectEmployee.setMomOrgId("0914618");

		MvEmployeeMaster actualEmployee = momAuthorityService.findEmployeeFromEmployeeMasterRepository("00500784", "0914618");

		Assert.assertNotNull("正常に社員情報が作成されていること", actualEmployee);
		Assert.assertEquals("mom社員IDが設定されていること", expectEmployee.getMomEmployeeId(), actualEmployee.getMomEmployeeId());
		Assert.assertEquals("mom組織IDが設定されていること", expectEmployee.getMomOrgId(), actualEmployee.getMomOrgId());
		Assert.assertNotNull("シングルユーザIDが設定されていること", actualEmployee.getSingleUserId());
		Assert.assertNotNull("販社コードが設定されていること", actualEmployee.getHanshCd());
	}

	@Test
	public void 異常_mom社員IDが存在しない時nullにならないこと() {

		MvEmployeeMaster expectEmployee = new MvEmployeeMaster();
		expectEmployee.setMomEmployeeId("00000000");
		expectEmployee.setMomOrgId("0914618");

		MvEmployeeMaster actualEmployee = momAuthorityService.findEmployeeFromEmployeeMasterRepository("00000000", "0914618");

		Assert.assertNotNull("nullではないこと", actualEmployee);
		Assert.assertEquals("mom社員IDが設定されていること", expectEmployee.getMomEmployeeId(), actualEmployee.getMomEmployeeId());
		Assert.assertEquals("mom組織IDが設定されていること", expectEmployee.getMomOrgId(), actualEmployee.getMomOrgId());

	}

}
