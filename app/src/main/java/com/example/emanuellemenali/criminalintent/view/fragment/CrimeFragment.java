package com.example.emanuellemenali.criminalintent.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;

import com.example.emanuellemenali.criminalintent.R;
import com.example.emanuellemenali.criminalintent.model.Crime;
import com.example.emanuellemenali.criminalintent.model.CrimeLab;
import com.example.emanuellemenali.criminalintent.view.activity.CrimeActivity;

import java.util.UUID;

/**
 * Created by emanuellemenali on 02/10/17.
 */

public class CrimeFragment extends Fragment {

    private Crime mCrime;

    private TextView mTitleCrimeTextView;
    private TextView mDetailCrimeTextView;
    private EditText mTitleField;
    private Button mCrimeDateButton;
    private CheckBox mSolvedCheckBox;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCrime = new Crime();


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.crime_fragment, container, false);

        mTitleCrimeTextView = view.findViewById(R.id.crime_title_text_view);
        mDetailCrimeTextView = view.findViewById(R.id.crime_details);
        mTitleField = view.findViewById(R.id.edit_crime_title);
        mCrimeDateButton = view.findViewById(R.id.crime_date);

        mSolvedCheckBox = view.findViewById(R.id.crime_solved);
        mTitleField.addTextChangedListener(new TextWatcher() {
                                               @Override
                                               public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                                               }

                                               @Override
                                               public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                                                   mCrime.setmTitle(s.toString());
                                               }

                                               @Override
                                               public void afterTextChanged(Editable editable) {

                                               }
                                           }

        );

        mCrimeDateButton.setText(mCrime.getmDate().toString());
        mCrimeDateButton.setEnabled(false);

        mSolvedCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                mCrime.setmSolved(isChecked);

            }
        });

        return view;
    }
}
