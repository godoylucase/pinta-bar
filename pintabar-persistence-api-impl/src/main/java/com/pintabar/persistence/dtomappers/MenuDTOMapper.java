package com.pintabar.persistence.dtomappers;

import com.pintabar.dtomappers.GenericDTOMapper;
import com.pintabar.dto.MenuDTO;
import com.pintabar.entities.Menu;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;

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
	public Optional<MenuDTO> mapToDTO(Menu menu) {
		MenuDTO menuDTO = null;
		if (menu != null) {
			menuDTO = new MenuDTO();
			menuDTO.setName(menu.getName());
			menuDTO.setDeleted(menu.isDeleted());
			if (menu.getBusiness() != null) {
				menuDTO.setBusinessUuid(menu.getBusiness().getUuid());
			}
			if (menu.getCategories() != null) {
				menuDTO.getCategories().addAll(
						menu.getCategories().stream()
								.map(category -> menuCategoryDTOMapper.mapToDTO(category).orElse(null))
								.collect(Collectors.toList()));
			}
			menuDTO.setUuid(menu.getUuid());
			menuDTO.setCreatedOn(menu.getCreatedOn());
			menuDTO.setUpdatedOn(menu.getUpdatedOn());
		}
		return Optional.ofNullable(menuDTO);
	}
}
