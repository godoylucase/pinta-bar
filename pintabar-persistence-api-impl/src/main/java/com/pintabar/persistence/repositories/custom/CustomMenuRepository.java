package com.pintabar.persistence.repositories.custom;

import com.pintabar.entities.Menu;

import java.util.List;

/**
 * Created by lucasgodoy on 21/06/17.
 */
public interface CustomMenuRepository {

	List<Menu> findAllMenusByBusinessUuid(String businessUuid, Boolean isDeleted);

}
