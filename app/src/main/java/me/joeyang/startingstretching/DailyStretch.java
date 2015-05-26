package me.joeyang.startingstretching;

import java.util.Date;

/**
 * Created by Joe Yang on 5/25/2015.
 */
public class DailyStretch {
    private boolean[] finished;
    private int[] seconds;
    Date date;

    public DailyStretch(){
        this.finished = new boolean[9];
        this.seconds = new int[9];
        this.date = new Date();

    }

    public boolean[] getFinished() {
        return finished;
    }

    public void setFinished(int pos, boolean value) {
        this.finished[pos] = value;
    }

    public int[] getSeconds() {
        return seconds;
    }

    public void setSeconds(int pos, int value) {
        this.seconds[pos] = value;
    }


}
