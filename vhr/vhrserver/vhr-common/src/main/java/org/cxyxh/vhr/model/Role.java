package org.cxyxh.vhr.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
@Data
@TableName("role")
public class Role implements Serializable {

    private Integer id;

    private String name;

    private String nameZh;

}