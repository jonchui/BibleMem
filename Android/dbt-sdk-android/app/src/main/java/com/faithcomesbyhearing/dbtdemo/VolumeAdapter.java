package com.faithcomesbyhearing.dbtdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.faithcomesbyhearing.dbt.model.Volume;

import java.util.List;

public class VolumeAdapter extends BaseAdapter {

    private List<Volume> volumes;
    private LayoutInflater inflater;

    public VolumeAdapter(Context context, List<Volume> volumes) {
        inflater = LayoutInflater.from(context);
        this.volumes = volumes;
    }

    @Override
    public int getCount() {
        return volumes.size();
    }

    @Override
    public Object getItem(int position) {
        return volumes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        Volume currentVolume = volumes.get(position);

        if (convertView == null) {
            convertView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        StringBuilder builder = new StringBuilder(currentVolume.getVolumeName());
        builder.append(" ");
        builder.append(currentVolume.getCollectionName());
        builder.append(" ");
        builder.append(currentVolume.getMediaType());

        holder.languageVolume.setText(builder.toString());

        return convertView;
    }

    private class ViewHolder {

        TextView languageVolume;

        public ViewHolder(View view) {
            languageVolume = (TextView) view.findViewById(android.R.id.text1);
        }
    }
}
