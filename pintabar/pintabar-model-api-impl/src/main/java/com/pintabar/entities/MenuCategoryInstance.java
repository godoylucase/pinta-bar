package com.pintabar.entities;

import com.pintabar.entities.base.UUIDBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Lucas.Godoy on 7/07/17.
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MenuCategoryInstance extends UUIDBaseEntity {

    @Type(type = "yes_no")
    private boolean available = false;

    @ManyToOne
    @JoinColumn(name = "menu_instance_id")
    private MenuInstance menuInstance;

    @OneToOne(mappedBy = "menuCategoryInstance")
    private MenuCategory menuCategory;

    @OneToMany(mappedBy = "menuCategoryInstance")
    private Set<MenuItemInstance> menuItemInstances = new HashSet<>();
}
