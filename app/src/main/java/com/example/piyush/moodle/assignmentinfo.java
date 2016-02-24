package com.example.piyush.moodle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class assignmentinfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignmentinfo);
        TextView output=(TextView)findViewById(R.id.nameass);
        output.setText(MainActivity.ass_name);
        output=(TextView)findViewById(R.id.idass);
        output.setText(MainActivity.ass_id);
        output=(TextView)findViewById(R.id.descriptionass);
        output.setText(MainActivity.ass_descriptopn);
        output=(TextView)findViewById(R.id.deadlineass);
        output.setText(MainActivity.ass_deadline);
    }

}
