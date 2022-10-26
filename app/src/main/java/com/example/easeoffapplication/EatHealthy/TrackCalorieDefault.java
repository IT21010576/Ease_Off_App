package com.example.easeoffapplication.EatHealthy;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.example.easeoffapplication.R;

public class TrackCalorieDefault extends Fragment {

    View view;
    ViewFlipper v_flipper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_track_calorie_default, container, false);
        int images[]={R.drawable.carbsdefault, R.drawable.protdefault, R.drawable.fatdefault};
        v_flipper=view.findViewById(R.id.view_flip);

        for(int image:images){
            flipImages(image);
        }
        return view;
    }

    public void flipImages(int image){
        ImageView iview=new ImageView(getContext());
        iview.setBackgroundResource(image);

        v_flipper.addView(iview);
        v_flipper.setFlipInterval(4000);
        v_flipper.setAutoStart(true);

        v_flipper.setInAnimation(getContext(),android.R.anim.slide_in_left);
        v_flipper.setOutAnimation(getContext(),android.R.anim.slide_out_right);

    }
}