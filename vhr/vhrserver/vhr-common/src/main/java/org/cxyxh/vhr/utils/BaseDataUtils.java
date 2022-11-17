package org.cxyxh.vhr.utils;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.cxyxh.vhr.model.Nation;
import org.cxyxh.vhr.model.PoliticsStatus;
import org.cxyxh.vhr.service.INationService;
import org.cxyxh.vhr.service.IPoliticsStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author ： cxyxh
 * @date : 2022/9/4 16:11
 * @describetion :
 */
@Component
public class BaseDataUtils {

	@Autowired
	INationService nationService;

	@Autowired
	IPoliticsStatusService politicsStatusService;

	private List<Nation> nationList = new ArrayList<>();

	private Map<String,Nation> nationMap = new HashMap<>();

	private List<PoliticsStatus> politicsStatusList = new ArrayList<>();

	private Map<String,PoliticsStatus> politicsStatusMap = new HashMap<>();

	@PostConstruct
	public List<Nation> initNationList() {
		if(CollectionUtils.isEmpty(nationList)){
			nationList = nationService.getAllNations();
		}
		return nationList;
	}

	@PostConstruct
	public Map<String,Nation> initNationMap(){
		if(MapUtils.isEmpty(nationMap)){
			List<Nation> nationList = initNationList();
			nationMap = nationList.stream().collect(Collectors.toMap(Nation::getName, Function.identity()));
		}
		return nationMap;
	}

	@PostConstruct
	public List<PoliticsStatus> initPoliticsStatusList() {
		if(CollectionUtils.isEmpty(politicsStatusList)){
			politicsStatusList = politicsStatusService.getAllPoliticsStatus();
		}
		return politicsStatusList;
	}

	@PostConstruct
	public Map<String,PoliticsStatus> initPoliticsStatusMap() {
		if(MapUtils.isEmpty(politicsStatusMap)){
			List<PoliticsStatus> politicsStatusList = initPoliticsStatusList();
			politicsStatusMap =  politicsStatusList.stream().collect(Collectors.toMap(PoliticsStatus::getName, Function.identity()));
		}
		return politicsStatusMap;
	}


	public List<Nation> getNationList() {
		return nationList;
	}

	public List<PoliticsStatus> getPoliticsStatusList() {
		return politicsStatusList;
	}

	public Map<String, Nation> getNationMap() {
		return nationMap;
	}

	public Map<String, PoliticsStatus> getPoliticsStatusMap() {
		return politicsStatusMap;
	}
}
