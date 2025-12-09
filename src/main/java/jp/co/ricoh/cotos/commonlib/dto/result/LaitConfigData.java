package jp.co.ricoh.cotos.commonlib.dto.result;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * BPN SACM事前設定情報取得API レスポンスのSACM手配業務.LaITコンフィグデータのDTO
 */

@Entity
@Data
public class LaitConfigData {

	/**
	 * コンフィグ情報ID
	 */
	@Id
	@Schema(description = "ID", required = false)
	@JsonIgnore
	private long id;

	/**
	 * AP区分
	 */
	@Schema(description = "AP区分", required = false, allowableValues = "AP以外(\"1\"), AP(\"2\")")
	private String apFlg;

	/**
	 * SACMマネジメントコード
	 */
	@Schema(description = "SACMマネジメントコード", required = false)
	private String sacmManageCode;

	/**
	 * サービスアダプタコード
	 */
	@Schema(description = "サービスアダプタコード", required = false)
	private String laitSaCode;

	/**
	 * サービスアダプタラベル
	 */
	@Schema(description = "サービスアダプタラベル", required = false)
	private String laitSaLabel;

	/**
	 * SSHDサービス
	 */
	@Schema(description = "SSHDサービス", required = false)
	private String sshdService;

	/**
	 * UPNPサービス
	 */
	@Schema(description = "UPNPサービス", required = false)
	private String upnpService;

	/**
	 * インターネットアカウント
	 */
	@Schema(description = "インターネットアカウント", required = false)
	private String internetAccount;

	/**
	 * インターネットPASS
	 */
	@Schema(description = "インターネットPASS", required = false)
	private String internetPass;

	/**
	 * PPPOE_MTU
	 */
	@Schema(description = "PPPOE_MTU", required = false)
	private String pppoeMtu;

	/**
	 * GE0メディア
	 */
	@Column(name = "GE0_MEDIA")
	@Schema(description = "GE0メディア", required = false)
	private String ge0Media;

	/**
	 * LANアドレス
	 */
	@Schema(description = "LANアドレス", required = false)
	private String lanAddress;

	/**
	 * LANサブネット
	 */
	@Schema(description = "LANサブネット", required = false)
	private String lanSubnet;

	/**
	 * GE1P0メディア
	 */
	@Column(name = "GE1P0_MEDIA")
	@Schema(description = "GE1P0メディア", required = false)
	private String ge1p0Media;

	/**
	 * GE1P1メディア
	 */
	@Column(name = "GE1P1_MEDIA")
	@Schema(description = "GE1P1メディア", required = false)
	private String ge1p1Media;

	/**
	 * GE1P2メディア
	 */
	@Column(name = "GE1P2_MEDIA")
	@Schema(description = "GE1P2メディア", required = false)
	private String ge1p2Media;

	/**
	 * GE1P3メディア
	 */
	@Column(name = "GE1P3_MEDIA")
	@Schema(description = "GE1P3メディア", required = false)
	private String ge1p3Media;

	/**
	 * IPV6ブリッジ
	 */
	@Column(name = "IPV6_BRIDGE")
	@Schema(description = "IPV6ブリッジ", required = false)
	private String ipv6Bridge;

	/**
	 * マイID
	 */
	@Schema(description = "マイID", required = false)
	private String myId;

	/**
	 * カスタマーID
	 */
	@Schema(description = "カスタマーID", required = false)
	private String customerId;

	/**
	 * PRESHAREDキー
	 */
	@Schema(description = "PRESHAREDキー", required = false)
	private String presharedKey;

	/**
	 * WLANサービス
	 */
	@Schema(description = "WLANサービス", required = false)
	private String wlanService;

	/**
	 * WLANモード
	 */
	@Schema(description = "WLANモード", required = false)
	private String wlanMode;

	/**
	 * WLAN_SSID_0
	 */
	@Column(name = "WLAN_SSID_0")
	@Schema(description = "WLAN_SSID_0", required = false)
	private String wlanSsid0;

	/**
	 * WLAN_HIDE_0
	 */
	@Column(name = "WLAN_HIDE_0")
	@Schema(description = "WLAN_HIDE_0", required = false)
	private String wlanHide0;

	/**
	 * WLANセキュリティ_0
	 */
	@Column(name = "WLAN_SECURITY_0")
	@Schema(description = "WLANセキュリティ_0", required = false)
	private String wlanSecurity0;

	/**
	 * WLANチャンネル
	 */
	@Schema(description = "WLANチャンネル", required = false)
	private String wlanChannel;

	/**
	 * WLANパス_0
	 */
	@Column(name = "WLAN_PASS_0")
	@Schema(description = "WLANパス_0", required = false)
	private String wlanPass0;

	/**
	 * WLAN_WEP_0
	 */
	@Column(name = "WLAN_WEP_0")
	@Schema(description = "WLAN_WEP_0", required = false)
	private String wlanWep0;

	/**
	 * WLANパワー
	 */
	@Schema(description = "WLANパワー", required = false)
	private String wlanPower;

	/**
	 * WLANマックスクライアント_0
	 */
	@Column(name = "WLAN_MAXCLIENT_0")
	@Schema(description = "WLANマックスクライアント_0", required = false)
	private String wlanMaxclient0;

	/**
	 * WLANサービス_5G
	 */
	@Column(name = "WLAN_SERVICE_5G")
	@Schema(description = "WLANサービス_5G", required = false)
	private String wlanService5g;

	/**
	 * WLANモード_5G
	 */
	@Column(name = "WLAN_MODE_5G")
	@Schema(description = "WLANモード_5G", required = false)
	private String wlanMode5g;

	/**
	 * WLAN_SSID_0_5G
	 */
	@Column(name = "WLAN_SSID_0_5G")
	@Schema(description = "WLAN_SSID_0_5G", required = false)
	private String wlanSsid05g;

	/**
	 * WLAN_HIDE_0_5G
	 */
	@Column(name = "WLAN_HIDE_0_5G")
	@Schema(description = "WLAN_HIDE_0_5G", required = false)
	private String wlanHide05g;

	/**
	 * WLANセキュリティ_0_5G
	 */
	@Column(name = "WLAN_SECURITY_0_5G")
	@Schema(description = "WLANセキュリティ_0_5G", required = false)
	private String wlanSecurity05g;

	/**
	 * WLAN_チャンネル_5G
	 */
	@Column(name = "WLAN_CHANNEL_5G")
	@Schema(description = "WLAN_チャンネル_5G", required = false)
	private String wlanChannel5g;

	/**
	 * WLAN_PASS_0_5G
	 */
	@Column(name = "WLAN_PASS_0_5G")
	@Schema(description = "WLAN_PASS_0_5G", required = false)
	private String wlanPass05g;

	/**
	 * WLAN_WEP_0_5G
	 */
	@Column(name = "WLAN_WEP_0_5G")
	@Schema(description = "WLAN_WEP_0_5G", required = false)
	private String wlanWep05g;

	/**
	 * WLAN_パワー_5G
	 */
	@Column(name = "WLAN_POWER_5G")
	@Schema(description = "WLAN_パワー_5G", required = false)
	private String wlanPower5g;

	/**
	 * WLANマックスクライアント_0_5G
	 */
	@Column(name = "WLAN_MAXCLIENT_0_5G")
	@Schema(description = "WLANマックスクライアント_0_5G", required = false)
	private String wlanMaxclient05g;

	/**
	 * LANセグメント
	 */
	@Schema(description = "LANセグメント", required = false)
	private String lanSegment;

	/**
	 * VPN番号
	 */
	@Schema(description = "VPN番号", required = false)
	private String vpnNumber;

	/**
	 * フォワードプロトコル_0
	 */
	@Column(name = "FORWARD_PROTOCOL_0")
	@Schema(description = "フォワードプロトコル_0", required = false)
	private String forwardProtocol0;

	/**
	 * リッスンポート_0
	 */
	@Column(name = "LISTEN_PORT_0")
	@Schema(description = "リッスンポート_0", required = false)
	private String listenPort0;

	/**
	 * フォワードアドレス_0
	 */
	@Column(name = "FORWARD_ADDRESS_0")
	@Schema(description = "フォワードアドレス_0", required = false)
	private String forwardAddress0;

	/**
	 * フォワードポート_0
	 */
	@Column(name = "FORWARD_PORT_0")
	@Schema(description = "フォワードポート_0", required = false)
	private String forwardPort0;

	/**
	 * フォワードプロトコル_1
	 */
	@Column(name = "FORWARD_PROTOCOL_1")
	@Schema(description = "フォワードプロトコル_1", required = false)
	private String forwardProtocol1;

	/**
	 * リッスンポート_1
	 */
	@Column(name = "LISTEN_PORT_1")
	@Schema(description = "リッスンポート_1", required = false)
	private String listenPort1;

	/**
	 * フォワードアドレス_1
	 */
	@Column(name = "FORWARD_ADDRESS_1")
	@Schema(description = "フォワードアドレス_1", required = false)
	private String forwardAddress1;

	/**
	 * フォワードポート_1
	 */
	@Column(name = "FORWARD_PORT_1")
	@Schema(description = "フォワードポート_1", required = false)
	private String forwardPort1;

	/**
	 * フォワードプロトコル_2
	 */
	@Column(name = "FORWARD_PROTOCOL_2")
	@Schema(description = "フォワードプロトコル_2", required = false)
	private String forwardProtocol2;

	/**
	 * リッスンポート_2
	 */
	@Column(name = "LISTEN_PORT_2")
	@Schema(description = "リッスンポート_2", required = false)
	private String listenPort2;

	/**
	 * フォワードアドレス_2
	 */
	@Column(name = "FORWARD_ADDRESS_2")
	@Schema(description = "フォワードアドレス_2", required = false)
	private String forwardAddress2;

	/**
	 * フォワードポート_2
	 */
	@Column(name = "FORWARD_PORT_2")
	@Schema(description = "フォワードポート_2", required = false)
	private String forwardPort2;

	/**
	 * フォワードプロトコル_3
	 */
	@Column(name = "FORWARD_PROTOCOL_3")
	@Schema(description = "フォワードプロトコル_3", required = false)
	private String forwardProtocol3;

	/**
	 * リッスンポート_3
	 */
	@Column(name = "LISTEN_PORT_3")
	@Schema(description = "リッスンポート_3", required = false)
	private String listenPort3;

	/**
	 * フォワードアドレス_3
	 */
	@Column(name = "FORWARD_ADDRESS_3")
	@Schema(description = "フォワードアドレス_3", required = false)
	private String forwardAddress3;

	/**
	 * フォワードポート_3
	 */
	@Column(name = "FORWARD_PORT_3")
	@Schema(description = "フォワードポート_3", required = false)
	private String forwardPort3;

	/**
	 * フォワードプロトコル_4
	 */
	@Column(name = "FORWARD_PROTOCOL_4")
	@Schema(description = "フォワードプロトコル_4", required = false)
	private String forwardProtocol4;

	/**
	 * リッスンポート_4
	 */
	@Column(name = "LISTEN_PORT_4")
	@Schema(description = "リッスンポート_4", required = false)
	private String listenPort4;

	/**
	 * フォワードアドレス_4
	 */
	@Column(name = "FORWARD_ADDRESS_4")
	@Schema(description = "フォワードアドレス_4", required = false)
	private String forwardAddress4;

	/**
	 * フォワードポート_4
	 */
	@Column(name = "FORWARD_PORT_4")
	@Schema(description = "フォワードポート_4", required = false)
	private String forwardPort4;

	/**
	 * フォワードプロトコル_5
	 */
	@Column(name = "FORWARD_PROTOCOL_5")
	@Schema(description = "フォワードプロトコル_5", required = false)
	private String forwardProtocol5;

	/**
	 * リッスンポート_5
	 */
	@Column(name = "LISTEN_PORT_5")
	@Schema(description = "リッスンポート_5", required = false)
	private String listenPort5;

	/**
	 * フォワードアドレス_5
	 */
	@Column(name = "FORWARD_ADDRESS_5")
	@Schema(description = "フォワードアドレス_5", required = false)
	private String forwardAddress5;

	/**
	 * フォワードポート_5
	 */
	@Column(name = "FORWARD_PORT_5")
	@Schema(description = "フォワードポート_5", required = false)
	private String forwardPort5;

	/**
	 * フォワードプロトコル_6
	 */
	@Column(name = "FORWARD_PROTOCOL_6")
	@Schema(description = "フォワードプロトコル_6", required = false)
	private String forwardProtocol6;

	/**
	 * リッスンポート_6
	 */
	@Column(name = "LISTEN_PORT_6")
	@Schema(description = "リッスンポート_6", required = false)
	private String listenPort6;

	/**
	 * フォワードアドレス_6
	 */
	@Column(name = "FORWARD_ADDRESS_6")
	@Schema(description = "フォワードアドレス_6", required = false)
	private String forwardAddress6;

	/**
	 * フォワードポート_6
	 */
	@Column(name = "FORWARD_PORT_6")
	@Schema(description = "フォワードポート_6", required = false)
	private String forwardPort6;

	/**
	 * フォワードプロトコル_7
	 */
	@Column(name = "FORWARD_PROTOCOL_7")
	@Schema(description = "フォワードプロトコル_7", required = false)
	private String forwardProtocol7;

	/**
	 * リッスンポート_7
	 */
	@Column(name = "LISTEN_PORT_7")
	@Schema(description = "リッスンポート_7", required = false)
	private String listenPort7;

	/**
	 * フォワードアドレス_7
	 */
	@Column(name = "FORWARD_ADDRESS_7")
	@Schema(description = "フォワードアドレス_7", required = false)
	private String forwardAddress7;

	/**
	 * フォワードポート_7
	 */
	@Column(name = "FORWARD_PORT_7")
	@Schema(description = "フォワードポート_7", required = false)
	private String forwardPort7;

	/**
	 * フォワードプロトコル_8
	 */
	@Column(name = "FORWARD_PROTOCOL_8")
	@Schema(description = "フォワードプロトコル_8", required = false)
	private String forwardProtocol8;

	/**
	 * リッスンポート_8
	 */
	@Column(name = "LISTEN_PORT_8")
	@Schema(description = "リッスンポート_8", required = false)
	private String listenPort8;

	/**
	 * フォワードアドレス_8
	 */
	@Column(name = "FORWARD_ADDRESS_8")
	@Schema(description = "フォワードアドレス_8", required = false)
	private String forwardAddress8;

	/**
	 * フォワードポート_8
	 */
	@Column(name = "FORWARD_PORT_8")
	@Schema(description = "フォワードポート_8", required = false)
	private String forwardPort8;

	/**
	 * フォワードプロトコル_9
	 */
	@Column(name = "FORWARD_PROTOCOL_9")
	@Schema(description = "フォワードプロトコル_9", required = false)
	private String forwardProtocol9;

	/**
	 * リッスンポート_9
	 */
	@Column(name = "LISTEN_PORT_9")
	@Schema(description = "リッスンポート_9", required = false)
	private String listenPort9;

	/**
	 * フォワードアドレス_9
	 */
	@Column(name = "FORWARD_ADDRESS_9")
	@Schema(description = "フォワードアドレス_9", required = false)
	private String forwardAddress9;

	/**
	 * フォワードポート_9
	 */
	@Column(name = "FORWARD_PORT_9")
	@Schema(description = "フォワードポート_9", required = false)
	private String forwardPort9;

	/**
	 * DHCPサーバ
	 */
	@Schema(description = "DHCPサーバ", required = false)
	private String dhcpServer;

	/**
	 * DHCP期限
	 */
	@Schema(description = "DHCP期限", required = false)
	private String dhcpExpire;

	/**
	 * DHCPアドレス
	 */
	@Schema(description = "DHCPアドレス", required = false)
	private String dhcpAddress;

	/**
	 * DHCP番号
	 */
	@Schema(description = "DHCP番号", required = false)
	private String dhcpNumber;

	/**
	 * DNSサーバ_1
	 */
	@Column(name = "DNS_SERVER_1")
	@Schema(description = "DNSサーバ_1", required = false)
	private String dnsServer1;

	/**
	 * DNSフォワーダー
	 */
	@Schema(description = "DNSフォワーダー", required = false)
	private String dnsForwarder;

	/**
	 * VPNタイプ
	 */
	@Schema(description = "VPNタイプ", required = false)
	private String vpnType;

	/**
	 * ICMPリプライ
	 */
	@Schema(description = "ICMPリプライ", required = false)
	private String icmpReply;

	/**
	 * DNSサーバ_2
	 */
	@Column(name = "DNS_SERVER_2")
	@Schema(description = "DNSサーバ_2", required = false)
	private String dnsServer2;

	/**
	 * リゾルバ_1
	 */
	@Column(name = "RESOLVER_1")
	@Schema(description = "リゾルバ_1", required = false)
	private String resolver1;

	/**
	 * リゾルバ_2
	 */
	@Column(name = "RESOLVER_2")
	@Schema(description = "リゾルバ_2", required = false)
	private String resolver2;

	/**
	 * バンドステアリング
	 */
	@Schema(description = "バンドステアリング", required = false)
	private String bandSteering;

	/**
	 * リザーブ_1
	 */
	@Column(name = "RESERVE_1")
	@Schema(description = "リザーブ_1", required = false)
	private String reserve1;

	/**
	 * リザーブ_2
	 */
	@Column(name = "RESERVE_2")
	@Schema(description = "リザーブ_2", required = false)
	private String reserve2;

	/**
	 * リザーブ_3
	 */
	@Column(name = "RESERVE_3")
	@Schema(description = "リザーブ_3", required = false)
	private String reserve3;

	/**
	 * リザーブ_4
	 */
	@Column(name = "RESERVE_4")
	@Schema(description = "リザーブ_4", required = false)
	private String reserve4;

	/**
	 * リザーブ_5
	 */
	@Column(name = "RESERVE_5")
	@Schema(description = "リザーブ_5", required = false)
	private String reserve5;

	/**
	 * RAS
	 */
	@Schema(description = "RAS", required = false)
	private String ras;

	/**
	 * RAS_PSK
	 */
	@Schema(description = "RAS_PSK", required = false)
	private String rasPsk;

	/**
	 * RAS_TOP_ADDRESS
	 */
	@Schema(description = "RAS_TOP_ADDRESS", required = false)
	private String rasTopAddress;

	/**
	 * PROXYARP_RANGE
	 */
	@Schema(description = "PROXYARP_RANGE", required = false)
	private String proxyarpRange;

	/**
	 * VPN_USER01_PASS
	 */
	@Column(name = "VPN_USER01_PASS")
	@Schema(description = "VPN_USER01_PASS", required = false)
	private String vpnUser01Pass;

	/**
	 * VPN_USER02_PASS
	 */
	@Column(name = "VPN_USER02_PASS")
	@Schema(description = "VPN_USER02_PASS", required = false)
	private String vpnUser02Pass;

	/**
	 * VPN_USER03_PASS
	 */
	@Column(name = "VPN_USER03_PASS")
	@Schema(description = "VPN_USER03_PASS", required = false)
	private String vpnUser03Pass;

	/**
	 * VPN_USER04_PASS
	 */
	@Column(name = "VPN_USER04_PASS")
	@Schema(description = "VPN_USER04_PASS", required = false)
	private String vpnUser04Pass;

	/**
	 * VPN_USER05_PASS
	 */
	@Column(name = "VPN_USER05_PASS")
	@Schema(description = "VPN_USER05_PASS", required = false)
	private String vpnUser05Pass;

	/**
	 * VPN_USER06_PASS
	 */
	@Column(name = "VPN_USER06_PASS")
	@Schema(description = "VPN_USER06_PASS", required = false)
	private String vpnUser06Pass;

	/**
	 * VPN_USER07_PASS
	 */
	@Column(name = "VPN_USER07_PASS")
	@Schema(description = "VPN_USER07_PASS", required = false)
	private String vpnUser07Pass;

	/**
	 * VPN_USER08_PASS
	 */
	@Column(name = "VPN_USER08_PASS")
	@Schema(description = "VPN_USER08_PASS", required = false)
	private String vpnUser08Pass;

	/**
	 * VPN_USER09_PASS
	 */
	@Column(name = "VPN_USER09_PASS")
	@Schema(description = "VPN_USER09_PASS", required = false)
	private String vpnUser09Pass;

	/**
	* VPN_USER10_PASS
	*/
	@Column(name = "VPN_USER10_PASS")
	@Schema(description = "VPN_USER_10_PASS", required = false)
	private String vpnUser10Pass;

	/**
	 * remote-address
	 */
	@Schema(description = "remote-address", required = false)
	private String remoteAddress;

	/**
	 * policybase-preshared-key
	 */
	@Schema(description = "policybase-preshared-key", required = false)
	private String policybasePresharedKey;

	/**
	 * fqdn
	 */
	@Schema(description = "fqdn", required = false)
	private String fqdn;

	/**
	 * リザーブ_6
	 */
	@Column(name = "RESERVE_6")
	@Schema(description = "リザーブ_6", required = false)
	private String reserve6;

	/**
	 * リザーブ_7
	 */
	@Column(name = "RESERVE_7")
	@Schema(description = "リザーブ_7", required = false)
	private String reserve7;

	/**
	 * リザーブ_8
	 */
	@Column(name = "RESERVE_8")
	@Schema(description = "リザーブ_8", required = false)
	private String reserve8;

	/**
	 * リザーブ_9
	 */
	@Column(name = "RESERVE_9")
	@Schema(description = "リザーブ_9", required = false)
	private String reserve9;

	/**
	 * リザーブ_10
	 */
	@Column(name = "RESERVE_10")
	@Schema(description = "リザーブ_10", required = false)
	private String reserve10;

	/**
	 * リザーブ_11
	 */
	@Column(name = "RESERVE_11")
	@Schema(description = "リザーブ_11", required = false)
	private String reserve11;

	/**
	 * リザーブ_12
	 */
	@Column(name = "RESERVE_12")
	@Schema(description = "リザーブ_12", required = false)
	private String reserve12;
	/**
	 * リザーブ_13
	 */
	@Column(name = "RESERVE_13")
	@Schema(description = "リザーブ_13", required = false)
	private String reserve13;

	/**
	 * リザーブ_14
	 */
	@Column(name = "RESERVE_14")
	@Schema(description = "リザーブ_14", required = false)
	private String reserve14;

	/**
	 * リザーブ_15
	 */
	@Column(name = "RESERVE_15")
	@Schema(description = "リザーブ_15", required = false)
	private String reserve15;

	/**
	 * リザーブ_16
	 */
	@Column(name = "RESERVE_16")
	@Schema(description = "リザーブ_16", required = false)
	private String reserve16;

	/**
	 * リザーブ_17
	 */
	@Column(name = "RESERVE_17")
	@Schema(description = "リザーブ_17", required = false)
	private String reserve17;

}
