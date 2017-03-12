package com.app.artyomokun.mymvpsample.flow.secondary.view.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.app.artyomokun.mymvpsample.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotesViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.note_text)
    public TextView text;

    @BindView(R.id.note_date)
    public TextView date;

    @BindView(R.id.btn_delete)
    public ImageButton btnDelete;

    public NotesViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
