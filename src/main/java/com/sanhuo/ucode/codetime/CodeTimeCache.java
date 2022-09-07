package com.sanhuo.ucode.codetime;

import com.sanhuo.ucode.cache.Cache;
import com.sanhuo.ucode.persistence.PersistenceFor;

import java.util.Date;

/**
 * @author zhangzs
 * @description
 * @date 2022/7/22 17:16
 **/

@PersistenceFor(CodeTimeCachePersistence.class)
public class CodeTimeCache implements Cache {

    public final static String TODAY_CACHE = "todayCodeTimeCache";
    public final static String YESTERDAY_CACHE = "yesterdayCodeTimeCache";

    private static final long serialVersionUID = 4862937873436864445L;
    private Integer IncreaseCodeNumber = 0;
    private Integer DecreaseCodeNumber = 0;
    private Date cacheDate;


    @Override
    public Date getCacheDate() {
        return cacheDate;
    }

    public void setCacheDate(Date cacheDate) {
        this.cacheDate = cacheDate;
    }

    public Integer getIncreaseCodeNumber() {
        return IncreaseCodeNumber;
    }

    public void setIncreaseCodeNumber(Integer increaseCodeNumber) {
        IncreaseCodeNumber = increaseCodeNumber;
    }

    public Integer getDecreaseCodeNumber() {
        return DecreaseCodeNumber;
    }

    public void setDecreaseCodeNumber(Integer decreaseCodeNumber) {
        DecreaseCodeNumber = decreaseCodeNumber;
    }
}
