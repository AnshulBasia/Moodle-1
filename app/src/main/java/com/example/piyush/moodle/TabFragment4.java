package com.example.piyush.moodle;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class TabFragment4 extends Fragment {
    private ListView mainListView ;
    private ArrayAdapter<String> listAdapter ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.tab_fragment_4, container, false);
        Toast.makeText(getActivity(), " Size of threads " + courseslist.threads_title.size(), Toast.LENGTH_SHORT).show();


        int n=courseslist.threads_title.size();
        //String[] threads={"adsf"};
        String threads[]=new String[n];

        for(int i=0;i<n;i++)
        {
            String s= "Title : "+courseslist.threads_title.get(i)+"\nDescription : "+courseslist.threads_desc.get(i)+"\nCreated on : "+courseslist.threads_createdat;
           threads[i]=s;
        }

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(),R.layout.rowlayout4,R.id.txtitem4,threads);
        // Bind adapter to the ListFragment
        ListView lv = (ListView)rootView.findViewById(R.id.mainListView4);
        lv.setAdapter(adapter);
        //  Retain the ListFragment instance across Activity re-creation
        setRetainInstance(true);

        return rootView;
        //return inflater.inflate(R.layout.tab_fragment_4, container, false);
    }
}
