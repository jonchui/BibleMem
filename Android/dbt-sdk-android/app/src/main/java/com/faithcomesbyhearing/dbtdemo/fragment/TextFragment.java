package com.faithcomesbyhearing.dbtdemo.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.faithcomesbyhearing.dbt.Dbt;
import com.faithcomesbyhearing.dbt.callback.VerseCallback;
import com.faithcomesbyhearing.dbt.model.Verse;
import com.faithcomesbyhearing.dbtdemo.R;

import java.util.List;

public class TextFragment extends Fragment {

    public static final String DAM_ID_KEY = "dam_id_key";
    public static final String BOOK_ID_KEY = "book_id_key";
    public static final String CHAPTER_ID_KEY = "chapter_id_key";

    private TextView text;
    private LinearLayout progressContainer;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((ActionBarActivity) activity).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_text, container, false);
        text = (TextView) view.findViewById(R.id.text_verses);
        progressContainer = (LinearLayout) view.findViewById(R.id.progressContainer);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressContainer.setVisibility(View.VISIBLE);
        String damId = getArguments().getString(DAM_ID_KEY);
        String bookId = getArguments().getString(BOOK_ID_KEY);
        final String chapterId = getArguments().getString(CHAPTER_ID_KEY);
        Dbt.getTextVerse(damId, bookId, chapterId, null, null, new VerseCallback() {
            @Override
            public void success(List<Verse> verses) {
                if (getActivity() != null) {
                    ((ActionBarActivity) getActivity()).getSupportActionBar().setTitle(verses.get(0).getBookName() + " - " + chapterId);
                    buildVersesString(verses);
                }
            }

            @Override
            public void failure(Exception e) {

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

    private void buildVersesString(List<Verse> verses) {
        SpannableStringBuilder builder = new SpannableStringBuilder();
        for (Verse verse : verses) {
            builder.append(verse.getVerseId());
            builder.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD),
                    builder.length() - verse.getVerseId().length(),
                    builder.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            builder.append(". ");
            builder.append(verse.getVerseText().replace("\n", ""));
        }
        progressContainer.setVisibility(View.GONE);
        text.setText(builder);
    }
}
