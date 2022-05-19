package com.bangahu.main.model.domain;


import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = {"uid"})
@JsonIgnoreProperties(value = {"uid", "password"})
public class User {
	private int uid;
	private String username;
	private String password;
	private Date createdAt;
}
