package org.cxyxh.vhr.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cxyxh.vhr.model.Hr;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * @author ： cxyxh
 * @date : 2022/8/25 9:08
 * @describetion :
 */
public interface IHrService extends IService<Hr> {

	List<Hr> getAllHrs(String keywords);

	Boolean updateHrRole(Integer hrId, Integer[] rids);

	Integer updateHr(Hr hr);

	Integer deleteHrById(Integer id);
}
