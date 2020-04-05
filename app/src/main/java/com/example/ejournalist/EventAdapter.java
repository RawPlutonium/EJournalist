package com.example.ejournalist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {
    List<Event> events;
    private OnItemClickListener mListener;
    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }
    public EventAdapter(List<Event> events) { this.events = events;
    }

    @NonNull
    @Override
    public EventAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.events_row, null);
        return new ViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull EventAdapter.ViewHolder holder, int position) {
        holder.eventName.setText(events.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return events.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView eventName;
        public ViewHolder(@NonNull View itemView, final OnItemClickListener listener){
            super(itemView);
            eventName = itemView.findViewById(R.id.event);
            itemView.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(listener != null){
                                int position = getAdapterPosition();
                                if (position != RecyclerView.NO_POSITION){
                                    listener.onItemClick(position);
                                }
                            }
                        }
                    }
            );
        }
    }

}
