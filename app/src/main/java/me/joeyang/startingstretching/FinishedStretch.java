package me.joeyang.startingstretching;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import org.joda.time.LocalDate;

/**
 * Created by Joe Yang on 6/21/2015.
 */
public class FinishedStretch extends SugarRecord<FinishedStretch> {
    int stretchId;
    int timeStretched;
    int yearDay;
    int dayOfYear;
    int year;

    @Ignore
    LocalDate dateFinished;

    public FinishedStretch(){
    }

    public FinishedStretch(int id, int seconds){
        this.stretchId = id;
        this.dateFinished = new LocalDate();
        this.dayOfYear = dateFinished.getDayOfYear();
        this.year = dateFinished.getYear();
        this.yearDay = Utility.formatYearDay(dayOfYear,year);
        this.timeStretched = seconds;
    }



}
