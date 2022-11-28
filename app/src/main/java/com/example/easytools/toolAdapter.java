package com.example.easytools;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class toolAdapter extends ArrayAdapter<Tool> {

    public toolAdapter(Context context, ArrayList<Tool> toolList) {
        super(context, 0, toolList);
    }
    public View getView (int position,View convertView, ViewGroup parent){
        Tool myTool = getItem(position);
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.one_line_tool, parent, false);
        }
        TextView tvToolName = (TextView) convertView.findViewById(R.id.toolName);
        TextView tvOwnerEmail = (TextView) convertView.findViewById(R.id.ownerEmail);
        TextView tvAval = (TextView) convertView.findViewById(R.id.tvAval);
        tvToolName.setText(myTool.getName());
        tvOwnerEmail.setText(myTool.getMyUserName());
        if(myTool.isAval()==true) {
            tvAval.setText("Available!");
        }else{
            tvAval.setText("Not Available!");
        }
        return convertView;
    }
}
