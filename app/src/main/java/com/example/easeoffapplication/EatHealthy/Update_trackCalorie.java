package com.example.easeoffapplication.EatHealthy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.easeoffapplication.R;
import com.example.easeoffapplication.db.DBhelper;
import com.example.easeoffapplication.db.TrackCalories;

public class Update_trackCalorie extends AppCompatActivity {

    EditText DisplayDate,DisplayComment;
    TextView DisplayTotal;
    Button update,delete;
    String date,comment,currentID;
    Double totalCal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_track_calorie);

        DisplayDate=findViewById(R.id.tbupdateDate);
        DisplayTotal=findViewById(R.id.tvDisTotalCal);
        DisplayComment=findViewById(R.id.tbupdateCmt);
        update=findViewById(R.id.updateCalBtn);
        delete=findViewById(R.id.delCalBtn);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateCalorieRec();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteCalorieRec();
            }
        });
        getIntentData();
    }

    public void getIntentData(){
        if(getIntent().hasExtra("date") && getIntent().hasExtra("totalCal") &&
                getIntent().hasExtra("comment")){

            //getting the data
            date=getIntent().getStringExtra("date");
            totalCal=Double.valueOf(getIntent().getStringExtra("totalCal"));
            comment=getIntent().getStringExtra("comment");
            currentID=getIntent().getStringExtra("id");

            //setting the data
            DisplayDate.setText(date);
            DisplayTotal.setText(String.valueOf(totalCal));
            DisplayComment.setText(comment);

        }
        else{
            Toast.makeText(this,"No data",Toast.LENGTH_LONG).show();
        }
    }

    public void updateCalorieRec(){
        DBhelper dbHelper = new DBhelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // New values or value
        String newDate=DisplayDate.getText().toString();
        String newComment=DisplayComment.getText().toString();

        ContentValues values = new ContentValues();
        values.put(TrackCalories.savedCalories.COLUMN_NAME_DATE, newDate);
        values.put(TrackCalories.savedCalories.COLUMN_NAME_COMMENT, newComment);

        String selection = BaseColumns._ID + " LIKE ?";
        String[] selectionArgs = { currentID };

        long result=db.update(TrackCalories.savedCalories.TABLE_NAME, values, selection, selectionArgs);

        if(result==-1){
            Toast.makeText(this, "Failed to Update!", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "Record Updated!", Toast.LENGTH_LONG).show();
        }

    }

    public void deleteCalorieRec(){
        DBhelper dbHelper = new DBhelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = BaseColumns._ID + " LIKE ?";
        String[] selectionArgs = { currentID };

        long result=db.delete(TrackCalories.savedCalories.TABLE_NAME, selection, selectionArgs);
        if(result==-1){
            Toast.makeText(this, "Failed to Delete!", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "Record Deleted!", Toast.LENGTH_LONG).show();
        }
        DisplayDate.setText("");
        DisplayTotal.setText("");
        DisplayComment.setText("");
    }
}