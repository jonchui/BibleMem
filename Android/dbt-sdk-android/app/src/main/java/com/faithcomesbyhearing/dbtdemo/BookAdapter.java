package com.faithcomesbyhearing.dbtdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.faithcomesbyhearing.dbt.model.Book;

import java.util.List;

public class BookAdapter extends BaseAdapter {

    private List<Book> books;
    private LayoutInflater inflater;

    public BookAdapter(Context context, List<Book> books) {
        this.books = books;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return books.size();
    }

    @Override
    public Object getItem(int position) {
        return books.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        Book book = books.get(position);

        if (convertView == null) {
            convertView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.text.setText(book.getBookName());

        return convertView;
    }

    private class ViewHolder {

        TextView text;

        public ViewHolder(View view) {
            text = (TextView) view.findViewById(android.R.id.text1);
        }
    }

    public void swapData(List<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }
}
