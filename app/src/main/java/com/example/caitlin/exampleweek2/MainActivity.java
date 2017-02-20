package com.example.caitlin.exampleweek2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText etTrack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTrack = (EditText) findViewById(R.id.etTrack);
        assert etTrack != null;
        etTrack.setHint("Search for a Track");
    }

    public void trackSearch(View view) {
        String trackSearch = etTrack.getText().toString();

        TrackAsyncTask asyncTask = new TrackAsyncTask(this);
        asyncTask.execute(trackSearch);

        etTrack.getText().clear();
    }

    /** Start the DataActivity and pass it the datatype and Arraylist of TrackData objects. */
    public void trackStartIntent(ArrayList<String> trackData) {
        Intent dataIntent = new Intent(this, DataActivity.class);
        dataIntent.putExtra("data", trackData);
        this.startActivity(dataIntent);
    }
}
