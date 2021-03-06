package com.pintabar.persistence.querydsl.predicates;

import com.pintabar.entities.user.QUser;
import com.querydsl.core.types.Predicate;

/**
 * Created by lucasgodoy on 4/06/17.
 */
public class UserPredicates {


	public static Predicate deletedUser(Boolean isDeleted) {
		if (isDeleted == null) {
			return null;
		} else if (isDeleted) {
			return QUser.user.deleted.isTrue();
		}
		return QUser.user.deleted.isFalse();
	}
}
