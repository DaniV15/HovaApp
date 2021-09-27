package com.example.final3.datacontroller;


import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.WeekFields;
import java.util.ArrayList;

public class DataParser<splitedDataLine> {

    String dataLine;
    Boolean type;

    int communalPeriod, selectivePeriod, glassPeriod, greenPeriod;
    String communal, selective, glass, green;

    String[] splitedDataLine;

    DataLine dl;

    @SuppressWarnings("ConstantConditions")
    public DataParser(String dataLine, String type) {
        this.dataLine = dataLine;
        splitedDataLine= dataLine.split(",");
        for(int i=0; i<splitedDataLine.length; i++)
            if(splitedDataLine[i].equals("-")) splitedDataLine[i]="-1";

       // Log.d("TAG", String.valueOf(splitedDataLine.length));
        //Log.d(type, "DataParser: ");

        if(type.equals("Társasház")) {
            communalPeriod = Integer.parseInt(splitedDataLine[1]);
            selectivePeriod = Integer.parseInt(splitedDataLine[3]);
            glassPeriod = -1;
            greenPeriod = -1;

            communal = splitedDataLine[2];
            selective = splitedDataLine[4];
        }
        else
        {
            Log.d("TAG", "EZ az Acél utca csak: ");
            communalPeriod = Integer.parseInt(splitedDataLine[5]);
            selectivePeriod = Integer.parseInt(splitedDataLine[7]);
            glassPeriod =Integer.parseInt(splitedDataLine[9]) ;
            greenPeriod = Integer.parseInt(splitedDataLine[11]);

            communal = splitedDataLine[6];
            selective = splitedDataLine[8];
            glass = splitedDataLine[10];
            green = splitedDataLine[12];
        }


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public int getCommunal(){
        Log.d(communal, "getCommunal: ");

        switch (communalPeriod) {
            case -1:
                return -1;
            case 0:
               return calcEveryWeek(communal);
            case 1:
            case 2:
                return calcEvenOddWeek(communalPeriod, communal);
            case 3:
                break;
        }
        return -1;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public int getSelective(){
        switch (selectivePeriod) {
            case -1:
                return -1;
            case 0:
                return calcEveryWeek(selective);
            case 1:
            case 2:
            return calcEvenOddWeek(selectivePeriod, selective);
            case 3:
                break;
        }
        return -1;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public int getGlass(){
        switch (glassPeriod) {
            case -1:
                return -1;
            case 0:
                return calcEveryWeek(glass);
            case 1:
            case 2:
                return calcEvenOddWeek(glassPeriod, glass);
            case 3:
                return calcUnique( glass);
        }
        return -1;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public int getGreen(){
        switch (greenPeriod) {
            case -1:
                return -1;
            case 0:
                return calcEveryWeek(green);
            case 1:
            case 2:
                return calcEvenOddWeek(greenPeriod, green);
            case 3:
                break;
        }
        return -1;
    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    int calcEveryWeek(String days)
    {
        Log.d("TAG", "calcEveryWeek: ");
        DayOfWeek dayofweek = DayOfWeek.from(LocalDate.now());
        int  today=dayofweek.getValue();

        String[] temp =  days.split(";");
        int size = temp.length;
        int [] dayNum = new int [size];

        for(int i=0; i<size; i++) {
            dayNum[i] = Integer.parseInt(temp[i]);
        }

        for(int i = 0; i<size; i++)
        {
            if (today <= dayNum[i]) {
                return dayNum[i]-today;
            }
        }
    return 7-(today - dayNum[0]);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    int calcEvenOddWeek(int period, String days)
    {

        DayOfWeek dayofweek = DayOfWeek.from(LocalDate.now());
        int  today=dayofweek.getValue();
        Log.d(String.valueOf(today), "calcEvenOddWeek: ");

        LocalDate date = LocalDate.now();
        int weekOfYear = date.get(WeekFields.ISO.weekOfYear());
        Log.d(String.valueOf(weekOfYear), "calcEvenOddWeek: ");

        int evenWeek = weekOfYear % 2;
        if(period == 2) period = 0;

        String[] temp =  days.split(";");
        int size = temp.length;
        int [] dayNum = new int [size];
        if(dayNum[0]==0) dayNum[0] = Integer.parseInt(days);

        Log.d("TAG daynum", String.valueOf(dayNum[0]));

        if(period == evenWeek) {
            for (int i = 0; i < size; i++) {
                if (today <= dayNum[i]) {
                    return dayNum[i] - today;
                }
            }

            return 14-(today - dayNum[0]);
        }

        return 7-(today - dayNum[0]);

    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    int calcUnique(String days)
    {

        LocalDate date = LocalDate.now();
// 1.13.;2.10.;3.10.;4.14.;5.12.;6.9.;7.14.;8.11.;9.8.;10.13.;11.10.;12.8.
        ArrayList<LocalDate> deliveryDays = new ArrayList<>();
        int year = date.getYear();

        String[] splitedDays = days.split(";");

        Log.d("Eslő vágás:", String.valueOf(splitedDays.length));

        for(int i=0; i<splitedDays.length; i++)
        {
            String[] split2 = splitedDays[i].split("\\.");

            deliveryDays.add(  LocalDate.of(year,  Integer.parseInt(split2[0]), Integer.parseInt(split2[1]) )  );
        }


        for(int i=0; i<splitedDays.length; i++) {
            long next = ChronoUnit.DAYS.between(date, deliveryDays.get(i));

           if( next >= 0)
            return (int) next;

        }

        return -1;
    }

}
