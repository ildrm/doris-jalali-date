package com.ildrm.jalali;

public class MyModUDF {
    public Long evaluate(Integer a, Integer b) {
        return JalaliUtils.myMod(a, b);
    }
}
