package com.pintabar.entities.entities.base;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by lucasgodoy on 1/03/17.
 */
@Getter
@Setter
@MappedSuperclass
public abstract class TimestampedBaseEntity extends BaseEntity {
	@Temporal(TemporalType.DATE)
	@Column(nullable = false, updatable = false)
	private Date createdOn;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date updatedOn;

	@PrePersist
	protected void onCreate() {
		updatedOn = createdOn = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		updatedOn = new Date();
	}
}
