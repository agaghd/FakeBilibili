package io.github.agaghd.basemodel.utils;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * author : wjy
 * time   : 2018/05/23
 * desc   :
 */

public class DateFormatUtil {

    public static final SimpleDateFormat HH_MM_SS;

    static {
        HH_MM_SS = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
    }

    private DateFormatUtil(){

    }
}
