package com.xuejungao.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 设置运行的时候的注解
@Retention(RetentionPolicy.RUNTIME)
// 设置注解类型
@Target({ ElementType.TYPE })
public @interface InterceptorClasses {

    // 注解里面可以使用的类型 这里用于接口 就是测试用例执行之前和执行之后的
    Class<?>[] value();

    // 需要运行的id数组
    String[]  ids() default "";

    // 需要运行的tag数组
    String[] tags() default "";



}
