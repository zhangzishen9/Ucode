package com.sanhuo.ucode.cache;

import com.sanhuo.ucode.persistence.CodeTimeCachePersistence;
import com.sanhuo.ucode.persistence.PersistenceFor;

import java.util.Date;

/**
 * @author zhangzs
 * @description
 * @date 2022/7/22 17:16
 **/

@PersistenceFor(CodeTimeCachePersistence.class)
public class CodeTimeCache implements Cache {

    private static final long serialVersionUID = 4862937873436864445L;
    private String TodayCodeTime = "";
    private String TodayActiveCodeTime = "";
    private Integer IncreaseCodeNumber = 0;
    private Integer DecreaseCodeNumber = 0;
    private Integer IncreaseWordNumber = 0;

    private Date cacheDate;

    public Integer getIncreaseWordNumber() {
        return IncreaseWordNumber;
    }

    public void setIncreaseWordNumber(Integer increaseWordNumber) {
        IncreaseWordNumber = increaseWordNumber;
    }

    public Date getCacheDate() {
        return cacheDate;
    }

    public void setCacheDate(Date cacheDate) {
        this.cacheDate = cacheDate;
    }

    public String getTodayCodeTime() {
        return TodayCodeTime;
    }

    public void setTodayCodeTime(String todayCodeTime) {
        TodayCodeTime = todayCodeTime;
    }

    public String getTodayActiveCodeTime() {
        return TodayActiveCodeTime;
    }

    public void setTodayActiveCodeTime(String todayActiveCodeTime) {
        TodayActiveCodeTime = todayActiveCodeTime;
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
