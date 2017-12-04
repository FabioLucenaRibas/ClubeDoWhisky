package com.projeto.clubedowhisky.httpDrink;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.projeto.clubedowhisky.classes.Drinks;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Renato on 03/12/2017.
 */

public class AllDrinksTask extends AsyncTaskLoader<ArrayList<Drinks>> {

    ArrayList<Drinks> mDrinks;
    int mLimit;

    public AllDrinksTask(Context context, int limit) {
        super(context);
        mLimit = limit;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        if (mDrinks == null){
            Log.d("NGVL","forceLoad");
            forceLoad();
        } else {
            deliverResult(mDrinks);
        }
    }

    @Override
    public ArrayList<Drinks> loadInBackground() {
        try {
            mDrinks = DrinkParser.allDrinks(mLimit);
        }catch (IOException e){
            e.printStackTrace();
        }
        return mDrinks;
    }
}
