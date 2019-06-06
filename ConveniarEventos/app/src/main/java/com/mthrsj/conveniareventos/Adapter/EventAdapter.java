package com.mthrsj.conveniareventos.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mthrsj.conveniareventos.R;
import com.mthrsj.conveniareventos.Utils.API.models.Event;

import java.util.List;

//RecyclerView.Adapter
//RecyclerView.ViewHolder
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
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        if (EventList.size() > 0) {
            Event event = EventList.get(position);
            holder.title.setText(event.getNomeEvento());
            String body = "";
            for (int i = 0; i < event.getInformacoes().size(); i++) {
                body = body.concat(event.getInformacoes().get(i).getNomeEventoInformacao() + "\n");
            }
            int vagas = event.getNumeroVagas();
            if (vagas >= 0) {
                holder.vacancies.setText("Vagas: " + vagas);
            } else {
                holder.vacancies.setText("Vagas: 0");
            }
            holder.body.setText(body);
            holder.date.setText(event.getStatusEvento()); //MUDAR ISSO DPS PARA A DATA DO EVENTO
        }
    }

    @Override
    public int getItemCount() {
        return EventList.size();
    }

    class EventViewHolder extends RecyclerView.ViewHolder {

        TextView title, date, body, vacancies;
        ImageButton bshare;

        private EventViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            date = itemView.findViewById(R.id.date);
            body = itemView.findViewById(R.id.body);
            bshare = itemView.findViewById(R.id.bshare);
            vacancies = itemView.findViewById(R.id.vacancies);
        }
    }
}
