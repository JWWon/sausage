package com.gunghi.tgwing.sausage.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.gunghi.tgwing.sausage.R;
import com.gunghi.tgwing.sausage.activity.SplashActivity;

import java.util.Map;

/**
 * Created by joyeongje on 2017. 8. 26..
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {



        private static final String TAG = "MyFirebaseMsgService";

        public static boolean IN_OUT_CODE = false;

        /**
         * Called when message is received.
         *
         * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
         */
        // [START receive_message]
        @Override
        public void onMessageReceived(RemoteMessage remoteMessage) {
            // [START_EXCLUDE]
            // Notification messages are only received here in onMessageReceived when the app
            // is in the foreground. When the app is in the background an automatically generated notification is displayed.
            // When the user taps on the notification they are returned to the app.

            // Messages containing both notification and data payloads are treated as notification messages.
            //  The Firebase console always sends notification
            // messages. For more see: https://firebase.google.com/docs/cloud-messaging/concept-options
            // [END_EXCLUDE]

            // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
            Log.d(TAG, "From: " + remoteMessage.getFrom());

            // Check if message contains a data payload.
            // 백그라운드 포그라운드 둘다됨.
            if (remoteMessage.getData().size() > 0) {
                Log.d(TAG, "Message data payload: " + remoteMessage.getData());
                Map<String, String> getRemoteMessageHash = remoteMessage.getData();
                sendNotification(getRemoteMessageHash);
            }

            // Check if message contains a notification payload.
            if (remoteMessage.getNotification() != null) {
                Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
                // sendNotification(remoteMessage.getNotification().get);
            }

            // Also if you intend on generating your own notifications as a result of a received FCM
            // message, here is where that should be initiated. See sendNotification method below.
        }
        // [END receive_message]

        /**
         * Schedule a job using FirebaseJobDispatcher.
         */
        private void scheduleJob() {
            // [START dispatch_job]
            //FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(this));
            //Job myJob = dispatcher.newJobBuilder()
            //        .setService(MyJobService.class)
            //        .setTag("my-job-tag")
            //        .build();
            //dispatcher.schedule(myJob);
            // [END dispatch_job]
        }

        /**
         * Handle time allotted to BroadcastReceivers.
         */
        private void handleNow() {
            Log.d(TAG, "Short lived task is done.");

        }


        /**
         * Create and show a simple notification containing the received FCM message.
         *
         * @param messageBody FCM message body received.
         */

        private void sendNotification(Map<String,String> messageBody) {

            String semiTitle = messageBody.get("title");
            String url = messageBody.get("url");

            Intent intent = new Intent(this, SplashActivity.class);
            intent.putExtra("url",url);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                    PendingIntent.FLAG_ONE_SHOT);

            Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.app_icon)
                    .setContentTitle("[오늘의 속보]")
                    .setContentText(semiTitle)
                    .setAutoCancel(true)
                    .setSound(defaultSoundUri)
                    .setContentIntent(pendingIntent);

            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());

        }
}
