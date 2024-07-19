package com.javaweb.repository.custom.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.utils.ConnectionJDBCUtil;

public class DistrictRepositoryImpl {

//	@Override 
//	public DistrictEntity findNameById(Long id) {
//		String sql = " SELECT district.name FROM district WHERE district.id = " +id +" ";
//		DistrictEntity districtEntity = new DistrictEntity();
//		try (
//				Connection conn = ConnectionJDBCUtil.getConnection();
//				Statement stmt = conn.createStatement();
//				ResultSet rs = stmt.executeQuery(sql); 
//			) {
//				while(rs.next()) {
//					districtEntity.setName(rs.getString("name"));					
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			} 
//		return districtEntity;
//	}

}
