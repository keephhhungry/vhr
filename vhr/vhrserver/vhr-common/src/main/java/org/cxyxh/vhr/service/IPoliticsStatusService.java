package org.cxyxh.vhr.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cxyxh.vhr.model.Nation;
import org.cxyxh.vhr.model.PoliticsStatus;

import java.util.List;

/**
 * @author ： cxyxh
 * @date : 2022/8/28 9:24
 * @describetion :
 */
public interface IPoliticsStatusService extends IService<PoliticsStatus> {

	List<PoliticsStatus> getAllPoliticsStatus();
}
