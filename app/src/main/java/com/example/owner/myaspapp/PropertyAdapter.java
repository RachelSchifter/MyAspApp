package com.example.owner.myaspapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class PropertyAdapter extends BaseAdapter {
    private ArrayList<SystemProperty> propertyList;
    private Activity parentActivity;
    private LayoutInflater inflater;

    /**
     * PropertyAdapter constructor
     *
     * @param parent            - the parent of the activity
     * @param propertyArrayList - list of properties.
     */
    public PropertyAdapter(Activity parent, ArrayList<SystemProperty> propertyArrayList) {
        parentActivity = parent;
        propertyList = propertyArrayList;
        inflater = (LayoutInflater) parentActivity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * counts the number of properties
     *
     * @return that number.
     */
    @Override
    public int getCount() {
        return propertyList.size();
    }

    /**
     * @param position - a position of a property in the arraylist.
     * @return the propety
     */
    @Override
    public Object getItem(int position) {
        return propertyList.get(position);
    }

    /**
     * @param position - a position of a proprty in the arraylist.
     * @return the position
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * @param position    - position of a property in the arrayList
     * @param convertView - the view to convert
     * @param parent      - the ViewGroup parent
     * @return
     */
    @Override
    public View getView(int position, View convertView,
                        ViewGroup parent) {
        View view = convertView;
        if (convertView == null)
            // takes row XML-file and creates a View-object
            view = inflater.inflate(R.layout.row, null);
        TextView propertyName = (TextView) view.findViewById(R.id.propertyName);
        TextView propertyValue = (TextView) view.findViewById(R.id.propertyValue);
        SystemProperty myObj = propertyList.get(position);
        propertyName.setText(String.valueOf(myObj.getPropertyName()) + ": ");
        propertyValue.setText(String.valueOf(myObj.getProperty()));
        return view;
    }
}
