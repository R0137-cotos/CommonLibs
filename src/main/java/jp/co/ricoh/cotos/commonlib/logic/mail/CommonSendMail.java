package jp.co.ricoh.cotos.commonlib.logic.mail;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.axis.utils.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import com.sun.mail.smtp.SMTPMessage;

import jp.co.ricoh.cotos.commonlib.dto.parameter.communication.BounceMailHeaderDto;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.ServiceCategory;
import jp.co.ricoh.cotos.commonlib.entity.master.MailTemplateMaster;
import jp.co.ricoh.cotos.commonlib.repository.master.MailTemplateMasterRepository;
import jp.co.ricoh.cotos.commonlib.util.AppProperties;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class CommonSendMail {

	@Autowired
	MailTemplateMasterRepository mailTemplateMasterRepository;

	@Autowired(required = false)
	JavaMailSender javaMailSender;

	@Autowired
	AppProperties appProperties;

	private static final Log log = LogFactory.getLog(CommonSendMail.class);

	/**
	 * メールテンプレートマスタ特定&メール送信処理
	 *
	 * <pre>
	 * 【処理内容】
	 * ・引数のサービスカテゴリー(見積、契約、手配etc)と処理カテゴリー(承認、承認依頼、承認依頼差戻etc)と商品グループマスタIDを元にメールテンプレートマスタTBL(MAIL_TEMPLATE_MASTER)からメールテンプレートマスタ情報を取得　　詳細は以下を参照
	 *  条件：
	 *    サービスカテゴリー(MAIL_TEMPLATE_MASTER.SERVICE_CATEGORY)=引数のサービスカテゴリー
	 *    処理カテゴリー(MAIL_TEMPLATE_MASTER.PROCESS_CATEGORY)=引数の処理カテゴリー
	 *    商品グループマスタID
	 * ・引数のメール件名置換リストとメールテンプレートマスタTBL.メール件名(MAIL_TEMPLATE_MASTER.MAIL_SUBJECT)を元にメール件名作成
	 *  例：
	 *    メールテンプレートマスタTBL.メール件名(MAIL_TEMPLATE_MASTER.MAIL_SUBJECT)
	 *     【{{replaceValue1}}】見積承認依頼メール {{replaceValue2}}
	 *    メール件名置換リスト
	 *     テスト1,テスト2,テスト3
	 *    各値が上記の場合、以下が生成されるメール件名
	 *     【テスト1】見積承認依頼メール テスト2
	 * ・引数のメール本文置換リストとメールテンプレートマスタTBL.メール本文(MAIL_TEMPLATE_MASTER.MAIL_BODY)を元にメール本文作成
	 *  ※設定できる引数の数がメール件名と異なるだけで、文字列生成方法はメール件名と同一
	 * ・引数のバウンスメールヘッダーDTOを使用し、バウンスメール処理のための独自ヘッダーを付与
	 * ・引数のToメールアドレスリストとCCメールアドレスリストと上記で作成したメール件名やメール本文を使用してメール送信
	 * ・送信元メールアドレスは、メールテンプレートマスタTBL.送信元メールアドレス(MAIL_TEMPLATE_MASTER.SEND_FROM_MAIL_ADDRESS)から取得
	 * ・メールは文字コードをUTF-8で作成しており、ファイル添付も可能
	 * </pre>
	 *
	 * @param emailToList
	 *            Toメールアドレスリスト(複数設定可能)
	 * @param emailCcList
	 *            CCメールアドレスリスト(複数設定可能)
	 * @param serviceCategory
	 *            サービスカテゴリー
	 * @param processCategory
	 *            処理カテゴリー
	 * @param mailSubjectRepalceValueList
	 *            メール件名置換リスト(最大5個まで)
	 * @param mailTextRepalceValueList
	 *            メール本文置換リスト(最大15個まで)
	 * @param uploadFile
	 *            添付ファイル
	 * @param bounceMailHeaderDto
	 *            バウンスメールヘッダーDTO
	 * @throws MessagingException
	 * @throws IOException
	 */
	public void findMailTemplateMasterAndSendMail(ServiceCategory serviceCategory, String processCategory, Long productGrpMasterId, List<String> emailToList, List<String> emailCcList, List<String> emailBccList, List<String> mailSubjectRepalceValueList, List<String> mailTextRepalceValueList, String uploadFile, BounceMailHeaderDto bounceMailHeaderDto) throws MessagingException, IOException {
		MailTemplateMaster mailTemplateMaster = mailTemplateMasterRepository.findByServiceCategoryAndProcessCategoryAndProductGrpMasterId(serviceCategory.toString(), processCategory, productGrpMasterId != null ? productGrpMasterId : 0L);
		sendMail(emailToList, emailCcList, emailBccList, mailTemplateMaster, mailSubjectRepalceValueList, mailTextRepalceValueList, null, uploadFile, bounceMailHeaderDto);
	}

	/**
	 * メールテンプレートマスタ特定&メール送信処理
	 *
	 * <pre>
	 * 【処理内容】
	 * ・引数のメールテンプレートマスタIDを元にメールテンプレートマスタTBL(MAIL_TEMPLATE_MASTER)からメールテンプレートマスタ情報を取得
	 *  条件：
	 *    メールテンプレートマスタID(MAIL_TEMPLATE_MASTER.ID)=引数のメールテンプレートマスタID
	 * ・引数のメール件名置換リストとメールテンプレートマスタTBL.メール件名(MAIL_TEMPLATE_MASTER.MAIL_SUBJECT)を元にメール件名作成
	 *  例：
	 *    メールテンプレートマスタTBL.メール件名(MAIL_TEMPLATE_MASTER.MAIL_SUBJECT)
	 *     【{{replaceValue1}}】見積承認依頼メール {{replaceValue2}}
	 *    メール件名置換リスト
	 *     テスト1,テスト2,テスト3
	 *    各値が上記の場合、以下が生成されるメール件名
	 *     【テスト1】見積承認依頼メール テスト2
	 * ・引数のメール本文置換リストとメールテンプレートマスタTBL.メール本文(MAIL_TEMPLATE_MASTER.MAIL_BODY)を元にメール本文作成
	 *  ※設定できる引数の数がメール件名と異なるだけで、文字列生成方法はメール件名と同一
	 * ・引数のバウンスメールヘッダーDTOを使用し、バウンスメール処理のための独自ヘッダーを付与
	 * ・引数のToメールアドレスリストとCCメールアドレスリストと上記で作成したメール件名やメール本文を使用してメール送信
	 * ・送信元メールアドレスは、メールテンプレートマスタTBL.送信元メールアドレス(MAIL_TEMPLATE_MASTER.SEND_FROM_MAIL_ADDRESS)から取得
	 * ・メールは文字コードをUTF-8で作成しており、ファイル添付も可能
	 * </pre>
	 *
	 * @param mailTemplateMasterId
	 *            メールテンプレートマスタID
	 * @param emailToList
	 *            Toメールアドレスリスト(複数設定可能)
	 * @param emailCcList
	 *            CCメールアドレスリスト(複数設定可能)
	 * @param mailSubjectRepalceValueList
	 *            メール件名置換リスト(最大5個まで)
	 * @param mailTextRepalceValueList
	 *            メール本文置換リスト(最大15個まで)
	 * @param uploadFile
	 *            添付ファイル
	 * @param bounceMailHeaderDto
	 *            バウンスメールヘッダーDTO
	 * @throws MessagingException
	 * @throws IOException
	 */
	public void findMailTemplateMasterAndSendMail(long mailTemplateMasterId, List<String> emailToList, List<String> emailCcList, List<String> emailBccList, List<String> mailSubjectRepalceValueList, List<String> mailTextRepalceValueList, String uploadFile, BounceMailHeaderDto bounceMailHeaderDto) throws MessagingException, IOException {
		MailTemplateMaster mailTemplateMaster = mailTemplateMasterRepository.findOne(mailTemplateMasterId);
		sendMail(emailToList, emailCcList, emailBccList, mailTemplateMaster, mailSubjectRepalceValueList, mailTextRepalceValueList, null, uploadFile, bounceMailHeaderDto);
	}

	/**
	 * メールテンプレートマスタ特定&メール送信処理
	 *
	 * <pre>
	 * 【処理内容】
	 * ・引数のメールテンプレートマスタIDを元にメールテンプレートマスタTBL(MAIL_TEMPLATE_MASTER)からメールテンプレートマスタ情報を取得
	 *  条件：
	 *    メールテンプレートマスタID(MAIL_TEMPLATE_MASTER.ID)=引数のメールテンプレートマスタID
	 * ・引数のメール件名置換リストとメールテンプレートマスタTBL.メール件名(MAIL_TEMPLATE_MASTER.MAIL_SUBJECT)を元にメール件名作成
	 *  例：
	 *    メールテンプレートマスタTBL.メール件名(MAIL_TEMPLATE_MASTER.MAIL_SUBJECT)
	 *     【{{replaceValue1}}】見積承認依頼メール {{replaceValue2}}
	 *    メール件名置換リスト
	 *     テスト1,テスト2,テスト3
	 *    各値が上記の場合、以下が生成されるメール件名
	 *     【テスト1】見積承認依頼メール テスト2
	 * ・引数のメール本文置換リストとメールテンプレートマスタTBL.メール本文(MAIL_TEMPLATE_MASTER.MAIL_BODY)を元にメール本文作成
	 *  ※文字列生成方法はメール件名と同じだが、メール本文ではリストデータの置換も可能としている。
	 *  例：
	 *  　メールテンプレートマスタTBL.メール本文(MAIL_TEMPLATE_MASTER.MAIL_BODY)
	 *  　　商品名：{{replaceValue1}}
	 *  　　▼品種名
	 *  　　{{replaceList1}}
	 *  　メール本文置換リスト
	 *  　　テスト商品
	 *  　メール本文リスト置換リスト
	 *  　　{テスト品種1,テスト品種2,テスト品種3}
	 *  　各値が上記の場合、以下が生成されるメール本文
	 *  　　商品名：テスト商品
	 *  　　▼品種名
	 *  　　テスト品種1
	 *  　　テスト品種2
	 *  　　テスト品種3
	 * ・引数のバウンスメールヘッダーDTOを使用し、バウンスメール処理のための独自ヘッダーを付与
	 * ・引数のToメールアドレスリストとCCメールアドレスリストと上記で作成したメール件名やメール本文を使用してメール送信
	 * ・送信元メールアドレスは、メールテンプレートマスタTBL.送信元メールアドレス(MAIL_TEMPLATE_MASTER.SEND_FROM_MAIL_ADDRESS)から取得
	 * ・メールは文字コードをUTF-8で作成しており、ファイル添付も可能
	 * </pre>
	 *
	 * @param mailTemplateMasterId
	 *            メールテンプレートマスタID
	 * @param emailToList
	 *            Toメールアドレスリスト(複数設定可能)
	 * @param emailCcList
	 *            CCメールアドレスリスト(複数設定可能)
	 * @param mailSubjectRepalceValueList
	 *            メール件名置換リスト(最大5個まで)
	 * @param mailTextRepalceValueList
	 *            メール本文置換リスト(最大15個まで)
	 * @param mailTextRepalceListValues
	 *            メール本文リスト置換リスト(最大3個まで)
	 * @param uploadFile
	 *            添付ファイル
	 * @param bounceMailHeaderDto
	 *            バウンスメールヘッダーDTO
	 * @throws MessagingException
	 * @throws IOException
	 */
	public void findMailTemplateMasterAndSendMail(long mailTemplateMasterId, List<String> emailToList, List<String> emailCcList, List<String> emailBccList, List<String> mailSubjectRepalceValueList, List<String> mailTextRepalceValueList, List<List<String>> mailTextRepalceListValues, String uploadFile, BounceMailHeaderDto bounceMailHeaderDto) throws MessagingException, IOException {
		MailTemplateMaster mailTemplateMaster = mailTemplateMasterRepository.findOne(mailTemplateMasterId);
		sendMail(emailToList, emailCcList, emailBccList, mailTemplateMaster, mailSubjectRepalceValueList, mailTextRepalceValueList, mailTextRepalceListValues, uploadFile, bounceMailHeaderDto);
	}

	/**
	 * メールテンプレートマスタ特定&メール送信処理
	 *
	 * <pre>
	 * 【処理内容】
	 * ・引数のメールテンプレートマスタIDを元にメールテンプレートマスタTBL(MAIL_TEMPLATE_MASTER)からメールテンプレートマスタ情報を取得
	 *  条件：
	 *    メールテンプレートマスタID(MAIL_TEMPLATE_MASTER.ID)=引数のメールテンプレートマスタID
	 * ・引数のメール件名置換リストとメールテンプレートマスタTBL.メール件名(MAIL_TEMPLATE_MASTER.MAIL_SUBJECT)を元にメール件名作成
	 *  例：
	 *    メールテンプレートマスタTBL.メール件名(MAIL_TEMPLATE_MASTER.MAIL_SUBJECT)
	 *     【{{replaceValue1}}】見積承認依頼メール {{replaceValue2}}
	 *    メール件名置換リスト
	 *     テスト1,テスト2,テスト3
	 *    各値が上記の場合、以下が生成されるメール件名
	 *     【テスト1】見積承認依頼メール テスト2
	 * ・引数のメール本文置換リストとメールテンプレートマスタTBL.メール本文(MAIL_TEMPLATE_MASTER.MAIL_BODY)を元にメール本文作成
	 *  ※設定できる引数の数がメール件名と異なるだけで、文字列生成方法はメール件名と同一
	 * ・引数のバウンスメールヘッダーDTOを使用し、バウンスメール処理のための独自ヘッダーを付与
	 * ・引数のToメールアドレスリストとCCメールアドレスリストと上記で作成したメール件名やメール本文を使用してメール送信
	 * ・送信元メールアドレスは、メールテンプレートマスタTBL.送信元メールアドレス(MAIL_TEMPLATE_MASTER.SEND_FROM_MAIL_ADDRESS)から取得
	 * ・メールは文字コードをUTF-8で作成しており、複数のファイル添付も可能
	 * </pre>
	 *
	 * @param mailTemplateMasterId
	 *            メールテンプレートマスタID
	 * @param emailToList
	 *            Toメールアドレスリスト(複数設定可能)
	 * @param emailCcList
	 *            CCメールアドレスリスト(複数設定可能)
	 * @param mailSubjectRepalceValueList
	 *            メール件名置換リスト(最大5個まで)
	 * @param mailTextRepalceValueList
	 *            メール本文置換リスト(最大15個まで)
	 * @param uploadFileList
	 *            添付ファイル(複数)
	 * @param bounceMailHeaderDto
	 *            バウンスメールヘッダーDTO
	 * @throws MessagingException
	 * @throws IOException
	 */
	public void findMailTemplateMasterAndSendMailAndAttachedFiles(long mailTemplateMasterId, List<String> emailToList, List<String> emailCcList, List<String> emailBccList, List<String> mailSubjectRepalceValueList, List<String> mailTextRepalceValueList, List<String> uploadFileList, BounceMailHeaderDto bounceMailHeaderDto) throws MessagingException, IOException {
		MailTemplateMaster mailTemplateMaster = mailTemplateMasterRepository.findOne(mailTemplateMasterId);
		sendMail(emailToList, emailCcList, emailBccList, mailTemplateMaster, mailSubjectRepalceValueList, mailTextRepalceValueList, null, uploadFileList, bounceMailHeaderDto);
	}

	/**
	 * メールテンプレートマスタ特定&メール送信処理
	 *
	 * <pre>
	 * 【処理内容】
	 * ・引数のメールテンプレートマスタIDを元にメールテンプレートマスタTBL(MAIL_TEMPLATE_MASTER)からメールテンプレートマスタ情報を取得
	 *  条件：
	 *    メールテンプレートマスタID(MAIL_TEMPLATE_MASTER.ID)=引数のメールテンプレートマスタID
	 * ・引数のメール件名置換リストとメールテンプレートマスタTBL.メール件名(MAIL_TEMPLATE_MASTER.MAIL_SUBJECT)を元にメール件名作成
	 *  例：
	 *    メールテンプレートマスタTBL.メール件名(MAIL_TEMPLATE_MASTER.MAIL_SUBJECT)
	 *     【{{replaceValue1}}】見積承認依頼メール {{replaceValue2}}
	 *    メール件名置換リスト
	 *     テスト1,テスト2,テスト3
	 *    各値が上記の場合、以下が生成されるメール件名
	 *     【テスト1】見積承認依頼メール テスト2
	 * ・引数のメール本文置換リストとメールテンプレートマスタTBL.メール本文(MAIL_TEMPLATE_MASTER.MAIL_BODY)を元にメール本文作成
	 *  ※文字列生成方法はメール件名と同じだが、メール本文ではリストデータの置換も可能としている。
	 *  例：
	 *  　メールテンプレートマスタTBL.メール本文(MAIL_TEMPLATE_MASTER.MAIL_BODY)
	 *  　　商品名：{{replaceValue1}}
	 *  　　▼品種名
	 *  　　{{replaceList1}}
	 *  　メール本文置換リスト
	 *  　　テスト商品
	 *  　メール本文リスト置換リスト
	 *  　　{テスト品種1,テスト品種2,テスト品種3}
	 *  　各値が上記の場合、以下が生成されるメール本文
	 *  　　商品名：テスト商品
	 *  　　▼品種名
	 *  　　テスト品種1
	 *  　　テスト品種2
	 *  　　テスト品種3
	 * ・引数のバウンスメールヘッダーDTOを使用し、バウンスメール処理のための独自ヘッダーを付与
	 * ・引数のToメールアドレスリストとCCメールアドレスリストと上記で作成したメール件名やメール本文を使用してメール送信
	 * ・送信元メールアドレスは、メールテンプレートマスタTBL.送信元メールアドレス(MAIL_TEMPLATE_MASTER.SEND_FROM_MAIL_ADDRESS)から取得
	 * ・メールは文字コードをUTF-8で作成しており、複数のファイル添付も可能
	 * </pre>
	 *
	 * @param mailTemplateMasterId
	 *            メールテンプレートマスタID
	 * @param emailToList
	 *            Toメールアドレスリスト(複数設定可能)
	 * @param emailCcList
	 *            CCメールアドレスリスト(複数設定可能)
	 * @param mailSubjectRepalceValueList
	 *            メール件名置換リスト(最大5個まで)
	 * @param mailTextRepalceValueList
	 *            メール本文置換リスト(最大15個まで)
	 * @param mailTextRepalceListValues
	 *            メール本文リスト置換リスト(最大3個まで)
	 * @param uploadFileList
	 *            添付ファイル(複数)
	 * @param bounceMailHeaderDto
	 *            バウンスメールヘッダーDTO
	 * @throws MessagingException
	 * @throws IOException
	 */
	public void findMailTemplateMasterAndSendMailAndAttachedFiles(long mailTemplateMasterId, List<String> emailToList, List<String> emailCcList, List<String> emailBccList, List<String> mailSubjectRepalceValueList, List<String> mailTextRepalceValueList, List<List<String>> mailTextRepalceListValues, List<String> uploadFileList, BounceMailHeaderDto bounceMailHeaderDto) throws MessagingException, IOException {
		MailTemplateMaster mailTemplateMaster = mailTemplateMasterRepository.findOne(mailTemplateMasterId);
		sendMail(emailToList, emailCcList, emailBccList, mailTemplateMaster, mailSubjectRepalceValueList, mailTextRepalceValueList, mailTextRepalceListValues, uploadFileList, bounceMailHeaderDto);
	}

	/**
	 * メール送信処理_添付ファイルあり
	 *
	 * @param emailTo
	 *            Toメールアドレス
	 * @param emailCcList
	 *            CCメールアドレスリスト
	 * @param mailTemplateMaster
	 *            メールテンプレートマスタ
	 * @param mailSubjectRepalceValueList
	 *            メール件名置換リスト(最大5個まで)
	 * @param mailTextRepalceValueList
	 *            メール本文置換リスト(最大15個まで)
	 * @param mailTextRepalceListValues
	 *            メール本文リスト置換リスト(最大3個まで)
	 * @param uploadFile
	 *            添付ファイル
	 * @param bounceMailHeaderDto
	 *            バウンスメールヘッダーDTO
	 * @throws MessagingException
	 * @throws IOException
	 */
	@Async
	private void sendMail(List<String> emailToList, List<String> emailCcList, List<String> emailBccList, MailTemplateMaster mailTemplateMaster, List<String> mailSubjectRepalceValueList, List<String> mailTextRepalceValueList, List<List<String>> mailTextRepalceListValues, String uploadFile, BounceMailHeaderDto bounceMailHeaderDto) throws MessagingException, IOException {
		MimeMessage attachedMsg = javaMailSender.createMimeMessage();
		attachedMsg.setHeader("Content-Transfer-Encoding", "base64");
		MimeMessageHelper attachedHelper = new MimeMessageHelper(attachedMsg, true, StandardCharsets.UTF_8.name());

		Writer writerMailSubject = createMailSubject(mailTemplateMaster, mailSubjectRepalceValueList);
		Writer writerMailText = createMailText(mailTemplateMaster, mailTextRepalceValueList, mailTextRepalceListValues);

		String[] toEmail = (String[]) emailToList.toArray(new String[0]);
		String[] ccEmail = (String[]) emailCcList.toArray(new String[0]);
		String[] bccEmail = (String[]) emailBccList.toArray(new String[0]);
		attachedHelper.setTo(toEmail);
		attachedHelper.setFrom(appProperties.getMailProperties().getFromMailAddress());
		attachedHelper.setCc(ccEmail);
		attachedHelper.setBcc(bccEmail);
		String subject = writerMailSubject.toString().replace("&#10;", "\r\n").replace("&#39;", "\'").replace("&quot;", "\"").replace("&amp;", "&").replace("&lt;", "<").replace("&#61;", "=").replace("&gt;", ">").replace("&#96;", "`");
		
		attachedHelper.setSubject(subject);
		String text = writerMailText.toString().replace("&#10;", "\r\n").replace("&#39;", "\'").replace("&quot;", "\"").replace("&amp;", "&").replace("&lt;", "<").replace("&#61;", "=").replace("&gt;", ">").replace("&#96;", "`");
		
		attachedHelper.setText(text);

		if (null != uploadFile) {
			FileSystemResource res = new FileSystemResource(uploadFile);
			attachedHelper.addAttachment(res.getFilename(), res);
		}
		SMTPMessage SMTPMessage = new SMTPMessage(attachedMsg);
		SMTPMessage.setEnvelopeFrom(Optional.ofNullable(mailTemplateMaster.getEnvelopeFrom()).orElse(appProperties.getMailProperties().getEnvelopeFromMailAddress()));
		setOriginalHeader(SMTPMessage, mailTemplateMaster, bounceMailHeaderDto);
		// ログにTo、Cc、Bccのメールアドレスを出力
		outputLog(SMTPMessage);
		javaMailSender.send(SMTPMessage);
	}

	/**
	 * メール送信処理_複数添付ファイルあり
	 *
	 * @param emailTo
	 *            Toメールアドレス
	 * @param emailCcList
	 *            CCメールアドレスリスト
	 * @param mailTemplateMaster
	 *            メールテンプレートマスタ
	 * @param mailSubjectRepalceValueList
	 *            メール件名置換リスト(最大5個まで)
	 * @param mailTextRepalceValueList
	 *            メール本文置換リスト(最大15個まで)
	 * @param mailTextRepalceListValues
	 *            メール本文リスト置換リスト(最大3個まで)
	 * @param uploadFileList
	 *            添付ファイル(複数)
	 * @param bounceMailHeaderDto
	 *            バウンスメールヘッダーDTO
	 * @throws MessagingException
	 * @throws IOException
	 */
	@Async
	private void sendMail(List<String> emailToList, List<String> emailCcList, List<String> emailBccList, MailTemplateMaster mailTemplateMaster, List<String> mailSubjectRepalceValueList, List<String> mailTextRepalceValueList, List<List<String>> mailTextRepalceListValues, List<String> uploadFileList, BounceMailHeaderDto bounceMailHeaderDto) throws MessagingException, IOException {
		MimeMessage attachedMsg = javaMailSender.createMimeMessage();
		attachedMsg.setHeader("Content-Transfer-Encoding", "base64");
		MimeMessageHelper attachedHelper = new MimeMessageHelper(attachedMsg, true, StandardCharsets.UTF_8.name());

		Writer writerMailSubject = createMailSubject(mailTemplateMaster, mailSubjectRepalceValueList);
		Writer writerMailText = createMailText(mailTemplateMaster, mailTextRepalceValueList, mailTextRepalceListValues);

		String[] toEmail = (String[]) emailToList.toArray(new String[0]);
		String[] ccEmail = (String[]) emailCcList.toArray(new String[0]);
		String[] bccEmail = (String[]) emailBccList.toArray(new String[0]);
		attachedHelper.setTo(toEmail);
		attachedHelper.setFrom(Optional.ofNullable(mailTemplateMaster.getFromMailAddress()).orElse(appProperties.getMailProperties().getFromMailAddress()));
		attachedHelper.setCc(ccEmail);
		attachedHelper.setBcc(bccEmail);
		String subject = writerMailSubject.toString().replace("&#10;", "\r\n").replace("&#39;", "\'");
		attachedHelper.setSubject(subject);
		String text = writerMailText.toString().replace("&#10;", "\r\n").replace("&#39;", "\'");
		attachedHelper.setText(text);

		if (null != uploadFileList) {
			for (String uploadFile : uploadFileList) {
				FileSystemResource res = new FileSystemResource(uploadFile);
				attachedHelper.addAttachment(res.getFilename(), res);
			}
		}
		SMTPMessage SMTPMessage = new SMTPMessage(attachedMsg);
		SMTPMessage.setEnvelopeFrom(Optional.ofNullable(mailTemplateMaster.getEnvelopeFrom()).orElse(appProperties.getMailProperties().getEnvelopeFromMailAddress()));
		setOriginalHeader(SMTPMessage, mailTemplateMaster, bounceMailHeaderDto);
		// ログにTo、Cc、Bccのメールアドレスを出力
		outputLog(SMTPMessage);
		javaMailSender.send(SMTPMessage);
	}

	/**
	 * メール件名作成
	 *
	 * @param mailTemplateMaster
	 *            エラーテンプレートマスタ
	 * @param mailSubjectRepalceValueList
	 *            メール件名置換リスト(最大5個まで)
	 * @return Writer
	 */
	public Writer createMailSubject(MailTemplateMaster mailTemplateMaster, List<String> mailSubjectRepalceValueList) {
		Writer writer = new StringWriter();

		String stringMailSubject = mailTemplateMaster.getMailSubject();
		MustacheFactory mustacheFactory = new DefaultMustacheFactory();
		Mustache mustacheMailSubject = mustacheFactory.compile(new StringReader(stringMailSubject), stringMailSubject);
		MailSubject mailSubject = new MailSubject(mailSubjectRepalceValueList);
		mustacheMailSubject.execute(writer, mailSubject);

		return writer;
	}

	/**
	 * メール本文作成
	 *
	 * @param mailTemplateMaster
	 *            エラーテンプレートマスタ
	 * @param mailTextRepalceValueList
	 *            メール本文置換リスト(最大15個まで)
	 * @return Writer
	 */
	public Writer createMailText(MailTemplateMaster mailTemplateMaster, List<String> mailTextRepalceValueList, List<List<String>> mailTextRepalceListValues) {
		Writer writer = new StringWriter();

		String stringMailText = mailTemplateMaster.getMailBody();
		MustacheFactory mustacheFactory = new DefaultMustacheFactory();
		Mustache mustacheMailText = mustacheFactory.compile(new StringReader(stringMailText), stringMailText);
		MailText mailText = new MailText(mailTextRepalceValueList, mailTextRepalceListValues);
		mustacheMailText.execute(writer, mailText);

		return writer;
	}

	/**
	 * 独自ヘッダー付与
	 *
	 * @param SMTPMessage
	 *            SMTPメッセージ
	 * @param mailTemplateMaster
	 *            メールテンプレートマスタ
	 * @param bounceMailHeaderDto
	 *            バウンスメールヘッダーDTO
	 * @return SMTPMessage
	 * @throws MessagingException
	 * @throws IOException
	 */
	private SMTPMessage setOriginalHeader(SMTPMessage SMTPMessage, MailTemplateMaster mailTemplateMaster, BounceMailHeaderDto bounceMailHeaderDto) throws MessagingException, IOException {
		bounceMailHeaderDto.setMailTemplateMasterId(mailTemplateMaster.getId());

		ObjectMapper mapper = new ObjectMapper();

		// オブジェクト→JSON
		String json = mapper.writeValueAsString(bounceMailHeaderDto);

		// JSON→Map
		Map<String, String> map = new HashMap<String, String>();
		map = mapper.readValue(json, new TypeReference<Map<String, String>>() {
		});

		for (Entry<String, String> entry : map.entrySet()) {
			if (!StringUtils.isEmpty(entry.getValue())) {
				SMTPMessage.setHeader(entry.getKey(), entry.getValue());
			}
		}

		return SMTPMessage;
	}

	/**
	 * ログにTo、Cc、Bccのメールアドレスを出力します。
	 *
	 * @param smtpMessage SMTPメッセージ
	 * @throws MessagingException
	 */
	private void outputLog(SMTPMessage smtpMessage) throws MessagingException {
		// ログ出力用にToメールアドレスを取得
		Address[] toList = smtpMessage.getRecipients(Message.RecipientType.TO);
		if (toList != null) {
			Arrays.stream(toList).forEach(a -> log.info("メールアドレスTo ：" + a.toString()));
		}
		// ログ出力用にCcメールアドレスを取得
		Address[] ccList = smtpMessage.getRecipients(Message.RecipientType.CC);
		if (ccList != null) {
			Arrays.stream(ccList).forEach(a -> log.info("メールアドレスCc ：" + a.toString()));
		}
		// ログ出力用にBccメールアドレスを取得
		Address[] bccList = smtpMessage.getRecipients(Message.RecipientType.BCC);
		if (bccList != null) {
			Arrays.stream(bccList).forEach(a -> log.info("メールアドレスBcc：" + a.toString()));
		}
	}
}
