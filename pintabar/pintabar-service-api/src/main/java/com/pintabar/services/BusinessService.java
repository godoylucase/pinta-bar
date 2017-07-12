package com.pintabar.services;


import com.pintabar.dto.MenuInstanceDTO;
import com.pintabar.dto.PurchaseOrderDTO;
import com.pintabar.entities.TableUnit;
import com.pintabar.entities.user.User;
import com.pintabar.exceptions.general.DataNotFoundException;
import com.pintabar.exceptions.purchaseorder.ClosedPurchaseOrderException;
import com.pintabar.exceptions.purchaseorder.InvalidPurchaseOrderException;
import com.pintabar.exceptions.user.UserWithOpenedOrderException;
import com.pintabar.ws.OrderingWS;

import java.util.List;
import java.util.Optional;

/**
 * Created by lucasgodoy on 14/06/17.
 */
public interface BusinessService {

	Optional<PurchaseOrderDTO> checkInUserToTable(String userUuid, String tableUnitUuid)
			throws DataNotFoundException, UserWithOpenedOrderException;

	Optional<PurchaseOrderDTO> checkInUserToTable(User user, TableUnit tableDTO)
			throws UserWithOpenedOrderException;

	List<MenuInstanceDTO> getMenuInstances(String businessUuid);

	List<MenuInstanceDTO> getMenuInstances(String businessUuid, Boolean isDeleted);

	PurchaseOrderDTO addItemsToPurchaseOrder(String purchaseOrderUuid, OrderingWS orderingWS)
			throws InvalidPurchaseOrderException, ClosedPurchaseOrderException, DataNotFoundException;
}
