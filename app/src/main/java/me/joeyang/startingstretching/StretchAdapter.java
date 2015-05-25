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


    public StretchAdapter(List<Stretch> stretches, Context context){
        this.stretchList = stretches;
        this.mContext = context;
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
        if (currentItem.isFinished()){
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


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == RESULT_STRETCH) {
            // Make sure the request was successful
            if (resultCode == Activity.RESULT_OK) {
                Log.v(LOG_TAG, "onActivityResult called");
                int position = data.getIntExtra(mContext.getString(R.string.key_stretch_id),0);
                Stretch stretch = stretchList.get(position);
                stretch.setIsFinished(true);
                notifyItemChanged(position);


            }
        }
    }



}
