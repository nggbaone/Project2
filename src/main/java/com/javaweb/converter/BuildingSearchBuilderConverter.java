package com.javaweb.converter;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.utils.MapUtil;

@Component
public class BuildingSearchBuilderConverter {

	public BuildingSearchBuilder toBuildingSearchBuilder(Map<String,Object> params, List<String> renttype) {
		BuildingSearchBuilder buildingSearchBuilder = new BuildingSearchBuilder.Builder()
														.setName(MapUtil.getObject(params, "name", String.class))
														.setFloorArea(MapUtil.getObject(params, "floorarea", Long.class))
														.setWard(MapUtil.getObject(params, "ward", String.class))
														.setStreet(MapUtil.getObject(params, "street", String.class))
														.setDistrictcode(MapUtil.getObject(params, "districtid", String.class))
														.setNumberOfBasement(MapUtil.getObject(params, "numberofbasement", Integer.class))
														.setRentType(renttype)
														.setManagerName(MapUtil.getObject(params, "managername", String.class))
														.setManagerPhoneNumber(MapUtil.getObject(params, "managernamephonenumber", String.class))
														.setRentpriceFrom(MapUtil.getObject(params, "rentpriceFrom", Long.class))
														.setRentpriceTo(MapUtil.getObject(params, "rentpriceTo", Long.class))
														.setAreaFrom(MapUtil.getObject(params, "rentareaFrom", Long.class))
														.setAreaTo(MapUtil.getObject(params, "rentareaTo", Long.class))
														.setStaffId(MapUtil.getObject(params, "staffId", Long.class))
														.build();																													
		return buildingSearchBuilder;
	}
	
}
