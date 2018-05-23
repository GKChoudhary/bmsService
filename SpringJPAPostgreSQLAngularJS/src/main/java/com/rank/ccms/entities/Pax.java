package com.rank.ccms.entities;
// Generated 23 May, 2018 11:11:36 PM by Hibernate Tools 4.3.1.Final

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
 * Pax generated by hbm2java
 */
@Entity
@Table(name = "pax", catalog = "Bms")
public class Pax implements java.io.Serializable {

	private Integer id;
	private String paxTitle;
	private String paxFirstName;
	private String paxLastName;
	private String paxPhone;
	private String paxEmail;
	private Integer paxNos;
	private Set<Jobs> jobses = new HashSet<Jobs>(0);

	public Pax() {
	}

	public Pax(String paxTitle, String paxFirstName, String paxLastName, String paxPhone, String paxEmail,
			Integer paxNos, Set<Jobs> jobses) {
		this.paxTitle = paxTitle;
		this.paxFirstName = paxFirstName;
		this.paxLastName = paxLastName;
		this.paxPhone = paxPhone;
		this.paxEmail = paxEmail;
		this.paxNos = paxNos;
		this.jobses = jobses;
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

	@Column(name = "pax_title", length = 45)
	public String getPaxTitle() {
		return this.paxTitle;
	}

	public void setPaxTitle(String paxTitle) {
		this.paxTitle = paxTitle;
	}

	@Column(name = "pax_first_name", length = 45)
	public String getPaxFirstName() {
		return this.paxFirstName;
	}

	public void setPaxFirstName(String paxFirstName) {
		this.paxFirstName = paxFirstName;
	}

	@Column(name = "pax_last_name", length = 45)
	public String getPaxLastName() {
		return this.paxLastName;
	}

	public void setPaxLastName(String paxLastName) {
		this.paxLastName = paxLastName;
	}

	@Column(name = "pax_phone", length = 45)
	public String getPaxPhone() {
		return this.paxPhone;
	}

	public void setPaxPhone(String paxPhone) {
		this.paxPhone = paxPhone;
	}

	@Column(name = "pax_email", length = 45)
	public String getPaxEmail() {
		return this.paxEmail;
	}

	public void setPaxEmail(String paxEmail) {
		this.paxEmail = paxEmail;
	}

	@Column(name = "pax_nos")
	public Integer getPaxNos() {
		return this.paxNos;
	}

	public void setPaxNos(Integer paxNos) {
		this.paxNos = paxNos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pax")
	public Set<Jobs> getJobses() {
		return this.jobses;
	}

	public void setJobses(Set<Jobs> jobses) {
		this.jobses = jobses;
	}

}
