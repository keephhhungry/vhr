package org.cxyxh.vhr.controller.system.basic;

import org.cxyxh.vhr.constant.Const;
import org.cxyxh.vhr.model.Position;
import org.cxyxh.vhr.model.RespBean;
import org.cxyxh.vhr.service.IPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ： cxyxh
 * @date : 2022/8/27 17:16
 * @describetion :
 */
@RestController
@RequestMapping("/system/basic/pos")
public class PositionController {

	@Autowired
	IPositionService positionService;

	@GetMapping("/")
	public List<Position> getAllPositions() {
		return positionService.getAllPositions();
	}


	@PostMapping("/")
	public RespBean addPosition(@RequestBody Position position) {
		if (positionService.addPosition(position) == 1) {
			return RespBean.ok(Const.ADD_SUCCESS);
		}
		return RespBean.error(Const.ADD_FAIL);
	}

	@PutMapping("/")
	public RespBean updatePosition(@RequestBody Position position) {
		if (positionService.updatePosition(position) == 1) {
			return RespBean.ok(Const.UPDATE_SUCCESS);
		}
		return RespBean.error(Const.UPDATE_FAIL);
	}

	@DeleteMapping("/{id}")
	public RespBean deletePositionById(@PathVariable Integer id){
		if (positionService.deletePositionById(id) == 1) {
			return RespBean.ok(Const.DELETE_SUCCESS);
		}
		return RespBean.error(Const.DELETE_FAIL);
	}

	@DeleteMapping("/")
	public RespBean deletePositionByIds(Integer[] ids){
		if(positionService.deletePositionByIds(ids) == ids.length){
			return RespBean.ok(Const.DELETE_SUCCESS);
		}
		return RespBean.ok(Const.DELETE_FAIL);
	}
}
