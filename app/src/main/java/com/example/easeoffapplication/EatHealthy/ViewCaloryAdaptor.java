package com.example.easeoffapplication.EatHealthy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easeoffapplication.R;

import java.util.ArrayList;

public class ViewCaloryAdaptor extends RecyclerView.Adapter<ViewCaloryAdaptor.MyViewHolder> {

    private Context context;
    private ArrayList Dates,Totals,Comments,ID;
    Activity activity;

    ViewCaloryAdaptor(Activity activity,Context context,ArrayList Dates,ArrayList Totals,ArrayList Comments,ArrayList ID){
        this.activity=activity;
        this.context=context;
        this.Dates=Dates;
        this.Totals=Totals;
        this.Comments=Comments;
        this.ID=ID;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.viewcalorierow,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.date.setText(String.valueOf(Dates.get(position)));
        holder.totalCal.setText(String.valueOf(Totals.get(position)));
        holder.comment.setText(String.valueOf(Comments.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,Update_trackCalorie.class);
                intent.putExtra("date",String.valueOf(Dates.get(holder.getAdapterPosition())));
                intent.putExtra("totalCal",String.valueOf(Totals.get(holder.getAdapterPosition())));
                intent.putExtra("comment",String.valueOf(Comments.get(holder.getAdapterPosition())));
                intent.putExtra("id",String.valueOf(ID.get(holder.getAdapterPosition())));
                activity.startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return Dates.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView date,totalCal,comment;
        ConstraintLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            date=itemView.findViewById(R.id.tvDtae);
            totalCal=itemView.findViewById(R.id.tvTotalCal);
            comment=itemView.findViewById(R.id.tvCmt);
            mainLayout=itemView.findViewById(R.id.update_tc_layout);
        }
    }
}
