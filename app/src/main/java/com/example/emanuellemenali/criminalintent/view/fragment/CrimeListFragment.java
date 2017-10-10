package com.example.emanuellemenali.criminalintent.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.emanuellemenali.criminalintent.R;
import com.example.emanuellemenali.criminalintent.model.Crime;
import com.example.emanuellemenali.criminalintent.model.CrimeLab;
import com.example.emanuellemenali.criminalintent.view.activity.CrimeActivity;
import com.example.emanuellemenali.criminalintent.view.adapter.CrimeAdapter;

import java.util.List;

/**
 * Created by emanuellemenali on 03/10/17.
 */

public class CrimeListFragment extends Fragment {

    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mCrimeAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_crime_list, container, false);

        mCrimeRecyclerView = rootView.findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUi();
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUi();
    }

    private void updateUi() {
        CrimeLab crimeLab = new CrimeLab(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();

        if(mCrimeAdapter == null) {

            mCrimeAdapter = new CrimeAdapter(crimes, getActivity());
            mCrimeRecyclerView.setAdapter(mCrimeAdapter);
        } else {
            mCrimeAdapter.notifyDataSetChanged();
        }
    }


//    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
//
//        private TextView mTitleTextView;
//        private TextView mDateTextView;
//        private Crime mCrime;
//        private Context mContext;
//
//    public CrimeHolder(LayoutInflater inflater, ViewGroup parent, Context context) {
//            super(inflater.inflate(R.layout.list_item_crime, parent, false));
//            itemView.setOnClickListener(this);
//
//            mTitleTextView = (TextView) itemView.findViewById(R.id.crime_title);
//            mDateTextView = (TextView) itemView.findViewById(R.id.crime_date);
//        }
//
//    public void bind(Crime crime) {
//        mCrime = crime;
//        mTitleTextView.setText(mCrime.getmTitle());
//        mDateTextView.setText(mCrime.getmDate().toString());
//    }
//
//    @Override
//    public void onClick(View view) {
//        Intent intent = new Intent(getActivity(), CrimeActivity.class);
//        startActivity(intent);
//    }

}
