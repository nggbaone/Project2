package com.javaweb.repository.custom.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import com.javaweb.repository.entity.BuildingEntity;

@Repository
public class BuildingRepositoryImpl  implements BuildingRepositoryCustom{

	@PersistenceContext
	private EntityManager entityManager;
	
	public static void joinTable(BuildingSearchBuilder buildingSearchBuilder, StringBuilder sql) {
		Long staffId =  buildingSearchBuilder.getStaffId();
		if (staffId != null) {
			sql.append(" INNER JOIN assignmentbuilding ON building.id = assignmentbuilding.buildingid ");
		}
		List<String> renttype = buildingSearchBuilder.getRentType();
		if (renttype != null && renttype.size() != 0) {
			sql.append(" INNER JOIN buildingrenttype ON building.id = buildingrenttype.buildingid ");
			sql.append(" INNER JOIN renttype ON buildingrenttype.renttypeid = renttype.id ");
		}
	}

	public static void queryNomal(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
		try {
			Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
			for (Field item : fields) {
				item.setAccessible(true);
				String fieldName = item.getName();
				if (!fieldName.equals("staffId") && !fieldName.equals("renttype")
						&& !fieldName.startsWith("rentarea") && !fieldName.startsWith("rentprice")) {
					Object value = item.get(buildingSearchBuilder);
					if (value != null) {
						if (item.getType().getName().equals("java.lang.Long") || item.getType().getName().equals("java.lang.Integer")) {
							where.append(" AND building." + fieldName + " = " + value);
						} else if (item.getType().getName().equals("java.lang.String")) {
							where.append(" AND building." + fieldName + " LIKE '%" + value + "%' ");
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void querySpecial(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
		Long staffId = buildingSearchBuilder.getStaffId();
		if (staffId != null) {
			where.append(" AND assignmentbuilding.staffid = " + staffId);
		}

		Long rentareaFrom = buildingSearchBuilder.getRentareaFrom();
		Long rentareaTo = buildingSearchBuilder.getRentareaTo();
		if (rentareaFrom != null || rentareaTo != null) {
			where.append(" AND EXISTS (SELECT * FROM rentarea WHERE building.id = rentarea.buildingid ");
			if (rentareaFrom != null) {
				where.append(" AND rentarea.value >= " + rentareaFrom);
			}
			if (rentareaTo != null) {
				where.append(" AND rentarea.value <= " + rentareaTo);
			}
			where.append(") ");
		}

		Long rentpriceFrom = buildingSearchBuilder.getRentpriceFrom();
		Long rentpriceTo = buildingSearchBuilder.getRentpriceTo();
		if (rentpriceFrom != null || rentpriceTo != null) {
			if (rentpriceFrom != null) {
				where.append(" AND building.rentprice >= " + rentpriceFrom);
			}
			if (rentpriceTo != null) {
				where.append(" AND building.rentprice <= " + rentpriceTo);
			}
		}
		// java 8 
		List<String> renttype = buildingSearchBuilder.getRentType();
		if (renttype != null && renttype.size() != 0) {
			where.append(" AND ( ");
			String sql = renttype.stream().map(i -> "renttype.code LIKE '%" + i + "%' ")
					.collect(Collectors.joining(" OR "));
			where.append(sql);
			where.append(" ) ");
		}
	}

//	@Override
	public List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder) {
		StringBuilder sql = new StringBuilder(
				" SELECT * FROM building ");
		StringBuilder where = new StringBuilder(" WHERE 1 = 1 ");
		joinTable(buildingSearchBuilder, sql);
		queryNomal(buildingSearchBuilder, where);
		querySpecial(buildingSearchBuilder, where);
		where.append(" GROUP BY building.id ");
		sql.append(where);
		// khi dùng đoạn dướicâu select phải có các thuộc tính trong Entity nó mới chạy 
		Query query = entityManager.createNativeQuery(sql.toString(),BuildingEntity.class);
		return query.getResultList();
	}

}
