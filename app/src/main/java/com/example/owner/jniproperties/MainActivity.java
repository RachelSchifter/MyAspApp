package com.example.owner.jniproperties;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //loading the library
    static {
        System.loadLibrary("native-lib");
    }

    private final String KINGS_TOAST_TEXT = "Welcome ranking111 and barvaserman!";

    /**
     * this method shows a toast message
     */
    protected void popup() {
        // popup window
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.kings_toast,
                (ViewGroup) findViewById(R.id.custom_toast_container));

        TextView text = (TextView) layout.findViewById(R.id.kingsToastText);
        text.setText(KINGS_TOAST_TEXT);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }

    /**
     * the method makes an ArrayList of properties and their values, and show them as a list.
     *
     * @param savedInstanceState - save the lastest instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.properties_listview);
        //Init ArrayList of MyObject
        ArrayList<SystemProperty> myPropertyList = getPropertiesArrayList();

        PropertyAdapter myAdapter = new PropertyAdapter(this, myPropertyList);
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(new OnItemClickListener() {
            /**
             * When an item get clicked, a toast with the property name and value pops to the
             * screen.
             * @param parent - the adapterView parent
             * @param view -
             * @param position - the position of the property in the arrayList
             * @param id - the id of the row
             */
            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view, int position, long id) {
                SystemProperty clickedObj = (SystemProperty) parent.getItemAtPosition(position);
                //make toast longer
                for (int i = 0; i < 2; i++) {
                    Toast.makeText(MainActivity.this,
                            clickedObj.getPropertyName() + ":\n" +
                                    clickedObj.getProperty(),
                            Toast.LENGTH_LONG).show();
                }

            }
        });
        popup();
    }

    // Get system properties using JNI
    public native ArrayList<SystemProperty> getPropertiesArrayList();
}


