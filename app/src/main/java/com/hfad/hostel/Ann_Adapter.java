package com.hfad.hostel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Ann_Adapter extends RecyclerView.Adapter<Ann_Adapter.viewHolder> {

    ArrayList<Ann_Model> AnnList;
    Context context;

    public Ann_Adapter(ArrayList<Ann_Model> annList, Context context) {
        AnnList = annList;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.announcement_row,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Ann_Model ann_model=AnnList.get(position);
        holder.title.setText(ann_model.getTitle());
        holder.disc.setText(ann_model.getDisc());

    }

    @Override
    public int getItemCount() {
        return AnnList.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        TextView title,disc;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            title=itemView.findViewById(R.id.tv_title);
            disc=itemView.findViewById(R.id.tv_disc);
        }
    }
}
