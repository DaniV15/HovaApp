package com.example.final3.sphandler;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.final3.datacontroller.UserData;

import java.util.ArrayList;

public class SharedPrefHandler {

    SharedPreferences sp;
    Context context;
    int numberOfCard;
    private UserData userData;
    private ArrayList<UserData> userDataList = new ArrayList<>();

    public SharedPrefHandler(Context c){

        this.context=c;
        readNumberOfCard();

    }

    public int getNumberOfCard() {
        return numberOfCard;
    }

    public ArrayList<UserData> readUsersdata(){

        Log.d(String.valueOf(numberOfCard), "readUsersdata: ");

        for(int i = 0; i < numberOfCard; i++)
        {
            sp = context.getSharedPreferences("UserAddress" + i, Context.MODE_PRIVATE);
            userData  =  new UserData(
                    sp.getInt("id",0),
                    sp.getString("name", "Otthon"),
                    sp.getString("city", "Nincs"),
                    sp.getString("dataline", "Nincs"),
                    sp.getString("type", ""));

            userDataList.add(userData);
        }

        return userDataList;
    }

    public void addNewData(String Name, String City, String DataLine, String Type){

        sp = context.getSharedPreferences("UserAddress" + numberOfCard, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("id", numberOfCard);
        editor.putString("name", Name);
        editor.putString("city", City);
        editor.putString("dataline", DataLine);
        editor.putString("type", Type);
        editor.apply();

        incNumberOfCard();

    }

    public void modifyExistingData(int id, String Name, String City, String DataLine, String Type){

        sp = context.getSharedPreferences("UserAddress" + id, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("id", id);
        editor.putString("name", Name);
        editor.putString("city", City);
        editor.putString("dataline", DataLine);
        editor.putString("type", Type);
        editor.apply();
    }

    private void incNumberOfCard()
    {
        numberOfCard++;
        sp = context.getSharedPreferences("Address_counter", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        editor.putInt("counter", numberOfCard);
        editor.apply();
    }

    private void decNumberOfCard()
    {
        numberOfCard--;
        sp = context.getSharedPreferences("Address_counter", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        editor.putInt("counter", numberOfCard);
        editor.apply();
    }

    private void readNumberOfCard()
    {
        sp = context.getSharedPreferences("Address_counter", Context.MODE_PRIVATE);
        numberOfCard = sp.getInt("counter", 0);
    }

    public void deleteUserData(int id)
    {
        readUsersdata();
        Log.d(String.valueOf(userDataList.size()), "deleteUserData: ");
        userDataList.remove(id);
        decNumberOfCard();
        Log.d(String.valueOf(userDataList.size()), "deleteUserData: ");
        rewriteAllUserDataList();
    }

    private void rewriteAllUserDataList(){

        numberOfCard = 0;
        for(int i =0; i < userDataList.size(); i++)
        {
            addNewData(userDataList.get(i).getName(), userDataList.get(i).getCity(),
                    userDataList.get(i).getDataLine(), userDataList.get(i).getType() ); }
    }

    public String getName(int id)
    {
        readUsersdata();
        return userDataList.get(id).getName();
    }
    public String getCity(int id)
    {
        readUsersdata();
        return userDataList.get(id).getCity();
    }
    public String getDataLine(int id)
    {
        readUsersdata();
        return userDataList.get(id).getDataLine();
    }
    public String getType(int id)
    {
        readUsersdata();
        return userDataList.get(id).getType();
    }



}
