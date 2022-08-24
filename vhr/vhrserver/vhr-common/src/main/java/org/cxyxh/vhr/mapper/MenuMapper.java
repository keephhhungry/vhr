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

	List<Menu> getMenusByHrId(Integer id);
}
