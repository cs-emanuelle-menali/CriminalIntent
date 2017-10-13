package com.example.emanuellemenali.criminalintent.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emanuellemenali.criminalintent.R;
import com.example.emanuellemenali.criminalintent.model.Crime;
import com.example.emanuellemenali.criminalintent.model.CrimeLab;
import com.example.emanuellemenali.criminalintent.view.activity.CrimePagerActivity;
import com.example.emanuellemenali.criminalintent.view.adapter.CrimeAdapter;

import java.util.List;

/**
 * Created by emanuellemenali on 03/10/17.
 */

public class CrimeListFragment extends Fragment {

    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mCrimeAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_crime_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_crime:
                Crime crime = new Crime();
                CrimeLab.get(getActivity()).addCrime(crime);
                Intent intent = CrimePagerActivity
                        .newIntent(getActivity(), crime.getmId());
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateUi() {
        CrimeLab crimeLab = new CrimeLab(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();

        if (mCrimeAdapter == null) {

            mCrimeAdapter = new CrimeAdapter(crimes, getActivity());
            mCrimeRecyclerView.setAdapter(mCrimeAdapter);
        } else {
            mCrimeAdapter.notifyDataSetChanged();
        }
    }

//    public class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {
//
//        private Context mContext;
//
//        private List<Crime> mCrimes;
//
//        private CrimeAdapter(List<Crime> crimes) {
//
//            mCrimes = crimes;
//        }
//
//        @Override
//        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
//            return new CrimeHolder(layoutInflater, parent);
//        }
//
//        @Override
//        public void onBindViewHolder(CrimeHolder holder, int position) {
//
//            Crime crime = mCrimes.get(position);
//            holder.bind(crime);
//        }
//
//        @Override
//        public int getItemCount() {
//            return mCrimes.size();
//        }
//
//    }
//
//    public class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//
//        private TextView mTitleTextView;
//        private TextView mDateTextView;
//        private Crime mCrime;
//
//        public CrimeHolder(LayoutInflater inflater, ViewGroup parent) {
//            super(inflater.inflate(R.layout.list_item_crime, parent, false));
//            itemView.setOnClickListener(this);
//
//            mTitleTextView = (TextView) itemView.findViewById(R.id.crime_title);
//            mDateTextView = (TextView) itemView.findViewById(R.id.crime_date);
//        }
//
//        public void bind(Crime crime) {
//            mCrime = crime;
//            mTitleTextView.setText(mCrime.getmTitle());
//            mDateTextView.setText(mCrime.getmDate().toString());
//        }
//
//        @Override
//        public void onClick(View view) {
//
//            Toast.makeText(getActivity(), mCrime.getmTitle(), Toast.LENGTH_SHORT).show();
////        Intent intent = new Intent(mContext, CrimeActivity.class);
////        Intent intent = CrimeActivity.newIntent(mContext, mCrime.getmId());
//            Intent intent = CrimePagerActivity.newIntent(getActivity(), mCrime.getmId());
//            startActivity(intent);
//
//        }
//    }
}
