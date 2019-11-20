package com.xhp.testutils.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesManager {
    public static final String SHARE_NAME = "share";
    public static final String SAVE_URL="SAVE_URL";
    private static SharedPreferencesManager instance;
    private static SharedPreferences sharedPreferences = null;
    private static SharedPreferences.Editor editor = null;

    private SharedPreferencesManager(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARE_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static SharedPreferencesManager getInstance(Context context) {
        if (instance == null) {
            synchronized (SharedPreferencesManager.class) {
                if (instance == null) {
                    instance = new SharedPreferencesManager(context);
                }
            }
        }
        return instance;
    }

    public void putString(String key, String value) {
        editor.putString(key, value);
        editor.apply();
    }

    public String getValue(String key) {
        return sharedPreferences.getString(key, "");
    }

    public void clear() {
        editor.clear();
    };
}
