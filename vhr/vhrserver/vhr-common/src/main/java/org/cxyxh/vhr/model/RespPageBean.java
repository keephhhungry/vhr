package org.cxyxh.vhr.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @作者 江南一点雨
 * @公众号 江南一点雨
 * @微信号 a_java_boy
 * @GitHub https://github.com/lenve
 * @博客 http://wangsong.blog.csdn.net
 * @网站 http://www.javaboy.org
 * @时间 2019-10-29 7:42
 */
@Data
@AllArgsConstructor
public class RespPageBean {

    private Long total;

    private List<?> data;

}
