package org.cxyxh.vhr.controller.system.basic;

import org.cxyxh.vhr.constant.Const;
import org.cxyxh.vhr.model.JobLevel;
import org.cxyxh.vhr.model.RespBean;
import org.cxyxh.vhr.service.IJobLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ： cxyxh
 * @date : 2022/8/28 8:32
 * @describetion :
 */
@RestController
@RequestMapping("/system/basic/joblevel")
public class JobLevelController {

	@Autowired
	IJobLevelService jobLevelService;

	@GetMapping("/")
	public List<JobLevel> getAllJobLevels() {
		return jobLevelService.getAllJobLevels();
	}


	@PostMapping("/")
	public RespBean addJobLevel(@RequestBody JobLevel jobLevel) {
		if (jobLevelService.addJobLevel(jobLevel) == 1) {
			return RespBean.ok(Const.ADD_SUCCESS);
		}
		return RespBean.error(Const.ADD_FAIL);
	}

	@PutMapping("/")
	public RespBean updateJobLevel(@RequestBody JobLevel jobLevel) {
		if (jobLevelService.updateJobLevel(jobLevel) == 1) {
			return RespBean.ok(Const.UPDATE_SUCCESS);
		}
		return RespBean.error(Const.UPDATE_FAIL);
	}

	@DeleteMapping("/{id}")
	public RespBean deleteJobLevelById(@PathVariable Integer id){
		if (jobLevelService.deleteJobLevelById(id) == 1) {
			return RespBean.ok(Const.DELETE_SUCCESS);
		}
		return RespBean.error(Const.DELETE_FAIL);
	}

	@DeleteMapping("/")
	public RespBean deleteJobLevelByIds(Integer[] ids){
		if(jobLevelService.deleteJobLevelByIds(ids) == ids.length){
			return RespBean.ok(Const.DELETE_SUCCESS);
		}
		return RespBean.ok(Const.DELETE_FAIL);
	}
	
}
