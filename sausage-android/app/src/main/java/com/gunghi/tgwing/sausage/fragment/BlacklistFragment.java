package com.gunghi.tgwing.sausage.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.gunghi.tgwing.sausage.R;
import com.gunghi.tgwing.sausage.activity.DetailActivity;
import com.gunghi.tgwing.sausage.model.BlackList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joyeongje on 2017. 8. 26..
 */

public class BlacklistFragment extends Fragment{

    private GridView BlacklistGridView;
    private List<BlackList> blackLists;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_blacklist, container, false);

        blackLists = new ArrayList<>();
        blackLists.add(new BlackList("남양유업","",R.drawable.brand_logo_12,""));
        blackLists.add(new BlackList("몽고식품","",R.drawable.brand_logo_02,""));
        blackLists.add(new BlackList("미스터피자","",R.drawable.brand_logo_03,""));
        blackLists.add(new BlackList("생탁","",R.drawable.brand_logo_04,""));
        blackLists.add(new BlackList("삼양식품","",R.drawable.brand_logo_05,""));
        blackLists.add(new BlackList("삼진어묵","",R.drawable.brand_logo_06,""));
        blackLists.add(new BlackList("이랜드","",R.drawable.brand_logo_07,""));
        blackLists.add(new BlackList("천호식품","",R.drawable.brand_logo_08,""));
        blackLists.add(new BlackList("팔도","",R.drawable.brand_logo_09,""));
        blackLists.add(new BlackList("피자헛","",R.drawable.brand_logo_10,""));
        blackLists.add(new BlackList("한국야구르트","",R.drawable.brand_logo_11,""));

        BlacklistGridView = (GridView) rootView.findViewById(R.id.blackListGridView);
        BlacklistGridView.setAdapter(new BlackListGridViewAdpater());

        return rootView;
    }


    public class BlackListGridViewAdpater extends BaseAdapter {
        LayoutInflater inflater;

        public BlackListGridViewAdpater() {
            inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        @Override
        public int getCount() {
            return blackLists.size();
        }

        @Override
        public Object getItem(int position) {
            return blackLists.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.black_list_item, parent, false);
            }

          //  Log.d("핸드모션 리스트",String.valueOf(handMotions.size()));
            final BlackList blackInfo = blackLists.get(position);

//
            ImageView imageView = (ImageView) convertView.findViewById(R.id.blackItemImage);

            TextView textView = (TextView) convertView.findViewById(R.id.blackItemText);
            imageView.setImageResource(blackInfo.getDrawable());
            if(position == 0){
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getActivity().startActivity(new Intent(getActivity(), DetailActivity.class));
                    }
                });
            } else if(position == 2) {
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
            textView.setText(blackInfo.getName());
          //  handImageView.setImageBitmap(handMotionInfo.getHandMotionImage());

          //  handTextView.setText(handMotionInfo.getHandMotionName());

            return convertView;
        }
    }

}
