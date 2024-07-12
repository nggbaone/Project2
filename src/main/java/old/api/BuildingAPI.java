/*
package old.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaweb.beans.BuildingDTO;
import com.javaweb.beans.ErrorResponseDTO;

import customexception.FieldRequiredException;

@Controller
// có thể @RestController thay cho @Controller vì trong @RestController gồm @ResponseBody và @Controller
public class BuildingAPI {
	
	@RequestMapping(value = "/api/building/", method = RequestMethod.GET)
	public void getBuilding(@RequestParam(value = "name", required = false) String name,
							@RequestParam(value = "age", required = false) Integer age,
							@RequestParam(value = "sex", required = false) String sex) {
		System.out.print(name + " " + age + " " + sex);
	}
	
	@RequestMapping(value = "/api/building1/", method = RequestMethod.GET)
	public void getBuilding1(@RequestParam Map<String, Object> params) {
		System.out.print("ok");
	}
	
	@GetMapping(value = "/api/building2/")
	public void getBuilding2(@RequestBody BuildingDTO t) {
		System.out.print("ok");
	}
	
//	@RequestMapping(value = "/api/building3/", method = RequestMethod.GET)
//	@ResponseBody
	// @ResponseBody trả Data về kiểu JSON, có thể dùng @ResponseyBody hoặc @RestController 
//	public BuildingDTO getBuilding3(@RequestParam(value = "name", required = false) String name,
//									@RequestParam(value = "age", required = false) Integer age,
//									@RequestParam(value = "sex", required = false) String sex) {
//		BuildingDTO result = new BuildingDTO();
//		result.setName(name);
//		result.setAge(age);
//		result.setSex(sex);
//		return result;
//	}
	// Dùng List
	@RequestMapping(value = "/api/building3/", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<BuildingDTO> getBuilding3(@RequestParam(value = "name", required = false) String name,
											   @RequestParam(value = "age", required = false) Integer age,
											   @RequestParam(value = "sex", required = false) String sex) {
		BuildingDTO result1 = new BuildingDTO();
		result1.setName(name);
		result1.setAge(age);
		result1.setSex(sex);
		BuildingDTO result2 = new BuildingDTO();
		result2.setName("Itachi");
		result2.setAge(12);
		result2.setSex("Male");
		ArrayList<BuildingDTO> listBuildingDTO = new ArrayList<>();
		listBuildingDTO.add(result1);
		listBuildingDTO.add(result2);
		return listBuildingDTO;
	}
	
	@DeleteMapping(value = "/api/building4/{id}/{name}")
	public void getBuilding4(@PathVariable Integer id,
							 @PathVariable String name) {
		System.out.print("Xoa id: " + id + "," + name + " thanh cong!");
	}
	
	@PostMapping(value = "/api/building5/")
	@ResponseBody
	public Object getBuilding5(@RequestBody BuildingDTO building) {
//		try {
//			System.out.print(5/0);
//			valiDate(building);
//		} catch (FieldRequiredException e) {
//			ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
//			errorResponseDTO.setError(e.getMessage());
//			List<String> details = new ArrayList<>();
//			details.add("Kiem tra lai name hoac age boi vi no dang null!");
//			errorResponseDTO.setDetail(details);
//			return errorResponseDTO;
//		}
		// khi dung @ControllerAdvice khong can try catch
		valiDate(building);
		return null;
	}
	// Neu su dung RuntimeException thi khong can phai throws FieldRequiredException
	public void valiDate(BuildingDTO buildingDTO) {
		if (buildingDTO.getName() == null || buildingDTO.getName().equals("") || buildingDTO.getAge() == null) {
			throw new FieldRequiredException("name or age is null");
		}
	}
	
}
*/
