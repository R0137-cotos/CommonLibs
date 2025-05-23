package jp.co.ricoh.cotos.commonlib.fileupdownload;

import static org.junit.Assert.*;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StreamUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.ricoh.cotos.commonlib.DBConfig;
import jp.co.ricoh.cotos.commonlib.WithMockCustomUser;
import jp.co.ricoh.cotos.commonlib.dto.parameter.common.CreateZipParameter;
import jp.co.ricoh.cotos.commonlib.entity.common.AttachedFile;
import jp.co.ricoh.cotos.commonlib.exception.ErrorCheckException;
import jp.co.ricoh.cotos.commonlib.exception.ErrorInfo;
import jp.co.ricoh.cotos.commonlib.logic.fileupdownload.FileUpDownload;
import jp.co.ricoh.cotos.commonlib.repository.common.AttachedFileRepository;
import jp.co.ricoh.cotos.commonlib.util.AppProperties;
import net.lingala.zip4j.exception.ZipException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestFileUpDownload {

	@Autowired
	AttachedFileRepository attachedFileRepository;

	@Autowired
	FileUpDownload fileUpDownload;

	@Autowired
	AppProperties appProperties;

	static ConfigurableApplicationContext context;

	@Autowired
	ObjectMapper mapper;

	@SpyBean
	CreateZipParameter parameter;

	@Autowired
	public void injectContext(ConfigurableApplicationContext injectContext) {
		context = injectContext;
		context.getBean(DBConfig.class).initTargetTestData("sql/fileupdownload/deleteAttachedFile.sql");
	}

	@AfterClass
	public static void stopAPServer() throws InterruptedException {
		if (null != context) {
			context.getBean(DBConfig.class).initTargetTestData("sql/fileupdownload/deleteAttachedFile.sql");
			context.stop();
		}
	}

	@Test
	@WithMockCustomUser
	public void ファイルアップロード_ダウンロード() throws Exception {

		アップロードディレクトリファイル削除();

		String fileNm = "testFile1.xlsx";
		AttachedFile attachedFile = fileUpDownload.fileUpload(ファイル情報作成(fileNm));
		ファイルダウンロード(attachedFile.getId(), fileNm);
	}

	@Test
	@WithMockCustomUser
	public void ファイルアップロード_ダウンロード_IE() throws Exception {

		アップロードディレクトリファイル削除();

		String fileNm = "testFile1.xlsx";
		AttachedFile attachedFile = fileUpDownload.fileUpload(ファイル情報作成_IE(fileNm));
		ファイルダウンロード(attachedFile.getId(), fileNm);
	}

	@Test
	public void ファイルアップロードエラー() throws Exception {
		try {
			fileUpDownload.fileUpload(null);
			Assert.fail("正常終了してしまった");
		} catch (ErrorCheckException e) {
			Assert.assertEquals("エラーIDが正しく設定されること", "ROT00104", e.getErrorInfoList().get(0).getErrorId());
			Assert.assertEquals("エラーメッセージが正しく設定されること", "アップロード対象のファイル情報が設定されていません。", e.getErrorInfoList().get(0).getErrorMessage());
		}

		try {
			fileUpDownload.fileUpload(ファイル情報作成("filename.aaa"));
			Assert.fail("正常終了してしまった");
		} catch (ErrorCheckException e) {
			Assert.assertEquals("エラーIDが正しく設定されること", "ROT00105", e.getErrorInfoList().get(0).getErrorId());
			Assert.assertEquals("エラーメッセージが正しく設定されること", "アップロード対象ファイルの拡張子が設定可能な拡張子ではありません。", e.getErrorInfoList().get(0).getErrorMessage());
		}

		try {
			fileUpDownload.fileUpload(ファイル情報作成("abcdefghijk.txt"));
			Assert.fail("正常終了してしまった");
		} catch (ErrorCheckException e) {
			Assert.assertEquals("エラーIDが正しく設定されること", "ROT00106", e.getErrorInfoList().get(0).getErrorId());
			Assert.assertEquals("エラーメッセージが正しく設定されること", "アップロード対象ファイルのファイル名サイズが最大サイズを超えています。", e.getErrorInfoList().get(0).getErrorMessage());
		}
	}

	@Test
	public void ファイルダウンロードエラー() throws Exception {
		テストデータ作成();
		try {
			fileUpDownload.downloadFile(13L, "test.text");
			Assert.fail("正常終了してしまった");
		} catch (ErrorCheckException e) {
			Assert.assertEquals("エラーIDが正しく設定されること", "ROT00108", e.getErrorInfoList().get(0).getErrorId());
			Assert.assertEquals("エラーメッセージが正しく設定されること", "ダウンロード対象の添付ファイル情報が存在しません。", e.getErrorInfoList().get(0).getErrorMessage());
		}
		try {
			fileUpDownload.downloadFile(12L, "test.text");
			Assert.fail("正常終了してしまった");
		} catch (ErrorCheckException e) {
			Assert.assertEquals("エラーIDが正しく設定されること", "ROT00100", e.getErrorInfoList().get(0).getErrorId());
			Assert.assertEquals("エラーメッセージが正しく設定されること", "指定されたファイルが存在しません。", e.getErrorInfoList().get(0).getErrorMessage());
		}
	}

	@Test
	public void ファイル削除エラー() throws Exception {
		テストデータ作成();
		try {
			fileUpDownload.deleteFile(13L);
			Assert.fail("正常終了してしまった");
		} catch (ErrorCheckException e) {
			Assert.assertEquals("エラーIDが正しく設定されること", "ROT00108", e.getErrorInfoList().get(0).getErrorId());
			Assert.assertEquals("エラーメッセージが正しく設定されること", "削除対象の添付ファイル情報が存在しません。", e.getErrorInfoList().get(0).getErrorMessage());
		}
		try {
			fileUpDownload.deleteFile(12L);
			Assert.fail("正常終了してしまった");
		} catch (ErrorCheckException e) {
			Assert.assertEquals("エラーIDが正しく設定されること", "ROT00100", e.getErrorInfoList().get(0).getErrorId());
			Assert.assertEquals("エラーメッセージが正しく設定されること", "指定されたファイルが存在しません。", e.getErrorInfoList().get(0).getErrorMessage());
		}
	}

	@Test
	@Ignore
	public void EIMファイルダウンロード() throws Exception {
		テストデータ作成();
		try {
			fileUpDownload.downloadFile(14L, "EIMテスト");
		} catch (Exception e) {
			Assert.fail("動作確認NG");
		}
	}

	@Test
	public void ZIP圧縮_暗号化なし_削除あり_ディレクトリとファイル() {
		アップロードディレクトリファイル削除();
		String path = appProperties.getFileProperties().getUploadFileDir();
		// 圧縮対象の設定
		try {
			ファイルコピー("/src/test/resources/attachmentFiles/testFile2.txt", "testFile2_999.txt");
			new File(path + "/test_attachmentFiles").mkdir();
			ファイルコピー("/src/test/resources/attachmentFiles/testFile2.txt", "test_attachmentFiles/testFile2_999.txt");
		} catch (Exception e1) {
			e1.printStackTrace();
			Assert.fail("想定外のエラーが発生");
		}

		CreateZipParameter zipParam = new CreateZipParameter();
		List<String> inputPathList = new ArrayList<>();
		inputPathList.add(path + "/testFile2_999.txt");
		inputPathList.add(path + "/test_attachmentFiles");
		zipParam.setInputPathList(inputPathList);
		zipParam.setOutputPath(appProperties.getFileProperties().getUploadFileDir() + "/暗号化なし.zip");
		zipParam.setFileNameCharset("MS932");
		zipParam.setPassword("");
		zipParam.setInputFileDeleteFlg(true);
		try {
			byte[] zipBytes = fileUpDownload.createZipAndDelete(zipParam);

			File zip = new File(zipParam.getOutputPath());
			Assert.assertFalse("作成したzipファイルが削除されていること", zip.exists());

			zipParam.getInputPathList().stream().forEach(input -> Assert.assertFalse("zipファイルのソースが削除されていること", new File(input).exists()));

			// zipファイルを作成する
			Files.write(zip.toPath(), zipBytes);
			Assert.assertTrue("zipファイルが存在すること", zip.exists());
			Assert.assertEquals("ファイル名が想定通りであること（文字化けしていないこと）", "暗号化なし.zip", zip.getName());

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("想定外のエラーが発生");
		} finally {
			アップロードディレクトリファイル削除();
			new File(zipParam.getOutputPath()).delete();
		}
	}

	@Test
	public void ZIP圧縮_暗号化あり_削除なし_ファイルのみ() {
		アップロードディレクトリファイル削除();

		// 圧縮対象の設定
		try {
			ファイルコピー("/src/test/resources/attachmentFiles/testFile2.txt", "testFile2_999.txt");
		} catch (Exception e1) {
			e1.printStackTrace();
			Assert.fail("想定外のエラーが発生");
		}

		String path = appProperties.getFileProperties().getUploadFileDir();
		CreateZipParameter zipParam = new CreateZipParameter();
		List<String> inputPathList = new ArrayList<>();
		inputPathList.add(path + "/testFile2_999.txt");
		zipParam.setInputPathList(inputPathList);
		zipParam.setOutputPath(appProperties.getFileProperties().getUploadFileDir() + "/暗号化あり.zip");
		zipParam.setFileNameCharset("MS932");
		zipParam.setPassword("test");
		zipParam.setInputFileDeleteFlg(false);
		try {
			byte[] zipBytes = fileUpDownload.createZipAndDelete(zipParam);

			File zip = new File(zipParam.getOutputPath());
			Assert.assertFalse("作成したzipファイルが削除されていること", zip.exists());

			zipParam.getInputPathList().stream().forEach(input -> Assert.assertTrue("zipファイルのソースが削除されていないこと", new File(input).exists()));

			// zipファイルを作成する
			Files.write(zip.toPath(), zipBytes);
			Assert.assertTrue("zipファイルが存在すること", zip.exists());
			Assert.assertEquals("ファイル名が想定通りであること（文字化けしていないこと）", "暗号化あり.zip", zip.getName());

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("想定外のエラーが発生");
		} finally {
			アップロードディレクトリファイル削除();
			new File(zipParam.getOutputPath()).delete();
		}
	}

	@Test
	public void ZIP圧縮_異常系_パラメータ自体がnull() {
		try {
			fileUpDownload.createZipAndDelete(null);
			fail("異常系のテストで正常終了した");
		} catch (ErrorCheckException e) {
			List<ErrorInfo> errorList = e.getErrorInfoList();
			// チェック
			assertEquals("エラー件数が一致すること", 1, errorList.size());
			assertEquals("エラーIDが一致すること", "ROT00013", e.getErrorInfoList().get(0).getErrorId());
			assertEquals("エラーメッセージが一致すること", "Zipファイル作成パラメータが設定されていません。", e.getErrorInfoList().get(0).getErrorMessage());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("想定外のエラーが発生");
		}
	}

	@Test
	public void ZIP圧縮_異常系_パラメータ内部チェック() {
		CreateZipParameter zipParam = new CreateZipParameter();
		try {
			fileUpDownload.createZipAndDelete(zipParam);
			fail("異常系のテストで正常終了した");
		} catch (ErrorCheckException e) {
			List<ErrorInfo> errorList = e.getErrorInfoList();
			// チェック
			assertEquals("エラー件数が一致すること", 1, errorList.size());
			assertEquals("エラーIDが一致すること", "ROT00001", e.getErrorInfoList().get(0).getErrorId());
			assertEquals("エラーメッセージが一致すること", "パラメータ「圧縮対象ファイル/ディレクトリ」が設定されていません。", e.getErrorInfoList().get(0).getErrorMessage());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("想定外のエラーが発生");
		}

		List<String> inputList = new ArrayList<>();
		inputList.add("AAAAA");
		zipParam.setInputPathList(inputList);
		try {
			fileUpDownload.createZipAndDelete(zipParam);
			fail("異常系のテストで正常終了した");
		} catch (ErrorCheckException e) {
			List<ErrorInfo> errorList = e.getErrorInfoList();
			// チェック
			assertEquals("エラー件数が一致すること", 1, errorList.size());
			assertEquals("エラーIDが一致すること", "ROT00001", e.getErrorInfoList().get(0).getErrorId());
			assertEquals("エラーメッセージが一致すること", "パラメータ「圧縮先ファイル」が設定されていません。", e.getErrorInfoList().get(0).getErrorMessage());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("想定外のエラーが発生");
		}

		zipParam.setOutputPath("AAAAA/BBB");
		try {
			fileUpDownload.createZipAndDelete(zipParam);
			fail("異常系のテストで正常終了した");
		} catch (ErrorCheckException e) {
			List<ErrorInfo> errorList = e.getErrorInfoList();
			// チェック
			assertEquals("エラー件数が一致すること", 1, errorList.size());
			assertEquals("エラーIDが一致すること", "ROT00001", e.getErrorInfoList().get(0).getErrorId());
			assertEquals("エラーメッセージが一致すること", "パラメータ「パスワード」が設定されていません。", e.getErrorInfoList().get(0).getErrorMessage());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("想定外のエラーが発生");
		}

		zipParam.setPassword("");
		try {
			fileUpDownload.createZipAndDelete(zipParam);
			fail("異常系のテストで正常終了した");
		} catch (ErrorCheckException e) {
			List<ErrorInfo> errorList = e.getErrorInfoList();
			// チェック
			assertEquals("エラー件数が一致すること", 1, errorList.size());
			assertEquals("エラーIDが一致すること", "ROT00001", e.getErrorInfoList().get(0).getErrorId());
			assertEquals("エラーメッセージが一致すること", "パラメータ「ファイル名文字コード」が設定されていません。", e.getErrorInfoList().get(0).getErrorMessage());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("想定外のエラーが発生");
		}

		zipParam.setFileNameCharset("");
		try {
			fileUpDownload.createZipAndDelete(zipParam);
			fail("異常系のテストで正常終了した");
		} catch (ErrorCheckException e) {
			List<ErrorInfo> errorList = e.getErrorInfoList();
			// チェック
			assertEquals("エラー件数が一致すること", 1, errorList.size());
			assertEquals("エラーIDが一致すること", "ROT00003", e.getErrorInfoList().get(0).getErrorId());
			assertEquals("エラーメッセージが一致すること", "圧縮対象ファイル/ディレクトリが特定できません。", e.getErrorInfoList().get(0).getErrorMessage());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("想定外のエラーが発生");
		}

		inputList = new ArrayList<>();
		inputList.add(appProperties.getFileProperties().getUploadFileDir());
		zipParam.setInputPathList(inputList);
		try {
			fileUpDownload.createZipAndDelete(zipParam);
			fail("異常系のテストで正常終了した");
		} catch (ErrorCheckException e) {
			List<ErrorInfo> errorList = e.getErrorInfoList();
			// チェック
			assertEquals("エラー件数が一致すること", 1, errorList.size());
			assertEquals("エラーIDが一致すること", "ROT00003", e.getErrorInfoList().get(0).getErrorId());
			assertEquals("エラーメッセージが一致すること", "圧縮先ディレクトリが特定できません。", e.getErrorInfoList().get(0).getErrorMessage());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("想定外のエラーが発生");
		}
	}

	@Test
	public void ZIP圧縮_異常系_ZIP作成時にZipException発生() {
		try {
			Mockito.doAnswer(a -> {
				throw new ZipException("test");
			}).when(parameter).getInputPathList();
			fileUpDownload.createZipAndDelete(parameter);
			fail("異常系のテストで正常終了した");
		} catch (ErrorCheckException e) {
			List<ErrorInfo> errorList = e.getErrorInfoList();
			// チェック
			assertEquals("エラー件数が一致すること", 1, errorList.size());
			assertEquals("エラーIDが一致すること", "ROT00117", e.getErrorInfoList().get(0).getErrorId());
			assertEquals("エラーメッセージが一致すること", "圧縮ファイルの作成に失敗しました。", e.getErrorInfoList().get(0).getErrorMessage());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("想定外のエラーが発生");
		}
	}

	@Test
	public void ZIP圧縮_異常系_ZIP作成時にIOException発生() {
		try {
			Mockito.doAnswer(a -> {
				throw new ZipException("test");
			}).when(parameter).getInputPathList();
			fileUpDownload.createZipAndDelete(parameter);
			fail("異常系のテストで正常終了した");
		} catch (ErrorCheckException e) {
			List<ErrorInfo> errorList = e.getErrorInfoList();
			// チェック
			assertEquals("エラー件数が一致すること", 1, errorList.size());
			assertEquals("エラーIDが一致すること", "ROT00117", e.getErrorInfoList().get(0).getErrorId());
			assertEquals("エラーメッセージが一致すること", "圧縮ファイルの作成に失敗しました。", e.getErrorInfoList().get(0).getErrorMessage());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("想定外のエラーが発生");
		}
	}

	/**
	 * ファイル情報作成
	 *
	 * @param fileNm
	 *            ファイル名
	 * @return ファイル情報
	 * @throws Exception
	 */
	private MockMultipartFile ファイル情報作成(String fileNm) throws Exception {
		String path = new File(".").getAbsoluteFile().getParent();
		File file = new File(path + "/src/test/resources/attachmentFiles/" + fileNm);
		InputStream stream = Files.newInputStream(file.toPath());
		return new MockMultipartFile(file.getName(), file.getName(), "multipart/form-data", stream);
	}

	/**
	 * ファイル情報作成(IE)
	 *
	 * @param fileNm
	 *            ファイル名
	 * @return ファイル情報
	 * @throws Exception
	 */
	private MockMultipartFile ファイル情報作成_IE(String fileNm) throws Exception {
		String path = new File(".").getAbsoluteFile().getParent();
		File file = new File(path + "/src/test/resources/attachmentFiles/" + fileNm);
		InputStream stream = Files.newInputStream(file.toPath());
		return new MockMultipartFile(file.getName(), file.getPath(), "multipart/form-data", stream);
	}

	private void ファイルダウンロード(Long attachedFileId, String compareFileNm) {
		String path = new File(".").getAbsoluteFile().getParent();
		File compareFile = new File(path + "/src/test/resources/attachmentFiles/" + compareFileNm);
		try {
			InputStream stream = fileUpDownload.downloadFile(attachedFileId, compareFileNm).getBody();
			File file = new File(appProperties.getFileProperties().getUploadFileDir() + "/" + attachedFileId + "_output." + ファイル拡張子取得(compareFileNm));
			try (OutputStream out = Files.newOutputStream(file.toPath())) {
				StreamUtils.copy(stream, out);
			}
			Assert.assertTrue("ファイル内容が一致していること", Arrays.equals(Files.readAllBytes(file.toPath()), Files.readAllBytes(compareFile.toPath())));
			AttachedFile attachedFileDb = attachedFileRepository.findById(attachedFileId).get();
			Assert.assertEquals("物理ファイル名が正しく登録されていること", attachedFileId + "_" + compareFileNm, attachedFileDb.getFilePhysicsName());

			// 作成した一時ファイルを削除
			file.delete();
		} catch (Exception e) {
			Assert.fail("異常終了した");
		}
	}

	/**
	 * ファイルコピー
	 *
	 * @param copyFileNm
	 *            コピー元ファイル
	 * @param fileNm
	 *            ファイル名
	 * @return ファイル
	 * @throws Exception
	 */
	private File ファイルコピー(String copyFileNm, String fileNm) throws Exception {
		String path = new File(".").getAbsoluteFile().getParent();
		File copyFile = new File(path + copyFileNm);
		File baseDir = new File(appProperties.getFileProperties().getUploadFileDir());
		Files.createDirectories(baseDir.toPath());
		File file = new File(baseDir, fileNm);
		try (OutputStream out = Files.newOutputStream(file.toPath())) {
			StreamUtils.copy(Files.newInputStream(copyFile.toPath()), out);
		}
		Assert.assertTrue("ファイルが存在すること", file.exists());
		return file;
	}

	private void アップロードディレクトリファイル削除() {
		File dir = new File(appProperties.getFileProperties().getUploadFileDir());
		if (null != dir.listFiles()) {
			List<File> fileList = Arrays.asList(dir.listFiles());
			fileList.stream().forEach(file -> file.delete());
		}
	}

	/**
	 * ファイル拡張子取得
	 *
	 * @param fileName
	 *            ファイル名
	 * @return 拡張子
	 */
	private String ファイル拡張子取得(String fileName) {
		if (null != fileName) {
			int point = fileName.lastIndexOf(".");
			if (point != -1) {
				return fileName.substring(point + 1);
			}
		}
		return null;
	}

	private void テストデータ作成() {
		context.getBean(DBConfig.class).initTargetTestData("sql/fileupdownload/testAttachedFileInsert.sql");
	}
}
