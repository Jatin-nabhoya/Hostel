package com.hfad.hostel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Com_Adapter extends RecyclerView.Adapter<Com_Adapter.viewHolder> {

    ArrayList<Com_Model> ComList;
    Context context;

    public Com_Adapter(ArrayList<Com_Model> comList, Context context) {
        ComList = comList;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.complain_row,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Com_Model com_model=ComList.get(position);
        holder.room_No.setText(com_model.getRoomNo());
        holder.name.setText(com_model.getFname());
        holder.complain.setText(com_model.getComplain());

    }

    @Override
    public int getItemCount() {
        return ComList.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        TextView room_No,name,complain;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            room_No=itemView.findViewById(R.id.tv_roomNo);
            name=itemView.findViewById(R.id.tv_name);
            complain=itemView.findViewById(R.id.tv_complain);
        }
    }
}
