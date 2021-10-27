package com.example.marquedo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import net.cachapa.expandablelayout.ExpandableLayout;

import org.w3c.dom.Text;

public class varient_card extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("onclick", "clicking ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_varient_card);

        ExpandableLayout expandableLayout=(ExpandableLayout) findViewById(R.id.expandable_layout);
        Button dropdown1=(Button)  findViewById(R.id.add_size_variant);
        dropdown1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandableLayout.toggle();

            }
        });
    }
}