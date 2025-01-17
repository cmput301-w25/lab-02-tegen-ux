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
        String[] cities = {"Edmonton","Sylvan Lake",
                "Vancouver", "Spruce Grove","Camrose","Tegen","Awesome","Poggers"};

        // Then we can declare a new arrayList
        dataList = new ArrayList<>();

        // Then we all all of our cities into that array
        dataList.addAll(Arrays.asList(cities));
        /*
         Then we do some adapter thing, which I think just relays our array here to the actual app
         All I know is that it links all this code to the content.xml that we had earlier.
        */


        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        // Same again, I have no clue what is there
        cityList.setAdapter(cityAdapter);
        //Delete button
        Button deleteButton = findViewById(R.id.delete_button);
        // This just pops the last item off the city list
        deleteButton.setOnClickListener(v -> {
            if (!dataList.isEmpty()) {
                dataList.remove(dataList.size() - 1);
                /*
                Taken from https://developer.android.com/reference/android/widget/ArrayAdapter
                Authored By a Google Employee
                Taken By Tegen Hilker Readman
                Taken on 2024-01-16
                 */
                cityAdapter.notifyDataSetChanged();
                /* Taken from https://developer.android.com/reference/android/widget/AbsListView#invalidateViews()
                Authored by A Google Employee
                Taken By Tegen Hilker Readman
                Taken on 2024-01-17
                 */
                cityList.invalidateViews();
            }
        });
            /*
             Taken from //https://stackoverflow.com/a/70769157
            Authored by Aman Bhatia
            Taken by Tegen Hilker Readman
            Taken on: 2024-01-15
            */

        // Adding references to all our views rather than calling `findViewById` a bunch
        EditText newCityInput = findViewById(R.id.new_city);
        Button confirmingButton = findViewById(R.id.confirm_button);
        Button cancelButton = findViewById(R.id.cancel_button);
        Button addCity = findViewById(R.id.add_button);
        // The add city button makes it so that our adding city steps are visible
        addCity.setOnClickListener(v -> {
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
                cityList.invalidateViews();
                // Honestly the adding of to the view is so slow so I have this so I can be sane
                Log.d("MainActivity", "City added: " + strToAdd);
                Log.d("MainActivity", "List size: " + dataList.size());
                newCityInput.setText("");
                newCityInput.setVisibility(View.GONE);
                confirmingButton.setVisibility(View.GONE);
                cancelButton.setVisibility(View.GONE);


            }});
        cancelButton.setOnClickListener(v -> {
            newCityInput.setText(""); // Clear the input text
            newCityInput.setVisibility(View.GONE); // Make the views disappear
            confirmingButton.setVisibility(View.GONE);
            cancelButton.setVisibility(View.GONE);
            cityList.invalidateViews();


        });
        //Taken from https://stackoverflow.com/a/21264550
        // Authored By Rohan Kandwal
        // Edited by Yehuda Clinton
        // Taken By Tegen Hilker Readman
        // Taken on 2024-01-16
        // This is used to make the cursor/hint go away when the person clicks on the text
        newCityInput.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus)
                newCityInput.setHint("");
            else
                newCityInput.setHint("Enter a city");
        });
    }
}