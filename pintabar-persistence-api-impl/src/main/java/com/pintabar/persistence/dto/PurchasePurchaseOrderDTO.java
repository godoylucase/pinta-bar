package com.pintabar.persistence.dto;

import com.pintabar.dto.BaseDTO;
import com.pintabar.persistence.entities.PurchaseOrderStatus;
import com.pintabar.persistence.interfaces.IPurchaseOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;
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
public class PurchasePurchaseOrderDTO extends BaseDTO implements IPurchaseOrder {
	private PurchaseOrderStatus status = com.pintabar.persistence.entities.PurchaseOrderStatus.OPENED;
	private String tableUuid;
	private String userUuid;
	private List<PurchasePurchaseOrderDetailDTO> orderDetail;
}
