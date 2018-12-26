package com.example.danielgood.chatapp3.Notifications;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class OreoNotification extends ContextWrapper{

    private static final String CHANNEL_ID="com.example.danielgood.chatapp";
    private static final String CHANNEL_NAME="chatapp";

    private NotificationManager notificationManager;

    public OreoNotification(Context base) {
        super(base);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            createChannel();
        }
    }
    @TargetApi(Build.VERSION_CODES.O)
    private void createChannel() {
        NotificationChannel channel =new NotificationChannel(CHANNEL_ID,CHANNEL_NAME,NotificationManager.IMPORTANCE_DEFAULT);
        channel.enableLights(false);
        channel.enableVibration(true);
        channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        getManager().createNotificationChannel(channel);

    }

    public NotificationManager getManager() {
        if(notificationManager==null){
            notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        }
        return notificationManager;
    }

    @TargetApi(Build.VERSION_CODES.O)
    public Notification.Builder getOreoNotification(String title, String body, PendingIntent pendingIntent,Uri soundUri,String icon,String image){
        return new Notification.Builder(getApplicationContext(),CHANNEL_ID)
                .setContentIntent(pendingIntent)
                .setContentTitle(title)
                .setContentText(body)
                .setLargeIcon(getBitmap(image))
                .setSmallIcon(Integer.parseInt(icon))
                .setSound(soundUri)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                //.setFullScreenIntent(pendingIntent,false)
                //.setVisibility(Notification.VISIBILITY_PRIVATE)
                .setPriority(Notification.PRIORITY_MAX)//设置最高权限

                .setDefaults(Notification.DEFAULT_ALL);
    }

    public Bitmap getBitmap(String urlpath) {

        Bitmap map = null;
        try {
            URL url = new URL(urlpath);
            URLConnection conn = url.openConnection();
            conn.connect();
            InputStream in;
            in = conn.getInputStream();
            map = BitmapFactory.decodeStream(in);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
}
