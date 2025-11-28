package com.zzyweb.myblogcommon.aspect;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface ApiOperationLog {
    /*
    * api功能描述
    * @return
     */
    String description() default "";
}
