package com.iamshekhargh.averybasicapp.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.iamshekhargh.averybasicapp.R;
import com.iamshekhargh.averybasicapp.models.response.UserResponseModel;
import com.iamshekhargh.averybasicapp.networkFiles.ApiEndpoints;
import com.iamshekhargh.averybasicapp.networkFiles.CustomCallback;
import com.iamshekhargh.averybasicapp.networkFiles.EnvironmentProvider;
import com.iamshekhargh.averybasicapp.networkFiles.RetrofitObjectProvider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import retrofit2.Call;

public class HomeActivity extends AppCompatActivity {
    ApiEndpoints apiEndpoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", v -> getSingleUser()).show();
            getSingleUser();
        });
//        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new FirstFragment()).addToBackStack(null).commit();
    }



    protected void getSingleUser() {
        apiEndpoints = RetrofitObjectProvider.getClient(
                EnvironmentProvider.getUrl(), this).create(ApiEndpoints.class);
        Call<UserResponseModel> c = apiEndpoints.getUserInfo();
        c.enqueue(new CustomCallback<UserResponseModel>() {
            @Override
            public void onDataArrived(UserResponseModel userResponseModel) {
                Log.i("onDataArrive", userResponseModel.toString());

            }

            @Override
            public void onError(String message) {

            }
        });
    }
}