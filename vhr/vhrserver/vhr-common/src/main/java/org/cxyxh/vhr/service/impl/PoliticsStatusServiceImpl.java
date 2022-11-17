package org.cxyxh.vhr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.cxyxh.vhr.mapper.HrRoleMapper;
import org.cxyxh.vhr.mapper.PoliticsStatusMapper;
import org.cxyxh.vhr.model.HrRole;
import org.cxyxh.vhr.model.PoliticsStatus;
import org.cxyxh.vhr.service.IHrRoleService;
import org.cxyxh.vhr.service.IPoliticsStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ： cxyxh
 * @date : 2022/8/31 1:03
 * @describetion :
 */
@Service
public class PoliticsStatusServiceImpl extends ServiceImpl<PoliticsStatusMapper, PoliticsStatus> implements IPoliticsStatusService {

	@Autowired
	PoliticsStatusMapper politicsStatusMapper;

	@Override
	public List<PoliticsStatus> getAllPoliticsStatus() {
		QueryWrapper<PoliticsStatus> wrapper = new QueryWrapper<>();
		return politicsStatusMapper.selectList(wrapper);
	}
}
