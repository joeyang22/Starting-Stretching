package me.joeyang.startingstretching;

/**
 * Created by joe on 15-05-13.
 */
public class Stretch {
    private int iconId;
    private String stretchName;
    private boolean bothLimbs;

    public boolean isFinished() {
        return isFinished;
    }

    public void setIsFinished(boolean isFinished) {
        this.isFinished = isFinished;
    }

    private boolean isFinished;

    public Stretch(int iconId, String stretchName, boolean bothLimbs) {
        this.iconId = iconId;
        this.stretchName = stretchName;
        this.bothLimbs = bothLimbs;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public String getStretchName() {
        return stretchName;
    }

    public void setStretchName(String stretchName) {
        this.stretchName = stretchName;
    }

    public boolean isBothLimbs() {
        return bothLimbs;
    }

    public void setBothLimbs(boolean bothLimbs) {
        this.bothLimbs = bothLimbs;
    }
}
