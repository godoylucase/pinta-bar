package com.pintabar.entities.base;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * Created by lucasgodoy on 1/03/17.
 */
@Getter
@Setter
@MappedSuperclass
public abstract class TimestampedBaseEntity extends BaseEntity {
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, updatable = false)
	private Date createdOn;

	@Temporal(TemporalType.TIMESTAMP)
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
