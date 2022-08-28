package org.cxyxh.vhr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.cxyxh.vhr.constant.Const;
import org.cxyxh.vhr.mapper.DepartmentMapper;
import org.cxyxh.vhr.model.Department;
import org.cxyxh.vhr.model.RespBean;
import org.cxyxh.vhr.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ： cxyxh
 * @date : 2022/8/28 17:59
 * @describetion :
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

	@Autowired
	DepartmentMapper departmentMapper;

	@Override
	public List<Department> getAllDepartmentsByParentId(int i) {
		List<Department> departmentList = new ArrayList<>();
		Department department = departmentMapper.selectById(i);
		getDepartmentChildren(department);
		departmentList.add(department);
		return departmentList;
	}

	private Department getDepartmentChildren(Department department){
		if (department == null) {
			return null;
		}
		// 查询子部门
		QueryWrapper<Department> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("parent_id",department.getId());
		List<Department> departments = departmentMapper.selectList(queryWrapper);
		if (CollectionUtils.isNotEmpty(departments)){
			department.setChildren(departments);
			for (Department children : departments) {
				getDepartmentChildren(children);
			}
		}
		return department;
	}

	@Transactional
	@Override
	public Department addDepartment(Department department) {
		// 1.添加进去
		departmentMapper.insert(department);
		// 2. 更新path
		Department parentDepartment = departmentMapper.selectById(department.getParentId());
		department.setDepPath(parentDepartment.getDepPath()+"."+department.getId());
		departmentMapper.updateById(department);
		// 3. 更新父类enable
		if(Boolean.FALSE.equals(parentDepartment.getIsParent())){
			parentDepartment.setIsParent(Boolean.TRUE);
			departmentMapper.updateById(parentDepartment);
		}
		return department;
	}

	@Override
	public RespBean deleteDepartmentById(Integer departmentId) {
		// 查看是否有子部门
		Department department = departmentMapper.selectById(departmentId);
		if(Boolean.TRUE.equals(department.getIsParent())){
			RespBean.error("该部门下有子部门，删除失败");
		}
		// 查看该部门是否有员工 todo


		// 删除完以后，查看父部门是否有其他子部门
		departmentMapper.deleteById(departmentId);

		QueryWrapper<Department> wrapper = new QueryWrapper<>();
		wrapper.eq("parent_id",department.getParentId());
		List<Department> departments = departmentMapper.selectList(wrapper);
		if(CollectionUtils.isEmpty(departments)){
			Department parentDepartment = departmentMapper.selectById(department.getParentId());
			parentDepartment.setIsParent(Boolean.FALSE);
			departmentMapper.updateById(parentDepartment);
		}

		return RespBean.ok(Const.DELETE_SUCCESS);
	}

}
