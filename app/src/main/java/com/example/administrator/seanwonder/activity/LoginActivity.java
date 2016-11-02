package com.example.administrator.seanwonder.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.seanwonder.BaseActivity;
import com.example.administrator.seanwonder.MainActivity;
import com.example.administrator.seanwonder.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sean on 2016/11/2.
 */

public class LoginActivity extends BaseActivity {
    private TextInputLayout textInputLayout, textInputLayout2;
    private EditText editText, editText2;
    private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findView();
        initView();
    }

    private void findView() {
        textInputLayout = (TextInputLayout) findViewById(R.id.textInputLayout);
        textInputLayout2 = (TextInputLayout) findViewById(R.id.textInputLayout2);
        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);
        btn_login = (Button) findViewById(R.id.btn_login);
    }

    private void initView() {
        editText.addTextChangedListener(textWatcher);
        editText2.addTextChangedListener(textWatcher2);
        btn_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                if ("15626272292".equals(editText.getText().toString()) && "123456".equals(editText2.getText().toString())) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "账号密码不正确", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        super.onClick(view);
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String phone = editText.getText().toString();
            if (TextUtils.isEmpty(phone) || !isPhone(editText.getText().toString())) {
                textInputLayout.setErrorEnabled(true);
                textInputLayout.setError("手机号码不合法");
                editText.requestFocus();
            } else {
                textInputLayout.setErrorEnabled(false);
            }
        }
    };

    private TextWatcher textWatcher2 = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String password = editText2.getText().toString();
            if (TextUtils.isEmpty(password)) {
                textInputLayout2.setErrorEnabled(true);
                textInputLayout2.setError("密码不能为空");
                editText2.requestFocus();
            } else if (password.length() < 6) {
                textInputLayout2.setErrorEnabled(true);
                textInputLayout2.setError("密码长度为6到16位");
                editText2.requestFocus();
            } else {
                textInputLayout2.setErrorEnabled(false);
            }
        }
    };

    /**
     * 要更加准确的匹配手机号码只匹配11位数字是不够的，比如说就没有以144开始的号码段，
     * 故先要整清楚现在已经开放了多少个号码段，国家号码段分配如下：
     * 移动：134、135、136、137、138、139、150、151、152、157(TD)、158、159、182、183、184、187、188、147、178、1340-1348
     * 联通：130、131、132、145、152、155、156、176、175、185、186
     * 电信：133、153、180、181、189、177、173、149、（1349卫通）
     */
    public static boolean isPhone(String param) {
        //Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,3-9]))\\d{8}$");
        Pattern p = Pattern.compile("^1(3[0-9]|4[57]|5[0-35-9]|7[0135678]|8[0-9])\\d{8}$");
        Matcher m = p.matcher(param);
        return m.matches();
    }
}
