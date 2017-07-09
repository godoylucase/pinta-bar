package com.pintabar.persistence.dtomappers;

import com.pintabar.dto.MenuDTO;
import com.pintabar.dtomappers.GenericDTOMapper;
import com.pintabar.entities.Menu;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Created by lucasgodoy on 18/06/17.
 */
@Component
public class MenuDTOMapper implements GenericDTOMapper<Menu, MenuDTO> {

	private final MenuCategoryDTOMapper menuCategoryDTOMapper;

	public MenuDTOMapper(MenuCategoryDTOMapper menuCategoryDTOMapper) {
		this.menuCategoryDTOMapper = menuCategoryDTOMapper;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Optional<MenuDTO> mapToDTO(Menu entity) {
		MenuDTO dto = null;
		if (entity != null) {
			dto = new MenuDTO();
			dto.setName(entity.getName());
			dto.setDeleted(entity.isDeleted());
			if (entity.getBusiness() != null) {
				dto.setBusinessUuid(entity.getBusiness().getUuid());
			}
			if (entity.getMenuInstance() != null) {
				dto.setMenuInstanceUuid(entity.getMenuInstance().getUuid());
			}
			dto.setUuid(entity.getUuid());
			dto.setCreatedOn(entity.getCreatedOn());
			dto.setUpdatedOn(entity.getUpdatedOn());
		}
		return Optional.ofNullable(dto);
	}
}
