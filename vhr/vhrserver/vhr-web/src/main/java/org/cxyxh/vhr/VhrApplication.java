package org.cxyxh.vhr;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

/**
 * @author ： cxyxh
 * @date : 2022/8/24 22:05
 * @describetion :
 */
@SpringBootApplication
//@EnableCaching
@MapperScan(basePackages = "org.cxyxh.vhr.mapper")
@ComponentScan(basePackages = "org.cxyxh")
//@ComponentScans("org.cxyxh.vhr")
public class VhrApplication {

	public static void main(String[] args){
		SpringApplication.run(VhrApplication.class, args);
	}
}
