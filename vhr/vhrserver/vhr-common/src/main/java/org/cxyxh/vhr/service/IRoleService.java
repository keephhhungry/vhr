package org.cxyxh.vhr.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cxyxh.vhr.model.JobLevel;
import org.cxyxh.vhr.model.Menu;
import org.cxyxh.vhr.model.Role;

import java.util.List;

/**
 * @author ： cxyxh
 * @date : 2022/8/28 9:24
 * @describetion :
 */
public interface IRoleService extends IService<Role> {

	List<Role> getAllRoles();

	Integer addRole(Role role);

	Integer deleteRole(Integer roleId);
}
