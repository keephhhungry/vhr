package org.cxyxh.vhr.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cxyxh.vhr.model.MenuRole;
import org.cxyxh.vhr.model.Role;

import java.util.List;

/**
 * @author ： cxyxh
 * @date : 2022/8/28 9:24
 * @describetion :
 */
public interface IMenusRoleService extends IService<MenuRole> {

	List<Integer> getMenuIdsByRoleId(Integer roleId);

	Boolean updateMenuRole(Integer roleId, Integer[] menuIds);
}
