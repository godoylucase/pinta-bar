package com.pintabar.persistence.repositories.custom;

import com.pintabar.entities.Menu;
import com.pintabar.entities.MenuInstance;

import java.util.List;

/**
 * Created by lucasgodoy on 21/06/17.
 */
public interface CustomMenuInstanceRepository {

	List<MenuInstance> findAllMenuInstancesByBusinessUuid(String businessUuid, Boolean isDeleted);

}
