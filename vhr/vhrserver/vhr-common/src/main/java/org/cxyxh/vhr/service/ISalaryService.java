package org.cxyxh.vhr.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cxyxh.vhr.model.RespBean;
import org.cxyxh.vhr.model.Salary;

import java.util.List;

/**
 * @author ： cxyxh
 * @date : 2022/9/18 10:12
 * @describetion :
 */
public interface ISalaryService extends IService<Salary> {

	List<Salary> getAllSalaries();

	boolean updateSalary(Salary salary);
}
