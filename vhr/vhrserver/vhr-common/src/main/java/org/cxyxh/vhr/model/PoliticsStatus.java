package org.cxyxh.vhr.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
@TableName("politics_status")
public class PoliticsStatus implements Serializable {

    @TableId(value = "id" , type = IdType.AUTO)
    private Integer id;

    @TableField("name")
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PoliticsStatus that = (PoliticsStatus) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }

    public PoliticsStatus() {

    }

    public PoliticsStatus(String name) {

        this.name = name;
    }

}