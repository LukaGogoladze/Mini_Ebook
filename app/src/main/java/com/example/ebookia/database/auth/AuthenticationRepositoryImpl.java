package com.example.ebookia.database.auth;

import com.example.ebookia.model.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AuthenticationRepositoryImpl implements AuthenticationRepository{

    private final FirebaseAuth auth = FirebaseAuth.getInstance();




    @Override
    public Task<AuthResult> signIn(User user) {
        return auth.signInWithEmailAndPassword(user.getEmail(), user.getPassword());
    }

    @Override
    public void Logout() {
        auth.signOut();

    }

    @Override
    public Task<AuthResult> signUp(User user) {

        return auth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword());
    }
}
