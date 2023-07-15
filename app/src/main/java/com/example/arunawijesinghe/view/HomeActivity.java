package com.example.arunawijesinghe.view;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.arunawijesinghe.R;
import com.example.arunawijesinghe.model.User;
import com.example.arunawijesinghe.util.DBManager;

public class HomeActivity extends AppCompatActivity {
    private LinearLayout ll_pro_details;
    private Button btn_view_profile;
    private TextView tv_user_id, tv_user_email, tv_user_name, tv_user_dob, tv_user_gender, tv_user_comp, tv_user_position;
    private User user;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
    }

    //initialize view
    private void initView() {
        ll_pro_details = findViewById(R.id.ll_pro_details);
        tv_user_id = findViewById(R.id.tv_user_id);
        tv_user_email = findViewById(R.id.tv_user_email);
        tv_user_name = findViewById(R.id.tv_user_name);
        tv_user_dob = findViewById(R.id.tv_user_dob);
        tv_user_gender = findViewById(R.id.tv_user_gender);
        tv_user_comp = findViewById(R.id.tv_user_comp);
        tv_user_position = findViewById(R.id.tv_user_position);

        dbManager = new DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();

        User user=new User(cursor.getString(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5),
                cursor.getString(6)
                );
        dbManager.close();

        btn_view_profile = findViewById(R.id.btn_view_profile);
        btn_view_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ll_pro_details.getVisibility() == View.INVISIBLE) {
                    ll_pro_details.setVisibility(View.VISIBLE);
                } else {
                    ll_pro_details.setVisibility(View.INVISIBLE);
                }
            }
        });

        // load user data
        loadUserData(user);
    }


    //load user data from db
    private void loadUserData(User user) {
        tv_user_id.setText(user.getId());
        tv_user_email.setText(user.getEmail());
        tv_user_name.setText(user.getName());
        tv_user_dob.setText(user.getDob());
        tv_user_gender.setText(user.getGender());
        tv_user_comp.setText(user.getCompany());
        tv_user_position.setText(user.getPosition());
    }
}