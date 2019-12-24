package com.proda5.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "useraccount")
public class UserAccount {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "serial")
	private int id;
	@Id
	private String phonenumber;
	private String name,shopname,email;
	
//	 private Set<String> role;
	
	private String type,status;
	
	
	public UserAccount(int id, String name, String phone,String email, String shopname, String password, String type,
			String status) {
		super();
		this.id = id;
		this.name = name;
		this.phonenumber = phone;
		this.email = email;
		this.shopname = shopname;
//		this.password = password;
		this.type = type;
		this.status = status;
	}
	
	public UserAccount( String name, String phone,String email, String shopname, String type,
			String status) {
		super();
//		this.id = id;
		this.name = name;
		this.phonenumber = phone;
		this.email = email;
		this.shopname = shopname;
//		this.password = password;
		this.type = type;
		this.status = status;
	}

	public UserAccount() {
		
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPhonenumber() {
		return phonenumber;
	}


	public void setPhonenumber(String phone) {
		this.phonenumber = phone;
	}



	public String getShopname() {
		return shopname;
	}


	public void setShopname(String shopname) {
		this.shopname = shopname;
	}


	


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}



}
