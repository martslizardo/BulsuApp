package com.mtechnologies.martin.bulsuapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mtechnologies.martin.bulsuapp.R;
import com.mtechnologies.martin.bulsuapp.models.CurrentUser;

import java.util.List;

/**
 * Created by martin on 4/5/18.
 */

public class ProfileAdapter  extends BaseAdapter {
    private Context context;
    private List<CurrentUser> data;


    public ProfileAdapter(Context context, List<CurrentUser> data){
        this.context = context;
        this.data = data;
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.profile_list_row, parent, false);
        }
        TextView mJobTitleTextView = (TextView)view.findViewById(R.id.profileName);
        TextView profileDefinition = (TextView)view.findViewById(R.id.profileDefinition);
        TextView profileID = (TextView)view.findViewById(R.id.profileID);
        TextView profileAddress = (TextView)view.findViewById(R.id.profileAddress);
        TextView profileEmail = (TextView)view.findViewById(R.id.profileEmail);
        CurrentUser applicantEvaluation = data.get(position);
        mJobTitleTextView.setText(applicantEvaluation.getCurrentuserName());
        profileDefinition.setText(applicantEvaluation.getCurrentUserDefinition());
        profileID.setText(applicantEvaluation.getCurrentUserID());
        profileAddress.setText(applicantEvaluation.getCurrentUserAddress());
        profileEmail.setText(applicantEvaluation.getCurrentUserEmail());
        return view;
    }


}
