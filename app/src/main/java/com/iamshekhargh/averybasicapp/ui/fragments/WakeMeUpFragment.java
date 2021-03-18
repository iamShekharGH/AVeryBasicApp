package com.iamshekhargh.averybasicapp.ui.fragments;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iamshekhargh.averybasicapp.R;
import com.iamshekhargh.averybasicapp.TempBroadcastReceiver;
import com.iamshekhargh.averybasicapp.ui.ResultActivity;

import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

public class WakeMeUpFragment extends Fragment {
    AppCompatEditText time;
    AppCompatEditText date;
    AppCompatTextView timeTv;
    AppCompatTextView dateTv;
    TimePickerDialog timePickerDialog;
    DatePickerDialog datePickerDialog;
    AppCompatButton b1;
    AppCompatButton b2;
    Calendar c;


    public static final String channelNameHigh = "URGENT";
    public static final String channelNameLow = "FREE";

    public WakeMeUpFragment() {
        // Required empty public constructor
    }

    public static WakeMeUpFragment newInstance() {
        return new WakeMeUpFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_wake_me_up, container, false);
        time = v.findViewById(R.id.wakemeupfrag_time_acet);
        date = v.findViewById(R.id.wakemeupfrag_date_acet);
        timeTv = v.findViewById(R.id.wakemeupfrag_time_actv);
        dateTv = v.findViewById(R.id.wakemeupfrag_date_actv);
        b1 = v.findViewById(R.id.wakemeupfrag_notifone_button);
        b2 = v.findViewById(R.id.wakemeupfrag_notiftwo_button);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        c = Calendar.getInstance();

        timePickerDialog = new TimePickerDialog(requireContext(),
                (v, hourOfDay, minute) -> {
                    timeTv.setText(hourOfDay + ":" + minute);
                    c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    c.set(Calendar.MINUTE, minute);
                    c.set(Calendar.SECOND, 0);
                },
                Calendar.getInstance().get(Calendar.HOUR_OF_DAY),
                Calendar.getInstance().get(Calendar.MINUTE),
                false);


        datePickerDialog = new DatePickerDialog(requireContext(), (view1, year, month, dayOfMonth) -> {
            dateTv.setText(dayOfMonth + "/" + ++month + "/" + year);
            c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            c.set(Calendar.MONTH, month);

        },
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.setMessage("dis is message!!");
        datePickerDialog.setTitle("This is title");
        datePickerDialog.setIcon(R.mipmap.ic_tractor);
        datePickerDialog.getDatePicker().setMinDate(Calendar.getInstance().getTimeInMillis());

        timeTv.setOnClickListener(v -> timePickerDialog.show());
        dateTv.setOnClickListener(v -> datePickerDialog.show());
        setupNotification();

        b1.setOnClickListener(v -> showNotificationOne());
        b2.setOnClickListener(v -> showNotificationTwo());
    }

    protected void setupNotification() {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel c1 = new NotificationChannel(channelNameHigh, "URGENT", NotificationManager.IMPORTANCE_HIGH);
            c1.setDescription("This is a description for the notification channel that is about to come to the smartphone nearest to you.");
            NotificationChannel c2 = new NotificationChannel(channelNameLow, "Not Urgent", NotificationManager.IMPORTANCE_LOW);
            c2.setDescription("This is different notification channel description coming to smartphone nearest to you but yeah whatever.");

            NotificationManager manager = requireActivity().getSystemService(NotificationManager.class);
            manager.createNotificationChannel(c1);
            manager.createNotificationChannel(c2);
        }
    }

    protected void showNotificationOne() {

        Intent intent = new Intent(requireContext(), ResultActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(requireContext(), 7, intent, 0);

        Notification n1 = new NotificationCompat.Builder(requireActivity(), channelNameHigh)
                .setSmallIcon(R.mipmap.ic_glass)
                .setContentTitle("Content Title phor HIGH")
                .setContentIntent(pendingIntent)
                .setContentText("Content Text HIGH")
                .addAction(
                        R.mipmap.ic_glass,
                        "BroodcastRcvr",
                        PendingIntent.getBroadcast(requireContext(),
                                0,
                                new Intent(requireActivity(), TempBroadcastReceiver.class),
                                0))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .build();
        NotificationManagerCompat.from(requireContext()).notify(0, n1);
    }

    protected void showNotificationTwo() {

        Intent i = new Intent(requireActivity(), TempBroadcastReceiver.class);

//        Notification n1 = new NotificationCompat.Builder(requireActivity(), channelNameLow)
//                .setSmallIcon(R.mipmap.ic_glass)
//                .setContentTitle("Content Title phor LOW")
//                .setContentText("Content Text LOW")
//                .setPriority(NotificationCompat.PRIORITY_LOW)
//                .build();
//        NotificationManagerCompat.from(requireContext()).notify(1, n1);

        AlarmManager a = (AlarmManager) requireActivity().getSystemService(Context.ALARM_SERVICE);
        a.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP, c.getTimeInMillis(),
                PendingIntent.getBroadcast(requireContext(),
                        0,
                        i,
                        0));

    }
}
