package org.cxyxh.vhr.controller.system.basic;

import org.cxyxh.vhr.constant.Const;
import org.cxyxh.vhr.model.Menu;
import org.cxyxh.vhr.model.RespBean;
import org.cxyxh.vhr.model.Role;
import org.cxyxh.vhr.service.IMenuService;
import org.cxyxh.vhr.service.IMenusRoleService;
import org.cxyxh.vhr.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ： cxyxh
 * @date : 2022/8/28 9:23
 * @describetion :
 */
@RestController
@RequestMapping("/system/basic/permiss")
public class PermissController {

	@Autowired
	IRoleService roleService;

	@Autowired
	IMenuService menuService;

	@Autowired
	IMenusRoleService menusRoleService;

	@GetMapping("/")
	public List<Role> getAllRoles(){
		return roleService.getAllRoles();
	}

	@GetMapping("/menus")
	public List<Menu> getAllMenus(){
		return menuService.getAllMenus();
	}

	@GetMapping("/menuIds/{roleId}")
	public List<Integer> getMenuIdsByRoleId(@PathVariable Integer roleId){
		return menusRoleService.getMenuIdsByRoleId(roleId);
	}

	@PutMapping("/")
	public RespBean updateMenuRole(Integer roleId, Integer[] menuIds){
		if(menusRoleService.updateMenuRole(roleId,menuIds)){
			return RespBean.ok(Const.UPDATE_SUCCESS);
		}
		return RespBean.error(Const.UPDATE_FAIL);
	}

	@PostMapping("/role")
	public RespBean addRole(@RequestBody Role role){
		if(roleService.addRole(role) == 1){
			return RespBean.ok(Const.ADD_SUCCESS);
		}
		return RespBean.error(Const.ADD_FAIL);
	}

	@DeleteMapping("/role/{roleId}")
	public RespBean deleteRole(@PathVariable Integer roleId){
		if(roleService.deleteRole(roleId) == 1){
			return RespBean.ok(Const.DELETE_SUCCESS);
		}
		return RespBean.error(Const.DELETE_FAIL);
	}

}
