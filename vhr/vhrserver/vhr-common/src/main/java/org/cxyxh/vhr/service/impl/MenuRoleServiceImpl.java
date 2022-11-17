package org.cxyxh.vhr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.cxyxh.vhr.mapper.MenuMapper;
import org.cxyxh.vhr.mapper.MenuRoleMapper;
import org.cxyxh.vhr.mapper.RoleMapper;
import org.cxyxh.vhr.model.MenuRole;
import org.cxyxh.vhr.model.Role;
import org.cxyxh.vhr.service.IMenusRoleService;
import org.cxyxh.vhr.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ： cxyxh
 * @date : 2022/8/28 9:25
 * @describetion :
 */
@Service
public class MenuRoleServiceImpl extends ServiceImpl<MenuRoleMapper, MenuRole> implements IMenusRoleService {

	@Autowired
	MenuRoleMapper menuRoleMapper;

	@Override
	public List<Integer> getMenuIdsByRoleId(Integer roleId) {
		QueryWrapper<MenuRole> wrapper = new QueryWrapper<>();
		wrapper.eq("rid", roleId);
		List<MenuRole> menuRoles = menuRoleMapper.selectList(wrapper);
		if(CollectionUtils.isNotEmpty(menuRoles)){
			return menuRoles.stream().map(MenuRole::getMid).collect(Collectors.toList());
		}
		return new ArrayList<>();
	}

	@Transactional
	@Override
	public Boolean updateMenuRole(Integer roleId, Integer[] menuIds) {
		QueryWrapper<MenuRole> wrapper = new QueryWrapper<>();
		wrapper.eq("rid",roleId);
		menuRoleMapper.delete(wrapper);

		List<MenuRole> menuRoles = new ArrayList<>();
		if(ObjectUtils.isNotEmpty(menuIds)){
			for (Integer menuId : menuIds) {
				MenuRole menuRole = new MenuRole();
				menuRole.setRid(roleId);
				menuRole.setMid(menuId);
				menuRoles.add(menuRole);
			}
			return this.saveBatch(menuRoles);
		}

		return Boolean.TRUE;
	}
}
