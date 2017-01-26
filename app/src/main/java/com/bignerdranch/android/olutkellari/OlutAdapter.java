package com.bignerdranch.android.olutkellari;

/**
 * Created by Sami on 26.1.2017.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

public class OlutAdapter extends RecyclerView.Adapter<OlutAdapter.MyViewHolder> {

    private List<OlutKortti> olutList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView nimi, hinta, tyyppi, alkoholi, maa, paikka;
        public RatingBar rate;

        public MyViewHolder(View view) {
            super(view);
            nimi = (TextView) view.findViewById(R.id.Nimi);
            hinta = (TextView) view.findViewById(R.id.Hinta);
            tyyppi = (TextView) view.findViewById(R.id.Tyyppi);
            alkoholi = (TextView) view.findViewById(R.id.alkoholi);
            maa = (TextView) view.findViewById(R.id.maa);
            paikka = (TextView) view.findViewById(R.id.paikka);
            rate = (RatingBar) view.findViewById(R.id.ratingBar);
            rate.setStepSize((float)0.5);
        }
    }


    public OlutAdapter(List<OlutKortti> moviesList) {
        this.olutList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.olut_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        OlutKortti olut = olutList.get(position);
        holder.nimi.setText(olut.getNimi());
        holder.hinta.setText(olut.getHinta().toString() + " €");
        holder.tyyppi.setText(olut.getTyyppi());
        holder.paikka.setText("Juotu paikassa: "+olut.getPaikka());
        holder.alkoholi.setText(olut.getAlkoholi().toString() + " %");
        holder.maa.setText(olut.getMaa());
        double arvosana = olut.getArvosana();
        holder.rate.setRating((float) arvosana);

    }

    @Override
    public int getItemCount() {
        return olutList.size();
    }
}
