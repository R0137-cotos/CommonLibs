package jp.co.ricoh.cotos.commonlib.entity.master;

import javax.persistence.Entity;
import javax.persistence.Table;

import jp.co.ricoh.cotos.commonlib.entity.common.VMailAddressListAbstractEntity;

@Entity
@Table(name = "v_mail_address_arrangement_list")
public class VMailAddressArrangementList extends VMailAddressListAbstractEntity {
}
