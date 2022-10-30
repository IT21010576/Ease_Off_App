package com.example.easeoffapplication.mindandyou;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easeoffapplication.R;

import java.util.ArrayList;

public class CustomAdapterMindMeditationTime extends RecyclerView.Adapter<CustomAdapterMindMeditationTime.MedTimesHolder> {


    private Context context;

    private ArrayList meditate_id,meditate_date, meditate_duration;

    Activity activity;
    Animation translate_anim;
    CustomAdapterMindMeditationTime(Activity activity,
                                    Context context,
                                    ArrayList meditate_id,
                                    ArrayList meditate_date, ArrayList meditate_duration){

        this.activity = activity;
        this.context = context;
        this.meditate_id = meditate_id;
        this.meditate_date= meditate_date;
        this.meditate_duration = meditate_duration;
     }

    @NonNull
    @Override
    public CustomAdapterMindMeditationTime.MedTimesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.mind_meditation_time_row,parent,false);
        return new MedTimesHolder(view);
    }

    @Override
    public  void onBindViewHolder(@NonNull MedTimesHolder holder,final int position){
        holder.mind_meditate_times_id.setText((String.valueOf(meditate_id.get(position))));
        holder.mind_meditate_times_date.setText(String.valueOf(meditate_date.get(position)));
        holder.mind_meditate_times_duration.setText(String.valueOf(meditate_duration.get(position)));

    }


    @Override
    public int getItemCount(){
        return meditate_id.size();
    }

    public class MedTimesHolder extends RecyclerView.ViewHolder{

        TextView mind_meditate_times_id, mind_meditate_times_date, mind_meditate_times_duration;
        LinearLayout meditation_row_linear_l;
        public MedTimesHolder(@NonNull View itemView) {
            super(itemView);
            mind_meditate_times_id = itemView.findViewById(R.id.mind_meditate_times_card_date);
            mind_meditate_times_date = itemView.findViewById(R.id.mind_meditate_times_card_date);
            mind_meditate_times_duration = itemView.findViewById(R.id.mind_meditate_times_card_duration);
            meditation_row_linear_l = itemView.findViewById(R.id.meditation_row_linear_layout);

            translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            meditation_row_linear_l.setAnimation(translate_anim);
        }
    }
}
