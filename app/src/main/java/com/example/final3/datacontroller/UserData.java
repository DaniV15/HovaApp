package com.example.final3.datacontroller;

public class UserData {

    int id;
    String name;
    String dataLine;
    String city;
    String type;

    public UserData(int id, String name, String city, String dataLine, String type) {
        this.id = id;
        this.name = name;
        this.dataLine = dataLine;
        this.city = city;
        this.type = type;
    }

    public UserData() {
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDataLine() {
        return dataLine;
    }

    public void setDataLine(String dataLine) {
        this.dataLine = dataLine;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

   public String getAddress()
   {
       String[] frag= dataLine.split(",");
       return frag[0];
   }








}
