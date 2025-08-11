package com.ildrm.jalali;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class JalaliUtils {
    // Helper method for integer division (equivalent to __mydiv)
    public static Long myDiv(Integer a, Integer b) {
        if (a == null || b == null || b == 0) return null;
        return (long) Math.floorDiv(a, b);
    }

    // Helper method for modulo operation (equivalent to __mymod)
    public static Long myMod(Integer a, Integer b) {
        if (a == null || b == null || b == 0) return null;
        return (long) (a - b * Math.floorDiv(a, b));
    }

    // Days in Gregorian month (equivalent to _gdmarray)
    public static Short gdmArray(Short m) {
        if (m == null) return null;
        switch (m) {
            case 0: return 31;
            case 1: return 28;
            case 2: return 31;
            case 3: return 30;
            case 4: return 31;
            case 5: return 30;
            case 6: return 31;
            case 7: return 31;
            case 8: return 30;
            case 9: return 31;
            case 10: return 30;
            case 11: return 31;
            default: return null;
        }
    }

    // Days in Jalali month, 0-based (equivalent to _jdmarray)
    public static Short jdmArray(Short m) {
        if (m == null) return null;
        switch (m) {
            case 0: return 31;
            case 1: return 31;
            case 2: return 31;
            case 3: return 31;
            case 4: return 31;
            case 5: return 31;
            case 6: return 30;
            case 7: return 30;
            case 8: return 30;
            case 9: return 30;
            case 10: return 30;
            case 11: return 29;
            default: return null;
        }
    }

    // Days in Jalali month, 1-based (equivalent to _jdmarray2)
    public static Short jdmArray2(Short m) {
        if (m == null) return null;
        switch (m) {
            case 1: return 31;
            case 2: return 31;
            case 3: return 31;
            case 4: return 31;
            case 5: return 31;
            case 6: return 31;
            case 7: return 30;
            case 8: return 30;
            case 9: return 30;
            case 10: return 30;
            case 11: return 30;
            case 12: return 29;
            default: return null;
        }
    }

    // Days in Gregorian month with leap year adjustment (equivalent to _gdmarray2)
    public static Short gdmArray2(Short m, Short k) {
        if (m == null || k == null) return null;
        switch (m) {
            case 0: return 31;
            case 1: return (short) (28 + k);
            case 2: return 31;
            case 3: return 30;
            case 4: return 31;
            case 5: return 30;
            case 6: return 31;
            case 7: return 31;
            case 8: return 30;
            case 9: return 31;
            case 10: return 30;
            case 11: return 31;
            default: return null;
        }
    }

    // Convert Gregorian DATETIME to Jalali date string (equivalent to pdate)
    public static String pDate(String gdate) {
        if (gdate == null) return null;
        try {
            LocalDateTime dateTime = LocalDateTime.parse(gdate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            int gy = dateTime.getYear() - 1600;
            int gm = dateTime.getMonthValue() - 1;
            int gd = dateTime.getDayOfMonth() - 1;
            String ttime = dateTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

            long g_day_no = (365 * gy) + myDiv(gy + 3, 4) - myDiv(gy + 99, 100) + myDiv(gy + 399, 400);
            for (int i = 0; i < gm; i++) {
                g_day_no += gdmArray((short) i);
            }
            if (gm > 1 && ((gy % 4 == 0 && gy % 100 != 0) || gy % 400 == 0)) {
                g_day_no += 1;
            }
            g_day_no += gd;
            long j_day_no = g_day_no - 79;
            long j_np = j_day_no / 12053;
            j_day_no = j_day_no % 12053;
            long jy = 979 + 33 * j_np + 4 * myDiv((int) j_day_no, 1461);
            j_day_no = j_day_no % 1461;

            if (j_day_no >= 366) {
                jy += myDiv((int) (j_day_no - 1), 365);
                j_day_no = (j_day_no - 1) % 365;
            }

            int i = 0;
            while (i < 11 && j_day_no >= jdmArray((short) i)) {
                j_day_no -= jdmArray((short) i);
                i++;
            }
            int jm = i + 1;
            int jd = (int) j_day_no + 1;
            String resout = String.format("%d-%02d-%02d", jy, jm, jd);
            if (!ttime.equals("00:00:00")) {
                resout += " " + ttime;
            }
            return resout;
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    // Get Jalali month number (equivalent to PMONTH)
    public static Integer pMonth(String gdate) {
        if (gdate == null) return null;
        try {
            LocalDateTime dateTime = LocalDateTime.parse(gdate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            int gy = dateTime.getYear() - 1600;
            int gm = dateTime.getMonthValue() - 1;
            int gd = dateTime.getDayOfMonth() - 1;

            long g_day_no = (365 * gy) + myDiv(gy + 3, 4) - myDiv(gy + 99, 100) + myDiv(gy + 399, 400);
            for (int i = 0; i < gm; i++) {
                g_day_no += gdmArray((short) i);
            }
            if (gm > 1 && ((gy % 4 == 0 && gy % 100 != 0) || gy % 400 == 0)) {
                g_day_no += 1;
            }
            g_day_no += gd;
            long j_day_no = g_day_no - 79;
            long j_np = j_day_no / 12053;
            j_day_no = j_day_no % 12053;
            long jy = 979 + 33 * j_np + 4 * myDiv((int) j_day_no, 1461);
            j_day_no = j_day_no % 1461;

            if (j_day_no >= 366) {
                jy += myDiv((int) (j_day_no - 1), 365);
                j_day_no = (j_day_no - 1) % 365;
            }

            int i = 0;
            while (i < 11 && j_day_no >= jdmArray((short) i)) {
                j_day_no -= jdmArray((short) i);
                i++;
            }
            return i + 1;
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    // Get Persian month name (equivalent to pmonthname)
    public static String pMonthName(String gdate) {
        Integer month = pMonth(gdate);
        if (month == null) return null;
        switch (month) {
            case 1: return "فروردین";
            case 2: return "اردیبهشت";
            case 3: return "خرداد";
            case 4: return "تیر";
            case 5: return "مرداد";
            case 6: return "شهریور";
            case 7: return "مهر";
            case 8: return "آبان";
            case 9: return "آذر";
            case 10: return "دی";
            case 11: return "بهمن";
            case 12: return "اسفند";
            default: return null;
        }
    }

    // Get Jalali year (equivalent to pyear)
    public static Integer pYear(String gdate) {
        if (gdate == null) return null;
        try {
            LocalDateTime dateTime = LocalDateTime.parse(gdate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            int gy = dateTime.getYear() - 1600;
            int gm = dateTime.getMonthValue() - 1;
            int gd = dateTime.getDayOfMonth() - 1;

            long g_day_no = (365 * gy) + myDiv(gy + 3, 4) - myDiv(gy + 99, 100) + myDiv(gy + 399, 400);
            for (int i = 0; i < gm; i++) {
                g_day_no += gdmArray((short) i);
            }
            if (gm > 1 && ((gy % 4 == 0 && gy % 100 != 0) || gy % 400 == 0)) {
                g_day_no += 1;
            }
            g_day_no += gd;
            long j_day_no = g_day_no - 79;
            long j_np = j_day_no / 12053;
            j_day_no = j_day_no % 12053;
            long jy = 979 + 33 * j_np + 4 * myDiv((int) j_day_no, 1461);
            j_day_no = j_day_no % 1461;

            if (j_day_no >= 366) {
                jy += myDiv((int) (j_day_no - 1), 365);
                j_day_no = (j_day_no - 1) % 365;
            }
            return (int) jy;
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    // Get Jalali day (equivalent to pday)
    public static Integer pDay(String gdate) {
        if (gdate == null) return null;
        try {
            LocalDateTime dateTime = LocalDateTime.parse(gdate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            int gy = dateTime.getYear() - 1600;
            int gm = dateTime.getMonthValue() - 1;
            int gd = dateTime.getDayOfMonth() - 1;

            long g_day_no = (365 * gy) + myDiv(gy + 3, 4) - myDiv(gy + 99, 100) + myDiv(gy + 399, 400);
            for (int i = 0; i < gm; i++) {
                g_day_no += gdmArray((short) i);
            }
            if (gm > 1 && ((gy % 4 == 0 && gy % 100 != 0) || gy % 400 == 0)) {
                g_day_no += 1;
            }
            g_day_no += gd;
            long j_day_no = g_day_no - 79;
            long j_np = j_day_no / 12053;
            j_day_no = j_day_no % 12053;
            long jy = 979 + 33 * j_np + 4 * myDiv((int) j_day_no, 1461);
            j_day_no = j_day_no % 1461;

            if (j_day_no >= 366) {
                jy += myDiv((int) (j_day_no - 1), 365);
                j_day_no = (j_day_no - 1) % 365;
            }

            int i = 0;
            while (i < 11 && j_day_no >= jdmArray((short) i)) {
                j_day_no -= jdmArray((short) i);
                i++;
            }
            return (int) j_day_no + 1;
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    // Convert Jalali date to Gregorian DATETIME (equivalent to gdate)
    public static String gDate(Short jy, Short jm, Short jd) {
        if (jy == null || jm == null || jd == null) return null;
        int j = 0, j1 = 0, e = 0, k = 0, mday = 0, mo = 0, gm = 0, gd = 0;
        long j_day_no, g_day_no;

        int bkab = myMod((int) jy, 33).intValue();
        if (bkab == 1 || bkab == 5 || bkab == 9 || bkab == 13 || bkab == 17 || bkab == 22 || bkab == 26 || bkab == 30) {
            j = 1;
        }
        int bkab1 = myMod((int) jy + 1, 33).intValue();
        if (bkab1 == 1 || bkab1 == 5 || bkab1 == 9 || bkab1 == 13 || bkab1 == 17 || bkab1 == 22 || bkab1 == 26 || bkab1 == 30) {
            j1 = 1;
        }

        switch (jm) {
            case 1: case 2: case 3: case 4: case 5: case 6:
            case 7: case 8: case 9: case 10: case 11:
                if (jd > jdmArray2(jm) || jd <= 0) e = 1;
                break;
            case 12:
                if (jd > jdmArray2(jm) + j || jd <= 0) e = 1;
                break;
            default:
                e = 1;
                break;
        }
        if (jm > 12 || jm <= 0 || jy <= 0) e = 1;
        if (e > 0) return null;

        int i = (jm >= 11 || (jm == 10 && jd >= 11 && j == 0) || (jm == 10 && jd > 11 && j == 1)) ? 1 : 0;
        int gy = jy + 621 + i;
        k = (myMod(gy, 4) == 0) ? 1 : 0;
        if (myMod(gy, 100) == 0 && myMod(gy, 400) != 0) k = 0;

        int jmm = jm - 1;
        while (jmm > 0) {
            mday += jdmArray2((short) jmm);
            jmm--;
        }
        j_day_no = (jy - 1) * 365 + myDiv((int) jy, 4) + mday + jd;
        g_day_no = j_day_no + 226899 - myDiv(gy - 1, 4);
        Long g_day_mo_long = myMod((int) g_day_no, 365);
        if (g_day_mo_long == null) return null;
        int g_day_mo = g_day_mo_long.intValue();

        if (k == 1 && j == 1) {
            if (g_day_mo == 0) return String.format("%d-12-30", gy);
            if (g_day_mo == 1) return String.format("%d-12-31", gy);
        }
        if (g_day_mo == 0) return String.format("%d-12-31", gy);

        while (g_day_mo > gdmArray2((short) mo, (short) k)) {
            g_day_mo -= gdmArray2((short) mo, (short) k);
            mo++;
            gm++;
        }
        gd = g_day_mo;
        gm++;
        return String.format("%d-%02d-%02d", gy, gm, gd);
    }

    // Convert Jalali date string to Gregorian DATETIME (equivalent to gdatestr)
    public static String gDateStr(String jdat) {
        if (jdat == null) return null;
        try {
            String[] parts = jdat.split("/");
            if (parts.length != 3) return null;
            int jy = Integer.parseInt(parts[0]);
            int jm = Integer.parseInt(parts[1]);
            int jd = Integer.parseInt(parts[2]);
            return gDate((short) jy, (short) jm, (short) jd);
        } catch (NumberFormatException e) {
            return null;
        }
    }


    /* Doris entry point
     * This evaluate(String) method is the function Doris will call by default.
     * It tries to support both "yyyy-MM-dd" and "yyyy-MM-dd HH:mm:ss" inputs.
     */
    public String evaluate(String date) {
        if (date == null) return null;
        String d = date.trim();
        try {
            if (d.length() == 10) {
                // yyyy-MM-dd -> append midnight time
                return pDate(d + " 00:00:00");
            } else {
                // try as provided (expects yyyy-MM-dd HH:mm:ss)
                return pDate(d);
            }
        } catch (Exception e) {
            // fallback: try date-only with midnight
            try { return pDate(d.substring(0, Math.min(10, d.length())) + " 00:00:00"); }
            catch (Exception ex) { return null; }
        }
    }

}
