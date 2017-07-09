package com.pintabar.services;

import com.google.common.base.Preconditions;
import com.pintabar.dto.MenuInstanceDTO;
import com.pintabar.dto.PurchaseOrderDTO;
import com.pintabar.entities.MenuItemInstance;
import com.pintabar.entities.PurchaseOrder;
import com.pintabar.entities.PurchaseOrderDetail;
import com.pintabar.entities.PurchaseOrderStatus;
import com.pintabar.entities.TableUnit;
import com.pintabar.entities.user.User;
import com.pintabar.exceptions.ErrorCode;
import com.pintabar.exceptions.general.DataNotFoundException;
import com.pintabar.exceptions.purchaseorder.ClosedPurchaseOrderException;
import com.pintabar.exceptions.purchaseorder.InvalidPurchaseOrderException;
import com.pintabar.exceptions.user.UserWithOpenedOrderException;
import com.pintabar.persistence.dtomappers.MenuInstanceDTOMapper;
import com.pintabar.persistence.dtomappers.PurchaseOrderDTOMapper;
import com.pintabar.persistence.repositories.MenuInstanceRepository;
import com.pintabar.persistence.repositories.MenuItemInstanceRepository;
import com.pintabar.persistence.repositories.PurchaseOrderRepository;
import com.pintabar.persistence.repositories.TableUnitRepository;
import com.pintabar.persistence.repositories.UserRepository;
import com.pintabar.ws.OrderingWS;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
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
	private final MenuInstanceRepository menuInstanceRepository;
	private final MenuItemInstanceRepository menuItemInstanceRepository;
	private final PurchaseOrderDTOMapper purchaseOrderDTOMapper;
	private final MenuInstanceDTOMapper menuInstanceDTOMapper;

	public BusinessServiceImpl(UserRepository userRepository, TableUnitRepository tableUnitRepository,
							   PurchaseOrderRepository purchaseOrderRepository, MenuInstanceRepository menuInstanceRepository,
							   MenuItemInstanceRepository menuItemInstanceRepository, PurchaseOrderDTOMapper purchaseOrderDTOMapper,
							   MenuInstanceDTOMapper menuInstanceDTOMapper) {
		this.userRepository = userRepository;
		this.tableUnitRepository = tableUnitRepository;
		this.purchaseOrderRepository = purchaseOrderRepository;
		this.menuInstanceRepository = menuInstanceRepository;
		this.menuItemInstanceRepository = menuItemInstanceRepository;
		this.purchaseOrderDTOMapper = purchaseOrderDTOMapper;
		this.menuInstanceDTOMapper = menuInstanceDTOMapper;
	}

	@Override
	@Transactional
	public Optional<PurchaseOrderDTO> checkInUserToTable(String userUuid, String tableUnitUuid)
			throws DataNotFoundException, UserWithOpenedOrderException {
		User user = userRepository.findByUuid(userUuid)
				.orElseThrow(() -> new DataNotFoundException(ErrorCode.USER_NOT_FOUND));
		TableUnit tableUnit = tableUnitRepository.findByUuid(tableUnitUuid)
				.orElseThrow(() -> new DataNotFoundException(ErrorCode.TABLE_UNIT_NOT_FOUND));
		return checkInUserToTable(user, tableUnit);
	}

	@Override
	@Transactional
	public Optional<PurchaseOrderDTO> checkInUserToTable(User user, TableUnit tableUnit)
			throws UserWithOpenedOrderException {
		List<PurchaseOrder> purchaseOrders =
				purchaseOrderRepository.findPurchaseOrdersByUserIdAndStatus(user.getId(), PurchaseOrderStatus.OPEN_STATUSES);
		if (!purchaseOrders.isEmpty()) {
			throw new UserWithOpenedOrderException(ErrorCode.USER_ALREADY_HAS_OPENED_ORDERS);
		}
		PurchaseOrder purchaseOrder = createOrder(user, tableUnit);
		return purchaseOrderDTOMapper.mapToDTO(purchaseOrder);
	}

	@Override
	@Transactional
	public List<MenuInstanceDTO> getMenuInstances(String businessUuid) {
		return getMenuInstances(businessUuid, null);
	}

	@Override
	@Transactional
	public List<MenuInstanceDTO> getMenuInstances(String businessUuid, Boolean isDeleted) {
		Preconditions.checkNotNull(businessUuid);
		return menuInstanceRepository.findAllMenuInstancesByBusinessUuid(businessUuid, isDeleted)
				.stream()
				.map(menuInstance -> menuInstanceDTOMapper.mapToDTO(menuInstance).orElse(null))
				.collect(Collectors.toList());
	}

	@Override
	@Transactional
	public PurchaseOrderDTO addItemsToPurchaseOrder(String purchaseOrderUuid, OrderingWS orderingWS)
			throws InvalidPurchaseOrderException, ClosedPurchaseOrderException, DataNotFoundException {
		PurchaseOrder purchaseOrder = purchaseOrderRepository.findByUuid(purchaseOrderUuid)
				.orElseThrow(() -> new DataNotFoundException(ErrorCode.PURCHASE_ORDER_NOT_FOUND));

		// just prototyping move this to a very more complex validation
		if (!purchaseOrder.getUser().getUuid().equals(orderingWS.getUserUuid())
				|| !purchaseOrder.getTableUnit().getBusiness().getUuid().equals(orderingWS.getBusinessUuid())) {
			throw new InvalidPurchaseOrderException(ErrorCode.PURCHASE_ORDER_INVALID_OWNER);
		} else if (PurchaseOrderStatus.CLOSED_STATUSES.contains(purchaseOrder.getStatus())) {
			throw new ClosedPurchaseOrderException(ErrorCode.PURCHASE_ORDER_ALREADY_CLOSED);
		}

		// item detail rebuild from external resource and added into purchase order
		purchaseOrder.getDetails()
				.addAll(processPurchaseOrderDetailsFromMap(orderingWS.getPurchaseOrderLinesMap(), purchaseOrder));

		return purchaseOrderDTOMapper.mapToDTO(purchaseOrder).orElse(null);
	}

	private List<PurchaseOrderDetail> processPurchaseOrderDetailsFromMap(Map<String, BigDecimal> purchaseOrderLinesMap,
																		 PurchaseOrder purchaseOrder) throws DataNotFoundException {
		try {
			return purchaseOrderLinesMap.entrySet().stream()
					.map(entry -> {
						MenuItemInstance menuItemInstance = menuItemInstanceRepository.findByUuid(entry.getKey())
								.orElseThrow(IllegalArgumentException::new);
						if (!menuItemInstance.isFullAvailable()) {
							throw new IllegalArgumentException();
						}
						return new PurchaseOrderDetail(entry.getValue(), menuItemInstance, purchaseOrder);
					}).collect(Collectors.toList());
		} catch (IllegalArgumentException ex) {
			// temporal work around before implementing custom Function for above lambda expression
			throw new DataNotFoundException(ErrorCode.MENU_ITEM_NOT_FOUND);
		}
	}

	private PurchaseOrder createOrder(User user, TableUnit tableUnit) {
		PurchaseOrder purchaseOrder = new PurchaseOrder(user, tableUnit);
		return purchaseOrderRepository.save(purchaseOrder);
	}
}
