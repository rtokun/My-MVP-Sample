package com.app.artyomokun.mymvpsample.flow.type.two;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;

import com.app.artyomokun.mymvpsample.MVPApplication;
import com.app.artyomokun.mymvpsample.R;
import com.app.artyomokun.mymvpsample.common.dto.Note;
import com.app.artyomokun.mymvpsample.common.view.ActivityMVPBase;
import com.app.artyomokun.mymvpsample.di.dagger.components.DaggerSecondaryActivityComponent;
import com.app.artyomokun.mymvpsample.di.dagger.components.SecondaryActivityComponent;
import com.app.artyomokun.mymvpsample.di.dagger.modules.SecondaryModule;
import com.app.artyomokun.mymvpsample.flow.type.two.interfaces.Secondary;
import com.app.artyomokun.mymvpsample.flow.type.two.view.adapters.NotesAdapter;
import com.app.artyomokun.mymvpsample.flow.type.two.view.dialog.NoteDialogFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.app.artyomokun.mymvpsample.flow.type.two.view.dialog.NoteDialogFragment.NoteDialogFragmentInteractionInterface;
import static com.app.artyomokun.mymvpsample.flow.type.two.view.dialog.NoteDialogFragment.getInstance;

public class SecondaryActivity extends ActivityMVPBase implements
        Secondary.View,
        NoteDialogFragmentInteractionInterface, NotesAdapter.ItemsInteractionCallbacks {

    private Secondary.Presenter mPresenter;

    private NotesAdapter mNotesAdapter;

    @BindView(R.id.notes_recycler_view)
    RecyclerView mRecyclerViewNote;

    @BindView(R.id.action_btn_add_note)
    FloatingActionButton mBtnAddNote;

    private SecondaryActivityComponent mSecondaryActivityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);
        bindViews();
        initDependencies();
        initViews();
        loadData();
    }

    private void initViews() {
        initRecyclerView();
        initFloatingActionButton();
    }

    private void initFloatingActionButton() {
        mBtnAddNote.setOnClickListener(button -> showDialog());
    }

    private void showDialog() {
        NoteDialogFragment noteDialogFragment = getInstance(this);
        noteDialogFragment.show(getSupportFragmentManager(), "NoteDialogFragment");
    }

    private void initRecyclerView() {
        mRecyclerViewNote.setHasFixedSize(true);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerViewNote.setLayoutManager(lm);
        mRecyclerViewNote.setAdapter(mNotesAdapter);
        mNotesAdapter.registerDeleteClicks(this);
    }

    private void bindViews() {
        ButterKnife.bind(this);
    }

    private void loadData() {
        mPresenter.loadData();
    }

    private void initDependencies() {
        mSecondaryActivityComponent = DaggerSecondaryActivityComponent.builder()
                .applicationComponent(MVPApplication.getApplicationComponent())
                .secondaryModule(new SecondaryModule(this))
                .build();

        mPresenter = mSecondaryActivityComponent.getPresenter();
        mNotesAdapter = mSecondaryActivityComponent.getAdapter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    @Override
    public void showNotes(List<Note> notes) {
        mNotesAdapter.setData(notes);
    }

    @Override
    public void showError(String errorMessage) {

    }

    @Override
    public void onNewNoteWritten(String noteText) {
        mPresenter.addNote(noteText);
    }

    @Override
    public void onItemDeleted(Note noteToDelete) {
        mPresenter.deleteNote(noteToDelete);
    }
}
