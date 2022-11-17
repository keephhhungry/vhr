package org.cxyxh.vhr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.cxyxh.vhr.mapper.HrRoleMapper;
import org.cxyxh.vhr.mapper.NationMapper;
import org.cxyxh.vhr.model.HrRole;
import org.cxyxh.vhr.model.Nation;
import org.cxyxh.vhr.service.IHrRoleService;
import org.cxyxh.vhr.service.INationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ： cxyxh
 * @date : 2022/8/31 1:03
 * @describetion :
 */
@Service
public class NationServiceImpl extends ServiceImpl<NationMapper, Nation> implements INationService {

	@Autowired
	NationMapper nationMapper;

	@Override
	public List<Nation> getAllNations() {
		QueryWrapper<Nation> wrapper = new QueryWrapper<>();
		return nationMapper.selectList(wrapper);
	}
}
