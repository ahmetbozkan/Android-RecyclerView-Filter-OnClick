package com.example.recyclerviewfilteronclick;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {

    private ImageView detail_image;
    private TextView detail_textview;

    private String extra_text;
    private int extra_imageResource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        detail_image = findViewById(R.id.details_image);
        detail_textview = findViewById(R.id.details_text);

        setItems();

    }

    private void setItems() {
        Intent intent = getIntent();
        extra_text = intent.getStringExtra("Text");
        extra_imageResource = intent.getIntExtra("ImageResource", 0);

        detail_textview.setText(extra_text);
        detail_image.setImageResource(extra_imageResource);
    }
}