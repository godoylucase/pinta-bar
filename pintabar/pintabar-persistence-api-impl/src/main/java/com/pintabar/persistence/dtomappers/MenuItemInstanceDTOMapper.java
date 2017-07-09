package com.pintabar.persistence.dtomappers;

import com.pintabar.dto.MenuItemInstanceDTO;
import com.pintabar.dtomappers.GenericDTOMapper;
import com.pintabar.entities.MenuItemInstance;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author Lucas.Godoy on 12/07/17.
 */
@Component
public class MenuItemInstanceDTOMapper implements GenericDTOMapper<MenuItemInstance, MenuItemInstanceDTO> {

	private final MenuItemDTOMapper menuItemDTOMapper;

	public MenuItemInstanceDTOMapper(MenuItemDTOMapper menuItemDTOMapper) {
		this.menuItemDTOMapper = menuItemDTOMapper;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Optional<MenuItemInstanceDTO> mapToDTO(MenuItemInstance entity) {
		MenuItemInstanceDTO dto = null;
		if (entity != null) {
			dto = new MenuItemInstanceDTO();
			dto.setAvailable(entity.isAvailable());
			if (entity.getMenuCategoryInstance() != null) {
				dto.setMenuCategoryInstanceUuid(entity.getMenuCategoryInstance().getUuid());
			}
			dto.setMenuItem(menuItemDTOMapper.mapToDTO(entity.getMenuItem()).orElse(null));
			dto.setPrice(entity.getPrice());
			dto.setId(entity.getId());
			dto.setUuid(entity.getUuid());
			dto.setCreatedOn(entity.getCreatedOn());
			dto.setUpdatedOn(entity.getUpdatedOn());
		}
		return Optional.ofNullable(dto);
	}
}
