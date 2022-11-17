package org.cxyxh.vhr.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("salary")
public class Salary implements Serializable {

    @TableId(value = "id" ,type = IdType.AUTO)
    private Integer id;

    @TableField("basic_salary")
    private Integer basicSalary;

    @TableField("bonus")
    private Integer bonus;

    @TableField("lunch_salary")
    private Integer lunchSalary;

    @TableField("traffic_salary")
    private Integer trafficSalary;

    @TableField("all_salary")
    private Integer allSalary;

    @TableField("pension_base")
    private Integer pensionBase;

    @TableField("pension_per")
    private Float pensionPer;

    @TableField("create_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createDate;

    @TableField("medical_base")
    private Integer medicalBase;

    @TableField("medical_per")
    private Float medicalPer;

    @TableField("accumulation_fund_base")
    private Integer accumulationFundBase;

    @TableField("accumulation_fund_per")
    private Float accumulationFundPer;

    @TableField("name")
    private String name;

}