package org.cxyxh.vhr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.cxyxh.vhr.mapper.JobLevelMapper;
import org.cxyxh.vhr.model.JobLevel;
import org.cxyxh.vhr.service.IJobLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author ： cxyxh
 * @date : 2022/8/27 17:19
 * @describetion :
 */
@Service
public class JobLevelServiceImpl extends ServiceImpl<JobLevelMapper, JobLevel> implements IJobLevelService {

	@Autowired
	JobLevelMapper jobLevelMapper;

	@Override
	public List<JobLevel> getAllJobLevels() {
		QueryWrapper<JobLevel> wrapper = new QueryWrapper<>();
		List<JobLevel> jobLevels = jobLevelMapper.selectList(wrapper);
		return jobLevels;
	}

	@Override
	public Integer addJobLevel(JobLevel jobLevel) {
		jobLevel.setEnabled(true);
		jobLevel.setCreateDate(new Date());
		return jobLevelMapper.insert(jobLevel);
	}

	@Override
	public Integer updateJobLevel(JobLevel jobLevel) {
		return jobLevelMapper.updateById(jobLevel);
	}

	@Override
	public Integer deleteJobLevelById(Integer id) {
		return jobLevelMapper.deleteById(id);
	}

	@Override
	public Integer deleteJobLevelByIds(Integer[] ids) {
		return jobLevelMapper.deleteBatchIds(Arrays.asList(ids));
	}

}
