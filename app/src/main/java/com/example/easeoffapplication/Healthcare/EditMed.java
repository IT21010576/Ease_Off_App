package com.example.easeoffapplication.Healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.easeoffapplication.EatHealthy.Update_trackCalorie;
import com.example.easeoffapplication.R;
import com.example.easeoffapplication.db.DBhelper;
import com.example.easeoffapplication.db.Medicines;

public class EditMed extends AppCompatActivity {

    EditText medName_edit, phar_edit, des_edit, price_edit;
    Button MedUpdateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_med);

        medName_edit = findViewById(R.id.medNameEdit);
        phar_edit = findViewById(R.id.pharNameEdit);
        des_edit = findViewById(R.id.desEdit);
        price_edit = findViewById(R.id.medPriceEdit);

        MedUpdateBtn = findViewById(R.id.saveEditMedBtn);

        final String id = getIntent().getStringExtra("id");
        Medicines med = getSingleMed(Integer.parseInt(id));

        medName_edit.setText(med.getMedName());
        phar_edit.setText(med.getPharName());
        des_edit.setText(med.getMedDes());
        price_edit.setText(String.valueOf(med.getMedPrice()));

        MedUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String medName = medName_edit.getText().toString();
                String pharName = phar_edit.getText().toString();
                String des = des_edit.getText().toString();
                String price = price_edit.getText().toString();

                Medicines med = new Medicines(Integer.parseInt(id),medName,pharName,des,Double.parseDouble(price));
                updateSingleMed(med);
                startActivity(new Intent(getApplicationContext(), PharmacyMain.class));

            }
        });
    }

    public Medicines getSingleMed(int id){
        DBhelper dbHelper = new DBhelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String query = "SELECT * FROM " + Medicines.medicines.TABLE_NAME + " WHERE " + Medicines.medicines._ID + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(id)});

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
            return med;
        }
        return null;
    }

    public int updateSingleMed(Medicines med){
        DBhelper dbHelper = new DBhelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(Medicines.medicines.COLUMN_NAME_MEDNAME, med.getMedName());
        cv.put(Medicines.medicines.COLUMN_NAME_PHARMACY, med.getPharName());
        cv.put(Medicines.medicines.COLUMN_NAME_DESCRIPTION, med.getMedDes());
        cv.put(Medicines.medicines.COLUMN_NAME_PRICE, med.getMedPrice());

        int status = db.update(Medicines.medicines.TABLE_NAME,
                cv,Medicines.medicines._ID + " =?",
                new String[]{String.valueOf(med.getId())});
        db.close();
        if(status==-1){
            showToast("Failed To Update!");
        }
        else {
            showToast("Record Update!");
        }
        return status;
    }

    void showToast(String message) {

        Toast toast = new Toast(this);

        View view = LayoutInflater.from(EditMed.this).inflate(R.layout.sucesstoast, null);

        TextView tvMessage = view.findViewById(R.id.tvMessage);
        tvMessage.setText(message);

        toast.setView(view);
        toast.show();

    }


}