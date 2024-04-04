package com.example.countryinfo.CountryModel;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

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

    @SerializedName("official_name")
    private  String official_name;

    @SerializedName("callingCode")
    private String callingCode;

    @SerializedName("region")
    private String region;

    @SerializedName("subregion")
    private String subregion;
    private Map<String, Map<String, String>> currencies;

    public CountryData(String name, String capital, double area, int population, Flag flag, String official_name, String callingCode, String region, String subregion, Map<String, Map<String, String>> currencies) {
        this.name = name;
        this.capital = capital;
        this.area = area;
        this.population = population;
        this.flag = flag;
        this.official_name = official_name;
        this.callingCode = callingCode;
        this.region = region;
        this.subregion = subregion;
        this.currencies = currencies;
    }

    public Map<String, Map<String, String>> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(Map<String, Map<String, String>> currencies) {
        this.currencies = currencies;
    }

    public String getCallingCode() {
        return callingCode;
    }

    public void setCallingCode(String callingCode) {
        this.callingCode = callingCode;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public String getOfficial_name() {
        return official_name;
    }

    public void setOfficial_name(String official_name) {
        this.official_name = official_name;
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
