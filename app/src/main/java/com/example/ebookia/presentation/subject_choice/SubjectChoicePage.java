package com.example.ebookia.presentation.subject_choice;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ebookia.R;
import com.example.ebookia.databinding.ActivitySubjectChoicePageBinding;
import com.example.ebookia.model.SubjectItem;
import com.example.ebookia.database.subject.SubjectDatabase;
import com.example.ebookia.database.subject.SubjectDatabaseImpl;
import com.example.ebookia.presentation.subject_parameters.SubjectsParameters;

import java.util.List;

public class SubjectChoicePage extends AppCompatActivity implements SubjectAdapterListener {

    private ActivitySubjectChoicePageBinding binding;
    private SubjectDatabase subjectDatabase;
    private SubjectAdapter subjectAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySubjectChoicePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        subjectDatabase = new SubjectDatabaseImpl();
        subjectAdapter = new SubjectAdapter(this);

        binding.subjectRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.subjectRecyclerView.setAdapter(subjectAdapter);


        getAllSubjects();


        binding.addNoteBtn2.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View myView = LayoutInflater.from(this).inflate(R.layout.subject_layout, null);
            builder.setView(myView);
            EditText editText = myView.findViewById(R.id.subject_edit_text);
            builder.setTitle("Add subject");
            builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    String subjectName = editText.getText().toString();
                    if (subjectName.isEmpty()) {
                        Toast.makeText(SubjectChoicePage.this, "Enter the subject name", Toast.LENGTH_SHORT).show();
                    } else {
                        subjectDatabase.addSubject(subjectName).addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(SubjectChoicePage.this, "Successfully Added", Toast.LENGTH_SHORT).show();
                                getAllSubjects();
                            } else {
                                Toast.makeText(SubjectChoicePage.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            });

            builder.create().show();
        });
    }

    private void getAllSubjects() {
        subjectDatabase.getAll().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<SubjectItem> subjectItemList = task.getResult().toObjects(SubjectItem.class);
                subjectAdapter.setSubjectItemList(subjectItemList);
            } else {
                Toast.makeText(this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void clickSubject(SubjectItem subjectItem) {
        Intent intent = new Intent(this, SubjectsParameters.class);
        intent.putExtra("subject", subjectItem);
        startActivity(intent);
    }
}