package com.huuc.annotation;

import com.huuc.enums.BaseEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface GetMsg {
    Class<? extends BaseEnum> enumClass() default BaseEnum.class;
}