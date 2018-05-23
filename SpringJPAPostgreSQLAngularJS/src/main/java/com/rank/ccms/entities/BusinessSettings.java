package com.rank.ccms.entities;
// Generated 23 May, 2018 11:11:36 PM by Hibernate Tools 4.3.1.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * BusinessSettings generated by hbm2java
 */
@Entity
@Table(name = "business_settings", catalog = "Bms")
public class BusinessSettings implements java.io.Serializable {

	private Integer id;
	private Address address;
	private BankMst bankMst;
	private BusinessPhone businessPhone;
	private User user;
	private Date timeZone;
	private String businessName;
	private String tradingName;
	private String businessNumber;
	private String businessSlogan;
	private String paypalEmailId;
	private Integer addressId;
	private String logoUrl;
	private String logoName;
	private Set<EmailDetails> emailDetailses = new HashSet<EmailDetails>(0);

	public BusinessSettings() {
	}

	public BusinessSettings(Address address, BankMst bankMst, BusinessPhone businessPhone, User user, Date timeZone,
			String businessName, String tradingName, String businessNumber, String businessSlogan, String paypalEmailId,
			Integer addressId, String logoUrl, String logoName, Set<EmailDetails> emailDetailses) {
		this.address = address;
		this.bankMst = bankMst;
		this.businessPhone = businessPhone;
		this.user = user;
		this.timeZone = timeZone;
		this.businessName = businessName;
		this.tradingName = tradingName;
		this.businessNumber = businessNumber;
		this.businessSlogan = businessSlogan;
		this.paypalEmailId = paypalEmailId;
		this.addressId = addressId;
		this.logoUrl = logoUrl;
		this.logoName = logoName;
		this.emailDetailses = emailDetailses;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id1")
	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bank_mst_id")
	public BankMst getBankMst() {
		return this.bankMst;
	}

	public void setBankMst(BankMst bankMst) {
		this.bankMst = bankMst;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "business_phone_id")
	public BusinessPhone getBusinessPhone() {
		return this.businessPhone;
	}

	public void setBusinessPhone(BusinessPhone businessPhone) {
		this.businessPhone = businessPhone;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "time_zone", length = 19)
	public Date getTimeZone() {
		return this.timeZone;
	}

	public void setTimeZone(Date timeZone) {
		this.timeZone = timeZone;
	}

	@Column(name = "business_name", length = 45)
	public String getBusinessName() {
		return this.businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	@Column(name = "trading_name", length = 45)
	public String getTradingName() {
		return this.tradingName;
	}

	public void setTradingName(String tradingName) {
		this.tradingName = tradingName;
	}

	@Column(name = "business_number", length = 45)
	public String getBusinessNumber() {
		return this.businessNumber;
	}

	public void setBusinessNumber(String businessNumber) {
		this.businessNumber = businessNumber;
	}

	@Column(name = "business_slogan", length = 45)
	public String getBusinessSlogan() {
		return this.businessSlogan;
	}

	public void setBusinessSlogan(String businessSlogan) {
		this.businessSlogan = businessSlogan;
	}

	@Column(name = "paypal_email_id", length = 45)
	public String getPaypalEmailId() {
		return this.paypalEmailId;
	}

	public void setPaypalEmailId(String paypalEmailId) {
		this.paypalEmailId = paypalEmailId;
	}

	@Column(name = "address_id")
	public Integer getAddressId() {
		return this.addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	@Column(name = "logo_url", length = 45)
	public String getLogoUrl() {
		return this.logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	@Column(name = "logo_name", length = 45)
	public String getLogoName() {
		return this.logoName;
	}

	public void setLogoName(String logoName) {
		this.logoName = logoName;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "businessSettings")
	public Set<EmailDetails> getEmailDetailses() {
		return this.emailDetailses;
	}

	public void setEmailDetailses(Set<EmailDetails> emailDetailses) {
		this.emailDetailses = emailDetailses;
	}

}
