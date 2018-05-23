package io.github.agaghd.basemodel.utils;

import android.text.TextUtils;

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

    private StringFormatUtil() {

    }
}
