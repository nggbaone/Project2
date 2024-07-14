package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.RentareaRepository;
import com.javaweb.repository.entity.RentareaEntity;
import com.javaweb.utils.ConnectionJDBCUtil;

@Repository
public class RentareaRepositoryImpl implements RentareaRepository{

	@Override
	public List<RentareaEntity> getValueBuildingId(Long Id) {
		String sql = " SELECT * FROM rentarea WHERE rentarea.buildingid = " +Id;
		List<RentareaEntity> reantAreas = new ArrayList<RentareaEntity>();
		try (
				Connection conn = ConnectionJDBCUtil.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql); 
			) {
				while(rs.next()) {
					RentareaEntity rentareaEntity = new RentareaEntity();
					rentareaEntity.setValue(rs.getLong("value"));
					reantAreas.add(rentareaEntity);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		return reantAreas;
	}

}
