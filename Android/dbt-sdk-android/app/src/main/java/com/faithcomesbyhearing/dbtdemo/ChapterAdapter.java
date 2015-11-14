package com.faithcomesbyhearing.dbtdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.faithcomesbyhearing.dbt.model.Chapter;

import java.util.List;

public class ChapterAdapter extends BaseAdapter {

    private List<Chapter> chapters;
    private LayoutInflater inflater;

    public ChapterAdapter(Context context, List<Chapter> chapters) {
        this.chapters = chapters;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return chapters.size();
    }

    @Override
    public Object getItem(int position) {
        return chapters.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        Chapter chapter = chapters.get(position);

        if (convertView == null) {
            convertView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.text.setText(chapter.getChapterName());

        return convertView;
    }

    public void swapData(List<Chapter> chapters) {
        this.chapters = chapters;
        notifyDataSetChanged();
    }

    private class ViewHolder {

        TextView text;

        public ViewHolder(View view) {
            text = (TextView) view.findViewById(android.R.id.text1);
        }
    }
}
