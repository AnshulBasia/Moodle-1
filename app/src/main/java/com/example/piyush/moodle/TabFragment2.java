package com.example.piyush.moodle;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TabFragment2 extends Fragment {
    private ListView mainListView ;
    private ArrayAdapter<String> listAdapter ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        //mainListView = (ListView)getView().findViewById( R.id.mainListView );
        /*
        String[] planets = new String[] { "Mercury", "Venus", "Earth", "Mars",
                "Jupiter", "Saturn", "Uranus", "Neptune"};
        ArrayList<String> planetList = new ArrayList<String>();
        planetList.addAll( Arrays.asList(planets) );

        // Create ArrayAdapter using the planet list.
        listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.rowlayout, planetList);

        // Add more planets. If you passed a String[] instead of a List<String>
        // into the ArrayAdapter constructor, you must not add more items.
        // Otherwise an exception will occur.
        listAdapter.add( "Ceres" );
        listAdapter.add( "Pluto" );
        listAdapter.add( "Haumea" );
        listAdapter.add( "Makemake" );
        listAdapter.add( "Eris" );


        // Set the ArrayAdapter as the ListView's adapter.
        mainListView.setAdapter( listAdapter );
        */


        //return inflater.inflate(R.layout.tab_fragment_2, container, false);

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.tab_fragment_2, container, false);
        // Create an array of string to be data source of the ListFragment
        String[] courses={"English","French","Khmer","Japanese","Russian","Chinese"};
        // Create ArrayAdapter object to wrap the data source
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(),R.layout.rowlayout,R.id.txtitem,courses);
        // Bind adapter to the ListFragment
        ListView lv = (ListView)rootView.findViewById(R.id.mainListView);
        lv.setAdapter(adapter);
        //  Retain the ListFragment instance across Activity re-creation
        setRetainInstance(true);


        return rootView;

    }

}