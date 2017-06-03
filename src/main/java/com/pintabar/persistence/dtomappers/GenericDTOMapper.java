package com.pintabar.persistence.dtomappers;

import com.pintabar.persistence.dto.BaseDTO;
import com.pintabar.persistence.entities.base.BaseEntity;

import java.util.Optional;

/**
 * Created by lucasgodoy on 21/03/17.
 */
public interface GenericDTOMapper<E extends BaseEntity, DTO extends BaseDTO> {
	Optional<DTO> mapToDTO(E entity);

}

