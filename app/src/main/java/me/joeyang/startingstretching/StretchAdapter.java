package me.joeyang.startingstretching;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.joda.time.LocalDate;

import java.util.List;

/**
 * Created by joe on 15-05-13.
 */
public class StretchAdapter extends RecyclerView.Adapter<StretchAdapter.ViewHolder> {
    private static String LOG_TAG = "StretchAdapter";
    static final int RESULT_STRETCH = 1;
    private static List<Stretch> stretchList;
    private static Stretch currentItem;
    private static int currentItemId;
    private static Context mContext;
    private int yearDay;
    private LocalDate currentDate;



    public StretchAdapter(List<Stretch> stretches, Context context){
        this.stretchList = stretches;
        this.mContext = context;
        this.currentDate = new LocalDate();
        this.yearDay = Utility.formatYearDay(currentDate.getDayOfYear(), currentDate.getYear());
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_stretch, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        currentItem = stretchList.get(position);
        holder.imgStretch.setImageResource(currentItem.getIconId());
        holder.txtStretch.setText(currentItem.getStretchName());
        Log.v(LOG_TAG, "This is being called");
        if (FinishedStretch.find(FinishedStretch.class, "stretch_Id = ? and year_Day = ?",String.valueOf(position),String.valueOf(yearDay)).size()>0){
            holder.imgFinished.setVisibility(View.VISIBLE);
        }


    }
    @Override
    public int getItemCount() {
        return stretchList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgStretch;
        TextView txtStretch;
        ImageView imgFinished;

        public ViewHolder(View itemView) {
            super(itemView);
            imgStretch = (ImageView)itemView.findViewById(R.id.imgStretch);
            txtStretch = (TextView)itemView.findViewById(R.id.txtTitle);
            imgFinished = (ImageView)itemView.findViewById(R.id.imgFinished);
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    currentItem = stretchList.get(getPosition());
                    currentItemId = getPosition();
                    Intent intent = new Intent(mContext, StretchActivity.class);
                    intent.putExtra(mContext.getString(R.string.key_stretch_name), currentItem.getStretchName());
                    intent.putExtra(mContext.getString(R.string.key_stretch_icon), currentItem.getIconId());
                    intent.putExtra(mContext.getString(R.string.key_stretch_id), currentItemId);
                    ((Activity) mContext).startActivityForResult(intent, RESULT_STRETCH);


                }
            });
        }




    }






}
