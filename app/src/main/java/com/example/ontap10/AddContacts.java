package com.example.ontap10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddContacts extends AppCompatActivity {
    EditText edtId, edtName, edtPhone;
    Button btnAdd, btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contacts);

        edtId = findViewById(R.id.edtId);
        edtName = findViewById(R.id.edtName);
        edtPhone = findViewById(R.id.edtPhoneNumber);

        btnAdd  = findViewById(R.id.btnAdd);
        btnBack  = findViewById(R.id.btnBack);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt("Id", Integer.parseInt(edtId.getText().toString()));
                bundle.putString("Name", edtName.getText().toString());
                bundle.putString("Phone", edtPhone.getText().toString());
                intent.putExtras(bundle);
                setResult(200, intent);
                finish();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddContacts.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}