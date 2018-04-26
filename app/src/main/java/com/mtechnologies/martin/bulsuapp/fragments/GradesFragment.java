package com.mtechnologies.martin.bulsuapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mtechnologies.martin.bulsuapp.R;
import com.mtechnologies.martin.bulsuapp.adapter.ProfileAdapter;
import com.mtechnologies.martin.bulsuapp.adapter.TermAdapter;
import com.mtechnologies.martin.bulsuapp.api.MyGradesRequest;
import com.mtechnologies.martin.bulsuapp.api.MyTermRequest;
import com.mtechnologies.martin.bulsuapp.models.CurrentUser;
import com.mtechnologies.martin.bulsuapp.models.MyGrades;
import com.mtechnologies.martin.bulsuapp.models.TermUser;
import com.mtechnologies.martin.bulsuapp.utilities.Callback;
import com.mtechnologies.martin.bulsuapp.utilities.MyGradesCallback;
import com.mtechnologies.martin.bulsuapp.utilities.SessionManager;

import java.util.ArrayList;
import java.util.HashMap;
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
        SessionManager sessionManager=new SessionManager(getContext());
        HashMap<String,String> currentUser=sessionManager.currentUser();
        new MyTermRequest(requestCallback,getContext());

        termView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                 TextView termID=(TextView)view.findViewById(R.id.termCode);
                viewGrades(currentUser.get("PROFILE_ID"),termID.getText().toString(),myGradesCallback);
            }
        });




        return view;
    }
    Callback<List<TermUser>> requestCallback = new Callback<List<TermUser>>() {
        @Override
        public void result(List<TermUser> applicantEvaluations) {
            if (applicantEvaluations != null){
                data.clear();
                data.addAll(applicantEvaluations);
                for(int i=0;i<applicantEvaluations.size();i++){
                    Log.i("Data",data.get(i).getTerm());
                }

                mAdapter.notifyDataSetChanged();
            }

        }

    };

    public void viewGrades(String studentID,String termID,com.mtechnologies.martin.bulsuapp.utilities.Callback callback){
        MyGradesRequest myGradesRequest=new MyGradesRequest(studentID,termID,callback);


    }






    Callback<MyGrades> myGradesCallback=new Callback<MyGrades>() {
        @Override
        public void result(MyGrades myGrades) {
            if(myGrades.isSuccess()){
//                Toast.makeText(getContext(), myGrades.getContent(),
//                        Toast.LENGTH_LONG).show();
                DialogFragment fragment = DialogFragment.newInstance(myGrades.getContent());
                Bundle data=new Bundle();
                data.putString("Grades",myGrades.getContent());
                fragment.setArguments(data);
                fragment.show(getFragmentManager(), "My Grades");
            }
        }
    };


}
