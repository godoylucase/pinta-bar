package com.pintabar.persistence.dtomappers;

import com.pintabar.dtomappers.GenericDTOMapper;
import com.pintabar.persistence.dto.MenuItemDTO;
import com.pintabar.persistence.entities.MenuItem;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Created by lucasgodoy on 15/06/17.
 */
@Component
public class MenuItemDTOMapper implements GenericDTOMapper<MenuItem, MenuItemDTO> {

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Optional<MenuItemDTO> mapToDTO(MenuItem menuItem) {
		MenuItemDTO menuItemDTO = null;
		if (menuItem != null) {
			menuItemDTO = new MenuItemDTO();
			menuItemDTO.setName(menuItem.getName());
			//menuItemDTO.setDescription(menuItem.getDescription());
			menuItemDTO.setDeleted(menuItem.isDeleted());
			menuItemDTO.setAvailable(menuItem.isAvailable());
			menuItemDTO.setPrice(menuItem.getPrice());
			menuItemDTO.setUuid(menuItem.getUuid());
			menuItemDTO.setCreatedOn(menuItem.getCreatedOn());
			menuItemDTO.setUpdatedOn(menuItem.getUpdatedOn());
		}
		return Optional.ofNullable(menuItemDTO);
	}
}
