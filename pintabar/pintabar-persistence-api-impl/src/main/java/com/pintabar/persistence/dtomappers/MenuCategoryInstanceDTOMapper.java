package com.pintabar.persistence.dtomappers;

import com.pintabar.dto.MenuCategoryInstanceDTO;
import com.pintabar.dtomappers.GenericDTOMapper;
import com.pintabar.entities.MenuCategoryInstance;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Lucas.Godoy on 12/07/17.
 */
@Component
public class MenuCategoryInstanceDTOMapper implements GenericDTOMapper<MenuCategoryInstance, MenuCategoryInstanceDTO> {

	private final MenuCategoryDTOMapper menuCategoryDTOMapper;
	private final MenuItemInstanceDTOMapper menuItemInstanceDTOMapper;

	public MenuCategoryInstanceDTOMapper(MenuCategoryDTOMapper menuCategoryDTOMapper, MenuItemInstanceDTOMapper menuItemInstanceDTOMapper) {
		this.menuCategoryDTOMapper = menuCategoryDTOMapper;
		this.menuItemInstanceDTOMapper = menuItemInstanceDTOMapper;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Optional<MenuCategoryInstanceDTO> mapToDTO(MenuCategoryInstance entity) {
		MenuCategoryInstanceDTO dto = null;
		if (entity != null) {
			dto = new MenuCategoryInstanceDTO();
			dto.setAvailable(entity.isAvailable());
			if (entity.getMenuInstance() != null) {
				dto.setMenuInstanceUuid(entity.getMenuInstance().getUuid());
			}
			dto.setMenuCategory(menuCategoryDTOMapper.mapToDTO(entity.getMenuCategory()).orElse(null));
			dto.getMenuItemInstances().addAll(
					entity.getMenuItemInstances()
							.stream()
							.map(mii -> menuItemInstanceDTOMapper.mapToDTO(mii).orElse(null))
							.collect(Collectors.toList())
			);
			dto.setId(entity.getId());
			dto.setUuid(entity.getUuid());
			dto.setCreatedOn(entity.getCreatedOn());
			dto.setUpdatedOn(entity.getUpdatedOn());
		}
		return Optional.ofNullable(dto);
	}
}
