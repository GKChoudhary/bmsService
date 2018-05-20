package com.rank.ccms.entities;
// Generated 24 Apr, 2018 12:53:14 PM by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * BankParameter generated by hbm2java
 */
@Entity
@Table(name = "bank_parameter", catalog = "MyBms")
public class BankParameter implements java.io.Serializable {

	private int id;
	private Country country;
	private String fieldName;
	private String value;

	public BankParameter() {
	}

	public BankParameter(int id, Country country) {
		this.id = id;
		this.country = country;
	}

	public BankParameter(int id, Country country, String fieldName, String value) {
		this.id = id;
		this.country = country;
		this.fieldName = fieldName;
		this.value = value;
	}

	@Id

	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "country_id", nullable = false)
	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@Column(name = "field_name", length = 45)
	public String getFieldName() {
		return this.fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	@Column(name = "value", length = 45)
	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
