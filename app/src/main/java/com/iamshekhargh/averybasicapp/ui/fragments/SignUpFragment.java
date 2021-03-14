package com.iamshekhargh.averybasicapp.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iamshekhargh.averybasicapp.R;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class SignUpFragment extends Fragment {
    SignUpFragmentInterface fragmentInterface;

    public SignUpFragment() {
        // Required empty public constructor
    }

    public static SignUpFragment newInstance(String param1, String param2) {
        return new SignUpFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof SignUpFragmentInterface) {
            fragmentInterface = (SignUpFragmentInterface) context;
        } else {
            throw new RuntimeException(context.getPackageName() + " Must implement SignUpFragment.SignUpFragmentInterface");
        }
    }

    public interface SignUpFragmentInterface {

    }
}