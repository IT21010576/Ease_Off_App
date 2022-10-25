package com.example.easeoffapplication.Healthcare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.easeoffapplication.R;
import com.example.easeoffapplication.db.MedShedule;
import com.example.easeoffapplication.db.MedicineLists;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class MedicineSchedulesAdapter extends ArrayAdapter<MedShedule> {

    private Context context;
    private int resource;
    List<MedShedule> schedules;

    MedicineSchedulesAdapter(Context context, int resource, List<MedShedule> schedules){
        super(context,resource,schedules);
        this.context = context;
        this.resource = resource;
        this.schedules = schedules;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(resource,parent,false);

        TextView scheduleName = row.findViewById(R.id.schedule_date_display);

        MedShedule schedule = schedules.get(position);
        DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        scheduleName.setText(String.valueOf(dateFormat.format(schedule.getDate())));

        return row;
    }
}
