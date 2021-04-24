package com.iamshekhargh.averybasicapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iamshekhargh.averybasicapp.models.room.User;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by <<-- iamShekharGH -->>
 * on 25 March 2021
 * at 7:30 PM.
 */
public class RoomRVAdapter extends RecyclerView.Adapter<RoomRVAdapter.RoomActivViewHolder> {

    List<User> users = new ArrayList<>();
    Context c;
    RVAdapterInterface anInterface;

    public RoomRVAdapter(LiveData<List<User>> users, Context c) {
        this.users = users.getValue();
        this.c = c;
        if (c instanceof RVAdapterInterface) {
            anInterface = (RVAdapterInterface) c;
        } else {
            // Don't feel like crashing it here.
            Log.d(c.getClass().getName(), c.getClass().getName() + " must implement RVAdapterInterface.");
        }
    }

    @NonNull
    @Override
    public RoomActivViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_for_room_item, parent, false);
        return new RoomActivViewHolder(v);
    }

    public void addItem(User u) {
        users.add(u);
        notifyItemInserted(users.size() - 1);
    }

    public User getUserAtPos(int pos) {
        return users.get(pos);
    }

    public void setList(List<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull RoomActivViewHolder holder, int position) {
        if (users.size() == 0) {
            holder.emptyList(); // wont show as getItemCount returners 0;
        } else
            holder.setUser(users.get(position));
    }

    @Override
    public int getItemCount() {
        if (users == null) {
            return 0;
        }
        return users.size();
    }

    public class RoomActivViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView fname;
        AppCompatTextView lname;
        AppCompatTextView age;
        AppCompatTextView gender;
        AppCompatTextView mobileNo;
        AppCompatTextView id;
        AppCompatImageView delete;

        public void setUser(User u) {
            fname.setText(u.getFirstname());
            lname.setText(u.getLastname());
            age.setText(String.valueOf(u.getAge()));
            if (u.isGen()) {
                gender.setText("MALE");
            } else {
                gender.setText("FEMALE");
            }
            mobileNo.setText(u.getMobNo());
            id.setText(String.valueOf(u.getId()));
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    anInterface.deleteUser(u);
                }
            });

        }

        public void emptyList() {
            fname.setVisibility(View.INVISIBLE);
            lname.setVisibility(View.INVISIBLE);
            age.setVisibility(View.INVISIBLE);
            gender.setVisibility(View.INVISIBLE);
            mobileNo.setVisibility(View.INVISIBLE);
            id.setText("Nothing to show!!");
        }

        public RoomActivViewHolder(@NonNull View itemView) {
            super(itemView);

            fname = itemView.findViewById(R.id.card_room_firstname);
            lname = itemView.findViewById(R.id.card_room_lastname);
            age = itemView.findViewById(R.id.card_room_age);
            gender = itemView.findViewById(R.id.card_room_gen);
            mobileNo = itemView.findViewById(R.id.card_room_mobileNo);
            id = itemView.findViewById(R.id.card_room_userid);
            delete = itemView.findViewById(R.id.card_room_aciv_delete);
        }

    }

    public interface RVAdapterInterface {
        void deleteUser(User u);

        void updateUser(User u);

    }
}
