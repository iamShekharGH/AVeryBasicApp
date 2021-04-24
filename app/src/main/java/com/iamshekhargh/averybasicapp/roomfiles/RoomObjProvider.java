package com.iamshekhargh.averybasicapp.roomfiles;

import android.content.Context;

import androidx.room.Room;

/**
 * Created by <<-- iamShekharGH -->>
 * on 25 March 2021
 * at 7:16 PM.
 */
public class RoomObjProvider {
    public static UserDatabase userDatabase;

    public static synchronized UserDatabase getInstance(Context context) {
        if (userDatabase == null) {
            userDatabase = Room.databaseBuilder(context.getApplicationContext(), UserDatabase.class, "user_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return userDatabase;
    }
}
