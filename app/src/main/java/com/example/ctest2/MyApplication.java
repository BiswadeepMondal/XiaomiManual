package com.example.ctest2;

import android.app.Activity;
import android.app.Application;
import android.app.NotificationManager;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.clevertap.android.pushtemplates.PushTemplateNotificationHandler;
import com.clevertap.android.sdk.ActivityLifecycleCallback;
import com.clevertap.android.sdk.CleverTapAPI;
import com.clevertap.android.sdk.interfaces.NotificationHandler;
import com.clevertap.android.sdk.pushnotification.PushConstants;
import com.xiaomi.mipush.sdk.MiPushClient;

public class MyApplication extends Application implements Application.ActivityLifecycleCallbacks {
    private static final String TAG = MyApplication.class.getName();

    @Override
    public void onCreate() {
        CleverTapAPI.setNotificationHandler((NotificationHandler)new PushTemplateNotificationHandler());
        CleverTapAPI.createNotificationChannel(getApplicationContext(),"biswa2","biswa2","biswa2", NotificationManager.IMPORTANCE_MAX,true,"sound1.mp3");
        registerActivityLifecycleCallbacks(this);
  ActivityLifecycleCallback.register(this);
  CleverTapAPI.setDebugLevel(3);
        CleverTapAPI.getDefaultInstance(this).enableDeviceNetworkInfoReporting(true);
        super.onCreate();
        MiPushClient.registerPush(this, "2882303761520478796", "5382047861796");
        String xiaomiToken = MiPushClient.getRegId(this);
        String xiaomiRegion = MiPushClient.getAppRegion(getApplicationContext());

        if(CleverTapAPI.getDefaultInstance(this) != null){
            CleverTapAPI.getDefaultInstance(this).pushXiaomiRegistrationId(xiaomiToken, xiaomiRegion, true);
        }else{
            Log.e(TAG,"CleverTap is NULL");
        }
    }


    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onActivityCreated() called with: activity = [" + activity.getIntent() + "], savedInstanceState = [" + savedInstanceState + "]");

    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {
        Log.d(TAG, "onActivityStarted() called with: activity = [" + activity + "]");
    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {
        Log.d(TAG, "onActivityResumed() called with: activity = [" + activity + "]");

    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {
        Log.d(TAG, "onActivityPaused() called with: activity = [" + activity + "]");
    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {
        Log.d(TAG, "onActivityStopped() called with: activity = [" + activity + "]");
    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {
        Log.d(TAG, "onActivitySaveInstanceState() called with: activity = [" + activity + "], outState = [" + outState + "]");
    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {




    }

}
