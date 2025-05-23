package jp.co.ricoh.cotos.commonlib.check;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * 明細情報インポート登録API共通チェックメソッドのテストクラス
 */

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
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
import jp.co.ricoh.cotos.commonlib.dto.parameter.common.ProductStackingMiddleDto;
import jp.co.ricoh.cotos.commonlib.entity.master.ItemMaster;
import jp.co.ricoh.cotos.commonlib.entity.master.ProductMaster;
import jp.co.ricoh.cotos.commonlib.exception.ErrorInfo;
import jp.co.ricoh.cotos.commonlib.logic.check.CheckUtil;
import jp.co.ricoh.cotos.commonlib.repository.master.ItemMasterRepository;
import jp.co.ricoh.cotos.commonlib.repository.master.ProductMasterRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TestRegistImportDetailUtilCheck {

	@Autowired
	CheckUtil checkUtil;

	@Autowired
	ProductMasterRepository productMasterRepository;

	@Autowired
	ItemMasterRepository itemMasterRepository;

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
	public void TestMssLinkageRjManageNumberCheck_エラーなし() {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("check/RegistImportDetailUtilCheck_1.sql");
		ProductMaster pm = productMasterRepository.findOne(1L);
		ItemMaster im = itemMasterRepository.findOne(1L);
		ProductStackingMiddleDto dto = setTestDataProductStackingMiddleDto(pm, im);
		List<ErrorInfo> errorList = new ArrayList<>();
		try {
			errorList.addAll(checkUtil.registImportDetailUtilCheck(dto));
			Assert.assertTrue(CollectionUtils.isEmpty(errorList));
		} catch (Exception e) {
			Assert.fail("予期せぬエラー");
			e.printStackTrace();
		}
	}

	@Test
	public void TestMssLinkageRjManageNumberCheck_エラーあり_必須チェック() {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("check/RegistImportDetailUtilCheck_1.sql");
		ProductStackingMiddleDto dto = setTestDataProductStackingMiddleDtoNodata(null, null);
		List<ErrorInfo> errorList = new ArrayList<>();
		try {
			errorList.addAll(checkUtil.registImportDetailUtilCheck(dto));
			Assert.assertEquals(7, errorList.size());
			Assert.assertTrue(errorList.stream().anyMatch(e -> "品種コードが設定されていません。".equals(e.getErrorMessage())));
			Assert.assertTrue(errorList.stream().anyMatch(e -> "品種名が設定されていません。".equals(e.getErrorMessage())));
			Assert.assertTrue(errorList.stream().anyMatch(e -> "数量が設定されていません。".equals(e.getErrorMessage())));
			Assert.assertTrue(errorList.stream().anyMatch(e -> "単価(E/U売価)が設定されていません。".equals(e.getErrorMessage())));
			Assert.assertTrue(errorList.stream().anyMatch(e -> "verが設定されていません。".equals(e.getErrorMessage())));
			Assert.assertTrue(errorList.stream().anyMatch(e -> "商品マスタが特定できません。".equals(e.getErrorMessage())));
			Assert.assertTrue(errorList.stream().anyMatch(e -> "品種マスタが特定できません。".equals(e.getErrorMessage())));
		} catch (Exception e) {
			Assert.fail("予期せぬエラー");
			e.printStackTrace();
		}
	}

	@Test
	public void TestMssLinkageRjManageNumberCheck_エラーあり_マスタチェック() {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("check/RegistImportDetailUtilCheck_1.sql");
		ProductMaster pm = productMasterRepository.findOne(1L);
		ItemMaster im = itemMasterRepository.findOne(1L);
		ProductStackingMiddleDto dto = setTestDataProductStackingMiddleDto(pm, im);
		dto.setImportFileVersion(3L);
		dto.setUnitPrice(new BigDecimal(6050));
		List<ErrorInfo> errorList = new ArrayList<>();
		try {
			errorList.addAll(checkUtil.registImportDetailUtilCheck(dto));
			Assert.assertEquals(1, errorList.size());
			Assert.assertTrue(errorList.stream().anyMatch(e -> "最新のファイルバージョンではありません。".equals(e.getErrorMessage())));
		} catch (Exception e) {
			Assert.fail("予期せぬエラー");
			e.printStackTrace();
		}
	}

	@Test
	public void TestMssLinkageRjManageNumberCheck_エラーあり_マスタチェック2() {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("check/RegistImportDetailUtilCheck_2.sql");
		ProductMaster pm = productMasterRepository.findOne(1L);
		ItemMaster im = itemMasterRepository.findOne(1L);
		ProductStackingMiddleDto dto = setTestDataProductStackingMiddleDto(pm, im);
		List<ErrorInfo> errorList = new ArrayList<>();
		try {
			errorList.addAll(checkUtil.registImportDetailUtilCheck(dto));
			Assert.assertEquals(1, errorList.size());
			Assert.assertTrue(errorList.stream().anyMatch(e -> "単価（E/U売価）が値引き限度額を下回っています。".equals(e.getErrorMessage())));
		} catch (Exception e) {
			Assert.fail("予期せぬエラー");
			e.printStackTrace();
		}
	}

	@Test
	public void TestMssLinkageRjManageNumberCheck_単体項目エラーで後続チェックが実施されない() {
		// テストデータ登録
		context.getBean(DBConfig.class).initTargetTestData("check/RegistImportDetailUtilCheck_1.sql");
		ProductMaster pm = productMasterRepository.findOne(1L);
		ItemMaster im = itemMasterRepository.findOne(1L);
		ProductStackingMiddleDto dto = setTestDataProductStackingMiddleDto(pm, im);
		dto.setImportFileVersion(null);
		List<ErrorInfo> errorList = new ArrayList<>();
		try {
			errorList.addAll(checkUtil.registImportDetailUtilCheck(dto));
			Assert.assertEquals(1, errorList.size());
			Assert.assertTrue(errorList.stream().anyMatch(e -> "verが設定されていません。".equals(e.getErrorMessage())));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("予期せぬエラー");
		}
	}

	private ProductStackingMiddleDto setTestDataProductStackingMiddleDto(ProductMaster pm,ItemMaster im) {
		ProductStackingMiddleDto dto = new ProductStackingMiddleDto();
		dto.setRicohItemCode("LiteCode100");
		dto.setRicohItemName("ROCLite品種名1");
		dto.setQuantity(2);
		dto.setUnitPrice(new BigDecimal(5050));
		dto.setRjDividingPrice(new BigDecimal(0));
		dto.setMotherStorePrice(new BigDecimal(0));
		dto.setImportFileVersion(2L);
		dto.setProductMaster(pm);
		dto.setItemMaster(im);
		return dto;
	}

	private ProductStackingMiddleDto setTestDataProductStackingMiddleDtoNodata(ProductMaster pm, ItemMaster im) {
		ProductStackingMiddleDto dto = new ProductStackingMiddleDto();
		dto.setRicohItemCode(null);
		dto.setRicohItemName(null);
		dto.setQuantity(null);
		dto.setUnitPrice(null);
		dto.setRjDividingPrice(new BigDecimal(0));
		dto.setMotherStorePrice(new BigDecimal(0));
		dto.setImportFileVersion(null);
		dto.setProductMaster(pm);
		dto.setItemMaster(im);
		return dto;
	}
}
