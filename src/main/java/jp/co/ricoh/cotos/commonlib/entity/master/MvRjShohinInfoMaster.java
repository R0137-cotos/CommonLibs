package jp.co.ricoh.cotos.commonlib.entity.master;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

/**
 * RJ商品マスタ
 */
@Entity
@Data
@Table(name = "mv_rj_shohin_info")
public class MvRjShohinInfoMaster {

	private String hanshaCd;

	@Id
	private String ricohHinshuCd;

	private String shohinNameLegacy;

	private String shohinKanryakuNm;

	private String kishuRyakugo;

	private String kanpinCd;

	private String janCd;

	private String venderShohinCd;

	private String hyojyunKakaku;

	private String newHyojyunKakaku;

	private String newHyojyunKakakuTekiyoYmd;

	private String oroshiKijunGaku;

	private String oroshiKijungakuShin;

	private String oroshiKijungakuKirikaeYmd;

	private String casepackKbn;

	private String caseShohinCd;

	private String packShohinCd;

	private String casepackHenkanMaeSuryo;

	private String casepackHenkanAtoSuryo;

	private String kibanKanriFlg;

	private String machineKbn;

	private String koukeiHinshuCd1;

	private String kanbaiFlg;

	private String kanbaiYmd;

	private String torokuYmd;

	private String saishuHenkoYmd;

	private String koshinKbn;

	private String mitorokuTaishoFlg;

	private String shoumiJuryo;

	private String yosekiJuryo;

	private String shohinName;

	private String ukebaraiTaishoFlg;

	private String genkaKanriKbn;

	private String torokushaCd;

	private String saishuHenkoshaCd;

	private String shohinInfoFlg;

	private String kariTorokuFlg;

	private String shogutiShohinFlg;

	private String shohinNaibuCd;

	private String rjShohinNaibuCd;

	private String cleansingShohinNm;

	private String kikanUriageKbn;

	private String hoshutukiKikan;

	private String hoshutukiKikanEndMaisu;

	private String irisu;

	private String dairiTorihikiFlg;

	private String serviceStartYmd;

	private String serviceEndYmd;

	private String ryokinKeisanKbn;

	private String hoshutsukiShohinKbn;

	private String shokaiSeikyuKbn;

	private String kaiyakuHenkinKbn;

	private String kaiyakuIyakukinKbn;

	private String ceIraiTaishoKbn;

	private String kazeiKbn;

	private String hyojyunKakakuTani;

	private String openKakakuKbn;

	private String koukeiHinshuCd2;

	private String koukeiHinshuCd3;

	private String hingunBunruiCd1;

	private String hoshuSikiriRtKbn;

	private String shohinKanaHankaku;

	private String rjDokujiComment;

	private String muhoshoTaishoKikan;

	private String hoshoKikanEndMaisu;

	private String venderSiteId;

	private String shohinType;

	private String shohinShikibetsu;

	private String tani;

	private String kobetsuZaikoSentakuFlg;
}