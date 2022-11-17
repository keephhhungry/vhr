package org.cxyxh.vhr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.cxyxh.vhr.mapper.SalaryMapper;
import org.cxyxh.vhr.model.RespBean;
import org.cxyxh.vhr.model.Salary;
import org.cxyxh.vhr.service.ISalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ： cxyxh
 * @date : 2022/9/18 10:12
 * @describetion :
 */
@Service
public class SalaryServiceImpl extends ServiceImpl<SalaryMapper,Salary> implements ISalaryService {

	@Autowired
	SalaryMapper salaryMapper;


	@Override
	public List<Salary> getAllSalaries() {
		QueryWrapper<Salary> wrapper = new QueryWrapper<>();
		return salaryMapper.selectList(wrapper);
	}

	@Override
	public boolean updateSalary(Salary salary) {
		return this.updateById(salary);
	}


}
