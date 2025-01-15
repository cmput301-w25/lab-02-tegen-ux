package com.example.listycity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    //Declare the variables that we'll use later.
    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        // First we find the reference to ListView, then we set it to cityList
        cityList = findViewById(R.id.city_list);
        // Then we can make a String Array consiting of awesome cities
        String[] cities = {"Edmonton", "Red Deer", "Calgary", "Fort MacMurray", "Sylvan Lake",
                "Vancouver", "Spruce Grove", "Camrose", "Wetaskiwin", "Poggers", "New York"};
        // Then we can declare a new arrayList
        dataList = new ArrayList<>();

        // Then we all all of our cities into that array
        dataList.addAll(Arrays.asList(cities));
        // Then we do some adapter thing, which I have no clue what it does
        // All I know is that it links all this code to the content.xml that we had earlier.
        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        // Same again, I have no clue what is there
        cityList.setAdapter(cityAdapter);


    }
}