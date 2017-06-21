package com.pintabar.persistence.dtomappers;

import com.pintabar.dtomappers.GenericDTOMapper;
import com.pintabar.entities.base.UUIDBaseEntity;
import com.pintabar.persistence.dto.MenuCategoryDTO;
import com.pintabar.persistence.entities.MenuCategory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by lucasgodoy on 18/06/17.
 */
@Component
public class MenuCategoryDTOMapper implements GenericDTOMapper<MenuCategory, MenuCategoryDTO> {

	private final MenuItemDTOMapper menuItemDTOMapper;

	public MenuCategoryDTOMapper(MenuItemDTOMapper menuItemDTOMapper) {
		this.menuItemDTOMapper = menuItemDTOMapper;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Optional<MenuCategoryDTO> mapToDTO(MenuCategory menuCategory) {
		MenuCategoryDTO menuCategoryDTO = null;
		if (menuCategory != null) {
			menuCategoryDTO = new MenuCategoryDTO();
			menuCategoryDTO.setDeleted(menuCategory.isDeleted());
			menuCategoryDTO.setName(menuCategory.getName());
			menuCategoryDTO.setType(menuCategory.getType());
			menuCategoryDTO.getMenuUuids().addAll(
					menuCategory.getMenues().stream()
							.map(UUIDBaseEntity::getUuid)
							.collect(Collectors.toList()));
			menuCategoryDTO.getItems().addAll(
					menuCategory.getItems()
							.stream()
							.map(item -> menuItemDTOMapper.mapToDTO(item).get())
							.collect(Collectors.toList()));
			menuCategoryDTO.setUuid(menuCategory.getUuid());
			menuCategoryDTO.setCreatedOn(menuCategory.getCreatedOn());
			menuCategoryDTO.setUpdatedOn(menuCategory.getUpdatedOn());
		}
		return Optional.ofNullable(menuCategoryDTO);
	}
}
