package org.cxyxh.vhr.config;

import org.cxyxh.vhr.model.Menu;
import org.cxyxh.vhr.model.Role;
import org.cxyxh.vhr.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * @author ： cxyxh
 * @date : 2022/8/26 9:40
 * @describetion : 这个类的作用，主要是根据用户传来的请求地址，分析出请求需要的角色
 */
@Configuration
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

	@Autowired
	IMenuService menuService;

	AntPathMatcher antPathMatcher = new AntPathMatcher();

	/**
	 * 这个类主要是根据request url，通过和数据库进行匹配，拿到这个url所需要的角色
	 * @param object
	 * @return
	 * @throws IllegalArgumentException
	 */
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		String requestUrl = ((FilterInvocation) object).getRequestUrl();
		List<Menu> menus = menuService.getAllMenusWithRole();
		for (Menu menu : menus) {
			if (antPathMatcher.match(menu.getUrl(), requestUrl)) {
				List<Role> roles = menu.getRoles();
				String[] str = new String[roles.size()];
				for (int i = 0; i < roles.size(); i++) {
					str[i] = roles.get(i).getName();
				}
				return SecurityConfig.createList(str);
			}
		}
		return SecurityConfig.createList("ROLE_LOGIN");
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}
}
