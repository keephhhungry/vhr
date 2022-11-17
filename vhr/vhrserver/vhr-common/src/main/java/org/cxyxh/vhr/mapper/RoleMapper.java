package org.cxyxh.vhr.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.cxyxh.vhr.model.Menu;
import org.cxyxh.vhr.model.Role;

import java.util.List;

/**
 * @author ： cxyxh
 * @date : 2022/8/24 22:09
 * @describetion :
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

	//int deleteByPrimaryKey(Integer id);
	//
	//int insert(Role record);
	//
	//int insertSelective(Role record);
	//
	//Role selectByPrimaryKey(Integer id);
	//
	//int updateByPrimaryKeySelective(Role record);
	//
	//int updateByPrimaryKey(Role record);
	//
	//List<Role> getAllRoles();

	List<Role> getHrRolesById(Integer id);
}
