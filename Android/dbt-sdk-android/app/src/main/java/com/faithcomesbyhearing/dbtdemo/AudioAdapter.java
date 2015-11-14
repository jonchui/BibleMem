package com.faithcomesbyhearing.dbtdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.faithcomesbyhearing.dbt.model.AudioPath;

import java.util.List;

public class AudioAdapter extends BaseAdapter {

    private List<AudioPath> audioPaths;
    private LayoutInflater inflater;

    public AudioAdapter(Context context, List<AudioPath> paths) {
        audioPaths = paths;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return audioPaths.size();
    }

    @Override
    public Object getItem(int position) {
        return audioPaths.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        AudioPath path = audioPaths.get(position);

        if (convertView == null) {
            convertView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.text.setText(path.getBookId() + " - " + path.getChapterId());

        return convertView;
    }

    private class ViewHolder {

        TextView text;

        public ViewHolder(View view) {
            text = (TextView) view.findViewById(android.R.id.text1);
        }
    }
}
