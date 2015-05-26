package me.joeyang.startingstretching;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gc.materialdesign.views.ButtonRectangle;


public class StretchActivity extends ActionBarActivity {
    private static String LOG_TAG = "StretchActivity";

    private static enum TimerState {ON,PAUSE,OFF}

    final long TIMER_MILLISECONDS = 30*1000;
    //This needs to be 1 second less than total time because onTick is called once after its at 0
    final int TIMER_INTERVAL = 250;
    //Would use a round 1000ms, but android is pretty inaccurate so we need this for rounding reasons
    private long currentTime = TIMER_MILLISECONDS;


    private int finishedStretchCount = 0;

    private TimerState mTimerState = TimerState.OFF;

    StretchCountDownTimer mStretchCountDownTimer = new StretchCountDownTimer(TIMER_MILLISECONDS, TIMER_INTERVAL);


    ImageView imgStretch;
    TextView txtStretchTitle;
    TextView txtStretchDescription;
    TextView txtTimer;
    ButtonRectangle btnTimer;
    ButtonRectangle btnReset;
    ButtonRectangle btnFinish;

    Resources res;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stretch);
        res = getResources();
        intent = getIntent();
        String[] description = res.getStringArray(R.array.array_stretch_instructions);
        int imageId = intent.getIntExtra(res.getString(R.string.key_stretch_icon), R.drawable.ic_logo);

        //Find Views

        txtStretchTitle = (TextView) findViewById(R.id.txtStretchTitle);
        txtStretchDescription = (TextView) findViewById(R.id.txtStretchDescription);
        imgStretch = (ImageView) findViewById(R.id.imgStretchBig);
        txtTimer = (TextView) findViewById(R.id.txtTimer);
        btnTimer = (ButtonRectangle) findViewById(R.id.btnTimer);
        btnReset = (ButtonRectangle) findViewById(R.id.btnReset);
        btnFinish = (ButtonRectangle) findViewById(R.id.btnDone);

        //Set Views

        txtStretchTitle.setText(intent.getStringExtra(res.getString(R.string.key_stretch_name)));

        txtStretchDescription.setText(
                res.getStringArray(R.array.array_stretch_instructions)
                        [intent.getIntExtra(res.getString(R.string.key_stretch_id), 0)]);

        imgStretch.setImageResource(imageId);

        // Button Logic for the timer
        btnTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnTimer.setBackgroundColor(res.getColor(R.color.material_red));
                btnTimer.setText("Stop");
                switch (mTimerState) {
                    case OFF:
                        mStretchCountDownTimer.start();
                        btnReset.setVisibility(View.VISIBLE);
                        btnFinish.setVisibility(View.GONE);
                        mTimerState = TimerState.ON;
                        break;
                    case ON:
                        mStretchCountDownTimer.cancel();
                        btnTimer.setBackgroundColor(res.getColor(R.color.accent_green));
                        btnTimer.setText("Resume");
                        btnReset.setVisibility(View.VISIBLE);
                        mTimerState = TimerState.OFF;
                        break;
                }

            }
        });

        btnReset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                btnReset.setVisibility(View.GONE);
                btnTimer.setText("Start");
                btnTimer.setBackgroundColor(res.getColor(R.color.accent_green));
                mStretchCountDownTimer.cancel();
                currentTime = TIMER_MILLISECONDS;
                txtTimer.setText("Time Remaining: " + String.valueOf(currentTime / 1000));
                mTimerState = TimerState.OFF;

            }
        });

        btnFinish.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_stretch, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class StretchCountDownTimer extends CountDownTimer{

        int secondsLeft =0;

        public StretchCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            //The CountDownTimer isn't very reliable, so instead we check multiple times in a second
            //ms until finished counts down, once it rounds down to a whole number (secondsLeft starts at 0, will go to the total seconds
            if (Math.round((float) millisUntilFinished / 1000.0f)!= secondsLeft) {
                secondsLeft = Math.round((float)millisUntilFinished/1000.0f);
                txtTimer.setText("Time Remaining: " +secondsLeft );
                currentTime = millisUntilFinished;
            }
            Log.i("test","ms="+millisUntilFinished+" till finished="+secondsLeft );

        }

        @Override
        public void onFinish() {
            txtTimer.setText("Finished!");
            btnReset.setVisibility(View.GONE);
            btnTimer.setText("Start");
            btnTimer.setBackgroundColor(res.getColor(R.color.accent_green));
            btnFinish.setVisibility(View.VISIBLE);
            mTimerState = TimerState.OFF;
            currentTime = TIMER_MILLISECONDS;

            //Validate you've finished a stretch
            finishedStretchCount+=TIMER_MILLISECONDS/1000;
            intent.putExtra(res.getString(R.string.key_time_stretched), finishedStretchCount);
            setResult(RESULT_OK, intent);


        }
    }

}
