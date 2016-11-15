package com.olivierpayen.notificationaccesssample;

import android.content.Intent;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

public class NotificationListener extends NotificationListenerService {
    public static final String ACTION      = "com.olivierpayen.notificationaccesssample.NOTIFICATION_LISTENER_EXAMPLE";
    public static final String ARG_MESSAGE = "notification_event";
    private final       String TAG         = this.getClass().getSimpleName();

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        final String msg = "ID: " + sbn.getId() + "\t" + sbn.getNotification().tickerText + "\t" + sbn.getPackageName();
        Log.i(TAG, msg);
        Intent i = new Intent(ACTION);
        i.putExtra(ARG_MESSAGE, msg);
        sendBroadcast(i);
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
    }
}
