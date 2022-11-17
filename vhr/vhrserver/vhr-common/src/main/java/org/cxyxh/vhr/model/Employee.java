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
@TableName("employee")
public class Employee implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("name")
    private String name;

    @TableField("gender")
    private String gender;

    @TableField("birthday")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private Date birthday;

    @TableField("id_card")
    private String idCard;

    @TableField("wedlock")
    private String wedlock;

    @TableField("nation_id")
    private Integer nationId;

    @TableField("native_place")
    private String nativePlace;

    @TableField("politic_id")
    private Integer politicId;

    @TableField("email")
    private String email;

    @TableField("phone")
    private String phone;

    @TableField("address")
    private String address;

    @TableField("department_id")
    private Integer departmentId;

    @TableField("jobLevel_id")
    private Integer jobLevelId;

    @TableField("pos_id")
    private Integer posId;

    @TableField("salary_id")
    private Integer salaryId;

    @TableField("engage_form")
    private String engageForm;

    @TableField("tiptop_degree")
    private String tiptopDegree;

    @TableField("specialty")
    private String specialty;

    @TableField("school")
    private String school;

    @TableField("begin_date")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private Date beginDate;

    @TableField("work_state")
    private String workState;

    @TableField("work_id")
    private String workId;

    @TableField("contract_term")
    private Double contractTerm;

    @TableField("conversion_time")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private Date conversionTime;

    @TableField("not_work_date")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private Date notWorkDate;

    @TableField("begin_contract")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private Date beginContract;

    @TableField("end_contract")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private Date endContract;

    @TableField("work_age")
    private Integer workAge;

    @TableField(exist = false)
    private Nation nation;

    @TableField(exist = false)
    private PoliticsStatus politicsStatus;

    @TableField(exist = false)
    private Department department;

    @TableField(exist = false)
    private JobLevel jobLevel;

    @TableField(exist = false)
    private Position position;

    @TableField(exist = false)
    private Salary salary;

}