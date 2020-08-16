package com.example.recyclerviewfilteronclick;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ExampleAdapter.OnNoteListener {
    private static final String TAG = "MainActivity";

    private ArrayList<ExampleItem> exampleItems;

    private RecyclerView exampleRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ExampleAdapter adapter;

    private EditText editText_filter;
    private ArrayList<ExampleItem> filteredList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRecyclerItems();
        buildRecyclerView();

        editText_filter = findViewById(R.id.filter_editText);
        editText_filter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
    }

    private void filter(String text) {
        filteredList = new ArrayList<>();

        for(ExampleItem currentItem : exampleItems) {
            if(currentItem.getText1().toLowerCase().trim().contains(text.toLowerCase().trim())) {
                filteredList.add(currentItem);
            }
        }

        adapter.filterList(filteredList);
    }

    private void setRecyclerItems() {
        exampleItems = new ArrayList<>();

        for(int i = 0; i < 100; i++) {
            ExampleItem exampleItem = new ExampleItem("Item " + i, R.mipmap.ic_launcher);
            exampleItems.add(exampleItem);
        }

    }

    private void buildRecyclerView() {
        exampleRecyclerView = findViewById(R.id.example_recyclerview);
        exampleRecyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        adapter = new ExampleAdapter(exampleItems, this);

        exampleRecyclerView.setAdapter(adapter);
        exampleRecyclerView.setLayoutManager(layoutManager);
    }

    private int getPositionAfterFiltering(int position) {

        if(filteredList != null) {

            ExampleItem currentItem = filteredList.get(position);
            String text1 = currentItem.getText1();
            // We are getting the text from Filtered Recycler Items.

            // We are iterating over the original list.
            for(ExampleItem item : exampleItems) {

                // We are comparing the text that we clicked on after filtering items and compare it with current text that we get from original list.
                if (item.getText1().equalsIgnoreCase(text1) ) {

                    position = exampleItems.indexOf(item);
                    // If the both text equals to each other, we will return position of clicked item from original list so we will get the correct position.
                }
            }
        }

        return position;
    }


    @Override
    public void onNoteClick(int position) {
        position = getPositionAfterFiltering(position);

        ExampleItem currentItem = exampleItems.get(position);

        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
        intent.putExtra("Text", currentItem.getText1());
        intent.putExtra("ImageResource", currentItem.getImageResource());
        startActivity(intent);
    }


}