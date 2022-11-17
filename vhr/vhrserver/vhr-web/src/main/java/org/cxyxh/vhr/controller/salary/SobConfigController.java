package org.cxyxh.vhr.controller.salary;

import io.swagger.models.auth.In;
import org.cxyxh.vhr.constant.Const;
import org.cxyxh.vhr.model.Employee;
import org.cxyxh.vhr.model.RespBean;
import org.cxyxh.vhr.model.RespPageBean;
import org.cxyxh.vhr.model.Salary;
import org.cxyxh.vhr.service.IEmployeeService;
import org.cxyxh.vhr.service.ISalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

/**
 * @author ： cxyxh
 * @date : 2022/9/18 11:36
 * @describetion :
 */
@RestController
@RequestMapping("/salary/sobcfg")
public class SobConfigController {

	@Autowired
	IEmployeeService employeeService;

	@Autowired
	ISalaryService salaryService;

	@GetMapping("/")
	public RespPageBean getEmployeeByPageWithSalary(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size) throws ParseException {
		return employeeService.getEmployeeByPage(page,size,null,null);
	}

	@GetMapping("/salaries")
	public List<Salary> getAllSalaries(){
		return salaryService.getAllSalaries();
	}

	@PutMapping("/")
	public RespBean updateEmployeeSalary(Integer eid, Integer sid){
		if(employeeService.updateEmployeeSalary(eid,sid)){
			return RespBean.ok(Const.UPDATE_SUCCESS);
		}
		return RespBean.error(Const.UPDATE_FAIL);
	}

}
