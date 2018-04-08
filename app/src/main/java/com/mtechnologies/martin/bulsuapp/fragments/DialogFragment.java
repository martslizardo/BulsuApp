package com.mtechnologies.martin.bulsuapp.fragments;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mtechnologies.martin.bulsuapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialogFragment extends android.app.DialogFragment {


    public static DialogFragment newInstance(String message){

        DialogFragment fragment = new DialogFragment();
        Bundle args = new Bundle();
        args.putString("Message", message);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(getArguments().getString("Message"));
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }

        });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
