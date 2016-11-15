package com.olivierpayen.notificationaccesssample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationManagerCompat;

import java.util.Set;

class PermissionUtils {
    static boolean hasNotificationAccess(@NonNull final Context context) {
        String packageName = context.getPackageName();
        Set<String> enabledPackages = NotificationManagerCompat.getEnabledListenerPackages(context);
        return enabledPackages.contains(packageName);
    }
}
