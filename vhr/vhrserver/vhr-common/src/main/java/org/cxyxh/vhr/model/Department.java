package org.cxyxh.vhr.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Data
@TableName("department")
public class Department implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("name")
    private String name;

    @TableField("parent_id")
    private Integer parentId;

    @TableField("dep_path")
    private String depPath;

    @TableField("enabled")
    private Boolean enabled;

    @TableField("is_parent")
    private Boolean isParent;

    @TableField(exist = false)
    private List<Department> children = new ArrayList<>();

    @TableField(exist = false)
    private Integer result;

    public Department() {
    }

    public Department(String name) {

        this.name = name;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }

}