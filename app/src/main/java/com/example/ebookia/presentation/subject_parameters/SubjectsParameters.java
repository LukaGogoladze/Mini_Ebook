package com.example.ebookia.presentation.subject_parameters;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.ebookia.R;
import com.example.ebookia.databinding.ActivityParametersSubjectsBinding;
import com.example.ebookia.model.SubjectItem;
import com.example.ebookia.presentation.note.NoteAddingPage;

public class SubjectsParameters extends AppCompatActivity {

    private ActivityParametersSubjectsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityParametersSubjectsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        navigateToNotePage();

    }


    private void navigateToNotePage(){
        binding.button.setOnClickListener(view -> {
            Intent intent = new Intent(this, NoteAddingPage.class);
            SubjectItem subjectItem = (SubjectItem) getIntent().getSerializableExtra("subject");
            intent.putExtra("subject", subjectItem);
            startActivity(intent);

        });
    }
}