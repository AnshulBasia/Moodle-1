package com.example.piyush.moodle;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class TabFragment3 extends Fragment {
    private ListView mainListView ;
    private ArrayAdapter<String> listAdapter ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.tab_fragment_3, container, false);

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.tab_fragment_3, container, false);
        // Create an array of string to be data source of the ListFragment
        ArrayList<String> gradename=courseslist.grades_names;
        ArrayList<String> weightage=courseslist.grades_weightage;
        ArrayList<String> score=courseslist.grades_score;
        ArrayList<String> maximum=courseslist.grades_maximum;
        int n=gradename.size();
        String[] grades=new String[n];
        for(int i=0;i<n;i++)
        {
            grades[i]=" Name: "+gradename.get(i)+" \n "+" Weightage: "+weightage.get(i)+" \n "+" Score: "+score.get(i)+" \n "+" Maximum: "+maximum.get(i)+" \n ";

        }

        // Create ArrayAdapter object to wrap the data source
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(),R.layout.rowlayout,R.id.txtitem,grades);
        // Bind adapter to the ListFragment
        ListView lv = (ListView)rootView.findViewById(R.id.mainListView);
        lv.setAdapter(adapter);
        //  Retain the ListFragment instance across Activity re-creation
        setRetainInstance(true);


        return rootView;
    }
}
