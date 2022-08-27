package org.cxyxh.vhr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.cxyxh.vhr.mapper.PositionMapper;
import org.cxyxh.vhr.model.Position;
import org.cxyxh.vhr.service.IPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author ： cxyxh
 * @date : 2022/8/27 17:19
 * @describetion :
 */
@Service
public class PositionServiceImpl extends ServiceImpl<PositionMapper, Position> implements IPositionService {

	@Autowired
	PositionMapper positionMapper;

	@Override
	public List<Position> getAllPositions() {
		QueryWrapper<Position> wrapper = new QueryWrapper<>();
		List<Position> positions = positionMapper.selectList(wrapper);
		return positions;
	}

	@Override
	public Integer addPosition(Position position) {
		position.setEnabled(true);
		position.setCreateDate(new Date());
		return positionMapper.insert(position);
	}

	@Override
	public Integer updatePosition(Position position) {
		return positionMapper.updateById(position);
	}

	@Override
	public Integer deletePositionById(Integer id) {
		return positionMapper.deleteById(id);
	}

	@Override
	public Integer deletePositionByIds(Integer[] ids) {
		return positionMapper.deleteBatchIds(Arrays.asList(ids));
	}

}
