package com.test.myapplication.ui.live;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.test.myapplication.Onclick;
import com.test.myapplication.R;
import com.test.myapplication.activity.SelectTimeActivity;
import com.test.myapplication.adapter.DataAdapter;
import com.test.myapplication.adapter.UserAdapter;
import com.test.myapplication.database.DatabaseHelper;
import com.test.myapplication.model.Datamodel;
import com.test.myapplication.model.User;
import com.test.myapplication.ui.booking.BookingViewModel;

import java.util.ArrayList;
import java.util.List;

public class LiveFragment extends Fragment {

    private LiveViewModel liveViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        liveViewModel =
                new ViewModelProvider(this).get(LiveViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        return root;
    }


}