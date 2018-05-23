package com.rank.ccms.entities;
// Generated 24 May, 2018 12:57:27 AM by Hibernate Tools 4.3.1.Final

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
 * Jobs generated by hbm2java
 */
@Entity
@Table(name = "jobs", catalog = "Bms")
public class Jobs implements java.io.Serializable {

	private Integer id;
	private Address addressByFromAddressId;
	private Address addressByToAddressId;
	private JobFare jobFare;
	private Pax pax;
	private User user;
	private String jobStatus;
	private Date pickupDate;
	private Date pickupTime;
	private String flightNumber;
	private String airportTerminal;
	private String internalNotes;
	private String externalNotes;
	private String driverStatus;
	private String carType;
	private Boolean luggageLargeNos;
	private Boolean luggageMediumNos;
	private Set<Booking> bookings = new HashSet<Booking>(0);
	private Set<ExtraMst> extraMsts = new HashSet<ExtraMst>(0);

	public Jobs() {
	}

	public Jobs(Address addressByFromAddressId, Address addressByToAddressId, JobFare jobFare, Pax pax, User user,
			String jobStatus, Date pickupDate, Date pickupTime, String flightNumber, String airportTerminal,
			String internalNotes, String externalNotes, String driverStatus, String carType, Boolean luggageLargeNos,
			Boolean luggageMediumNos, Set<Booking> bookings, Set<ExtraMst> extraMsts) {
		this.addressByFromAddressId = addressByFromAddressId;
		this.addressByToAddressId = addressByToAddressId;
		this.jobFare = jobFare;
		this.pax = pax;
		this.user = user;
		this.jobStatus = jobStatus;
		this.pickupDate = pickupDate;
		this.pickupTime = pickupTime;
		this.flightNumber = flightNumber;
		this.airportTerminal = airportTerminal;
		this.internalNotes = internalNotes;
		this.externalNotes = externalNotes;
		this.driverStatus = driverStatus;
		this.carType = carType;
		this.luggageLargeNos = luggageLargeNos;
		this.luggageMediumNos = luggageMediumNos;
		this.bookings = bookings;
		this.extraMsts = extraMsts;
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
	@JoinColumn(name = "from_address_id")
	public Address getAddressByFromAddressId() {
		return this.addressByFromAddressId;
	}

	public void setAddressByFromAddressId(Address addressByFromAddressId) {
		this.addressByFromAddressId = addressByFromAddressId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "to_address_id")
	public Address getAddressByToAddressId() {
		return this.addressByToAddressId;
	}

	public void setAddressByToAddressId(Address addressByToAddressId) {
		this.addressByToAddressId = addressByToAddressId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fare_id")
	public JobFare getJobFare() {
		return this.jobFare;
	}

	public void setJobFare(JobFare jobFare) {
		this.jobFare = jobFare;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pax_id1")
	public Pax getPax() {
		return this.pax;
	}

	public void setPax(Pax pax) {
		this.pax = pax;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "booking_made_by_id")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "job_status", length = 45)
	public String getJobStatus() {
		return this.jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "pickup_date", length = 10)
	public Date getPickupDate() {
		return this.pickupDate;
	}

	public void setPickupDate(Date pickupDate) {
		this.pickupDate = pickupDate;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "pickup_time", length = 8)
	public Date getPickupTime() {
		return this.pickupTime;
	}

	public void setPickupTime(Date pickupTime) {
		this.pickupTime = pickupTime;
	}

	@Column(name = "flight_number", length = 45)
	public String getFlightNumber() {
		return this.flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	@Column(name = "airport_terminal", length = 45)
	public String getAirportTerminal() {
		return this.airportTerminal;
	}

	public void setAirportTerminal(String airportTerminal) {
		this.airportTerminal = airportTerminal;
	}

	@Column(name = "internal_notes", length = 45)
	public String getInternalNotes() {
		return this.internalNotes;
	}

	public void setInternalNotes(String internalNotes) {
		this.internalNotes = internalNotes;
	}

	@Column(name = "external_notes", length = 45)
	public String getExternalNotes() {
		return this.externalNotes;
	}

	public void setExternalNotes(String externalNotes) {
		this.externalNotes = externalNotes;
	}

	@Column(name = "driver_status", length = 45)
	public String getDriverStatus() {
		return this.driverStatus;
	}

	public void setDriverStatus(String driverStatus) {
		this.driverStatus = driverStatus;
	}

	@Column(name = "car_type", length = 45)
	public String getCarType() {
		return this.carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	@Column(name = "luggage_large_nos")
	public Boolean getLuggageLargeNos() {
		return this.luggageLargeNos;
	}

	public void setLuggageLargeNos(Boolean luggageLargeNos) {
		this.luggageLargeNos = luggageLargeNos;
	}

	@Column(name = "luggage_medium_nos")
	public Boolean getLuggageMediumNos() {
		return this.luggageMediumNos;
	}

	public void setLuggageMediumNos(Boolean luggageMediumNos) {
		this.luggageMediumNos = luggageMediumNos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "jobs")
	public Set<Booking> getBookings() {
		return this.bookings;
	}

	public void setBookings(Set<Booking> bookings) {
		this.bookings = bookings;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "jobs")
	public Set<ExtraMst> getExtraMsts() {
		return this.extraMsts;
	}

	public void setExtraMsts(Set<ExtraMst> extraMsts) {
		this.extraMsts = extraMsts;
	}

}
