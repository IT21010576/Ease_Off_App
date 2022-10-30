package com.example.easeoffapplication.stayfit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.easeoffapplication.R;
import com.example.easeoffapplication.db.Workoutprofile;

import java.util.List;

public class MyprofileAdapter extends ArrayAdapter<Workoutprofile> {


        private Context context;
        private int resource;
        List<Workoutprofile> works;

        MyprofileAdapter(Context context, int resource, List<Workoutprofile> works) {
        super(context, resource, works);
        this.context = context;
        this.resource = resource;
        this.works = works;

    }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(resource, parent, false);

        TextView set_name = row.findViewById(R.id.set_name);

        Workoutprofile work = works.get(position);
        set_name.setText(work.getUname());
        set_name.setText(work.getUname());
        set_name.setText(work.getUname());
        set_name.setText(work.getUname());


        return row;

    }
    }

