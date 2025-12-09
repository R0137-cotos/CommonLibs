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
	@Schema(description = "ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonIgnore
	private long id;

	/**
	 * AP区分
	 */
	@Schema(description = "AP区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "AP以外(\"1\"), AP(\"2\")")
	private String apFlg;

	/**
	 * SACMマネジメントコード
	 */
	@Schema(description = "SACMマネジメントコード", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String sacmManageCode;

	/**
	 * サービスアダプタコード
	 */
	@Schema(description = "サービスアダプタコード", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String laitSaCode;

	/**
	 * サービスアダプタラベル
	 */
	@Schema(description = "サービスアダプタラベル", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String laitSaLabel;

	/**
	 * SSHDサービス
	 */
	@Schema(description = "SSHDサービス", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String sshdService;

	/**
	 * UPNPサービス
	 */
	@Schema(description = "UPNPサービス", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String upnpService;

	/**
	 * インターネットアカウント
	 */
	@Schema(description = "インターネットアカウント", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String internetAccount;

	/**
	 * インターネットPASS
	 */
	@Schema(description = "インターネットPASS", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String internetPass;

	/**
	 * PPPOE_MTU
	 */
	@Schema(description = "PPPOE_MTU", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String pppoeMtu;

	/**
	 * GE0メディア
	 */
	@Column(name = "GE0_MEDIA")
	@Schema(description = "GE0メディア", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String ge0Media;

	/**
	 * LANアドレス
	 */
	@Schema(description = "LANアドレス", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String lanAddress;

	/**
	 * LANサブネット
	 */
	@Schema(description = "LANサブネット", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String lanSubnet;

	/**
	 * GE1P0メディア
	 */
	@Column(name = "GE1P0_MEDIA")
	@Schema(description = "GE1P0メディア", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String ge1p0Media;

	/**
	 * GE1P1メディア
	 */
	@Column(name = "GE1P1_MEDIA")
	@Schema(description = "GE1P1メディア", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String ge1p1Media;

	/**
	 * GE1P2メディア
	 */
	@Column(name = "GE1P2_MEDIA")
	@Schema(description = "GE1P2メディア", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String ge1p2Media;

	/**
	 * GE1P3メディア
	 */
	@Column(name = "GE1P3_MEDIA")
	@Schema(description = "GE1P3メディア", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String ge1p3Media;

	/**
	 * IPV6ブリッジ
	 */
	@Column(name = "IPV6_BRIDGE")
	@Schema(description = "IPV6ブリッジ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String ipv6Bridge;

	/**
	 * マイID
	 */
	@Schema(description = "マイID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String myId;

	/**
	 * カスタマーID
	 */
	@Schema(description = "カスタマーID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String customerId;

	/**
	 * PRESHAREDキー
	 */
	@Schema(description = "PRESHAREDキー", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String presharedKey;

	/**
	 * WLANサービス
	 */
	@Schema(description = "WLANサービス", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String wlanService;

	/**
	 * WLANモード
	 */
	@Schema(description = "WLANモード", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String wlanMode;

	/**
	 * WLAN_SSID_0
	 */
	@Column(name = "WLAN_SSID_0")
	@Schema(description = "WLAN_SSID_0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String wlanSsid0;

	/**
	 * WLAN_HIDE_0
	 */
	@Column(name = "WLAN_HIDE_0")
	@Schema(description = "WLAN_HIDE_0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String wlanHide0;

	/**
	 * WLANセキュリティ_0
	 */
	@Column(name = "WLAN_SECURITY_0")
	@Schema(description = "WLANセキュリティ_0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String wlanSecurity0;

	/**
	 * WLANチャンネル
	 */
	@Schema(description = "WLANチャンネル", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String wlanChannel;

	/**
	 * WLANパス_0
	 */
	@Column(name = "WLAN_PASS_0")
	@Schema(description = "WLANパス_0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String wlanPass0;

	/**
	 * WLAN_WEP_0
	 */
	@Column(name = "WLAN_WEP_0")
	@Schema(description = "WLAN_WEP_0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String wlanWep0;

	/**
	 * WLANパワー
	 */
	@Schema(description = "WLANパワー", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String wlanPower;

	/**
	 * WLANマックスクライアント_0
	 */
	@Column(name = "WLAN_MAXCLIENT_0")
	@Schema(description = "WLANマックスクライアント_0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String wlanMaxclient0;

	/**
	 * WLANサービス_5G
	 */
	@Column(name = "WLAN_SERVICE_5G")
	@Schema(description = "WLANサービス_5G", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String wlanService5g;

	/**
	 * WLANモード_5G
	 */
	@Column(name = "WLAN_MODE_5G")
	@Schema(description = "WLANモード_5G", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String wlanMode5g;

	/**
	 * WLAN_SSID_0_5G
	 */
	@Column(name = "WLAN_SSID_0_5G")
	@Schema(description = "WLAN_SSID_0_5G", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String wlanSsid05g;

	/**
	 * WLAN_HIDE_0_5G
	 */
	@Column(name = "WLAN_HIDE_0_5G")
	@Schema(description = "WLAN_HIDE_0_5G", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String wlanHide05g;

	/**
	 * WLANセキュリティ_0_5G
	 */
	@Column(name = "WLAN_SECURITY_0_5G")
	@Schema(description = "WLANセキュリティ_0_5G", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String wlanSecurity05g;

	/**
	 * WLAN_チャンネル_5G
	 */
	@Column(name = "WLAN_CHANNEL_5G")
	@Schema(description = "WLAN_チャンネル_5G", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String wlanChannel5g;

	/**
	 * WLAN_PASS_0_5G
	 */
	@Column(name = "WLAN_PASS_0_5G")
	@Schema(description = "WLAN_PASS_0_5G", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String wlanPass05g;

	/**
	 * WLAN_WEP_0_5G
	 */
	@Column(name = "WLAN_WEP_0_5G")
	@Schema(description = "WLAN_WEP_0_5G", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String wlanWep05g;

	/**
	 * WLAN_パワー_5G
	 */
	@Column(name = "WLAN_POWER_5G")
	@Schema(description = "WLAN_パワー_5G", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String wlanPower5g;

	/**
	 * WLANマックスクライアント_0_5G
	 */
	@Column(name = "WLAN_MAXCLIENT_0_5G")
	@Schema(description = "WLANマックスクライアント_0_5G", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String wlanMaxclient05g;

	/**
	 * LANセグメント
	 */
	@Schema(description = "LANセグメント", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String lanSegment;

	/**
	 * VPN番号
	 */
	@Schema(description = "VPN番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String vpnNumber;

	/**
	 * フォワードプロトコル_0
	 */
	@Column(name = "FORWARD_PROTOCOL_0")
	@Schema(description = "フォワードプロトコル_0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String forwardProtocol0;

	/**
	 * リッスンポート_0
	 */
	@Column(name = "LISTEN_PORT_0")
	@Schema(description = "リッスンポート_0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String listenPort0;

	/**
	 * フォワードアドレス_0
	 */
	@Column(name = "FORWARD_ADDRESS_0")
	@Schema(description = "フォワードアドレス_0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String forwardAddress0;

	/**
	 * フォワードポート_0
	 */
	@Column(name = "FORWARD_PORT_0")
	@Schema(description = "フォワードポート_0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String forwardPort0;

	/**
	 * フォワードプロトコル_1
	 */
	@Column(name = "FORWARD_PROTOCOL_1")
	@Schema(description = "フォワードプロトコル_1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String forwardProtocol1;

	/**
	 * リッスンポート_1
	 */
	@Column(name = "LISTEN_PORT_1")
	@Schema(description = "リッスンポート_1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String listenPort1;

	/**
	 * フォワードアドレス_1
	 */
	@Column(name = "FORWARD_ADDRESS_1")
	@Schema(description = "フォワードアドレス_1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String forwardAddress1;

	/**
	 * フォワードポート_1
	 */
	@Column(name = "FORWARD_PORT_1")
	@Schema(description = "フォワードポート_1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String forwardPort1;

	/**
	 * フォワードプロトコル_2
	 */
	@Column(name = "FORWARD_PROTOCOL_2")
	@Schema(description = "フォワードプロトコル_2", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String forwardProtocol2;

	/**
	 * リッスンポート_2
	 */
	@Column(name = "LISTEN_PORT_2")
	@Schema(description = "リッスンポート_2", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String listenPort2;

	/**
	 * フォワードアドレス_2
	 */
	@Column(name = "FORWARD_ADDRESS_2")
	@Schema(description = "フォワードアドレス_2", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String forwardAddress2;

	/**
	 * フォワードポート_2
	 */
	@Column(name = "FORWARD_PORT_2")
	@Schema(description = "フォワードポート_2", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String forwardPort2;

	/**
	 * フォワードプロトコル_3
	 */
	@Column(name = "FORWARD_PROTOCOL_3")
	@Schema(description = "フォワードプロトコル_3", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String forwardProtocol3;

	/**
	 * リッスンポート_3
	 */
	@Column(name = "LISTEN_PORT_3")
	@Schema(description = "リッスンポート_3", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String listenPort3;

	/**
	 * フォワードアドレス_3
	 */
	@Column(name = "FORWARD_ADDRESS_3")
	@Schema(description = "フォワードアドレス_3", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String forwardAddress3;

	/**
	 * フォワードポート_3
	 */
	@Column(name = "FORWARD_PORT_3")
	@Schema(description = "フォワードポート_3", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String forwardPort3;

	/**
	 * フォワードプロトコル_4
	 */
	@Column(name = "FORWARD_PROTOCOL_4")
	@Schema(description = "フォワードプロトコル_4", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String forwardProtocol4;

	/**
	 * リッスンポート_4
	 */
	@Column(name = "LISTEN_PORT_4")
	@Schema(description = "リッスンポート_4", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String listenPort4;

	/**
	 * フォワードアドレス_4
	 */
	@Column(name = "FORWARD_ADDRESS_4")
	@Schema(description = "フォワードアドレス_4", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String forwardAddress4;

	/**
	 * フォワードポート_4
	 */
	@Column(name = "FORWARD_PORT_4")
	@Schema(description = "フォワードポート_4", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String forwardPort4;

	/**
	 * フォワードプロトコル_5
	 */
	@Column(name = "FORWARD_PROTOCOL_5")
	@Schema(description = "フォワードプロトコル_5", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String forwardProtocol5;

	/**
	 * リッスンポート_5
	 */
	@Column(name = "LISTEN_PORT_5")
	@Schema(description = "リッスンポート_5", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String listenPort5;

	/**
	 * フォワードアドレス_5
	 */
	@Column(name = "FORWARD_ADDRESS_5")
	@Schema(description = "フォワードアドレス_5", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String forwardAddress5;

	/**
	 * フォワードポート_5
	 */
	@Column(name = "FORWARD_PORT_5")
	@Schema(description = "フォワードポート_5", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String forwardPort5;

	/**
	 * フォワードプロトコル_6
	 */
	@Column(name = "FORWARD_PROTOCOL_6")
	@Schema(description = "フォワードプロトコル_6", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String forwardProtocol6;

	/**
	 * リッスンポート_6
	 */
	@Column(name = "LISTEN_PORT_6")
	@Schema(description = "リッスンポート_6", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String listenPort6;

	/**
	 * フォワードアドレス_6
	 */
	@Column(name = "FORWARD_ADDRESS_6")
	@Schema(description = "フォワードアドレス_6", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String forwardAddress6;

	/**
	 * フォワードポート_6
	 */
	@Column(name = "FORWARD_PORT_6")
	@Schema(description = "フォワードポート_6", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String forwardPort6;

	/**
	 * フォワードプロトコル_7
	 */
	@Column(name = "FORWARD_PROTOCOL_7")
	@Schema(description = "フォワードプロトコル_7", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String forwardProtocol7;

	/**
	 * リッスンポート_7
	 */
	@Column(name = "LISTEN_PORT_7")
	@Schema(description = "リッスンポート_7", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String listenPort7;

	/**
	 * フォワードアドレス_7
	 */
	@Column(name = "FORWARD_ADDRESS_7")
	@Schema(description = "フォワードアドレス_7", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String forwardAddress7;

	/**
	 * フォワードポート_7
	 */
	@Column(name = "FORWARD_PORT_7")
	@Schema(description = "フォワードポート_7", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String forwardPort7;

	/**
	 * フォワードプロトコル_8
	 */
	@Column(name = "FORWARD_PROTOCOL_8")
	@Schema(description = "フォワードプロトコル_8", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String forwardProtocol8;

	/**
	 * リッスンポート_8
	 */
	@Column(name = "LISTEN_PORT_8")
	@Schema(description = "リッスンポート_8", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String listenPort8;

	/**
	 * フォワードアドレス_8
	 */
	@Column(name = "FORWARD_ADDRESS_8")
	@Schema(description = "フォワードアドレス_8", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String forwardAddress8;

	/**
	 * フォワードポート_8
	 */
	@Column(name = "FORWARD_PORT_8")
	@Schema(description = "フォワードポート_8", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String forwardPort8;

	/**
	 * フォワードプロトコル_9
	 */
	@Column(name = "FORWARD_PROTOCOL_9")
	@Schema(description = "フォワードプロトコル_9", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String forwardProtocol9;

	/**
	 * リッスンポート_9
	 */
	@Column(name = "LISTEN_PORT_9")
	@Schema(description = "リッスンポート_9", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String listenPort9;

	/**
	 * フォワードアドレス_9
	 */
	@Column(name = "FORWARD_ADDRESS_9")
	@Schema(description = "フォワードアドレス_9", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String forwardAddress9;

	/**
	 * フォワードポート_9
	 */
	@Column(name = "FORWARD_PORT_9")
	@Schema(description = "フォワードポート_9", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String forwardPort9;

	/**
	 * DHCPサーバ
	 */
	@Schema(description = "DHCPサーバ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String dhcpServer;

	/**
	 * DHCP期限
	 */
	@Schema(description = "DHCP期限", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String dhcpExpire;

	/**
	 * DHCPアドレス
	 */
	@Schema(description = "DHCPアドレス", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String dhcpAddress;

	/**
	 * DHCP番号
	 */
	@Schema(description = "DHCP番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String dhcpNumber;

	/**
	 * DNSサーバ_1
	 */
	@Column(name = "DNS_SERVER_1")
	@Schema(description = "DNSサーバ_1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String dnsServer1;

	/**
	 * DNSフォワーダー
	 */
	@Schema(description = "DNSフォワーダー", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String dnsForwarder;

	/**
	 * VPNタイプ
	 */
	@Schema(description = "VPNタイプ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String vpnType;

	/**
	 * ICMPリプライ
	 */
	@Schema(description = "ICMPリプライ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String icmpReply;

	/**
	 * DNSサーバ_2
	 */
	@Column(name = "DNS_SERVER_2")
	@Schema(description = "DNSサーバ_2", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String dnsServer2;

	/**
	 * リゾルバ_1
	 */
	@Column(name = "RESOLVER_1")
	@Schema(description = "リゾルバ_1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String resolver1;

	/**
	 * リゾルバ_2
	 */
	@Column(name = "RESOLVER_2")
	@Schema(description = "リゾルバ_2", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String resolver2;

	/**
	 * バンドステアリング
	 */
	@Schema(description = "バンドステアリング", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String bandSteering;

	/**
	 * リザーブ_1
	 */
	@Column(name = "RESERVE_1")
	@Schema(description = "リザーブ_1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String reserve1;

	/**
	 * リザーブ_2
	 */
	@Column(name = "RESERVE_2")
	@Schema(description = "リザーブ_2", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String reserve2;

	/**
	 * リザーブ_3
	 */
	@Column(name = "RESERVE_3")
	@Schema(description = "リザーブ_3", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String reserve3;

	/**
	 * リザーブ_4
	 */
	@Column(name = "RESERVE_4")
	@Schema(description = "リザーブ_4", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String reserve4;

	/**
	 * リザーブ_5
	 */
	@Column(name = "RESERVE_5")
	@Schema(description = "リザーブ_5", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String reserve5;

	/**
	 * RAS
	 */
	@Schema(description = "RAS", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String ras;

	/**
	 * RAS_PSK
	 */
	@Schema(description = "RAS_PSK", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String rasPsk;

	/**
	 * RAS_TOP_ADDRESS
	 */
	@Schema(description = "RAS_TOP_ADDRESS", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String rasTopAddress;

	/**
	 * PROXYARP_RANGE
	 */
	@Schema(description = "PROXYARP_RANGE", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String proxyarpRange;

	/**
	 * VPN_USER01_PASS
	 */
	@Column(name = "VPN_USER01_PASS")
	@Schema(description = "VPN_USER01_PASS", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String vpnUser01Pass;

	/**
	 * VPN_USER02_PASS
	 */
	@Column(name = "VPN_USER02_PASS")
	@Schema(description = "VPN_USER02_PASS", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String vpnUser02Pass;

	/**
	 * VPN_USER03_PASS
	 */
	@Column(name = "VPN_USER03_PASS")
	@Schema(description = "VPN_USER03_PASS", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String vpnUser03Pass;

	/**
	 * VPN_USER04_PASS
	 */
	@Column(name = "VPN_USER04_PASS")
	@Schema(description = "VPN_USER04_PASS", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String vpnUser04Pass;

	/**
	 * VPN_USER05_PASS
	 */
	@Column(name = "VPN_USER05_PASS")
	@Schema(description = "VPN_USER05_PASS", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String vpnUser05Pass;

	/**
	 * VPN_USER06_PASS
	 */
	@Column(name = "VPN_USER06_PASS")
	@Schema(description = "VPN_USER06_PASS", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String vpnUser06Pass;

	/**
	 * VPN_USER07_PASS
	 */
	@Column(name = "VPN_USER07_PASS")
	@Schema(description = "VPN_USER07_PASS", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String vpnUser07Pass;

	/**
	 * VPN_USER08_PASS
	 */
	@Column(name = "VPN_USER08_PASS")
	@Schema(description = "VPN_USER08_PASS", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String vpnUser08Pass;

	/**
	 * VPN_USER09_PASS
	 */
	@Column(name = "VPN_USER09_PASS")
	@Schema(description = "VPN_USER09_PASS", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String vpnUser09Pass;

	/**
	* VPN_USER10_PASS
	*/
	@Column(name = "VPN_USER10_PASS")
	@Schema(description = "VPN_USER_10_PASS", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String vpnUser10Pass;

	/**
	 * remote-address
	 */
	@Schema(description = "remote-address", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String remoteAddress;

	/**
	 * policybase-preshared-key
	 */
	@Schema(description = "policybase-preshared-key", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String policybasePresharedKey;

	/**
	 * fqdn
	 */
	@Schema(description = "fqdn", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String fqdn;

	/**
	 * リザーブ_6
	 */
	@Column(name = "RESERVE_6")
	@Schema(description = "リザーブ_6", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String reserve6;

	/**
	 * リザーブ_7
	 */
	@Column(name = "RESERVE_7")
	@Schema(description = "リザーブ_7", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String reserve7;

	/**
	 * リザーブ_8
	 */
	@Column(name = "RESERVE_8")
	@Schema(description = "リザーブ_8", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String reserve8;

	/**
	 * リザーブ_9
	 */
	@Column(name = "RESERVE_9")
	@Schema(description = "リザーブ_9", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String reserve9;

	/**
	 * リザーブ_10
	 */
	@Column(name = "RESERVE_10")
	@Schema(description = "リザーブ_10", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String reserve10;

	/**
	 * リザーブ_11
	 */
	@Column(name = "RESERVE_11")
	@Schema(description = "リザーブ_11", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String reserve11;

	/**
	 * リザーブ_12
	 */
	@Column(name = "RESERVE_12")
	@Schema(description = "リザーブ_12", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String reserve12;
	/**
	 * リザーブ_13
	 */
	@Column(name = "RESERVE_13")
	@Schema(description = "リザーブ_13", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String reserve13;

	/**
	 * リザーブ_14
	 */
	@Column(name = "RESERVE_14")
	@Schema(description = "リザーブ_14", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String reserve14;

	/**
	 * リザーブ_15
	 */
	@Column(name = "RESERVE_15")
	@Schema(description = "リザーブ_15", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String reserve15;

	/**
	 * リザーブ_16
	 */
	@Column(name = "RESERVE_16")
	@Schema(description = "リザーブ_16", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String reserve16;

	/**
	 * リザーブ_17
	 */
	@Column(name = "RESERVE_17")
	@Schema(description = "リザーブ_17", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String reserve17;

}
