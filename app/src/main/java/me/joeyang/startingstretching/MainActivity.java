package me.joeyang.startingstretching;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    StretchPageAdapter mStretchPageAdapter;
    ViewPager mViewPager;
    static DailyStretch dailyStretchList = new DailyStretch();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStretchPageAdapter = new StretchPageAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mStretchPageAdapter);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == StretchAdapter.RESULT_STRETCH) {
            // Make sure the request was successful
            if (resultCode == Activity.RESULT_OK) {
                Log.v("ASDF", "onActivityResult called");
                int position = data.getIntExtra(getString(R.string.key_stretch_id),0);
                int timeCount = data.getIntExtra(getString(R.string.key_time_stretched),30);
                dailyStretchList.setFinished(position, true);
                dailyStretchList.setSeconds(position, timeCount);


            }
        }
    }


}
