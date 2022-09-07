package com.sanhuo.ucode.cache;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhangzs
 * @description
 * @date 2022/8/11 16:22
 **/
public interface Cache extends Serializable {

    Date getCacheDate();
}
