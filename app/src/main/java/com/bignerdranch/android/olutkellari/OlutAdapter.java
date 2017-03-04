package com.bignerdranch.android.olutkellari;

/**
 * Created by Sami on 26.1.2017.
 */

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;

class OlutAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener,View.OnLongClickListener {
    private static final int header = 0;
    private static final int item = 1;
    private final List<OlutKortti> olutList;
    private Context context;
    private AlertDialog.Builder builder;




    @Override
    public void onClick(View v) {
        int hae = R.id.haeOlutViivakoodilla;
        int id = v.getId();
        if(id == hae){
            Intent intent =  new Intent(context, HaeViivakoodillaActivity.class);
            context.startActivity(intent);
        }
    }

    private void swap(){
        notifyDataSetChanged();
    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public final TextView nimi;
        public final TextView hinta;
        public final TextView tyyppi;
        public final TextView alkoholi;
        public final TextView maa;
        public final TextView paikka;
        public final RatingBar rate;
        private ConstraintLayout kortti = null;

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
            kortti = (ConstraintLayout) view.findViewById(R.id.olut_row_kortti);

        }
    }

    public class MyHeaderHolder extends RecyclerView.ViewHolder {

        public final Button viivakoodiHaku;

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
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

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
            ((MyViewHolder) holder).kortti.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    builder = new AlertDialog.Builder(context);
                    builder.setMessage("Haluatko poistaa vai muokata olutta?")
                            .setCancelable(true)
                            .setPositiveButton("Muokka olutta", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    OlutKortti olut = olutList.get(position-1);
                                    Intent intent = new Intent(context, PaivitaOlutActivity.class);
                                    intent.putExtra("Kortti", olut);
                                    context.startActivity(intent);
                                }
                            })
                            .setNegativeButton("Poista olut",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    new AlertDialog.Builder(context)
                                            .setTitle("Poista olut kannasta")
                                            .setMessage("Oletko varma, että haluat poistaa oluen kannasta? Toimintoa ei voi peruuttaa!")
                                            .setIcon(android.R.drawable.ic_dialog_alert)
                                            .setPositiveButton("Kyllä", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int whichButton) {
                                                    OlutKortti olut = olutList.get(position-1);
                                                    Context ctx = context;
                                                    DatabaseOperations dop = new DatabaseOperations(ctx);
                                                    dop.deleteOlut(dop, olut);
                                                    olutList.remove(position-1);
                                                    Toast.makeText(context,"Olut poistettu!",Toast.LENGTH_LONG).show();
                                                    swap();                        }})
                                            .setNegativeButton("Ei", null).show();
                                }
                            }).show();
                    return false;
                }
            });
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

    private boolean onkoHeaderi(int position){
        return position == 0;
    }

    @Override
    public int getItemCount() {
        return olutList.size() +1;
    }
}
