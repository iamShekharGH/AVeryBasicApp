package com.iamshekhargh.averybasicapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.iamshekhargh.averybasicapp.R;
import com.iamshekhargh.averybasicapp.models.response.UserResponseModel;
import com.iamshekhargh.averybasicapp.networkFiles.ApiEndpoints;
import com.iamshekhargh.averybasicapp.networkFiles.CustomCallback;
import com.iamshekhargh.averybasicapp.networkFiles.EnvironmentProvider;
import com.iamshekhargh.averybasicapp.networkFiles.RetrofitObjectProvider;
import com.iamshekhargh.averybasicapp.ui.fragments.ChooseFragment;
import com.iamshekhargh.averybasicapp.ui.fragments.GoogleLoginFragment;
import com.iamshekhargh.averybasicapp.ui.fragments.PlayWithCoordinator;
import com.iamshekhargh.averybasicapp.ui.fragments.ShowListOfCardsFragment;
import com.iamshekhargh.averybasicapp.ui.fragments.WakeMeUpFragment;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import retrofit2.Call;

public class HomeActivity extends AppCompatActivity implements ChooseFragment.ChooseFragInterface {

    ApiEndpoints apiEndpoints;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        chooseFragment();

        fab = findViewById(R.id.fab);
        fab.setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_toolbar, menu);

        MenuItem.OnActionExpandListener onActionExpandListener = new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                Toast.makeText(HomeActivity.this, "on Expanded!!", Toast.LENGTH_SHORT).show();
                showSnackbar("on Expanded!!");
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                Toast.makeText(HomeActivity.this, "on Collapsed!", Toast.LENGTH_SHORT).show();
                showSnackbar("on Collapsed!!");

                return true;
            }
        };
        MenuItem item = menu.findItem(R.id.search_bar);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                showSnackbar("onQueryTextSubmit " + query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                showSnackbar("onQueryTextChange " + newText);
                return false;
            }
        });
        item.setOnActionExpandListener(onActionExpandListener);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ek_tho_option:
                showSnackbar("Option Phirst");
                return true;
            case R.id.do_tho_option:
                showSnackbar("Option Doosra");
                return true;
            default:
//                showSnackbar("Option Default");
                return super.onOptionsItemSelected(item);
        }
    }

    private void showSnackbar(String s) {
        Snackbar sBar = Snackbar.make(findViewById(R.id.fragment_container), s, Snackbar.LENGTH_LONG);
        sBar.setAction("Action", v -> sBar.dismiss());
        sBar.show();

    }

    private void chooseFragment() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, ChooseFragment.newInstance()).addToBackStack(null).commit();
    }

    private void showWakeMeUp(boolean b) {
        if (b) {
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, WakeMeUpFragment.newInstance()).addToBackStack(null).commit();
        } else {

        }
    }

    private void setApiCallFAB(boolean b) {
//        FloatingActionButton fab = findViewById(R.id.fab);
        if (b) {
            fab.setVisibility(View.VISIBLE);
            fab.setOnClickListener(view -> {
//                getSingleUser();
                getMultiUser();

            });
        } else {
            fab.setVisibility(View.INVISIBLE);
        }

        //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        //                        .setAction("Action", v -> getSingleUser()).show();

    }

    private void setRVFragment(boolean b) {
        if (b)
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, ShowListOfCardsFragment.newInstance()).addToBackStack(null).commit();
    }

    public void openActivity() {
        Intent i = new Intent(this, ResultActivity.class);
        startActivity(i);
//        finish();
    }

    private void loadCoordinatorFragment() {
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, PlayWithCoordinator.newInstance()).addToBackStack(null).commit();
    }

    private void loadSignInFragment() {
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, GoogleLoginFragment.newInstance()).addToBackStack(null).commit();

    }

    private void loadRoomActivity() {
        startActivity(new Intent(this, RoomDBActivity.class));
    }

    @Override
    public void startFrag(int i) {

        switch (i) {
            case 1:
                showWakeMeUp(true);
                break;
            case 2:
                setApiCallFAB(true);
                break;
            case 3:
                setRVFragment(true);
                break;
            case 4:
                openActivity();
                break;
            case 5:
                loadCoordinatorFragment();
                break;
            case 6:
                loadRoomActivity();
                break;
            case 7:
                loadSignInFragment();
                break;

            default:
                showSnackbar("HAHAHA!!");
                break;
        }

    }

    protected void getSingleUser() {
        apiEndpoints = RetrofitObjectProvider.getClient(
                EnvironmentProvider.getUrl(), this).create(ApiEndpoints.class);
        Call<UserResponseModel> c = apiEndpoints.getUserInfo();
        c.enqueue(new CustomCallback<UserResponseModel>() {
            @Override
            public void onDataArrived(UserResponseModel userResponseModel) {
                Log.i("onDataArrive", userResponseModel.toString());
                showSnackbar("onDataArrive" + userResponseModel.toString());

            }

            @Override
            public void onError(String message) {

            }
        });
    }

    protected void getMultiUser() {
        apiEndpoints = RetrofitObjectProvider.getClient(EnvironmentProvider.getUrl(), this)
                .create(ApiEndpoints.class);
        Call<List<UserResponseModel>> c = apiEndpoints.getAllList();
        c.enqueue(new CustomCallback<List<UserResponseModel>>() {
            @Override
            public void onDataArrived(List<UserResponseModel> userResponseModels) {
                Log.i("onDataArriveList", userResponseModels.toString());
                showSnackbar("onDataArriveList" + userResponseModels.toString());


            }

            @Override
            public void onError(String message) {

            }
        });
    }

}