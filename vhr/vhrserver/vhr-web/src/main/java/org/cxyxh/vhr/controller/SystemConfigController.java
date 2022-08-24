package org.cxyxh.vhr.controller;

import org.cxyxh.vhr.model.Menu;
import org.cxyxh.vhr.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ： cxyxh
 * @date : 2022/8/25 8:57
 * @describetion :
 */
@RestController
@RequestMapping("/system/config")
public class SystemConfigController {

	@Autowired
	IMenuService menuService;

	@GetMapping("/menu")
	public List<Menu> getMenusByHrId(){
		List<Menu> menuList = menuService.getMenusByHrId();
		for (Menu menu : menuList) {
			System.out.println(menu.toString());
		}
		return menuList;
	}
}
