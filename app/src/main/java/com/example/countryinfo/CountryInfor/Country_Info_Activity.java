package com.example.countryinfo.CountryInfor;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.countryinfo.CountryModel.CountryData;
import com.example.countryinfo.R;
import com.example.countryinfo.databinding.ActivityCountryInfoBinding;

public class Country_Info_Activity extends AppCompatActivity {
    private ActivityCountryInfoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCountryInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("country")) {
            CountryData country = intent.getParcelableExtra("country");
            if (country != null) {
                binding.countryNameInfo.setText(country.getName());
                binding.capitalInfo.setText(country.getCapital());
                binding.areaInfo.setText(String.valueOf(country.getArea()+" sq.km"));
                binding.populationInfo.setText(String.valueOf(country.getPopulation()));


                // Load flag image using Glide
                Glide.with(this)
                        .load(country.getFlag().getMedium())
                        .placeholder(R.drawable.finish) // Placeholder image while loading
                        .error(R.drawable.close) // Error image if Glide fails to load
                        .into(binding.countryInfoImg);
            }
        }









    }



}