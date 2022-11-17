package org.cxyxh.vhr.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("hr_role")
public class HrRole {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("hrid")
    private Integer hrid;

    @TableField("rid")
    private Integer rid;

}