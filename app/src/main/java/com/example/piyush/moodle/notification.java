package com.example.piyush.moodle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import java.util.Map;
import java.util.*;
public class notification extends AppCompatActivity {
    public final String API_NOTIFICATIONS=login.ipaddress()+"/default/notifications.json";
    private RequestQueue requestQueue;
    private SharedPreferences preferences;
    public static ArrayList<String> desc=new ArrayList<>();
    public static ArrayList<String> is_seen=new ArrayList<>();                                //this store the name of courses
    public static ArrayList<String> created_at=new ArrayList<>();
    private  ListView mainListView ;
    private  ArrayAdapter<String> listAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        get_notifications();

    }
    public void response_notifications(String json){
        //System.out.println("333333333333333333333333"+desc.size());

        try
        {
            JSONObject jobject= new JSONObject(json);
            JSONArray notifications=jobject.getJSONArray("notifications");

            for(int i=0;i<notifications.length();i++){
                System.out.println("444444444444444444444 "+desc.size() + " no "+notifications.length());

                JSONObject jo = notifications.getJSONObject(i);
                desc.add(jo.getString("description"));
                is_seen.add(jo.getString("is_seen"));
                created_at.add(jo.getString("created_at"));
                System.out.println("555555555555555 " + desc.size() + " no " + notifications.length());
                              //TODO change mainactivity to whatever activity
                //System.out.println("HURRAY");
            }
            mainListView = (ListView) findViewById(R.id.mainListView3 );
            //System.out.println("00000000000000000000000000" + desc.size());
            Toast.makeText(this, " Size = " + desc.size(), Toast.LENGTH_SHORT).show();
            int n=desc.size();
            String notifs[]=new String[n];
            for(int i=0;i<desc.size();i++)
            {
                String s="Description:\n"+desc.get(i)+"\n\nCreated at: "+created_at.get(i);
                notifs[i]=s;
            }
            ArrayList<String> planetList = new ArrayList<String>();
            planetList.addAll( Arrays.asList(notifs) );

            // Create ArrayAdapter using the planet list.
            //System.out.println("##################### = "+desc.size());
            listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, planetList);
            mainListView.setAdapter( listAdapter );
            //System.out.println(" ******************** " + desc.size());
        }
        catch (org.json.JSONException e) {
            e.printStackTrace();
        }

    }

    public void get_notifications(){
        //System.out.println("111111111111111111111111"+desc.size());

        requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET,API_NOTIFICATIONS,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        //System.out.println("222222222222222222222222"+desc.size());
                        response_notifications(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(notification.this,error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        requestQueue.add(stringRequest);
    }
}
