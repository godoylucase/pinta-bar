package com.pintabar.persistence.dtomappers;

import com.pintabar.dtomappers.GenericDTOMapper;
import com.pintabar.dto.PurchaseOrderDTO;
import com.pintabar.entities.PurchaseOrder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nullable;
import java.util.Optional;

/**
 * Created by lucasgodoy on 14/06/17.
 */
@Component
public class PurchaseOrderDTOMapper implements GenericDTOMapper<PurchaseOrder, PurchaseOrderDTO> {

	private final UserDTOMapper userDTOMapper;
	private final TableUnitDTOMapper tableUnitDTOMapper;

	public PurchaseOrderDTOMapper(UserDTOMapper userDTOMapper, TableUnitDTOMapper tableUnitDTOMapper) {
		this.userDTOMapper = userDTOMapper;
		this.tableUnitDTOMapper = tableUnitDTOMapper;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Optional<PurchaseOrderDTO> mapToDTO(@Nullable PurchaseOrder purchaseOrder) {
		PurchaseOrderDTO purchaseOrderDTO = null;
		if (purchaseOrder != null) {
			purchaseOrderDTO = new PurchaseOrderDTO();
			if (purchaseOrder.getUser() != null) {
				purchaseOrderDTO.setUserUuid(purchaseOrder.getUser().getUuid());
			}
			if (purchaseOrder.getTableUnit() != null) {
				purchaseOrderDTO.setTableUuid(purchaseOrder.getTableUnit().getUuid());
			}
			purchaseOrderDTO.setStatus(purchaseOrder.getStatus());
			purchaseOrderDTO.setUuid(purchaseOrder.getUuid());
			purchaseOrderDTO.setCreatedOn(purchaseOrder.getCreatedOn());
			purchaseOrderDTO.setUpdatedOn(purchaseOrder.getUpdatedOn());
		}
		return Optional.ofNullable(purchaseOrderDTO);
	}
}
