package com.pintabar.persistence.dtomappers;

import com.pintabar.dto.PurchaseOrderDetailDTO;
import com.pintabar.dtomappers.GenericDTOMapper;
import com.pintabar.entities.PurchaseOrderDetail;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Created by lucasgodoy on 15/06/17.
 */
@Component
public class PurchaseOrderDetailDTOMapper implements GenericDTOMapper<PurchaseOrderDetail, PurchaseOrderDetailDTO> {

	private final MenuItemInstanceDTOMapper menuItemInstanceDTOMapper;

	public PurchaseOrderDetailDTOMapper(MenuItemInstanceDTOMapper menuItemInstanceDTOMapper) {
		this.menuItemInstanceDTOMapper = menuItemInstanceDTOMapper;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Optional<PurchaseOrderDetailDTO> mapToDTO(PurchaseOrderDetail purchaseOrderDetail) {
		PurchaseOrderDetailDTO purchaseOrderDetailDTO = null;
		if (purchaseOrderDetail != null) {
			purchaseOrderDetailDTO = new PurchaseOrderDetailDTO();
			if (purchaseOrderDetail.getMenuItemInstance() != null) {
				purchaseOrderDetailDTO.setMenuItemInstance(
						menuItemInstanceDTOMapper.mapToDTO(purchaseOrderDetail.getMenuItemInstance()).orElse(null));
			}
			purchaseOrderDetailDTO.setQuantity(purchaseOrderDetail.getQuantity());
			if (purchaseOrderDetail.getPurchaseOrder() != null) {
				purchaseOrderDetailDTO.setPurchaseOrderUuid(purchaseOrderDetail.getPurchaseOrder().getUuid());
			}
			purchaseOrderDetailDTO.setId(purchaseOrderDetail.getId());
			purchaseOrderDetailDTO.setCreatedOn(purchaseOrderDetail.getCreatedOn());
			purchaseOrderDetailDTO.setUpdatedOn(purchaseOrderDetail.getUpdatedOn());
		}
		return Optional.ofNullable(purchaseOrderDetailDTO);
	}
}
