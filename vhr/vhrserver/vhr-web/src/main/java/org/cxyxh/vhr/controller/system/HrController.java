package org.cxyxh.vhr.controller.system;

import org.cxyxh.vhr.constant.Const;
import org.cxyxh.vhr.model.Hr;
import org.cxyxh.vhr.model.RespBean;
import org.cxyxh.vhr.model.Role;
import org.cxyxh.vhr.service.IHrService;
import org.cxyxh.vhr.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ： cxyxh
 * @date : 2022/8/30 20:37
 * @describetion :
 */
@RestController
@RequestMapping("/system/hr")
public class HrController {

	@Autowired
	IHrService hrService;

	@Autowired
	IRoleService roleService;
	
	@GetMapping("/")
	public List<Hr> getAllHrs(String keywords) {
		return hrService.getAllHrs(keywords);
	}

	@PutMapping("/")
	public RespBean updateHr(@RequestBody Hr hr) {
		if (hrService.updateHr(hr) == 1) {
			return RespBean.ok(Const.UPDATE_SUCCESS);
		}
		return RespBean.error(Const.UPDATE_FAIL);
	}
	@GetMapping("/roles")
	public List<Role> getAllRoles() {
		return roleService.getAllRoles();
	}

	@PutMapping("/role")
	public RespBean updateHrRole(Integer hrid, Integer[] rids) {
		if (hrService.updateHrRole(hrid, rids)) {
			return RespBean.ok(Const.UPDATE_SUCCESS);
		}
		return RespBean.error(Const.UPDATE_FAIL);
	}

	@DeleteMapping("/{id}")
	public RespBean deleteHrById(@PathVariable Integer id) {
		if (hrService.deleteHrById(id) == 1) {
			return RespBean.ok(Const.DELETE_SUCCESS);
		}
		return RespBean.error(Const.DELETE_FAIL);
	}
}
