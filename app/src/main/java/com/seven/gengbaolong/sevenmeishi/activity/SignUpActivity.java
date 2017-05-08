package com.seven.gengbaolong.sevenmeishi.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dd.processbutton.iml.ActionProcessButton;
import com.seven.gengbaolong.sevenmeishi.R;
import com.seven.gengbaolong.sevenmeishi.presenter.SignUpPresenter;
import com.seven.gengbaolong.sevenmeishi.presenter.impl.SignUpPresenterImpl;
import com.seven.gengbaolong.sevenmeishi.view.SignView;
import com.seven.gengbaolong.sevenmeishi.widget.ZTextWatcher;

import org.sunger.net.utils.FormValidation;
import org.sunger.net.utils.KeyboardUtils;
import org.sunger.net.utils.WidgetUtils;

/**
 * 注册新账户页面
 * Created by gengbaolong on 2017/3/7.
 */

public class SignUpActivity extends BaseCompatActivity implements SignView, View.OnClickListener{
    private static final int DELAY_MILLIS = 1 * 1000;
    private TextInputLayout mTextInputLayoutPhone;
    private TextInputLayout mTextInputLayoutPassword;
    private EditText mEditTextVerifyCode;
    private Button mButtonSendVerifyCode;
    private ActionProcessButton mButtonSignUp;
    private SignUpPresenter mPresenter;
    private int verifyCodeCountdown = 30;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mPresenter = new SignUpPresenterImpl(this);
        mTextInputLayoutPhone = findView(R.id.textInputLayout_phone);
        mTextInputLayoutPassword = findView(R.id.textInputLayout_password);
        mEditTextVerifyCode = findView(R.id.editText_verify_code);
        mButtonSendVerifyCode = findView(R.id.button_send_verify_code);
        mButtonSendVerifyCode.setOnClickListener(this);
        mButtonSignUp = findView(R.id.buttonSignUp);
        mButtonSignUp.setOnClickListener(this);
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

        mTextInputLayoutPassword.getEditText().addTextChangedListener(new ZTextWatcher(mTextInputLayoutPassword) {
            @Override
            public void afterTextChanged(Editable s) {
                if (getEditString().length() > 5) {
                    if (FormValidation.isSimplePassword(getEditString())) {
                        mTextInputLayoutPassword.setErrorEnabled(false);
                    } else {
                        setEditTextError(mTextInputLayoutPassword, R.string.msg_error_password);
                    }
                }
            }
        });
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

    @Override
    public void onClick(View v) {
        KeyboardUtils.hide(this);
        String phone = mTextInputLayoutPhone.getEditText().getText().toString();
        String password = mTextInputLayoutPassword.getEditText().getText().toString();
        if(valid(phone, password)){
            return;
        }
        switch (v.getId()){
            case R.id.button_send_verify_code:
                mPresenter.getVerifySMS(phone, password);
                break;
            case R.id.buttonSignUp:
                String verify_code = mEditTextVerifyCode.getText().toString();
                if(FormValidation.isVerifyCode(verify_code)){
                    mPresenter.signUp(phone, password, verify_code);
                }else{
                    WidgetUtils.requestFocus(mEditTextVerifyCode);
                }
                break;
        }
    }


    private void setEditTextError(TextInputLayout layout, int msgId) {
        layout.setErrorEnabled(true);
        layout.setError(getString(msgId));
    }



    //这是SignView的五个实现方法
    @Override
    public void showVerifyError(String errorMsg) {
        showMsgInBottom(errorMsg);
    }

    @Override
    public void showVerifySuccess() {
        mButtonSendVerifyCode.setClickable(false);
        taskHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(verifyCodeCountdown == 0){
                    mButtonSendVerifyCode.setClickable(true);
                    mButtonSendVerifyCode.setText(R.string.msg_get_verify_code);
                    return;
                }
                mButtonSendVerifyCode.setText(verifyCodeCountdown + getString(R.string.msg_verify_code_point));
                verifyCodeCountdown--;
                taskHandler.postDelayed(this, DELAY_MILLIS);
            }
        },DELAY_MILLIS);
    }

    @Override
    public void showSignError(String errorMsg) {
        mButtonSignUp.setProgress(-1);
        showMsgInBottom(errorMsg);
    }

    @Override
    public void showSignSuccess() {
        mButtonSignUp.setProgress(100);
    }

    @Override
    public void showMsg(String msg) {
        showMsgInBottom(msg);
    }


}
