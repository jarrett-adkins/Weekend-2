package com.example.admin.weekendproject2;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CustomAlertFragment extends DialogFragment {

    private static final String TAG = "CustomAlertFrag";

    public CustomAlertFragment() {
        // Required empty public constructor
    }

    static CustomAlertFragment newInstance( int title ) {
        CustomAlertFragment f = new CustomAlertFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("title", title);
        f.setArguments(args);

        return f;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int title = getArguments().getInt("title");

        Log.d(TAG, "onCreateDialog: " + title);

        LayoutInflater inflater = getActivity().getLayoutInflater();
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder( getActivity() );

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.fragment_custome_alert, null))
                // Add action buttons
                .setPositiveButton( "Sign In", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // sign in the user ...
                    }
                })
                .setNegativeButton( "Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        CustomAlertFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();

        /*
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder( getActivity(), android.R.style.Theme_Holo_Dialog);
        } else {
            builder = new AlertDialog.Builder( getActivity() );
        }

        builder.setIcon(R.drawable.icon1)
                .setTitle(title)
                .setMessage( "How about now?" )
                .setNeutralButton( android.R.string.paste,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                //
                            }
                        }
                )
                .setPositiveButton( android.R.string.fingerprint_icon_content_description,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                ((MainActivity)getActivity()).doPositiveClick();
                            }
                        }
                )
                .setNegativeButton( android.R.string.no,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                //((FragmentAlertDialog)getActivity()).doNegativeClick();
                            }
                        }
                );

        return builder.create(); //*/
    }
}
