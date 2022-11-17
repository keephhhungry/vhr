package org.cxyxh.vhr.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.models.auth.In;
import org.cxyxh.vhr.model.Employee;
import org.cxyxh.vhr.model.Nation;
import org.cxyxh.vhr.model.RespPageBean;

import java.util.List;

/**
 * @author ： cxyxh
 * @date : 2022/8/28 9:24
 * @describetion :
 */
public interface INationService extends IService<Nation> {

	List<Nation> getAllNations();
}
