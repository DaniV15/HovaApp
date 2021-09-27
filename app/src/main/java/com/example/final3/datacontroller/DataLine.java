package com.example.final3.datacontroller;

public class DataLine {

    private int commPeriodT;
    private int comDaysT;
    private int selPeriodT;
    private int selDaysT;

    private int commPeriodCs;
    private int comDaysCs;
    private int selPeriodCs;
    private int selDaysCs;
    private int glassPeriod;
    private int glassDays;
    private int greenPeriod;
    private int greenDays;


    public DataLine(int commPeriodT, int comDaysT, int selPeriodT, int selDaysT, int commPeriodCs, int comDaysCs, int selPeriodCs, int selDaysCs, int glassPeriod, int glassDays, int greenPeriod, int greenDays) {
        this.commPeriodT = commPeriodT;
        this.comDaysT = comDaysT;
        this.selPeriodT = selPeriodT;
        this.selDaysT = selDaysT;
        this.commPeriodCs = commPeriodCs;
        this.comDaysCs = comDaysCs;
        this.selPeriodCs = selPeriodCs;
        this.selDaysCs = selDaysCs;
        this.glassPeriod = glassPeriod;
        this.glassDays = glassDays;
        this.greenPeriod = greenPeriod;
        this.greenDays = greenDays;
    }

    public int getCommPeriodT() {
        return commPeriodT;
    }

    public int getComDaysT() {
        return comDaysT;
    }

    public int getSelPeriodT() {
        return selPeriodT;
    }

    public int getSelDaysT() {
        return selDaysT;
    }

    public int getCommPeriodCs() {
        return commPeriodCs;
    }

    public int getComDaysCs() {
        return comDaysCs;
    }

    public int getSelPeriodCs() {
        return selPeriodCs;
    }

    public int getSelDaysCs() {
        return selDaysCs;
    }

    public int getGlassPeriod() {
        return glassPeriod;
    }

    public int getGlassDays() {
        return glassDays;
    }

    public int getGreenPeriod() {
        return greenPeriod;
    }

    public int getGreenDays() {
        return greenDays;
    }
}
