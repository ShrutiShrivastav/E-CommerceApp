package com.example.e_commerceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Paint;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class CartActivity extends AppCompatActivity {

    ViewFlipper viewFlipper;
    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        ViewFlipper viewFlipper = (ViewFlipper)findViewById(R.id.flipper);
        viewFlipper.startFlipping();

        TextView textView2= findViewById(R.id.tv_2);
        textView2.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
    }
}
