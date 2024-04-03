package com.example.countryinfo.Api;

import static com.example.countryinfo.Api.ApiUtilities.API_KEY;

import com.example.countryinfo.CountryModel.CountryData;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

public interface ApiInterface {
    @Headers("Authorization: Bearer " + ApiUtilities.API_KEY)
    @GET("all")
    Call<Map<String, CountryData>> getCountryDetails();

}
