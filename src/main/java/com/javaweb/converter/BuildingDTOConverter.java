package com.javaweb.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaweb.model.BuildingDTO;
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.RentareaRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.repository.entity.RentareaEntity;

@Component
public class BuildingDTOConverter {

	@Autowired
	private DistrictRepository  districtRepository;
	
	@Autowired
	private RentareaRepository rentareaRepository;
	
	public BuildingDTO toBuildingDTO(BuildingEntity item) {
		BuildingDTO building = new BuildingDTO();
		building.setName(item.getName());
		DistrictEntity districtEntity = districtRepository.findNameById(item.getDistrictid());
		building.setAddress(item.getStreet() +", " +item.getWard() +", " +districtEntity.getName());
		List<RentareaEntity> reantareas = rentareaRepository.getValueBuildingId(item.getId());
		String rentareaResult = reantareas.stream().map(i -> i.getValue().toString()).collect(Collectors.joining(","));
		building.setRentArea(rentareaResult);
		building.setBrokerageFee(item.getBrokerageFee());
		building.setEmptyArea(item.getEmptyArea());
		building.setFloorArea(item.getFloorArea());
		building.setManagerName(item.getManagerName());
		building.setManagerPhoneNumber(item.getManagerPhoneNumber ());
		building.setRentPrice(item.getRentPrice());
		building.setServiceFee(item.getServiceFee());
		building.setEmptyArea(item.getEmptyArea());
		return building;
	}
	
}
