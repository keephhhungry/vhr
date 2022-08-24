package org.cxyxh.vhr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.cxyxh.vhr.mapper.HrMapper;
import org.cxyxh.vhr.model.Hr;
import org.cxyxh.vhr.service.IHrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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

	@Override
	public UserDetails loadUserByUsername(String username) {
		//查询用户信息
		QueryWrapper<Hr> wrapper = new QueryWrapper<>();
		wrapper.eq("username", username);
		wrapper.last("limit 1");
		Hr hr = hrMapper.selectOne(wrapper);
		String encode = new BCryptPasswordEncoder().encode("123");
		System.out.println("密码："+encode);
		//hr.setPassword(encode);
		if (Objects.isNull(hr)){
			throw new UsernameNotFoundException("用户名不存在");
		}
		return hr;
	}
}
