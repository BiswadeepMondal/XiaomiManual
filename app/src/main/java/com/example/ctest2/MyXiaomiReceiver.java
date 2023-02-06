package com.example.ctest2;
import android.content.Context;
import android.os.Bundle;

import com.clevertap.android.sdk.Utils;
import com.clevertap.android.xps.CTXiaomiMessageHandler;
import com.google.android.exoplayer2.util.Log;
import com.google.gson.Gson;
import com.xiaomi.mipush.sdk.ErrorCode;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xiaomi.mipush.sdk.MiPushCommandMessage;
import com.xiaomi.mipush.sdk.MiPushMessage;
import com.xiaomi.mipush.sdk.PushMessageReceiver;
import com.clevertap.android.sdk.pushnotification.fcm.CTFcmMessageHandler;
import com.clevertap.android.sdk.CleverTapAPI;
//import com.clevertap.android.sdk.pushnotification.Xps.CTXiaomiMessageHandler;
import org.json.JSONException;

import java.util.List;

public class MyXiaomiReceiver extends PushMessageReceiver {
    @Override
    public void onReceivePassThroughMessage(Context context, MiPushMessage miPushMessage) {
        try {
            android.util.Log.d("clevertap", "CT json: " + new Gson().toJson(miPushMessage));
            Bundle extras = Utils.stringToBundle(miPushMessage.getContent());
         //   CleverTapAPI.createNotification(context, extras);
           new CTXiaomiMessageHandler().createNotification(context,miPushMessage);
            android.util.Log.d("clevertap", "MYxpsreceiver"+extras);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCommandResult(Context context, MiPushCommandMessage message) {
        String command = message.getCommand();
        List<String> arguments = message.getCommandArguments();
        String mRegId = ((arguments != null && arguments.size() > 0) ? arguments.get(0) : null);
        String xiaomiRegion = MiPushClient.getAppRegion(context);
        if (MiPushClient.COMMAND_REGISTER.equals(command)) {
            if (message.getResultCode() == ErrorCode.SUCCESS) {
                Log.d("MiReceiver", "Xiaomi token - " + mRegId);
                CleverTapAPI.getDefaultInstance(context).pushXiaomiRegistrationId(mRegId, xiaomiRegion, true);
            }
        }
    }

    @Override
    public void onReceiveRegisterResult(Context context, MiPushCommandMessage message) {
        String command = message.getCommand();
        List<String> arguments = message.getCommandArguments();
        String mRegId = ((arguments != null && arguments.size() > 0) ? arguments.get(0) : null);
        String xiaomiRegion = MiPushClient.getAppRegion(context);
        if (MiPushClient.COMMAND_REGISTER.equals(command)) {
            if (message.getResultCode() == ErrorCode.SUCCESS) {
                Log.d("MiReceiver", "Xiaomi token - " + mRegId);
                CleverTapAPI.getDefaultInstance(context).pushXiaomiRegistrationId(mRegId, xiaomiRegion, true);
            }
        }
    }
}
