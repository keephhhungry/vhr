package org.cxyxh.vhr.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.cxyxh.vhr.model.Menu;

import java.util.List;

/**
 * @author ： cxyxh
 * @date : 2022/8/25 9:01
 * @describetion :
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

	List<Menu> getAllMenus();

	// 查询某个用户的全部菜单
	List<Menu> getMenusByHrId(Integer id);

	// 查询某个角色的全部菜单
	List<Menu> getAllMenusWithRole();
}
