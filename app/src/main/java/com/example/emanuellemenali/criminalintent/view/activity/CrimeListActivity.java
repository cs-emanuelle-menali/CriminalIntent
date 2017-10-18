package com.example.emanuellemenali.criminalintent.view.activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.example.emanuellemenali.criminalintent.view.fragment.CrimeListFragment;

/**
 * Created by emanuellemenali on 03/10/17.
 */

public class CrimeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
