package com.jwt.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
	@Id
	private String userName;
	private String firstName;
	private String lastName;
	private String password;
	@ManyToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinTable(name="USER_ROLE", 
		    joinColumns= {
		    		@JoinColumn(name="USER_ID")
		    		},
		    inverseJoinColumns = {
		    		@JoinColumn(name="ROLE_ID")
		    }
		   )
	private Set<Role> role;

}
