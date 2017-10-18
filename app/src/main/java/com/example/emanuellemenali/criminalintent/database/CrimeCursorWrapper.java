package com.example.emanuellemenali.criminalintent.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.emanuellemenali.criminalintent.database.CrimeDbSchema.CrimeTable;
import com.example.emanuellemenali.criminalintent.model.Crime;

import java.util.Date;
import java.util.UUID;

/**
 * Created by emanuellemenali on 16/10/17.
 */

public class CrimeCursorWrapper extends CursorWrapper {

    public CrimeCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Crime getCrime() {
        String uuidString = getString(getColumnIndex(CrimeTable.Cols.UUID));
        String title = getString(getColumnIndex(CrimeTable.Cols.TITLE));
        long date = getLong(getColumnIndex(CrimeTable.Cols.DATE));
        int isSolved = getInt(getColumnIndex(CrimeTable.Cols.SOLVED));
        String suspect = getString(getColumnIndex(CrimeTable.Cols.SUSPECT));

        Crime crime = new Crime(UUID.fromString(uuidString));
        crime.setmTitle(title);
        crime.setmDate(new Date(date));
        crime.setmSolved(isSolved != 0);
        crime.setmSuspect(suspect);

        return crime;
    }
}
