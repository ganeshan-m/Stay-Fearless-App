package com.example.fearless;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.HashMap;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private ArrayList<HashMap<String, String>> contactList;
    private OnDeleteClickListener onDeleteClickListener; // Declare the listener

    public ContactAdapter(ArrayList<HashMap<String, String>> contactList, OnDeleteClickListener onDeleteClickListener) {
        this.contactList = contactList;
        this.onDeleteClickListener = onDeleteClickListener; // Initialize the listener
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item_layout, parent, false);
        return new ContactViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        HashMap<String, String> contact = contactList.get(position);
        holder.nameTextView.setText(contact.get("name"));
        holder.numberTextView.setText(contact.get("number"));
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView numberTextView;
        ImageView deleteImageView;

        ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            numberTextView = itemView.findViewById(R.id.numberTextView);
            deleteImageView = itemView.findViewById(R.id.deleteImageView); // Initialize delete icon

            // Set click listener for the delete icon
            deleteImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onDeleteClickListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            onDeleteClickListener.onDeleteClick(position);
                        }
                    }
                }
            });
        }
    }

    // Interface for the delete icon click listener
    public interface OnDeleteClickListener {
        void onDeleteClick(int position);
    }
}
