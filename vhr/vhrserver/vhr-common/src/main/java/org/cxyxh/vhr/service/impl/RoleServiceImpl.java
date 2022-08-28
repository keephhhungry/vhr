package org.cxyxh.vhr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.cxyxh.vhr.mapper.MenuRoleMapper;
import org.cxyxh.vhr.mapper.RoleMapper;
import org.cxyxh.vhr.model.MenuRole;
import org.cxyxh.vhr.model.Role;
import org.cxyxh.vhr.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.RowSetEvent;
import java.util.List;

/**
 * @author ： cxyxh
 * @date : 2022/8/28 9:25
 * @describetion :
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

	@Autowired
	RoleMapper roleMapper;

	@Autowired
	MenuRoleMapper menuRoleMapper;

	@Override
	public List<Role> getAllRoles() {
		QueryWrapper<Role> wrapper = new QueryWrapper<Role>();
		return roleMapper.selectList(wrapper);
	}

	@Override
	public Integer addRole(Role role) {
		if (!role.getName().startsWith("ROLE_")) {
			role.setName("ROLE_" + role.getName());
		}
		return roleMapper.insert(role);
	}

	@Transactional
	@Override
	public Integer deleteRole(Integer roleId) {
		QueryWrapper<MenuRole> wrapper = new QueryWrapper<MenuRole>();
		wrapper.eq("rid",roleId);
		menuRoleMapper.delete(wrapper);

		return roleMapper.deleteById(roleId);
	}
}
