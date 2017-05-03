package quotes.wakeupwithinspirationalquotes.inspiration;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private TextView  txttime,txtquotes ;

    TimePickerDialog timePickerDialog;

    final static int RQS_1 = 1;
    String[] quotesArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MobileAds.initialize(getApplicationContext(), "ca-app-pub-0954165513199363~9071079983");
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        txttime=(TextView) findViewById(R.id.settime_txt);
        txtquotes=(TextView) findViewById(R.id.txt_quotes);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener
                (
                        new BottomNavigationView.OnNavigationItemSelectedListener()
                        {
                            @Override
                            public boolean onNavigationItemSelected(@NonNull MenuItem item)
                            {
                                switch (item.getItemId())
                                {
                                    case R.id.exit:
                                        finish();
                                        System.exit(0);
                                        return true;
                                    case R.id.setalarm:
                                        openTimePickerDialog(false);
                                        return true;
                                    case R.id.cancellalarm:
                                        cancelAlarm();
                                        return true;
                                }
                                return false;
                            }
                        });





        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String mString= txtquotes.getText().toString();
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Alarm with Quotes 2017");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, mString);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });

        quotesArray =   getResources().getStringArray(R.array.quotes);
        quotesshow();

    }


    private void openTimePickerDialog(boolean is24r)
    {
        Calendar calendar = Calendar.getInstance();

        timePickerDialog = new TimePickerDialog
                (
                        MainActivity.this,
                        onTimeSetListener,
                        calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE),
                        is24r);
        timePickerDialog.setTitle("Set Alarm Time");

        timePickerDialog.show();

    }

    TimePickerDialog.OnTimeSetListener onTimeSetListener
            = new TimePickerDialog.OnTimeSetListener()
    {

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute)
        {

            Calendar calNow = Calendar.getInstance();
            Calendar calSet = (Calendar) calNow.clone();

            calSet.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calSet.set(Calendar.MINUTE, minute);
            calSet.set(Calendar.SECOND, 0);
            calSet.set(Calendar.MILLISECOND, 0);

            if(calSet.compareTo(calNow) <= 0)
            {
                //Today Set time passed, count to tomorrow
                calSet.add(Calendar.DATE, 1);
            }

            setAlarm(calSet);
        }};

    private void setAlarm(Calendar targetCal)
    {

        txttime.setText(
                "\n\n***\n"
                        + "Alarm is set@ " + targetCal.getTime() + "\n"
                        + "***\n");

        Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), RQS_1, intent, 0);
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), pendingIntent);

    }

    private void cancelAlarm()
    {

        txttime.setText(
                "\n\n***\n"
                        + "Alarm Cancelled! \n"
                        + "***\n");

        Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), RQS_1, intent, 0);
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);

    }


    public void quotesshow()
    {


        Calendar cal = Calendar.getInstance();
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        if (dayOfMonth==1)
        {
            txtquotes.post(new Runnable() {
                int i = 0;
                @Override
                public void run() {
                    txtquotes.setText(quotesArray[i]);
                    i++;
                    if (i ==2)
                        i = 0;
                    txtquotes.postDelayed(this, 5000);
                }
            });
        }
        else if (dayOfMonth==2)
        {
            txtquotes.post(new Runnable() {
                int i = 3;
                @Override
                public void run() {
                    txtquotes.setText(quotesArray[i]);
                    i++;
                    if (i ==5)
                        i = 3;
                    txtquotes.postDelayed(this, 5000);
                }
            });
        }
        else if (dayOfMonth==3)
        {
            txtquotes.post(new Runnable() {
                int i = 6;
                @Override
                public void run() {
                    txtquotes.setText(quotesArray[i]);
                    i++;
                    if (i ==8)
                        i = 6;
                    txtquotes.postDelayed(this, 5000);
                }
            });
        }

        else if (dayOfMonth==4)
        {
            txtquotes.post(new Runnable() {
                int i = 9;
                @Override
                public void run() {
                    txtquotes.setText(quotesArray[i]);
                    i++;
                    if (i ==11)
                        i = 9;
                    txtquotes.postDelayed(this, 5000);
                }
            });
        }
        else if (dayOfMonth==5)
        {
            txtquotes.post(new Runnable() {
                int i = 12;
                @Override
                public void run() {
                    txtquotes.setText(quotesArray[i]);
                    i++;
                    if (i ==14)
                        i = 12;
                    txtquotes.postDelayed(this, 5000);
                }
            });
        }
        else if (dayOfMonth==6)
        {
            txtquotes.post(new Runnable() {
                int i = 15;
                @Override
                public void run() {
                    txtquotes.setText(quotesArray[i]);
                    i++;
                    if (i ==17)
                        i = 15;
                    txtquotes.postDelayed(this, 5000);
                }
            });
        }
        else if (dayOfMonth==7)
        {
            txtquotes.post(new Runnable() {
                int i = 18;
                @Override
                public void run() {
                    txtquotes.setText(quotesArray[i]);
                    i++;
                    if (i ==20)
                        i = 18;
                    txtquotes.postDelayed(this, 5000);
                }
            });
        }

        else if (dayOfMonth==8)
        {
            txtquotes.post(new Runnable() {
                int i = 21;
                @Override
                public void run() {
                    txtquotes.setText(quotesArray[i]);
                    i++;
                    if (i ==23)
                        i = 21;
                    txtquotes.postDelayed(this, 5000);
                }
            });
        }

        else if (dayOfMonth==9)
        {
            txtquotes.post(new Runnable() {
                int i = 24;
                @Override
                public void run() {
                    txtquotes.setText(quotesArray[i]);
                    i++;
                    if (i ==26)
                        i = 24;
                    txtquotes.postDelayed(this, 5000);
                }
            });
        }
        else if (dayOfMonth==10)
        {
            txtquotes.post(new Runnable() {
                int i = 27;
                @Override
                public void run() {
                    txtquotes.setText(quotesArray[i]);
                    i++;
                    if (i ==29)
                        i = 27;
                    txtquotes.postDelayed(this, 5000);
                }
            });
        }
        else if (dayOfMonth==10)
        {
            txtquotes.post(new Runnable() {
                int i = 27;
                @Override
                public void run() {
                    txtquotes.setText(quotesArray[i]);
                    i++;
                    if (i ==29)
                        i = 27;
                    txtquotes.postDelayed(this, 5000);
                }
            });
        }
        else if (dayOfMonth==11)
        {
            txtquotes.post(new Runnable() {
                int i = 30;
                @Override
                public void run() {
                    txtquotes.setText(quotesArray[i]);
                    i++;
                    if (i ==32)
                        i = 30;
                    txtquotes.postDelayed(this, 5000);
                }
            });
        }
        else if (dayOfMonth==12)
        {
            txtquotes.post(new Runnable() {
                int i = 33;
                @Override
                public void run() {
                    txtquotes.setText(quotesArray[i]);
                    i++;
                    if (i ==35)
                        i = 33;
                    txtquotes.postDelayed(this, 5000);
                }
            });
        }
        else if (dayOfMonth==13)
        {
            txtquotes.post(new Runnable() {
                int i = 36;
                @Override
                public void run() {
                    txtquotes.setText(quotesArray[i]);
                    i++;
                    if (i ==38)
                        i = 36;
                    txtquotes.postDelayed(this, 5000);
                }
            });
        }
        else if (dayOfMonth==14)
        {
            txtquotes.post(new Runnable() {
                int i = 39;
                @Override
                public void run() {
                    txtquotes.setText(quotesArray[i]);
                    i++;
                    if (i ==41)
                        i = 39;
                    txtquotes.postDelayed(this, 5000);
                }
            });
        }
        else if (dayOfMonth==15)
        {
            txtquotes.post(new Runnable() {
                int i = 42;
                @Override
                public void run() {
                    txtquotes.setText(quotesArray[i]);
                    i++;
                    if (i ==44)
                        i = 42;
                    txtquotes.postDelayed(this, 5000);
                }
            });
        }
        else if (dayOfMonth==16)
        {
            txtquotes.post(new Runnable() {
                int i = 45;
                @Override
                public void run() {
                    txtquotes.setText(quotesArray[i]);
                    i++;
                    if (i ==2)
                        i = 45;
                    txtquotes.postDelayed(this, 5000);
                }
            });
        }
        else if (dayOfMonth==17)
        {
            txtquotes.post(new Runnable() {
                int i = 3;
                @Override
                public void run() {
                    txtquotes.setText(quotesArray[i]);
                    i++;
                    if (i ==5)
                        i = 3;
                    txtquotes.postDelayed(this, 5000);
                }
            });
        }
        else if (dayOfMonth==18)
        {
            txtquotes.post(new Runnable() {
                int i = 6;
                @Override
                public void run() {
                    txtquotes.setText(quotesArray[i]);
                    i++;
                    if (i ==8)
                        i = 6;
                    txtquotes.postDelayed(this, 5000);
                }
            });
        }
        else if (dayOfMonth==19)
        {
            txtquotes.post(new Runnable() {
                int i = 9;
                @Override
                public void run() {
                    txtquotes.setText(quotesArray[i]);
                    i++;
                    if (i ==11)
                        i = 9;
                    txtquotes.postDelayed(this, 5000);
                }
            });
        }
        else if (dayOfMonth==20)
        {
            txtquotes.post(new Runnable() {
                int i = 12;
                @Override
                public void run() {
                    txtquotes.setText(quotesArray[i]);
                    i++;
                    if (i ==14)
                        i = 12;
                    txtquotes.postDelayed(this, 5000);
                }
            });
        }
        else if (dayOfMonth==21)
        {
            txtquotes.post(new Runnable() {
                int i = 15;
                @Override
                public void run() {
                    txtquotes.setText(quotesArray[i]);
                    i++;
                    if (i ==17)
                        i = 15;
                    txtquotes.postDelayed(this, 5000);
                }
            });
        }
        else if (dayOfMonth==22)
        {
            txtquotes.post(new Runnable() {
                int i = 18;
                @Override
                public void run() {
                    txtquotes.setText(quotesArray[i]);
                    i++;
                    if (i ==20)
                        i = 18;
                    txtquotes.postDelayed(this, 5000);
                }
            });
        }
        else if (dayOfMonth==23)
        {
            txtquotes.post(new Runnable() {
                int i = 21;
                @Override
                public void run() {
                    txtquotes.setText(quotesArray[i]);
                    i++;
                    if (i ==23)
                        i = 21;
                    txtquotes.postDelayed(this, 5000);
                }
            });
        }

        else if (dayOfMonth==24)
        {
            txtquotes.post(new Runnable() {
                int i = 24;
                @Override
                public void run() {
                    txtquotes.setText(quotesArray[i]);
                    i++;
                    if (i ==26)
                        i = 24;
                    txtquotes.postDelayed(this, 5000);
                }
            });
        }
        else if (dayOfMonth==25)
        {
            txtquotes.post(new Runnable() {
                int i = 27;
                @Override
                public void run() {
                    txtquotes.setText(quotesArray[i]);
                    i++;
                    if (i ==29)
                        i = 27;
                    txtquotes.postDelayed(this, 5000);
                }
            });
        }
        else if (dayOfMonth==26)
        {
            txtquotes.post(new Runnable() {
                int i = 30;
                @Override
                public void run() {
                    txtquotes.setText(quotesArray[i]);
                    i++;
                    if (i ==32)
                        i = 30;
                    txtquotes.postDelayed(this, 5000);
                }
            });
        }
        else if (dayOfMonth==27)
        {
            txtquotes.post(new Runnable() {
                int i = 33;
                @Override
                public void run() {
                    txtquotes.setText(quotesArray[i]);
                    i++;
                    if (i ==35)
                        i = 33;
                    txtquotes.postDelayed(this, 5000);
                }
            });
        }
        else if (dayOfMonth==28)
        {
            txtquotes.post(new Runnable() {
                int i = 36;
                @Override
                public void run() {
                    txtquotes.setText(quotesArray[i]);
                    i++;
                    if (i ==38)
                        i = 36;
                    txtquotes.postDelayed(this, 5000);
                }
            });
        }
        else if (dayOfMonth==29)
        {
            txtquotes.post(new Runnable() {
                int i = 39;
                @Override
                public void run() {
                    txtquotes.setText(quotesArray[i]);
                    i++;
                    if (i ==41)
                        i = 39;
                    txtquotes.postDelayed(this, 5000);
                }
            });
        }

        else if (dayOfMonth==30)
        {
            txtquotes.post(new Runnable() {
                int i = 42;
                @Override
                public void run() {
                    txtquotes.setText(quotesArray[i]);
                    i++;
                    if (i ==45)
                        i = 42;
                    txtquotes.postDelayed(this, 5000);
                }
            });
        }
        else if (dayOfMonth==31)
        {
            txtquotes.post(new Runnable() {
                int i = 1;
                @Override
                public void run() {
                    txtquotes.setText(quotesArray[i]);
                    i++;
                    if (i ==5)
                        i = 1;
                    txtquotes.postDelayed(this, 5000);
                }
            });
        }


    }



}

