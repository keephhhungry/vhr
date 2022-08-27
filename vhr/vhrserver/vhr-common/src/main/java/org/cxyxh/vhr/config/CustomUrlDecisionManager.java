package org.cxyxh.vhr.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author ： cxyxh
 * @date : 2022/8/27 12:22
 * @describetion :
 */
@Configuration
public class CustomUrlDecisionManager implements AccessDecisionManager {
	@Override
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
		// 获取当前登录用户的角色
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

		for (ConfigAttribute configAttribute : configAttributes) {
			String needRole = configAttribute.getAttribute();
			if ("ROLE_LOGIN".equals(needRole)) {
				if(authentication instanceof AnonymousAuthenticationToken){
					throw new AccessDeniedException("尚未登录，请登录！");
				}else{
					return;
				}
			}

			for (GrantedAuthority authority : authorities) {
				if (authority.getAuthority().equals(needRole)) {
					return;
				}
			}
		}
		throw new AccessDeniedException("权限不足，请联系管理员！");
	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}
}
