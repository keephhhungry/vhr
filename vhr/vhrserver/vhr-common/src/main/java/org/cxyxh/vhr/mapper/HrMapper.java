package org.cxyxh.vhr.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.cxyxh.vhr.model.Hr;
import org.cxyxh.vhr.model.Role;

import java.util.List;

/**
 * @author ： cxyxh
 * @date : 2022/8/25 9:07
 * @describetion :
 */
@Mapper
public interface HrMapper extends BaseMapper<Hr> {

	//List<Role> getHrRolesById(Integer id);

	List<Hr> getAllHrs(@Param("hrid") Integer hrId, @Param("keywords") String keywords);

	//List<Hr> getAllHrsExceptCurrentHr(Integer id);
	//
	//Integer updatePasswd(@Param("hrId") Integer hrId, @Param("encodePass") String encodePass);
	//
	//Integer updateUserface(@Param("url") String url, @Param("id") Integer id);

}
