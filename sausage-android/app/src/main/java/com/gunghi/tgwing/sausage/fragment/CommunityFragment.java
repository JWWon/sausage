package com.gunghi.tgwing.sausage.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.gunghi.tgwing.sausage.R;
import com.gunghi.tgwing.sausage.model.Community;

import java.util.ArrayList;

/**
 * Created by joyeongje on 2017. 8. 26..
 */

public class CommunityFragment extends Fragment{

    private ArrayList<Community> communities;
    private CommunityAdapter communityAdapter;

    private ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_community, container, false);
        imageView = (ImageView)rootView.findViewById(R.id.communityWriteButton);
        imageView.bringToFront();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2017. 8. 27. 글쓰기페이지 이동 
            }
        });
        RecyclerView mateRecyclerView = (RecyclerView) rootView.findViewById(R.id.communityRecylerView);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mateRecyclerView.setLayoutManager(mLayoutManager);
        mateRecyclerView.setFocusable(false);

        communities = new ArrayList<>();

        communities.add(new Community("by 홍길동","","호식이 두마리치킨 성추행 불매운동","20대 여직원을 불러서 저녁을 먹자고 하다가 강제로 호텔로 데려가려 했던 사건이지요. 사건발생 초기에는 꽃뱀이냐, 아니면…","",55,8,"조영제","앞으로 절대 이용하고 싶지않네요"));
        communities.add(new Community("by 일산천사","","불매운동 같이해요!","제 메모장에 적어논 불매기업 리스트에요 혹시 빠진거 있음 알려주세요 잊지말고 악덕기업들, 팔도(비빔면).아베크롬비.한국야쿠 … ","",22,17,"조영제","좋은 자세입니다"));
        communities.add(new Community("by 혜진","","이랜드 착취너무하네요 ㅠㅠ","여기도 불매운동 해야 하나봐요. 젤 나쁜게 인건비 떼어 먹는놈들 이죠....에휴","",32,1,"원지","저도 당했어요"));
        communityAdapter = new CommunityAdapter(communities,getContext());
        mateRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mateRecyclerView.setAdapter(communityAdapter);



        return rootView;
    }

    private class CommunityAdapter extends RecyclerView.Adapter<CommunityAdapter.ViewHolder>  {

        private ArrayList<Community> _communities;
        private Context context;

        public CommunityAdapter(ArrayList<Community> communities, Context context) {
            this._communities = communities;
            this.context = context;

        }

        @Override
        public CommunityAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_community, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(final CommunityAdapter.ViewHolder holder, int position) {
            Community item = communities.get(position);
            if(position == 0) {
                holder.profileImageView.setImageResource(R.drawable.profile_default);
            } else if(position == 1) {
                holder.profileImageView.setImageResource(R.drawable.profile_chunsa);
            } else {
                holder.profileImageView.setImageResource(R.drawable.profile);
            }


                     holder.communityTitleView.setText(item.getTitle());
                     holder.communityDescriptionTextView.setText(item.getDescription());
                     holder.communityReviewIdView.setText(item.getReviewUserName());
                     holder.communityReviewDescriptionTextView.setText(item.getReviewDescripton());
            holder.upTextView.setText(item.getLikeNum() + "");
            holder.downTextview.setText(item.getHateNum() + "");

                holder.upImageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        holder.upTextView.setText((Integer.parseInt(holder.upTextView.getText().toString()) + 1) + "");


                    }
                });

                holder.downImageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        holder.downTextview.setText((Integer.parseInt(holder.downTextview.getText().toString()) + 1) + "");
                    }
                });


        }

        @Override
        public int getItemCount() {
            return _communities.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            public ImageView profileImageView;
            public TextView communityTitleView;
            public TextView communityDescriptionTextView;
            public TextView communityReviewIdView;
            public TextView communityReviewDescriptionTextView;
            public ImageButton upImageButton;
            public TextView upTextView;
            public ImageButton downImageButton;
            public TextView downTextview;
            //  public CircleImageView mateProfile;
            //  public TextView mateName;
            //  public TextView mateDoorOpenTime;
            //  public ImageView mateOutingFlag;

            public ViewHolder(View itemView) {
                super(itemView);

                profileImageView = (ImageView) itemView.findViewById(R.id.communityImageView);
                communityTitleView = (TextView) itemView.findViewById(R.id.communityTitle);
                communityDescriptionTextView = (TextView) itemView.findViewById(R.id.communityDescription);
                communityReviewIdView = (TextView) itemView.findViewById(R.id.communityReviewId);
                communityReviewDescriptionTextView = (TextView) itemView.findViewById(R.id.communityReviewDescription);
                upImageButton = (ImageButton) itemView.findViewById(R.id.upButton);
                downImageButton = (ImageButton) itemView.findViewById(R.id.downButton);
                upTextView = (TextView)itemView.findViewById(R.id.upTextview);
                downTextview = (TextView)itemView.findViewById(R.id.downTextview);



                //   mateProfile = (CircleImageView) itemView.findViewById(R.id.mateProfileImageView);
                //   mateName = (TextView) itemView.findViewById(R.id.mateNameTextView);
                //   mateDoorOpenTime = (TextView) itemView.findViewById(R.id.mateOutingTimeTextView);
                //   mateOutingFlag = (ImageView) itemView.findViewById(R.id.mateOutingFlag);
            }
        }
    }





}
