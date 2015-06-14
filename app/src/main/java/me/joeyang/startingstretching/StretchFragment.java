package me.joeyang.startingstretching;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gc.materialdesign.views.ButtonRectangle;
import com.gc.materialdesign.widgets.Dialog;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Joe Yang on 6/14/2015.
 */
public class StretchFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private StretchAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Stretch> stretchList;
    ButtonRectangle mProgressButton;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);


    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        View rootView = inflater.inflate(R.layout.fragment_stretches, container, false);
        stretchList = new ArrayList<Stretch>();
        stretchList.add(new Stretch(R.drawable.stretch_shoulder_extension, "Overhead Shoulder Extension", false));
        stretchList.add(new Stretch(R.drawable.ic_logo, "Front Stretch", false));
        stretchList.add(new Stretch(R.drawable.ic_logo, "Rear Stretch", false));
        stretchList.add(new Stretch(R.drawable.ic_logo, "Side Stretch", false));
        stretchList.add(new Stretch(R.drawable.ic_logo, "Side Stretch", false));

        mAdapter = new StretchAdapter(stretchList, getActivity());
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.rvStretches);
        mRecyclerView.setHasFixedSize(true);


        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setAdapter(mAdapter);

        mProgressButton = (ButtonRectangle) rootView.findViewById(R.id.btnTrack);
        mProgressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = "";
                for (int i = 0; i < 9; i++) {
                    text += "Stretch " + i + 1 + " ; " + StretchAdapter.dailyStretchList.getFinished()[i] + " for " + StretchAdapter.dailyStretchList.getSeconds()[i] + " seconds\n";
                }
                Dialog dialog = new Dialog(getActivity(), "Time done", text);
                dialog.show();

            }
        });
        return rootView;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mAdapter.onActivityResult(requestCode, resultCode, data);
    }
}
