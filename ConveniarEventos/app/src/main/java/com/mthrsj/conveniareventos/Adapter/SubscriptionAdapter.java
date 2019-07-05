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
import com.mthrsj.conveniareventos.Utils.API.models.Subscription;
import com.mthrsj.conveniareventos.Utils.Database.Database;
import com.mthrsj.conveniareventos.Utils.Database.Models.Config;

import java.util.List;

import io.realm.Realm;

public class SubscriptionAdapter extends RecyclerView.Adapter<SubscriptionAdapter.EventViewHolder> {

    private Context mCtx;
    private List<Subscription> subscriptionList;
    private OnItemClickListener listener;

    public SubscriptionAdapter(Context mCtx, List<Subscription> subscription_list) {
        this.mCtx = mCtx;
        subscriptionList = subscription_list;
    }

    public void setOnItemClickListener(OnItemClickListener mListener) {
        listener = mListener;
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.card_subscription, null);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final @NonNull EventViewHolder holder, final int position) {
        if (subscriptionList.size() > 0) {
            final Subscription event = subscriptionList.get(position);
            holder.bshare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String message = String.format("Conheça o evento %s \n\nLink: http://conveniar.com.br", event.getNomeEvento());
                    Intent i = new Intent();
                    i.setAction(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.putExtra(Intent.EXTRA_TEXT, message);
                    mCtx.startActivity(Intent.createChooser(i, "Compartilhar em"));
                }
            });
            if (checkFav(event.getCodEvento())) {
                holder.bfav.setColorFilter(Color.parseColor("#CD5C5C"));
            } else {
                holder.bfav.setColorFilter(Color.parseColor("#707070"));
            }
            holder.bfav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!checkFav(event.getCodEvento())) {
                        toggleFav(event.getCodEvento());
                        holder.bfav.setColorFilter(Color.parseColor("#CD5C5C"));
                    } else {
                        if (toggleFav(event.getCodEvento()) == getItemCount()) {
                            subscriptionList.remove(position);
                            notifyItemRemoved(position);
                            notifyItemRangeChanged(position, subscriptionList.size());
                            notifyDataSetChanged();
                        }
                        holder.bfav.setColorFilter(Color.parseColor("#707070"));
                    }
                }
            });

            holder.title.setText(event.getNomeEvento());
            String body = "";
            holder.body.setText(body);
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
            Log.d("FAV", newFavs);
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
        return subscriptionList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
