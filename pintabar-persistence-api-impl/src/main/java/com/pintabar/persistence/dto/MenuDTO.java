package com.pintabar.persistence.dto;

import com.pintabar.dto.BaseDTO;
import com.pintabar.persistence.interfaces.IMenu;
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
public class MenuDTO extends BaseDTO implements IMenu {
	private String name;
	private boolean deleted = false;
	private String businessUuid;
	private List<MenuCategoryDTO> categories = new ArrayList<>();
}
