package io.github.agaghd.basemodel.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.util.ArraySet;

import java.util.Map;
import java.util.Set;

/**
 * author : wjy
 * time   : 2018/05/08
 * desc   : sp工具类
 */

public class SharePreferenceUtil {

    private static final String NAME = "fakebilibili";

    private SharedPreferences sharedPreferences;

    public SharePreferenceUtil(Context context) {
        sharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
    }

    public String getString(String key) {
        return sharedPreferences.getString(key, "");
    }

    public int getInt(String key) {
        return sharedPreferences.getInt(key, 0);
    }

    public float getFloat(String key) {
        return sharedPreferences.getFloat(key, 0);
    }

    public boolean getBoolean(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    public long getLong(String key) {
        return sharedPreferences.getLong(key, 0L);
    }

    public Set<String> getStringSet(String key) {
        return sharedPreferences.getStringSet(key, new ArraySet<String>());
    }

    public Map<String, ?> getAll(String key) {
        return sharedPreferences.getAll();
    }

    @SuppressWarnings("unchecked")
    public void put(String key, Object value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (value == null) {
            value = "";
        }
        if (value instanceof String) {
            editor.putString(key, value.toString());
        } else if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        } else if (value instanceof Float) {
            editor.putFloat(key, (Float) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (Long) value);
        } else if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Set) {
            editor.putStringSet(key, (Set<String>) value);
        } else {
            editor.putString(key, value.toString());
        }
        editor.apply();
    }

}