package com.pintabar.services;

import com.pintabar.persistence.dto.PurchasePurchaseOrderDTO;
import com.pintabar.persistence.dto.TableUnitDTO;
import com.pintabar.persistence.dto.UserDTO;
import com.pintabar.persistence.dtomappers.PurchaseOrderDTOMapper;
import com.pintabar.persistence.entities.PurchaseOrder;
import com.pintabar.persistence.entities.TableUnit;
import com.pintabar.persistence.entities.user.User;
import com.pintabar.persistence.repositories.PurchaseOrderRepository;
import com.pintabar.persistence.repositories.TableUnitRepository;
import com.pintabar.persistence.repositories.UserRepository;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * Created by lucasgodoy on 14/06/17.
 */
@Component
public class BusinessServiceImpl implements BusinessService {

	private final UserRepository userRepository;
	private final TableUnitRepository tableUnitRepository;
	private final PurchaseOrderRepository purchaseOrderRepository;
	private final PurchaseOrderDTOMapper purchaseOrderDTOMapper;

	public BusinessServiceImpl(UserRepository userRepository, TableUnitRepository tableUnitRepository,
	                           PurchaseOrderRepository purchaseOrderRepository, PurchaseOrderDTOMapper purchaseOrderDTOMapper) {
		this.userRepository = userRepository;
		this.tableUnitRepository = tableUnitRepository;
		this.purchaseOrderRepository = purchaseOrderRepository;
		this.purchaseOrderDTOMapper = purchaseOrderDTOMapper;
	}

	@Override
	@Transactional
	public Optional<PurchasePurchaseOrderDTO> checkInUserToTable(UserDTO userDTO, TableUnitDTO tableDTO) {
		Optional<User> userOp = userRepository.findByUuid(userDTO.getUuid());
		Optional<TableUnit> tableOp = tableUnitRepository.findByUuid(tableDTO.getUuid());
		// check exception, this is just prototyping
		User user = userOp.orElseThrow(IllegalArgumentException::new);
		TableUnit tableUnit = tableOp.orElseThrow(IllegalArgumentException::new);

		PurchaseOrder purchaseOrder = createOrder(user, tableUnit);

		return purchaseOrderDTOMapper.mapToDTO(purchaseOrder);
	}

	private PurchaseOrder createOrder(User user, TableUnit tableUnit) {
		PurchaseOrder purchaseOrder = new PurchaseOrder(user, tableUnit);
		return purchaseOrderRepository.save(purchaseOrder);
	}
}
