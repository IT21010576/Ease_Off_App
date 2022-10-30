package com.example.easeoffapplication.mindandyou;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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

public class CustomAdapterMindTJ extends RecyclerView.Adapter<CustomAdapterMindTJ.MindJournalHolder> {

    private Context context;
    private ArrayList mj_id, mj_date,mj_rate,mj_note;
////
    int position;
    Activity activity;
    Animation translate_anim;
    //int position;
    CustomAdapterMindTJ(
                        Activity activity,
                        Context context,
                        ArrayList mj_id,
                        ArrayList mj_date,
                        ArrayList mj_rate,
                        ArrayList mj_note){
    this.activity = activity;
        this.context = context;
        this.mj_id = mj_id;
        this.mj_date = mj_date;
        this.mj_rate = mj_rate;
        this.mj_note = mj_note;
    }

    @NonNull
    @Override
    public MindJournalHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.mind_journal_row,parent,false);
        return new MindJournalHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MindJournalHolder holder,final int position) {
        ////
        this.position= position;
        holder.mj_id_txt.setText(String.valueOf(mj_id.get(position)));
        holder.mj_date_txt.setText(String.valueOf(mj_date.get(position)));
        holder.mj_rate_txt.setText(String.valueOf(mj_rate.get(position)));
        holder.mj_note_txt.setText(String.valueOf(mj_note.get(position)));
//        holder.tj_row_linear_l.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent(context, MindThoughtJournalUpdate.class);
//                intent.putExtra("id", String.valueOf(mj_id.get(position)));
//                intent.putExtra("date", String.valueOf(mj_date.get(position)));
//                intent.putExtra("rate", String.valueOf(mj_rate.get(position)));
//                intent.putExtra("note", String.valueOf(mj_note.get(position)));
//                //intent.putExtra("tj_note",String.valueOf(mj_note.get(position)));
//
//                activity.startActivityForResult(intent, 1);
//            }
//        });
        holder.tj_row_linear_l.setOnClickListener((view)->{
            Intent intent = new Intent(context, MindThoughtJournalUpdate.class);
                intent.putExtra("id", String.valueOf(mj_id.get(position)));
                intent.putExtra("date", String.valueOf(mj_date.get(position)));
                intent.putExtra("rate", String.valueOf(mj_rate.get(position)));
               intent.putExtra("note", String.valueOf(mj_note.get(position)));
            activity.startActivityForResult(intent, 1);

        });
    }

    @Override
    public int getItemCount() {
        return mj_id.size();
    }

    public class MindJournalHolder extends RecyclerView.ViewHolder{
        TextView mj_id_txt, mj_date_txt, mj_rate_txt, mj_note_txt;
        LinearLayout tj_row_linear_l;

        public MindJournalHolder(@NonNull View itemView) {
            super(itemView);
            mj_date_txt = itemView.findViewById(R.id.mind_journal_card_date);
            mj_rate_txt = itemView.findViewById(R.id.mind_journal_card_rate);
            mj_note_txt = itemView.findViewById(R.id.mind_journal_card_note);
            mj_id_txt = itemView.findViewById(R.id.mind_journal_card_id);
            tj_row_linear_l = itemView.findViewById(R.id.journal_row_linear_layout);

            translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            tj_row_linear_l.setAnimation(translate_anim);
        }
    }
}
