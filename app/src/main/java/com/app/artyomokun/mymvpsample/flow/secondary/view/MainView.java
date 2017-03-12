//package com.app.artyomokun.mymvpsample.flow.secondary.view;
//
//import android.support.design.widget.FloatingActionButton;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.widget.Toast;
//
//import com.app.artyomokun.mymvpsample.R;
//import com.app.artyomokun.mymvpsample.common.dto.Note;
//import com.app.artyomokun.mymvpsample.flow.main.MainActivity;
//import com.app.artyomokun.mymvpsample.flow.main.interfaces.Main;
//import com.app.artyomokun.mymvpsample.flow.secondary.view.adapters.NotesAdapter;
//import com.app.artyomokun.mymvpsample.flow.secondary.view.dialog.NoteDialogFragment;
//
//import java.util.List;
//
//import javax.inject.Inject;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//
///**
// * Created by artyomokun on 10/03/2017.
// */
//
//public class MainView implements Main.View{
//
//    private final MainActivity mMainActivity;
//
//    private final NotesAdapter mNotesAdapter;
//
//    @BindView(R.id.notes_recycler_view)
//    RecyclerView mRecyclerViewNote;
//
//    @BindView(R.id.action_btn_add_note)
//    FloatingActionButton mBtnAddNote;
//
//    private NoteDialogFragment.InteractionInterface mNoteDialogFragmentInteractionListener;
//
//    @Inject
//    public MainView(MainActivity mainActivity, NotesAdapter notesAdapter) {
//        this.mMainActivity = mainActivity;
//        mNotesAdapter = notesAdapter;
//        bindViews();
//        initViews();
//    }
//
//    private void initViews() {
//        initRecyclerView();
//        initFloatingActionButton();
//    }
//
//    private void initFloatingActionButton() {
//        mBtnAddNote.setOnClickListener(button -> showDialog());
//    }
//
//    private void showDialog() {
//        NoteDialogFragment noteDialogFragment = NoteDialogFragment.getInstance(mNoteDialogFragmentInteractionListener);
//        noteDialogFragment.show(mMainActivity.getSupportFragmentManager(), "NoteDialogFragment");
//    }
//
//    private void initRecyclerView() {
//        mRecyclerViewNote.setHasFixedSize(true);
//        LinearLayoutManager lm = new LinearLayoutManager(mMainActivity);
//        lm.setOrientation(LinearLayoutManager.VERTICAL);
//        mRecyclerViewNote.setLayoutManager(lm);
//        mRecyclerViewNote.setAdapter(mNotesAdapter);
//    }
//
//    private void bindViews() {
//        ButterKnife.bind(this, mMainActivity);
//    }
//
//    @Override
//    public void onNoteAdded(int position) {
//
//    }
//
//    @Override
//    public void showNotes(List<Note> notes) {
//        mNotesAdapter.setData(notes);
//    }
//
//    @Override
//    public void registerAddingNoteListener(NoteDialogFragment.InteractionInterface interactionInterface) {
//        mNoteDialogFragmentInteractionListener = interactionInterface;
//    }
//
//    @Override
//    public void registerDeleteNoteListener(NotesAdapter.ItemsInteractionCallbacks interactionCallbacks) {
//        mNotesAdapter.registerDeleteClicks(interactionCallbacks);
//    }
//
//    @Override
//    public void showError(String errorMessage) {
//        Toast.makeText(mMainActivity, errorMessage, Toast.LENGTH_SHORT).show();
//    }
//
//}
