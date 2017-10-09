package com.example.admin.weekendproject2;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SMSFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void buttonPressed(View view) {
        switch (view.getId()) {
            case R.id.btnGoToPDF:
                Intent intent = new Intent(this, PDFActivity.class);
                startActivity(intent);
                break;
            case R.id.btnDialogFragment:

                FragmentManager fm = getSupportFragmentManager();
                final PictureFragment pictureFragment = PictureFragment.newInstance("Some Title");
                pictureFragment.show(fm, "fragment_edit_name");

                //pictureFragment.dismiss();

                // Hide after some seconds
                //*
                final Handler handler = new Handler();
                final Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        if (pictureFragment.getShowsDialog()) {
                            pictureFragment.dismiss();
                        }
                    }
                };

                fm.executePendingTransactions();
                pictureFragment.getDialog().setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        handler.removeCallbacks(runnable);
                    }
                });

                handler.postDelayed(runnable, 3000);
                //*/

                break;
            case R.id.btnAlertDialog1:

                AlertDialog.Builder builder;
                //*
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(this);
                }//*/

                builder.setTitle("Alert")
                        .setMessage("Are you alerted?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

                break;
            case R.id.btnAlertDialog2:

                CustomAlertFragment customAlertFragment = CustomAlertFragment
                        .newInstance( R.string.custom_alert_title );
                customAlertFragment.show(getSupportFragmentManager(), "dialog");
                //customAlertFragment.show(getFragmentManager(), "dialog");

                break;
            case R.id.btnAlertDialog3:

                AlertDialog.Builder builder3 = new AlertDialog.Builder( this );
                // Set the dialog title
                builder3.setTitle( "Pick Your Pizza Toppings" )
                        // Specify the list array, the items to be selected by default (null for none),
                        // and the listener through which to receive callbacks when items are selected
                        .setMultiChoiceItems(R.array.toppings, null,
                                new DialogInterface.OnMultiChoiceClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which,
                                                        boolean isChecked) {
                                    }
                                })
                        // Set the action buttons
                        .setPositiveButton( android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                // User clicked OK, so save the mSelectedItems results somewhere
                                // or return them to the component that opened the dialog
                            }
                        })
                        .setNegativeButton( android.R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                //
                            }
                        })
                        .show();

                break;
            case R.id.btnSendNotification:

                String CHANNEL_ID = "my_channel_01";
                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(this)
                                .setSmallIcon(R.drawable.icon1)
                                .setContentTitle("Twisted Tyrants Notification System")
                                .setContentText("Hello World!")
                                .setChannelId(CHANNEL_ID);

                // Creates an explicit intent for an Activity in your app
                Intent resultIntent = new Intent(this, PDFActivity.class);

                // The stack builder object will contain an artificial back stack for the
                // started Activity.
                // This ensures that navigating backward from the Activity leads out of
                // your app to the Home screen.
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);

                // Adds the back stack for the Intent (but not the Intent itself)
                stackBuilder.addParentStack(PDFActivity.class);

                // Adds the Intent that starts the Activity to the top of the stack
                stackBuilder.addNextIntent(resultIntent);
                PendingIntent resultPendingIntent =
                        stackBuilder.getPendingIntent(
                                0,
                                PendingIntent.FLAG_UPDATE_CURRENT
                        );
                mBuilder.setContentIntent(resultPendingIntent);
                NotificationManager mNotificationManager =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                // mNotificationId is a unique integer your app uses to identify the
                // notification. For example, to cancel the notification, you can pass its ID
                // number to NotificationManager.cancel().
                mNotificationManager.notify( 666, mBuilder.build());

                break;
            case R.id.btnGoToTimer:

                Intent timerIntent = new Intent(this, TimerActivity.class);
                startActivity( timerIntent );

                break;
        }
    }

    public void doPositiveClick() {
        Toast.makeText(this, "Positive", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        Toast.makeText(this, "uri", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void sendText(String phoneNumber, String smsBody) {
        //Toast.makeText(this, "ddd" + phoneNumber, Toast.LENGTH_SHORT).show();

        // Get the default instance of SmsManager
        SmsManager smsManager = SmsManager.getDefault();

        // Send a text based SMS
        smsManager.sendTextMessage(phoneNumber, null, smsBody, null, null);
    }
}

/*
Create an android application which
X 1. Have a splash screen when you start the application. Splash screen should have an image like a
     logo displayed to 2 secs and then go to the main activity.
X 2. Have a pdf file viewer. You can save the pdf file in the res folder.
X 3. Have dialog fragment shows a picture for 3 secs.
X 4. Have a AlertDialog.
X    -with default layout
X    -with custom layout
X    -with a list of options to select from (passing xml array)
X 5. Have a feature to send a notification using the NotificationManager and PendingIntent classes. Be
     creative (the user should be able to go to another activity by clicking on the notification)
X 6. Have a feature to send text directly from the app with inputs(Edittext) as “Text” and “Phone
     Number”. Use SmsManager class.
7. Have a activity with two fragments:
     Fragment1: two buttons (Start and Stop)
     Fragment2: textview
   When clicked on the Start, Fragment two should start the counter, when clicked stop the counter
   should be stopped. Use Timer class/TimerTask
 */