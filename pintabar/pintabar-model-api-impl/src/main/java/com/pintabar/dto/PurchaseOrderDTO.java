package com.pintabar.dto;

import com.pintabar.entities.PurchaseOrderStatus;
import com.pintabar.interfaces.IPurchaseOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucasgodoy on 14/06/17.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@XmlRootElement
public class PurchaseOrderDTO extends BaseDTO implements IPurchaseOrder {
	private PurchaseOrderStatus status = com.pintabar.entities.PurchaseOrderStatus.OPENED;
	private String tableUuid;
	private String userUuid;
	private List<PurchaseOrderDetailDTO> purchaseOrderDetails = new ArrayList<>();
}
