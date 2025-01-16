package com.example.listycity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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

        /* Then we can make a String Array consisting of awesome cities */
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
        //Delete button
        Button deleteButton = findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(v -> {
            if (!dataList.isEmpty()) {
                dataList.remove(dataList.size() - 1); // Remove the last city
                /*
                Taken from https://developer.android.com/reference/android/widget/ArrayAdapter
                Authored By a Google Employee
                Taken By Tegen Hilker Readman
                Taken on 2024-01-16
                 */
                cityAdapter.notifyDataSetChanged();
            }
        });
            /*
             Taken from //https://stackoverflow.com/a/70769157
            Authored by Aman Bhatia
            Taken by Tegen Hilker Readman
            Taken on: 2024-01-15
            */


        EditText newCityInput = findViewById(R.id.new_city);
        Button confirmingButton = findViewById(R.id.confirm_button);
        Button cancelButton = findViewById(R.id.cancel_button);
        findViewById(R.id.add_button).setOnClickListener(v -> {
            newCityInput.setVisibility(View.VISIBLE);
            confirmingButton.setVisibility(View.VISIBLE);
            cancelButton.setVisibility(View.VISIBLE);
        });
        confirmingButton.setOnClickListener(v -> {
                    String strToAdd;
                    strToAdd = String.valueOf(newCityInput.getText());
                    if (!strToAdd.isEmpty()) {
                        dataList.add(strToAdd);
                        // Taken from https://developer.android.com/reference/android/widget/ArrayAdapter
                        // Authored By a Google Employee
                        // Taken By Tegen Hilker Readman
                        // Taken on 2024-01-16
                        cityAdapter.notifyDataSetChanged();
                        newCityInput.setText("");
                        newCityInput.setVisibility(View.GONE);
                        confirmingButton.setVisibility(View.GONE);
                        cancelButton.setVisibility(View.GONE);

                    }


                }
        );
        cancelButton.setOnClickListener(v -> {
            newCityInput.setText("");
            newCityInput.setVisibility(View.GONE);
            confirmingButton.setVisibility(View.GONE);
            cancelButton.setVisibility(View.GONE);

        });

        // https://stackoverflow.com/a/21264550
        newCityInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    newCityInput.setHint("");
                else
                    newCityInput.setHint("Your hint");
            }
        });
    }
}