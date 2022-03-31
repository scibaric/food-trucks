package com.typeqast.foodtrucks.model;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

@Table
@Entity(name = "FOOD_TRUCK")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FoodTruck {
	
	@Id
	@Column(name = "LOCATION_ID", unique = true, nullable = false)
	private Long locationId;
	
	@Column(name = "APPLICANT", nullable = false)
	private String applicant;
	
	@Column(name = "FACILITY_TYPE")
	private String facilityType;
	
	@Column(name = "CNN")
	private Long cnn;
	
	@Column(name = "LOCATION_DESCRIPTION")
	private String locationDescription;

	@Column(name = "ADDRESS")
	private String address;
	
	@Column(name = "BLOCK_LOT")
	private String blockLot;
	
	@Column(name = "BLOCK")
	private String block;
	
	@Column(name = "LOT")
	private String lot;
	
	@Column(name = "PERMIT")
	private String permit;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "FOOD_ITEMS", length = 512)
	private String foodItems;
	
	@Column(name = "X")
	private BigDecimal x;
	
	@Column(name = "Y")
	private BigDecimal y;
	
	@Column(name = "LATITUDE")
	private String latitude;
	
	@Column(name = "LONGITUDE")
	private String longitude;
	
	@Column(name = "SCHEDULE")
	private String schedule;
	
	@Column(name = "DAYSHOURS")
	private String daysHours;
	
	@Column(name = "NOI_SENT")
	private String noiSent;
	
	@Column(name = "APPROVED")
	private String approved;
	
	@Column(name = "RECEIVED")
	private String received;
	
	@Column(name = "PRIOR_PERMIT")
	private Boolean priorPermit;
	
	@Column(name = "EXPIRATION_DATE")
	private String expirationDate;
	
	@Column(name = "LOCATION")
	private String location;
	
	@Column(name = "FIRE_PREV_DISTRICTS")
	private Integer firePreventionDistricts;
	
	@Column(name = "POLICE_DISTRICTS")
	private Integer policeDistricts;
	
	@Column(name = "SUPERVISOR_DISTRICTS")
	private Integer supervisorDistricts;
	
	@Column(name = "ZIP_CODES")
	private Long zipCodes;
	
	@Column(name = "NEIGHBORHOOODS_OLD")
	private Integer neighborhoodsOld;

	public FoodTruck() {}
	
	public FoodTruck(Long locationId, String applicant, String facilityType, String status, String foodItems) {
		this.locationId = locationId;
		this.applicant = applicant;
		this.facilityType = facilityType;
		this.status = status;
		this.foodItems = foodItems;
	}
	
	public FoodTruck(Long locationId, String applicant, String facilityType, String status, String foodItems,
			String latitude, String longitude) {
		
		this(locationId, applicant, facilityType, status, foodItems);
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public String getFacilityType() {
		return facilityType;
	}

	public void setFacilityType(String facilityType) {
		this.facilityType = facilityType;
	}

	public Long getCnn() {
		return cnn;
	}

	public void setCnn(Long cnn) {
		this.cnn = cnn;
	}

	public String getLocationDescription() {
		return locationDescription;
	}

	public void setLocationDescription(String locationDescription) {
		this.locationDescription = locationDescription;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBlockLot() {
		return blockLot;
	}

	public void setBlockLot(String blockLot) {
		this.blockLot = blockLot;
	}

	public String getBlock() {
		return block;
	}

	public void setBlock(String block) {
		this.block = block;
	}

	public String getLot() {
		return lot;
	}

	public void setLot(String lot) {
		this.lot = lot;
	}

	public String getPermit() {
		return permit;
	}

	public void setPermit(String permit) {
		this.permit = permit;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFoodItems() {
		return foodItems;
	}

	public void setFoodItems(String foodItems) {
		this.foodItems = foodItems;
	}

	public BigDecimal getX() {
		return x;
	}

	public void setX(BigDecimal x) {
		this.x = x;
	}

	public BigDecimal getY() {
		return y;
	}

	public void setY(BigDecimal y) {
		this.y = y;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	public String getDaysHours() {
		return daysHours;
	}

	public void setDaysHours(String daysHours) {
		this.daysHours = daysHours;
	}

	public String getNoiSent() {
		return noiSent;
	}

	public void setNoiSent(String noiSent) {
		this.noiSent = noiSent;
	}

	public String getApproved() {
		return approved;
	}

	public void setApproved(String approved) {
		this.approved = approved;
	}

	public String getReceived() {
		return received;
	}

	public void setReceived(String received) {
		this.received = received;
	}

	public Boolean getPriorPermit() {
		return priorPermit;
	}

	public void setPriorPermit(Boolean priorPermit) {
		this.priorPermit = priorPermit;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getFirePreventionDistricts() {
		return firePreventionDistricts;
	}

	public void setFirePreventionDistricts(Integer firePreventionDistricts) {
		this.firePreventionDistricts = firePreventionDistricts;
	}

	public Integer getPoliceDistricts() {
		return policeDistricts;
	}

	public void setPoliceDistricts(Integer policeDistricts) {
		this.policeDistricts = policeDistricts;
	}

	public Integer getSupervisorDistricts() {
		return supervisorDistricts;
	}

	public void setSupervisorDistricts(Integer supervisorDistricts) {
		this.supervisorDistricts = supervisorDistricts;
	}

	public Long getZipCodes() {
		return zipCodes;
	}

	public void setZipCodes(Long zipCodes) {
		this.zipCodes = zipCodes;
	}

	public Integer getNeighborhoodsOld() {
		return neighborhoodsOld;
	}

	public void setNeighborhoodsOld(Integer neighborhoodsOld) {
		this.neighborhoodsOld = neighborhoodsOld;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, applicant, approved, block, blockLot, cnn, daysHours, expirationDate, facilityType,
				firePreventionDistricts, foodItems, latitude, location, locationDescription, locationId, longitude,
				lot, neighborhoodsOld, noiSent, permit, policeDistricts, priorPermit, received, schedule, status,
				supervisorDistricts, x, y, zipCodes);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FoodTruck other = (FoodTruck) obj;
		return Objects.equals(address, other.address) && Objects.equals(applicant, other.applicant)
				&& Objects.equals(approved, other.approved) && Objects.equals(block, other.block)
				&& Objects.equals(blockLot, other.blockLot) && Objects.equals(cnn, other.cnn)
				&& Objects.equals(daysHours, other.daysHours) && Objects.equals(expirationDate, other.expirationDate)
				&& Objects.equals(facilityType, other.facilityType)
				&& Objects.equals(firePreventionDistricts, other.firePreventionDistricts)
				&& Objects.equals(foodItems, other.foodItems) && Objects.equals(latitude, other.latitude)
				&& Objects.equals(location, other.location)
				&& Objects.equals(locationDescription, other.locationDescription)
				&& Objects.equals(locationId, other.locationId) && Objects.equals(longitude, other.longitude)
				&& Objects.equals(lot, other.lot) && Objects.equals(neighborhoodsOld, other.neighborhoodsOld)
				&& Objects.equals(noiSent, other.noiSent) && Objects.equals(permit, other.permit)
				&& Objects.equals(policeDistricts, other.policeDistricts)
				&& Objects.equals(priorPermit, other.priorPermit) && Objects.equals(received, other.received)
				&& Objects.equals(schedule, other.schedule) && Objects.equals(status, other.status)
				&& Objects.equals(supervisorDistricts, other.supervisorDistricts) && Objects.equals(x, other.x)
				&& Objects.equals(y, other.y) && Objects.equals(zipCodes, other.zipCodes);
	}
	
	
}
