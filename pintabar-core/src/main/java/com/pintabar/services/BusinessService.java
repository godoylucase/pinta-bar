package com.pintabar.services;

import com.pintabar.persistence.dto.PurchasePurchaseOrderDTO;
import com.pintabar.persistence.dto.TableUnitDTO;
import com.pintabar.persistence.dto.UserDTO;

import java.util.Optional;

/**
 * Created by lucasgodoy on 14/06/17.
 */
public interface BusinessService {

	Optional<PurchasePurchaseOrderDTO> checkInUserToTable(UserDTO userDTO, TableUnitDTO tableDTO);

}