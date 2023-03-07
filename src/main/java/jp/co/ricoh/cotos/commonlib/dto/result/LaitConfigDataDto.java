package jp.co.ricoh.cotos.commonlib.dto.result;

import lombok.Data;

/**
 * BPN SACM事前設定情報取得API レスポンスのSACM手配業務.LaITコンフィグデータのDTO
 */

@Data
public class LaitConfigDataDto {

	/**
	 * AP区分
	 */
	private String apFlg;

	/**
	 * SACMマネジメントコード
	 */
	private String sacmManageCode;

	/**
	 * サービスアダプタコード
	 */
	private String laitSaCode;

	/**
	 * サービスアダプタラベル
	 */
	private String laitSaLabel;

	/**
	 * SSHDサービス
	 */
	private String sshdService;

	/**
	 * UPNPサービス
	 */
	private String upnpService;

	/**
	 * インターネットアカウント
	 */
	private String internetAccount;

	/**
	 * インターネットPASS
	 */
	private String internetPass;

	/**
	 * PPPOE_MTU
	 */
	private String pppoeMtu;

	/**
	 * GE0メディア
	 */
	private String ge0Media;

	/**
	 * LANアドレス
	 */
	private String lanAddress;

	/**
	 * LANサブネット
	 */
	private String lanSubnet;

	/**
	 * GE1P0メディア
	 */
	private String ge1p0Media;

	/**
	 * GE1P1メディア
	 */
	private String ge1p1Media;

	/**
	 * GE1P2メディア
	 */
	private String ge1p2Media;

	/**
	 * GE1P3メディア
	 */
	private String ge1p3Media;

	/**
	 * IPV6ブリッジ
	 */
	private String ipv6Bridge;

	/**
	 * マイID
	 */
	private String myId;

	/**
	 * カスタマーID
	 */
	private String customerId;

	/**
	 * PRESHAREDキー
	 */
	private String presharedKey;

	/**
	 * WLANサービス
	 */
	private String wlanService;

	/**
	 * WLANモード
	 */
	private String wlanMode;

	/**
	 * WLAN_SSID_0
	 */
	private String wlanSsid0;

	/**
	 * WLAN_HIDE_0
	 */
	private String wlanHide0;

	/**
	 * WLANセキュリティ_0
	 */
	private String wlanSecurity0;

	/**
	 * WLANチャンネル
	 */
	private String wlanChannel;

	/**
	 * WLANパス_0
	 */
	private String wlanPass0;

	/**
	 * WLAN_WEP_0
	 */
	private String wlanWep0;

	/**
	 * WLANパワー
	 */
	private String wlanPower;

	/**
	 * WLANマックスクライアント_0
	 */
	private String wlanMaxclient0;

	/**
	 * WLANサービス_5G
	 */
	private String wlanService5g;

	/**
	 * WLANモード_5G
	 */
	private String wlanMode5g;

	/**
	 * WLAN_SSID_0_5G
	 */
	private String wlanSsid05g;

	/**
	 * WLAN_HIDE_0_5G
	 */
	private String wlanHide05g;

	/**
	 * WLANセキュリティ_0_5G
	 */
	private String wlanSecurity05g;

	/**
	 * WLAN_チャンネル_5G
	 */
	private String wlanChannel5g;

	/**
	 * WLAN_PASS_0_5G
	 */
	private String wlanPass05g;

	/**
	 * WLAN_WEP_0_5G
	 */
	private String wlanWep05g;

	/**
	 * WLAN_パワー_5G
	 */
	private String wlanPower5g;

	/**
	 * WLANマックスクライアント_0_5G
	 */
	private String wlanMaxclient05g;

	/**
	 * LANセグメント
	 */
	private String lanSegment;

	/**
	 * VPN番号
	 */
	private String vpnNumber;

	/**
	 * フォワードプロトコル_0
	 */
	private String forwardProtocol0;

	/**
	 * リッスンポート_0
	 */
	private String listenPort0;

	/**
	 * フォワードアドレス_0
	 */
	private String forwardAddress0;

	/**
	 * フォワードポート_0
	 */
	private String forwardPort0;

	/**
	 * フォワードプロトコル_1
	 */
	private String forwardProtocol1;

	/**
	 * リッスンポート_1
	 */
	private String listenPort1;

	/**
	 * フォワードアドレス_1
	 */
	private String forwardAddress1;

	/**
	 * フォワードポート_1
	 */
	private String forwardPort1;

	/**
	 * フォワードプロトコル_2
	 */
	private String forwardProtocol2;

	/**
	 * リッスンポート_2
	 */
	private String listenPort2;

	/**
	 * フォワードアドレス_2
	 */
	private String forwardAddress2;

	/**
	 * フォワードポート_2
	 */
	private String forwardPort2;

	/**
	 * フォワードプロトコル_3
	 */
	private String forwardProtocol3;

	/**
	 * リッスンポート_3
	 */
	private String listenPort3;

	/**
	 * フォワードアドレス_3
	 */
	private String forwardAddress3;

	/**
	 * フォワードポート_3
	 */
	private String forwardPort3;

	/**
	 * フォワードプロトコル_4
	 */
	private String forwardProtocol4;

	/**
	 * リッスンポート_4
	 */
	private String listenPort4;

	/**
	 * フォワードアドレス_4
	 */
	private String forwardAddress4;

	/**
	 * フォワードポート_4
	 */
	private String forwardPort4;

	/**
	 * フォワードプロトコル_5
	 */
	private String forwardProtocol5;

	/**
	 * リッスンポート_5
	 */
	private String listenPort5;

	/**
	 * フォワードアドレス_5
	 */
	private String forwardAddress5;

	/**
	 * フォワードポート_5
	 */
	private String forwardPort5;

	/**
	 * フォワードプロトコル_6
	 */
	private String forwardProtocol6;

	/**
	 * リッスンポート_6
	 */
	private String listenPort6;

	/**
	 * フォワードアドレス_6
	 */
	private String forwardAddress6;

	/**
	 * フォワードポート_6
	 */
	private String forwardPort6;

	/**
	 * フォワードプロトコル_7
	 */
	private String forwardProtocol7;

	/**
	 * リッスンポート_7
	 */
	private String listenPort7;

	/**
	 * フォワードアドレス_7
	 */
	private String forwardAddress7;

	/**
	 * フォワードポート_7
	 */
	private String forwardPort7;

	/**
	 * フォワードプロトコル_8
	 */
	private String forwardProtocol8;

	/**
	 * リッスンポート_8
	 */
	private String listenPort8;

	/**
	 * フォワードアドレス_8
	 */
	private String forwardAddress8;

	/**
	 * フォワードポート_8
	 */
	private String forwardPort8;

	/**
	 * フォワードプロトコル_9
	 */
	private String forwardProtocol9;

	/**
	 * リッスンポート_9
	 */
	private String listenPort9;

	/**
	 * フォワードアドレス_9
	 */
	private String forwardAddress9;

	/**
	 * フォワードポート_9
	 */
	private String forwardPort9;

	/**
	 * DHCPサーバ
	 */
	private String dhcpServer;

	/**
	 * DHCP期限
	 */
	private String dhcpExpire;

	/**
	 * DHCPアドレス
	 */
	private String dhcpAddress;

	/**
	 * DHCP番号
	 */
	private String dhcpNumber;

	/**
	 * DNSサーバ_1
	 */
	private String dnsServer1;

	/**
	 * DNSフォワーダー
	 */
	private String dnsForwarder;

	/**
	 * VPNタイプ
	 */
	private String vpnType;

	/**
	 * ICMPリプライ
	 */
	private String icmpReply;

	/**
	 * DNSサーバ_2
	 */
	private String dnsServer2;

	/**
	 * リゾルバ_1
	 */
	private String resolver1;

	/**
	 * リゾルバ_2
	 */
	private String resolver2;

	/**
	 * バンドステアリング
	 */
	private String bandSteering;

	/**
	 * リザーブ_1
	 */
	private String reserve1;

	/**
	 * リザーブ_2
	 */
	private String reserve2;

	/**
	 * リザーブ_3
	 */
	private String reserve3;

	/**
	 * リザーブ_4
	 */
	private String reserve4;

	/**
	 * リザーブ_5
	 */
	private String reserve5;

	/**
	 * RAS
	 */
	private String ras;

	/**
	 * RAS_PSK
	 */
	private String rasPsk;

	/**
	 * RAS_TOP_ADDRESS
	 */
	private String rasTopAddress;

	/**
	 * PROXYARP_RANGE
	 */
	private String proxyarpRange;

	/**
	 * VPN_USER01_PASS
	 */
	private String vpnUser01Pass;

	/**
	 * VPN_USER02_PASS
	 */
	private String vpnUser02Pass;

	/**
	 * VPN_USER03_PASS
	 */
	private String vpnUser03Pass;

	/**
	 * VPN_USER04_PASS
	 */
	private String vpnUser04Pass;

	/**
	 * VPN_USER05_PASS
	 */
	private String vpnUser05Pass;

	/**
	 * VPN_USER06_PASS
	 */
	private String vpnUser06Pass;

	/**
	 * VPN_USER07_PASS
	 */
	private String vpnUser07Pass;

	/**
	 * VPN_USER08_PASS
	 */
	private String vpnUser08Pass;

	/**
	 * VPN_USER09_PASS
	 */
	private String vpnUser09Pass;

	/**
	* VPN_USER10_PASS
	*/
	private String vpnUser10Pass;

	/**
	 * remote-address
	 */
	private String remoteAddress;

	/**
	 * policybase-preshared-key
	 */
	private String policybasePresharedKey;

	/**
	 * fqdn
	 */
	private String fqdn;

	/**
	 * リザーブ_6
	 */
	private String reserve6;

	/**
	 * リザーブ_7
	 */
	private String reserve7;

	/**
	 * リザーブ_8
	 */
	private String reserve8;

	/**
	 * リザーブ_9
	 */
	private String reserve9;

	/**
	 * リザーブ_10
	 */
	private String reserve10;

	/**
	 * リザーブ_11
	 */
	private String reserve11;

	/**
	 * リザーブ_12
	 */
	private String reserve12;
	/**
	 * リザーブ_13
	 */
	private String reserve13;

	/**
	 * リザーブ_14
	 */
	private String reserve14;

	/**
	 * リザーブ_15
	 */
	private String reserve15;

	/**
	 * リザーブ_16
	 */
	private String reserve16;

	/**
	 * リザーブ_17
	 */
	private String reserve17;

}
