package com.seven.gengbaolong.sevenmeishi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.dd.processbutton.iml.ActionProcessButton;
import com.seven.gengbaolong.sevenmeishi.R;
import com.seven.gengbaolong.sevenmeishi.presenter.LoginPresenter;
import com.seven.gengbaolong.sevenmeishi.presenter.impl.LoginPresenterImpl;
import com.seven.gengbaolong.sevenmeishi.view.LoginView;
import com.seven.gengbaolong.sevenmeishi.widget.ZTextWatcher;

import org.sunger.net.utils.FormValidation;
import org.sunger.net.utils.KeyboardUtils;
import org.sunger.net.utils.WidgetUtils;

/**
 * Created by gengbaolong on 2017/3/7.
 */

public class LoginActivity extends BaseCompatActivity implements View.OnClickListener, LoginView{
    private LoginPresenter mPresenter;
    //ActionProcessButton-------内置进度条功能的Android自定义按纽
    private ActionProcessButton loginProcessButton;
    //Design Support Library中的TextInputLayout控件.TextInputLayout控件和LinearLayout完全一样，它只是一个容器。跟ScrollView一样，TextInputLayout只接受一个子元素。子元素需要是一个EditText元素。
    // http://www.jcodecraeer.com/a/basictutorial/2015/0821/3338.html
    private TextInputLayout mTextInputLayoutPhone;
    private TextInputLayout mTextInputLayoutPassword;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setUpCommonBackToolBar(R.id.tool_bar, R.string.title_login);
        loginProcessButton = (ActionProcessButton) findViewById(R.id.button_sign);
        loginProcessButton.setOnClickListener(this);
        mTextInputLayoutPhone = findView(R.id.textInputLayout_phone);
        mTextInputLayoutPhone.getEditText().addTextChangedListener(new ZTextWatcher(mTextInputLayoutPhone) {
            @Override
            public void afterTextChanged(Editable s) {
                if (getEditString().length() > 10) {
                    if (FormValidation.isMobile(getEditString())) {
                        mTextInputLayoutPhone.setErrorEnabled(false);
                    } else {
                        setEditTextError(mTextInputLayoutPhone, R.string.msg_error_phone);
                    }
                }
            }
        });

        mTextInputLayoutPassword = findView(R.id.textInputLayout_password);
        mTextInputLayoutPassword.getEditText().addTextChangedListener(new ZTextWatcher(mTextInputLayoutPassword) {
            @Override
            public void afterTextChanged(Editable s) {
                if (getEditString().length() > 5)
                    if (FormValidation.isSimplePassword(getEditString())) {
                        mTextInputLayoutPassword.setErrorEnabled(false);
                    } else {
                        setEditTextError(mTextInputLayoutPassword, R.string.msg_error_password);
                    }
            }
        });

        findViewById(R.id.textView_create_account).setOnClickListener(this);
        findView(R.id.textView_reset_password).setOnClickListener(this);
        mPresenter = new LoginPresenterImpl(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textView_create_account:
                startActivity(new Intent(this, SignUpActivity.class));
                finish();
                break;
            case R.id.button_sign:
                login();
                break;
            case R.id.textView_reset_password:
                startActivity(new Intent(this, ResetPasswordActivity.class));
                finish();
                break;
        }
    }

    public void login() {
        KeyboardUtils.hide(this);
        String phone = mTextInputLayoutPhone.getEditText().getText().toString();
        String password = mTextInputLayoutPassword.getEditText().getText().toString();
        if (valid(phone, password))
            return;
        mPresenter.login(phone, password);
        loginProcessButton.setMode(ActionProcessButton.Mode.ENDLESS);
    }


    public boolean valid(String phone, String password) {
        if (!FormValidation.isMobile(phone)) {
            WidgetUtils.requestFocus(mTextInputLayoutPhone.getEditText());
            setEditTextError(mTextInputLayoutPhone, R.string.msg_error_phone);
            return true;
        }
        if (!FormValidation.isSimplePassword(password)) {
            WidgetUtils.requestFocus(mTextInputLayoutPassword.getEditText());
            setEditTextError(mTextInputLayoutPassword, R.string.msg_error_password);
            return true;
        }
        return false;
    }


    private void setEditTextError(TextInputLayout layout, int msgId) {
        layout.setErrorEnabled(true);
        layout.setError(getString(msgId));
    }

    //这是LoginView中的三个方法

    @Override
    public void showLoading() {
        loginProcessButton.setProgress(50);
    }

    @Override
    public void showLoginFaile(String msg) {
        loginProcessButton.setProgress(-1);
        showMsgInBottom(msg);
    }

    @Override
    public void loginSuccess() {
        loginProcessButton.setProgress(100);
        this.finish();
    }
}
