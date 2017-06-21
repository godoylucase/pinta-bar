package com.pintabar.persistence.dtomappers;

import com.pintabar.dtomappers.GenericDTOMapper;
import com.pintabar.persistence.dto.PurchasePurchaseOrderDetailDTO;
import com.pintabar.persistence.entities.PurchaseOrderDetail;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Created by lucasgodoy on 15/06/17.
 */
@Component
public class PurchaseOrderDetailDTOMapper implements GenericDTOMapper<PurchaseOrderDetail, PurchasePurchaseOrderDetailDTO> {

	private final MenuItemDTOMapper menuItemDTOMapper;

	public PurchaseOrderDetailDTOMapper(MenuItemDTOMapper menuItemDTOMapper) {
		this.menuItemDTOMapper = menuItemDTOMapper;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Optional<PurchasePurchaseOrderDetailDTO> mapToDTO(PurchaseOrderDetail purchaseOrderDetail) {
		PurchasePurchaseOrderDetailDTO purchaseOrderDetailDTO = null;
		if (purchaseOrderDetail != null) {
			purchaseOrderDetailDTO = new PurchasePurchaseOrderDetailDTO();
			if (purchaseOrderDetail.getItem() != null) {
				purchaseOrderDetailDTO.setItem(menuItemDTOMapper.mapToDTO(purchaseOrderDetail.getItem()).orElse(null));
			}
			if (purchaseOrderDetail.getPurchaseOrder() != null) {
				purchaseOrderDetailDTO.setOrderUuid(purchaseOrderDetail.getPurchaseOrder().getUuid());
			}
			purchaseOrderDetailDTO.setId(purchaseOrderDetail.getId());
			purchaseOrderDetailDTO.setCreatedOn(purchaseOrderDetail.getCreatedOn());
			purchaseOrderDetailDTO.setUpdatedOn(purchaseOrderDetail.getUpdatedOn());
		}
		return Optional.ofNullable(purchaseOrderDetailDTO);
	}
}
