package com.iamshekhargh.averybasicapp.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.iamshekhargh.averybasicapp.R;
import com.iamshekhargh.averybasicapp.models.room.User;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

/**
 * Created by <<-- iamShekharGH -->>
 * on 25 March 2021
 * at 8:44 PM.
 */
public class BottomSheetUserFragment extends BottomSheetDialogFragment implements View.OnClickListener {
    BottomSheetDialogFragmentInterface bottomSheetDialogFragmentInterface;
    AppCompatEditText fName;
    AppCompatEditText lName;
    AppCompatEditText age;
    AppCompatEditText gender;
    AppCompatEditText mobileNo;
    AppCompatEditText id;
    AppCompatButton submit;
    User u;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_get_edit_user_data, container, false);
        fName = v.findViewById(R.id.bottomfrag_firstname);
        lName = v.findViewById(R.id.bottomfrag_lastname);
        age = v.findViewById(R.id.bottomfrag_age);
        gender = v.findViewById(R.id.bottomfrag_gender);
        mobileNo = v.findViewById(R.id.bottomfrag_mobileno);
        id = v.findViewById(R.id.bottomfrag_id);
        submit = v.findViewById(R.id.bottomfrag_submit);
        u = new User();
        submit.setOnClickListener(this);

        return v;
    }

    public static BottomSheetUserFragment getInstance() {
        return new BottomSheetUserFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof BottomSheetDialogFragmentInterface) {
            bottomSheetDialogFragmentInterface = (BottomSheetDialogFragmentInterface) context;
        } else {
            throw new RuntimeException(context.getClass().getSimpleName() + " must implement BottomSheetDialogFragmentInterface");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        bottomSheetDialogFragmentInterface = null;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bottomfrag_submit) {
            u.setFirstname(nullChk(fName));
            u.setLastname(nullChk(lName));
            u.setAge(Integer.parseInt(nullChkInt(age)));
            u.setGen(true);
            u.setMobNo(nullChk(mobileNo));

            bottomSheetDialogFragmentInterface.insertUser(u);
            dismiss();
        }
    }

    private String nullChk(AppCompatEditText appCompatEditText) {
        if (appCompatEditText != null) {
            if (appCompatEditText.getText() != null) {
                if (!appCompatEditText.getText().toString().isEmpty()) {
                    return appCompatEditText.getText().toString();
                }
            }
        }
        return "";

    }

    private String nullChkInt(AppCompatEditText appCompatEditText) {
        if (appCompatEditText != null) {
            if (appCompatEditText.getText() != null) {
                if (!appCompatEditText.getText().toString().isEmpty()) {
                    return appCompatEditText.getText().toString();
                }
            }
        }
        return "0";

    }

    public interface BottomSheetDialogFragmentInterface {
        void insertUser(User user);
    }
}
