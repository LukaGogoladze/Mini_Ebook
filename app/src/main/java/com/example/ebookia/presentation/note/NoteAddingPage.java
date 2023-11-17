package com.example.ebookia.presentation.note;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.ebookia.model.SubjectItem;
import com.example.ebookia.presentation.note_details.NoteDetails;
import com.example.ebookia.R;
import com.example.ebookia.model.Note;
import com.example.ebookia.database.note.NoteDatabasaeImpl;
import com.example.ebookia.database.note.NoteDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class NoteAddingPage extends AppCompatActivity implements NoteAdapterListener{

    private FloatingActionButton addNoteBtn;
    private RecyclerView recyclerView;
    private ImageButton menuBtn;
    private NoteAdapter adapter;
    private NoteDatabase noteDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_adding_page);


        addNoteBtn = findViewById(R.id.add_note_btn);
        recyclerView = findViewById(R.id.recycler_view);
        menuBtn = findViewById(R.id.menu_btn);
        noteDatabase = new NoteDatabasaeImpl();
        menuBtn.setOnClickListener((v) -> showMenu());
        setupRecyclerView();
        getAllNote();

        navigateToAddNote();
    }

    private void navigateToAddNote(){

        addNoteBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, NoteDetails.class);
            SubjectItem subjectItem = (SubjectItem) getIntent().getSerializableExtra("subject");
            intent.putExtra("subject", subjectItem);
            intent.putExtra("isNoteSaved", false);

            startActivity(intent);
        });
    }

    void showMenu() {

    }

    void setupRecyclerView() {
        adapter = new NoteAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    void getAllNote() {
        SubjectItem subjectItem = (SubjectItem) getIntent().getSerializableExtra("subject");
        noteDatabase.getAllNote(subjectItem.subjectId).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<Note> noteList = task.getResult().toObjects(Note.class);
                adapter.setNote_list(noteList);
            }
        });
    }

    @Override
    public void deleteNoteListener(Note note) {
        noteDatabase.deleteNote(note.noteId).addOnCompleteListener(task -> {
           if (task.isSuccessful()){
               getAllNote();
           }else {
               Toast.makeText(this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
           }
        });
    }

    @Override
    public void navigateSavedNote(Note note) {
        Intent intent = new Intent(this, NoteDetails.class);
        SubjectItem subjectItem = (SubjectItem) getIntent().getSerializableExtra("subject");
        intent.putExtra("subject", subjectItem);
        intent.putExtra("note", note);
        intent.putExtra("isNoteSaved", true);
        startActivity(intent);
    }
}