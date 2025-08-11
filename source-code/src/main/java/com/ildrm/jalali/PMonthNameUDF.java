package com.ildrm.jalali;

public class PMonthNameUDF {
    public String evaluate(String date) {
        return JalaliUtils.pMonthName(date);
    }
}
