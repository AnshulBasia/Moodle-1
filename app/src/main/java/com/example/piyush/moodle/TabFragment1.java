package com.example.piyush.moodle;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class TabFragment1 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String detail[]=courseslist.course_details;
        String name="Course name    : "+detail[0],id="Course id         : "+detail[1],credits="Course credits : "+detail[2],ltp="Course ltp        : "+detail[3];
        String test[]={name, id, credits, ltp};
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.tab_fragment_1, container, false);
        Toast.makeText(getActivity(),detail[0]+detail[1]+detail[2]+detail[3] , Toast.LENGTH_SHORT).show();
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(),R.layout.rowlayout1,R.id.txtitem1,test);
        // Bind adapter to the ListFragment
        ListView lv = (ListView)rootView.findViewById(R.id.mainListView1);
        lv.setAdapter(adapter);
        //  Retain the ListFragment instance across Activity re-creation
        setRetainInstance(true);

        /*
        TextView output=(TextView)getActivity().findViewById(R.id.coursename2);
        output.setText(name);

        output=(TextView)getView().findViewById(R.id.coursecode2);
        output.setText(id);
        output=(TextView)getView().findViewById(R.id.coursecredits2);
        output.setText(credits);
        output=(TextView)getView().findViewById(R.id.courseltp2);
        output.setText(ltp);
        */
        return rootView;
    }
}