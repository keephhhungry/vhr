package org.cxyxh.vhr.utils;

import org.cxyxh.vhr.model.Hr;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author ： cxyxh
 * @date : 2022/8/18 22:29
 * @describetion :
 */
public class UserUtils {

   private final static Logger logger = LoggerFactory.getLogger(UserUtils.class);

   /**
    * 获取登录用户的信息
    * @return
    */
   public static Hr getCurrentUser() {
      Hr user = null;
      try {
         user = (Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      } catch (Exception e) {
         //logger.warn("获取登录用户的信息失败，用户可能未登录");
      }
      return user;
   }
}
