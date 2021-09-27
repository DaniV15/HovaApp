package com.example.final3;

public class UserAddressProfil {

    private String Name;
    private String City;
    private String Address;
    private String Type;
    private String Inform;

    public UserAddressProfil(String City, String Address, String Type)
    {
        this.City = City;
        this.Address = Address;
        this.Type = Type;
    }

    public UserAddressProfil()
    {
    }

    public String getAddress() {
        return Address;
    }

    public String getType() {
        return Type;
    }

    public String getInform() {
        return Inform;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setType(String type) {
        Type = type;
    }

    public void setInform(String inform) {
        Inform = inform;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
