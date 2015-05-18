package me.joeyang.startingstretching;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;


public class StretchActivity extends ActionBarActivity {
    private static String LOG_TAG = "StretchActivity";
    ImageView imgStretch;
    TextView txtStretchTitle;
    TextView txtStretchDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stretch);
        Intent intent = getIntent();
        Resources res = getResources();
        String[] description = res.getStringArray(R.array.array_stretch_instructions);
        Log.v(LOG_TAG, String.valueOf(intent.getIntExtra(res.getString(R.string.key_stretch_id),2424)));

        txtStretchTitle = (TextView)findViewById(R.id.txtStretchTitle);
        txtStretchDescription = (TextView)findViewById(R.id.txtStretchDescription);
        imgStretch = (ImageView)findViewById(R.id.imgStretch);

        txtStretchTitle.setText(intent.getStringExtra(res.getString(R.string.key_stretch_name)));
//
        txtStretchDescription.setText(
                res.getStringArray(R.array.array_stretch_instructions)
                [intent.getIntExtra(res.getString(R.string.key_stretch_id),0)]);
//
//        imgStretch.setImageResource(intent.getIntExtra(res.getString(R.string.key_stretch_icon),
//                R.drawable.ic_logo));
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
