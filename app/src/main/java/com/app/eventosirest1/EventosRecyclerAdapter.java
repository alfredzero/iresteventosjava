package com.app.eventosirest1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class EventosRecyclerAdapter extends RecyclerView.Adapter<EventosRecyclerAdapter.EventosViewHolder> {

    List<ApiResponse.Evento> eventosList;
    OnCardClickListener onCardClickListener;

    public EventosRecyclerAdapter(List<ApiResponse.Evento> eventosList, OnCardClickListener onCardClickListener) {
        this.eventosList = eventosList;
        this.onCardClickListener = onCardClickListener;
    }

    @NonNull
    @Override
    public EventosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.eventos_recycler_row, parent, false);
        return new EventosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventosViewHolder holder, int position) {
        ApiResponse.Evento evento = eventosList.get(position);
        holder.titleTextView.setText(evento.getTitle());
        Picasso.get().load(evento.getImage()).into(holder.imageView);

        // Set OnClickListener to invoke the onCardClick method
        holder.itemView.setOnClickListener(v -> onCardClickListener.onCardClick(evento.getUrlToRegister()));
    }

    @Override
    public int getItemCount() {
        return eventosList.size();
    }

    class EventosViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        ImageView imageView;

        public EventosViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.evento_title);
            imageView = itemView.findViewById(R.id.evento_image_view);
        }
    }

    public interface OnCardClickListener {
        void onCardClick(String urlToRegister);
    }
}
