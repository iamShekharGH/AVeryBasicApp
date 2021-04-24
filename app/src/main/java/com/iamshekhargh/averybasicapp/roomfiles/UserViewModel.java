package com.iamshekhargh.averybasicapp.roomfiles;

import android.app.Application;

import com.iamshekhargh.averybasicapp.models.room.User;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

/**
 * Created by <<-- iamShekharGH -->>
 * on 26 March 2021
 * at 2:38 PM.
 */
public class UserViewModel extends AndroidViewModel {
    UserRepository userRepository;
    LiveData<List<User>> allUsers;

    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
        allUsers = userRepository.getAllUsers();
    }

    public void insert(User u) {
        userRepository.insert(u);
    }

    public void delete(User u) {
        userRepository.delete(u);
    }

    public void update(User u) {
        userRepository.update(u);
    }

    public LiveData<List<User>> getAllUsers() {
        allUsers = userRepository.getAllUsers();
        return allUsers;
    }

    public void findUser(String fn, String ln) {
        userRepository.findUser(fn, ln);
    }

}
