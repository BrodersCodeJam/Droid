package za.co.standardbank.broders.messaging;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.microsoft.windowsazure.notifications.NotificationsHandler;

import za.co.standardbank.broders.MainActivity;
import za.co.standardbank.broders.R;

/**
 * Created by a159937 on 2014/12/05.
 */
public class NotificationHubHandler extends NotificationsHandler {

    public static final int NOTIFICATION_ID = 1;
    private NotificationManagerCompat mNotificationManager;
    //TODO change to NotificationManagerCompat for Wearable
    NotificationCompat.Builder builder;
    Context ctx;

    @Override
    public void onReceive(Context context, Bundle bundle) {
        ctx = context;
        String nhMessage = bundle.getString("msg");

        sendNotification(nhMessage);
    }

    private void sendNotification(String msg) {
        mNotificationManager = NotificationManagerCompat.from(ctx);

        PendingIntent contentIntent = PendingIntent.getActivity(ctx, 0, new Intent(ctx, MainActivity.class), 0);;
        if ("Your coffee is ready for collection".equals(msg)) {
            contentIntent = PendingIntent.getActivity(ctx, 0, new Intent(ctx, MainActivity.class), 0);
        }

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(ctx)
                        .setSmallIcon(R.drawable.house_icon)
                        .setContentTitle("Coffee Time")
                        //.setStyle(new NotificationCompat.BigTextStyle().bigText(msg))
                        //.setAutoCancel(true)
                        .setContentText(msg)
                        .setContentIntent(contentIntent);

        //mBuilder.setContentIntent(contentIntent);
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }

}
