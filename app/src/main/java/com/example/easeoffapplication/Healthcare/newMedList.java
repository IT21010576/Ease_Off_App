package com.example.easeoffapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.easeoffapplication.db.DBhelper;
import com.example.easeoffapplication.db.Medicines;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class newMedList extends AppCompatActivity {

    TextView listName, totalView;
    Spinner med1_select,med2_select,med3_select,med4_select,med5_select;
    EditText med1_qty, med2_qty, med3_qty, med4_qty, med5_qty;
    Button calculate;
    String selection1, selection2, selection3, selection4, selection5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_med_list);

        med1_select = findViewById(R.id.med1_select);
        med2_select = findViewById(R.id.med2_select);
        med3_select = findViewById(R.id.med3_select);
        med4_select = findViewById(R.id.med4_select);
        med5_select = findViewById(R.id.med5_select);

        med1_qty = findViewById(R.id.med1_qty);
        med2_qty = findViewById(R.id.med2_qty);
        med3_qty = findViewById(R.id.med3_qty);
        med4_qty = findViewById(R.id.med4_qty);
        med5_qty = findViewById(R.id.med5_qty);

        listName = findViewById(R.id.listNameEdt);
        calculate = findViewById(R.id.calculateBtn);
        totalView = findViewById(R.id.total_view);

        ArrayList<String> meds = getAllMedicines();
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,meds);
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        med1_select.setAdapter(myAdapter);
        med2_select.setAdapter(myAdapter);
        med3_select.setAdapter(myAdapter);
        med4_select.setAdapter(myAdapter);
        med5_select.setAdapter(myAdapter);



        med1_select.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selection1 = adapterView.getItemAtPosition(i).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        med2_select.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selection2 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        med3_select.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selection3 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        med4_select.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selection4 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        med5_select.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selection5 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double tot = getSingleMedPrice(selection1) * Integer.parseInt(med1_qty.getText().toString()) +
                        getSingleMedPrice(selection2) * Integer.parseInt(med2_qty.getText().toString()) +
                        getSingleMedPrice(selection3) * Integer.parseInt(med3_qty.getText().toString()) +
                        getSingleMedPrice(selection4) * Integer.parseInt(med4_qty.getText().toString()) +
                        getSingleMedPrice(selection5) * Integer.parseInt(med5_qty.getText().toString());

                totalView.setText(String.valueOf(tot));
            }
        });

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
}