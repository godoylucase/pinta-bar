package com.pintabar.entities.base;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.util.UUID;

/**
 * Created by lucasgodoy on 1/03/17.
 */
@Getter
@Setter
@MappedSuperclass
public abstract class UUIDBaseEntity extends TimestampedBaseEntity {

	@Column(nullable = false, unique = true)
	protected String uuid;

	@PrePersist
	public void initializeUUID() {
		if (uuid == null) {
			uuid = UUID.randomUUID().toString();
		}
	}
}
