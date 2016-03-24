package com.demo.pug3eye.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class LoginActivity extends ActionBarActivity {

    private Button mLogin;
    private EditText mUsername;
    private EditText mPassword;
    private TextView mRegister;
    private Context mContext;

    private UserManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login);

        mManager = new UserManager(this);

        mContext = this;

        mLogin = (Button) findViewById(R.id.button_login);
        mUsername = (EditText) findViewById(R.id.username);
        mPassword = (EditText) findViewById(R.id.password);
        mRegister = (TextView) findViewById(R.id.register);

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();
            }
        });

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void checkLogin() {
        String username = mUsername.getText().toString().trim().toLowerCase();
        String password = mPassword.getText().toString().trim();

        boolean isSuccess = mManager.checkLoginValidate(username, password);

        if (isSuccess) {
            Intent intent = new Intent(mContext, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            String message = getString(R.string.login_error_message);
            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
        }
    }
}
