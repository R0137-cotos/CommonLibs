package jp.co.ricoh.cotos.commonlib.barcode;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.ricoh.cotos.commonlib.DBConfig;
import jp.co.ricoh.cotos.commonlib.logic.barcode.BarcodeGenerator;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestBarcodeCreater {

	@Autowired
	BarcodeGenerator barcodeGenerator;

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
	public void バーコード生成() throws IOException {
		String filePath = "output/barcode1.png";

		// バーコード生成とファイル保存
		try (FileOutputStream fileOuputStream = new FileOutputStream(filePath)) {
			byte[] barcodeArray = barcodeGenerator.generate(new Code39Bean(), 400, "1234567893123");
			fileOuputStream.write(barcodeArray);
		} catch (IOException e) {
			Assert.fail();
		}

		Assert.assertTrue("バーコードファイルが保存されること", Files.exists(Paths.get(filePath), LinkOption.NOFOLLOW_LINKS));

	}
}
