package com.bignerdranch.android.olutkellari;

/**
 * Created by Sami on 26.1.2017.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class OlutAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private static final int header = 0;
    private static final int item = 1;
    private List<OlutKortti> olutList;
    private Context context;



    @Override
    public void onClick(View v) {
        Intent intent =  new Intent(context, HaeViivakoodillaActivity.class);
        context.startActivity(intent);

    }


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

    public class MyHeaderHolder extends RecyclerView.ViewHolder {

        public Button viivakoodiHaku;

        public MyHeaderHolder(View view) {
            super(view);
            context = itemView.getContext();
            viivakoodiHaku = (Button) view.findViewById(R.id.haeOlutViivakoodilla);
        }
    }


    public OlutAdapter(List<OlutKortti> moviesList) {
        this.olutList = moviesList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(viewType == item){
            return new MyViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.olut_row, parent, false));
        }else if(viewType == header){
            return new MyHeaderHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.hae_viivakoodilla, parent, false));
        }
        throw new RuntimeException("Ei löytynyt listaa");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof MyViewHolder){
            OlutKortti olut = olutList.get(position-1);
            ((MyViewHolder) holder).nimi.setText(olut.getNimi());
            ((MyViewHolder) holder).hinta.setText(olut.getHinta().toString() + " €");
            ((MyViewHolder) holder).tyyppi.setText(olut.getTyyppi());
            ((MyViewHolder) holder).paikka.setText("Juotu paikassa: "+olut.getPaikka());
            ((MyViewHolder) holder).alkoholi.setText(olut.getAlkoholi().toString() + " %");
            ((MyViewHolder) holder).maa.setText(olut.getMaa());
            double arvosana = olut.getArvosana();
            ((MyViewHolder) holder).rate.setRating((float) arvosana);
        }else if(holder instanceof MyHeaderHolder){
            ((MyHeaderHolder) holder).viivakoodiHaku.setOnClickListener(this);
        }


    }


    @Override
    public int getItemViewType(int position) {
        if (onkoHeaderi(position)){
            return header;
        }else {
            return item;
        }
    }

    public boolean onkoHeaderi(int position){
        if(position == 0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public int getItemCount() {
        return olutList.size() +1;
    }
}
