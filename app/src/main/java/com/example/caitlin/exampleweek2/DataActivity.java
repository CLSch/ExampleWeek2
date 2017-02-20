package com.example.caitlin.exampleweek2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/** This activity gets all the data from the search query and puts it in an adapter for a listview. */
public class DataActivity extends AppCompatActivity {
    ArrayList<String> trackArray;
    ListView lvItems;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        tvResult = (TextView) findViewById(R.id.tvFound);
        assert tvResult != null;

        Bundle extras = getIntent().getExtras();

        trackArray = (ArrayList<String>) extras.getSerializable("data");
        makeTrackAdapter();
    }

    /** Makes an adapter for TrackData objects. */
    public void makeTrackAdapter() {
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, android.R.id.text1, trackArray);
        lvItems = (ListView) findViewById(R.id.listViewID);
        assert lvItems != null;
        lvItems.setAdapter(arrayAdapter);
    }
}

