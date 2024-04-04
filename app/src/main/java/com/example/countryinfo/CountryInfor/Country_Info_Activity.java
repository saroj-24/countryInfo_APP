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


        Intent data = getIntent();
        String countryname = data.getStringExtra("country_name");
        String capital = data.getStringExtra("country_capital");
        String area = data.getStringExtra("country_area");
        String population = data.getStringExtra("country_population");
        String officialname = data.getStringExtra("official_name");
        String flag  = data.getStringExtra("flag_image");
        String region= data.getStringExtra("region");
        String subregion= data.getStringExtra("sub_region");
        String callingcode = data.getStringExtra("calling_code");
        String currency = data.getStringExtra("currency");
        String symbol = data.getStringExtra("symbol");

        Glide.with(this)
                .load(flag)
                .placeholder(R.drawable.finish) // Placeholder image while loading
                .error(R.drawable.close)// Error image if Glide fails to load
                .into(binding.countryInfoImg);
        binding.countryNameInfo.setText(countryname);
        binding.capitalInfo.setText(capital);
        binding.areaInfo.setText(area);
        binding.populationInfo.setText(population);
        binding.fullCapital.setText(capital);
        binding.fulOfficialName.setText(officialname);
        binding.fullPopulation.setText(population);
        binding.fullTotalArea.setText(area);
        binding.fullCallingCode.setText(callingcode);
        binding.fullRegionName.setText(region);
        binding.fullSubRegion.setText(subregion);
        binding.fullCurrency.setText(currency);
        binding.fullCurSysmbol.setText(symbol);


    }



}