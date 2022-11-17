package org.cxyxh.vhr.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.cxyxh.vhr.model.Role;
import org.cxyxh.vhr.model.Salary;

import java.util.List;

/**
 * @author ： cxyxh
 * @date : 2022/8/24 22:09
 * @describetion :
 */
@Mapper
public interface SalaryMapper extends BaseMapper<Salary> {

}
