package com.pintabar.dto;

import com.pintabar.entities.MenuCategoryType;
import com.pintabar.interfaces.IMenuCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by lucasgodoy on 18/06/17.
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class MenuCategoryDTO extends BaseDTO implements IMenuCategory {
	private boolean deleted = false;
	private String name;
	private String description;
	private MenuCategoryType type = MenuCategoryType.FOOD;
	private String businessUuid;
	private String menuCategoryInstanceUuid;
}
