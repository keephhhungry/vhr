package org.cxyxh.vhr.controller.emp;

import io.swagger.models.auth.In;
import org.cxyxh.vhr.constant.Const;
import org.cxyxh.vhr.model.*;
import org.cxyxh.vhr.service.*;
import org.cxyxh.vhr.utils.BaseDataUtils;
import org.cxyxh.vhr.utils.POIUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author ： cxyxh
 * @date : 2022/9/1 23:51
 * @describetion :
 */

@RestController
@RequestMapping("/employee/basic")
public class EmpBasicController {

	@Autowired
	IEmployeeService employeeService;

	@Autowired
	INationService nationService;

	@Autowired
	IPoliticsStatusService politicsStatusService;

	@Autowired
	IJobLevelService jobLevelService;

	@Autowired
	IPositionService positionService;

	@Autowired
	IDepartmentService departmentService;

	@Autowired
	BaseDataUtils baseDataUtils;

	@GetMapping("/")
	public RespPageBean getEmployeeByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size, Employee employee, String[] beginDateScope) throws ParseException {
		return employeeService.getEmployeeByPage(page, size, employee, beginDateScope);
	}

	@PostMapping("/")
	public RespBean addEmployee(@RequestBody Employee employee){
		if(Boolean.TRUE.equals(employeeService.addEmployee(employee))){
			return RespBean.ok(Const.ADD_SUCCESS);
		}
		return RespBean.error(Const.ADD_FAIL);
	}

	@DeleteMapping("/{id}")
	public RespBean deleteEmpById(@PathVariable Integer id){
		if(employeeService.removeById(id)){
			return RespBean.ok(Const.DELETE_SUCCESS);
		}
		return RespBean.error(Const.DELETE_FAIL);
	}

	@PutMapping("/")
	public RespBean updateEmp(@RequestBody Employee employee){
		if(employeeService.updateById(employee)){
			return RespBean.ok(Const.UPDATE_SUCCESS);
		}
		return RespBean.error(Const.UPDATE_FAIL);
	}


	@GetMapping("/nations")
	public List<Nation> getAllNations() {
		return baseDataUtils.getNationList();
	}

	@GetMapping("/politicsStatus")
	public List<PoliticsStatus> getAllPoliticsStatus() {
		return baseDataUtils.getPoliticsStatusList();
	}

	@GetMapping("/jobLevels")
	public List<JobLevel> getAllJobLevels() {
		return jobLevelService.getAllJobLevels();
	}

	@GetMapping("/positions")
	public List<Position> getAllPositions() {
		return positionService.getAllPositions();
	}

	@GetMapping("/deps")
	public List<Department> getAllDepartments() {
		return departmentService.getAllDepartmentsByParentId(1);
	}

	@GetMapping("/maxWorkId")
	public RespBean maxWorkID() {
		RespBean respBean = RespBean.build().setStatus(200)
				.setObj(String.format("%08d", employeeService.maxWorkId() + 1));
		return respBean;
	}

	@GetMapping("/export")
	public ResponseEntity<byte[]> exportData(){
		List<Employee> list = employeeService.getAllEmployee();
		return POIUtils.employeeToExcel(list);
	}

	@PostMapping("/import")
	public RespBean importData(MultipartFile file) throws IOException {
		List<Employee> list = POIUtils.excelToEmployee(file, baseDataUtils.getNationList(), baseDataUtils.getPoliticsStatusList(),
				departmentService.getAllDepartments(), positionService.getAllPositions(), jobLevelService.getAllJobLevels());
		if (Boolean.TRUE.equals(employeeService.saveBatch(list))) {
			return RespBean.ok("上传成功");
		}
		return RespBean.error("上传失败");
	}
}
