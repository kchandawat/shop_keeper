package com.marquedo.marquedo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class update_product extends AppCompatActivity
{
    private String pos;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);


        Intent preview = new Intent(this, preview_product.class);
        Button preview_prod = findViewById(R.id.show_preview);
        preview_prod.setOnClickListener(view ->
        {
            startActivity(preview);
        });


        Bundle intent = getIntent().getExtras();
        pos = (String) intent.get("key");


        CollectionReference collectionReference = db.collection("Store");
        db.collectionGroup("products");
    }
}
