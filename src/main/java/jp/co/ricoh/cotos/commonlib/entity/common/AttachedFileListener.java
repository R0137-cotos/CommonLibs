package jp.co.ricoh.cotos.commonlib.entity.common;

import jakarta.persistence.PrePersist;

import org.springframework.stereotype.Component;

import jp.co.ricoh.cotos.commonlib.entity.EnumType.EimLinkedStatus;
import jp.co.ricoh.cotos.commonlib.entity.common.AttachedFile;

@Component
public class AttachedFileListener {

	@PrePersist
	public void prePersist(AttachedFile attachedFile) {
		this.setEimLinkedStatus(attachedFile);
	}

	private void setEimLinkedStatus(AttachedFile attachedFile) {
		/**
		 * EIM連携済状態
		 */
		if (null == attachedFile.getEimLinkedStatus()) {
			attachedFile.setEimLinkedStatus(EimLinkedStatus.未連携);
		}
	}
}
