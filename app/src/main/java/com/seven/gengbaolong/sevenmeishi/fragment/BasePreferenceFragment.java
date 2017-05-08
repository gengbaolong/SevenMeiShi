package com.seven.gengbaolong.sevenmeishi.fragment;

import android.preference.Preference;
import android.preference.PreferenceFragment;

/**
 * Created by gengbaolong on 2017/3/16.
 */

public class BasePreferenceFragment extends PreferenceFragment {

    public <T extends Preference> T findPreference(String key){
        return (T)getPreferenceScreen().findPreference(key);
    }

    public <T extends Preference> T findPreference(int keyRes) {
        return findPreference(getString(keyRes));
    }

}
