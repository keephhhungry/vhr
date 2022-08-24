package org.cxyxh.vhr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author ： cxyxh
 * @date : 2022/8/14 9:40
 * @describetion :
 */
@Configuration
@EnableOpenApi
@EnableWebMvc
public class SwaggerConfig {

	/**
	 * 创建该API的基本信息（这些基本信息会展现在文档页面中）
	 * 访问地址：http://ip:port/swagger-ui.html
	 *
	 * @return
	 */
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("5101Club日常管理系统API——Gateway")
				.description("更多请关注: https://blog.csdn.net/xqnode")
				.termsOfServiceUrl("https://blog.csdn.net/xqnode")
				.contact(new Contact("xqnode", "https://blog.csdn.net/xqnode", "xiaqingweb@163.com"))
				.version("1.0")
				.build();
	}

	@Bean
	public Docket docket() {
		return new Docket(DocumentationType.OAS_30)
				.apiInfo(apiInfo())
				.select()
				// 配置swagger显示的controller，如果不配置则默认扫描所有后端接口
				.apis(RequestHandlerSelectors.basePackage("com.cxyxh"))
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build();
	}

}
