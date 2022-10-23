package com.example.easeoffapplication.EatHealthy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easeoffapplication.R;

import java.util.ArrayList;

public class ViewCaloryAdaptor extends RecyclerView.Adapter<ViewCaloryAdaptor.MyViewHolder> {

    private Context context;
    private ArrayList Dates,Totals,Comments;

    ViewCaloryAdaptor(Context context,ArrayList Dates,ArrayList Totals,ArrayList Comments){
        this.context=context;
        this.Dates=Dates;
        this.Totals=Totals;
        this.Comments=Comments;
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
    }

    @Override
    public int getItemCount() {
        return Dates.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView date,totalCal,comment;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            date=itemView.findViewById(R.id.tvDtae);
            totalCal=itemView.findViewById(R.id.tvTotalCal);
            comment=itemView.findViewById(R.id.tvCmt);
        }
    }
}
