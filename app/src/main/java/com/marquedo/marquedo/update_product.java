package com.marquedo.marquedo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.marquedo.marquedo.ui.Prod_n_Cat.Product.AboutModelClass;

public class update_product extends AppCompatActivity
{

    private String pos;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private EditText category;
    private AboutModelClass aboutModelClass;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);

        category= findViewById(R.id.prod_category_up);

        Intent preview = new Intent(this, preview_product.class);
        Button preview_prod = findViewById(R.id.show_preview);
        preview_prod.setOnClickListener(view ->
        {
            startActivity(preview);
        });


        Bundle intent = getIntent().getExtras();
        pos = intent.get("key").toString();

        String id = db.collection("Store").document().getId();


        db.collection("Store").document("uniquename.TFTVHvZaHOIxjYLnHvwc").collection("products").document(pos).collection("about").document("product_id").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(@NonNull DocumentSnapshot documentSnapshot)
            {
                String cat = documentSnapshot.getString("Category");
                Log.i("workresult", cat);
                //Log.d("workresult", cat);
                //aboutModelClass = documentSnapshot.get("Category", AboutModelClass.class);
                //category.setText(aboutModelClass.getCategory());
                category.setText(cat);
            }
        });
        //CollectionReference collectionReference = db.collection("Store");
        //db.collectionGroup("about");
        //Log.i("trial", db.collectionGroup("about").orderBy(pos).get().toString());
    }
}
