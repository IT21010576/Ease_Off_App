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
import com.example.easeoffapplication.db.MedicineLists;
import com.example.easeoffapplication.db.Medicines;

import java.util.List;

public class MedicineListsAdapter extends ArrayAdapter<MedicineLists> {

    private Context context;
    private int resource;
    List<MedicineLists> medsLists;

    MedicineListsAdapter(Context context, int resource, List<MedicineLists> medsLists){
        super(context,resource,medsLists);
        this.context = context;
        this.resource = resource;
        this.medsLists = medsLists;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(resource,parent,false);

        TextView listName = row.findViewById(R.id.listName);

        MedicineLists list = medsLists.get(position);
        listName.setText(list.getListName());

        return row;
    }
}
