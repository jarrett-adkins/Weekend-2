package com.example.admin.weekendproject2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TimerActivity extends AppCompatActivity implements TimerButtonFragment.OnFragmentInteractionListener {

    private TimerButtonFragment timerButtonFragment;
    private TimerDisplayFragment timerDisplayFragment;

    public static final String BUTTON_FRAG_TAG = "buttonFragmentTag";
    public static final String DISPLAY_FRAG_TAG = "displayFragmentTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        timerButtonFragment = new TimerButtonFragment();
        getSupportFragmentManager().beginTransaction()
                .add( R.id.flTimer, timerButtonFragment, BUTTON_FRAG_TAG )
                .addToBackStack( BUTTON_FRAG_TAG )
                .commit();

        timerDisplayFragment = new TimerDisplayFragment();
        getSupportFragmentManager().beginTransaction()
                .add( R.id.flDisplay, timerDisplayFragment, DISPLAY_FRAG_TAG )
                .addToBackStack( DISPLAY_FRAG_TAG )
                .commit();
    }

    @Override
    public void onFragmentInteraction(int i) {
        timerDisplayFragment.Display(i);
    }
}
