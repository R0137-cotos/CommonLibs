package jp.co.ricoh.cotos.commonlib.logic.maintenancePossibleLimit;

import java.util.Date;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.ibm.icu.text.SimpleDateFormat;

import jp.co.ricoh.cotos.commonlib.DBConfig;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MaintenancePossibleLimitUtilTest {

	static ConfigurableApplicationContext context;

	@Autowired
	private MaintenancePossibleLimitUtil maintenancePossibleLimitUtil;

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
	public void 正常系_新規_メーカ保守終了日_31日が算出される確認() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		Date maintenanceEndDate = sdf.parse("2026/01/02 12:32:20");
		Date purchaseDate = sdf.parse("2021/01/01 09:40:20");
		SimpleDateFormat assertSdf = new SimpleDateFormat("yyyy/MM/dd");
		Date assertDate = assertSdf.parse("2026/02/02");
		Date resultDate = maintenancePossibleLimitUtil.calcMaintenancePossibleLimit(maintenanceEndDate, purchaseDate);
		Assert.assertEquals("指定した日付が返却されること", assertDate, resultDate);
	}

	@Test
	public void 正常系_新規_購入日_5年31日が算出される確認() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		Date maintenanceEndDate = null;
		Date purchaseDate = sdf.parse("2021/01/01 09:40:20");
		SimpleDateFormat assertSdf = new SimpleDateFormat("yyyy/MM/dd");
		Date assertDate = assertSdf.parse("2026/02/01");
		Date resultDate = maintenancePossibleLimitUtil.calcMaintenancePossibleLimit(maintenanceEndDate, purchaseDate);
		Assert.assertEquals("指定した日付が返却されること", assertDate, resultDate);
	}

	@Test
	public void 正常系_新規_購入日_5年62日が算出される確認() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		Date maintenanceEndDate = sdf.parse("2026/02/02 12:32:20");
		Date purchaseDate = sdf.parse("2021/01/01 09:40:20");
		SimpleDateFormat assertSdf = new SimpleDateFormat("yyyy/MM/dd");
		Date assertDate = assertSdf.parse("2026/03/04");
		Date resultDate = maintenancePossibleLimitUtil.calcMaintenancePossibleLimit(maintenanceEndDate, purchaseDate);
		Assert.assertEquals("指定した日付が返却されること", assertDate, resultDate);
	}

	@Test
	public void 正常系_契約変更_メーカー保守終了日_31日が算出される確認() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		Date maintenanceEndDate = sdf.parse("2025/12/31 12:32:20");
		Date purchaseDate = sdf.parse("2021/01/01 09:40:20");
		SimpleDateFormat assertSdf = new SimpleDateFormat("yyyy/MM/dd");
		Date assertDate = assertSdf.parse("2026/01/31");
		Date resultDate = maintenancePossibleLimitUtil.calcMaintenancePossibleLimit(maintenanceEndDate, purchaseDate);
		Assert.assertEquals("指定した日付が返却されること", assertDate, resultDate);
	}

	@Test
	public void 正常系_契約変更_購入日_5年31日が算出される確認() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		Date maintenanceEndDate = null;
		Date purchaseDate = sdf.parse("2021/01/01 09:40:20");
		SimpleDateFormat assertSdf = new SimpleDateFormat("yyyy/MM/dd");
		Date assertDate = assertSdf.parse("2026/02/01");
		Date resultDate = maintenancePossibleLimitUtil.calcMaintenancePossibleLimit(maintenanceEndDate, purchaseDate);
		Assert.assertEquals("指定した日付が返却されること", assertDate, resultDate);
	}

	@Test
	public void 正常系_契約変更_購入日_5年62日が算出される確認() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		Date maintenanceEndDate = sdf.parse("2026/03/01 12:32:20");
		Date purchaseDate = sdf.parse("2021/01/01 09:40:20");
		SimpleDateFormat assertSdf = new SimpleDateFormat("yyyy/MM/dd");
		Date assertDate = assertSdf.parse("2026/03/04");
		Date resultDate = maintenancePossibleLimitUtil.calcMaintenancePossibleLimit(maintenanceEndDate, purchaseDate);
		Assert.assertEquals("指定した日付が返却されること", assertDate, resultDate);
	}

	@Test
	public void 正常系_新規_購入日がNULL() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		Date maintenanceEndDate = sdf.parse("2026/03/01 12:32:20");
		Date purchaseDate = null;
		Date resultDate = maintenancePossibleLimitUtil.calcMaintenancePossibleLimit(maintenanceEndDate, purchaseDate);
		Assert.assertNull("nullが返却されること", resultDate);
	}
}
