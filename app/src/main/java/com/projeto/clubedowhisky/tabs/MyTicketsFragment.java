package com.projeto.clubedowhisky.tabs;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.projeto.clubedowhisky.R;
import com.projeto.clubedowhisky.TicketActivity;
import com.projeto.clubedowhisky.classes.Clients;

/**
 * Created by pedrolima on 30/11/2017.
 */

public class MyTicketsFragment extends Fragment {
    FloatingActionButton mFab;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_mytickets_main, container, false);

        Clients c = (Clients) getArguments().getSerializable("client");
        if (c != null){
            Toast toast = Toast.makeText(getContext(), c.getUser().getName(), Toast.LENGTH_LONG);
            toast.show();
        }

        mFab = root.findViewById(R.id.fab);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), TicketActivity.class);
                startActivityForResult(i, 1);
            }
        });

        return root;
    }
}
