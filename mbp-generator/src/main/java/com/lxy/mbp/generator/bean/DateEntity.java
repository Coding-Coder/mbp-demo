package com.lxy.mbp.generator.bean;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: lxy
 * @Date: 2020-03-26 16:59
 * @Description: 通用字段父类
 */
@Data
public class DateEntity implements Serializable {
    @TableField(
            fill = FieldFill.INSERT
    )
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC+8")
    private Date createTime;

    @TableField(
            fill = FieldFill.INSERT_UPDATE
    )
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC+8")
    private Date updateTime;
}
