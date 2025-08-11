package com.ildrm.jalali;

public class MyDivUDF {
    public Long evaluate(Integer a, Integer b) {
        return JalaliUtils.myDiv(a, b);
    }
}
