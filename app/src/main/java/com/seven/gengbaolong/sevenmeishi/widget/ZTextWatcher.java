package com.seven.gengbaolong.sevenmeishi.widget;

import android.support.design.widget.TextInputLayout;

/**
 * Created by gengbaolong on 2017/3/15.
 */

public abstract class ZTextWatcher implements android.text.TextWatcher {

    private TextInputLayout textInputLayout;

    public ZTextWatcher(TextInputLayout textInputLayout) {
        this.textInputLayout = textInputLayout;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    public String getEditString() {
        return textInputLayout.getEditText().getText().toString();
    }

}
