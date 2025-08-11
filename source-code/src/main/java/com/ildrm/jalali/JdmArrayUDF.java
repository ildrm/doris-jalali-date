package com.ildrm.jalali;

public class JdmArrayUDF {
    public Short evaluate(Short index) {
        return JalaliUtils.jdmArray(index);
    }
}
