package com.example.fearless;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class saved_contacts extends AppCompatActivity implements ContactAdapter.OnDeleteClickListener {

    private RecyclerView recyclerView;
    private ArrayList<HashMap<String, String>> contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_contacts);

        recyclerView = findViewById(R.id.recyclerView);
        contactList = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadSavedContacts();
    }

    private void loadSavedContacts() {
        SharedPreferences sharedPreferences = getSharedPreferences("EmergencyContacts", MODE_PRIVATE);
        Map<String, ?> allContacts = sharedPreferences.getAll();

        contactList.clear(); // Clear the list before loading contacts

        for (Map.Entry<String, ?> entry : allContacts.entrySet()) {
            String contactName = entry.getKey();
            String contactNumber = entry.getValue().toString();

            HashMap<String, String> contact = new HashMap<>();
            contact.put("name", contactName);
            contact.put("number", contactNumber);

            contactList.add(contact);
        }

        // Sort the contactList alphabetically by contact name
        Collections.sort(contactList, new Comparator<HashMap<String, String>>() {
            @Override
            public int compare(HashMap<String, String> contact1, HashMap<String, String> contact2) {
                String name1 = contact1.get("name");
                String name2 = contact2.get("name");
                return name1.compareToIgnoreCase(name2);
            }
        });

        // Set up RecyclerView adapter here
        ContactAdapter adapter = new ContactAdapter(contactList, this); // Pass 'this' as onDeleteClickListener
        recyclerView.setAdapter(adapter);
    }

    // Implement the onDeleteClick method
    @Override
    public void onDeleteClick(int position) {
        // Get the selected contact's details from the contactList using 'position'
        HashMap<String, String> selectedContact = contactList.get(position);
        String selectedName = selectedContact.get("name");
        String selectedNumber = selectedContact.get("number");

        // Implement the delete logic using SharedPreferences or any other method you prefer
        deleteContactFromSharedPreferences(selectedName);

        // Remove the selected contact from the contactList and update the adapter
        contactList.remove(position);
        recyclerView.getAdapter().notifyItemRemoved(position);
    }

    // Inside saved_contacts.java

    public static String[] getContactNumbers(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("EmergencyContacts", MODE_PRIVATE);
        Map<String, ?> allContacts = sharedPreferences.getAll();

        List<String> contactNumbersList = new ArrayList<>();

        for (Map.Entry<String, ?> entry : allContacts.entrySet()) {
            String contactNumber = entry.getValue().toString();
            contactNumbersList.add(contactNumber);
        }

        // Convert the list to an array
        String[] contactNumbers = contactNumbersList.toArray(new String[contactNumbersList.size()]);
        return contactNumbers;
    }

    private void deleteContactFromSharedPreferences(String name) {
        SharedPreferences sharedPreferences = getSharedPreferences("EmergencyContacts", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(name); // Remove the contact using the name as the key
        editor.apply();
    }
}
