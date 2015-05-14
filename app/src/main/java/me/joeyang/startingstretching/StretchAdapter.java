package me.joeyang.startingstretching;

import android.support.v7.widget.RecyclerView;
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
    private List<Stretch> stretchList;
    public StretchAdapter(List<Stretch> stretchList){
        this.stretchList = stretchList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_stretch, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Stretch stretch = stretchList.get(position);
        holder.imgStretch.setImageResource(stretch.getIconId());
        holder.txtStretch.setText(stretch.getStretchName());

    }

    @Override
    public int getItemCount() {
        return stretchList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView imgStretch;
        public TextView txtStretch;

        public ViewHolder(View itemView) {
            super(itemView);
            imgStretch = (ImageView)itemView.findViewById(R.id.imgStretch);
            txtStretch = (TextView)itemView.findViewById(R.id.txtTitle);

        }
    }

}
