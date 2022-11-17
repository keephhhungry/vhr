package org.cxyxh.vhr.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cxyxh.vhr.model.Employee;
import org.cxyxh.vhr.model.HrRole;
import org.cxyxh.vhr.model.RespPageBean;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * @author ： cxyxh
 * @date : 2022/8/28 9:24
 * @describetion :
 */
public interface IEmployeeService extends IService<Employee> {

	RespPageBean getEmployeeByPage(Integer page, Integer size, Employee employee, String[] beginDateScope) throws ParseException;

	List<Employee> getAllEmployee();

	Integer maxWorkId();

	Boolean addEmployee(Employee employee);

	boolean updateEmployeeSalary(Integer eid, Integer sid);
}
