package com.test.myapplication.ui.account;

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
import com.test.myapplication.database.DatabaseHelper;
import com.test.myapplication.model.User;
import com.test.myapplication.ui.booking.BookingViewModel;

import java.util.ArrayList;
import java.util.List;


public class AccountFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<User> resultList ;
    private DataAdapter mAdapter;
    Onclick itemClick;
    private DatabaseHelper databaseHelper;

    private AccountViewModel accountViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        accountViewModel =
                new ViewModelProvider(this).get(AccountViewModel.class);
        View root = inflater.inflate(R.layout.fragment_account, container, false);
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

        mAdapter = new DataAdapter(resultList, itemClick);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        databaseHelper = new DatabaseHelper(getActivity());
        getDataFromSQLite();

        return root;
    }

    private void getDataFromSQLite() {
        // AsyncTask is used that SQLite operation not blocks the UI Thread.
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                resultList.clear();
                resultList.addAll(databaseHelper.getAllUser());
                return null;
            }
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                mAdapter.notifyDataSetChanged();
            }
        }.execute();
    }

}