package com.gunghi.tgwing.sausage.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.gunghi.tgwing.sausage.R;

public class DetailActivity extends AppCompatActivity {

    ImageView topImageView;
    ImageView bottomImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        bottomImageView = (ImageView)findViewById(R.id.nayangBottomImageView);
    }

    public void onReportListButton(View view) {
        bottomImageView.setImageResource(R.drawable.contents_02);
    }

    public void onCommunityListButton(View view) {
        bottomImageView.setImageResource(R.drawable.contents_03);
    }
}
