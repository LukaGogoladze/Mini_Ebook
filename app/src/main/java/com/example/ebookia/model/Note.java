package com.example.ebookia.model;

import java.io.Serializable;

public class Note implements Serializable {

    public String noteId;
    public String subjectId;
    public String ownerId;
    public String title;
    public String description;


    public Note(String noteId, String subjectId, String ownerId, String title, String description) {
        this.noteId = noteId;
        this.subjectId = subjectId;
        this.ownerId = ownerId;
        this.title = title;
        this.description = description;
    }

    public Note(){}
}
