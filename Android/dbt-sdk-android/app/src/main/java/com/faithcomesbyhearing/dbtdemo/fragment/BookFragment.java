package com.faithcomesbyhearing.dbtdemo.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ListFragment;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.faithcomesbyhearing.dbt.Dbt;
import com.faithcomesbyhearing.dbt.callback.BookCallback;
import com.faithcomesbyhearing.dbt.model.Book;
import com.faithcomesbyhearing.dbtdemo.BookAdapter;
import com.faithcomesbyhearing.dbtdemo.ShowFragmentCallback;

import java.util.ArrayList;
import java.util.List;

public class BookFragment extends ListFragment implements AdapterView.OnItemClickListener {

    public static final String DAM_ID_KEY = "dam_id_key";

    private BookAdapter adapter;
    private ShowFragmentCallback callback;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((ActionBarActivity) activity).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        callback = (ShowFragmentCallback) activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getListView().setOnItemClickListener(this);
        String damId = getArguments().getString(DAM_ID_KEY);
        ((ActionBarActivity) getActivity()).getSupportActionBar().setTitle("Books");
        adapter = new BookAdapter(getActivity(), new ArrayList<Book>());
        Dbt.getLibraryBook(damId, new BookCallback() {
            @Override
            public void success(List<Book> books) {
                refreshList(books);
            }

            @Override
            public void failure(Exception e) {

            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Book book = (Book) adapter.getItem(position);
        Bundle bundle = new Bundle();
        bundle.putString(ChapterFragment.DAM_ID_KEY, getArguments().getString(DAM_ID_KEY));
        bundle.putString(ChapterFragment.BOOK_ID_KEY, book.getBookId());
        Fragment fragment = new ChapterFragment();
        fragment.setArguments(bundle);
        callback.showFragment(fragment, true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.popBackStack();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void refreshList(List<Book> books) {
        adapter.swapData(books);
        setListAdapter(adapter);
    }
}
