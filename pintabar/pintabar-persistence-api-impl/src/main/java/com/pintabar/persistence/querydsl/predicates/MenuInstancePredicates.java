package com.pintabar.persistence.querydsl.predicates;

import com.pintabar.entities.QMenuInstance;
import com.querydsl.core.types.dsl.BooleanExpression;

/**
 * Created by lucasgodoy on 18/06/17.
 */
public class MenuInstancePredicates {

	public static BooleanExpression whereBusiness(String uuid) {
		return QMenuInstance.menuInstance.menu.business.uuid.eq(uuid);
	}

	public static BooleanExpression deletedMenuInstance(Boolean isDeleted) {
		if (isDeleted != null && !isDeleted) {
			return QMenuInstance.menuInstance.menu.deleted.isFalse();
		}
		return QMenuInstance.menuInstance.menu.deleted.isTrue();
	}
}
