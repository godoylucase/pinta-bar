package com.pintabar.services;

import com.google.common.base.Preconditions;
import com.pintabar.exceptions.UserWithOpenedOrderException;
import com.pintabar.persistence.dto.MenuDTO;
import com.pintabar.persistence.dto.PurchasePurchaseOrderDTO;
import com.pintabar.persistence.dto.TableUnitDTO;
import com.pintabar.persistence.dto.UserDTO;
import com.pintabar.persistence.dtomappers.MenuDTOMapper;
import com.pintabar.persistence.dtomappers.PurchaseOrderDTOMapper;
import com.pintabar.persistence.entities.PurchaseOrder;
import com.pintabar.persistence.entities.PurchaseOrderStatus;
import com.pintabar.persistence.entities.TableUnit;
import com.pintabar.persistence.entities.user.User;
import com.pintabar.persistence.repositories.MenuRepository;
import com.pintabar.persistence.repositories.PurchaseOrderRepository;
import com.pintabar.persistence.repositories.TableUnitRepository;
import com.pintabar.persistence.repositories.UserRepository;
import com.pintabar.webservices.apis.exception.AppException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by lucasgodoy on 14/06/17.
 */
@Component
public class BusinessServiceImpl implements BusinessService {

	private final UserRepository userRepository;
	private final TableUnitRepository tableUnitRepository;
	private final PurchaseOrderRepository purchaseOrderRepository;
	private final MenuRepository menuRepository;
	private final PurchaseOrderDTOMapper purchaseOrderDTOMapper;
	private final MenuDTOMapper menuDTOMapper;

	public BusinessServiceImpl(UserRepository userRepository, TableUnitRepository tableUnitRepository,
	                           PurchaseOrderRepository purchaseOrderRepository, MenuRepository menuRepository,
	                           PurchaseOrderDTOMapper purchaseOrderDTOMapper, MenuDTOMapper menuDTOMapper) {
		this.userRepository = userRepository;
		this.tableUnitRepository = tableUnitRepository;
		this.purchaseOrderRepository = purchaseOrderRepository;
		this.menuRepository = menuRepository;
		this.purchaseOrderDTOMapper = purchaseOrderDTOMapper;
		this.menuDTOMapper = menuDTOMapper;
	}

	@Override
	@Transactional
	public Optional<PurchasePurchaseOrderDTO> checkInUserToTable(UserDTO userDTO, TableUnitDTO tableDTO)
			throws UserWithOpenedOrderException {
		Optional<User> userOp = userRepository.findByUuid(userDTO.getUuid());
		Optional<TableUnit> tableOp = tableUnitRepository.findByUuid(tableDTO.getUuid());
		// check exception, this is just prototyping
		User user = userOp.orElseThrow(IllegalArgumentException::new);
		TableUnit tableUnit = tableOp.orElseThrow(IllegalArgumentException::new);

		List<PurchaseOrder> purchaseOrders =
				purchaseOrderRepository.findPurchaseOrdersByUserIdAndStatus(user.getId(), PurchaseOrderStatus.OPEN);

		if (!purchaseOrders.isEmpty()) {
			throw new UserWithOpenedOrderException();
		}

		PurchaseOrder purchaseOrder = createOrder(user, tableUnit);

		return purchaseOrderDTOMapper.mapToDTO(purchaseOrder);
	}

	@Override
	@Transactional
	public List<MenuDTO> getMenues(String businessUuid) {
		return getMenues(businessUuid, null);
	}

	@Override
	@Transactional
	public List<MenuDTO> getMenues(String businessUuid, Boolean isDeleted) {
		Preconditions.checkNotNull(businessUuid);
		return menuRepository.findAllMenusByBusinessUuid(businessUuid, isDeleted)
				.stream()
				.map(menu -> menuDTOMapper.mapToDTO(menu).get())
				.collect(Collectors.toList());
	}

	private PurchaseOrder createOrder(User user, TableUnit tableUnit) {
		PurchaseOrder purchaseOrder = new PurchaseOrder(user, tableUnit);
		return purchaseOrderRepository.save(purchaseOrder);
	}
}
