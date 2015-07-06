package za.co.standardbank.broders.messaging;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.microsoft.windowsazure.notifications.NotificationsHandler;

import za.co.standardbank.broders.MainActivity;
import za.co.standardbank.broders.R;

/**
 * Created by a159937 on 2014/12/05.
 */
public class NotificationHubHandler extends NotificationsHandler {
    public static final String TAG = NotificationHubHandler.class.getSimpleName();
    public static final int NOTIFICATION_ID = 1;
    private NotificationManagerCompat mNotificationManager;
    //TODO change to NotificationManagerCompat for Wearable
    NotificationCompat.Builder builder;
    Context ctx;

    @Override
    public void onReceive(Context context, Bundle bundle) {
        ctx = context;

        String title = bundle.getString("title");
        String message = bundle.getString("message");
        String status = bundle.getString("status");

        Log.d(TAG, "Message: " + message);
        Log.d(TAG, "Title: " + title);
        Log.d(TAG, "Status: " + status);

        mNotificationManager = NotificationManagerCompat.from(ctx);

        PendingIntent contentIntent = PendingIntent.getActivity(ctx, 0, new Intent(ctx, MainActivity.class), 0);
        if ("Your coffee is ready for collection".equals(message)) {
            contentIntent = PendingIntent.getActivity(ctx, 0, new Intent(ctx, MainActivity.class), 0);
        }

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(ctx)
                        .setSmallIcon(R.drawable.house_icon)
                        .setContentTitle(title)
                        .setLargeIcon(BitmapFactory.decodeResource(ctx.getResources(), R.drawable.house_icon))
                        .setStyle(new NotificationCompat.BigTextStyle().bigText(message))
                        .setAutoCancel(true)
                        .setContentText(message)
                        .setContentIntent(contentIntent);

        //mBuilder.setContentIntent(contentIntent);
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }

}
