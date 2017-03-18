package com.app.artyomokun.mymvpsample.flow.type.two.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.artyomokun.mymvpsample.R;
import com.app.artyomokun.mymvpsample.common.dto.Note;
import com.app.artyomokun.mymvpsample.flow.type.two.view.viewholders.NotesViewHolder;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by artyomokun on 04/03/2017.
 */

public class NotesAdapter extends RecyclerView.Adapter<NotesViewHolder> {

    private final LayoutInflater mInflater;

    private List<Note> data = new ArrayList<>();

    private ItemsInteractionCallbacks mInteractionCallbacks;

    @Inject
    public NotesAdapter(LayoutInflater inflater) {
        mInflater = inflater;
    }

    public void setData(List<Note> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public NotesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_item_note, parent, false);
        NotesViewHolder viewHolder = new NotesViewHolder(view);
        viewHolder.btnDelete.setOnClickListener(btnDelete -> {
            int adapterPosition = viewHolder.getAdapterPosition();
            if (mInteractionCallbacks != null && adapterPosition != RecyclerView.NO_POSITION) {
                mInteractionCallbacks.onItemDeleted(data.get(adapterPosition));
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NotesViewHolder holder, int position) {
        Note note = data.get(position);
        holder.date.setText(note.getDate());
        holder.text.setText(note.getText());
    }

    public void registerDeleteClicks(ItemsInteractionCallbacks interactionCallbacks){
        mInteractionCallbacks = interactionCallbacks;
    }

    public interface ItemsInteractionCallbacks{
        void onItemDeleted(Note noteToDelete);
    }

}
