package org.cxyxh.vhr.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@TableName("menu")
public class Menu implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("url")
    private String url;

    @TableField("path")
    private String path;

    @TableField("component")
    private String component;

    @TableField("name")
    private String name;

    @TableField("icon_cls")
    private String iconCls;

    @TableField(exist = false)
    private Meta meta;

    @TableField("parent_id")
    private Integer parentId;

    @TableField("enabled")
    private Boolean enabled;

    @TableField(exist = false)
    private List<Menu> children;

    @TableField(exist = false)
    private List<Role> roles;

}