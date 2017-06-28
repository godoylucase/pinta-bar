package com.pintabar.persistence.querydsl.predicates;

import com.pintabar.entities.QMenu;
import com.querydsl.core.types.dsl.BooleanExpression;

/**
 * Created by lucasgodoy on 18/06/17.
 */
public class MenuPredicates {

	public static BooleanExpression whereBusiness(String uuid) {
		return QMenu.menu.business.uuid.eq(uuid);
	}

	public static BooleanExpression deletedMenu(Boolean isDeleted) {
		if (isDeleted != null && !isDeleted) {
			return QMenu.menu.deleted.isFalse();
		}
		return QMenu.menu.deleted.isTrue();
	}
}
