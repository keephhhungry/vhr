package org.cxyxh.vhr.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Data
@TableName("job_level")
public class JobLevel implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("name")
    private String name;

    @TableField("title_level")
    private String titleLevel;

    @TableField("create_date")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private Date createDate;

    @TableField("enabled")
    private Boolean enabled;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobLevel jobLevel = (JobLevel) o;
        return Objects.equals(name, jobLevel.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public JobLevel() {
    }

    public JobLevel(String name) {
        this.name = name;
    }

}