package com.example.ontap10;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final int MY_REQUEST_CODE = 100;
    EditText edtSearch;
    Button btnAdd;
    ArrayList<Contact> contactArrayList;
    ContactAdapter adapter;
    ListView lvContact;
    ContactSQLite myDB;
    int contact_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtSearch = findViewById(R.id.edtSearch);
        btnAdd = findViewById(R.id.btnAdd);
        lvContact = findViewById(R.id.lvContact);
        registerForContextMenu(lvContact);
//        contactArrayList = new ArrayList<>();
        myDB = new ContactSQLite(this, "ContactTable", null, 1);
//        myDB.onUpgrade(myDB.getReadableDatabase(),1,1);
        myDB.addContact(new Contact(1, "Ha", "0358604666"));
        myDB.addContact(new Contact(2, "Hung", "0258963147"));
        myDB.addContact(new Contact(3, "Huong", "1479965234"));
        contactArrayList = myDB.getAllContact();
        adapter = new ContactAdapter(this, contactArrayList);
        lvContact.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AddContacts.class);
                startActivityForResult(i, MY_REQUEST_CODE);
            }
        });

        lvContact.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                contact_id = i;
                return false;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Bundle bundle = data.getExtras();
        int id = bundle.getInt("Id");
        String name = bundle.getString("Name");
        String phone = bundle.getString("Phone");

        if (requestCode == MY_REQUEST_CODE && resultCode == 200) {
            contactArrayList.add(new Contact(id, name, phone));
            myDB.addContact(new Contact(id, name, phone));
        }

        adapter.notifyDataSetChanged();
    }
}