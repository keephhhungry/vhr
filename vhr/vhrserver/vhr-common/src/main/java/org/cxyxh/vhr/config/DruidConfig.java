package org.cxyxh.vhr.config;

//import jakarta.servlet.Filter;
//import jakarta.servlet.Servlet;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ： cxyxh
 * @date : 2022/8/14 1:14
 * @describetion :
 */
@Configuration
public class DruidConfig {
	@ConfigurationProperties(prefix = "spring.datasource")
	@Bean
	public DataSource druidDataSource() {
		return new DruidDataSource();
	}

	@Bean
	public ServletRegistrationBean statViewServlet(){
		ServletRegistrationBean bean =
				new ServletRegistrationBean(new StatViewServlet(), "/druid/*");

		Map<String,String> initParams = new HashMap<>();

		initParams.put("loginUsername","admin");
		initParams.put("loginPassword","123456");
		//initParams.put("allow","");//默认就是允许所有访问
		initParams.put("allow", "127.0.0.1");//表示只有本机可以访问
		initParams.put("deny","");
		//设置初始化参数
		bean.setInitParameters(initParams);
		return bean;
	}

	@Bean
	public FilterRegistrationBean webStatFilter(){
		FilterRegistrationBean bean = new FilterRegistrationBean();
		bean.setFilter(new WebStatFilter());
		//exclusions：设置哪些请求进行过滤排除掉，从而不进行统计
		Map<String,String> initParams = new HashMap<>();
		initParams.put("exclusions","*.js,*.css,/druid/*");
		bean.setInitParameters(initParams);
		//"/*" 表示过滤所有请求
		bean.setUrlPatterns(Arrays.asList("/*"));
		return  bean;
	}
}
