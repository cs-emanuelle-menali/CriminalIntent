package com.example.emanuellemenali.criminalintent.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.emanuellemenali.criminalintent.model.Crime;
import com.example.emanuellemenali.criminalintent.view.fragment.CrimeListFragment;

import java.util.List;

/**
 * Created by emanuellemenali on 03/10/17.
 */

public class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {

    private Context mContext;


    private List<Crime> mCrimes;

    public CrimeAdapter(List<Crime> crimes, Context context) {
        mContext = context;
        mCrimes = crimes;
    }

    @Override
    public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        return new CrimeHolder(layoutInflater, parent, mContext.getApplicationContext());
    }
    @Override
    public void onBindViewHolder(CrimeHolder holder, int position) {

        Crime crime = mCrimes.get(position);
        holder.bind(crime);
    }
    @Override
    public int getItemCount() {
        return mCrimes.size();
    }



}