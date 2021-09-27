package com.example.final3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.final3.report.ReportMaker;

public class Report extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.report_main, container, false);


        ImageView illegalimage = (ImageView) root.findViewById(R.id.illegalReport);

        illegalimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction sel = getParentFragmentManager().beginTransaction();
                sel.replace(R.id.nav_host_fragment , new ReportMaker());
                sel.commit();
            }
        });

        return root;
    }



}