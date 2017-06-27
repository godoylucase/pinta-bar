package com.pintabar.entities.user.profile;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Created by lucasgodoy on 16/03/17.
 */
@Getter
@Setter
@Entity
@Table(name = "user_profile_customer")
@PrimaryKeyJoinColumn(name = "id")
public class CustomerUserProfile extends UserProfile {
}
