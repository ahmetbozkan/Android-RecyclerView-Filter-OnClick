package com.example.recyclerviewfilteronclick;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {

    private ArrayList<ExampleItem> exampleItems;
    private OnNoteListener onNoteListener;

    public ExampleAdapter(ArrayList<ExampleItem> exampleItems, OnNoteListener onNoteListener) {
        this.exampleItems = exampleItems;
        this.onNoteListener = onNoteListener;
    }

    @Override
    public int getItemCount() {
        return exampleItems.size();
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView textview;
        public ImageView imageView;
        public OnNoteListener onNoteListener;

        public ExampleViewHolder(View itemView, OnNoteListener onNoteListener) {
            super(itemView);

            textview = itemView.findViewById(R.id.textview);
            imageView = itemView.findViewById(R.id.imageview);
            this.onNoteListener = onNoteListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item, parent, false);

        return new ExampleViewHolder(v, onNoteListener);
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        ExampleItem currentItem = exampleItems.get(position);

        holder.textview.setText(currentItem.getText1());
        holder.imageView.setImageResource(currentItem.getImageResource());
    }

    public interface OnNoteListener {
        void onNoteClick(int position);
    }

    public void filterList(ArrayList<ExampleItem> filteredList) {
        exampleItems = filteredList;
        notifyDataSetChanged();
    }


}
