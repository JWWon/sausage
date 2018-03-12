package com.gunghi.tgwing.sausage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.messaging.FirebaseMessaging;
import com.gunghi.tgwing.sausage.activity.SplashActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseMessaging.getInstance().subscribeToTopic("news");

        startActivity(new Intent(this, SplashActivity.class));
        MainActivity.this.finish();

    }

}
