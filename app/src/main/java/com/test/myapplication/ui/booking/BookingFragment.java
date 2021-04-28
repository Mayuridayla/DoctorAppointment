package com.test.myapplication.ui.booking;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
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
import com.test.myapplication.ui.live.LiveViewModel;

import java.util.ArrayList;
import java.util.List;

public class BookingFragment extends Fragment {

    private LiveViewModel liveViewModel;
    private RecyclerView recyclerView;
    private BookingViewModel bookingViewModel ;
    private List<Datamodel> resultList ;
    private UserAdapter mAdapter;
    Onclick itemClick;
    private DatabaseHelper databaseHelper;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        bookingViewModel  = new ViewModelProvider(this).get(BookingViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        recyclerView = (RecyclerView) root.findViewById(R.id.recycler_view);
        resultList = new ArrayList();
        itemClick = new Onclick() {
            @Override
            public void onItemClick(View view, int position, int value) {
                if (value == 12) {
                    //   Log.e("getTitle....", ""+resultList.get(position).getName());
                    Intent intent = new Intent(getActivity(), SelectTimeActivity.class);
                    // intent.putExtra("name", resultList.get(position).getName());
                    startActivity(intent);
                }
            }
        };
        mAdapter = new UserAdapter(resultList, itemClick);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        preparedatamodelData();
        return root;
    }

    private void preparedatamodelData() {
        Datamodel datamodel = new Datamodel("Dr. Nema", "cardiologist", "Baner");
        resultList.add(datamodel);

        datamodel = new Datamodel("Dr. Jain", "dermatologist", "Viman Nagar");
        resultList.add(datamodel);

        datamodel = new Datamodel("Dr. Mayuri", "dentist", "Hinjewadi");
        resultList.add(datamodel);

        datamodel = new Datamodel("Dr. Aakansha", "physical therapist", "Baner");
        resultList.add(datamodel);

        datamodel = new Datamodel("Dr. Rachita", "gynecologist", "anand park");
        resultList.add(datamodel);

        datamodel = new Datamodel("Dr. Mishra", "psychiatrist", "Wakad");
        resultList.add(datamodel);



        mAdapter.notifyDataSetChanged();
    }
}