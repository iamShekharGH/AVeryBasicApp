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
import com.iamshekhargh.averybasicapp.ui.fragments.ShowListOfCardsFragment;

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


        setRVFragment(false);
        setApiCallFAB(false);
        

    }

    private void setApiCallFAB(boolean b) {
        FloatingActionButton fab = findViewById(R.id.fab);
        if (b) {
            fab.setVisibility(View.VISIBLE);
            fab.setOnClickListener(view -> {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", v -> getSingleUser()).show();
                getSingleUser();
            });
        } else {
            fab.setVisibility(View.INVISIBLE);

        }

    }

    private void setRVFragment(boolean b) {
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, ShowListOfCardsFragment.newInstance()).addToBackStack(null).commit();
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