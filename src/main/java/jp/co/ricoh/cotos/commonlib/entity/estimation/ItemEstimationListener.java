package jp.co.ricoh.cotos.commonlib.entity.estimation;

import jakarta.persistence.PrePersist;
import jakarta.transaction.Transactional;

import org.apache.axis.utils.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.ricoh.cotos.commonlib.entity.master.ItemMaster;
import jp.co.ricoh.cotos.commonlib.repository.master.ItemMasterRepository;

@Component
public class ItemEstimationListener {

	private static ItemMasterRepository itemMasterRepository;

	@Autowired
	public void setItemMasterRepository(ItemMasterRepository itemMasterRepository) {
		ItemEstimationListener.itemMasterRepository = itemMasterRepository;
	}

	/**
	 * 品種マスタ情報を品種（見積用）トランザクションに紐づけます。
	 *
	 * @param itemEstimation
	 */
	@PrePersist
	@Transactional
	public void appendsEstimationItemFields(ItemEstimation itemEstimation) {
		ItemMaster itemMaster = itemMasterRepository.findByProductMasterIdAndRicohItemCode(itemEstimation.getProductMasterId(), itemEstimation.getRicohItemCode());
		itemEstimation.setItemMasterId(itemMaster.getId());
		// 価格等の他システムにより連携される項目は品種マスタのコピー対象外
		BeanUtils.copyProperties(itemMaster, itemEstimation, "id", "updatedAt", "updatedUserId", "createdAt", "createdUserId", "version", "itemName", "RCost", "rjPurchasePrice", "rjDividingPrice", "motherStorePrice", "standardPrice", "partitionPrice", "priceRevisionDateMasterId");
		if (StringUtils.isEmpty(itemEstimation.getItemEstimationName())) {
			itemEstimation.setItemEstimationName(itemMaster.getItemName());
		}
		if (!"O365".equals(itemMaster.getProductMaster().getProductClassDiv())) {
			// O365は契約変更時にマスタ価格ではなく変更元の価格を事前に設定しているためコピー対象外
			itemEstimation.setStandardPrice(itemMaster.getStandardPrice());
			itemEstimation.setPartitionPrice(itemMaster.getPartitionPrice());
			itemEstimation.setPriceRevisionDateMasterId(itemMaster.getPriceRevisionDateMasterId());
		}
	}

}
