package com.iamshekhargh.averybasicapp.roomfiles;

import com.iamshekhargh.averybasicapp.models.room.User;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

/**
 * Created by <<-- iamShekharGH -->>
 * on 25 March 2021
 * at 6:36 PM.
 */
@Dao
public interface UserDao {

    @Insert
    void insertUser(User u);

    @Update
    void updateUser(User u);

    @Delete
    void deleteUser(User u);

    @Query("SELECT * FROM user_table")
    LiveData<List<User>> getAll();

    @Query("SELECT * FROM user_table WHERE first_name LIKE :fn AND last_name LIKE :ln LIMIT 1")
    User findUser(String fn, String ln);

}
