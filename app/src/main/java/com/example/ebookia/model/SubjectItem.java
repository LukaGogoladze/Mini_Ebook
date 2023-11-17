package com.example.ebookia.model;

import java.io.Serializable;

public class SubjectItem implements Serializable {
    public String subjectId;
    public String subjectOwner;
    public String name;

    public SubjectItem(String subjectId, String subjectOwner, String name) {
        this.subjectId = subjectId;
        this.subjectOwner = subjectOwner;
        this.name = name;
    }

    public SubjectItem(){}
}
