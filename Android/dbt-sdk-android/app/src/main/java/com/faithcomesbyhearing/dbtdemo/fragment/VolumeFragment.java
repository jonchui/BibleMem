package com.faithcomesbyhearing.dbtdemo.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.faithcomesbyhearing.dbt.Dbt;
import com.faithcomesbyhearing.dbt.callback.VolumeCallback;
import com.faithcomesbyhearing.dbt.model.Volume;
import com.faithcomesbyhearing.dbtdemo.R;
import com.faithcomesbyhearing.dbtdemo.ShowFragmentCallback;
import com.faithcomesbyhearing.dbtdemo.VolumeAdapter;

import java.util.List;

;

public class VolumeFragment extends Fragment implements AdapterView.OnItemClickListener, TextWatcher {

    private VolumeAdapter adapter;
    private ListView volumeList;
    private EditText searchText;
    private ShowFragmentCallback callback;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        callback = (ShowFragmentCallback) activity;
        ((ActionBarActivity) activity).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_volume, container, false);
        volumeList = (ListView) view.findViewById(R.id.volume_list);
        searchText = (EditText) view.findViewById(R.id.volume_search);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        volumeList.setOnItemClickListener(this);
        searchText.addTextChangedListener(this);
        searchText.setText("Eng");
        getVolumes("Eng");
    }

    @Override
    public void onResume() {
        super.onResume();
        ((ActionBarActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    public void refreshList(List<Volume> volumes) {
        adapter = new VolumeAdapter(getActivity(), volumes);
        volumeList.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Volume volume = (Volume) adapter.getItem(position);
        Bundle bundle = new Bundle();
        bundle.putString(BookFragment.DAM_ID_KEY, volume.getDamId());
        Fragment fragment = new BookFragment();
        fragment.setArguments(bundle);
        callback.showFragment(fragment, true);
        hideKeyboard();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (!s.toString().isEmpty()) {
            getVolumes(s.toString());
        }
    }

    private void getVolumes(String languageCode) {
        Dbt.getLibraryVolume(null, "text", null, languageCode, new VolumeCallback() {
            @Override
            public void success(List<Volume> volumes) {
                if (!volumes.isEmpty()) {
                    ((ActionBarActivity)getActivity()).getSupportActionBar().setTitle(volumes.get(0).getLangaugeEnglish());
                }
                refreshList(volumes);
            }

            @Override
            public void failure(Exception e) {
                //TODO: show error
            }
        });
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(searchText.getWindowToken(), 0);
    }
}
