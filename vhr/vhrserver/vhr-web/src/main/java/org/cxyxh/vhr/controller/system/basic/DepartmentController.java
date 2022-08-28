package org.cxyxh.vhr.controller.system.basic;

import org.apache.commons.lang3.ObjectUtils;
import org.cxyxh.vhr.constant.Const;
import org.cxyxh.vhr.model.Department;
import org.cxyxh.vhr.model.RespBean;
import org.cxyxh.vhr.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ： cxyxh
 * @date : 2022/8/28 16:08
 * @describetion :
 */
@RestController
@RequestMapping("/system/basic/department")
public class DepartmentController {

	@Autowired
	IDepartmentService departmentService;

	@GetMapping("/")
	public List<Department> getAllDepartments(){
		return departmentService.getAllDepartmentsByParentId(1);
	}


	@PostMapping("/")
	public RespBean addDepartment(@RequestBody Department department){
		if (ObjectUtils.isNotEmpty(departmentService.addDepartment(department))) {
			return RespBean.ok(Const.ADD_SUCCESS, department);
		}
		return RespBean.error(Const.ADD_FAIL);
	}

	@DeleteMapping("/{departmentId}")
	public RespBean deleteDepartmentById(@PathVariable Integer departmentId){
		return departmentService.deleteDepartmentById(departmentId);
	}
}
