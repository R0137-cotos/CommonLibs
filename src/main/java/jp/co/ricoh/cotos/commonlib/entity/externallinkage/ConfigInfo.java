package jp.co.ricoh.cotos.commonlib.entity.externallinkage;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * コンフィグ情報を表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "config_info")
public class ConfigInfo extends EntityBase {

	/**
	 * コンフィグ情報ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "config_info_seq")
	@SequenceGenerator(name = "config_info_seq", sequenceName = "config_info_seq", allocationSize = 1)
	@ApiModelProperty(value = "コンフィグ情報ID(作成時不要)", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 契約ID
	 */
	@Min(0)
	@ApiModelProperty(value = "契約ID", required = false, position = 2, allowableValues = "range[0,9223372036854775807]")
	private Long contractId;

	/**
	 * RJ管理番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "RJ管理番号", required = false, position = 3, allowableValues = "range[0,255]")
	private String rjManageNumber;

	/**
	 * 連番
	 */
	@Column(nullable = false)
	@ApiModelProperty(value = "連番", required = false, position = 4, allowableValues = "range[0,99]")
	private int seqNo;

	/**
	 * CSV取込日
	 */
	@ApiModelProperty(value = "CSV取込日", required = false, position = 5)
	@Temporal(TemporalType.DATE)
	private Date csvImportDate;

	/**
	 * コンフィグファイル名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "コンフィグファイル名", required = false, position = 6, allowableValues = "range[0,255]")
	private String configFileName;

	/**
	 * SSHDサービス
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "SSHDサービス", required = false, position = 7, allowableValues = "range[0,255]")
	private String sshdService;

	/**
	 * UPNPサービス
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "UPNPサービス", required = false, position = 8, allowableValues = "range[0,255]")
	private String upnpService;

	/**
	 * インターネットアカウント
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "インターネットアカウント", required = false, position = 9, allowableValues = "range[0,255]")
	private String internetAccount;

	/**
	 * インターネットPASS
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "インターネットPASS", required = false, position = 10, allowableValues = "range[0,255]")
	private String internetPass;

	/**
	 * PPPOE_MTU
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "PPPOE_MTU", required = false, position = 11, allowableValues = "range[0,255]")
	private String pppoeMtu;

	/**
	 * GE0メディア
	 */
	@Size(max = 255)
	@Column(name = "GE0_MEDIA")
	@ApiModelProperty(value = "GE0メディア", required = false, position = 12, allowableValues = "range[0,255]")
	private String ge0Media;

	/**
	 * LANアドレス
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "LANアドレス", required = false, position = 13, allowableValues = "range[0,255]")
	private String lanAddress;

	/**
	 * LANサブネット
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "LANサブネット", required = false, position = 14, allowableValues = "range[0,255]")
	private String lanSubnet;

	/**
	 * GE1P0メディア
	 */
	@Size(max = 255)
	@Column(name = "GE1P0_MEDIA")
	@ApiModelProperty(value = "GE1P0メディア", required = false, position = 15, allowableValues = "range[0,255]")
	private String ge1p0Media;

	/**
	 * GE1P1メディア
	 */
	@Size(max = 255)
	@Column(name = "GE1P1_MEDIA")
	@ApiModelProperty(value = "GE1P1メディア", required = false, position = 16, allowableValues = "range[0,255]")
	private String ge1p1Media;

	/**
	 * GE1P2メディア
	 */
	@Size(max = 255)
	@Column(name = "GE1P2_MEDIA")
	@ApiModelProperty(value = "GE1P2メディア", required = false, position = 17, allowableValues = "range[0,255]")
	private String ge1p2Media;

	/**
	 * GE1P3メディア
	 */
	@Size(max = 255)
	@Column(name = "GE1P3_MEDIA")
	@ApiModelProperty(value = "GE1P3メディア", required = false, position = 18, allowableValues = "range[0,255]")
	private String ge1p3media;

	/**
	 * IPV6ブリッジ
	 */
	@Size(max = 255)
	@Column(name = "IPV6_BRIDGE")
	@ApiModelProperty(value = "IPV6ブリッジ", required = false, position = 19, allowableValues = "range[0,255]")
	private String ipv6Bridge;

	/**
	 * マイID
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "マイID", required = false, position = 20, allowableValues = "range[0,255]")
	private String myId;

	/**
	 * カスタマーID
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "カスタマーID", required = false, position = 21, allowableValues = "range[0,255]")
	private String customerId;

	/**
	 * PRESHAREDキー
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "PRESHAREDキー", required = false, position = 22, allowableValues = "range[0,255]")
	private String presharedKey;

	/**
	 * WLANサービス
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "WLANサービス", required = false, position = 23, allowableValues = "range[0,255]")
	private String wlanService;

	/**
	 * WLANモード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "WLANモード", required = false, position = 24, allowableValues = "range[0,255]")
	private String wlanMode;

	/**
	 * WLAN_SSID_0
	 */
	@Size(max = 255)
	@Column(name = "WLAN_SSID_0")
	@ApiModelProperty(value = "WLAN_SSID_0", required = false, position = 25, allowableValues = "range[0,255]")
	private String wlanSsid0;

	/**
	 * WLAN_HIDE_0
	 */
	@Size(max = 255)
	@Column(name = "WLAN_HIDE_0")
	@ApiModelProperty(value = "WLAN_HIDE_0", required = false, position = 26, allowableValues = "range[0,255]")
	private String wlanHide0;

	/**
	 * WLANセキュリティ_0
	 */
	@Size(max = 255)
	@Column(name = "WLAN_SECURITY_0")
	@ApiModelProperty(value = "WLANセキュリティ_0", required = false, position = 27, allowableValues = "range[0,255]")
	private String wlanSecurity0;

	/**
	 * WLANチャンネル
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "WLANチャンネル", required = false, position = 28, allowableValues = "range[0,255]")
	private String wlanChannel;

	/**
	 * WLANパス_0
	 */
	@Size(max = 255)
	@Column(name = "WLAN_PASS_0")
	@ApiModelProperty(value = "WLANパス_0", required = false, position = 29, allowableValues = "range[0,255]")
	private String wlanPass0;

	/**
	 * WLAN_WEP_0
	 */
	@Size(max = 255)
	@Column(name = "WLAN_WEP_0")
	@ApiModelProperty(value = "WLAN_WEP_0", required = false, position = 30, allowableValues = "range[0,255]")
	private String wlanWep0;

	/**
	 * WLANパワー
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "WLANパワー", required = false, position = 31, allowableValues = "range[0,255]")
	private String wlanPower;

	/**
	 * WLANマックスクライアント_0
	 */
	@Size(max = 255)
	@Column(name = "WLAN_MAXCLIENT_0")
	@ApiModelProperty(value = "WLANマックスクライアント_0", required = false, position = 32, allowableValues = "range[0,255]")
	private String wlanMaxclient0;

	/**
	 * WLANサービス_5G
	 */
	@Size(max = 255)
	@Column(name = "WLAN_SERVICE_5G")
	@ApiModelProperty(value = "WLANサービス_5G", required = false, position = 33, allowableValues = "range[0,255]")
	private String wlanService5g;

	/**
	 * WLANモード_5G
	 */
	@Size(max = 255)
	@Column(name = "WLAN_MODE_5G")
	@ApiModelProperty(value = "WLANモード_5G", required = false, position = 34, allowableValues = "range[0,255]")
	private String wlanMode5g;

	/**
	 * WLAN_SSID_0_5G
	 */
	@Size(max = 255)
	@Column(name = "WLAN_SSID_0_5G")
	@ApiModelProperty(value = "WLAN_SSID_0_5G", required = false, position = 35, allowableValues = "range[0,255]")
	private String wlanSsid05g;

	/**
	 * WLAN_HIDE_0_5G
	 */
	@Size(max = 255)
	@Column(name = "WLAN_HIDE_0_5G")
	@ApiModelProperty(value = "WLAN_HIDE_0_5G", required = false, position = 36, allowableValues = "range[0,255]")
	private String wlanHide05g;

	/**
	 * WLANセキュリティ_0_5G
	 */
	@Size(max = 255)
	@Column(name = "WLAN_SECURITY_0_5G")
	@ApiModelProperty(value = "WLANセキュリティ_0_5G", required = false, position = 37, allowableValues = "range[0,255]")
	private String wlanSecurity05g;

	/**
	 * WLAN_チャンネル_5G
	 */
	@Size(max = 255)
	@Column(name = "WLAN_CHANNEL_5G")
	@ApiModelProperty(value = "WLAN_チャンネル_5G", required = false, position = 38, allowableValues = "range[0,255]")
	private String wlanChannel5g;

	/**
	 * WLAN_PASS_0_5G
	 */
	@Size(max = 255)
	@Column(name = "WLAN_PASS_0_5G")
	@ApiModelProperty(value = "WLAN_PASS_0_5G", required = false, position = 39, allowableValues = "range[0,255]")
	private String wlanPass05g;

	/**
	 * WLAN_WEP_0_5G
	 */
	@Size(max = 255)
	@Column(name = "WLAN_WEP_0_5G")
	@ApiModelProperty(value = "WLAN_WEP_0_5G", required = false, position = 40, allowableValues = "range[0,255]")
	private String wlanWep05G;

	/**
	 * WLAN_パワー_5G
	 */
	@Size(max = 255)
	@Column(name = "WLAN_POWER_5G")
	@ApiModelProperty(value = "WLAN_パワー_5G", required = false, position = 41, allowableValues = "range[0,255]")
	private String wlanPower5g;

	/**
	 * WLANマックスクライアント_0_5G
	 */
	@Size(max = 255)
	@Column(name = "WLAN_MAXCLIENT_0_5G")
	@ApiModelProperty(value = "WLANマックスクライアント_0_5G", required = false, position = 42, allowableValues = "range[0,255]")
	private String wlanMaxclient05g;

	/**
	 * LANセグメント
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "LANセグメント", required = false, position = 43, allowableValues = "range[0,255]")
	private String lanSegment;

	/**
	 * VPN番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "VPN番号", required = false, position = 44, allowableValues = "range[0,255]")
	private String vpnNumber;

	/**
	 * フォワードプロトコル_0
	 */
	@Size(max = 255)
	@Column(name = "FORWARD_PROTOCOL_0")
	@ApiModelProperty(value = "フォワードプロトコル_0", required = false, position = 45, allowableValues = "range[0,255]")
	private String forwardProtocol0;

	/**
	 * リッスンポート_0
	 */
	@Size(max = 255)
	@Column(name = "LISTEN_PORT_0")
	@ApiModelProperty(value = "リッスンポート_0", required = false, position = 46, allowableValues = "range[0,255]")
	private String listenPort0;

	/**
	 * フォワードアドレス_0
	 */
	@Size(max = 255)
	@Column(name = "FORWARD_ADDRESS_0")
	@ApiModelProperty(value = "フォワードアドレス_0", required = false, position = 47, allowableValues = "range[0,255]")
	private String forwardAddress0;

	/**
	 * フォワードポート_0
	 */
	@Size(max = 255)
	@Column(name = "FORWARD_PORT_0")
	@ApiModelProperty(value = "フォワードポート_0", required = false, position = 48, allowableValues = "range[0,255]")
	private String forwardPort0;

	/**
	 * フォワードプロトコル_1
	 */
	@Size(max = 255)
	@Column(name = "FORWARD_PROTOCOL_1")
	@ApiModelProperty(value = "フォワードプロトコル_1", required = false, position = 49, allowableValues = "range[0,255]")
	private String forwardProtocol1;

	/**
	 * リッスンポート_1
	 */
	@Size(max = 255)
	@Column(name = "LISTEN_PORT_1")
	@ApiModelProperty(value = "リッスンポート_1", required = false, position = 50, allowableValues = "range[0,255]")
	private String listenPort1;

	/**
	 * フォワードアドレス_1
	 */
	@Size(max = 255)
	@Column(name = "FORWARD_ADDRESS_1")
	@ApiModelProperty(value = "フォワードアドレス_1", required = false, position = 51, allowableValues = "range[0,255]")
	private String forwardAddress1;

	/**
	 * フォワードポート_1
	 */
	@Size(max = 255)
	@Column(name = "FORWARD_PORT_1")
	@ApiModelProperty(value = "フォワードポート_1", required = false, position = 52, allowableValues = "range[0,255]")
	private String forwardPort1;

	/**
	 * フォワードプロトコル_2
	 */
	@Size(max = 255)
	@Column(name = "FORWARD_PROTOCOL_2")
	@ApiModelProperty(value = "フォワードプロトコル_2", required = false, position = 53, allowableValues = "range[0,255]")
	private String forwardProtocol2;

	/**
	 * リッスンポート_2
	 */
	@Size(max = 255)
	@Column(name = "LISTEN_PORT_2")
	@ApiModelProperty(value = "リッスンポート_2", required = false, position = 54, allowableValues = "range[0,255]")
	private String listenPort2;

	/**
	 * フォワードアドレス_2
	 */
	@Size(max = 255)
	@Column(name = "FORWARD_ADDRESS_2")
	@ApiModelProperty(value = "フォワードアドレス_2", required = false, position = 55, allowableValues = "range[0,255]")
	private String forwardAddress2;

	/**
	 * フォワードポート_2
	 */
	@Size(max = 255)
	@Column(name = "FORWARD_PORT_2")
	@ApiModelProperty(value = "フォワードポート_2", required = false, position = 56, allowableValues = "range[0,255]")
	private String forwardPort2;

	/**
	 * フォワードプロトコル_3
	 */
	@Size(max = 255)
	@Column(name = "FORWARD_PROTOCOL_3")
	@ApiModelProperty(value = "フォワードプロトコル_3", required = false, position = 57, allowableValues = "range[0,255]")
	private String forwardProtocol3;

	/**
	 * リッスンポート_3
	 */
	@Size(max = 255)
	@Column(name = "LISTEN_PORT_3")
	@ApiModelProperty(value = "リッスンポート_3", required = false, position = 58, allowableValues = "range[0,255]")
	private String listenPort3;

	/**
	 * フォワードアドレス_3
	 */
	@Size(max = 255)
	@Column(name = "FORWARD_ADDRESS_3")
	@ApiModelProperty(value = "フォワードアドレス_3", required = false, position = 59, allowableValues = "range[0,255]")
	private String forwardAddress3;

	/**
	 * フォワードポート_3
	 */
	@Size(max = 255)
	@Column(name = "FORWARD_PORT_3")
	@ApiModelProperty(value = "フォワードポート_3", required = false, position = 60, allowableValues = "range[0,255]")
	private String forwardPort3;

	/**
	 * フォワードプロトコル_4
	 */
	@Size(max = 255)
	@Column(name = "FORWARD_PROTOCOL_4")
	@ApiModelProperty(value = "フォワードプロトコル_4", required = false, position = 61, allowableValues = "range[0,255]")
	private String forwardProtocol4;

	/**
	 * リッスンポート_4
	 */
	@Size(max = 255)
	@Column(name = "LISTEN_PORT_4")
	@ApiModelProperty(value = "リッスンポート_4", required = false, position = 62, allowableValues = "range[0,255]")
	private String listenPort4;

	/**
	 * フォワードアドレス_4
	 */
	@Size(max = 255)
	@Column(name = "FORWARD_ADDRESS_4")
	@ApiModelProperty(value = "フォワードアドレス_4", required = false, position = 63, allowableValues = "range[0,255]")
	private String forwardAddress4;

	/**
	 * フォワードポート_4
	 */
	@Size(max = 255)
	@Column(name = "FORWARD_PORT_4")
	@ApiModelProperty(value = "フォワードポート_4", required = false, position = 64, allowableValues = "range[0,255]")
	private String forwardPort4;

	/**
	 * フォワードプロトコル_5
	 */
	@Size(max = 255)
	@Column(name = "FORWARD_PROTOCOL_5")
	@ApiModelProperty(value = "フォワードプロトコル_5", required = false, position = 65, allowableValues = "range[0,255]")
	private String forwardProtocol5;

	/**
	 * リッスンポート_5
	 */
	@Size(max = 255)
	@Column(name = "LISTEN_PORT_5")
	@ApiModelProperty(value = "リッスンポート_5", required = false, position = 66, allowableValues = "range[0,255]")
	private String listenPort5;

	/**
	 * フォワードアドレス_5
	 */
	@Size(max = 255)
	@Column(name = "FORWARD_ADDRESS_5")
	@ApiModelProperty(value = "フォワードアドレス_5", required = false, position = 67, allowableValues = "range[0,255]")
	private String forwardAddress5;

	/**
	 * フォワードポート_5
	 */
	@Size(max = 255)
	@Column(name = "FORWARD_PORT_5")
	@ApiModelProperty(value = "フォワードポート_5", required = false, position = 68, allowableValues = "range[0,255]")
	private String forwardPort5;

	/**
	 * フォワードプロトコル_6
	 */
	@Size(max = 255)
	@Column(name = "FORWARD_PROTOCOL_6")
	@ApiModelProperty(value = "フォワードプロトコル_6", required = false, position = 69, allowableValues = "range[0,255]")
	private String forwardProtocol6;

	/**
	 * リッスンポート_6
	 */
	@Size(max = 255)
	@Column(name = "LISTEN_PORT_6")
	@ApiModelProperty(value = "リッスンポート_6", required = false, position = 70, allowableValues = "range[0,255]")
	private String listenPort6;

	/**
	 * フォワードアドレス_6
	 */
	@Size(max = 255)
	@Column(name = "FORWARD_ADDRESS_6")
	@ApiModelProperty(value = "フォワードアドレス_6", required = false, position = 71, allowableValues = "range[0,255]")
	private String forwardAddress6;

	/**
	 * フォワードポート_6
	 */
	@Size(max = 255)
	@Column(name = "FORWARD_PORT_6")
	@ApiModelProperty(value = "フォワードポート_6", required = false, position = 72, allowableValues = "range[0,255]")
	private String forwardPort6;

	/**
	 * フォワードプロトコル_7
	 */
	@Size(max = 255)
	@Column(name = "FORWARD_PROTOCOL_7")
	@ApiModelProperty(value = "フォワードプロトコル_7", required = false, position = 73, allowableValues = "range[0,255]")
	private String forwardProtocol7;

	/**
	 * リッスンポート_7
	 */
	@Size(max = 255)
	@Column(name = "LISTEN_PORT_7")
	@ApiModelProperty(value = "リッスンポート_7", required = false, position = 74, allowableValues = "range[0,255]")
	private String listenPort7;

	/**
	 * フォワードアドレス_7
	 */
	@Size(max = 255)
	@Column(name = "FORWARD_ADDRESS_7")
	@ApiModelProperty(value = "フォワードアドレス_7", required = false, position = 75, allowableValues = "range[0,255]")
	private String forwardAddress7;

	/**
	 * フォワードポート_7
	 */
	@Size(max = 255)
	@Column(name = "FORWARD_PORT_7")
	@ApiModelProperty(value = "フォワードポート_7", required = false, position = 76, allowableValues = "range[0,255]")
	private String forwardPort7;

	/**
	 * フォワードプロトコル_8
	 */
	@Size(max = 255)
	@Column(name = "FORWARD_PROTOCOL_8")
	@ApiModelProperty(value = "フォワードプロトコル_8", required = false, position = 77, allowableValues = "range[0,255]")
	private String forwardProtocol8;

	/**
	 * リッスンポート_8
	 */
	@Size(max = 255)
	@Column(name = "LISTEN_PORT_8")
	@ApiModelProperty(value = "リッスンポート_8", required = false, position = 78, allowableValues = "range[0,255]")
	private String listenPort8;

	/**
	 * フォワードアドレス_8
	 */
	@Size(max = 255)
	@Column(name = "FORWARD_ADDRESS_8")
	@ApiModelProperty(value = "フォワードアドレス_8", required = false, position = 79, allowableValues = "range[0,255]")
	private String forwardAddress8;

	/**
	 * フォワードポート_8
	 */
	@Size(max = 255)
	@Column(name = "FORWARD_PORT_8")
	@ApiModelProperty(value = "フォワードポート_8", required = false, position = 80, allowableValues = "range[0,255]")
	private String forwardPort8;

	/**
	 * フォワードプロトコル_9
	 */
	@Size(max = 255)
	@Column(name = "FORWARD_PROTOCOL_9")
	@ApiModelProperty(value = "フォワードプロトコル_9", required = false, position = 81, allowableValues = "range[0,255]")
	private String forwardProtocol9;

	/**
	 * リッスンポート_9
	 */
	@Size(max = 255)
	@Column(name = "LISTEN_PORT_9")
	@ApiModelProperty(value = "リッスンポート_9", required = false, position = 82, allowableValues = "range[0,255]")
	private String listenPort9;

	/**
	 * フォワードアドレス_9
	 */
	@Size(max = 255)
	@Column(name = "FORWARD_ADDRESS_9")
	@ApiModelProperty(value = "フォワードアドレス_9", required = false, position = 83, allowableValues = "range[0,255]")
	private String forwardAddress9;

	/**
	 * フォワードポート_9
	 */
	@Size(max = 255)
	@Column(name = "FORWARD_PORT_9")
	@ApiModelProperty(value = "フォワードポート_9", required = false, position = 84, allowableValues = "range[0,255]")
	private String forwardPort9;

	/**
	 * DHCPサーバ
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "DHCPサーバ", required = false, position = 85, allowableValues = "range[0,255]")
	private String dhcpServer;

	/**
	 * DHCP期限
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "DHCP期限", required = false, position = 86, allowableValues = "range[0,255]")
	private String dhcpExpire;

	/**
	 * DHCPアドレス
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "DHCPアドレス", required = false, position = 87, allowableValues = "range[0,255]")
	private String dhcpAddress;

	/**
	 * DHCP番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "DHCP番号", required = false, position = 88, allowableValues = "range[0,255]")
	private String dhcpNumber;

	/**
	 * DNSサーバ_1
	 */
	@Size(max = 255)
	@Column(name = "DNS_SERVER_1")
	@ApiModelProperty(value = "DNSサーバ_1", required = false, position = 89, allowableValues = "range[0,255]")
	private String dnsServer1;

	/**
	 * DNSフォワーダー
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "DNSフォワーダー", required = false, position = 90, allowableValues = "range[0,255]")
	private String dnsForwarder;

	/**
	 * VPNタイプ
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "VPNタイプ", required = false, position = 91, allowableValues = "range[0,255]")
	private String vpnType;

	/**
	 * ICMPリプライ
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "ICMPリプライ", required = false, position = 92, allowableValues = "range[0,255]")
	private String icmpReply;

	/**
	 * DNSサーバ_2
	 */
	@Size(max = 255)
	@Column(name = "DNS_SERVER_2")
	@ApiModelProperty(value = "DNSサーバ_2", required = false, position = 93, allowableValues = "range[0,255]")
	private String dnsServer2;

	/**
	 * リゾルバ_1
	 */
	@Size(max = 255)
	@Column(name = "RESOLVER_1")
	@ApiModelProperty(value = "リゾルバ_1", required = false, position = 94, allowableValues = "range[0,255]")
	private String resolver1;

	/**
	 * リゾルバ_2
	 */
	@Size(max = 255)
	@Column(name = "RESOLVER_2")
	@ApiModelProperty(value = "リゾルバ_2", required = false, position = 95, allowableValues = "range[0,255]")
	private String resolver2;

	/**
	 * バンドステアリング
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "バンドステアリング", required = false, position = 96, allowableValues = "range[0,255]")
	private String bandSteering;

	/**
	 * リザーブ_1
	 */
	@Size(max = 255)
	@Column(name = "RESERVE_1")
	@ApiModelProperty(value = "リザーブ_1", required = false, position = 97, allowableValues = "range[0,255]")
	private String reserve1;

	/**
	 * リザーブ_2
	 */
	@Size(max = 255)
	@Column(name = "RESERVE_2")
	@ApiModelProperty(value = "リザーブ_2", required = false, position = 98, allowableValues = "range[0,255]")
	private String reserve2;

	/**
	 * リザーブ_3
	 */
	@Size(max = 255)
	@Column(name = "RESERVE_3")
	@ApiModelProperty(value = "リザーブ_3", required = false, position = 99, allowableValues = "range[0,255]")
	private String reserve3;

	/**
	 * リザーブ_4
	 */
	@Size(max = 255)
	@Column(name = "RESERVE_4")
	@ApiModelProperty(value = "リザーブ_4", required = false, position = 100, allowableValues = "range[0,255]")
	private String reserve4;

	/**
	 * リザーブ_5
	 */
	@Size(max = 255)
	@Column(name = "RESERVE_5")
	@ApiModelProperty(value = "リザーブ_5", required = false, position = 101, allowableValues = "range[0,255]")
	private String reserve5;

	/**
	 * RAS
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "RAS", required = false, position = 102, allowableValues = "range[0,255]")
	private String ras;

	/**
	 * RAS_PSK
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "RAS_PSK", required = false, position = 103, allowableValues = "range[0,255]")
	private String rasPsk;

	/**
	 * RAS_TOP_ADDRESS
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "RAS_TOP_ADDRESS", required = false, position = 104, allowableValues = "range[0,255]")
	private String rasTopAddress;

	/**
	 * PROXYARP_RANGE
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "PROXYARP_RANGE", required = false, position = 105, allowableValues = "range[0,255]")
	private String proxyarpRange;

	/**
	 * VPN_USER01_PASS
	 */
	@Size(max = 255)
	@Column(name = "VPN_USER01_PASS")
	@ApiModelProperty(value = "VPN_USER01_PASS", required = false, position = 106, allowableValues = "range[0,255]")
	private String vpnUser01Pass;

	/**
	 * VPN_USER02_PASS
	 */
	@Size(max = 255)
	@Column(name = "VPN_USER02_PASS")
	@ApiModelProperty(value = "VPN_USER02_PASS", required = false, position = 107, allowableValues = "range[0,255]")
	private String vpnUser02Pass;

	/**
	 * VPN_USER03_PASS
	 */
	@Size(max = 255)
	@Column(name = "VPN_USER03_PASS")
	@ApiModelProperty(value = "VPN_USER03_PASS", required = false, position = 108, allowableValues = "range[0,255]")
	private String vpnUser03Pass;

	/**
	 * VPN_USER04_PASS
	 */
	@Size(max = 255)
	@Column(name = "VPN_USER04_PASS")
	@ApiModelProperty(value = "VPN_USER04_PASS", required = false, position = 109, allowableValues = "range[0,255]")
	private String vpnUser04Pass;

	/**
	 * VPN_USER05_PASS
	 */
	@Size(max = 255)
	@Column(name = "VPN_USER05_PASS")
	@ApiModelProperty(value = "VPN_USER05_PASS", required = false, position = 110, allowableValues = "range[0,255]")
	private String vpnUser05Pass;

	/**
	 * VPN_USER06_PASS
	 */
	@Size(max = 255)
	@Column(name = "VPN_USER06_PASS")
	@ApiModelProperty(value = "VPN_USER06_PASS", required = false, position = 111, allowableValues = "range[0,255]")
	private String vpnUser06Pass;

	/**
	 * VPN_USER07_PASS
	 */
	@Size(max = 255)
	@Column(name = "VPN_USER07_PASS")
	@ApiModelProperty(value = "VPN_USER07_PASS", required = false, position = 112, allowableValues = "range[0,255]")
	private String vpnUser07Pass;

	/**
	 * VPN_USER08_PASS
	 */
	@Size(max = 255)
	@Column(name = "VPN_USER08_PASS")
	@ApiModelProperty(value = "VPN_USER08_PASS", required = false, position = 113, allowableValues = "range[0,255]")
	private String vpnUser08Pass;

	/**
	 * VPN_USER09_PASS
	 */
	@Size(max = 255)
	@Column(name = "VPN_USER09_PASS")
	@ApiModelProperty(value = "VPN_USER09_PASS", required = false, position = 114, allowableValues = "range[0,255]")
	private String vpnUser09Pass;

	/**
	* VPN_USER10_PASS
	*/
	@Size(max = 255)
	@Column(name = "VPN_USER10_PASS")
	@ApiModelProperty(value = "VPN_USER_10_PASS", required = false, position = 115, allowableValues = "range[0,255]")
	private String vpnUser10Pass;

	/**
	 * remote-address
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "remote-address", required = false, position = 116, allowableValues = "range[0,255]")
	private String remoteAddress;

	/**
	 * policybase-preshared-key
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "policybase-preshared-key", required = false, position = 117, allowableValues = "range[0,255]")
	private String policybasePresharedKey;

	/**
	 * fqdn
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "fqdn", required = false, position = 118, allowableValues = "range[0,255]")
	private String fqdn;

	/**
	 * リザーブ_6
	 */
	@Size(max = 255)
	@Column(name = "RESERVE_6")
	@ApiModelProperty(value = "リザーブ_6", required = false, position = 119, allowableValues = "range[0,255]")
	private String reserve6;

	/**
	 * リザーブ_7
	 */
	@Size(max = 255)
	@Column(name = "RESERVE_7")
	@ApiModelProperty(value = "リザーブ_7", required = false, position = 120, allowableValues = "range[0,255]")
	private String reserve7;

	/**
	 * リザーブ_8
	 */
	@Size(max = 255)
	@Column(name = "RESERVE_8")
	@ApiModelProperty(value = "リザーブ_8", required = false, position = 121, allowableValues = "range[0,255]")
	private String reserve8;

	/**
	 * リザーブ_9
	 */
	@Size(max = 255)
	@Column(name = "RESERVE_9")
	@ApiModelProperty(value = "リザーブ_9", required = false, position = 122, allowableValues = "range[0,255]")
	private String reserve9;

	/**
	 * リザーブ_10
	 */
	@Size(max = 255)
	@Column(name = "RESERVE_10")
	@ApiModelProperty(value = "リザーブ_10", required = false, position = 123, allowableValues = "range[0,255]")
	private String reserve10;

	/**
	 * リザーブ_11
	 */
	@Size(max = 255)
	@Column(name = "RESERVE_11")
	@ApiModelProperty(value = "リザーブ_11", required = false, position = 124, allowableValues = "range[0,255]")
	private String reserve11;

	/**
	 * リザーブ_12
	 */
	@Size(max = 255)
	@Column(name = "RESERVE_12")
	@ApiModelProperty(value = "リザーブ_12", required = false, position = 125, allowableValues = "range[0,255]")
	private String reserve12;
	/**
	 * リザーブ_13
	 */
	@Size(max = 255)
	@Column(name = "RESERVE_13")
	@ApiModelProperty(value = "リザーブ_13", required = false, position = 126, allowableValues = "range[0,255]")
	private String reserve13;

	/**
	 * リザーブ_14
	 */
	@Size(max = 255)
	@Column(name = "RESERVE_14")
	@ApiModelProperty(value = "リザーブ_14", required = false, position = 127, allowableValues = "range[0,255]")
	private String reserve14;

	/**
	 * リザーブ_15
	 */
	@Size(max = 255)
	@Column(name = "RESERVE_15")
	@ApiModelProperty(value = "リザーブ_15", required = false, position = 128, allowableValues = "range[0,255]")
	private String reserve15;

	/**
	 * リザーブ_16
	 */
	@Size(max = 255)
	@Column(name = "RESERVE_16")
	@ApiModelProperty(value = "リザーブ_16", required = false, position = 129, allowableValues = "range[0,255]")
	private String reserve16;

	/**
	 * リザーブ_17
	 */
	@Size(max = 255)
	@Column(name = "RESERVE_17")
	@ApiModelProperty(value = "リザーブ_17", required = false, position = 130, allowableValues = "range[0,255]")
	private String reserve17;

}
