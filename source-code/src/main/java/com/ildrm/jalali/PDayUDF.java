package com.ildrm.jalali;

public class PDayUDF {
    public Integer evaluate(String date) {
        return JalaliUtils.pDay(date);
    }
}
