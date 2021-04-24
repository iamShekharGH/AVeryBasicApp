package com.iamshekhargh.averybasicapp.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iamshekhargh.averybasicapp.R;
import com.iamshekhargh.averybasicapp.models.response.UserResponseModel;
import com.iamshekhargh.averybasicapp.ui.fragments.ShowListOfCardsFragment;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by <<-- iamShekharGH -->>
 * on 15 March 2021
 * at 4:18 PM.
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.VH> {
    List<UserResponseModel> list;
    Context context;
    ShowListOfCardsFragment showListOfCardsFragment;

    public RVAdapter(List<UserResponseModel> list, Context context, ShowListOfCardsFragment showListOfCardsFragment) {
        this.list = list;
        this.context = context;
        this.showListOfCardsFragment = showListOfCardsFragment;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.card_for_list, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.setName(list.get(position).getTitle());
        holder.setAge(String.valueOf(list.get(position).getId()));
        holder.setOnClick(list.get(position).toString());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class VH extends RecyclerView.ViewHolder {
        AppCompatTextView name;
        AppCompatTextView age;
        CardView cardView;


        public VH(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.card_actv_name);
            age = itemView.findViewById(R.id.card_actv_number);
            cardView = itemView.findViewById(R.id.card);
        }

        public void setName(String s) {
            name.setText(s);
        }

        public void setAge(String s) {
            age.setText(s);
        }

        public void setOnClick(String s) {
            cardView.setOnClickListener(v -> {
                showListOfCardsFragment.showClick(s);
            });

        }
    }
}
