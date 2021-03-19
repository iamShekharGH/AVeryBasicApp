package com.iamshekhargh.averybasicapp.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iamshekhargh.averybasicapp.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

public class ChooseFragment extends Fragment implements View.OnClickListener {

    AppCompatButton rv;
    AppCompatButton api;
    AppCompatButton alarmM;
    AppCompatButton openChildActivity;

    ChooseFragInterface chooseFragInterface;

    public ChooseFragment() {
        // Required empty public constructor
    }

    public static ChooseFragment newInstance() {
        return new ChooseFragment();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_choose, container, false);
        rv = v.findViewById(R.id.fragchoose_rv);
        api = v.findViewById(R.id.fragchoose_api);
        alarmM = v.findViewById(R.id.fragchoose_alarm);
        openChildActivity = v.findViewById(R.id.fragchoose_open_child_activity);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rv.setOnClickListener(this);
        api.setOnClickListener(this);
        alarmM.setOnClickListener(this);
        openChildActivity.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fragchoose_alarm) {
            chooseFragInterface.startFrag(1);
        }
        if (v.getId() == R.id.fragchoose_api) {
            chooseFragInterface.startFrag(2);
        }
        if (v.getId() == R.id.fragchoose_rv) {
            chooseFragInterface.startFrag(3);
        }
        if (v.getId() == R.id.fragchoose_open_child_activity) {
            chooseFragInterface.startFrag(4);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof ChooseFragInterface) {
            chooseFragInterface = (ChooseFragInterface) context;
        } else {
            throw new RuntimeException(context.getClass().getSimpleName() + " must implement ChooseFragInterface");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        chooseFragInterface = null;
    }

    public interface ChooseFragInterface {

        void startFrag(int i);
    }
}