package com.app.artyomokun.mymvpsample.flow.type.two.view.dialog;

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

    private NoteDialogFragmentInteractionInterface mNoteDialogFragmentInteractionInterface;

    private EditText editTextNote;

    public static NoteDialogFragment getInstance(NoteDialogFragmentInteractionInterface noteDialogFragmentInteractionInterface) {
        NoteDialogFragment noteDialogFragment = new NoteDialogFragment();
        noteDialogFragment.setInteractionInterface(noteDialogFragmentInteractionInterface);
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
        ad.setOnShowListener(dialog -> ((AlertDialog) dialog).getButton(DialogInterface.BUTTON_POSITIVE)
                .setOnClickListener(v -> {
                    if (TextUtils.isEmpty(editTextNote.getText())) {
                        editTextNote.setError("can not be empty");
                    } else {
                        mNoteDialogFragmentInteractionInterface.onNewNoteWritten(editTextNote.getText().toString());
                        dialog.dismiss();
                    }
                }));
        return ad;
    }

    private void setInteractionInterface(NoteDialogFragmentInteractionInterface noteDialogFragmentInteractionInterface) {
        mNoteDialogFragmentInteractionInterface = noteDialogFragmentInteractionInterface;
    }

    public interface NoteDialogFragmentInteractionInterface {

        void onNewNoteWritten(String noteText);
    }
}
