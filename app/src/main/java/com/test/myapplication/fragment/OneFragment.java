package com.test.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.test.myapplication.Onclick;
import com.test.myapplication.R;
import com.test.myapplication.activity.SelectTimeActivity;
import com.test.myapplication.adapter.DataAdapter;
import com.test.myapplication.adapter.SelectSlotAdapter;
import com.test.myapplication.adapter.UserAdapter;
import com.test.myapplication.database.DatabaseHelper;
import com.test.myapplication.model.Datamodel;
import com.test.myapplication.ui.booking.BookingViewModel;
import com.test.myapplication.ui.live.LiveViewModel;

import java.util.ArrayList;
import java.util.List;

public class OneFragment extends Fragment{

    private RecyclerView recyclerView;
    private List<Datamodel> resultList ;
    private SelectSlotAdapter mAdapter;
    Onclick itemClick;

    public OneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_one, container, false);
        recyclerView = (RecyclerView) root.findViewById(R.id.recycler_view_new);
        resultList = new ArrayList();
        itemClick = new Onclick() {
            @Override
            public void onItemClick(View view, int position, int value) {
                if (value == 12) {

                    Intent emailIntent = new Intent(Intent.ACTION_SEND);
                    emailIntent.setType("message/rfc822");
                    emailIntent.putExtra(Intent.EXTRA_EMAIL  , "mayuridayla@gmail.com");
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Appointment confirmation email");
                    emailIntent.putExtra(Intent.EXTRA_TEXT   , "Hi,\n" +
                            "\n" +
                            "This mail is regarding appointment confirmation for 7PM, 30/04/2021.\n" +
                            "\n");
                    try {
                        startActivity(Intent.createChooser(emailIntent, "Select a Email Client"));
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(getActivity(), "No Email client found!!",
                                Toast.LENGTH_SHORT).show();
                    }


                }
            }
        };

        mAdapter = new SelectSlotAdapter(resultList, itemClick);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
preparedatamodelData();
       return  root;

    }
    private void preparedatamodelData() {
        Datamodel datamodel = new Datamodel("07:45 PM - 08:00 PM", "Available", "");
        resultList.add(datamodel);

        datamodel = new Datamodel("08:00 PM - 09:00 PM", "Almost Full", "");
        resultList.add(datamodel);

        datamodel = new Datamodel("06:45 PM - 08:00 PM", "Almost Full", "");
        resultList.add(datamodel);

        datamodel = new Datamodel("07:45 PM - 08:30 PM", "Almost Full", "");
        resultList.add(datamodel);

        datamodel = new Datamodel("07:15 PM - 08:30 PM", "Filling Fast", "");
        resultList.add(datamodel);

        datamodel = new Datamodel("07:00 PM - 08:00 PM", "Filling Fast", "");
        resultList.add(datamodel);



        mAdapter.notifyDataSetChanged();
    }

}