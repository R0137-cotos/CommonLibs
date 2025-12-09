package jp.co.ricoh.cotos.commonlib.entity.externallinkage;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(description = "コンフィグ情報ID(作成時不要)", allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 契約ID
	 */
	@Min(0)
	@Schema(description = "契約ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private Long contractId;

	/**
	 * RJ管理番号
	 */
	@Size(max = 255)
	@Schema(description = "RJ管理番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String rjManageNumber;

	/**
	 * 連番
	 */
	@Column(nullable = false)
	@Schema(description = "連番", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,99]")
	private int seqNo;

	/**
	 * CSV取込日
	 */
	@Schema(description = "CSV取込日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Temporal(TemporalType.DATE)
	private Date csvImportDate;

	/**
	 * コンフィグファイル名
	 */
	@Size(max = 255)
	@Schema(description = "コンフィグファイル名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String configFileName;

	/**
	 * SSHDサービス
	 */
	@Size(max = 255)
	@Schema(description = "SSHDサービス", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String sshdService;

	/**
	 * UPNPサービス
	 */
	@Size(max = 255)
	@Schema(description = "UPNPサービス", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String upnpService;

	/**
	 * インターネットアカウント
	 */
	@Size(max = 255)
	@Schema(description = "インターネットアカウント", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String internetAccount;

	/**
	 * インターネットPASS
	 */
	@Size(max = 255)
	@Schema(description = "インターネットPASS", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String internetPass;

	/**
	 * PPPOE_MTU
	 */
	@Size(max = 255)
	@Schema(description = "PPPOE_MTU", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String pppoeMtu;

	/**
	 * GE0メディア
	 */
	@Size(max = 255)
	@Column(name = "GE0_MEDIA")
	@Schema(description = "GE0メディア", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ge0Media;

	/**
	 * LANアドレス
	 */
	@Size(max = 255)
	@Schema(description = "LANアドレス", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String lanAddress;

	/**
	 * LANサブネット
	 */
	@Size(max = 255)
	@Schema(description = "LANサブネット", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String lanSubnet;

	/**
	 * GE1P0メディア
	 */
	@Size(max = 255)
	@Column(name = "GE1P0_MEDIA")
	@Schema(description = "GE1P0メディア", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ge1p0Media;

	/**
	 * GE1P1メディア
	 */
	@Size(max = 255)
	@Column(name = "GE1P1_MEDIA")
	@Schema(description = "GE1P1メディア", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ge1p1Media;

	/**
	 * GE1P2メディア
	 */
	@Size(max = 255)
	@Column(name = "GE1P2_MEDIA")
	@Schema(description = "GE1P2メディア", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ge1p2Media;

	/**
	 * GE1P3メディア
	 */
	@Size(max = 255)
	@Column(name = "GE1P3_MEDIA")
	@Schema(description = "GE1P3メディア", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ge1p3Media;

	/**
	 * IPV6ブリッジ
	 */
	@Size(max = 255)
	@Column(name = "IPV6_BRIDGE")
	@Schema(description = "IPV6ブリッジ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ipv6Bridge;

	/**
	 * マイID
	 */
	@Size(max = 255)
	@Schema(description = "マイID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String myId;

	/**
	 * カスタマーID
	 */
	@Size(max = 255)
	@Schema(description = "カスタマーID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String customerId;

	/**
	 * PRESHAREDキー
	 */
	@Size(max = 255)
	@Schema(description = "PRESHAREDキー", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String presharedKey;

	/**
	 * WLANサービス
	 */
	@Size(max = 255)
	@Schema(description = "WLANサービス", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String wlanService;

	/**
	 * WLANモード
	 */
	@Size(max = 255)
	@Schema(description = "WLANモード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String wlanMode;

	/**
	 * WLAN_SSID_0
	 */
	@Size(max = 255)
	@Column(name = "WLAN_SSID_0")
	@Schema(description = "WLAN_SSID_0", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String wlanSsid0;

	/**
	 * WLAN_HIDE_0
	 */
	@Size(max = 255)
	@Column(name = "WLAN_HIDE_0")
	@Schema(description = "WLAN_HIDE_0", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String wlanHide0;

	/**
	 * WLANセキュリティ_0
	 */
	@Size(max = 255)
	@Column(name = "WLAN_SECURITY_0")
	@Schema(description = "WLANセキュリティ_0", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String wlanSecurity0;

	/**
	 * WLANチャンネル
	 */
	@Size(max = 255)
	@Schema(description = "WLANチャンネル", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String wlanChannel;

	/**
	 * WLANパス_0
	 */
	@Size(max = 255)
	@Column(name = "WLAN_PASS_0")
	@Schema(description = "WLANパス_0", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String wlanPass0;

	/**
	 * WLAN_WEP_0
	 */
	@Size(max = 255)
	@Column(name = "WLAN_WEP_0")
	@Schema(description = "WLAN_WEP_0", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String wlanWep0;

	/**
	 * WLANパワー
	 */
	@Size(max = 255)
	@Schema(description = "WLANパワー", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String wlanPower;

	/**
	 * WLANマックスクライアント_0
	 */
	@Size(max = 255)
	@Column(name = "WLAN_MAXCLIENT_0")
	@Schema(description = "WLANマックスクライアント_0", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String wlanMaxclient0;

	/**
	 * WLANサービス_5G
	 */
	@Size(max = 255)
	@Column(name = "WLAN_SERVICE_5G")
	@Schema(description = "WLANサービス_5G", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String wlanService5g;

	/**
	 * WLANモード_5G
	 */
	@Size(max = 255)
	@Column(name = "WLAN_MODE_5G")
	@Schema(description = "WLANモード_5G", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String wlanMode5g;

	/**
	 * WLAN_SSID_0_5G
	 */
	@Size(max = 255)
	@Column(name = "WLAN_SSID_0_5G")
	@Schema(description = "WLAN_SSID_0_5G", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String wlanSsid05g;

	/**
	 * WLAN_HIDE_0_5G
	 */
	@Size(max = 255)
	@Column(name = "WLAN_HIDE_0_5G")
	@Schema(description = "WLAN_HIDE_0_5G", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String wlanHide05g;

	/**
	 * WLANセキュリティ_0_5G
	 */
	@Size(max = 255)
	@Column(name = "WLAN_SECURITY_0_5G")
	@Schema(description = "WLANセキュリティ_0_5G", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String wlanSecurity05g;

	/**
	 * WLAN_チャンネル_5G
	 */
	@Size(max = 255)
	@Column(name = "WLAN_CHANNEL_5G")
	@Schema(description = "WLAN_チャンネル_5G", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String wlanChannel5g;

	/**
	 * WLAN_PASS_0_5G
	 */
	@Size(max = 255)
	@Column(name = "WLAN_PASS_0_5G")
	@Schema(description = "WLAN_PASS_0_5G", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String wlanPass05g;

	/**
	 * WLAN_WEP_0_5G
	 */
	@Size(max = 255)
	@Column(name = "WLAN_WEP_0_5G")
	@Schema(description = "WLAN_WEP_0_5G", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String wlanWep05G;

	/**
	 * WLAN_パワー_5G
	 */
	@Size(max = 255)
	@Column(name = "WLAN_POWER_5G")
	@Schema(description = "WLAN_パワー_5G", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String wlanPower5g;

	/**
	 * WLANマックスクライアント_0_5G
	 */
	@Size(max = 255)
	@Column(name = "WLAN_MAXCLIENT_0_5G")
	@Schema(description = "WLANマックスクライアント_0_5G", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String wlanMaxclient05g;

	/**
	 * LANセグメント
	 */
	@Size(max = 255)
	@Schema(description = "LANセグメント", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String lanSegment;

	/**
	 * VPN番号
	 */
	@Size(max = 255)
	@Schema(description = "VPN番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String vpnNumber;

	/**
	 * フォワードプロトコル_0
	 */
	@Size(max = 255)
	@Column(name = "FORWARD_PROTOCOL_0")
	@Schema(description = "フォワードプロトコル_0", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String forwardProtocol0;

	/**
	 * リッスンポート_0
	 */
	@Size(max = 255)
	@Column(name = "LISTEN_PORT_0")
	@Schema(description = "リッスンポート_0", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String listenPort0;

	/**
	 * フォワードアドレス_0
	 */
	@Size(max = 255)
	@Column(name = "FORWARD_ADDRESS_0")
	@Schema(description = "フォワードアドレス_0", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String forwardAddress0;

	/**
	 * フォワードポート_0
	 */
	@Size(max = 255)
	@Column(name = "FORWARD_PORT_0")
	@Schema(description = "フォワードポート_0", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String forwardPort0;

	/**
	 * フォワードプロトコル_1
	 */
	@Size(max = 255)
	@Column(name = "FORWARD_PROTOCOL_1")
	@Schema(description = "フォワードプロトコル_1", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String forwardProtocol1;

	/**
	 * リッスンポート_1
	 */
	@Size(max = 255)
	@Column(name = "LISTEN_PORT_1")
	@Schema(description = "リッスンポート_1", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String listenPort1;

	/**
	 * フォワードアドレス_1
	 */
	@Size(max = 255)
	@Column(name = "FORWARD_ADDRESS_1")
	@Schema(description = "フォワードアドレス_1", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String forwardAddress1;

	/**
	 * フォワードポート_1
	 */
	@Size(max = 255)
	@Column(name = "FORWARD_PORT_1")
	@Schema(description = "フォワードポート_1", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String forwardPort1;

	/**
	 * フォワードプロトコル_2
	 */
	@Size(max = 255)
	@Column(name = "FORWARD_PROTOCOL_2")
	@Schema(description = "フォワードプロトコル_2", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String forwardProtocol2;

	/**
	 * リッスンポート_2
	 */
	@Size(max = 255)
	@Column(name = "LISTEN_PORT_2")
	@Schema(description = "リッスンポート_2", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String listenPort2;

	/**
	 * フォワードアドレス_2
	 */
	@Size(max = 255)
	@Column(name = "FORWARD_ADDRESS_2")
	@Schema(description = "フォワードアドレス_2", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String forwardAddress2;

	/**
	 * フォワードポート_2
	 */
	@Size(max = 255)
	@Column(name = "FORWARD_PORT_2")
	@Schema(description = "フォワードポート_2", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String forwardPort2;

	/**
	 * フォワードプロトコル_3
	 */
	@Size(max = 255)
	@Column(name = "FORWARD_PROTOCOL_3")
	@Schema(description = "フォワードプロトコル_3", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String forwardProtocol3;

	/**
	 * リッスンポート_3
	 */
	@Size(max = 255)
	@Column(name = "LISTEN_PORT_3")
	@Schema(description = "リッスンポート_3", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String listenPort3;

	/**
	 * フォワードアドレス_3
	 */
	@Size(max = 255)
	@Column(name = "FORWARD_ADDRESS_3")
	@Schema(description = "フォワードアドレス_3", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String forwardAddress3;

	/**
	 * フォワードポート_3
	 */
	@Size(max = 255)
	@Column(name = "FORWARD_PORT_3")
	@Schema(description = "フォワードポート_3", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String forwardPort3;

	/**
	 * フォワードプロトコル_4
	 */
	@Size(max = 255)
	@Column(name = "FORWARD_PROTOCOL_4")
	@Schema(description = "フォワードプロトコル_4", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String forwardProtocol4;

	/**
	 * リッスンポート_4
	 */
	@Size(max = 255)
	@Column(name = "LISTEN_PORT_4")
	@Schema(description = "リッスンポート_4", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String listenPort4;

	/**
	 * フォワードアドレス_4
	 */
	@Size(max = 255)
	@Column(name = "FORWARD_ADDRESS_4")
	@Schema(description = "フォワードアドレス_4", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String forwardAddress4;

	/**
	 * フォワードポート_4
	 */
	@Size(max = 255)
	@Column(name = "FORWARD_PORT_4")
	@Schema(description = "フォワードポート_4", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String forwardPort4;

	/**
	 * フォワードプロトコル_5
	 */
	@Size(max = 255)
	@Column(name = "FORWARD_PROTOCOL_5")
	@Schema(description = "フォワードプロトコル_5", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String forwardProtocol5;

	/**
	 * リッスンポート_5
	 */
	@Size(max = 255)
	@Column(name = "LISTEN_PORT_5")
	@Schema(description = "リッスンポート_5", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String listenPort5;

	/**
	 * フォワードアドレス_5
	 */
	@Size(max = 255)
	@Column(name = "FORWARD_ADDRESS_5")
	@Schema(description = "フォワードアドレス_5", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String forwardAddress5;

	/**
	 * フォワードポート_5
	 */
	@Size(max = 255)
	@Column(name = "FORWARD_PORT_5")
	@Schema(description = "フォワードポート_5", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String forwardPort5;

	/**
	 * フォワードプロトコル_6
	 */
	@Size(max = 255)
	@Column(name = "FORWARD_PROTOCOL_6")
	@Schema(description = "フォワードプロトコル_6", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String forwardProtocol6;

	/**
	 * リッスンポート_6
	 */
	@Size(max = 255)
	@Column(name = "LISTEN_PORT_6")
	@Schema(description = "リッスンポート_6", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String listenPort6;

	/**
	 * フォワードアドレス_6
	 */
	@Size(max = 255)
	@Column(name = "FORWARD_ADDRESS_6")
	@Schema(description = "フォワードアドレス_6", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String forwardAddress6;

	/**
	 * フォワードポート_6
	 */
	@Size(max = 255)
	@Column(name = "FORWARD_PORT_6")
	@Schema(description = "フォワードポート_6", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String forwardPort6;

	/**
	 * フォワードプロトコル_7
	 */
	@Size(max = 255)
	@Column(name = "FORWARD_PROTOCOL_7")
	@Schema(description = "フォワードプロトコル_7", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String forwardProtocol7;

	/**
	 * リッスンポート_7
	 */
	@Size(max = 255)
	@Column(name = "LISTEN_PORT_7")
	@Schema(description = "リッスンポート_7", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String listenPort7;

	/**
	 * フォワードアドレス_7
	 */
	@Size(max = 255)
	@Column(name = "FORWARD_ADDRESS_7")
	@Schema(description = "フォワードアドレス_7", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String forwardAddress7;

	/**
	 * フォワードポート_7
	 */
	@Size(max = 255)
	@Column(name = "FORWARD_PORT_7")
	@Schema(description = "フォワードポート_7", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String forwardPort7;

	/**
	 * フォワードプロトコル_8
	 */
	@Size(max = 255)
	@Column(name = "FORWARD_PROTOCOL_8")
	@Schema(description = "フォワードプロトコル_8", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String forwardProtocol8;

	/**
	 * リッスンポート_8
	 */
	@Size(max = 255)
	@Column(name = "LISTEN_PORT_8")
	@Schema(description = "リッスンポート_8", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String listenPort8;

	/**
	 * フォワードアドレス_8
	 */
	@Size(max = 255)
	@Column(name = "FORWARD_ADDRESS_8")
	@Schema(description = "フォワードアドレス_8", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String forwardAddress8;

	/**
	 * フォワードポート_8
	 */
	@Size(max = 255)
	@Column(name = "FORWARD_PORT_8")
	@Schema(description = "フォワードポート_8", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String forwardPort8;

	/**
	 * フォワードプロトコル_9
	 */
	@Size(max = 255)
	@Column(name = "FORWARD_PROTOCOL_9")
	@Schema(description = "フォワードプロトコル_9", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String forwardProtocol9;

	/**
	 * リッスンポート_9
	 */
	@Size(max = 255)
	@Column(name = "LISTEN_PORT_9")
	@Schema(description = "リッスンポート_9", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String listenPort9;

	/**
	 * フォワードアドレス_9
	 */
	@Size(max = 255)
	@Column(name = "FORWARD_ADDRESS_9")
	@Schema(description = "フォワードアドレス_9", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String forwardAddress9;

	/**
	 * フォワードポート_9
	 */
	@Size(max = 255)
	@Column(name = "FORWARD_PORT_9")
	@Schema(description = "フォワードポート_9", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String forwardPort9;

	/**
	 * DHCPサーバ
	 */
	@Size(max = 255)
	@Schema(description = "DHCPサーバ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String dhcpServer;

	/**
	 * DHCP期限
	 */
	@Size(max = 255)
	@Schema(description = "DHCP期限", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String dhcpExpire;

	/**
	 * DHCPアドレス
	 */
	@Size(max = 255)
	@Schema(description = "DHCPアドレス", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String dhcpAddress;

	/**
	 * DHCP番号
	 */
	@Size(max = 255)
	@Schema(description = "DHCP番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String dhcpNumber;

	/**
	 * DNSサーバ_1
	 */
	@Size(max = 255)
	@Column(name = "DNS_SERVER_1")
	@Schema(description = "DNSサーバ_1", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String dnsServer1;

	/**
	 * DNSフォワーダー
	 */
	@Size(max = 255)
	@Schema(description = "DNSフォワーダー", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String dnsForwarder;

	/**
	 * VPNタイプ
	 */
	@Size(max = 255)
	@Schema(description = "VPNタイプ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String vpnType;

	/**
	 * ICMPリプライ
	 */
	@Size(max = 255)
	@Schema(description = "ICMPリプライ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String icmpReply;

	/**
	 * DNSサーバ_2
	 */
	@Size(max = 255)
	@Column(name = "DNS_SERVER_2")
	@Schema(description = "DNSサーバ_2", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String dnsServer2;

	/**
	 * リゾルバ_1
	 */
	@Size(max = 255)
	@Column(name = "RESOLVER_1")
	@Schema(description = "リゾルバ_1", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String resolver1;

	/**
	 * リゾルバ_2
	 */
	@Size(max = 255)
	@Column(name = "RESOLVER_2")
	@Schema(description = "リゾルバ_2", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String resolver2;

	/**
	 * バンドステアリング
	 */
	@Size(max = 255)
	@Schema(description = "バンドステアリング", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String bandSteering;

	/**
	 * リザーブ_1
	 */
	@Size(max = 255)
	@Column(name = "RESERVE_1")
	@Schema(description = "リザーブ_1", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String reserve1;

	/**
	 * リザーブ_2
	 */
	@Size(max = 255)
	@Column(name = "RESERVE_2")
	@Schema(description = "リザーブ_2", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String reserve2;

	/**
	 * リザーブ_3
	 */
	@Size(max = 255)
	@Column(name = "RESERVE_3")
	@Schema(description = "リザーブ_3", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String reserve3;

	/**
	 * リザーブ_4
	 */
	@Size(max = 255)
	@Column(name = "RESERVE_4")
	@Schema(description = "リザーブ_4", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String reserve4;

	/**
	 * リザーブ_5
	 */
	@Size(max = 255)
	@Column(name = "RESERVE_5")
	@Schema(description = "リザーブ_5", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String reserve5;

	/**
	 * RAS
	 */
	@Size(max = 255)
	@Schema(description = "RAS", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ras;

	/**
	 * RAS_PSK
	 */
	@Size(max = 255)
	@Schema(description = "RAS_PSK", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String rasPsk;

	/**
	 * RAS_TOP_ADDRESS
	 */
	@Size(max = 255)
	@Schema(description = "RAS_TOP_ADDRESS", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String rasTopAddress;

	/**
	 * PROXYARP_RANGE
	 */
	@Size(max = 255)
	@Schema(description = "PROXYARP_RANGE", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String proxyarpRange;

	/**
	 * VPN_USER01_PASS
	 */
	@Size(max = 255)
	@Column(name = "VPN_USER01_PASS")
	@Schema(description = "VPN_USER01_PASS", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String vpnUser01Pass;

	/**
	 * VPN_USER02_PASS
	 */
	@Size(max = 255)
	@Column(name = "VPN_USER02_PASS")
	@Schema(description = "VPN_USER02_PASS", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String vpnUser02Pass;

	/**
	 * VPN_USER03_PASS
	 */
	@Size(max = 255)
	@Column(name = "VPN_USER03_PASS")
	@Schema(description = "VPN_USER03_PASS", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String vpnUser03Pass;

	/**
	 * VPN_USER04_PASS
	 */
	@Size(max = 255)
	@Column(name = "VPN_USER04_PASS")
	@Schema(description = "VPN_USER04_PASS", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String vpnUser04Pass;

	/**
	 * VPN_USER05_PASS
	 */
	@Size(max = 255)
	@Column(name = "VPN_USER05_PASS")
	@Schema(description = "VPN_USER05_PASS", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String vpnUser05Pass;

	/**
	 * VPN_USER06_PASS
	 */
	@Size(max = 255)
	@Column(name = "VPN_USER06_PASS")
	@Schema(description = "VPN_USER06_PASS", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String vpnUser06Pass;

	/**
	 * VPN_USER07_PASS
	 */
	@Size(max = 255)
	@Column(name = "VPN_USER07_PASS")
	@Schema(description = "VPN_USER07_PASS", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String vpnUser07Pass;

	/**
	 * VPN_USER08_PASS
	 */
	@Size(max = 255)
	@Column(name = "VPN_USER08_PASS")
	@Schema(description = "VPN_USER08_PASS", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String vpnUser08Pass;

	/**
	 * VPN_USER09_PASS
	 */
	@Size(max = 255)
	@Column(name = "VPN_USER09_PASS")
	@Schema(description = "VPN_USER09_PASS", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String vpnUser09Pass;

	/**
	* VPN_USER10_PASS
	*/
	@Size(max = 255)
	@Column(name = "VPN_USER10_PASS")
	@Schema(description = "VPN_USER_10_PASS", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String vpnUser10Pass;

	/**
	 * remote-address
	 */
	@Size(max = 255)
	@Schema(description = "remote-address", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String remoteAddress;

	/**
	 * policybase-preshared-key
	 */
	@Size(max = 255)
	@Schema(description = "policybase-preshared-key", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String policybasePresharedKey;

	/**
	 * fqdn
	 */
	@Size(max = 255)
	@Schema(description = "fqdn", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String fqdn;

	/**
	 * リザーブ_6
	 */
	@Size(max = 255)
	@Column(name = "RESERVE_6")
	@Schema(description = "リザーブ_6", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String reserve6;

	/**
	 * リザーブ_7
	 */
	@Size(max = 255)
	@Column(name = "RESERVE_7")
	@Schema(description = "リザーブ_7", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String reserve7;

	/**
	 * リザーブ_8
	 */
	@Size(max = 255)
	@Column(name = "RESERVE_8")
	@Schema(description = "リザーブ_8", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String reserve8;

	/**
	 * リザーブ_9
	 */
	@Size(max = 255)
	@Column(name = "RESERVE_9")
	@Schema(description = "リザーブ_9", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String reserve9;

	/**
	 * リザーブ_10
	 */
	@Size(max = 255)
	@Column(name = "RESERVE_10")
	@Schema(description = "リザーブ_10", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String reserve10;

	/**
	 * リザーブ_11
	 */
	@Size(max = 255)
	@Column(name = "RESERVE_11")
	@Schema(description = "リザーブ_11", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String reserve11;

	/**
	 * リザーブ_12
	 */
	@Size(max = 255)
	@Column(name = "RESERVE_12")
	@Schema(description = "リザーブ_12", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String reserve12;
	/**
	 * リザーブ_13
	 */
	@Size(max = 255)
	@Column(name = "RESERVE_13")
	@Schema(description = "リザーブ_13", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String reserve13;

	/**
	 * リザーブ_14
	 */
	@Size(max = 255)
	@Column(name = "RESERVE_14")
	@Schema(description = "リザーブ_14", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String reserve14;

	/**
	 * リザーブ_15
	 */
	@Size(max = 255)
	@Column(name = "RESERVE_15")
	@Schema(description = "リザーブ_15", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String reserve15;

	/**
	 * リザーブ_16
	 */
	@Size(max = 255)
	@Column(name = "RESERVE_16")
	@Schema(description = "リザーブ_16", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String reserve16;

	/**
	 * リザーブ_17
	 */
	@Size(max = 255)
	@Column(name = "RESERVE_17")
	@Schema(description = "リザーブ_17", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String reserve17;

}
