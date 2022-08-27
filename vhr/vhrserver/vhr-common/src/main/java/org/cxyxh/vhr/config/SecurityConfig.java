package org.cxyxh.vhr.config;

import org.cxyxh.vhr.model.Hr;
import org.cxyxh.vhr.model.RespBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.cxyxh.vhr.service.IHrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author ： cxyxh
 * @date : 2022/8/17 22:19
 * @describetion :
 */
@Configuration
public class SecurityConfig {

	@Autowired
	IHrService hrService;

	@Autowired
	CustomFilterInvocationSecurityMetadataSource customFilterInvocationSecurityMetadataSource;

	@Autowired
	CustomUrlDecisionManager customUrlDecisionManager;

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encode = encoder.encode("123");
		System.out.println(encode);
	}

	//@Bean
	//UserDetailsService userDetailsService() {
	//	System.out.println("55555");
	//	InMemoryUserDetailsManager users = new InMemoryUserDetailsManager();
	//	users.createUser(org.springframework.security.core.userdetails.User.withUsername("admin").password("{noop}123").roles("admin").build());
	//	users.createUser(org.springframework.security.core.userdetails.User.withUsername("user").password("{noop}123").roles("admin").build());
	//
	//	return users;
	//}

	@Bean
	WebSecurityCustomizer webSecurityCustomizer() {
		System.out.println("444444");
		return new WebSecurityCustomizer() {
			@Override
			public void customize(WebSecurity web) {
				web.ignoring().antMatchers("/v2/api-docs",
						"/swagger-resources",
						"/swagger-resources/**",
						"/configuration/ui",
						"/configuration/security",
						"/swagger-ui.html/**",
						"/webjars/**",//前面是swagger
						"/",
						"/login",
						"/verifyCode",
						"/js/**",
						"/css/**",
						"/images/**",
						"/tinymce/**",
						"/fonts/**",
						"/index.html",
						"/favicon.ico");
			}
		};
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
					@Override
					public <O extends FilterSecurityInterceptor> O postProcess(O object) {
						object.setAccessDecisionManager(customUrlDecisionManager);
						object.setSecurityMetadataSource(customFilterInvocationSecurityMetadataSource);
						return object;
					}
				})
				.and()
				.formLogin()
				.usernameParameter("username")
				.passwordParameter("password")
				.loginProcessingUrl("/doLogin")
				.loginPage("/login")
				.successHandler(new AuthenticationSuccessHandler() {
					@Override
					public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
						response.setContentType("application/json;charset=utf-8");
						PrintWriter out = response.getWriter();
						Hr user = (Hr) authentication.getPrincipal();
						user.setPassword(null);
						RespBean resp = RespBean.ok("登录成功", user);
						String s = new ObjectMapper().writeValueAsString(resp);
						out.write(s);
						out.flush();
						out.close();
					}
				})
				.failureHandler(new AuthenticationFailureHandler() {
					@Override
					public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
						response.setContentType("application/json;charset=utf-8");
						PrintWriter out = response.getWriter();
						RespBean error = RespBean.error("登录失败!!!");
						if (exception instanceof LockedException) {
							error.setMsg("账号被锁定，请联系管理员");
						} else if (exception instanceof CredentialsExpiredException) {
							error.setMsg("密码过期，请联系管理员");
						} else if (exception instanceof AccountExpiredException) {
							error.setMsg("账户过期，请联系管理员");
						} else if (exception instanceof DisabledException) {
							error.setMsg("账户被禁用，请联系管理员");
						} else if (exception instanceof BadCredentialsException) {
							error.setMsg("用户名或者密码输入错误");
						} else {
							System.out.println(exception.getMessage());
						}
						out.write(new ObjectMapper().writeValueAsString(error));
						out.flush();
						out.close();
					}
				})
				.permitAll()
				.and()
				.logout()
				.logoutSuccessHandler(new LogoutSuccessHandler() {
					@Override
					public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
						response.setContentType("application/json;charset=utf-8");
						PrintWriter out = response.getWriter();
						out.write(new ObjectMapper().writeValueAsString(RespBean.ok("注销成功!")));
						out.flush();
						out.close();
					}
				})
				.permitAll()
				.and()
				.csrf().disable().exceptionHandling()
				//没有认证时，在这里处理结果，不要重定向
				.authenticationEntryPoint((req, resp, authException) -> {
							resp.setContentType("application/json;charset=utf-8");
							resp.setStatus(401);
							PrintWriter out = resp.getWriter();
							RespBean respBean = RespBean.error("访问失败!");
							if (authException instanceof InsufficientAuthenticationException) {
								respBean.setMsg("请求失败，请联系管理员!");
							}
							out.write(new ObjectMapper().writeValueAsString(respBean));
							out.flush();
							out.close();
						}
				);
		return http.build();
	}

}
