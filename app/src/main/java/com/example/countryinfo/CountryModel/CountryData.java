package com.example.countryinfo.CountryModel;

import com.google.gson.annotations.SerializedName;

public class CountryData {
    @SerializedName("name")
    private String name;

    @SerializedName("capital")
    private String capital;

    @SerializedName("area")
    private double area;

    @SerializedName("population")
    private int population;

    @SerializedName("flag")
    private Flag flag;

    public CountryData(String name, String capital, double area, int population, Flag flag) {
        this.name = name;
        this.capital = capital;
        this.area = area;
        this.population = population;
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public Flag getFlag() {
        return flag;
    }

    public void setFlag(Flag flag) {
        this.flag = flag;
    }

    public static class Flag {
        @SerializedName("medium")
        private String medium;

        public String getMedium() {
            return medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }
    }

}
