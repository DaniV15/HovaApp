package com.example.final3;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final3.card.MyCardAdapter;
import com.example.final3.sphandler.SharedPrefHandler;

import java.util.ArrayList;



public class Main<root> extends Fragment {

    String city;
    String Address;
    String type;

    private RecyclerView Card;
    MyCardAdapter mCardAdapter;

    ArrayList<UserAddressProfil> uaps = new ArrayList();


    UserAddressProfil uap = new UserAddressProfil();
    int numberOfCard;


    public void onResume(){
        super.onResume();

        // Set title bar
        ((MainActivity) getActivity())
                .setActionBarTitle("Kezdőlap");

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_main, container, false);

        SharedPrefHandler shh = new SharedPrefHandler(getContext());
        numberOfCard = shh.getNumberOfCard();


        Log.d("TAG", String.valueOf(numberOfCard));


        Card = root.findViewById(R.id.recycleViewCard);
        Card.setLayoutManager( new LinearLayoutManager(root.getContext()));

        mCardAdapter = new MyCardAdapter(root.getContext(), shh.readUsersdata() , getParentFragmentManager().beginTransaction() );
        Card.setAdapter(mCardAdapter);


        Button add_card = (Button) root.findViewById(R.id.Add_Card);

        add_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View r) {

                FragmentTransaction sel = getParentFragmentManager().beginTransaction();
                sel.replace(R.id.nav_host_fragment , new FormFragment(numberOfCard + 1, false));
                sel.commit();

            }
        });

        return root;
    }



}