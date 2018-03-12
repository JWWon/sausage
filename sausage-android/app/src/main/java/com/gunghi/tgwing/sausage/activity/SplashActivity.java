package com.gunghi.tgwing.sausage.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.gunghi.tgwing.sausage.R;
import com.gunghi.tgwing.sausage.model.User;

public class SplashActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("users");


        // 저장된 데이터 불러온다.!
        SharedPreferences sharedPreferences = getSharedPreferences("SausageLocalData", MODE_PRIVATE);
        final String deviceId = sharedPreferences.getString("deviceId", "");
        Log.d("deviceId",deviceId);

        if(deviceId.length() > 0) {
            getUserDataFromDB(deviceId);

        } else {
            // 없음. 회원가입으로 보냄
            Log.d("dd","ddd");
            startActivity(new Intent(this,GoogleSignInActivity.class));
            finish();
        }
        
    }

    private void getUserDataFromDB(final String deviceId) {
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            mDatabase.child(deviceId).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    User.setInstance(dataSnapshot.getValue(User.class));
                    Intent sausageMainIntent = new Intent(SplashActivity.this,SausageMainActivity.class);
                    final String url = getIntent().getStringExtra("url");
                    if(url != null) {
                        sausageMainIntent.putExtra("url",url);
                    }
                    startActivity(sausageMainIntent);
                    finish();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    });

    }



}
