package com.mtechnologies.martin.bulsuapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mtechnologies.martin.bulsuapp.R;
import com.mtechnologies.martin.bulsuapp.adapter.ProfileAdapter;
import com.mtechnologies.martin.bulsuapp.adapter.TermAdapter;
import com.mtechnologies.martin.bulsuapp.api.MyTermRequest;
import com.mtechnologies.martin.bulsuapp.models.CurrentUser;
import com.mtechnologies.martin.bulsuapp.models.TermUser;
import com.mtechnologies.martin.bulsuapp.utilities.Callback;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class GradesFragment extends Fragment {

    ListView termView;
    private List<TermUser> data;
    private TermAdapter mAdapter;
    public GradesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_grades, container, false);
        getActivity().setTitle("Grades");
        data=new ArrayList<>();
        termView=view.findViewById(R.id.term);
        mAdapter=new TermAdapter(getContext(),data);
        termView.setAdapter(mAdapter);

        new MyTermRequest(requestCallback,getContext());




        return view;
    }
    Callback<List<TermUser>> requestCallback = new Callback<List<TermUser>>() {
        @Override
        public void result(List<TermUser> applicantEvaluations) {
            if (applicantEvaluations != null){
                data.clear();
                data.addAll(applicantEvaluations);
                Log.i("Size",String.valueOf(data.size()));
                mAdapter.notifyDataSetChanged();
            }

        }

    };






}
