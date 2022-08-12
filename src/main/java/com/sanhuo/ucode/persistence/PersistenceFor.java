package com.sanhuo.ucode.persistence;

import java.lang.annotation.*;

/**
 * @author zhangzs
 * @description
 * @date 2022/8/11 15:51
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PersistenceFor {

    Class<? extends Persistence> value();
}
