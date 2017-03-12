package com.app.artyomokun.mymvpsample.flow.secondary;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.app.artyomokun.mymvpsample.MVPApplication;
import com.app.artyomokun.mymvpsample.R;
import com.app.artyomokun.mymvpsample.common.dto.Note;
import com.app.artyomokun.mymvpsample.common.view.ActivityMVPBase;
import com.app.artyomokun.mymvpsample.di.dagger.components.DaggerSecondaryActivityComponent;
import com.app.artyomokun.mymvpsample.di.dagger.components.SecondaryActivityComponent;
import com.app.artyomokun.mymvpsample.di.dagger.modules.SecondaryModule;
import com.app.artyomokun.mymvpsample.flow.secondary.interfaces.Secondary;
import com.app.artyomokun.mymvpsample.flow.secondary.view.adapters.NotesAdapter;
import com.app.artyomokun.mymvpsample.flow.secondary.view.dialog.NoteDialogFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.app.artyomokun.mymvpsample.flow.secondary.view.dialog.NoteDialogFragment.*;

public class SecondaryActivity extends ActivityMVPBase
        implements Secondary.View {

    private Secondary.Presenter mPresenter;

    private NotesAdapter mNotesAdapter;

    @BindView(R.id.notes_recycler_view)
    RecyclerView mRecyclerViewNote;

    @BindView(R.id.action_btn_add_note)
    FloatingActionButton mBtnAddNote;

    private InteractionInterface mNoteDialogFragmentInteractionListener;

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
        NoteDialogFragment noteDialogFragment = getInstance(mNoteDialogFragmentInteractionListener);
        noteDialogFragment.show(getSupportFragmentManager(), "NoteDialogFragment");
    }

    private void initRecyclerView() {
        mRecyclerViewNote.setHasFixedSize(true);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerViewNote.setLayoutManager(lm);
        mRecyclerViewNote.setAdapter(mNotesAdapter);
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
    public void onNoteAdded(int position) {

    }

    @Override
    public void showNotes(List<Note> notes) {

    }

    @Override
    public void registerAddingNoteListener(InteractionInterface interactionInterface) {

    }

    @Override
    public void registerDeleteNoteListener(NotesAdapter.ItemsInteractionCallbacks interactionCallbacks) {

    }

    @Override
    public void showError(String errorMessage) {

    }
}
