package com.example.final3;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.final3.datacontroller.Reader;
import com.example.final3.datacontroller.UserData;
import com.example.final3.sphandler.SharedPrefHandler;

import java.io.IOException;



public class FormFragment extends Fragment {

    int StreetXML_Id;
    UserData data;
    SharedPreferences sp, sp2;
    boolean editing;

    int id=0;

    public FormFragment(int id, boolean editing)
    {
        this.id = id;
        this.editing = editing;
    }

    public void onResume(){
        super.onResume();

        // Set title bar
        ((MainActivity) getActivity())
                .setActionBarTitle("Cím hozzáadása");

    }



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        Reader reader = new Reader(getContext());

        View root = inflater.inflate(R.layout.fragment_form, container, false);
        AutoCompleteTextView city_list =  root.findViewById(R.id.CitiesList);
        AutoCompleteTextView street_list = root.findViewById(R.id.StreetsList);
        EditText Name = root.findViewById(R.id.Name);

        View root2 = inflater.inflate(R.layout.action_bar_layout, container, false);
        TextView t = root2.findViewById(R.id.actionBar_Text);
        t.setVisibility(View.INVISIBLE);

        Button deleteButton = root.findViewById(R.id.delete_button);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPrefHandler sph = new SharedPrefHandler(getContext());
                Log.d("asd ", String.valueOf(id));
                sph.deleteUserData(id);
                backHome();
            }
        });

        if(editing )
        {
            SharedPrefHandler sph = new SharedPrefHandler(getContext());
            Name.setText(sph.getName(id));
            city_list.setText(sph.getCity(id));
            street_list.setText(sph.getDataLine(id).split(",")[0]);

            deleteButton.setVisibility(View.VISIBLE);

        }


        street_list.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View r) {
                setAdapter(city_list.getText().toString(), street_list);
            }
        });

        street_list.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    setAdapter(city_list.getText().toString(), street_list);
                }
                else v.clearFocus();
            }
        });

        street_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                hideKeyboard(getActivity());
            }
        });

        Spinner spinner = (Spinner) root.findViewById(R.id.type);
        Button save = (Button) root.findViewById(R.id.save_button);
        Button cancel = (Button) root.findViewById(R.id.cancel_button);
        EditText nameEt = (EditText)  root.findViewById(R.id.Name);



        save.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View r) {

                data = new UserData();

                data.setCity( city_list.getText().toString() );
                try {
                    data.setDataLine( reader.getDataLine( city_list.getText().toString(), street_list.getText().toString() ) );
                    Log.d("asd", data.getDataLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                data.setType(spinner.getSelectedItem().toString());
                data.setName(nameEt.getText().toString());

                SharedPrefHandler sph = new SharedPrefHandler(getContext());

                if(editing)
                    sph.modifyExistingData( id, data.getName(),data.getCity(),data.getDataLine(),data.getType());
                else
                    sph.addNewData(data.getName(),data.getCity(),data.getDataLine(),data.getType());

                backHome();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                backHome();
            }
        });

        ArrayAdapter < CharSequence > adapter = ArrayAdapter.createFromResource(getContext(),
                        R.array.cities_name, android.R.layout.simple_dropdown_item_1line);
        city_list.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getContext(),
                R.array.house_type, android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter2);

        return root;
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

    private void setAdapter(String city, AutoCompleteTextView street_list )
    {
        Reader reader = new Reader(getContext());
        reader.setCity(city);
        try {
            street_list.setAdapter(reader.getStreetsList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void backHome()
    {
        FragmentTransaction sel = getParentFragmentManager().beginTransaction();
        sel.replace(R.id.nav_host_fragment , new Main());
        sel.commit();
    }
}