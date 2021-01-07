package com.lcfc.demo.common.annotation;


import org.apache.ibatis.jdbc.Null;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface LogOperation {
    // 属性值的数据类型
    String value() default "";
}
