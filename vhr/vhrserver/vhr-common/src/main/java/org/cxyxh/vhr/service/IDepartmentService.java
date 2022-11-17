package org.cxyxh.vhr.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cxyxh.vhr.model.Department;
import org.cxyxh.vhr.model.RespBean;

import java.util.List;

/**
 * @author ： cxyxh
 * @date : 2022/8/28 17:58
 * @describetion :
 */
public interface IDepartmentService extends IService<Department> {

	List<Department> getAllDepartmentsByParentId(int i);

	List<Department> getAllDepartments();

	Department addDepartment(Department department);

	RespBean deleteDepartmentById(Integer departmentId);

}
