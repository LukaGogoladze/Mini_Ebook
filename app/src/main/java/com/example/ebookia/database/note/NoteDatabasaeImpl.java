package com.example.ebookia.database.note;

import com.example.ebookia.model.Note;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.UUID;

public class NoteDatabasaeImpl implements NoteDatabase {
    private final FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    private final CollectionReference collection = firestore.collection("Note");
    private final FirebaseAuth auth = FirebaseAuth.getInstance();


    @Override
    public Task<Void> createNote(String title, String noteDescription, String subjectId) {
        String userId = auth.getCurrentUser().getUid();
        String noteId = UUID.randomUUID().toString();
        Note newNote = new Note(noteId, subjectId, userId, title, noteDescription);
        return collection.document(noteId).set(newNote);
    }

    @Override
    public Task<Void> updateNote(String title, String noteDescription, String subjectId, String noteId) {
        String userId = auth.getCurrentUser().getUid();
        Note newNote = new Note(noteId, subjectId, userId, title, noteDescription);
        return collection.document(noteId).set(newNote);
    }

    @Override
    public Task<Void> deleteNote(String noteId) {
        return collection.document(noteId).delete();
    }

    @Override
    public Task<QuerySnapshot> getAllNote(String subjectId) {
        String userId = auth.getCurrentUser().getUid();
        return collection.whereEqualTo("ownerId", userId).whereEqualTo("subjectId", subjectId).get();
    }
}
