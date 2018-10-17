package jp.co.ricoh.cotos.commonlib.repository;

import javax.transaction.Transactional;

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
import jp.co.ricoh.cotos.commonlib.WithMockCustomUser;
import jp.co.ricoh.cotos.commonlib.entity.arrangement.Arrangement;
import jp.co.ricoh.cotos.commonlib.repository.arrangement.ArrangementRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TestArrangement {
	
	@Autowired
	ArrangementRepository arrangementRepository;

	@Autowired
	TestTools testTools;
	
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
	@WithMockCustomUser
	@Transactional
	public void 手配取得空行なし() throws Exception {
		
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("repository/arrangement.sql");
				
		Arrangement found = arrangementRepository.findOne(4L);

		// Entityが null ではないことを確認
		Assert.assertNotNull(found);
		
		// Entityの各項目の値が null ではないことを確認
		testTools.assertColumnsNotNull(found);
	}

	
	
	
	
	
	
	
	
	
}
