package com.ildrm.jalali;

public class GDateUDF {
    public String evaluate(Short jy, Short jm, Short jd) {
        return JalaliUtils.gDate(jy, jm, jd);
    }
}