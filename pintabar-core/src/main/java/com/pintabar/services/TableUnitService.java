package com.pintabar.services;

import com.pintabar.persistence.dto.TableUnitDTO;

import java.util.Optional;

/**
 * Created by lucasgodoy on 13/06/17.
 */
public interface TableUnitService {

	Optional<TableUnitDTO> getTableUnit(String tableUuid);

}
