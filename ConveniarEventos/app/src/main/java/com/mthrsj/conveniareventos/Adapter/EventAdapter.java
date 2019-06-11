package com.mthrsj.conveniareventos.Adapter;


import android.content.Context;
import android.content.Intent;
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

import java.util.List;

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
            final Event event = EventList.get(position);
            holder.bshare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String message = String.format("Conheça o evento %s, que começará no dia %s \n\nLink: http://conveniar.com.br",event.getNomeEvento(), event.getDataInicio().split("T")[0]);
                    Intent i = new Intent();
                    i.setAction(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.putExtra(Intent.EXTRA_TEXT,message);
                    mCtx.startActivity(Intent.createChooser(i,"Compartilhar em"));
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
