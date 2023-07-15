package com.example.arunawijesinghe.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.arunawijesinghe.R;
import com.example.arunawijesinghe.constants.RetrofitClient;
import com.example.arunawijesinghe.model.LoginResponse;
import com.example.arunawijesinghe.model.User;
import com.example.arunawijesinghe.model.UserAuthentication;
import com.example.arunawijesinghe.util.AppUtillity;
import com.example.arunawijesinghe.util.DBManager;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private EditText et_username, et_password;
    private Button btn_login;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        btn_login = findViewById(R.id.btn_login);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AppUtillity.isInternetAvailable(MainActivity.this)) {
                    if (!isAllEmpty()) {
                        if (TextUtils.isEmpty(et_username.getText().toString())) {
                            Toast.makeText(MainActivity.this, "Please Enter username", Toast.LENGTH_SHORT).show();
                        } else if (TextUtils.isEmpty(et_password.getText().toString())) {
                            Toast.makeText(MainActivity.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                        } else {
                            doAuthentication(new UserAuthentication(et_username.getText().toString(), et_password.getText().toString()));
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "All Fields are required!", Toast.LENGTH_SHORT).show();

                    }
                } else {
                    Toast.makeText(MainActivity.this, "Check your internet connection", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void doAuthentication(UserAuthentication authentication) {

        dbManager = new DBManager(MainActivity.this);
        dbManager.open();
        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("wait for Authentication..!"); // set message
        progressDialog.show();
        RetrofitClient.getInstance().getRetrofitApi().login(authentication).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if (response.isSuccessful()) {
                    LoginResponse loginResponse = response.body();
                    if (loginResponse.getRes_code() == 0) {
                        dbManager.insert(loginResponse.getUser());
                        dbManager.close();
                        progressDialog.dismiss();
                        startActivity(new Intent(MainActivity.this,HomeActivity.class));
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this, loginResponse.getRes_desc(), Toast.LENGTH_SHORT).show();
                    }

                } else {
                    progressDialog.dismiss();

                    Toast.makeText(MainActivity.this, "Server response Failed!", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                t.printStackTrace();
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Server response Failed!", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private boolean isAllEmpty() {
        boolean x;
        if (TextUtils.isEmpty(et_username.getText().toString()) && TextUtils.isEmpty(et_username.getText().toString())) {
            x = true;
        } else {
            x = false;
        }
        return x;
    }
}