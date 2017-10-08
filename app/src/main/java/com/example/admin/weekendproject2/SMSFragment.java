package com.example.admin.weekendproject2;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SMSFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "SMS_Frag";
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;

    EditText number, message;
    Button button;

    public SMSFragment() {
        // Required empty public constructor
    }

    public static SMSFragment newInstance(String param1, String param2) {
        Log.d(TAG, "newInstance: ");

        SMSFragment fragment = new SMSFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");

        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sm, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.d(TAG, "onViewCreated: ");

        number = view.findViewById( R.id.etPhoneNumber );
        message = view.findViewById( R.id.etText );
        button = view.findViewById( R.id.btnSendText );
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Log.d(TAG, "onActivityCreated: ");

        button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( mListener != null )
                    mListener.sendText( number.getText().toString(), message.getText().toString() );
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        Log.d(TAG, "onAttach: ");

        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
        void sendText(String phoneNumber, String smsBody);
    }
}
