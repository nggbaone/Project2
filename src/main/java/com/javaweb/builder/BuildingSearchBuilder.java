package com.javaweb.builder;

import java.util.ArrayList;
import java.util.List;

public class BuildingSearchBuilder {
	
	private String name; 
	private Long floorArea; 
	private String ward; 
	private String street;
	private Long districtid;
	private Integer numberOfBasement;
	private List<String> rentType = new ArrayList<>();
	private String managerName;
	private String managerPhoneNumber;
	private Long rentpriceFrom;
	private Long rentpriceTo;
	private Long rentareaFrom;
	private Long rentareaTo;
	private Long staffId;
	
	private BuildingSearchBuilder (Builder builder) {
		this.name = builder.name;
		this.floorArea = builder.floorArea;
		this.ward = builder.ward;
		this.street = builder.street;
		this.districtid = builder.districtid;
		this.numberOfBasement = builder.numberOfBasement;
		this.rentType = builder.rentType;
		this.managerName = builder.managerName;
		this.managerPhoneNumber = builder.managerPhoneNumber;
		this.rentpriceFrom = builder.rentpriceFrom;
		this.rentpriceTo = builder.rentpriceTo;
		this.rentareaFrom = builder.rentareaFrom;
		this.rentareaTo = builder.rentareaTo;
		this.staffId = builder.staffId;
	}
	
	public String getName() {
		return name;
	}
	public Long getFloorArea() {
		return floorArea;
	}
	public String getWard() {
		return ward;
	}
	public String getStreet() {
		return street;
	}
	public Long getDistrictid() {
		return districtid;
	}
	public Integer getNumberOfBasement() {
		return numberOfBasement;
	}
	public List<String> getRentType() {
		return rentType;
	}
	public String getManagerName() {
		return managerName;
	}
	public String getManagerPhoneNumber() {
		return managerPhoneNumber;
	}
	public Long getRentpriceFrom() {
		return rentpriceFrom;
	}
	public Long getRentpriceTo() {
		return rentpriceTo;
	}
	public Long getRentareaFrom() {
		return rentareaFrom;
	}
	public Long getRentareaTo() {
		return rentareaTo;
	}
	public Long getStaffId() {
		return staffId;
	}


	public static class Builder {
		
		private String name; 
		private Long floorArea; 
		private String ward; 
		private String street;
		private Long districtid;
		private Integer numberOfBasement;
		private List<String> rentType = new ArrayList<>();
		private String managerName;
		private String managerPhoneNumber;
		private Long rentpriceFrom;
		private Long rentpriceTo;
		private Long rentareaFrom;
		private Long rentareaTo;
		private Long staffId;
		
		public Builder setName(String name) {
			this.name = name;
			return this;
		}
		public Builder setFloorArea(Long floorArea) {
			this.floorArea = floorArea;
			return this;
		}
		public Builder setWard(String ward) {
			this.ward = ward;
			return this;
		}
		public Builder setStreet(String street) {
			this.street = street;
			return this;
		}
		public Builder setDistrictid(Long districtid) {
			this.districtid = districtid;
			return this;
		}
		public Builder setNumberOfBasement(Integer numberOfBasement) {
			this.numberOfBasement = numberOfBasement;
			return this;
		}
		public Builder setRentType(List<String> typeCode) {
			this.rentType = typeCode;
			return this;
		}
		public Builder setManagerName(String managerName) {
			this.managerName = managerName;
			return this;
		}
		public Builder setManagerPhoneNumber(String managerPhoneNumber) {
			this.managerPhoneNumber = managerPhoneNumber;
			return this;
		}
		public Builder setRentpriceFrom(Long rentpriceFrom) {
			this.rentpriceFrom = rentpriceFrom;
			return this;
		}
		public Builder setRentpriceTo(Long rentpriceTo) {
			this.rentpriceTo = rentpriceTo;
			return this;
		}
		public Builder setAreaFrom(Long rentareaFrom) {
			this.rentareaFrom = rentareaFrom;
			return this;
		}
		public Builder setAreaTo(Long rentareaTo) {
			this.rentareaTo = rentareaTo;
			return this;
		}
		public Builder setStaffId(Long staffId) {
			this.staffId = staffId;
			return this;
		}
		
		public BuildingSearchBuilder build() {
			return new BuildingSearchBuilder(this);
		}
		
	}
	
}
