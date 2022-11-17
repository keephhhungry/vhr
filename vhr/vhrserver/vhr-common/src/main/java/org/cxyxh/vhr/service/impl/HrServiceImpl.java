package org.cxyxh.vhr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.cxyxh.vhr.mapper.HrMapper;
import org.cxyxh.vhr.mapper.HrRoleMapper;
import org.cxyxh.vhr.mapper.RoleMapper;
import org.cxyxh.vhr.model.Hr;
import org.cxyxh.vhr.model.HrRole;
import org.cxyxh.vhr.model.Role;
import org.cxyxh.vhr.service.IHrRoleService;
import org.cxyxh.vhr.service.IHrService;
import org.cxyxh.vhr.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author ： cxyxh
 * @date : 2022/8/25 9:08
 * @describetion :
 */
@Service
public class HrServiceImpl extends ServiceImpl<HrMapper, Hr> implements IHrService, UserDetailsService {

	@Autowired
	HrMapper hrMapper;

	@Autowired
	RoleMapper roleMapper;

	@Autowired
	HrRoleMapper hrRoleMapper;

	@Autowired
	IHrRoleService hrRoleService;

	@Override
	public UserDetails loadUserByUsername(String username) {
		//查询用户信息
		QueryWrapper<Hr> wrapper = new QueryWrapper<>();
		wrapper.eq("username", username);
		wrapper.last("limit 1");
		Hr hr = hrMapper.selectOne(wrapper);
		if (Objects.isNull(hr)){
			throw new UsernameNotFoundException("用户名不存在");
		}
		hr.setRoles(roleMapper.getHrRolesById(hr.getId()));
		return hr;
	}

	@Override
	public List<Hr> getAllHrs(String keywords) {
		return hrMapper.getAllHrs(UserUtils.getCurrentUser().getId(),keywords);
	}

	@Override
	public Boolean updateHrRole(Integer hrId, Integer[] rids) {
		QueryWrapper<HrRole> hrRoleQueryWrapper = new QueryWrapper<>();
		hrRoleQueryWrapper.eq("hrid",hrId);
		hrRoleMapper.delete(hrRoleQueryWrapper);

		ArrayList<HrRole> hrRoles = new ArrayList<>();
		for (Integer rid : rids) {
			HrRole hrRole = new HrRole();
			hrRole.setHrid(hrId);
			hrRole.setRid(rid);
			hrRoles.add(hrRole);
		}
		return hrRoleService.saveBatch(hrRoles);
	}

	@Override
	public Integer updateHr(Hr hr) {
		return hrMapper.updateById(hr);
	}

	@Override
	public Integer deleteHrById(Integer id) {
		return hrMapper.deleteById(id);
	}
}
