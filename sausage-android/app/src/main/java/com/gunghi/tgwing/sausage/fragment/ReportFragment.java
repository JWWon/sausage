package com.gunghi.tgwing.sausage.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.gunghi.tgwing.sausage.R;

/**
 * Created by joyeongje on 2017. 8. 26..
 */

public class ReportFragment extends Fragment {

    private String firstImageURL = "http://foodsafetykorea.go.kr/hazard/foodInjryInfo/searchFoodInjryInfoDetail.do?start_idx=1&show_cnt=5&lifecyc_meta_cd=ALL&kwrd_chrctr=&injry_info_nm=&food_injry_info_seq=694&type=A&search_keyword=";
    private String secondImageURL = "http://hei.hankyung.com/hub01/201708259911I";
    private String thirdImageURL = "http://www.segye.com/newsView/20170823001133";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_report, container, false);

        ImageView imageView = (ImageView) rootView.findViewById(R.id.firstImage);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(firstImageURL);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        ImageView imageView2 = (ImageView) rootView.findViewById(R.id.secondImage);
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(thirdImageURL);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        ImageView imageView3 = (ImageView) rootView.findViewById(R.id.thirdImage);
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(secondImageURL);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        return rootView;
    }





}
