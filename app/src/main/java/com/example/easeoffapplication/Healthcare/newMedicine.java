package com.example.easeoffapplication.Healthcare;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

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

public class newMedicine extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    EditText medName_input, phar_input, des_input, price_input;
    Button newMedSaveBtn;


    public newMedicine() {
    }
    public static newMedicine newInstance(String param1, String param2) {
        newMedicine fragment = new newMedicine();
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
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_new_medicine, container, false);

        medName_input = view.findViewById(R.id.medNameInput);
        phar_input = view.findViewById(R.id.pharNameInput);
        des_input = view.findViewById(R.id.desInput);
        price_input = view.findViewById(R.id.medPriceInput);

        newMedSaveBtn = view.findViewById(R.id.saveNewMedBtn);
        newMedSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewMedicine();

                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.pharmacyDefaultfrag, new pharmacyDefault());
                ft.commit();
            }
        });

        return view;

    }

    public int addNewMedicine(){
        String medName = medName_input.getText().toString();
        String pharName = phar_input.getText().toString();
        String des = des_input.getText().toString();
        int price = Integer.valueOf(price_input.getText().toString());

        Medicines newMed = new Medicines(medName,pharName,des,price);

        DBhelper dbHelper = new DBhelper(getContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(Medicines.medicines.COLUMN_NAME_MEDNAME, newMed.getMedName());
        cv.put(Medicines.medicines.COLUMN_NAME_PHARMACY, newMed.getPharName());
        cv.put(Medicines.medicines.COLUMN_NAME_DESCRIPTION, newMed.getMedDes());
        cv.put(Medicines.medicines.COLUMN_NAME_PRICE, newMed.getMedPrice());

        int success = 0;
        long result = db.insert(Medicines.medicines.TABLE_NAME, null, cv);
        db.close();
        if(result == -1){
            showToast("Failed To Add Medicine!");
            success = 1;
        }else{
            showToast("Medicine Added!");
        }
        return success;
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