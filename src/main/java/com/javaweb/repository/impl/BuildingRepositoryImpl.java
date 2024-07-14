package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.utils.ConnectionJDBCUtil;
import com.javaweb.utils.NumberUtil;
import com.javaweb.utils.StringUtil;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository {
	
	public static void joinTable(Map<String, Object> params, List<String> renttype, StringBuilder sql) {
		String staffId = (String)params.get("staffId");
		if (StringUtil.checkString(staffId)) {
			sql.append(" INNER JOIN assignmentbuilding ON building.id = assignmentbuilding.buildingid ");
		}
		if (renttype != null && renttype.size() != 0) {
			sql.append(" INNER JOIN buildingrenttype ON building.id = buildingrenttype.buildingid ");
			sql.append(" INNER JOIN renttype ON buildingrenttype.renttypeid = renttype.id ");
		}
		String rentareaFrom = (String)params.get("rentareaFrom");
		String rentareaTo = (String)params.get("rentareaTo");
		if (StringUtil.checkString(rentareaFrom) || StringUtil.checkString(rentareaTo)) {
			sql.append(" INNER JOIN rentarea ON building.id = rentarea.buildingid ");
		}
	}
	
	public static void queryNomal(Map<String, Object> params, StringBuilder where) {
		for (Map.Entry<String, Object> i : params.entrySet()) {
			if (!i.getKey().equals("staffId") && !i.getKey().equals("renttype") && 
					!i.getKey().startsWith("rentarea") && !i.getKey().startsWith("rentprice")) {
				String value = i.getValue().toString();
				if (NumberUtil.isNumber(value)) {
					where.append(" AND building." +i.getKey() +" = " +value);
				} else {
					where.append(" AND building." +i.getKey() +" LIKE '%" +value +"%' ");
				}
			}
		}
	}
	
	public static void querySpecial(Map<String, Object> params, List<String> renttype, StringBuilder where) {
		String staffId = (String)params.get("staffId");
		if (StringUtil.checkString(staffId)) {
			where.append(" AND assignmentbuilding.staffid = " +staffId);
		}
		
		String rentareaFrom = (String)params.get("rentareaFrom");
		String rentareaTo = (String)params.get("rentareaTo");
		if (StringUtil.checkString(rentareaFrom) || StringUtil.checkString(rentareaTo)) {
			if (StringUtil.checkString(rentareaFrom)) {
				where.append(" AND rentarea.value >= " +rentareaFrom);
			}
			if (StringUtil.checkString(rentareaTo)) {
				where.append(" AND rentarea.value <= " +rentareaTo);
			}
		}
		
		String rentpriceFrom = (String)params.get("rentpriceFrom");
		String rentpriceTo = (String)params.get("rentpriceTo");
		if (StringUtil.checkString(rentpriceFrom) || StringUtil.checkString(rentpriceTo)) {
			if (StringUtil.checkString(rentpriceFrom)) {
				where.append(" AND building.rentprice >= " +rentpriceFrom);
			}
			if (StringUtil.checkString(rentpriceTo)) {
				where.append(" AND building.rentprice <= " +rentpriceTo);
			}
		}
		// java 7
		if(renttype != null && renttype.size() != 0) {
			List<String > code = new ArrayList<>();
			for (String item : renttype) {
				code.add("'" + item + "'");
			}
			where.append(" AND renttype.code IN (" +String.join(",", code) +") ");
		}
		
	}
	
	@Override
	public List<BuildingEntity> findAll(Map<String, Object> params, List<String> renttype) {
		StringBuilder sql = new StringBuilder(" SELECT building.id, building.name, building.districtid, building.street, building.ward, building.numberofbasement, building.floorarea, building.rentprice, building.managername, building.managerphonenumber, building.servicefee, building.brokeragefee FROM building ");
		StringBuilder where = new StringBuilder(" WHERE 1 = 1 ");
		joinTable(params, renttype, sql);
		queryNomal(params, where);
		querySpecial(params, renttype, where);
		where.append(" GROUP BY building.id ");
		sql.append(where);
		List<BuildingEntity> result = new ArrayList<>();
		try (
			Connection conn = ConnectionJDBCUtil.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql.toString()); 
		) {
			while(rs.next()) {
				BuildingEntity building = new BuildingEntity();
				building.setId(rs.getLong("building.id"));
				building.setName(rs.getString("building.name"));
				building.setWard(rs.getString("building.ward"));
				building.setDistrictid(rs.getLong("building.districtid"));
				building.setStreet(rs.getString("building.street"));
				building.setFloorArea(rs.getLong("building.floorarea"));
				building.setRentPrice(rs.getLong("building.rentprice"));
				building.setServiceFee(rs.getString("building.servicefee")) ;
				building.setBrokerageFee(rs.getLong("building.brokeragefee"));
				building.setManagerName(rs.getString("building.managername")) ;
				building.setManagerPhoneNumber(rs.getString("building.managerphonenumber"));
				result.add(building);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
