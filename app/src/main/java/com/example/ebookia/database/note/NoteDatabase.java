package com.example.ebookia.database.note;

import com.example.ebookia.model.Note;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QuerySnapshot;


public interface NoteDatabase {

    Task<Void> createNote(String title, String noteDescription, String subjectId);
    Task<Void> updateNote(String title, String noteDescription, String subjectId, String noteId);
    Task<Void> deleteNote(String noteId);
    Task<QuerySnapshot> getAllNote(String subjectId);






}
