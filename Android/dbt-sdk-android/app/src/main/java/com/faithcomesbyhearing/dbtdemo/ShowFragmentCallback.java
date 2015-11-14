package com.faithcomesbyhearing.dbtdemo;

import android.app.Fragment;

public interface ShowFragmentCallback {
    void showFragment(Fragment fragment, boolean addToBackStack);
}
