package com.example.owner.myaspapp;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Context;

public class MainActivity extends AppCompatActivity {


        private class SystemProperties{
            private String propertiesName;
            private String properties;

            SystemProperties(String pName, String p){
                propertiesName = pName;
                properties = p;
            }

            public String getPropertiesName(){
                return propertiesName;
            }

            public String getpPoperties(){
                return properties;
            }
        }

        protected void popup(){
            //popup window decleration
            LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(R.layout.custom_toast,
                    (ViewGroup) findViewById(R.id.custom_toast_container));

            TextView text = (TextView)layout.findViewById(R.id.text);
            text.setText("Welcome ranking111 and barvaserman!");

            Toast toast = new Toast(getApplicationContext());
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(layout);
            toast.show();
        }

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView)findViewById(R.id.listview);
            //Init ArrayList of MyObject
            ArrayList<SystemProperties> myPropertyList = new ArrayList<>();

            //get System Properties
            Properties properties = System.getProperties();
            Enumeration<String> propEnum =
                    (Enumeration<String>)properties.propertyNames();

            while(propEnum.hasMoreElements()){
                String propName = propEnum.nextElement();
                String prop = System.getProperty(propName);
                SystemProperties nexProp = new SystemProperties(propName, prop);
                myPropertyList.add(nexProp);
            }


            MyAdapter myAdapter = new MyAdapter(this, myPropertyList);
            listView.setAdapter(myAdapter);

            listView.setOnItemClickListener(new OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> parent,
                                        View view, int position, long id) {
                    SystemProperties clickedObj = (SystemProperties)parent.getItemAtPosition(position);
                    Toast.makeText(MainActivity.this,
                            clickedObj.getPropertiesName() + ":\n" +
                                    clickedObj.getpPoperties(),
                            Toast.LENGTH_LONG).show();
                }});
            popup();

        }

    private class MyAdapter extends BaseAdapter {

        private ArrayList<SystemProperties> myList;

        private Activity parentActivity;
        private LayoutInflater inflater;

        public MyAdapter(Activity parent, ArrayList<SystemProperties> l) {
            parentActivity = parent;
            myList=l;
            inflater = (LayoutInflater)parentActivity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return myList.size();
        }

        @Override
        public Object getItem(int position) {
            return myList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView (int position, View convertView,
                             ViewGroup parent) {
            View view = convertView;
            if(convertView == null)
                view = inflater.inflate(R.layout.row, null);



           // TextView text1 = (TextView)view.findViewById(R.id.text1);
            TextView text1 = (TextView)view.findViewById(R.id.text1);
            //TextView dots = (TextView)view.
            TextView text2 = (TextView)view.findViewById(R.id.text2);
            SystemProperties myObj = myList.get(position);
            text1.setText(String.valueOf(myObj.getPropertiesName()) + ": " );
            text2.setText(myObj.getpPoperties());
            return view;
        }
    }
}


