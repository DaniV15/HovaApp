package com.example.final3.card;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final3.R;

public class MyCardHolder extends RecyclerView.ViewHolder {

    TextView name, city, address;
    TextView Glass_Countdown, Green_Countdown, Communal_Countdown, Selective_countdown, Glass_Text, Green_Text, Communal_Text, Selective_Text;
    ImageView Communal_Icon, Selective_Icon, Glass_Icon, Green_Icon;
    ImageButton Settings;


    public MyCardHolder(@NonNull View itemView) {
        super(itemView);

        this.name = itemView.findViewById(R.id.Locality_Name);
        this.city = itemView.findViewById(R.id.User_City);
        this.address = itemView.findViewById(R.id.User_Address);
        this.Communal_Countdown = itemView.findViewById(R.id.Communal_Countdown);
        this.Selective_countdown = itemView.findViewById(R.id.Selective_CountDown);

        this.Glass_Countdown = itemView.findViewById(R.id.Glass_CountDown);
        this.Green_Countdown = itemView.findViewById(R.id.Green_CountDown);

        this.Communal_Icon = itemView.findViewById(R.id.Communal_Icon);
        this.Selective_Icon = itemView.findViewById(R.id.Selective_Icon);
        this.Glass_Icon = itemView.findViewById(R.id.Glass_Icon);
        this.Green_Icon = itemView.findViewById(R.id.Green_Icon);

        this.Glass_Text = itemView.findViewById(R.id.Glass_Text);
        this.Green_Text = itemView.findViewById(R.id.Green_Text);
        this.Selective_Text = itemView.findViewById(R.id.Selective_Text);
        this.Communal_Text = itemView.findViewById(R.id.Communal_Text);

        this.Settings = itemView.findViewById(R.id.Settings);

    }
}
