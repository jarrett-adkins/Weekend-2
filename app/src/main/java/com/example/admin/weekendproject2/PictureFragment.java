package com.example.admin.weekendproject2;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class PictureFragment extends DialogFragment {
    //may need to be static.

    private ImageView imageView;

    public PictureFragment() {
        // Required empty public constructor. Empty constructor is required for DialogFragment. Make
        // sure not to add arguments to the constructor. Use `newInstance` instead as shown below.
    }

    public static PictureFragment newInstance( String title ) {
        PictureFragment p = new PictureFragment();

        Bundle args = new Bundle();
        args.putString("title", title);
        p.setArguments(args);

        return p;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_picture, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //bind the views
        imageView = view.findViewById( R.id.ivDialogImage );

        // Fetch arguments from bundle and set title
        String title = getArguments().getString("title", "Enter Name");
        getDialog().setTitle(title);
    }
}
