package com.olivierpayen.notificationaccesssample;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView             txtNotifications;
    private NotificationReceiver nReceiver;
    private View                 txtNoNotificationAccess;
    private View                 btnEnableNotificationAccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNotifications = (TextView) findViewById(R.id.txtNotifications);
        txtNoNotificationAccess = findViewById(R.id.txtNoNotificationAccess);
        btnEnableNotificationAccess = findViewById(R.id.btnEnableNotificationAccess);

        nReceiver = new NotificationReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(NotificationListener.ACTION);
        registerReceiver(nReceiver, filter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (PermissionUtils.hasNotificationAccess(this)) {
            txtNoNotificationAccess.setVisibility(View.GONE);
            btnEnableNotificationAccess.setVisibility(View.GONE);
        } else {
            txtNoNotificationAccess.setVisibility(View.VISIBLE);
            btnEnableNotificationAccess.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(nReceiver);
    }

    public void buttonClicked(View v) {
        if (v.getId() == R.id.btnCreateNotify) {
            NotificationManager nManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
            builder.setContentTitle("My Notification");
            builder.setContentText("Notification Listener Service Example");
            builder.setTicker("Notification Listener Service Example");
            builder.setSmallIcon(R.mipmap.ic_launcher);
            builder.setAutoCancel(true);
            nManager.notify((int) System.currentTimeMillis(), builder.build());
        } else if (v.getId() == R.id.btnEnableNotificationAccess) {
            startActivity(new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"));
        }
    }

    class NotificationReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String temp = intent.getStringExtra(NotificationListener.ARG_MESSAGE) + "\n" + txtNotifications.getText();
            txtNotifications.setText(temp);
        }
    }
}
