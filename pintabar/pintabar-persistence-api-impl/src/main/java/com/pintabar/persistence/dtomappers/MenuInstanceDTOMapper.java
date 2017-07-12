package com.pintabar.persistence.dtomappers;

import com.pintabar.dto.MenuInstanceDTO;
import com.pintabar.dtomappers.GenericDTOMapper;
import com.pintabar.entities.MenuInstance;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Lucas.Godoy on 12/07/17.
 */
@Component
public class MenuInstanceDTOMapper implements GenericDTOMapper<MenuInstance, MenuInstanceDTO> {

	private final MenuDTOMapper menuDTOMapper;
	private final MenuCategoryInstanceDTOMapper menuCategoryInstanceDTOMapper;

	public MenuInstanceDTOMapper(MenuDTOMapper menuDTOMapper, MenuCategoryInstanceDTOMapper menuCategoryInstanceDTOMapper) {
		this.menuDTOMapper = menuDTOMapper;
		this.menuCategoryInstanceDTOMapper = menuCategoryInstanceDTOMapper;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Optional<MenuInstanceDTO> mapToDTO(MenuInstance entity) {
		MenuInstanceDTO dto = null;
		if (entity != null) {
			dto = new MenuInstanceDTO();
			dto.setAvailable(entity.isAvailable());
			dto.setMenu(menuDTOMapper.mapToDTO(entity.getMenu()).orElse(null));
			dto.getMenuCategoryInstances().addAll(
					entity.getMenuCategoryInstances()
							.stream()
							.map(mci -> menuCategoryInstanceDTOMapper.mapToDTO(mci).orElse(null))
							.collect(Collectors.toSet()));
			dto.setId(entity.getId());
			dto.setUuid(entity.getUuid());
			dto.setCreatedOn(entity.getCreatedOn());
			dto.setUpdatedOn(entity.getUpdatedOn());
		}
		return Optional.ofNullable(dto);
	}
}
