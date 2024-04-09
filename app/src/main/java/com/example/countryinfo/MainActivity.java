package com.example.countryinfo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.countryinfo.Adapter.AdapterCountry;
import com.example.countryinfo.Api.ApiUtilities;
import com.example.countryinfo.CountryInfor.Country_Info_Activity;
import com.example.countryinfo.CountryModel.CountryData;
import com.example.countryinfo.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private ArrayList<CountryData> arrayList;
    private AdapterCountry adapterCountry;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // Check if binding is null before accessing its properties
        if (binding != null) {
            arrayList = new ArrayList<>();
            adapterCountry = new AdapterCountry(this, arrayList);
            binding.countryRv.setLayoutManager(new LinearLayoutManager(this));
            binding.countryRv.setAdapter(adapterCountry);
            progressBar = new ProgressDialog(this);
            progressBar.setMessage("Please wait.....");
            progressBar.setCancelable(false);
            progressBar.show();
            getData();
        } else {
            // Handle the case where binding is null
            Log.e("MainActivity", "Binding is null");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.filter, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int item_id = item.getItemId();


        if (item_id == R.id.sort_by_AtoZ)
        {
            // sort a to z
            Collections.sort(arrayList, CountryData.countrynameatoz);
            Toast.makeText(MainActivity.this, "sorted by A to Z", Toast.LENGTH_SHORT).show();
            adapterCountry.notifyDataSetChanged();
            return true;
        }
        else if (item_id==R.id.search_bar_action)
        {

           // search bar
            SearchView searchView = (SearchView) item.getActionView();
            searchView.setQueryHint("Search by name");
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }
                @Override
                public boolean onQueryTextChange(String newText) {
                    filter(newText);
                    adapterCountry.notifyDataSetChanged();
                    return true;
                }
            });
            return true;

        } else if (item_id == R.id.sort_by_ZtoA)
        {
            // sort Z to A
            Collections.sort(arrayList, CountryData.countrynameztoa);
            Toast.makeText(MainActivity.this, "sorted by Z to A", Toast.LENGTH_SHORT).show();
            adapterCountry.notifyDataSetChanged();
            return true;
        } else if (item_id == R.id.sortby_area)
        {
            // sort by area
            Collections.sort(arrayList, CountryData.countrynamearea);
            Toast.makeText(MainActivity.this, "sorted by area", Toast.LENGTH_SHORT).show();
            adapterCountry.notifyDataSetChanged();
            return true;
        } else if (item_id==R.id.about_developer) {
            Intent intent =  new Intent(MainActivity.this,AboutActivity.class);
            startActivity(intent);

            return true;

        }
        return super.onOptionsItemSelected(item);
    }

    public void getData() {
        ApiUtilities.getApiInterface().getCountryDetails().enqueue(new Callback<Map<String, CountryData>>() {
            @Override
            public void onResponse(Call<Map<String, CountryData>> call, Response<Map<String, CountryData>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Map<String, CountryData> countryDataMap = response.body();
                    // Convert the map to a list if needed
                    arrayList.addAll(countryDataMap.values());
                    adapterCountry.notifyDataSetChanged();
                    progressBar.dismiss();
                } else {
                    String errorMessage = "Unsuccessful response";
                    if (response.errorBody() != null) {
                        try {
                            errorMessage = response.errorBody().string();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    Log.e("API", errorMessage);
                    Toast.makeText(MainActivity.this, "Sorry, data is not loading", Toast.LENGTH_SHORT).show();
                    progressBar.dismiss();
                }
            }

            @Override
            public void onFailure(Call<Map<String, CountryData>> call, Throwable t) {
                Log.e("API", "Failed to fetch data:" + t.getMessage());
                Toast.makeText(MainActivity.this, "Sorry something wrong with api", Toast.LENGTH_SHORT).show();
            }
        });
    }

//    public void searchEngine() {
//        binding.searchCountrys.addTextChangedListener(new TextWatcher() {
//                @Override
//                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                }
//
//                @Override
//                public void onTextChanged(CharSequence s, int start, int before, int count) {
//                }
//
//                @Override
//                public void afterTextChanged(Editable s) {
//                    filter(s.toString());
//                }
//            });
//
//    }

    private void filter(String countryname) {
        ArrayList<CountryData> list = new ArrayList<>();
        for (CountryData data : arrayList) {
            if (data.getName().toLowerCase().contains(countryname.toLowerCase())) {
                list.add(data);
            }
        }
        adapterCountry.search_CountryEngine(list);
    }
}