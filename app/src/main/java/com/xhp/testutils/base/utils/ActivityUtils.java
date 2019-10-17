package com.xhp.testutils.base.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

public class ActivityUtils {

    private static ActivityUtils sInstance;
    private List<Activity> activityList = new ArrayList<>();

    public synchronized static ActivityUtils getInstance() {
        if (sInstance == null) {
            sInstance = new ActivityUtils();
        }
        return sInstance;
    }

    private ActivityUtils() {

    }

    public void addActivity(Activity activity) {
        if (activityList == null)
            activityList = new ArrayList<>();
        if (activity != null)
            activityList.add(activity);
    }

    public void remove(Activity activity) {
        if (activity != null) {
            activityList.remove(activity);
        }
    }

    public void exitApp() {
        for (Activity activity : activityList) {
            activity.finish();
        }
        System.exit(0);
    }
}
