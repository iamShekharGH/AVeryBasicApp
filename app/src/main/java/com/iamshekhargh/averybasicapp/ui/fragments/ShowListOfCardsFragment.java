package com.iamshekhargh.averybasicapp.ui.fragments;

import android.graphics.Canvas;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iamshekhargh.averybasicapp.R;
import com.iamshekhargh.averybasicapp.models.response.UserResponseModel;
import com.iamshekhargh.averybasicapp.ui.RVAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ShowListOfCardsFragment extends Fragment {
    RecyclerView rv;
    RVAdapter rvAdapter;
    AppCompatTextView clickresult;
    List<UserResponseModel> userResponseModelList;


    public ShowListOfCardsFragment() {
        // Required empty public constructor
    }

    public static ShowListOfCardsFragment newInstance() {
        return new ShowListOfCardsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_show_list_of_cards, container, false);
        rv = v.findViewById(R.id.showlist_rv);
        clickresult = v.findViewById(R.id.showlist_click_result);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listSetup();
        recyclerViewSetup();
    }

    private void listSetup() {
        userResponseModelList = new ArrayList<>();
        List<String> list = new ArrayList<>();
        list.add("Ramess");
        list.add("Suress");
        list.add("Ganess");
        list.add("Prasad");
        list.add("Ghatotkaj");
        list.add("Raj");
        list.add("PatliSharma");
        list.add("Divas");
        list.add("Rahim");
        list.add("Aatmaram");
        list.add("Jhetalal");
        list.add("Prakash");
        list.add("JohiJi");
        list.add("Yogendra");
        list.add("Rakess");
        list.add("Dhrutarashtra");
        list.add("Arjun");
        list.add("Lassi");
        list.add("Das");
        list.add("Gunjaz");
        list.add("Takli");
        for (String s : list) {
            UserResponseModel u = new UserResponseModel();
            u.setTitle(s);
            u.setId(new Random().nextInt(100));
            userResponseModelList.add(u);
        }

    }

    private void recyclerViewSetup() {
        rvAdapter = new RVAdapter(userResponseModelList, requireContext(), this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(rvAdapter);
    }

    public void showClick(String s) {
        if (clickresult.getVisibility() == View.VISIBLE)
            clickresult.setVisibility(View.INVISIBLE);
        else clickresult.setVisibility(View.VISIBLE);
        if (s == null) s = "s is null!!";
        clickresult.setText(s);
    }
}