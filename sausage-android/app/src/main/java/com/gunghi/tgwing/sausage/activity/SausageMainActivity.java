package com.gunghi.tgwing.sausage.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.gunghi.tgwing.sausage.R;
import com.gunghi.tgwing.sausage.fragment.BlacklistFragment;
import com.gunghi.tgwing.sausage.fragment.CommunityFragment;
import com.gunghi.tgwing.sausage.fragment.ReportFragment;
import com.gunghi.tgwing.sausage.fragment.WarningFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SausageMainActivity extends AppCompatActivity {

    private BottomBar mBottomBar;

    ReportFragment reportFragment;
    BlacklistFragment blacklistFragment;
    CommunityFragment communityFragment;
    WarningFragment warningFragment;

    Fragment currentFragment;

    //FragmentDoorOnOff fragmentDoorOnOff;
    //FragmentMate      fragmentMate     ;
    //FragmentInfo fragmentInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sausage_main);


        ImageButton searchButton = (ImageButton)findViewById(R.id.searchButton);
        ImageButton menuButton = (ImageButton)findViewById(R.id.menuButton);
        ImageView logoButton = (ImageView) findViewById(R.id.logoButton);

        searchButton.bringToFront();
        menuButton.bringToFront();
        logoButton.bringToFront();

        reportFragment = new ReportFragment();
        blacklistFragment = new BlacklistFragment();
        communityFragment = new CommunityFragment();
        warningFragment = new WarningFragment();

        currentFragment = new Fragment();
        currentFragment = reportFragment;

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,currentFragment).commit();
        initView(savedInstanceState);

        String urlLink = getIntent().getStringExtra("url");
        if(urlLink != null) {
            Uri uri = Uri.parse(getIntent().getStringExtra("url"));
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        } else {

        }

    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private void initView(Bundle savedInstanceState) {

        mBottomBar = BottomBar.attach(this, savedInstanceState);
        mBottomBar.useFixedMode();
        mBottomBar.setItems(R.menu.bottombar_menus);
        mBottomBar.setActiveTabColor("#0b94d2");

        mBottomBar.setOnMenuTabClickListener(new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {

                switch (menuItemId) {
                    case R.id.menu_report:
                        currentFragment = reportFragment;
                        break;
                    case R.id.menu_community:
                        currentFragment = communityFragment;
                        break;
                    case R.id.menu_blacklist:
                        currentFragment = blacklistFragment;
                        break;
                    case R.id.menu_product:
                        currentFragment = warningFragment;
                        break;

                }
                  getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,currentFragment).commit();
            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {

            }
        });
    }
}
