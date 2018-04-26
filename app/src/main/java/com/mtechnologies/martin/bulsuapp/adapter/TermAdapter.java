package com.mtechnologies.martin.bulsuapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mtechnologies.martin.bulsuapp.R;
import com.mtechnologies.martin.bulsuapp.models.TermUser;
import com.mtechnologies.martin.bulsuapp.pages.Term;

import java.util.List;

/**
 * Created by martin on 4/8/18.
 */

public class TermAdapter extends BaseAdapter {
    private Context context;
    private List<TermUser> data;


    public TermAdapter(Context context, List<TermUser> data){
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
            view = LayoutInflater.from(context).inflate(R.layout.term_row, parent, false);
        }
        TextView mJobTitleTextView = (TextView)view.findViewById(R.id.termName);
        TextView mJobDateTextView = (TextView)view.findViewById(R.id.termCode);
        TermUser applicantEvaluation = data.get(position);
        mJobTitleTextView.setText(String.valueOf(applicantEvaluation.getTerm()));
        mJobDateTextView.setText(String.valueOf(applicantEvaluation.getTermID()));
        return view;
    }
}
