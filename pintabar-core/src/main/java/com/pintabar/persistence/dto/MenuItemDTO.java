package com.pintabar.persistence.dto;

import com.pintabar.persistence.interfaces.IMenuItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucasgodoy on 15/06/17.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@XmlRootElement
public class MenuItemDTO extends BaseDTO implements IMenuItem {
	public List<String> categoriesUuid = new ArrayList<>();
	private String name;
	private String description;
	private boolean deleted = false;
	private boolean available = true;
	private BigDecimal price = BigDecimal.ZERO;
}
