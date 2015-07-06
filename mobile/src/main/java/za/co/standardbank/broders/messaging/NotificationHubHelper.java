package za.co.standardbank.broders.messaging;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.microsoft.windowsazure.messaging.NotificationHub;
import com.microsoft.windowsazure.notifications.NotificationsManager;

/**
 * Created by a159937 on 2014/12/05.
 */
public class NotificationHubHelper {

    private static final String SENDER_ID = "907271717475";
    //private static final String connectionString = "Endpoint=sb://broderz-ns.servicebus.windows.net/;SharedAccessKeyName=DefaultListenSharedAccessSignature;SharedAccessKey=Fa0tsWBuaX4Iu44TjNFeMFXc0MigBwvo3eMSr10pjGg=";
      private static final String connectionString = "Endpoint=sb://broders.servicebus.windows.net/;SharedAccessKeyName=DefaultListenSharedAccessSignature;SharedAccessKey=a0wHB1lUPr1Sq2Eb2TQsIRsEmOYAKoEW6xV2n7ke3W8=";
    private static final String hubName = "broders";
    private static GoogleCloudMessaging gcm;
    private static NotificationHub hub;

    public static void init(Context context) {
        NotificationsManager.handleNotifications(context, SENDER_ID, NotificationHubHandler.class);
        gcm = GoogleCloudMessaging.getInstance(context);

        hub = new NotificationHub(hubName, connectionString, context);
        registerWithNotificationHubs(context);
    }

    private static void registerWithNotificationHubs(Context context) {
        new AsyncTask<Object, Object, Object>() {
            @Override
            protected Object doInBackground(Object... params) {
                try {
                    String registerId = gcm.register(SENDER_ID);
                    Log.d("PUSH-ID", registerId);
                    hub.register(registerId);
                } catch (Exception e) {
                    return e;
                }
                return null;
            }
        }.execute(null, null, null);
    }
}
