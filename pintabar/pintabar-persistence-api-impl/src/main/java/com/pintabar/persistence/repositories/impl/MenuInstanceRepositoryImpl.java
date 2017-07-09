package com.pintabar.persistence.repositories.impl;

import com.pintabar.entities.MenuInstance;
import com.pintabar.entities.QMenuInstance;
import com.pintabar.persistence.querydsl.predicates.MenuInstancePredicates;
import com.pintabar.persistence.repositories.custom.CustomMenuInstanceRepository;
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
public class MenuInstanceRepositoryImpl implements CustomMenuInstanceRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<MenuInstance> findAllMenuInstancesByBusinessUuid(String businessUuid, Boolean isDeleted) {
		JPAQuery<MenuInstance> query = new JPAQuery<MenuInstance>(em);
		QMenuInstance qMenuInstance = QMenuInstance.menuInstance;
		BooleanExpression searchPredicate = MenuInstancePredicates.whereBusiness(businessUuid);

		if (isDeleted != null) {
			searchPredicate = searchPredicate.and(MenuInstancePredicates.deletedMenuInstance(isDeleted));
		}

		return query.from(qMenuInstance).where(searchPredicate).fetch();
	}
}
