package io.github.agaghd.basemodel.utils;

/**
 * author : wjy
 * time   : 2018/05/08
 * desc   : API 分析工具
 */

public class APIAnalyzeUtil {

    public static String splitAPI(String sourceUrl){
        StringBuilder sb = new StringBuilder();
        if (sourceUrl!=null){
            String[] sourceArray = sourceUrl.split("&");
            if (sourceArray.length>0){
                for (String s:sourceArray){
                    sb.append("&");
                    sb.append(s);
                    sb.append("\n");
                }
            }
        }
        return sb.toString();
    }
}
