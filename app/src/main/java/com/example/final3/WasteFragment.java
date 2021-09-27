  package com.example.final3;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class WasteFragment extends Fragment {



    public WasteFragment()
    {

    }




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Log.d("asd", "anyáááááád itt van");
        View root = inflater.inflate(R.layout.fragment_waste, container, false);
        FrameLayout frame = (FrameLayout) root.findViewById(R.id.frameLayout);
        Button close = (Button) root.findViewById(R.id.close_frag);
        TextView what_text = (TextView) root.findViewById(R.id.what_quest);

        SearchView waste_search = (SearchView) root.findViewById(R.id.searchView);
        ListView waste_list = (ListView) root.findViewById(R.id.searchList);




        ArrayAdapter< CharSequence > adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.waste_name, android.R.layout.simple_dropdown_item_1line);
        waste_list.setAdapter(adapter);

        waste_search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                adapter.getFilter().filter(newText);

                return false;
            }
        });

        waste_list.setOnItemClickListener(new ListView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                hideKeyboard(getActivity());
                waste_list.setVisibility(View.GONE);
                waste_search.setVisibility(View.GONE);
                what_text.setText(waste_list.getItemAtPosition(position).toString() );
                waste_list.setVisibility(View.GONE);
                kereso( waste_list.getItemAtPosition(position).toString() , frame, close);

            }
        });



        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                frame.setVisibility(View.GONE);
                close.setVisibility(View.GONE);
                waste_list.setVisibility(View.VISIBLE);
                waste_search.setVisibility(View.VISIBLE);
                waste_search.setQuery("",false);
                what_text.setText("Mit szeretne kidobni?");

            }
        });



        return root;
    }


    void kereso(String s, FrameLayout frame, Button btn)
    {


        hideKeyboard(getActivity());
        String[] selectives = getResources().getStringArray(R.array.selective_name);

        for(int i=0; i<selectives.length; i++)
        {
            if( selectives[i].equals(s) )
            {

                FragmentTransaction sel = getChildFragmentManager().beginTransaction();
                sel.replace(R.id.frameLayout , new SelectiveFragment(frame));
                sel.commit();

                frame.setVisibility(View.VISIBLE);
                btn.setVisibility(View.VISIBLE);
                return;
            }
        }
        String[] communal = getResources().getStringArray(R.array.communal_name);
        for(int i=0; i<communal.length; i++)
        {
            if( communal[i].equals(s) )
            {

                FragmentTransaction sel = getChildFragmentManager().beginTransaction();
                sel.replace(R.id.frameLayout , new CommunalFragment());
                sel.commit();

                frame.setVisibility(View.VISIBLE);
                btn.setVisibility(View.VISIBLE);
                return;
            }
        }
        String[] yard = getResources().getStringArray(R.array.yard_name);
        for(int i=0; i<yard.length; i++)
        {
            if( yard[i].equals(s) )
            {

                FragmentTransaction sel = getChildFragmentManager().beginTransaction();
                sel.replace(R.id.frameLayout , new YardFragment());
                sel.commit();

                frame.setVisibility(View.VISIBLE);
                btn.setVisibility(View.VISIBLE);
                return;
            }
        }

    }


    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }



}