package com.example.ebookia.presentation.note_details;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.ebookia.R;
import com.example.ebookia.database.auth.AuthenticationRepository;

import com.example.ebookia.model.Note;
import com.example.ebookia.model.SubjectItem;
import com.example.ebookia.presentation.note.NoteAddingPage;
import com.example.ebookia.database.note.NoteDatabasaeImpl;
import com.example.ebookia.database.note.NoteDatabase;

public class NoteDetails extends AppCompatActivity {
    private EditText titleEditText, contentEditText;
    private ImageButton saveNoteBtn;
    private NoteDatabase noteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_note_details);
        titleEditText = findViewById(R.id.note_title_text);
        contentEditText = findViewById(R.id.note_content_text);
        saveNoteBtn = findViewById(R.id.save_note_btn);
        noteDatabase = new NoteDatabasaeImpl();
        saveNoteBtn.setOnClickListener(view -> {


                saveNote();

        });
        setupNote();

    }

    void saveNote() {
        String noteTitle = titleEditText.getText().toString();
        String noteContent = contentEditText.getText().toString();
        if (noteTitle.isEmpty() || noteContent.isEmpty()) {
            titleEditText.setError("Title is required");
        } else {
            boolean isNoteSaved = getIntent().getBooleanExtra("isNoteSaved", false);

            SubjectItem subjectItem = (SubjectItem) getIntent().getSerializableExtra("subject");



            if (isNoteSaved){
                Note noteItem = (Note) getIntent().getSerializableExtra("note");

                noteDatabase.updateNote(noteTitle, noteContent, subjectItem.subjectId, noteItem.noteId).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Note Edited!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(this, NoteAddingPage.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("subject", subjectItem);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Can't Edit note", Toast.LENGTH_SHORT).show();
                    }
                });

            }else {
                noteDatabase.createNote(noteTitle, noteContent, subjectItem.subjectId).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Note Added!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(this, NoteAddingPage.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("subject", subjectItem);
                        startActivity(intent);

                    } else {
                        Toast.makeText(getApplicationContext(), "Can't add note", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }

    void setupNote() {
        boolean isNoteSaved = getIntent().getBooleanExtra("isNoteSaved", false);
        if (isNoteSaved) {
            Note noteItem = (Note) getIntent().getSerializableExtra("note");

            titleEditText.setText(noteItem.title);
            contentEditText.setText(noteItem.description);

        }
    }
}