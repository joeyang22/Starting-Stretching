package me.joeyang.startingstretching;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gc.materialdesign.views.ButtonRectangle;


public class StretchActivity extends ActionBarActivity {
    private static String LOG_TAG = "StretchActivity";

    private static enum TimerState {ON,PAUSE,OFF}

    private int TIMER_MILLISECONDS = 30*1000;
    private int TIMER_INTERVAL = 1000;
    private long currentTime = TIMER_MILLISECONDS;

    private TimerState mTimerState = TimerState.OFF;

    CountDownTimer mCountDownTimer;

    ImageView imgStretch;
    TextView txtStretchTitle;
    TextView txtStretchDescription;
    TextView txtTimer;
    ButtonRectangle btnTimer;
    ButtonRectangle btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stretch);
        Intent intent = getIntent();
        final Resources res = getResources();
        String[] description = res.getStringArray(R.array.array_stretch_instructions);

        int imageId = intent.getIntExtra(res.getString(R.string.key_stretch_icon),R.drawable.ic_logo);


        txtStretchTitle = (TextView) findViewById(R.id.txtStretchTitle);
        txtStretchDescription = (TextView) findViewById(R.id.txtStretchDescription);
        imgStretch = (ImageView) findViewById(R.id.imgStretchBig);
        txtTimer = (TextView) findViewById(R.id.txtTimer);
        btnTimer = (ButtonRectangle) findViewById(R.id.btnTimer);
        btnReset = (ButtonRectangle) findViewById(R.id.btnReset);


        txtStretchTitle.setText(intent.getStringExtra(res.getString(R.string.key_stretch_name)));

        txtStretchDescription.setText(
                res.getStringArray(R.array.array_stretch_instructions)
                        [intent.getIntExtra(res.getString(R.string.key_stretch_id), 0)]);

        imgStretch.setImageResource(imageId);

        btnTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnTimer.setBackgroundColor(res.getColor(R.color.material_red));
                btnTimer.setText("Stop");
                switch (mTimerState) {
                    case OFF:
                        mCountDownTimer = new CountDownTimer(currentTime, TIMER_INTERVAL) {

                            public void onTick(long millisUntilFinished) {
                                txtTimer.setText("Time Remaining: " + millisUntilFinished / 1000);
                                currentTime = millisUntilFinished;
                            }

                            public void onFinish() {
                                txtTimer.setText("Finished!");
                            }
                        }.start();
                        btnReset.setVisibility(View.VISIBLE);

                        mTimerState = TimerState.ON;
                        break;

                    case ON:
                        mCountDownTimer.cancel();
                        btnTimer.setBackgroundColor(res.getColor(R.color.accent_green));
                        btnTimer.setText("Resume");
                        mTimerState = TimerState.OFF;
                        break;

                    case PAUSE:

                }

            }
        });

        btnReset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                btnReset.setVisibility(View.GONE);
                btnTimer.setText("Start");
                btnTimer.setBackgroundColor(res.getColor(R.color.accent_green));
                mCountDownTimer.cancel();
                currentTime = TIMER_MILLISECONDS;
                txtTimer.setText("Time Remaining: "+String.valueOf(currentTime/1000));

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
}
