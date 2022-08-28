package org.cxyxh.vhr.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.cxyxh.vhr.model.Department;
import org.cxyxh.vhr.model.Hr;

/**
 * @author ： cxyxh
 * @date : 2022/8/25 9:07
 * @describetion :
 */
@Mapper
public interface DepartmentMapper extends BaseMapper<Department> {

	//Hr loadUserByUsername(String username);

}
