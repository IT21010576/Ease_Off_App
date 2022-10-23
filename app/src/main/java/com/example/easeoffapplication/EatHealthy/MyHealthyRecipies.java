package com.example.easeoffapplication.EatHealthy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.easeoffapplication.EatHealthy.ViewSavedRecipes;
import com.example.easeoffapplication.R;

public class MyHealthyRecipies extends AppCompatActivity {

    Button viewRecipeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_healthy_recipies);
        viewRecipeBtn=findViewById(R.id.viewRecipeBtn);

        viewRecipeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new ViewSavedRecipes());
            }
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.FrameLayout1,fragment);
        fragmentTransaction.commit();
    }
}