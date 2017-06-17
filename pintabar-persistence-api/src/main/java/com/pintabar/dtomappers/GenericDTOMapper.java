package com.pintabar.dtomappers;

import com.pintabar.dto.BaseDTO;
import com.pintabar.entities.base.BaseEntity;

import java.util.Optional;

/**
 * Created by lucasgodoy on 21/03/17.
 */
@FunctionalInterface
public interface GenericDTOMapper<E extends BaseEntity, DTO extends BaseDTO> {
	Optional<DTO> mapToDTO(E entity);

}

