package com.pintabar.persistence.dto;

import com.pintabar.dto.BaseDTO;
import com.pintabar.persistence.entities.MenuCategoryType;
import com.pintabar.persistence.interfaces.IMenuCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucasgodoy on 18/06/17.
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuCategoryDTO extends BaseDTO implements IMenuCategory {
	private boolean deleted = false;
	private String name;
	private MenuCategoryType type = MenuCategoryType.FOOD;
	private List<String> menuUuids = new ArrayList<>();
	private List<MenuItemDTO> items = new ArrayList<>();
}
