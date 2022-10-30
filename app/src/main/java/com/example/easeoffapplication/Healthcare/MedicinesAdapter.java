package com.example.easeoffapplication.Healthcare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.easeoffapplication.R;
import com.example.easeoffapplication.db.Medicines;

import java.util.List;

public class MedicinesAdapter extends ArrayAdapter<Medicines> {

    private Context context;
    private int resource;
    List<Medicines> meds;

    MedicinesAdapter(Context context, int resource, List<Medicines> meds){
        super(context,resource,meds);
        this.context = context;
        this.resource = resource;
        this.meds = meds;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(resource,parent,false);

        TextView medName = row.findViewById(R.id.MedName);
        TextView description = row.findViewById(R.id.Med_description);
        TextView medPrice = row.findViewById(R.id.med_price);
        TextView pharName = row.findViewById(R.id.pharName);
        ImageView pillIcon = row.findViewById(R.id.pill_icon);

        Medicines med = meds.get(position);
        medName.setText(med.getMedName());
        description.setText(med.getMedDes());
        medPrice.setText(String.valueOf(med.getMedPrice()));
        pharName.setText(med.getPharName());
        pillIcon.setVisibility(View.VISIBLE);


        return row;
    }


}
