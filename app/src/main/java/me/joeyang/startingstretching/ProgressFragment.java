package me.joeyang.startingstretching;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.orm.query.Condition;
import com.orm.query.Select;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProgressFragment extends Fragment {
    static LineChart mProgressChart;
    final String LOG_TAG = ProgressFragment.this.getClass().getSimpleName();

    private static ArrayList<Entry> entries = new ArrayList<Entry>();
    private static LineDataSet dataSet;
    private static ArrayList<LineDataSet> dataSets;
    private static ArrayList<String> xVals;
    private static LineData data;


    public ProgressFragment() {
        // Required empty public constructor
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        Log.i(LOG_TAG, String.valueOf(isVisibleToUser));
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            Log.i(LOG_TAG,"Happening");
            updateData();

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_progress, container, false);

        mProgressChart = (LineChart) rootView.findViewById(R.id.progressChart);

        updateData();

        return rootView;
    }

    public static void updateData(){
        int pastWeeks = Utility.getGreaterThan();

        List<FinishedStretch> weeklyStretches = Select.from(FinishedStretch.class)
                .where(Condition.prop("year_day").gt(pastWeeks-1)).list();

        LocalDate localDate = new LocalDate();
        int localDayInt = Utility.formatYearDay(localDate.getDayOfYear(),localDate.getYear());
        int[] secondsPerDay = new int[7];

        int temp;
        for (FinishedStretch fs : weeklyStretches){
            temp = (Utility.getDaysBetween(fs.yearDay, localDayInt));
            secondsPerDay[temp] +=fs.timeStretched;
        }

        //fill chart with values

        entries = new ArrayList<>();

        for (int i=0; i<7; i++){
            entries.add(new Entry(secondsPerDay[i],i));
        }

        //get how much time was stretched in total for the past week

        dataSet = new LineDataSet(entries, "Daily Time Stretched");
        dataSet.setAxisDependency(YAxis.AxisDependency.RIGHT);

        dataSets = new ArrayList<>();
        dataSets.add(dataSet);
        String[] strings = {"a","b","c","d","e","f","g"};
        xVals =  new ArrayList<>(Arrays.asList(strings));

        LineData data = new LineData(xVals, dataSets);

        mProgressChart.setData(data);

        mProgressChart.invalidate();

    }





}
