package com.example.ejournalist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class notesAdapter extends RecyclerView.Adapter<notesAdapter.ViewHolder> {
    private List<Notes> notesList;
    private OnItemClickListener mListener;
    public interface OnItemClickListener{
        void onItemClick(Notes notes);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }
    public notesAdapter(List<Notes> notesList){
        this.notesList = notesList;
    }
    @NonNull
    @Override
    public notesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_row,null);
        return new ViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull notesAdapter.ViewHolder holder, int position) {
        holder.note.setText(notesList.get(position).getText());
        holder.timestamp.setText(notesList.get(position).getCreationDate());
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView note, timestamp;
        public ViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
           note = itemView.findViewById(R.id.note);
           timestamp = itemView.findViewById(R.id.timestamp);
           itemView.setOnClickListener(
                   new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                            int position = getAdapterPosition();
                            if(listener != null && position != RecyclerView.NO_POSITION){
                                listener.onItemClick(notesList.get(position));
                            }
                       }
                   }
           );
        }
    }
}
