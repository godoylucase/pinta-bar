package com.pintabar.persistence.dto;

import com.pintabar.persistence.interfaces.IPurchaseOrderDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by lucasgodoy on 14/06/17.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@XmlRootElement
public class PurchasePurchaseOrderDetailDTO extends BaseDTO implements IPurchaseOrderDetail {
	private MenuItemDTO item;
	private String orderUuid;
}
