package org.cxyxh.vhr.exception;

import org.cxyxh.vhr.model.RespBean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @author ： cxyxh
 * @date : 2022/8/27 20:02
 * @describetion :
 */
@RestControllerAdvice
public class GlobalExceptionHandle {

	@ExceptionHandler(SQLException.class)
	public RespBean sqlException(SQLException e){
		if(e instanceof SQLIntegrityConstraintViolationException){
			return RespBean.error("该数据有关联数据，操作失败!");
		}
		return RespBean.error("数据库异常，操作失败!");
	}
}
