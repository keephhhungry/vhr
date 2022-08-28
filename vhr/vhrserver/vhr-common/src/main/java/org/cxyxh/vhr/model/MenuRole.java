package org.cxyxh.vhr.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("menu_role")
public class MenuRole {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("mid")
    private Integer mid;

    @TableField("rid")
    private Integer rid;

}