package com.example.final3.card;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final3.FormFragment;
import com.example.final3.R;
import com.example.final3.datacontroller.DataParser;
import com.example.final3.datacontroller.UserData;

import java.util.ArrayList;

public class MyCardAdapter extends RecyclerView.Adapter<MyCardHolder> {

    Context c;
    ArrayList<UserData> cards;
    FragmentTransaction sel;
    int communal, selective, green, glass;

    public MyCardAdapter(Context c, ArrayList<UserData> cards, FragmentTransaction sel ) {
        this.c = c;
        this.cards = cards;
        this.sel = sel;
    }

    @NonNull
    @Override
    public MyCardHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_profil, null);
        return new MyCardHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull  MyCardHolder holder, int position) {


        holder.name.setText(cards.get(position).getName());
        holder.city.setText(cards.get(position).getCity());
        holder.address.setText(cards.get(position).getAddress());

        DataParser dp = new DataParser(cards.get(position).getDataLine() ,cards.get(position).getType());

        communal = dp.getCommunal();
        selective = dp.getSelective();
        glass = dp.getGlass();
        green = dp.getGreen();

// Communal -----------------------------------
        if( communal == -1 ) {
            holder.Communal_Countdown.setVisibility(View.GONE);
            holder.Communal_Icon.setVisibility(View.GONE);
            holder.Communal_Text.setVisibility(View.GONE);
        }
        else
        {
            if(communal == 1){
                holder.Communal_Countdown.setTextColor(Color.MAGENTA);
                holder.Communal_Countdown.setText(String.valueOf( "Holnap!"));

            }
            else if( communal == 0)
            {
                holder.Communal_Countdown.setTextColor(Color.RED);
                holder.Communal_Countdown.setText(String.valueOf( "MA!"));
            }
            else
                holder.Communal_Countdown.setText(String.valueOf( communal + " nap múlva!"));
        }
// Selective -----------------------------------
        if( selective == -1 ) {
            holder.Selective_Text.setVisibility(View.GONE);
            holder.Selective_countdown.setVisibility(View.GONE);
            holder.Selective_Icon.setVisibility(View.GONE);
        }
        else
        {
            if(selective == 1){
                holder.Selective_countdown.setTextColor(Color.MAGENTA);
                holder.Selective_countdown.setText(String.valueOf( "Holnap!"));

            }
            else if( selective == 0)
            {
                holder.Selective_countdown.setTextColor(Color.RED);
                holder.Selective_countdown.setText(String.valueOf( "MA!"));
            }
            else
                holder.Selective_countdown.setText(String.valueOf( selective + " nap múlva!"));
        }

// Green -----------------------------------
        if( green == -1 ) {
            holder.Green_Text.setVisibility(View.GONE);
            holder.Green_Icon.setVisibility(View.GONE);
            holder.Green_Countdown.setVisibility(View.GONE);
        }
        else
        {
            if(green == 1){
                holder.Green_Countdown.setTextColor(Color.MAGENTA);
                holder.Green_Countdown.setText(String.valueOf( "Holnap!"));

            }
            else if( green == 0)
            {
                holder.Green_Countdown.setTextColor(Color.RED);
                holder.Green_Countdown.setText(String.valueOf( "MA!"));
            }
            else
                holder.Green_Countdown.setText(String.valueOf( green + " nap múlva!"));
        }

// GLASS -----------------------------------
        if( glass == -1 ) {
            holder.Glass_Text.setVisibility(View.GONE);
            holder.Glass_Icon.setVisibility(View.GONE);
            holder.Glass_Countdown.setVisibility(View.GONE);
        }
        else
        {
            if(glass == 1){
                holder.Glass_Countdown.setTextColor(Color.MAGENTA);
                holder.Glass_Countdown.setText(String.valueOf( "Holnap!"));

            }
            else if( glass == 0)
            {
                holder.Glass_Countdown.setTextColor(Color.RED);
                holder.Glass_Countdown.setText(String.valueOf( "MA!"));
            }
            else
            holder.Glass_Countdown.setText(String.valueOf( glass + " nap múlva!"));
        }

        holder.Settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sel.replace(R.id.nav_host_fragment , new FormFragment(position, true ));
                sel.commit();

            }
        });


    }

    @Override
    public int getItemCount() {
        return cards.size();
    }
}
