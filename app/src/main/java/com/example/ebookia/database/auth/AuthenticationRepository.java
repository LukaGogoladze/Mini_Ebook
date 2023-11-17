package com.example.ebookia.database.auth;

import com.example.ebookia.model.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public interface AuthenticationRepository {
    Task<AuthResult> signUp(User user);
    Task<AuthResult> signIn(User user);
    void Logout();
}
