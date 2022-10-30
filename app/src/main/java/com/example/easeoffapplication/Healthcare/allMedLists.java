package com.example.easeoffapplication.Healthcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
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
import com.example.easeoffapplication.db.MedicineLists;
import com.example.easeoffapplication.db.Medicines;

import java.util.ArrayList;
import java.util.List;

public class allMedLists extends AppCompatActivity {

    ListView lists_list;
    List<MedicineLists> medLists;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_med_lists);

        lists_list = findViewById(R.id.medlists_list);

        //Create the Dialog here
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false); //Optional
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation; //Setting the animations to dialog

        Button DialogYes = dialog.findViewById(R.id.btn_okay);
        Button Cancel = dialog.findViewById(R.id.btn_cancel);

        medLists = new ArrayList<>();
        medLists = getAllMedicinesLists();

        MedicineListsAdapter medicineListsAdapter = new MedicineListsAdapter(getApplicationContext(),R.layout.single_medicine_list, medLists);
        lists_list.setAdapter(medicineListsAdapter);

        lists_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                MedicineLists list = medLists.get(i);
                AlertDialog.Builder builder = new AlertDialog.Builder(allMedLists.this);
                builder.setTitle(list.getListName());

                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialog.show();
                    }
                });

                DialogYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deleteMedList(list.getId());
                        dialog.dismiss();
                        startActivity(new Intent(getApplicationContext(), allMedLists.class));
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
                        Intent intent = new Intent(getApplicationContext(), EditMedLists.class);
                        intent.putExtra("editMedList", String.valueOf(list.getId()));
                        startActivity(intent);
                    }
                });
                builder.show();
            }
        });
    }

    public List<MedicineLists> getAllMedicinesLists(){

        List<MedicineLists> lists = new ArrayList<>();

        DBhelper dbHelper = new DBhelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM " + MedicineLists.medicineLists.TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                MedicineLists list = new MedicineLists();

                list.setId(cursor.getInt(0));
                list.setListName(cursor.getString(1));
                list.setMedName1(cursor.getString(2));
                list.setMedName2(cursor.getString(3));
                list.setMedName3(cursor.getString(4));
                list.setMedName4(cursor.getString(5));
                list.setMedName5(cursor.getString(6));
                list.setTotal(cursor.getDouble(7));

                lists.add(list);
            }while (cursor.moveToNext());
        }
        return lists;
    }

    public void deleteMedList(int id){
        DBhelper dbHelper = new DBhelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        long result = db.delete(MedicineLists.medicineLists.TABLE_NAME, MedicineLists.medicineLists._ID + " =?",
                new String[]{String.valueOf(id)});
        if(result==-1){
            showToast("Failed To Delete!");
        }
        else {
            showToast("Record Deleted!");
        }
        db.close();
    }
    void showToast(String message) {

        Toast toast = new Toast(this);

        View view = LayoutInflater.from(allMedLists.this).inflate(R.layout.sucesstoast, null);

        TextView tvMessage = view.findViewById(R.id.tvMessage);
        tvMessage.setText(message);

        toast.setView(view);
        toast.show();

    }
}