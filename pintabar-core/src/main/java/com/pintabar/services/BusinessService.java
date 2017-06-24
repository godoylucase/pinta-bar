package com.pintabar.services;

import com.pintabar.exceptions.purchaseorder.InvalidPurchaseOrderException;
import com.pintabar.exceptions.purchaseorder.ClosedPurchaseOrderException;
import com.pintabar.exceptions.purchaseorder.PurchaseOrderNotFoundException;
import com.pintabar.exceptions.UserWithOpenedOrderException;
import com.pintabar.persistence.dto.MenuDTO;
import com.pintabar.persistence.dto.PurchaseOrderDTO;
import com.pintabar.persistence.dto.TableUnitDTO;
import com.pintabar.persistence.dto.UserDTO;
import com.pintabar.webservices.request.OrderingWS;

import java.util.List;
import java.util.Optional;

/**
 * Created by lucasgodoy on 14/06/17.
 */
public interface BusinessService {

	Optional<PurchaseOrderDTO> checkInUserToTable(UserDTO userDTO, TableUnitDTO tableDTO) throws UserWithOpenedOrderException;

	List<MenuDTO> getMenues(String businessUuid);

	List<MenuDTO> getMenues(String businessUuid, Boolean isDeleted);

	PurchaseOrderDTO addItemsToPurchaseOrder(String purchaseOrderUuid, OrderingWS orderingWS) throws PurchaseOrderNotFoundException, InvalidPurchaseOrderException, ClosedPurchaseOrderException;
}
