package com.pintabar.services;

import com.pintabar.dto.TableUnitDTO;
import com.pintabar.persistence.dtomappers.TableUnitDTOMapper;
import com.pintabar.entities.TableUnit;
import com.pintabar.persistence.repositories.TableUnitRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by lucasgodoy on 13/06/17.
 */
@Component
public class TableUnitServiceImpl implements TableUnitService {

	private final TableUnitRepository tableUnitRepository;
	private final TableUnitDTOMapper tableUnitDTOMapper;

	public TableUnitServiceImpl(TableUnitRepository tableUnitRepository, TableUnitDTOMapper tableUnitDTOMapper) {
		this.tableUnitRepository = tableUnitRepository;
		this.tableUnitDTOMapper = tableUnitDTOMapper;
	}

	@Override
	public Optional<TableUnitDTO> getTableUnit(String tableUuid) {
		Optional<TableUnit> table = tableUnitRepository.findByUuid(tableUuid);
		return tableUnitDTOMapper.mapToDTO(table.orElse(null));
	}
}
