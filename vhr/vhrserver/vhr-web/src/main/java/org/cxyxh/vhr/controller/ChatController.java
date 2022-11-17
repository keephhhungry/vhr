package org.cxyxh.vhr.controller;

import org.cxyxh.vhr.model.Hr;
import org.cxyxh.vhr.service.IHrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ： cxyxh
 * @date : 2022/9/24 11:09
 * @describetion :
 */
@RestController
@RequestMapping("/chat")
public class ChatController {

	@Autowired
	IHrService hrService;

	@GetMapping("/hrs")
	public List<Hr> getAllHrs(){
		return hrService.getAllHrs(null);
	}

}
