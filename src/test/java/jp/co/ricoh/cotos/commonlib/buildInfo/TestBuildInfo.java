package jp.co.ricoh.cotos.commonlib.buildInfo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Properties;

import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.ricoh.cotos.commonlib.DBConfig;
import lombok.val;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TestBuildInfo {

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

	@SuppressWarnings("unchecked")
	@Test
	public void ビルド情報が取得できること() throws Exception {
		val appProps = new Properties();
		appProps.put("name", "testName");
		appProps.put("time", "2022-08-23T19:05:03+0900");
		val gitProps = new Properties();
		gitProps.put("commit.time", "2022-08-23T19:05:03+0900");
		gitProps.put("commit.id", "testCommitId");
		gitProps.put("tags", "testTags");
		gitProps.put("branch", "testBranch");
		val buildInfo = new BuildInfo(new BuildProperties(appProps), new GitProperties(gitProps));
		val buildInfoMap = buildInfo.toMap();
		HashMap<String, Object> appInfo = (HashMap<String, Object>) buildInfoMap.get("app");
		HashMap<String, Object> gitInfo = (HashMap<String, Object>) buildInfoMap.get("git");
		assertNotNull("appビルド情報が取得できること", appInfo);
		assertNotNull("gitビルド情報が取得できること", gitInfo);
		assertEquals("nameが取得できること", "testName", appInfo.get("name"));
		assertEquals("buildTimeが取得できること", "2022-08-23T19:05:03Z", appInfo.get("buildTime"));
		assertEquals("commitTimeが取得できること", "2022-08-23T19:05:03Z", gitInfo.get("commitTime"));
		assertEquals("commitIdが取得できること", "testCommitId", gitInfo.get("commitId"));
		assertEquals("tagsが取得できること", "testTags", gitInfo.get("tags"));
		assertEquals("branchが取得できること", "testBranch", gitInfo.get("branch"));
	}
}
