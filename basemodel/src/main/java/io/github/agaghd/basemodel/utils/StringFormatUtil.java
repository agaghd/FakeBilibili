package io.github.agaghd.basemodel.utils;

import android.text.TextUtils;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * author : wjy
 * time   : 2018/05/23
 * desc   : 字符串处理工具
 */

public class StringFormatUtil {

    public static String getFormatedNumsWithWan(String s) {
        String result = s;
        if (!TextUtils.isEmpty(s)) {
            try {
                long number = Long.parseLong(s);
                if (number >= 10000) {
                    number = number / 1000;
                    result = number + "万";
                }
            } catch (Exception e) {
                result = s;
            }
        }
        return result;
    }

    public static String getHMSTimeString(long time) {
        NumberFormat numberFormat = new DecimalFormat("00");
        long s = time % 60;//秒
        long m = (time % 3600) / 60;//分
        long h = time / 3600;//时
        String st = numberFormat.format(s);
        String mt = numberFormat.format(m);
        String ht = numberFormat.format(h);
        String timeString;
        if (h > 0) {
            timeString = ht + ":" + mt + ":" + st;
        } else {
            timeString = mt + ":" + st;
        }
        return timeString;
    }

    private StringFormatUtil() {

    }
}
