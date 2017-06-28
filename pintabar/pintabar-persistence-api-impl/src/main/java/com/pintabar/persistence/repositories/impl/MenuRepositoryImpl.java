package com.pintabar.persistence.repositories.impl;

import com.pintabar.entities.Menu;
import com.pintabar.entities.QMenu;
import com.pintabar.persistence.querydsl.predicates.MenuPredicates;
import com.pintabar.persistence.repositories.custom.CustomMenuRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by lucasgodoy on 21/06/17.
 */
@Component
public class MenuRepositoryImpl implements CustomMenuRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Menu> findAllMenusByBusinessUuid(String businessUuid, Boolean isDeleted) {
		JPAQuery<Menu> query = new JPAQuery<Menu>(em);
		QMenu qMenu = QMenu.menu;
		BooleanExpression searchPredicate = MenuPredicates.whereBusiness(businessUuid);

		if (isDeleted != null) {
			searchPredicate = searchPredicate.and(MenuPredicates.deletedMenu(isDeleted));
		}

		return query.from(qMenu).where(searchPredicate).fetch();
	}
}
