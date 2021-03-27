package com.iamshekhargh.averybasicapp.roomfiles;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import com.iamshekhargh.averybasicapp.models.room.User;

import java.util.List;

import androidx.lifecycle.LiveData;

/**
 * Created by <<-- iamShekharGH -->>
 * on 26 March 2021
 * at 1:40 PM.
 */
public class UserRepository {
    private UserDao userDao;
    private LiveData<List<User>> users;

    public UserRepository(Application application) {
        userDao = UserDatabase.getInstance(application).userDao();
        users = userDao.getAll();
        Log.i("UserRepository", "users: " + users.toString());
    }

    public void insert(User u) {
        new InsertUserAsyncTask(userDao).execute(u);

    }

    public void update(User u) {
        new UpdateUserAsyncTask(userDao).execute(u);

    }

    public void delete(User u) {
        new DeleteUserAsyncTask(userDao).execute(u);
    }

    public void findUser(String fn, String ln) {
        new FindUserAsyncTask(userDao).execute(fn, ln);
    }

    public void deleteAll() {

    }

    public LiveData<List<User>> getAllUsers() {
        return users;
    }

    private static class InsertUserAsyncTask extends AsyncTask<User, Void, Void> {

        private UserDao userDao;

        public InsertUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.insertUser(users[0]);
            return null;
        }
    }

    private static class UpdateUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        public UpdateUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.updateUser(users[0]);
            return null;
        }
    }

    private static class DeleteUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        public DeleteUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.deleteUser(users[0]);
            return null;
        }
    }

    private static class FindUserAsyncTask extends AsyncTask<String, Void, User> {
        private UserDao userDao;

        public FindUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected User doInBackground(String... strings) {
            return userDao.findUser(strings[0], strings[1]);
        }
    }


}
