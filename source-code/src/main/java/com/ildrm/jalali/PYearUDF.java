package com.ildrm.jalali;

public class PYearUDF {
    public Integer evaluate(String date) {
        return JalaliUtils.pYear(date);
    }
}