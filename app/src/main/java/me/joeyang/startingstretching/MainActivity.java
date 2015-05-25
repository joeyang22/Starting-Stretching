package me.joeyang.startingstretching;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private RecyclerView mRecyclerView;
    private StretchAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Stretch> stretchList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stretchList = new ArrayList<Stretch>();
        stretchList.add(new Stretch(R.drawable.ic_logo, "Back Stretch", false));
        stretchList.add(new Stretch(R.drawable.ic_logo, "Front Stretch", false));
        stretchList.add(new Stretch(R.drawable.ic_logo, "Rear Stretch", false));
        stretchList.add(new Stretch(R.drawable.ic_logo, "Side Stretch", false));
        stretchList.add(new Stretch(R.drawable.ic_logo, "Side Stretch", false));

        mAdapter = new StretchAdapter(stretchList, this);

        mRecyclerView = (RecyclerView) findViewById(R.id.rvStretches);
        mRecyclerView.setHasFixedSize(true);


        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setAdapter(mAdapter);




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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mAdapter.onActivityResult(requestCode, resultCode, data);
    }

}
