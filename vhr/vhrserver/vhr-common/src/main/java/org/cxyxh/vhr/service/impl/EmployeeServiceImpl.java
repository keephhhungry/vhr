package org.cxyxh.vhr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.cxyxh.vhr.mapper.*;
import org.cxyxh.vhr.model.*;
import org.cxyxh.vhr.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springfox.documentation.annotations.ApiIgnore;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author ： cxyxh
 * @date : 2022/8/31 1:03
 * @describetion :
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {


	public static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Autowired
	EmployeeMapper employeeMapper;

	@Autowired
	INationService nationService;

	@Autowired
	IPoliticsStatusService politicsStatusService;

	@Autowired
	IDepartmentService departmentService;

	@Autowired
	IJobLevelService jobLevelService;

	@Autowired
	IPositionService positionService;

	@Autowired
	ISalaryService salaryService;

	@Autowired
	RabbitTemplate rabbitTemplate;

	SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
	SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
	DecimalFormat decimalFormat = new DecimalFormat("##.00");

	@Override
	public RespPageBean getEmployeeByPage(Integer page, Integer size, Employee employee, String[] beginDateScope) throws ParseException {

		IPage<Employee> pages = new Page<>(page, size);
		QueryWrapper<Employee> wrapper = new QueryWrapper<>();
		if(ObjectUtils.isNotEmpty(employee)){
			if (StringUtils.isNotBlank(employee.getName())) {
				wrapper.like("name", employee.getName());
			}
			if (ObjectUtils.isNotEmpty(employee.getPoliticId())) {
				wrapper.eq("politic_id", employee.getPoliticId());
			}
			if (ObjectUtils.isNotEmpty(employee.getNationId())) {
				wrapper.eq("nation_id", employee.getNationId());
			}
			if (ObjectUtils.isNotEmpty(employee.getJobLevelId())) {
				wrapper.eq("jobLevel_id", employee.getJobLevelId());
			}
			if (ObjectUtils.isNotEmpty(employee.getPosId())) {
				wrapper.eq("pos_id", employee.getPosId());
			}
			if (StringUtils.isNotBlank(employee.getEngageForm())) {
				wrapper.eq("engage_form", employee.getEngageForm());
			}
			if (ObjectUtils.isNotEmpty(employee.getDepartmentId())) {
				wrapper.eq("department_id", employee.getDepartmentId());
			}
			if (ObjectUtils.isNotEmpty(beginDateScope)) {
				wrapper.ge("begin_date",  beginDateScope[0]);
				wrapper.le("begin_date", beginDateScope[1]);
			}
		}
		IPage<Employee> employeeIPage = employeeMapper.selectPage(pages, wrapper);
		populateEmployeeList(employeeIPage.getRecords());
		return new RespPageBean(employeeIPage.getTotal(), employeeIPage.getRecords());
	}

	@Override
	public List<Employee> getAllEmployee() {
		QueryWrapper<Employee> wrapper = new QueryWrapper<>();
		List<Employee> list = employeeMapper.selectList(wrapper);
		populateEmployeeList(list);
		return list;
	}

	@Override
	public Integer maxWorkId() {
		QueryWrapper<Employee> wrapper = new QueryWrapper<>();
		wrapper.orderByDesc("id");
		wrapper.last("limit 1");
		Employee employee = employeeMapper.selectOne(wrapper);
		if (employee != null) {
			return employee.getId();
		}
		return 0;
	}

	@Override
	public Boolean addEmployee(Employee employee) {
		Date beginContract = employee.getBeginContract();
		Date endContract = employee.getEndContract();
		double month = (Double.parseDouble(yearFormat.format(endContract)) - Double.parseDouble(yearFormat.format(beginContract))) * 12 + (Double.parseDouble(monthFormat.format(endContract)) - Double.parseDouble(monthFormat.format(beginContract)));
		employee.setContractTerm(Double.parseDouble(decimalFormat.format(month / 12)));
		boolean flag = this.save(employee);
		if(flag){
			employee = this.getById(employee.getId());
			populateEmployee(employee);
			logger.info(employee.toString());
			rabbitTemplate.convertAndSend("javaboy.mail.welcome",employee);
		}
		return flag;
	}

	@Override
	public boolean updateEmployeeSalary(Integer eid, Integer sid) {
		Employee employee = employeeMapper.selectById(eid);
		Salary salary = salaryService.getById(sid);
		if(ObjectUtils.isEmpty(employee) || ObjectUtils.isEmpty(salary)){
			return false;
		}
		employee.setSalaryId(sid);
		return this.updateById(employee);
	}


	private void populateEmployeeList(List<Employee> list){
		if(CollectionUtils.isNotEmpty(list)){
			for (Employee employee : list) {
				populateEmployee(employee);
			}
		}
	}

	private void populateEmployee(Employee employee){
		if(ObjectUtils.isNotEmpty(employee)){
			Nation nation = nationService.getById(employee.getNationId());
			employee.setNation(nation);

			PoliticsStatus politicsStatus = politicsStatusService.getById(employee.getPoliticId());
			employee.setPoliticsStatus(politicsStatus);

			Department department = departmentService.getById(employee.getDepartmentId());
			employee.setDepartment(department);

			JobLevel jobLevel = jobLevelService.getById(employee.getJobLevelId());
			employee.setJobLevel(jobLevel);

			Position position = positionService.getById(employee.getPosId());
			employee.setPosition(position);

			Salary salary = salaryService.getById(employee.getSalaryId());
			employee.setSalary(salary);
		}
	}
}
