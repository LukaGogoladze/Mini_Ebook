package com.example.ebookia.database.subject;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QuerySnapshot;

public interface SubjectDatabase {
    Task<Void> addSubject(String name);
    Task<Void> deleteSubject(String id);
    Task<QuerySnapshot> getAll();
}
