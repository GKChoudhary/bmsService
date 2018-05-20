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
 * Car generated by hbm2java
 */
@Entity
@Table(name = "car", catalog = "MyBms")
public class Car implements java.io.Serializable {

	private int id;
	private DriverMst driverMst;
	private Extra extra;
	private String carClass;
	private String orderId;
	private String description;
	private String distanceFare;
	private String minimumFare;
	private String perMinWaitingCharges;
	private String hourlyFare;
	private String minimumNumberOfHours;
	private String maximumPassengers;
	private String maximumSuitcases;
	private String carImage;

	public Car() {
	}

	public Car(int id, DriverMst driverMst) {
		this.id = id;
		this.driverMst = driverMst;
	}

	public Car(int id, DriverMst driverMst, Extra extra, String carClass, String orderId, String description,
			String distanceFare, String minimumFare, String perMinWaitingCharges, String hourlyFare,
			String minimumNumberOfHours, String maximumPassengers, String maximumSuitcases, String carImage) {
		this.id = id;
		this.driverMst = driverMst;
		this.extra = extra;
		this.carClass = carClass;
		this.orderId = orderId;
		this.description = description;
		this.distanceFare = distanceFare;
		this.minimumFare = minimumFare;
		this.perMinWaitingCharges = perMinWaitingCharges;
		this.hourlyFare = hourlyFare;
		this.minimumNumberOfHours = minimumNumberOfHours;
		this.maximumPassengers = maximumPassengers;
		this.maximumSuitcases = maximumSuitcases;
		this.carImage = carImage;
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
	@JoinColumn(name = "driver_mst_id", nullable = false)
	public DriverMst getDriverMst() {
		return this.driverMst;
	}

	public void setDriverMst(DriverMst driverMst) {
		this.driverMst = driverMst;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "extra_id")
	public Extra getExtra() {
		return this.extra;
	}

	public void setExtra(Extra extra) {
		this.extra = extra;
	}

	@Column(name = "car_class", length = 45)
	public String getCarClass() {
		return this.carClass;
	}

	public void setCarClass(String carClass) {
		this.carClass = carClass;
	}

	@Column(name = "order_id", length = 45)
	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Column(name = "description", length = 45)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "distance_fare", length = 45)
	public String getDistanceFare() {
		return this.distanceFare;
	}

	public void setDistanceFare(String distanceFare) {
		this.distanceFare = distanceFare;
	}

	@Column(name = "minimum_fare", length = 45)
	public String getMinimumFare() {
		return this.minimumFare;
	}

	public void setMinimumFare(String minimumFare) {
		this.minimumFare = minimumFare;
	}

	@Column(name = "per_min_waiting_charges", length = 45)
	public String getPerMinWaitingCharges() {
		return this.perMinWaitingCharges;
	}

	public void setPerMinWaitingCharges(String perMinWaitingCharges) {
		this.perMinWaitingCharges = perMinWaitingCharges;
	}

	@Column(name = "hourly_fare", length = 45)
	public String getHourlyFare() {
		return this.hourlyFare;
	}

	public void setHourlyFare(String hourlyFare) {
		this.hourlyFare = hourlyFare;
	}

	@Column(name = "minimum_number_of_hours", length = 45)
	public String getMinimumNumberOfHours() {
		return this.minimumNumberOfHours;
	}

	public void setMinimumNumberOfHours(String minimumNumberOfHours) {
		this.minimumNumberOfHours = minimumNumberOfHours;
	}

	@Column(name = "maximum_passengers", length = 45)
	public String getMaximumPassengers() {
		return this.maximumPassengers;
	}

	public void setMaximumPassengers(String maximumPassengers) {
		this.maximumPassengers = maximumPassengers;
	}

	@Column(name = "maximum_suitcases", length = 45)
	public String getMaximumSuitcases() {
		return this.maximumSuitcases;
	}

	public void setMaximumSuitcases(String maximumSuitcases) {
		this.maximumSuitcases = maximumSuitcases;
	}

	@Column(name = "car_image", length = 45)
	public String getCarImage() {
		return this.carImage;
	}

	public void setCarImage(String carImage) {
		this.carImage = carImage;
	}

}
