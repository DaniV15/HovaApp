package com.example.final3.datacontroller;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.example.final3.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class Reader {

    private String city;
    private Context context;

    public Reader(Context c)
    {
        this.context = c;
    }

    private InputStream setInputStream(String city)
    {
        InputStream is = null;
        switch(city)
        {
            case "Kazincbarcika":
               // is = context.getResources().openRawResource(R.raw.kazincbarcika);
                break;
            case "Miskolc":
                is = context.getResources().openRawResource(R.raw.miskolc);
                break;
            default:
                break;
        }

        return is;
    }


    public ArrayAdapter< CharSequence > getStreetsList() throws IOException {

        ArrayList<CharSequence> streets = new ArrayList<>();
        InputStream is = setInputStream(city);
        if(is == null) {

            streets.add("Kérjük add meg a városod!");
            return new ArrayAdapter<>(context, android.R.layout.simple_dropdown_item_1line, streets);

        }
        BufferedReader reader =new BufferedReader( new InputStreamReader(is, Charset.forName("UTF-8") ));
        String line;

        while(  (line = reader.readLine() ) != null  )
        {
            String[] tokens = line.split(",");
            streets.add(tokens[0]);
        }


        return new ArrayAdapter<>(context, android.R.layout.simple_dropdown_item_1line, streets);
    }
    public String getDataLine(String address) throws IOException {
        InputStream is = setInputStream(city);
        BufferedReader reader =new BufferedReader( new InputStreamReader(is, Charset.forName("UTF-8") ));
        String line;

        while(  (line = reader.readLine() ) != null  )
        {
            if(line.equals(address))
            {
                return line;
            }
        }
        return "";
    }
    public String getDataLine(String lcity, String address) throws IOException {
        InputStream is = setInputStream(lcity);
        BufferedReader reader =new BufferedReader( new InputStreamReader(is, Charset.forName("UTF-8") ));
        String line="";
        Log.d("TAG22222222222222222", line);

        while(  (line = reader.readLine() ) != null  )
        {
            String[] token = line.split(",");
            if( token[0].equals(address))
            {
                return line;
            }
        }
        return "";
    }

    public void setCity(String city) {
        this.city = city;
    }
}
