package jp.co.ricoh.cotos.commonlib.mail;

import java.io.File;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import com.sun.mail.smtp.SMTPMessage;

import jp.co.ricoh.cotos.commonlib.DBConfig;
import jp.co.ricoh.cotos.commonlib.dto.parameter.communication.BounceMailHeaderDto;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.ProcessCategory;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.ServiceCategory;
import jp.co.ricoh.cotos.commonlib.entity.master.MailTemplateMaster;
import jp.co.ricoh.cotos.commonlib.logic.mail.CommonSendMail;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TestSendMail {

	@Autowired
	CommonSendMail commonSendMail;

	@Autowired
	JavaMailSender javaMailSender;

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
	public void メール送信_商品グループマスタID未指定() throws MessagingException {
		テストデータ作成();

		List<String> emailToList = 送信先TOメールアドレスリスト作成();
		List<String> emailCcList = 送信先CCメールアドレスリスト作成();
		List<String> emailBccList = 送信先BCCメールアドレスリスト作成();
		List<String> mailSubjectRepalceValueList = メール件名置換リスト作成();
		List<String> mailTextRepalceValueList = メール本文置換リスト作成();
		BounceMailHeaderDto bounceMailHeaderDto = バウンスメールヘッダーDTO作成();
		try {
			commonSendMail.findMailTemplateMasterAndSendMail(ServiceCategory.見積, ProcessCategory.承認依頼.toString(), null, emailToList, emailCcList, emailBccList, mailSubjectRepalceValueList, mailTextRepalceValueList, null, bounceMailHeaderDto);
		} catch (Exception e) {
			Assert.fail("異常終了");
		}
	}

	@Test
	public void メール送信_商品グループマスタID指定() throws MessagingException {
		テストデータ作成();

		List<String> emailToList = 送信先TOメールアドレスリスト作成();
		List<String> emailCcList = 送信先CCメールアドレスリスト作成();
		List<String> emailBccList = 送信先BCCメールアドレスリスト作成();
		List<String> mailSubjectRepalceValueList = メール件名置換リスト作成();
		List<String> mailTextRepalceValueList = メール本文置換リスト作成();
		BounceMailHeaderDto bounceMailHeaderDto = バウンスメールヘッダーDTO作成();
		try {
			commonSendMail.findMailTemplateMasterAndSendMail(ServiceCategory.見積, ProcessCategory.承認依頼.toString(), 1L, emailToList, emailCcList, emailBccList, mailSubjectRepalceValueList, mailTextRepalceValueList, null, bounceMailHeaderDto);
		} catch (Exception e) {
			Assert.fail("異常終了");
		}
	}

	@Test
	public void メール送信API呼び出し() throws MessagingException {
		テストデータ作成();

		List<String> emailToList = 送信先TOメールアドレスリスト作成();
		List<String> emailCcList = 送信先CCメールアドレスリスト作成();
		List<String> emailBccList = 送信先BCCメールアドレスリスト作成();
		List<String> mailSubjectRepalceValueList = メール件名置換リスト作成();
		List<String> mailTextRepalceValueList = メール本文置換リスト作成();
		BounceMailHeaderDto bounceMailHeaderDto = バウンスメールヘッダーDTO作成();
		try {
			commonSendMail.findMailTemplateMasterAndSendMail(2L, emailToList, emailCcList, emailBccList, mailSubjectRepalceValueList, mailTextRepalceValueList, null, bounceMailHeaderDto);
		} catch (Exception e) {
			Assert.fail("異常終了");
		}
	}

	@Test
	@Transactional
	public void メール送信添付ファイル() throws MessagingException {
		テストデータ作成();

		List<String> emailToList = 送信先TOメールアドレスリスト作成();
		List<String> emailCcList = 送信先CCメールアドレスリスト作成();
		List<String> emailBccList = 送信先BCCメールアドレスリスト作成();
		List<String> mailSubjectRepalceValueList = メール件名置換リスト作成();
		List<String> mailTextRepalceValueList = メール本文置換リスト作成();
		BounceMailHeaderDto bounceMailHeaderDto = バウンスメールヘッダーDTO作成();
		String path = new File(".").getAbsoluteFile().getParent();
		String uploadFile = path + "/src/test/resources/dummyFile/10130102146_201712.zip";
		try {
			commonSendMail.findMailTemplateMasterAndSendMail(3L, emailToList, emailCcList, emailBccList, mailSubjectRepalceValueList, mailTextRepalceValueList, uploadFile, bounceMailHeaderDto);
		} catch (Exception e) {
			Assert.fail("異常終了");
		}
	}

	@Test
	@Transactional
	public void メール送信複数添付ファイル() throws MessagingException {
		テストデータ作成();

		List<String> emailToList = 送信先TOメールアドレスリスト作成();
		List<String> emailCcList = 送信先CCメールアドレスリスト作成();
		List<String> emailBccList = 送信先BCCメールアドレスリスト作成();
		List<String> mailSubjectRepalceValueList = メール件名置換リスト作成();
		List<String> mailTextRepalceValueList = メール本文置換リスト作成();
		List<String> uploadFileList = new ArrayList<>();
		BounceMailHeaderDto bounceMailHeaderDto = バウンスメールヘッダーDTO作成();
		String path = new File(".").getAbsoluteFile().getParent();
		uploadFileList.add(path + "/src/test/resources/dummyFile/請求書_201909.pdf");
		uploadFileList.add(path + "/src/test/resources/dummyFile/電力使用料金明細書_201909.xlsx");
		try {
			commonSendMail.findMailTemplateMasterAndSendMailAndAttachedFiles(3L, emailToList, emailCcList, emailBccList, mailSubjectRepalceValueList, mailTextRepalceValueList, uploadFileList, bounceMailHeaderDto);
		} catch (Exception e) {
			Assert.fail("異常終了");
		}
	}

	@Test
	public void メール送信置換リストNull値あり() throws MessagingException {
		テストデータ作成();

		List<String> emailToList = 送信先TOメールアドレスリスト作成();
		List<String> emailCcList = 送信先CCメールアドレスリスト作成();
		List<String> emailBccList = 送信先BCCメールアドレスリスト作成();
		List<String> mailSubjectRepalceValueList = メール件名置換リスト作成Null値あり();
		List<String> mailTextRepalceValueList = メール本文置換リスト作成Null値あり();
		BounceMailHeaderDto bounceMailHeaderDto = バウンスメールヘッダーDTO作成();
		try {
			commonSendMail.findMailTemplateMasterAndSendMail(ServiceCategory.見積, ProcessCategory.承認依頼.toString(), 0L, emailToList, emailCcList, emailBccList, mailSubjectRepalceValueList, mailTextRepalceValueList, null, bounceMailHeaderDto);
		} catch (Exception e) {
			Assert.fail("異常終了");
		}
	}

	@Test
	public void メール送信環境依存文字あり() throws MessagingException {
		テストデータ作成();

		List<String> emailToList = 送信先TOメールアドレスリスト作成();
		List<String> emailCcList = 送信先CCメールアドレスリスト作成();
		List<String> emailBccList = 送信先BCCメールアドレスリスト作成();
		List<String> mailSubjectRepalceValueList = メール件名置換リスト作成();
		List<String> mailTextRepalceValueList = メール本文置換リスト作成();
		BounceMailHeaderDto bounceMailHeaderDto = バウンスメールヘッダーDTO作成();
		try {
			commonSendMail.findMailTemplateMasterAndSendMail(10L, emailToList, emailCcList, emailBccList, mailSubjectRepalceValueList, mailTextRepalceValueList, null, bounceMailHeaderDto);
		} catch (Exception e) {
			Assert.fail("異常終了");
		}
	}

	@Test
	public void メール送信アポストロフィ() throws MessagingException {
		テストデータ作成();

		List<String> emailToList = 送信先TOメールアドレスリスト作成();
		List<String> emailCcList = 送信先CCメールアドレスリスト作成();
		List<String> emailBccList = 送信先BCCメールアドレスリスト作成();
		List<String> mailSubjectRepalceValueList = メール件名置換リスト作成_アポストロフィ();
		List<String> mailTextRepalceValueList = メール本文置換リスト作成_アポストロフィ();
		BounceMailHeaderDto bounceMailHeaderDto = バウンスメールヘッダーDTO作成();
		try {
			commonSendMail.findMailTemplateMasterAndSendMail(ServiceCategory.見積, ProcessCategory.承認依頼.toString(), 1L, emailToList, emailCcList, emailBccList, mailSubjectRepalceValueList, mailTextRepalceValueList, null, bounceMailHeaderDto);
		} catch (Exception e) {
			Assert.fail("異常終了");
		}

		// 複数添付あり用のメソッドを通る場合
		List<String> uploadFileList = new ArrayList<>();
		String path = new File(".").getAbsoluteFile().getParent();
		uploadFileList.add(path + "/src/test/resources/dummyFile/請求書_201909.pdf");
		uploadFileList.add(path + "/src/test/resources/dummyFile/電力使用料金明細書_201909.xlsx");
		try {
			commonSendMail.findMailTemplateMasterAndSendMailAndAttachedFiles(1L, emailToList, emailCcList, emailBccList, mailSubjectRepalceValueList, mailTextRepalceValueList, uploadFileList, bounceMailHeaderDto);
		} catch (Exception e) {
			Assert.fail("異常終了");
		}
	}

	@Test
	public void 独自ヘッダー付与テスト() throws Exception {
		MimeMessage attachedMsg = javaMailSender.createMimeMessage();
		MimeMessageHelper attachedHelper = new MimeMessageHelper(attachedMsg, true, StandardCharsets.UTF_8.name());

		String[] toEmail = { "to" };
		attachedHelper.setTo(toEmail);
		attachedHelper.setFrom("from");
		attachedHelper.setSubject("subject");
		attachedHelper.setText("text");

		SMTPMessage SMTPMessage = new SMTPMessage(attachedMsg);

		MailTemplateMaster mailTemplateMaster = new MailTemplateMaster();
		mailTemplateMaster.setId(1L);

		BounceMailHeaderDto bounceMailHeaderDto = バウンスメールヘッダーDTO作成();

		Method method = CommonSendMail.class.getDeclaredMethod("setOriginalHeader", SMTPMessage.class, MailTemplateMaster.class, BounceMailHeaderDto.class);
		method.setAccessible(true);

		SMTPMessage actual = (SMTPMessage) method.invoke(commonSendMail, SMTPMessage, mailTemplateMaster, bounceMailHeaderDto);

		Assert.assertEquals("契約IDが正しく設定されていること", "E000000001", actual.getHeader("ContractId")[0]);
		Assert.assertEquals("文書番号が正しく設定されていること", "CC2020102800001", actual.getHeader("DocNumber")[0]);
		Assert.assertEquals("契約番号が正しく設定されていること", "CIC2020102800001", actual.getHeader("ContractNumber")[0]);
		Assert.assertEquals("契約番号枝番が正しく設定されていること", "1", actual.getHeader("ContractBranchNumber")[0]);
	}

	@Test
	public void メール送信リスト置換リストあり() throws MessagingException {
		テストデータ作成();

		List<String> emailToList = 送信先TOメールアドレスリスト作成();
		List<String> emailCcList = 送信先CCメールアドレスリスト作成();
		List<String> emailBccList = 送信先BCCメールアドレスリスト作成();
		List<String> mailSubjectRepalceValueList = メール件名置換リスト作成();
		List<String> mailTextRepalceValueList = メール本文置換リスト作成();
		List<List<String>> mailTextRepalceListValues = メール本文リスト置換リスト作成();

		BounceMailHeaderDto bounceMailHeaderDto = バウンスメールヘッダーDTO作成();
		try {
			commonSendMail.findMailTemplateMasterAndSendMail(12L, emailToList, emailCcList, emailBccList, mailSubjectRepalceValueList, mailTextRepalceValueList, mailTextRepalceListValues, null, bounceMailHeaderDto);
		} catch (Exception e) {
			Assert.fail("異常終了");
		}
	}

	@Test
	public void メール送信リスト置換リストあり_Null値あり() throws MessagingException {
		テストデータ作成();

		List<String> emailToList = 送信先TOメールアドレスリスト作成();
		List<String> emailCcList = 送信先CCメールアドレスリスト作成();
		List<String> emailBccList = 送信先BCCメールアドレスリスト作成();
		List<String> mailSubjectRepalceValueList = メール件名置換リスト作成();
		List<String> mailTextRepalceValueList = メール本文置換リスト作成();
		List<List<String>> mailTextRepalceListValues = メール本文リスト置換リスト作成Null値あり();

		BounceMailHeaderDto bounceMailHeaderDto = バウンスメールヘッダーDTO作成();
		try {
			commonSendMail.findMailTemplateMasterAndSendMail(12L, emailToList, emailCcList, emailBccList, mailSubjectRepalceValueList, mailTextRepalceValueList, mailTextRepalceListValues, null, bounceMailHeaderDto);
		} catch (Exception e) {
			Assert.fail("異常終了");
		}
	}

	@Test
	public void メール送信リストマッピング最大件数テスト() throws MessagingException {
		テストデータ作成();

		List<String> emailToList = 送信先TOメールアドレスリスト作成();
		List<String> emailCcList = 送信先CCメールアドレスリスト作成();
		List<String> emailBccList = 送信先BCCメールアドレスリスト作成();
		List<String> mailSubjectRepalceValueList = メール件名置換リスト作成();
		List<String> mailTextRepalceValueList = メール本文置換リスト最大件数作成();
		List<List<String>> mailTextRepalceListValues = メール本文リスト置換リスト最大件数作成();

		BounceMailHeaderDto bounceMailHeaderDto = バウンスメールヘッダーDTO作成();
		try {
			commonSendMail.findMailTemplateMasterAndSendMail(13L, emailToList, emailCcList, emailBccList, mailSubjectRepalceValueList, mailTextRepalceValueList, mailTextRepalceListValues, null, bounceMailHeaderDto);
		} catch (Exception e) {
			Assert.fail("異常終了");
		}
	}

	@Test
	public void メール送信_CC_BCCなし() throws MessagingException {
		テストデータ作成();

		List<String> emailToList = 送信先TOメールアドレスリスト作成();
		List<String> mailSubjectRepalceValueList = メール件名置換リスト作成();
		List<String> mailTextRepalceValueList = メール本文置換リスト作成();
		BounceMailHeaderDto bounceMailHeaderDto = バウンスメールヘッダーDTO作成();
		try {
			commonSendMail.findMailTemplateMasterAndSendMail(ServiceCategory.見積, ProcessCategory.承認依頼.toString(), null, new ArrayList<String>(), new ArrayList<String>(), emailToList, mailSubjectRepalceValueList, mailTextRepalceValueList, null, bounceMailHeaderDto);
		} catch (Exception e) {
			Assert.fail("異常終了");
		}
	}

	private void テストデータ作成() {
		context.getBean(DBConfig.class).initTargetTestData("sql/mail/testProductGrpMasterInsert.sql");
		context.getBean(DBConfig.class).initTargetTestData("sql/mail/testMailTemplateMasterInset.sql");
	}

	private List<String> 送信先TOメールアドレスリスト作成() {
		return IntStream.rangeClosed(1, 2).mapToObj(i -> "send_mail_to" + i + "@softcomm.co.jp").collect(Collectors.toList());
	}

	private List<String> 送信先CCメールアドレスリスト作成() {
		return IntStream.rangeClosed(1, 2).mapToObj(i -> "send_mail_cc" + i + "@softcomm.co.jp").collect(Collectors.toList());
	}

	private List<String> 送信先BCCメールアドレスリスト作成() {
		return IntStream.rangeClosed(1, 2).mapToObj(i -> "send_mail_bcc" + i + "@softcomm.co.jp").collect(Collectors.toList());
	}

	private List<String> メール件名置換リスト作成() {
		return IntStream.rangeClosed(1, 6).mapToObj(i -> "test_subject" + i).collect(Collectors.toList());
	}

	private List<String> メール本文置換リスト作成() {
		return IntStream.rangeClosed(1, 11).mapToObj(i -> "test_text" + i).collect(Collectors.toList());
	}

	private List<String> メール件名置換リスト作成Null値あり() {
		List<String> list = IntStream.rangeClosed(2, 3).mapToObj(i -> "test_subject" + i).collect(Collectors.toList());
		list.add(0, null);
		return list;
	}

	private List<String> メール本文置換リスト作成Null値あり() {
		List<String> list = IntStream.rangeClosed(2, 3).mapToObj(i -> "test_text" + i).collect(Collectors.toList());
		list.add(0, null);
		return list;
	}

	private List<String> メール件名置換リスト作成_アポストロフィ() {
		return IntStream.rangeClosed(1, 6).mapToObj(i -> "test_subject's" + i).collect(Collectors.toList());
	}

	private List<String> メール本文置換リスト作成_アポストロフィ() {
		return IntStream.rangeClosed(1, 11).mapToObj(i -> "test_text's" + i).collect(Collectors.toList());
	}

	private List<List<String>> メール本文リスト置換リスト作成() {
		return IntStream.rangeClosed(1, 3).mapToObj(i -> IntStream.rangeClosed(1, 5).mapToObj(t -> "test_list" + i + "_" + t).collect(Collectors.toList())).collect(Collectors.toList());
	}

	private List<List<String>> メール本文リスト置換リスト作成Null値あり() {
		List<List<String>> lists = IntStream.rangeClosed(2, 3).mapToObj(i -> IntStream.rangeClosed(2, 5).mapToObj(t -> "test_list" + i + "_" + t).collect(Collectors.toList())).collect(Collectors.toList());
		lists.add(0, null);
		return lists;
	}

	private List<String> メール本文置換リスト最大件数作成() {
		return IntStream.rangeClosed(1, 16).mapToObj(i -> "test_text" + i).collect(Collectors.toList());
	}

	private List<List<String>> メール本文リスト置換リスト最大件数作成() {
		return IntStream.rangeClosed(1, 3).mapToObj(i -> IntStream.rangeClosed(1, 5).mapToObj(t -> "test_list" + i + "_" + t).collect(Collectors.toList())).collect(Collectors.toList());
	}

	private BounceMailHeaderDto バウンスメールヘッダーDTO作成() {

		BounceMailHeaderDto bounceMailHeaderDto = new BounceMailHeaderDto();
		bounceMailHeaderDto.setContractId("E000000001");
		bounceMailHeaderDto.setDocNumber("CC2020102800001");
		bounceMailHeaderDto.setContractNumber("CIC2020102800001");
		bounceMailHeaderDto.setContractBranchNumber(1);

		return bounceMailHeaderDto;
	}
}
