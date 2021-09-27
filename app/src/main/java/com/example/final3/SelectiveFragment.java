package com.example.final3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SelectiveFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectiveFragment extends Fragment {

   private FrameLayout frag;

public SelectiveFragment(FrameLayout fr)
{
frag=frag;
}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_selective, container, false);
        final int random = new Random().nextInt(20) ;
        TextView motivation_text = (TextView) view.findViewById(R.id.selectivemultilineid);
        String[] motiv = getResources().getStringArray(R.array.motivation);


        motivation_text.setText(motiv[random]);

        return view;
    }
}