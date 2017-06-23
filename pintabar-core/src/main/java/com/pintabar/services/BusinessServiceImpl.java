package com.pintabar.services;

import com.google.common.base.Preconditions;
import com.pintabar.exceptions.UserWithOpenedOrderException;
import com.pintabar.exceptions.purchaseorder.ClosedPurchaseOrderException;
import com.pintabar.exceptions.purchaseorder.InvalidPurchaseOrderException;
import com.pintabar.exceptions.purchaseorder.PurchaseOrderNotFoundException;
import com.pintabar.persistence.dto.MenuDTO;
import com.pintabar.persistence.dto.PurchaseOrderDTO;
import com.pintabar.persistence.dto.TableUnitDTO;
import com.pintabar.persistence.dto.UserDTO;
import com.pintabar.persistence.dtomappers.MenuDTOMapper;
import com.pintabar.persistence.dtomappers.PurchaseOrderDTOMapper;
import com.pintabar.persistence.entities.MenuItem;
import com.pintabar.persistence.entities.PurchaseOrder;
import com.pintabar.persistence.entities.PurchaseOrderDetail;
import com.pintabar.persistence.entities.PurchaseOrderStatus;
import com.pintabar.persistence.entities.TableUnit;
import com.pintabar.persistence.entities.user.User;
import com.pintabar.persistence.repositories.MenuItemRepository;
import com.pintabar.persistence.repositories.MenuRepository;
import com.pintabar.persistence.repositories.PurchaseOrderRepository;
import com.pintabar.persistence.repositories.TableUnitRepository;
import com.pintabar.persistence.repositories.UserRepository;
import com.pintabar.webservices.request.OrderingWS;
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
	private final MenuRepository menuRepository;
	private final MenuItemRepository menuItemRepository;
	private final PurchaseOrderDTOMapper purchaseOrderDTOMapper;
	private final MenuDTOMapper menuDTOMapper;

	public BusinessServiceImpl(UserRepository userRepository, TableUnitRepository tableUnitRepository,
	                           PurchaseOrderRepository purchaseOrderRepository, MenuRepository menuRepository,
	                           MenuItemRepository menuItemRepository, PurchaseOrderDTOMapper purchaseOrderDTOMapper, MenuDTOMapper menuDTOMapper) {
		this.userRepository = userRepository;
		this.tableUnitRepository = tableUnitRepository;
		this.purchaseOrderRepository = purchaseOrderRepository;
		this.menuRepository = menuRepository;
		this.menuItemRepository = menuItemRepository;
		this.purchaseOrderDTOMapper = purchaseOrderDTOMapper;
		this.menuDTOMapper = menuDTOMapper;
	}

	@Override
	@Transactional
	public Optional<PurchaseOrderDTO> checkInUserToTable(UserDTO userDTO, TableUnitDTO tableDTO)
			throws UserWithOpenedOrderException {
		Optional<User> userOp = userRepository.findByUuid(userDTO.getUuid());
		Optional<TableUnit> tableOp = tableUnitRepository.findByUuid(tableDTO.getUuid());
		// check exception, this is just prototyping
		User user = userOp.orElseThrow(IllegalArgumentException::new);
		TableUnit tableUnit = tableOp.orElseThrow(IllegalArgumentException::new);

		List<PurchaseOrder> purchaseOrders =
				purchaseOrderRepository.findPurchaseOrdersByUserIdAndStatus(user.getId(), PurchaseOrderStatus.OPEN_STATUSES);

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

	@Override
	@Transactional
	public PurchaseOrderDTO addItemsToPurchaseOrder(String purchaseOrderUuid, OrderingWS orderingWS)
			throws PurchaseOrderNotFoundException, InvalidPurchaseOrderException, ClosedPurchaseOrderException {
		PurchaseOrder purchaseOrder = purchaseOrderRepository.findByUuid(purchaseOrderUuid)
				.orElseThrow(PurchaseOrderNotFoundException::new);

		// just prototyping move this to a very more complex validation
		if (!purchaseOrder.getUser().getUuid().equals(orderingWS.getUserUuid())
				|| !purchaseOrder.getTableUnit().getBusiness().getUuid().equals(orderingWS.getBusinessUuid())) {
			throw new InvalidPurchaseOrderException();
		} else if (PurchaseOrderStatus.CLOSED_STATUSES.contains(purchaseOrder.getStatus())) {
			throw new ClosedPurchaseOrderException();
		}

		// item detail rebuild from external resource and added into purchase order
		purchaseOrder.getDetails()
				.addAll(processPurchaseOrderDetailsFromMap(orderingWS.getPurchaseOrderLinesMap(), purchaseOrder));

		return purchaseOrderDTOMapper.mapToDTO(purchaseOrder).orElse(null);
	}

	private List<PurchaseOrderDetail> processPurchaseOrderDetailsFromMap(Map<String, BigDecimal> purchaseOrderLinesMap,
	                                                                     PurchaseOrder purchaseOrder) {
		return purchaseOrderLinesMap.entrySet().stream()
				.map(entry -> {
					MenuItem menuItem = menuItemRepository.findByUuid(entry.getKey())
							.orElseThrow(IllegalArgumentException::new);
					if (!validateMenuItem(menuItem)) {
						throw new IllegalArgumentException();
					}

					PurchaseOrderDetail purchaseOrderDetail =
							new PurchaseOrderDetail(entry.getValue(), menuItem, purchaseOrder);
					return purchaseOrderDetail;
				}).collect(Collectors.toList());
	}

	private boolean validateMenuItem(MenuItem menuItem) {
		return menuItem.getCategories().parallelStream()
				.anyMatch(menuCategory -> !menuCategory.isDeleted()
						&& menuCategory.getMenues().parallelStream()
						.anyMatch(menu -> !menu.isDeleted()));
	}

	private PurchaseOrder createOrder(User user, TableUnit tableUnit) {
		PurchaseOrder purchaseOrder = new PurchaseOrder(user, tableUnit);
		return purchaseOrderRepository.save(purchaseOrder);
	}
}
