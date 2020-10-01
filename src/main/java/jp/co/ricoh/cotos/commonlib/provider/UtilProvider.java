package jp.co.ricoh.cotos.commonlib.provider;

import java.util.Map;

import jp.co.ricoh.cotos.commonlib.logic.check.CheckUtil;
import jp.co.ricoh.cotos.commonlib.logic.message.MessageUtil;

/**
 *
 * Utilクラスのインスタンスを生成、返却する
 *
 */
public class UtilProvider {

	private static MessageUtil messageUtil;

	private static CheckUtil checkUtil;

	private UtilProvider() {
	}

	/**
	 * MessageUtilを取得
	 * @return MessageUtil
	 */
	public static MessageUtil getMessageUtil() {
		if (messageUtil == null) {
			messageUtil = createMessageUtil();
		}
		return messageUtil;
	}

	/**
	 * CheckUtilを取得
	 * @return CheckUtil
	 */
	public static CheckUtil getCheckUtil() {
		if (checkUtil == null) {
			checkUtil = createCheckUtil();
		}
		return checkUtil;
	}

	/**
	 * MessageUtilを生成
	 * @return MessageUtil
	 */
	private static MessageUtil createMessageUtil() {

		MessageUtil messageUtil = new MessageUtil();
		Map<String, String> messageProperties = LoadConfigulation.getMessageProperties();
		messageUtil.setMessageSource(messageProperties.get("basename"), messageProperties.get("defaultEncoding"));

		return messageUtil;
	}

	/**
	 * CheckUtilを生成
	 * @return CheckUtil
	 */
	private static CheckUtil createCheckUtil() {

		CheckUtil checkUtil = new CheckUtil();
		Map<String, String> messageProperties = LoadConfigulation.getMessageProperties();
		checkUtil.setMessageUtil(messageProperties.get("basename"), messageProperties.get("defaultEncoding"));

		return checkUtil;
	}
}