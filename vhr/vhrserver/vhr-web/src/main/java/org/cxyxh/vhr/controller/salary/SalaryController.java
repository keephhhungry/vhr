package org.cxyxh.vhr.controller.salary;

import io.swagger.models.auth.In;
import org.cxyxh.vhr.constant.Const;
import org.cxyxh.vhr.model.RespBean;
import org.cxyxh.vhr.model.Salary;
import org.cxyxh.vhr.service.ISalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

/**
 * @author ： cxyxh
 * @date : 2022/9/18 10:10
 * @describetion :
 */
@RestController
@RequestMapping("/salary/sob")
public class SalaryController {

	@Autowired
	ISalaryService salaryService;

	@GetMapping("/")
	public List<Salary> getAllSalaries(){
		return salaryService.getAllSalaries();
	}

	@PostMapping("/")
	public RespBean addSalary(@RequestBody Salary salary){
		salary.setCreateDate(new Date());
		if(salaryService.save(salary)){
			return RespBean.ok(Const.ADD_SUCCESS);
		}
		return RespBean.error(Const.ADD_FAIL);
	}

	@DeleteMapping("/{id}")
	public RespBean deleteSalary(@PathVariable Integer id){
		if(salaryService.removeById(id)){
			return RespBean.ok(Const.DELETE_SUCCESS);
		}
		return RespBean.error(Const.DELETE_FAIL);
	}

	@PutMapping("/")
	public RespBean updateSalary(@RequestBody Salary salary){
		if(salaryService.updateSalary(salary)){
			return RespBean.ok(Const.UPDATE_SUCCESS);
		}
		return RespBean.error(Const.UPDATE_FAIL);
	}
}
