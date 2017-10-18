package com.example.emanuellemenali.criminalintent.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emanuellemenali.criminalintent.R;
import com.example.emanuellemenali.criminalintent.model.Crime;
import com.example.emanuellemenali.criminalintent.model.CrimeLab;
import com.example.emanuellemenali.criminalintent.view.activity.CrimePagerActivity;

import java.util.List;

/**
 * Created by emanuellemenali on 13/10/17.
 */

public class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private TextView mTitleTextView;
    private TextView mDateTextView;
    private Crime mCrimeList;
    private Context mContext;

    public CrimeHolder(LayoutInflater inflater, ViewGroup parent, Context context) {
        super(inflater.inflate(R.layout.list_item_crime, parent, false));
        itemView.setOnClickListener(this);

        mTitleTextView = (TextView) itemView.findViewById(R.id.crime_title);
        mDateTextView = (TextView) itemView.findViewById(R.id.crime_date);
        mContext = context;
    }

    public void bind(Crime crime) {
        mCrimeList = crime;
        mTitleTextView.setText(mCrimeList.getmTitle());
        mDateTextView.setText(mCrimeList.getmDate().toString());
    }


    public static final String EXTRA_CRIME_ID =
            "com.example.emanuellemenali.criminalintent.crime_id";

    @Override
    public void onClick(View view) {

        Toast.makeText(mContext, mCrimeList.getmTitle(), Toast.LENGTH_SHORT).show();
        Intent intent = CrimePagerActivity.newIntent(mContext, mCrimeList.getmId());
        mContext.startActivity(intent);

  }

}