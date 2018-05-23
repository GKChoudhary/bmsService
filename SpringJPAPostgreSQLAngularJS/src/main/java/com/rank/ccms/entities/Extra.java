package com.rank.ccms.entities;
// Generated 24 May, 2018 12:57:27 AM by Hibernate Tools 4.3.1.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Extra generated by hbm2java
 */
@Entity
@Table(name = "extra", catalog = "Bms")
public class Extra implements java.io.Serializable {

	private Integer id;
	private String name;
	private String type;
	private String value;
	private Set<Car> cars = new HashSet<Car>(0);

	public Extra() {
	}

	public Extra(String name, String type, String value, Set<Car> cars) {
		this.name = name;
		this.type = type;
		this.value = value;
		this.cars = cars;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "name", length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "type", length = 45)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "value", length = 45)
	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "extra")
	public Set<Car> getCars() {
		return this.cars;
	}

	public void setCars(Set<Car> cars) {
		this.cars = cars;
	}

}
