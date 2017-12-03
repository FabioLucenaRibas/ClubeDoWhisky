package com.projeto.clubedowhisky.tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.projeto.clubedowhisky.R;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by pedrolima on 30/11/2017.
 */

public class QrcodeFragment extends Fragment{

    ZXingScannerView scanner;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_qrcode, container, false);
    }
}
