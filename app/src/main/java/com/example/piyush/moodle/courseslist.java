package com.example.piyush.moodle;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class courseslist extends AppCompatActivity {

    public final String IP_ADDRESS = login.ipaddress();
    public final String API_COURSES = IP_ADDRESS + "/courses/list.json";
    /*
    private static final String SET_COOKIE_KEY = "Set-Cookie";
    private static final String COOKIE_KEY = "Cookie";
    private static final String SESSION_COOKIE = "session_id_moodleplus";
    */
    private RequestQueue requestQueue;
    private SharedPreferences preferences;
    ArrayList<String> course_names=new ArrayList<>();                                //this store the name of courses
    ArrayList<String> course_codes=new ArrayList<>();
    ArrayList<String> course_credits=new ArrayList<>();                                //this store the credit of courses
    ArrayList<String> course_ltp=new ArrayList<>();


    private ListView mainListView ;
    private ArrayAdapter<String> listAdapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courseslist);
        requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, API_COURSES,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        response_courses(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(courseslist.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        requestQueue.add(stringRequest);


        mainListView = (ListView) findViewById( R.id.mainListView );

        // Create and populate a List of planet names.
        String[] planets = new String[] { "Mercury", "Venus", "Earth", "Mars",
                "Jupiter", "Saturn", "Uranus", "Neptune"};
/*
        ArrayList<String> planetList = new ArrayList<String>();
        planetList.addAll( Arrays.asList(planets) );
*/
        // Create ArrayAdapter using the planet list.



    }
    public void response_courses(String json){
        try
        {
            JSONObject jobject= new JSONObject(json);
            JSONArray courses=jobject.getJSONArray("courses");

            for(int i=0;i<courses.length();i++){
                JSONObject jo = courses.getJSONObject(i);
                course_names.add(jo.getString("name"));
                course_codes.add(jo.getString("code"));
                course_credits.add(jo.getString("credits"));
                course_ltp.add(jo.getString("l_t_p"));

                //Toast.makeText(courseslist.this,course_names.toString(), Toast.LENGTH_LONG).show();               //TODO change mainactivity to whatever activity
                //System.out.println("HURRAY");
                listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, course_names);

                // Add more planets. If you passed a String[] instead of a List<String>
                // into the ArrayAdapter constructor, you must not add more items.
                // Otherwise an exception will occur.


                // Set the ArrayAdapter as the ListView's adapter.
                mainListView.setAdapter( listAdapter );
            }

        }
        catch (org.json.JSONException e) {
            e.printStackTrace();
        }

    }
    public String[] details(String s)
    {
        String[] details=new String[4];
        for(int i=0;i<course_names.size();i++)
        {
            if(s.equals(course_names.get(i)))
            {
                details[0]=course_names.get(i);
                details[1]=course_codes.get(i);
                details[2]=course_credits.get(i);
                details[3]=course_ltp.get(i);
            }
        }
        return details;
    }
}