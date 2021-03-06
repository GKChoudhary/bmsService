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
 * Country generated by hbm2java
 */
@Entity
@Table(name = "country", catalog = "Bms")
public class Country implements java.io.Serializable {

	private Integer id;
	private String name;
	private String callingCode;
	private String abbreviationCode;
	private String currencyName;
	private String currencySymbol;
	private Boolean stauts;
	private String businessNumberName;
	private String distanceUnits;
	private Integer bankParameterId;
	private Set<BankParameter> bankParameters = new HashSet<BankParameter>(0);
	private Set<Address> addresses = new HashSet<Address>(0);
	private Set<State> states = new HashSet<State>(0);

	public Country() {
	}

	public Country(String name, String callingCode, String abbreviationCode, String currencyName, String currencySymbol,
			Boolean stauts, String businessNumberName, String distanceUnits, Integer bankParameterId,
			Set<BankParameter> bankParameters, Set<Address> addresses, Set<State> states) {
		this.name = name;
		this.callingCode = callingCode;
		this.abbreviationCode = abbreviationCode;
		this.currencyName = currencyName;
		this.currencySymbol = currencySymbol;
		this.stauts = stauts;
		this.businessNumberName = businessNumberName;
		this.distanceUnits = distanceUnits;
		this.bankParameterId = bankParameterId;
		this.bankParameters = bankParameters;
		this.addresses = addresses;
		this.states = states;
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

	@Column(name = "calling_code", length = 45)
	public String getCallingCode() {
		return this.callingCode;
	}

	public void setCallingCode(String callingCode) {
		this.callingCode = callingCode;
	}

	@Column(name = "abbreviation_code", length = 45)
	public String getAbbreviationCode() {
		return this.abbreviationCode;
	}

	public void setAbbreviationCode(String abbreviationCode) {
		this.abbreviationCode = abbreviationCode;
	}

	@Column(name = "currency_name", length = 45)
	public String getCurrencyName() {
		return this.currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	@Column(name = "currency_symbol", length = 45)
	public String getCurrencySymbol() {
		return this.currencySymbol;
	}

	public void setCurrencySymbol(String currencySymbol) {
		this.currencySymbol = currencySymbol;
	}

	@Column(name = "stauts")
	public Boolean getStauts() {
		return this.stauts;
	}

	public void setStauts(Boolean stauts) {
		this.stauts = stauts;
	}

	@Column(name = "business_number_name", length = 45)
	public String getBusinessNumberName() {
		return this.businessNumberName;
	}

	public void setBusinessNumberName(String businessNumberName) {
		this.businessNumberName = businessNumberName;
	}

	@Column(name = "distance_units", length = 45)
	public String getDistanceUnits() {
		return this.distanceUnits;
	}

	public void setDistanceUnits(String distanceUnits) {
		this.distanceUnits = distanceUnits;
	}

	@Column(name = "bank_parameter_id")
	public Integer getBankParameterId() {
		return this.bankParameterId;
	}

	public void setBankParameterId(Integer bankParameterId) {
		this.bankParameterId = bankParameterId;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "country")
	public Set<BankParameter> getBankParameters() {
		return this.bankParameters;
	}

	public void setBankParameters(Set<BankParameter> bankParameters) {
		this.bankParameters = bankParameters;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "country")
	public Set<Address> getAddresses() {
		return this.addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "country")
	public Set<State> getStates() {
		return this.states;
	}

	public void setStates(Set<State> states) {
		this.states = states;
	}

}
