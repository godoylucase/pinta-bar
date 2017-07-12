package com.pintabar.dto;

import com.pintabar.interfaces.IMenuInstance;
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
public class MenuInstanceDTO extends BaseDTO implements IMenuInstance {
    private boolean available = false;
    private MenuDTO menu;
    private Set<MenuCategoryInstanceDTO> menuCategoryInstances = new HashSet<>();
}
