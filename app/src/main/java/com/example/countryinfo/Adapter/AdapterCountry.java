package com.example.countryinfo.Adapter;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.countryinfo.CountryInfor.Country_Info_Activity;
import com.example.countryinfo.CountryModel.CountryData;
import com.example.countryinfo.R;
import com.example.countryinfo.databinding.CountryDetailsBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AdapterCountry extends RecyclerView.Adapter<AdapterCountry.myviewHolder> {

   public Context context;
   ArrayList<CountryData> dataArrayList;
   // private OnCountryClickListener listener;

    public AdapterCountry(Context context, ArrayList<CountryData> dataArrayList) {
        this.context = context;
        this.dataArrayList = dataArrayList;

    }

    @NonNull
    @Override
    public myviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myview = LayoutInflater.from(context).inflate(R.layout.country_details,parent,false);
        return new myviewHolder(myview);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewHolder holder, int position) {
        CountryData country = dataArrayList.get(position);
        holder.countryname.setText(country.getName());
        holder.country_capital.setText(country.getCapital());
        holder.countryarea.setText(String.valueOf(country.getArea()+" sq.km")); // Convert int to String
        holder.countryopopulation.setText(String.valueOf(country.getPopulation())); // Convert int to String

        // Load flag image using Glide
        Glide.with(context)
                .load(country.getFlag().getMedium())// Pass the flag URL
                .placeholder(R.drawable.finish) // Placeholder image while loading
                .error(R.drawable.close)// Error image if Glide fails to load
                .into(holder.flag_image);


        // Load currency data
        Map<String, Map<String, String>> currencies = country.getCurrencies();
        if (currencies != null && !currencies.isEmpty()) {
            Map.Entry<String, Map<String, String>> entry = currencies.entrySet().iterator().next();
            Map<String, String> currencyInfo = entry.getValue();
            String currencyName = currencyInfo.get("name");
            String currencySymbol = currencyInfo.get("symbol");
            holder.currency.setText(currencyName + " (" + currencySymbol + ")");

        } else {
            holder.currency.setText("Currency data not available");
        }

        holder.itemView.setOnClickListener(v->{
           Intent intent  = new Intent(v.getContext(), Country_Info_Activity.class);
           intent.putExtra("country_name",country.getName());
           intent.putExtra("country_capital",country.getCapital());
           intent.putExtra("country_area",country.getArea()+" sq.km");
           intent.putExtra("country_population",String.valueOf(country.getPopulation()));
           intent.putExtra("flag_image",country.getFlag().getMedium());
           intent.putExtra("official_name",country.getOfficial_name());
           intent.putExtra("calling_code",country.getCallingCode());
           intent.putExtra("region",country.getRegion());
           intent.putExtra("sub_region",country.getSubregion());
           intent.putExtra("currency", holder.currency.getText().toString());
           v.getContext().startActivity(intent);

        });



    }

    public void search_CountryEngine(ArrayList<CountryData> filterList)
    {
        dataArrayList = filterList;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return dataArrayList.size();
    }

    public  class myviewHolder extends RecyclerView.ViewHolder{

          TextView countryname, country_capital, countryarea, countryopopulation,currency;
          ImageView flag_image;
        public myviewHolder(@NonNull View itemView) {
            super(itemView);
            countryname = itemView.findViewById(R.id.title);
            country_capital= itemView.findViewById(R.id.country_capital);
            countryarea= itemView.findViewById(R.id.totalarea_textview);
            countryopopulation= itemView.findViewById(R.id.total_population);
            flag_image = itemView.findViewById(R.id.country_flag_img);
            currency = itemView.findViewById(R.id.currency);
        }
    }
}
