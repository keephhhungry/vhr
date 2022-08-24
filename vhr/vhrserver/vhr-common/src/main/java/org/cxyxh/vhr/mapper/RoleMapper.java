package org.cxyxh.vhr.mapper;

import org.cxyxh.vhr.model.Role;

import java.util.List;

/**
 * @author ： cxyxh
 * @date : 2022/8/24 22:09
 * @describetion :
 */
public interface RoleMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(Role record);

	int insertSelective(Role record);

	Role selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Role record);

	int updateByPrimaryKey(Role record);

	List<Role> getAllRoles();
}
