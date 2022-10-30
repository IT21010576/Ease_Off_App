package com.example.easeoffapplication.Healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.easeoffapplication.EatHealthy.Update_trackCalorie;
import com.example.easeoffapplication.R;
import com.example.easeoffapplication.db.DBhelper;
import com.example.easeoffapplication.db.MedicineLists;
import com.example.easeoffapplication.db.Medicines;

import java.util.ArrayList;

public class EditMedLists extends AppCompatActivity {

    EditText listName, med1QTY_edit, med2QTY_edit, med3QTY_edit, med4QTY_edit, med5QTY_edit;
    Spinner med1_edit, med2_edit, med3_edit, med4_edit, med5_edit;
    Button save_edit, calculate_edit;
    TextView total_edit;
    String selection1, selection2, selection3, selection4, selection5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_med_lists);

        listName = findViewById(R.id.listNameEdt_edit);
        med1QTY_edit = findViewById(R.id.med1_qty_edit);
        med2QTY_edit = findViewById(R.id.med2_qty_edit);
        med3QTY_edit = findViewById(R.id.med3_qty_edit);
        med4QTY_edit = findViewById(R.id.med4_qty_edit);
        med5QTY_edit = findViewById(R.id.med5_qty_edit);

        med1_edit = findViewById(R.id.med1_select_edit);
        med2_edit = findViewById(R.id.med2_select_edit);
        med3_edit = findViewById(R.id.med3_select_edit);
        med4_edit = findViewById(R.id.med4_select_edit);
        med5_edit = findViewById(R.id.med5_select_edit);

        save_edit = findViewById(R.id.saveMedList_edit);
        calculate_edit = findViewById(R.id.calculateBtn_edit);

        total_edit = findViewById(R.id.total_view_edit);

        final String id = getIntent().getStringExtra("editMedList");
        MedicineLists list = getSingleMedList(Integer.parseInt(id));

        listName.setText(list.getListName());

        ArrayList<String> meds = getAllMedicines();
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,meds);
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        med1_edit.setAdapter(myAdapter);
        int p1 = myAdapter.getPosition(list.getMedName1());
        med1_edit.setSelection(p1);

        med2_edit.setAdapter(myAdapter);
        int p2 = myAdapter.getPosition(list.getMedName2());
        med2_edit.setSelection(p2);

        med3_edit.setAdapter(myAdapter);
        int p3 = myAdapter.getPosition(list.getMedName3());
        med3_edit.setSelection(p3);

        med4_edit.setAdapter(myAdapter);
        int p4 = myAdapter.getPosition(list.getMedName4());
        med4_edit.setSelection(p4);

        med5_edit.setAdapter(myAdapter);
        int p5 = myAdapter.getPosition(list.getMedName5());
        med5_edit.setSelection(p5);

        med1_edit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selection1 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        med2_edit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selection2 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        med3_edit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selection3 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        med4_edit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selection4 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        med5_edit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selection5 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        calculate_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double tot = getSingleMedPrice(selection1) * Integer.parseInt(med1QTY_edit.getText().toString()) +
                        getSingleMedPrice(selection2) * Integer.parseInt(med2QTY_edit.getText().toString()) +
                        getSingleMedPrice(selection3) * Integer.parseInt(med3QTY_edit.getText().toString()) +
                        getSingleMedPrice(selection4) * Integer.parseInt(med4QTY_edit.getText().toString()) +
                        getSingleMedPrice(selection5) * Integer.parseInt(med5QTY_edit.getText().toString());

                total_edit.setText(String.valueOf(tot));
            }
        });

        save_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name_list = listName.getText().toString();
                double total = Double.parseDouble(total_edit.getText().toString());

                MedicineLists list = new MedicineLists(Integer.parseInt(id),name_list,selection1,selection2,selection3,selection4,selection5,total);
                updateSingleMedList(list);
                startActivity(new Intent(getApplicationContext(), allMedLists.class));

            }
        });
    }

    public MedicineLists getSingleMedList(int id){
        DBhelper dbHelper = new DBhelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String query = "SELECT * FROM " + MedicineLists.medicineLists.TABLE_NAME + " WHERE " + MedicineLists.medicineLists._ID + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(id)});

        MedicineLists list;
        if(cursor != null){
            cursor.moveToFirst();
            list = new MedicineLists(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getDouble(7)
            );
            return list;
        }
        return null;
    }

    public ArrayList<String> getAllMedicines(){

        ArrayList<String> meds = new ArrayList<>();

        DBhelper dbHelper = new DBhelper(getApplicationContext());
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

                meds.add(med.getMedName());
            }while (cursor.moveToNext());
        }
        return meds;
    }

    public double getSingleMedPrice(String medName){
        DBhelper dbHelper = new DBhelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String query = "SELECT * FROM " + Medicines.medicines.TABLE_NAME + " WHERE " + Medicines.medicines.COLUMN_NAME_MEDNAME + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(medName)});

        Medicines med;
        if(cursor != null){
            cursor.moveToFirst();
            med = new Medicines(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getDouble(4)
            );
            return med.getMedPrice();
        }
        return 0.0;
    }

    public int updateSingleMedList(MedicineLists list){
        DBhelper dbHelper = new DBhelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(MedicineLists.medicineLists.COLUMN_NAME_LISTNAME, list.getListName());
        cv.put(MedicineLists.medicineLists.COLUMN_NAME_MEDNAME1, list.getMedName1());
        cv.put(MedicineLists.medicineLists.COLUMN_NAME_MEDNAME2, list.getMedName2());
        cv.put(MedicineLists.medicineLists.COLUMN_NAME_MEDNAME3, list.getMedName3());
        cv.put(MedicineLists.medicineLists.COLUMN_NAME_MEDNAME4, list.getMedName4());
        cv.put(MedicineLists.medicineLists.COLUMN_NAME_MEDNAME5, list.getMedName5());
        cv.put(MedicineLists.medicineLists.COLUMN_NAME_TOTAL, list.getTotal());

        int status = db.update(MedicineLists.medicineLists.TABLE_NAME,
                cv,MedicineLists.medicineLists._ID + " =?",
                new String[]{String.valueOf(list.getId())});
        db.close();
        if(status==-1){
            showToast("Failed To Update!");
        }
        else {
            showToast("Record Updated!");
        }
        return status;
    }

    void showToast(String message) {

        Toast toast = new Toast(this);

        View view = LayoutInflater.from(EditMedLists.this).inflate(R.layout.sucesstoast, null);

        TextView tvMessage = view.findViewById(R.id.tvMessage);
        tvMessage.setText(message);

        toast.setView(view);
        toast.show();

    }
}