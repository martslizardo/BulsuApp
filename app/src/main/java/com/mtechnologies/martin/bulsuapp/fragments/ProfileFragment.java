package com.mtechnologies.martin.bulsuapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mtechnologies.martin.bulsuapp.R;
import com.mtechnologies.martin.bulsuapp.adapter.ProfileAdapter;
import com.mtechnologies.martin.bulsuapp.api.MyProfileRequest;
import com.mtechnologies.martin.bulsuapp.models.CurrentUser;
import com.mtechnologies.martin.bulsuapp.utilities.Callback;
import com.mtechnologies.martin.bulsuapp.utilities.ProfileCallback;
import com.mtechnologies.martin.bulsuapp.utilities.SessionManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {



    ListView profileView;
    private List<CurrentUser> data;
    private ProfileAdapter mAdapter;
    public ProfileFragment() {
        // Required empty public constructor
    }
    private List<CurrentUser> profile = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_profile, container, false);
        getActivity().setTitle("Home");
        data=new ArrayList<>();
        profileView=view.findViewById(R.id.profile);
        mAdapter=new ProfileAdapter(getContext(),data);
        profileView.setAdapter(mAdapter);
        new MyProfileRequest(requestCallback,getContext());

        HashMap<String,String> currentUser=new SessionManager(getContext()).currentUser();
        System.out.println(currentUser.get("PROFILE_ID"));
        System.out.println(currentUser.get("PROFILE_EMAIL"));
        return  view;
    }

    Callback<List<CurrentUser>> requestCallback = new Callback<List<CurrentUser>>() {
        @Override
        public void result(List<CurrentUser> applicantEvaluations) {
            if (applicantEvaluations != null){
                data.clear();
                data.addAll(applicantEvaluations);
                new SessionManager(getContext())
                        .profile(applicantEvaluations.get(0).getCurrentUserID(),
                        applicantEvaluations.get(0).getCurrentuserName(),
                        applicantEvaluations.get(0).getCurrentUserEmail(),
                                applicantEvaluations.get(0).getCurrentUserImage());

                mAdapter.notifyDataSetChanged();
            }

        }

    };




}
