package org.cxyxh.vhr.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cxyxh.vhr.model.JobLevel;
import org.cxyxh.vhr.model.JobLevel;

import java.util.List;

/**
 * @author ： cxyxh
 * @date : 2022/8/27 17:18
 * @describetion :
 */
public interface IJobLevelService extends IService<JobLevel> {

	List<JobLevel> getAllJobLevels();

	Integer addJobLevel(JobLevel jobLevel);

	Integer updateJobLevel(JobLevel jobLevel);

	Integer deleteJobLevelById(Integer id);

	Integer deleteJobLevelByIds(Integer[] ids);
}
