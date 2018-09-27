package com.example.josethomas.countrydetailsrcview;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by JoseThomas on 3/22/2016.
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.CountryViewHolder>{

    private ArrayList<String> countries;

    public RVAdapter(ArrayList<String> countries) {
        this.countries = countries;
    }

    @Override
    public RVAdapter.CountryViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_row, viewGroup, false);
        CountryViewHolder cvh = new CountryViewHolder(v);
        return cvh;
    }

    @Override
    public void onBindViewHolder(RVAdapter.CountryViewHolder cvholder, int position) {
        cvholder.tv_country.setText(countries.get(position));
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public class CountryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView tv_country;
        public final static String COUNTRY_NAME = "COUNTRY_NAME";

        public CountryViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            tv_country = (TextView)view.findViewById(R.id.countryName);
        }

        @Override
        public void onClick(View v) {

            Intent intent = new Intent(v.getContext(),DetailsActivity.class);
            intent.putExtra(COUNTRY_NAME, tv_country.getText().toString());
            v.getContext().startActivity(intent);
        }
    }
}
