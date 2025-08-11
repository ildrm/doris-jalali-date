package com.ildrm.jalali;

public class PMonthUDF {
    public Integer evaluate(String date) {
        return JalaliUtils.pMonth(date);
    }
}