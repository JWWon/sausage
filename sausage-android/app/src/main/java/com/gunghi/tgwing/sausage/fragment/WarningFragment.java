package com.gunghi.tgwing.sausage.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.gunghi.tgwing.sausage.R;
import com.gunghi.tgwing.sausage.model.Recall;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by joyeongje on 2017. 8. 27..
 */

public class WarningFragment extends Fragment {

    ArrayList<Recall> newRecalls;
    ArrayList<Recall> oldRecalls;
    WarningGridViewAdpater  warningGridViewAdpater;
    WarningGridViewAdpater2 warningGridViewAdpater2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_warning, container, false);
        GridView newGridView = (GridView) rootView.findViewById(R.id.newGridView);
        GridView oldGridView = (GridView) rootView.findViewById(R.id.oldGridView);
        Log.d("ddd","ddddd");

        warningGridViewAdpater  = new WarningGridViewAdpater();
        warningGridViewAdpater2 = new WarningGridViewAdpater2();

        newRecalls = new ArrayList<>();
        newGridView.setAdapter(warningGridViewAdpater);

        oldRecalls = new ArrayList<>();
        oldGridView.setAdapter(warningGridViewAdpater2);

        loadRecall();


        return rootView;
    }

    public void loadRecall() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference().child("recalls");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("dataSnapshot", dataSnapshot.getChildrenCount() + "");
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Recall recall = dataSnapshot1.getValue(Recall.class);
                    if(newRecalls.size() <= 3) {
                        newRecalls.add(recall);
                    } else {
                        oldRecalls.add(recall);
                    }

                }

                warningGridViewAdpater.notifyDataSetChanged();
                warningGridViewAdpater2.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public class WarningGridViewAdpater extends BaseAdapter {
        LayoutInflater inflater;

        public WarningGridViewAdpater() {
            inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        @Override
        public int getCount() {
            return newRecalls.size();
        }

        @Override
        public Object getItem(int position) {
            return newRecalls.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.card_recall, parent, false);
            }

            final Recall recall = newRecalls.get(position);

            ImageView imageView = (ImageView) convertView.findViewById(R.id.recallImageView);
            TextView recallNameTextView = (TextView) convertView.findViewById(R.id.recallNameTextView);
            TextView recallReasonTextView  = (TextView) convertView.findViewById(R.id.recallReasonTextView);
            TextView recallDateTextView = (TextView) convertView.findViewById(R.id.recallDateTextView);

            Picasso.with(parent.getContext()).load(recall.getImageUrl()).into(imageView);
            recallNameTextView.setText(recall.getName());
            recallReasonTextView.setText(recall.getReason());
            recallDateTextView.setText(recall.getDate());

            return convertView;
        }
    }


    public class WarningGridViewAdpater2 extends BaseAdapter {

        LayoutInflater inflater;

        public WarningGridViewAdpater2() {
            inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        @Override
        public int getCount() {
            return oldRecalls.size();
        }

        @Override
        public Object getItem(int position) {
            return oldRecalls.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.card_recall, parent, false);
            }

            final Recall recall = oldRecalls.get(position);

            ImageView imageView = (ImageView) convertView.findViewById(R.id.recallImageView);
            TextView recallNameTextView = (TextView) convertView.findViewById(R.id.recallNameTextView);
            TextView recallReasonTextView  = (TextView) convertView.findViewById(R.id.recallReasonTextView);
            TextView recallDateTextView = (TextView) convertView.findViewById(R.id.recallDateTextView);

            Picasso.with(parent.getContext()).load(recall.getImageUrl()).into(imageView);
            recallNameTextView.setText(recall.getName());
            recallReasonTextView.setText(recall.getReason());
            recallDateTextView.setText(recall.getDate());

            return convertView;
        }

    }
}
