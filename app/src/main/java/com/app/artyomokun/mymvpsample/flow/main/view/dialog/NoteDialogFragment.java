package com.app.artyomokun.mymvpsample.flow.main.view.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.app.artyomokun.mymvpsample.R;

/**
 * Created by artyomokun on 04/03/2017.
 */

public class NoteDialogFragment extends DialogFragment {

    private InteractionInterface mInteractionInterface;

    private EditText editTextNote;

    public static NoteDialogFragment getInstance(InteractionInterface interactionInterface) {
        NoteDialogFragment noteDialogFragment = new NoteDialogFragment();
        noteDialogFragment.setInteractionInterface(interactionInterface);
        return noteDialogFragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View rootView = inflater.inflate(R.layout.note_dialog_layout, null);
        editTextNote = (EditText) rootView.findViewById(R.id.et_note);

        AlertDialog ad = new AlertDialog.Builder(getActivity())
                .setIcon(R.drawable.ic_note_add_black_24dp)
                .setTitle("Enter new note")
                .setView(rootView)
                .setPositiveButton("OK", null)
                .setNegativeButton("Cancel", null).create();
        ad.setOnShowListener(dialog -> ((AlertDialog) dialog).getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(editTextNote.getText())) {
                    editTextNote.setError("can not be empty");
                } else {
                    mInteractionInterface.onNewNoteWritten(editTextNote.getText().toString());
                    dialog.dismiss();
                }
            }
        }));
        return ad;
    }

    private void setInteractionInterface(InteractionInterface interactionInterface) {
        mInteractionInterface = interactionInterface;
    }

    public interface InteractionInterface {

        void onNewNoteWritten(String noteText);
    }
}
