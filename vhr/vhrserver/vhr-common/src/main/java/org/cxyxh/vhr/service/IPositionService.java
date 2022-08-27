package org.cxyxh.vhr.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cxyxh.vhr.model.Position;

import java.util.List;

/**
 * @author ： cxyxh
 * @date : 2022/8/27 17:18
 * @describetion :
 */
public interface IPositionService extends IService<Position> {

	List<Position> getAllPositions();

	Integer addPosition(Position position);

	Integer updatePosition(Position position);

	Integer deletePositionById(Integer id);

	Integer deletePositionByIds(Integer[] ids);
}
