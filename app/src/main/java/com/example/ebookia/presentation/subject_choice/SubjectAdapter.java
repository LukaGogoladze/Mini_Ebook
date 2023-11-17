package com.example.ebookia.presentation.subject_choice;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ebookia.databinding.SubjectAdapterLayoutBinding;
import com.example.ebookia.model.SubjectItem;

import java.util.ArrayList;
import java.util.List;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder>{

    private List<SubjectItem> subjectItemList = new ArrayList<>();
    private SubjectAdapterListener subjectAdapterListener;


    public SubjectAdapter(SubjectAdapterListener subjectAdapterListener){
        this.subjectAdapterListener = subjectAdapterListener;
    }

    @NonNull
    @Override
    public SubjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SubjectViewHolder viewHolder = new SubjectViewHolder(SubjectAdapterLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

        viewHolder.binding.subjectName.setOnClickListener(view -> {
            subjectAdapterListener.clickSubject(subjectItemList.get(viewHolder.getAdapterPosition()));
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectViewHolder holder, int position) {
        holder.onBind(subjectItemList.get(position));

    }

    @Override
    public int getItemCount() {
        return subjectItemList.size();
    }

    public void setSubjectItemList(List<SubjectItem> list){
        this.subjectItemList = list;
        notifyDataSetChanged();
    }

    class SubjectViewHolder extends RecyclerView.ViewHolder {

        SubjectAdapterLayoutBinding binding;

        public SubjectViewHolder(@NonNull SubjectAdapterLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(SubjectItem subjectItem){
            binding.subjectName.setText(subjectItem.name);
        }
    }
}
