package com.mthrsj.conveniareventos.Adapter;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mthrsj.conveniareventos.R;
import com.mthrsj.conveniareventos.Utils.API.models.Event;
import com.mthrsj.conveniareventos.Utils.API.models.Informacoes;
import com.mthrsj.conveniareventos.Utils.Database.Database;
import com.mthrsj.conveniareventos.Utils.Database.Models.Config;

import java.util.List;

import io.realm.Realm;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    private Context mCtx;
    private List<Event> EventList;

    public EventAdapter(Context mCtx, List<Event> eventList) {
        this.mCtx = mCtx;
        EventList = eventList;
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.card_event, null);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final @NonNull EventViewHolder holder, final int position) {
        if (EventList.size() > 0) {
            final Event event = EventList.get(position);
            holder.bshare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String message = String.format("Conheça o evento %s, que começará no dia %s \n\nLink: http://conveniar.com.br", event.getNomeEvento(), event.getDataInicio().split("T")[0]);
                    Intent i = new Intent();
                    i.setAction(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.putExtra(Intent.EXTRA_TEXT, message);
                    mCtx.startActivity(Intent.createChooser(i, "Compartilhar em"));
                }
            });
            if (checkFav(event.getCodEvent())) {
                holder.bfav.setColorFilter(Color.parseColor("#CD5C5C"));
            } else {
                holder.bfav.setColorFilter(Color.parseColor("#707070"));
            }
            holder.bfav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!checkFav(event.getCodEvent())) {
                        toggleFav(event.getCodEvent());
                        holder.bfav.setColorFilter(Color.parseColor("#CD5C5C"));
                    } else {
                        if (toggleFav(event.getCodEvent()) == getItemCount()) {
                            notifyItemRemoved(position);
                            notifyItemRangeChanged(position, getItemCount() - 1);
                        }
                        holder.bfav.setColorFilter(Color.parseColor("#707070"));
                    }
                }
            });

            holder.title.setText(event.getNomeEvento());
            String body = "";
            int vagas;
            try {
                Informacoes i = event.getInformacoes();
                body = body.concat(i.getNomeEventoInformacao() + "\n");
                body = body.concat(i.getDescEventoInformacao());
            } catch (NullPointerException E) {

            }
            try {
                vagas = event.getNumeroVagas();
                holder.vacancies.setText("Vagas: " + vagas);
            } catch (NullPointerException E) {
                holder.vacancies.setText("Vagas: 0");
            }
            holder.body.setText(body);
            holder.date.setText(event.getSituacao()); //MUDAR ISSO DPS PARA A DATA DO EVENTO
        }
    }

    public boolean checkFav(int id) {
        Realm db = Database.getInstance();
        try {
            db.beginTransaction();
            Config favConfig = db.where(Config.class).equalTo("name", "favorites").findFirst();
            db.commitTransaction();
            String[] favS = favConfig.getValue().split(" ");
            String newFavs = "";
            for (int i = 0; i < favS.length; i++) {
                if (favS[i].equals(String.format("%d", id))) {
                    return true;
                }
            }
        } catch (NullPointerException e) {

        }
        return false;
    }

    public int toggleFav(int id) {
        Realm db = Database.getInstance();
        boolean found = false;
        int size = 0;
        try {
            db.beginTransaction();
            Config favConfig = db.where(Config.class).equalTo("name", "favorites").findFirst();
            String[] favS = favConfig.getValue().split(" ");
            size = favS.length;
            String newFavs = "";
            Log.d("FAV", "Favorites size: " + size);
            for (int i = 0; i < favS.length; i++) {
                if (!favS[i].trim().equals(String.format("%d", id))) {
                    if (i < favS.length - 1 || favS.length >= 1) {
                        newFavs = newFavs.concat(favS[i].trim() + " ");
                    } else {
                        newFavs = newFavs.concat(favS[i].trim());
                    }
                } else {
                    found = true;
                }
            }
            if (!found) {
                newFavs = newFavs.concat(id + " ");
            }
            favConfig.setValue(newFavs);
            db.commitTransaction();
        } catch (NullPointerException e) {
            Config favList = db.createObject(Config.class);
            String fav = "";
            fav = fav.concat(id + " ");
            favList.setName("favorites");
            favList.setValue(fav);
            Config managedFav = db.copyFromRealm(favList);
            db.commitTransaction();
        }
        return size;
    }

    @Override
    public int getItemCount() {
        return EventList.size();
    }

    class EventViewHolder extends RecyclerView.ViewHolder {

        TextView title, date, body, vacancies;
        ImageButton bshare, bfav;

        private EventViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            date = itemView.findViewById(R.id.date);
            body = itemView.findViewById(R.id.body);
            bshare = itemView.findViewById(R.id.bshare);
            vacancies = itemView.findViewById(R.id.vacancies);
            bfav = itemView.findViewById(R.id.bfav);
        }
    }
}
