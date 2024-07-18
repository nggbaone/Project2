package com.javaweb.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
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
	private ModelMapper modelMapper;
	
	public BuildingDTO toBuildingDTO(BuildingEntity item) {
		BuildingDTO building = modelMapper.map(item, BuildingDTO.class);
		building.setAddress(item.getStreet() +", " +item.getWard() +", " +item.getDistrict().getName());
		List<RentareaEntity> reantareas = item.getItems();
		String rentareaResult = reantareas.stream().map(i -> i.getValue().toString()).collect(Collectors.joining(","));
		building.setRentArea(rentareaResult);
		return building;
	}
	
}
