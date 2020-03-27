package com.lxy.mbp.generator.bean;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: lxy
 * @Date: 2020-03-26 16:59
 * @Description: 通用字段父类
 */
@Data
public class LogicDelEntity implements Serializable {
    @TableLogic
    @TableField(
            select = false,
            fill = FieldFill.INSERT
    )
    private Integer isDel;
}
