package com.projeto.clubedowhisky.tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.zxing.Result;
import com.projeto.clubedowhisky.R;

import org.xmlpull.v1.XmlPullParser;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by pedrolima on 30/11/2017.
 */

public class QrcodeFragment extends Fragment implements ZXingScannerView.ResultHandler{

    ZXingScannerView mScannerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mScannerView = new ZXingScannerView(getActivity());
        return mScannerView;
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void handleResult(Result result) {

    }
}
