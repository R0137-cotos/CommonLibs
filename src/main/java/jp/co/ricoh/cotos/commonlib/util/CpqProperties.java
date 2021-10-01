package jp.co.ricoh.cotos.commonlib.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "cotos.cpq")
public class CpqProperties {
	/**
	 * CPQのログインユーザー
	 */
	String user;
	/**
	 * CPQのログインパスワード
	 */
	String password;
	/**
	 * CPQの環境情報（本番：ricoh、ステージングor開発：ricohtest1）
	 */
	String environment;
	/**
	 * CPQのドメイン情報
	 */
	String url;
	/**
	 * CPQのコマース(プロセス）情報
	 */
	String resoure;
	 /**
	 * 再積上げ時にコールするAPI情報
	 */
	String createCpqDestinationUrl;
	/**
	 * 再積上げ時にコールするAPI情報
	 */
	String createCpqDestinationUrl;
	/**
	 * 契約更新時にコールするAPI情報
	 */
	String updateAssets;
	/**
	 * 契約更新時にコールするAPI情報
	 */
	String createReconfigUrl;
	/**
	 * 見積コピー時にコールするAPI情報
	 */
	String saveTransaction;
	/**
	 * 見積コピー時にコールするAPI情報
	 */
	String copyTransaction;
	/**
	 * CPQの管理者ログインユーザー
	 */
	String adminUser;
	/**
	 * CPQの管理者ログインパスワード
	 */
	String adminPassword;
	/**
	 * コマース遷移URL生成時にコールするAPI情報
	 */
	String createCommerceUrl;
	/**
	 * トランザクション構成情報作成時にコールするAPI情報
	 */
	String createConfigure;
	/**
	 * 構成情報の紐づけ時にコールするAPI情報
	 */
	String addToTxn;
	/**
	 * 構成情報の紐づけ時にコールするAPI情報
	 */
	String cleanSaveTransaction;
	/**
	 * SVP移行データ作成時にコールするAPI情報
	 */
	String migrationSvPack;
	/**
	 * CPQのドキュメント情報
	 */
	String documentId;
	/**
	 * PCP移行データ作成時のダミートランザクションID情報
	 */
	String pcpDummyTransactionId;
	/**
	 * SVP移行データ作成時のダミートランザクションID情報
	 */
	String svpDummyTransactionId;
}
