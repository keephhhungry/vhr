package org.cxyxh.vhr.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cxyxh.vhr.model.Menu;

import java.util.List;

/**
 * @author ： cxyxh
 * @date : 2022/8/25 9:00
 * @describetion :
 */

public interface IMenuService extends IService<Menu> {

	List<Menu> getMenusByHrId();
}
