package com.pintabar.persistence.dtomappers;

import com.pintabar.persistence.dto.BaseDTO;
import com.pintabar.persistence.entities.base.BaseEntity;

/**
 * Created by lucasgodoy on 21/03/17.
 */
public interface GenericDTOMapper<E extends BaseEntity, DTO extends BaseDTO> {
	DTO mapToDTO(E entity);

}

