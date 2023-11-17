package com.example.ebookia.database.subject;

import com.example.ebookia.model.SubjectItem;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.UUID;

public class SubjectDatabaseImpl implements SubjectDatabase{

    private final FirebaseAuth auth =FirebaseAuth.getInstance();
    private final FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    private final CollectionReference collectionReference = firestore.collection("Subject");

    @Override
    public Task<Void> addSubject(String name) {
        String ownerId = auth.getCurrentUser().getUid();
        String subjectId = UUID.randomUUID().toString();
        SubjectItem subjectItem = new SubjectItem(subjectId, ownerId, name);
        return collectionReference.document(subjectId).set(subjectItem);
    }

    @Override
    public Task<Void> deleteSubject(String id) {
        return collectionReference.document(id).delete();
    }

    @Override
    public Task<QuerySnapshot> getAll() {
        String ownerId = auth.getCurrentUser().getUid();
        return collectionReference.whereEqualTo("subjectOwner", ownerId).get();
    }
}
