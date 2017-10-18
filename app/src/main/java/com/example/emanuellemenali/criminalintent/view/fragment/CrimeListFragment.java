package com.example.emanuellemenali.criminalintent.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
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
import com.example.emanuellemenali.criminalintent.view.adapter.ClickListener;
import com.example.emanuellemenali.criminalintent.view.adapter.CrimeAdapter;
import com.example.emanuellemenali.criminalintent.view.adapter.RecyclerTouchListener;

import java.util.List;

/**
 * Created by emanuellemenali on 03/10/17.
 */

public class CrimeListFragment extends Fragment {

    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mCrimeAdapter;
    private boolean mSubtitleVisible;
    private static final String SAVED_SUBTITLE_VISIBLE = "subtitle";

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

        if (savedInstanceState != null) {
            mSubtitleVisible = savedInstanceState.getBoolean(SAVED_SUBTITLE_VISIBLE);
        }

        updateUi();

        mCrimeRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(),
                mCrimeRecyclerView, new ClickListener() {

            @Override
            public void onLongClick(View view, int position) {
            }
        }));
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUi();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(SAVED_SUBTITLE_VISIBLE, mSubtitleVisible);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_crime_list, menu);

        MenuItem subtitleItem = menu.findItem(R.id.show_subtitle);
        if (mSubtitleVisible) {
            subtitleItem.setTitle(R.string.hide_subtitle);
        } else {
            subtitleItem.setTitle(R.string.show_subtitle);
        }
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

            case R.id.show_subtitle:
                mSubtitleVisible = !mSubtitleVisible;
                getActivity().invalidateOptionsMenu();
                updateDescription();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateDescription(){

        CrimeLab crimeLab = CrimeLab.get(getActivity());
        int crimeCount = crimeLab.getCrimes().size();
        String subtitle = getString(R.string.subtitle_format, crimeCount);

        if (!mSubtitleVisible) {
            subtitle = null;
        }

        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        appCompatActivity.getSupportActionBar().setSubtitle(subtitle);

    }

    private void updateUi() {
        CrimeLab crimeLab = new CrimeLab(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();

        if (mCrimeAdapter == null) {

            mCrimeAdapter = new CrimeAdapter(crimes, getActivity());
            mCrimeRecyclerView.setAdapter(mCrimeAdapter);
        } else {
            mCrimeAdapter.setCrimes(crimes);
            mCrimeAdapter.notifyDataSetChanged();
        }

        updateDescription();
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
