package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.helper.widget.Carousel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.adapter.aadapter;

import java.util.ArrayList;
import java.util.List;

public class search extends AppCompatActivity {
    EditText enterCity;
    Button btn;
//    SearchView srch;
    RecyclerView recview;

    ArrayList<String> city;
    aadapter aadapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        enterCity = findViewById(R.id.enterCity);
        btn=findViewById(R.id.searchCity);
        recview = findViewById(R.id.storeCity);
        city = new ArrayList<>();
//        city = getIntent().getStringArrayListExtra("arr");
        city.add("Aligarh");
        city.add("Agra");
        city.add("Delhi");
        city.add("Noida");
        recview.setLayoutManager(new LinearLayoutManager(this));
        aadapter = new aadapter(city,this);
        recview.setAdapter(aadapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                city.add(enterCity.getText().toString());

//                aadapter = new aadapter(city);
//                recview.setAdapter(aadapter);

                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("city",enterCity.getText().toString().trim());
                startActivity(intent);
                finish();

            }
        });

//       aadapter = new aadapter(city,this);


//        recview.setLayoutManager(new LinearLayoutManager(this));
    }
}