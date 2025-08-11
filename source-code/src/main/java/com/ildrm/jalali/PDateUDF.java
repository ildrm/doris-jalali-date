package com.ildrm.jalali;

public class PDateUDF {
    public String evaluate(String date) {
        return JalaliUtils.pDate(date);
    }
}