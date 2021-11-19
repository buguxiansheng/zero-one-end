package com.lcfc.demo.modules.security.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseEntity {
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
