package com.pintabar.dto;

import com.pintabar.interfaces.IMenuCategoryInstance;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Lucas.Godoy on 7/07/17.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class MenuCategoryInstanceDTO extends BaseDTO implements IMenuCategoryInstance {
    private boolean available = false;
    private String menuInstanceUuid;
    private MenuCategoryDTO menuCategory;
    private Set<MenuItemInstanceDTO> menuItemInstances = new HashSet<>();
}
