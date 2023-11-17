package com.example.ebookia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.ebookia.database.auth.AuthenticationRepository;
import com.example.ebookia.database.auth.AuthenticationRepositoryImpl;
import com.example.ebookia.databinding.ActivityMainBinding;
import com.example.ebookia.model.User;
import com.example.ebookia.presentation.note.NoteAddingPage;
import com.example.ebookia.presentation.subject_choice.SubjectChoicePage;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private AuthenticationRepository auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth = new AuthenticationRepositoryImpl();
        signUp();
        signIn();
    }

    private void signUp() {
        binding.signUpBtn.setOnClickListener(view -> {
            binding.myProgressBar.setVisibility(View.VISIBLE);
            String email = binding.emailEditText.getText().toString();
            String password = binding.passwordEditText.getText().toString();
            User user = new User(email, password);
            auth.signUp(user).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    startActivity(new Intent(this, SubjectChoicePage.class));
                } else {
                    binding.myProgressBar.setVisibility(View.GONE);
                    Toast.makeText(this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });

    }

    public void signIn() {
        binding.signInBtn.setOnClickListener(view -> {
            binding.myProgressBar.setVisibility(View.VISIBLE);
            String email = binding.emailEditText.getText().toString();
            String password = binding.passwordEditText.getText().toString();
            User user = new User(email, password);
            auth.signIn(user).addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    binding.myProgressBar.setVisibility(View.GONE);
                    startActivity(new Intent(this, SubjectChoicePage.class));
                }else {
                    binding.myProgressBar.setVisibility(View.GONE);
                    Toast.makeText(this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }

            });
        });

    }

}