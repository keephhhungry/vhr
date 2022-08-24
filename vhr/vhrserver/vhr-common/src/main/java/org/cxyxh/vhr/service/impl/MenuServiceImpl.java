package org.cxyxh.vhr.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.cxyxh.vhr.mapper.MenuMapper;
import org.cxyxh.vhr.model.Hr;
import org.cxyxh.vhr.model.Menu;
import org.cxyxh.vhr.service.IMenuService;
import org.cxyxh.vhr.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ： cxyxh
 * @date : 2022/8/25 9:00
 * @describetion :
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

	@Autowired
	MenuMapper menuMapper;

	@Override
	public List<Menu> getMenusByHrId() {
		Hr user = UserUtils.getCurrentUser();
		return menuMapper.getMenusByHrId(user.getId());
	}
}
