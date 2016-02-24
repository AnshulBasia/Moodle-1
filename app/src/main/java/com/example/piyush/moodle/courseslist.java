package com.example.piyush.moodle;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
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
import android.widget.*;
public class courseslist extends AppCompatActivity {
    public static String coursecode="";
    public static String assignment="";
    public final String IP_ADDRESS = login.ipaddress();
    public final String API_COURSES = IP_ADDRESS + "/courses/list.json";
    public  String API_COURSE_GRADES=IP_ADDRESS+"/courses/course.json/"+coursecode+"/grades";
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
    public static ArrayList<String> grades_names = new ArrayList();
    public static ArrayList<String> grades_weightage = new ArrayList();
    public static ArrayList<String> grades_score = new ArrayList();
    public static ArrayList<String> grades_maximum = new ArrayList();
    public static ArrayList<String> assignment_names = new ArrayList();
    public static ArrayList<String> assignment_id = new ArrayList();
    public static ArrayList<String> deadline = new ArrayList();
    public static ArrayList<String> description = new ArrayList();
    public static String[] course_details=new String[4];
    public  String API_LIST_ASSIGNMENTS=login.ipaddress()+"/courses/course.json/"+courseslist.coursecode+"/assignments";
    public final String API_LIST_COURSE_THREADS=IP_ADDRESS+"/courses/course.json/"+coursecode+"/threads";
    //public final String API_INFO_THREAD=IP_ADDRESS+"/threads/thread.json/"+threadno;
    public final String API_ADD_THREAD=login.ipaddress()+"/threads/new.json?title="+" THREAD TITLE 01 "+"&description="+" THREAD DESCRIPTION 01 "+"&course_code="+coursecode;
    //public final String API_COMMENT_THREAD=IP_ADDRESS+"/threads/post_comment.json?thread_id="+threadid+"&description="+threaddesc;


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
                listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, course_codes);

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
        for(int i=0;i<course_codes.size();i++)
        {
            if(s.equals(course_codes.get(i)))
            {
                details[0]=course_names.get(i);
                details[1]=course_codes.get(i);
                details[2]=course_credits.get(i);
                details[3]=course_ltp.get(i);
            }
        }
        return details;
    }
    public void response_listassignments(String json) {                                       //TODO this functio
        try {
            JSONObject jobject = new JSONObject(json);

            JSONArray assignment = jobject.getJSONArray("assignments");

            for (int i = 0; i < assignment.length(); i++) {
                System.out.println(coursecode+assignment_names.size()+" inside loop i= "+i);
                Toast.makeText(this, coursecode+assignment_names.size()+" inside loop i= "+i, Toast.LENGTH_SHORT).show();
                JSONObject jo = assignment.getJSONObject(i);
                assignment_names.add(jo.getString("name"));
                assignment_id.add(jo.getString("id"));
                deadline.add(jo.getString("deadline"));
                description.add(jo.getString("description"));
                Toast.makeText(courseslist.this, assignment_names.toString(), Toast.LENGTH_SHORT).show();
                System.out.println("HURRAY");
            }
            Intent intent3= new Intent(this, MainActivity.class);
            startActivity(intent3);
            Toast.makeText(this, "called"+courseslist.coursecode+assignment_names.size(), Toast.LENGTH_SHORT).show();
        } catch (org.json.JSONException e) {
            e.printStackTrace();
        }
    }
    public void get_listassignments(){
        System.out.println(coursecode+assignment_names.size()+" 1");
        Toast.makeText(this, coursecode+assignment_names.size()+" 1", Toast.LENGTH_SHORT).show();
        requestQueue = Volley.newRequestQueue(this);
        System.out.println(coursecode+assignment_names.size()+" 2");
        StringRequest stringRequest = new StringRequest(Request.Method.GET,API_LIST_ASSIGNMENTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        response_listassignments(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(courseslist.this,error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        requestQueue.add(stringRequest);
    }
    public  void response_course_grades(String json) {

        try {
            JSONObject jobject = new JSONObject(json);
            JSONArray grades = jobject.getJSONArray("grades");
            System.out.println(grades);
            for (int i = 0; i < grades.length(); i++) {
                JSONObject jo = grades.getJSONObject(i);
                grades_names.add(jo.getString("name"));
                grades_weightage.add(jo.getString("weightage"));
                grades_score.add(jo.getString("score"));
                grades_maximum.add(jo.getString("out_of"));

                Toast.makeText(courseslist.this, grades_names.toString(), Toast.LENGTH_LONG).show();
                System.out.println("HURRAY");
            }
        } catch (org.json.JSONException e) {
            e.printStackTrace();
        }
    }
    public void get_course_grades(){
        requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET,API_COURSE_GRADES,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        response_course_grades(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(courseslist.this,error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        requestQueue.add(stringRequest);
    }
    public void getcoursedetails(View v)
    {

        TextView tv=(TextView)v;
        //v.setTag("Coursera"+position);
        coursecode = ((TextView) v).getText().toString();
        API_LIST_ASSIGNMENTS=login.ipaddress()+"/courses/course.json/"+courseslist.coursecode+"/assignments";
        API_COURSE_GRADES=login.ipaddress()+"/courses/course.json/"+coursecode+"/grades";
        //Toast.makeText(this, coursecode+"sfdf", Toast.LENGTH_LONG).show();
        course_details=details(coursecode);
        get_listassignments();
        get_course_grades();

        System.out.println(coursecode + assignment_names.size() + " 0");
        Toast.makeText(this, coursecode+assignment_names.size() + " 0", Toast.LENGTH_SHORT).show();

        //Intent intent3= new Intent(this, MainActivity.class);
        //startActivity(intent3);
    }
    public void gotonotification(View v)
    {
        Intent intent=new Intent(this, notification.class);
        startActivity(intent);
    }
}