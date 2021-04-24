package com.iamshekhargh.averybasicapp.ui;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.iamshekhargh.averybasicapp.R;
import com.iamshekhargh.averybasicapp.RoomRVAdapter;
import com.iamshekhargh.averybasicapp.models.room.User;
import com.iamshekhargh.averybasicapp.roomfiles.UserViewModel;
import com.iamshekhargh.averybasicapp.ui.fragments.BottomSheetUserFragment;

import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RoomDBActivity extends AppCompatActivity
        implements BottomSheetUserFragment.BottomSheetDialogFragmentInterface,
        RoomRVAdapter.RVAdapterInterface {

    RecyclerView rv;
    FloatingActionButton fab;
    LiveData<List<User>> users;
    RoomRVAdapter roomRVAdapter;
    UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_d_b);
        Toolbar toolbar = findViewById(R.id.room_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        rv = findViewById(R.id.room_rv);
        fab = findViewById(R.id.room_fab);


//        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class); deprecated
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.getAllUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                roomRVAdapter.setList(users);
            }
        });

        users = userViewModel.getAllUsers();

        setfabFeatures();

        rv.setLayoutManager(new LinearLayoutManager(this));
        roomRVAdapter = new RoomRVAdapter(users, this);
        rv.setAdapter(roomRVAdapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                userViewModel.delete(roomRVAdapter.getUserAtPos(viewHolder.getAdapterPosition()));

            }
        }).attachToRecyclerView(rv);

    }

    private void setfabFeatures() {
        fab.setOnClickListener(v -> {
            BottomSheetUserFragment bottomSheetUserFragment = BottomSheetUserFragment.getInstance();
            bottomSheetUserFragment.show(getSupportFragmentManager(), "RoomDBActivity");
        });
    }

    @Override
    public void insertUser(User user) {
        Log.i("insertUser", "insertUser: " + user.toString());
        userViewModel.insert(user);

    }

    @Override
    public void deleteUser(User u) {
        userViewModel.delete(u);
    }

    @Override
    public void updateUser(User u) {
        userViewModel.update(u);
    }
}