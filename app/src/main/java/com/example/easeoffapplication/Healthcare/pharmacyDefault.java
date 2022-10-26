package com.example.easeoffapplication.Healthcare;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.easeoffapplication.EatHealthy.Update_trackCalorie;
import com.example.easeoffapplication.R;
import com.example.easeoffapplication.db.DBhelper;
import com.example.easeoffapplication.db.Medicines;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class pharmacyDefault extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    FloatingActionButton addMedicineBtn;
    ListView medList;
    List<Medicines> meds;
    private Dialog dialog;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public pharmacyDefault() {
    }

    public static pharmacyDefault newInstance(String param1, String param2) {
        pharmacyDefault fragment = new pharmacyDefault();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pharmacy_default, container, false);

        addMedicineBtn = view.findViewById(R.id.addNewMedBtn);
        medList = view.findViewById(R.id.medlist);

        //Create the Dialog here
        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.custom_dialog);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false); //Optional
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation; //Setting the animations to dialog

        Button DialogYes = dialog.findViewById(R.id.btn_okay);
        Button Cancel = dialog.findViewById(R.id.btn_cancel);

        meds = new ArrayList<>();
        meds = getAllMedicines();

        MedicinesAdapter medicinesAdapter = new MedicinesAdapter(getContext(),R.layout.single_medicine, meds);
        medList.setAdapter(medicinesAdapter);

        addMedicineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.pharmacyDefaultfrag, new newMedicine());
                ft.commit();
            }
        });

        medList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Medicines med = meds.get(i);
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle(med.getMedName());

                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialog.show();

                    }
                });
                DialogYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deleteMed(med.getId());
                        dialog.dismiss();
                        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.pharmacyDefaultfrag, new pharmacyDefault());
                        ft.commit();
                    }
                });
                Cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                builder.setNeutralButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(getContext(), EditMed.class);
                        intent.putExtra("id", String.valueOf(med.getId()));
                        startActivity(intent);
                    }
                });
                builder.show();
            }
        });

        return view;
    }



    public int countMed(){
        DBhelper dbHelper = new DBhelper(getContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String query = "SELECT * FROM " + Medicines.medicines.TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);
        return cursor.getCount();
    }

    public List<Medicines> getAllMedicines(){

        List<Medicines> meds = new ArrayList<>();

        DBhelper dbHelper = new DBhelper(getContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM " + Medicines.medicines.TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                Medicines med = new Medicines();

                med.setId(cursor.getInt(0));
                med.setMedName(cursor.getString(1));
                med.setPharName(cursor.getString(2));
                med.setMedDes(cursor.getString(3));
                med.setMedPrice(cursor.getDouble(4));

                meds.add(med);
            }while (cursor.moveToNext());
        }
        return meds;
    }

    public void deleteMed(int id){
        DBhelper dbHelper = new DBhelper(getContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        long result = db.delete(Medicines.medicines.TABLE_NAME, Medicines.medicines._ID + " =?",
                new String[]{String.valueOf(id)});
        db.close();

        if(result==-1){
            showToast("Failed To Delete!");
        }
        else {
            showToast("Record Deleted!");
        }
    }

    void showToast(String message) {

        Toast toast = new Toast(getContext());

        View view = LayoutInflater.from(getContext()).inflate(R.layout.sucesstoast, null);

        TextView tvMessage = view.findViewById(R.id.tvMessage);
        tvMessage.setText(message);

        toast.setView(view);
        toast.show();

    }



}