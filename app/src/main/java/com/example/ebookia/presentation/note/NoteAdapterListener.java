package com.example.ebookia.presentation.note;

import com.example.ebookia.model.Note;

public interface NoteAdapterListener {
    void deleteNoteListener(Note note);
    void navigateSavedNote(Note note);
}
