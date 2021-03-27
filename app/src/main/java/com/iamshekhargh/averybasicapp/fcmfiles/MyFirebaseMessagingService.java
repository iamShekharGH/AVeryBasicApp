package com.iamshekhargh.averybasicapp.fcmfiles;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;

import androidx.annotation.NonNull;

/**
 * Created by <<-- iamShekharGH -->>
 * on 24 March 2021
 * at 4:03 PM.
 */
public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "MyFMS";

    @Override
    public void onNewToken(@NonNull String s) {
        Log.i(TAG, "onNewToken:" + s);
        super.onNewToken(s);
    }
}
