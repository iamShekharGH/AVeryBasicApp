package com.iamshekhargh.averybasicapp;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

/**
 * Created by <<-- iamShekharGH -->>
 * on 17 March 2021
 * at 8:01 PM.
 */
public class TempBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String s = "This is the survival of the fittest this is do or die!! baba mnm bole!!";
        Toast.makeText(context, "Broadcast Received here!!", Toast.LENGTH_LONG).show();

        // Ideally save channelID it in string.xml or a static variable.
        Notification n = new NotificationCompat.Builder(context, "URGENT")
                .setContentTitle(s)
                .setSmallIcon(R.mipmap.ic_glass)
                .setContentText("Dekho saand aaya, sand nazar aayaa!!!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build();
        NotificationManagerCompat.from(context).notify(2, n);
    }
}
