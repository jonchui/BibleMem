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
import com.faithcomesbyhearing.dbt.callback.ChapterCallback;
import com.faithcomesbyhearing.dbt.model.Chapter;
import com.faithcomesbyhearing.dbtdemo.ChapterAdapter;
import com.faithcomesbyhearing.dbtdemo.ShowFragmentCallback;

import java.util.ArrayList;
import java.util.List;

public class ChapterFragment extends ListFragment implements AdapterView.OnItemClickListener {

    public static final String DAM_ID_KEY = "dam_id_key";
    public static final String BOOK_ID_KEY = "book_id_key";

    private ChapterAdapter adapter;
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
        String bookId = getArguments().getString(BOOK_ID_KEY);
        ((ActionBarActivity) getActivity()).getSupportActionBar().setTitle("Chapters");
        adapter = new ChapterAdapter(getActivity(), new ArrayList<Chapter>());
        Dbt.getLibraryChapter(damId, bookId, new ChapterCallback() {
            @Override
            public void success(List<Chapter> chapters) {
                refreshList(chapters);
            }

            @Override
            public void failure(Exception e) {
                return;
            }
        });
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

    public void refreshList(List<Chapter> chapters) {
        adapter.swapData(chapters);
        setListAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Chapter chapter = (Chapter) adapter.getItem(position);
        Bundle bundle = new Bundle();
        bundle.putString(TextFragment.DAM_ID_KEY, getArguments().getString(DAM_ID_KEY));
        bundle.putString(TextFragment.BOOK_ID_KEY, getArguments().getString(BOOK_ID_KEY));
        bundle.putString(TextFragment.CHAPTER_ID_KEY, chapter.getChapterId());
        Fragment fragment = new TextFragment();
        fragment.setArguments(bundle);
        callback.showFragment(fragment, true);
    }
}
