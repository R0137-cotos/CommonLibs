package jp.co.ricoh.cotos.commonlib.dto.result;

import javax.persistence.Column;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * BPN SACM事前設定情報取得API レスポンスのSACM手配業務.LaITコンフィグデータのDTO
 */

@Data
public class LaitConfigDataDto {

	/**
	 * AP区分
	 */
	@ApiModelProperty(value = "AP区分", required = false, allowableValues = "AP以外(\"1\"), AP(\"2\")", position = 1)
	private String apFlg;

	/**
	 * SACMマネジメントコード
	 */
	@ApiModelProperty(value = "SACMマネジメントコード", required = false, position = 2)
	private String sacmManageCode;

	/**
	 * サービスアダプタコード
	 */
	@ApiModelProperty(value = "サービスアダプタコード", required = false, position = 3)
	private String laitSaCode;

	/**
	 * サービスアダプタラベル
	 */
	@ApiModelProperty(value = "サービスアダプタラベル", required = false, position = 4)
	private String laitSaLabel;

	/**
	 * SSHDサービス
	 */
	@ApiModelProperty(value = "SSHDサービス", required = false, position = 5)
	private String sshdService;

	/**
	 * UPNPサービス
	 */
	@ApiModelProperty(value = "UPNPサービス", required = false, position = 6)
	private String upnpService;

	/**
	 * インターネットアカウント
	 */
	@ApiModelProperty(value = "インターネットアカウント", required = false, position = 7)
	private String internetAccount;

	/**
	 * インターネットPASS
	 */
	@ApiModelProperty(value = "インターネットPASS", required = false, position = 8)
	private String internetPass;

	/**
	 * PPPOE_MTU
	 */
	@ApiModelProperty(value = "PPPOE_MTU", required = false, position = 9)
	private String pppoeMtu;

	/**
	 * GE0メディア
	 */
	@Column(name = "GE0_MEDIA")
	@ApiModelProperty(value = "GE0メディア", required = false, position = 10)
	private String ge0Media;

	/**
	 * LANアドレス
	 */
	@ApiModelProperty(value = "LANアドレス", required = false, position = 11)
	private String lanAddress;

	/**
	 * LANサブネット
	 */
	@ApiModelProperty(value = "LANサブネット", required = false, position = 12)
	private String lanSubnet;

	/**
	 * GE1P0メディア
	 */
	@Column(name = "GE1P0_MEDIA")
	@ApiModelProperty(value = "GE1P0メディア", required = false, position = 13)
	private String ge1p0Media;

	/**
	 * GE1P1メディア
	 */
	@Column(name = "GE1P1_MEDIA")
	@ApiModelProperty(value = "GE1P1メディア", required = false, position = 14)
	private String ge1p1Media;

	/**
	 * GE1P2メディア
	 */
	@Column(name = "GE1P2_MEDIA")
	@ApiModelProperty(value = "GE1P2メディア", required = false, position = 15)
	private String ge1p2Media;

	/**
	 * GE1P3メディア
	 */
	@Column(name = "GE1P3_MEDIA")
	@ApiModelProperty(value = "GE1P3メディア", required = false, position = 16)
	private String ge1p3Media;

	/**
	 * IPV6ブリッジ
	 */
	@Column(name = "IPV6_BRIDGE")
	@ApiModelProperty(value = "IPV6ブリッジ", required = false, position = 17)
	private String ipv6Bridge;

	/**
	 * マイID
	 */
	@ApiModelProperty(value = "マイID", required = false, position = 18)
	private String myId;

	/**
	 * カスタマーID
	 */
	@ApiModelProperty(value = "カスタマーID", required = false, position = 19)
	private String customerId;

	/**
	 * PRESHAREDキー
	 */
	@ApiModelProperty(value = "PRESHAREDキー", required = false, position = 20)
	private String presharedKey;

	/**
	 * WLANサービス
	 */
	@ApiModelProperty(value = "WLANサービス", required = false, position = 21)
	private String wlanService;

	/**
	 * WLANモード
	 */
	@ApiModelProperty(value = "WLANモード", required = false, position = 22)
	private String wlanMode;

	/**
	 * WLAN_SSID_0
	 */
	@Column(name = "WLAN_SSID_0")
	@ApiModelProperty(value = "WLAN_SSID_0", required = false, position = 23)
	private String wlanSsid0;

	/**
	 * WLAN_HIDE_0
	 */
	@Column(name = "WLAN_HIDE_0")
	@ApiModelProperty(value = "WLAN_HIDE_0", required = false, position = 24)
	private String wlanHide0;

	/**
	 * WLANセキュリティ_0
	 */
	@Column(name = "WLAN_SECURITY_0")
	@ApiModelProperty(value = "WLANセキュリティ_0", required = false, position = 25)
	private String wlanSecurity0;

	/**
	 * WLANチャンネル
	 */
	@ApiModelProperty(value = "WLANチャンネル", required = false, position = 26)
	private String wlanChannel;

	/**
	 * WLANパス_0
	 */
	@Column(name = "WLAN_PASS_0")
	@ApiModelProperty(value = "WLANパス_0", required = false, position = 27)
	private String wlanPass0;

	/**
	 * WLAN_WEP_0
	 */
	@Column(name = "WLAN_WEP_0")
	@ApiModelProperty(value = "WLAN_WEP_0", required = false, position = 28)
	private String wlanWep0;

	/**
	 * WLANパワー
	 */
	@ApiModelProperty(value = "WLANパワー", required = false, position = 29)
	private String wlanPower;

	/**
	 * WLANマックスクライアント_0
	 */
	@Column(name = "WLAN_MAXCLIENT_0")
	@ApiModelProperty(value = "WLANマックスクライアント_0", required = false, position = 30)
	private String wlanMaxclient0;

	/**
	 * WLANサービス_5G
	 */
	@Column(name = "WLAN_SERVICE_5G")
	@ApiModelProperty(value = "WLANサービス_5G", required = false, position = 31)
	private String wlanService5g;

	/**
	 * WLANモード_5G
	 */
	@Column(name = "WLAN_MODE_5G")
	@ApiModelProperty(value = "WLANモード_5G", required = false, position = 32)
	private String wlanMode5g;

	/**
	 * WLAN_SSID_0_5G
	 */
	@Column(name = "WLAN_SSID_0_5G")
	@ApiModelProperty(value = "WLAN_SSID_0_5G", required = false, position = 33)
	private String wlanSsid05g;

	/**
	 * WLAN_HIDE_0_5G
	 */
	@Column(name = "WLAN_HIDE_0_5G")
	@ApiModelProperty(value = "WLAN_HIDE_0_5G", required = false, position = 34)
	private String wlanHide05g;

	/**
	 * WLANセキュリティ_0_5G
	 */
	@Column(name = "WLAN_SECURITY_0_5G")
	@ApiModelProperty(value = "WLANセキュリティ_0_5G", required = false, position = 35)
	private String wlanSecurity05g;

	/**
	 * WLAN_チャンネル_5G
	 */
	@Column(name = "WLAN_CHANNEL_5G")
	@ApiModelProperty(value = "WLAN_チャンネル_5G", required = false, position = 36)
	private String wlanChannel5g;

	/**
	 * WLAN_PASS_0_5G
	 */
	@Column(name = "WLAN_PASS_0_5G")
	@ApiModelProperty(value = "WLAN_PASS_0_5G", required = false, position = 37)
	private String wlanPass05g;

	/**
	 * WLAN_WEP_0_5G
	 */
	@Column(name = "WLAN_WEP_0_5G")
	@ApiModelProperty(value = "WLAN_WEP_0_5G", required = false, position = 38)
	private String wlanWep05g;

	/**
	 * WLAN_パワー_5G
	 */
	@Column(name = "WLAN_POWER_5G")
	@ApiModelProperty(value = "WLAN_パワー_5G", required = false, position = 39)
	private String wlanPower5g;

	/**
	 * WLANマックスクライアント_0_5G
	 */
	@Column(name = "WLAN_MAXCLIENT_0_5G")
	@ApiModelProperty(value = "WLANマックスクライアント_0_5G", required = false, position = 40)
	private String wlanMaxclient05g;

	/**
	 * LANセグメント
	 */
	@ApiModelProperty(value = "LANセグメント", required = false, position = 41)
	private String lanSegment;

	/**
	 * VPN番号
	 */
	@ApiModelProperty(value = "VPN番号", required = false, position = 42)
	private String vpnNumber;

	/**
	 * フォワードプロトコル_0
	 */
	@Column(name = "FORWARD_PROTOCOL_0")
	@ApiModelProperty(value = "フォワードプロトコル_0", required = false, position = 43)
	private String forwardProtocol0;

	/**
	 * リッスンポート_0
	 */
	@Column(name = "LISTEN_PORT_0")
	@ApiModelProperty(value = "リッスンポート_0", required = false, position = 44)
	private String listenPort0;

	/**
	 * フォワードアドレス_0
	 */
	@Column(name = "FORWARD_ADDRESS_0")
	@ApiModelProperty(value = "フォワードアドレス_0", required = false, position = 45)
	private String forwardAddress0;

	/**
	 * フォワードポート_0
	 */
	@Column(name = "FORWARD_PORT_0")
	@ApiModelProperty(value = "フォワードポート_0", required = false, position = 46)
	private String forwardPort0;

	/**
	 * フォワードプロトコル_1
	 */
	@Column(name = "FORWARD_PROTOCOL_1")
	@ApiModelProperty(value = "フォワードプロトコル_1", required = false, position = 47)
	private String forwardProtocol1;

	/**
	 * リッスンポート_1
	 */
	@Column(name = "LISTEN_PORT_1")
	@ApiModelProperty(value = "リッスンポート_1", required = false, position = 48)
	private String listenPort1;

	/**
	 * フォワードアドレス_1
	 */
	@Column(name = "FORWARD_ADDRESS_1")
	@ApiModelProperty(value = "フォワードアドレス_1", required = false, position = 49)
	private String forwardAddress1;

	/**
	 * フォワードポート_1
	 */
	@Column(name = "FORWARD_PORT_1")
	@ApiModelProperty(value = "フォワードポート_1", required = false, position = 50)
	private String forwardPort1;

	/**
	 * フォワードプロトコル_2
	 */
	@Column(name = "FORWARD_PROTOCOL_2")
	@ApiModelProperty(value = "フォワードプロトコル_2", required = false, position = 51)
	private String forwardProtocol2;

	/**
	 * リッスンポート_2
	 */
	@Column(name = "LISTEN_PORT_2")
	@ApiModelProperty(value = "リッスンポート_2", required = false, position = 52)
	private String listenPort2;

	/**
	 * フォワードアドレス_2
	 */
	@Column(name = "FORWARD_ADDRESS_2")
	@ApiModelProperty(value = "フォワードアドレス_2", required = false, position = 53)
	private String forwardAddress2;

	/**
	 * フォワードポート_2
	 */
	@Column(name = "FORWARD_PORT_2")
	@ApiModelProperty(value = "フォワードポート_2", required = false, position = 54)
	private String forwardPort2;

	/**
	 * フォワードプロトコル_3
	 */
	@Column(name = "FORWARD_PROTOCOL_3")
	@ApiModelProperty(value = "フォワードプロトコル_3", required = false, position = 55)
	private String forwardProtocol3;

	/**
	 * リッスンポート_3
	 */
	@Column(name = "LISTEN_PORT_3")
	@ApiModelProperty(value = "リッスンポート_3", required = false, position = 56)
	private String listenPort3;

	/**
	 * フォワードアドレス_3
	 */
	@Column(name = "FORWARD_ADDRESS_3")
	@ApiModelProperty(value = "フォワードアドレス_3", required = false, position = 57)
	private String forwardAddress3;

	/**
	 * フォワードポート_3
	 */
	@Column(name = "FORWARD_PORT_3")
	@ApiModelProperty(value = "フォワードポート_3", required = false, position = 58)
	private String forwardPort3;

	/**
	 * フォワードプロトコル_4
	 */
	@Column(name = "FORWARD_PROTOCOL_4")
	@ApiModelProperty(value = "フォワードプロトコル_4", required = false, position = 59)
	private String forwardProtocol4;

	/**
	 * リッスンポート_4
	 */
	@Column(name = "LISTEN_PORT_4")
	@ApiModelProperty(value = "リッスンポート_4", required = false, position = 60)
	private String listenPort4;

	/**
	 * フォワードアドレス_4
	 */
	@Column(name = "FORWARD_ADDRESS_4")
	@ApiModelProperty(value = "フォワードアドレス_4", required = false, position = 61)
	private String forwardAddress4;

	/**
	 * フォワードポート_4
	 */
	@Column(name = "FORWARD_PORT_4")
	@ApiModelProperty(value = "フォワードポート_4", required = false, position = 62)
	private String forwardPort4;

	/**
	 * フォワードプロトコル_5
	 */
	@Column(name = "FORWARD_PROTOCOL_5")
	@ApiModelProperty(value = "フォワードプロトコル_5", required = false, position = 63)
	private String forwardProtocol5;

	/**
	 * リッスンポート_5
	 */
	@Column(name = "LISTEN_PORT_5")
	@ApiModelProperty(value = "リッスンポート_5", required = false, position = 64)
	private String listenPort5;

	/**
	 * フォワードアドレス_5
	 */
	@Column(name = "FORWARD_ADDRESS_5")
	@ApiModelProperty(value = "フォワードアドレス_5", required = false, position = 65)
	private String forwardAddress5;

	/**
	 * フォワードポート_5
	 */
	@Column(name = "FORWARD_PORT_5")
	@ApiModelProperty(value = "フォワードポート_5", required = false, position = 66)
	private String forwardPort5;

	/**
	 * フォワードプロトコル_6
	 */
	@Column(name = "FORWARD_PROTOCOL_6")
	@ApiModelProperty(value = "フォワードプロトコル_6", required = false, position = 67)
	private String forwardProtocol6;

	/**
	 * リッスンポート_6
	 */
	@Column(name = "LISTEN_PORT_6")
	@ApiModelProperty(value = "リッスンポート_6", required = false, position = 68)
	private String listenPort6;

	/**
	 * フォワードアドレス_6
	 */
	@Column(name = "FORWARD_ADDRESS_6")
	@ApiModelProperty(value = "フォワードアドレス_6", required = false, position = 69)
	private String forwardAddress6;

	/**
	 * フォワードポート_6
	 */
	@Column(name = "FORWARD_PORT_6")
	@ApiModelProperty(value = "フォワードポート_6", required = false, position = 70)
	private String forwardPort6;

	/**
	 * フォワードプロトコル_7
	 */
	@Column(name = "FORWARD_PROTOCOL_7")
	@ApiModelProperty(value = "フォワードプロトコル_7", required = false, position = 71)
	private String forwardProtocol7;

	/**
	 * リッスンポート_7
	 */
	@Column(name = "LISTEN_PORT_7")
	@ApiModelProperty(value = "リッスンポート_7", required = false, position = 72)
	private String listenPort7;

	/**
	 * フォワードアドレス_7
	 */
	@Column(name = "FORWARD_ADDRESS_7")
	@ApiModelProperty(value = "フォワードアドレス_7", required = false, position = 73)
	private String forwardAddress7;

	/**
	 * フォワードポート_7
	 */
	@Column(name = "FORWARD_PORT_7")
	@ApiModelProperty(value = "フォワードポート_7", required = false, position = 74)
	private String forwardPort7;

	/**
	 * フォワードプロトコル_8
	 */
	@Column(name = "FORWARD_PROTOCOL_8")
	@ApiModelProperty(value = "フォワードプロトコル_8", required = false, position = 75)
	private String forwardProtocol8;

	/**
	 * リッスンポート_8
	 */
	@Column(name = "LISTEN_PORT_8")
	@ApiModelProperty(value = "リッスンポート_8", required = false, position = 76)
	private String listenPort8;

	/**
	 * フォワードアドレス_8
	 */
	@Column(name = "FORWARD_ADDRESS_8")
	@ApiModelProperty(value = "フォワードアドレス_8", required = false, position = 77)
	private String forwardAddress8;

	/**
	 * フォワードポート_8
	 */
	@Column(name = "FORWARD_PORT_8")
	@ApiModelProperty(value = "フォワードポート_8", required = false, position = 78)
	private String forwardPort8;

	/**
	 * フォワードプロトコル_9
	 */
	@Column(name = "FORWARD_PROTOCOL_9")
	@ApiModelProperty(value = "フォワードプロトコル_9", required = false, position = 79)
	private String forwardProtocol9;

	/**
	 * リッスンポート_9
	 */
	@Column(name = "LISTEN_PORT_9")
	@ApiModelProperty(value = "リッスンポート_9", required = false, position = 80)
	private String listenPort9;

	/**
	 * フォワードアドレス_9
	 */
	@Column(name = "FORWARD_ADDRESS_9")
	@ApiModelProperty(value = "フォワードアドレス_9", required = false, position = 81)
	private String forwardAddress9;

	/**
	 * フォワードポート_9
	 */
	@Column(name = "FORWARD_PORT_9")
	@ApiModelProperty(value = "フォワードポート_9", required = false, position = 82)
	private String forwardPort9;

	/**
	 * DHCPサーバ
	 */
	@ApiModelProperty(value = "DHCPサーバ", required = false, position = 83)
	private String dhcpServer;

	/**
	 * DHCP期限
	 */
	@ApiModelProperty(value = "DHCP期限", required = false, position = 84)
	private String dhcpExpire;

	/**
	 * DHCPアドレス
	 */
	@ApiModelProperty(value = "DHCPアドレス", required = false, position = 85)
	private String dhcpAddress;

	/**
	 * DHCP番号
	 */
	@ApiModelProperty(value = "DHCP番号", required = false, position = 86)
	private String dhcpNumber;

	/**
	 * DNSサーバ_1
	 */
	@Column(name = "DNS_SERVER_1")
	@ApiModelProperty(value = "DNSサーバ_1", required = false, position = 87)
	private String dnsServer1;

	/**
	 * DNSフォワーダー
	 */
	@ApiModelProperty(value = "DNSフォワーダー", required = false, position = 88)
	private String dnsForwarder;

	/**
	 * VPNタイプ
	 */
	@ApiModelProperty(value = "VPNタイプ", required = false, position = 89)
	private String vpnType;

	/**
	 * ICMPリプライ
	 */
	@ApiModelProperty(value = "ICMPリプライ", required = false, position = 90)
	private String icmpReply;

	/**
	 * DNSサーバ_2
	 */
	@Column(name = "DNS_SERVER_2")
	@ApiModelProperty(value = "DNSサーバ_2", required = false, position = 91)
	private String dnsServer2;

	/**
	 * リゾルバ_1
	 */
	@Column(name = "RESOLVER_1")
	@ApiModelProperty(value = "リゾルバ_1", required = false, position = 92)
	private String resolver1;

	/**
	 * リゾルバ_2
	 */
	@Column(name = "RESOLVER_2")
	@ApiModelProperty(value = "リゾルバ_2", required = false, position = 93)
	private String resolver2;

	/**
	 * バンドステアリング
	 */
	@ApiModelProperty(value = "バンドステアリング", required = false, position = 94)
	private String bandSteering;

	/**
	 * リザーブ_1
	 */
	@Column(name = "RESERVE_1")
	@ApiModelProperty(value = "リザーブ_1", required = false, position = 95)
	private String reserve1;

	/**
	 * リザーブ_2
	 */
	@Column(name = "RESERVE_2")
	@ApiModelProperty(value = "リザーブ_2", required = false, position = 96)
	private String reserve2;

	/**
	 * リザーブ_3
	 */
	@Column(name = "RESERVE_3")
	@ApiModelProperty(value = "リザーブ_3", required = false, position = 97)
	private String reserve3;

	/**
	 * リザーブ_4
	 */
	@Column(name = "RESERVE_4")
	@ApiModelProperty(value = "リザーブ_4", required = false, position = 98)
	private String reserve4;

	/**
	 * リザーブ_5
	 */
	@Column(name = "RESERVE_5")
	@ApiModelProperty(value = "リザーブ_5", required = false, position = 99)
	private String reserve5;

	/**
	 * RAS
	 */
	@ApiModelProperty(value = "RAS", required = false, position = 100)
	private String ras;

	/**
	 * RAS_PSK
	 */
	@ApiModelProperty(value = "RAS_PSK", required = false, position = 101)
	private String rasPsk;

	/**
	 * RAS_TOP_ADDRESS
	 */
	@ApiModelProperty(value = "RAS_TOP_ADDRESS", required = false, position = 102)
	private String rasTopAddress;

	/**
	 * PROXYARP_RANGE
	 */
	@ApiModelProperty(value = "PROXYARP_RANGE", required = false, position = 103)
	private String proxyarpRange;

	/**
	 * VPN_USER01_PASS
	 */
	@Column(name = "VPN_USER01_PASS")
	@ApiModelProperty(value = "VPN_USER01_PASS", required = false, position = 104)
	private String vpnUser01Pass;

	/**
	 * VPN_USER02_PASS
	 */
	@Column(name = "VPN_USER02_PASS")
	@ApiModelProperty(value = "VPN_USER02_PASS", required = false, position = 105)
	private String vpnUser02Pass;

	/**
	 * VPN_USER03_PASS
	 */
	@Column(name = "VPN_USER03_PASS")
	@ApiModelProperty(value = "VPN_USER03_PASS", required = false, position = 106)
	private String vpnUser03Pass;

	/**
	 * VPN_USER04_PASS
	 */
	@Column(name = "VPN_USER04_PASS")
	@ApiModelProperty(value = "VPN_USER04_PASS", required = false, position = 107)
	private String vpnUser04Pass;

	/**
	 * VPN_USER05_PASS
	 */
	@Column(name = "VPN_USER05_PASS")
	@ApiModelProperty(value = "VPN_USER05_PASS", required = false, position = 108)
	private String vpnUser05Pass;

	/**
	 * VPN_USER06_PASS
	 */
	@Column(name = "VPN_USER06_PASS")
	@ApiModelProperty(value = "VPN_USER06_PASS", required = false, position = 109)
	private String vpnUser06Pass;

	/**
	 * VPN_USER07_PASS
	 */
	@Column(name = "VPN_USER07_PASS")
	@ApiModelProperty(value = "VPN_USER07_PASS", required = false, position = 110)
	private String vpnUser07Pass;

	/**
	 * VPN_USER08_PASS
	 */
	@Column(name = "VPN_USER08_PASS")
	@ApiModelProperty(value = "VPN_USER08_PASS", required = false, position = 111)
	private String vpnUser08Pass;

	/**
	 * VPN_USER09_PASS
	 */
	@Column(name = "VPN_USER09_PASS")
	@ApiModelProperty(value = "VPN_USER09_PASS", required = false, position = 112)
	private String vpnUser09Pass;

	/**
	* VPN_USER10_PASS
	*/
	@Column(name = "VPN_USER10_PASS")
	@ApiModelProperty(value = "VPN_USER_10_PASS", required = false, position = 113)
	private String vpnUser10Pass;

	/**
	 * remote-address
	 */
	@ApiModelProperty(value = "remote-address", required = false, position = 114)
	private String remoteAddress;

	/**
	 * policybase-preshared-key
	 */
	@ApiModelProperty(value = "policybase-preshared-key", required = false, position = 115)
	private String policybasePresharedKey;

	/**
	 * fqdn
	 */
	@ApiModelProperty(value = "fqdn", required = false, position = 116)
	private String fqdn;

	/**
	 * リザーブ_6
	 */
	@Column(name = "RESERVE_6")
	@ApiModelProperty(value = "リザーブ_6", required = false, position = 117)
	private String reserve6;

	/**
	 * リザーブ_7
	 */
	@Column(name = "RESERVE_7")
	@ApiModelProperty(value = "リザーブ_7", required = false, position = 118)
	private String reserve7;

	/**
	 * リザーブ_8
	 */
	@Column(name = "RESERVE_8")
	@ApiModelProperty(value = "リザーブ_8", required = false, position = 119)
	private String reserve8;

	/**
	 * リザーブ_9
	 */
	@Column(name = "RESERVE_9")
	@ApiModelProperty(value = "リザーブ_9", required = false, position = 120)
	private String reserve9;

	/**
	 * リザーブ_10
	 */
	@Column(name = "RESERVE_10")
	@ApiModelProperty(value = "リザーブ_10", required = false, position = 121)
	private String reserve10;

	/**
	 * リザーブ_11
	 */
	@Column(name = "RESERVE_11")
	@ApiModelProperty(value = "リザーブ_11", required = false, position = 122)
	private String reserve11;

	/**
	 * リザーブ_12
	 */
	@Column(name = "RESERVE_12")
	@ApiModelProperty(value = "リザーブ_12", required = false, position = 123)
	private String reserve12;
	/**
	 * リザーブ_13
	 */
	@Column(name = "RESERVE_13")
	@ApiModelProperty(value = "リザーブ_13", required = false, position = 124)
	private String reserve13;

	/**
	 * リザーブ_14
	 */
	@Column(name = "RESERVE_14")
	@ApiModelProperty(value = "リザーブ_14", required = false, position = 125)
	private String reserve14;

	/**
	 * リザーブ_15
	 */
	@Column(name = "RESERVE_15")
	@ApiModelProperty(value = "リザーブ_15", required = false, position = 126)
	private String reserve15;

	/**
	 * リザーブ_16
	 */
	@Column(name = "RESERVE_16")
	@ApiModelProperty(value = "リザーブ_16", required = false, position = 127)
	private String reserve16;

	/**
	 * リザーブ_17
	 */
	@Column(name = "RESERVE_17")
	@ApiModelProperty(value = "リザーブ_17", required = false, position = 128)
	private String reserve17;

}
