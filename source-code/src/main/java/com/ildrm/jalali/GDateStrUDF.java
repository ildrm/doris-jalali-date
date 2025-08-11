package com.ildrm.jalali;

public class GDateStrUDF {
    public String evaluate(String jalaliDate) {
        return JalaliUtils.gDateStr(jalaliDate);
    }
}
