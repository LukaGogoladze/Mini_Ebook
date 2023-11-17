package com.example.ebookia.presentation.note;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ebookia.databinding.RecyclerNoteItemBinding;
import com.example.ebookia.model.Note;

import java.util.ArrayList;
import java.util.List;


public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private List<Note> note_list = new ArrayList<>();
    private NoteAdapterListener noteAdapterListener;

    void setNote_list(List<Note> note_list) {
        this.note_list = note_list;
        notifyDataSetChanged();

    }

    public NoteAdapter(NoteAdapterListener noteAdapterListener){
        this.noteAdapterListener = noteAdapterListener;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        NoteViewHolder viewHolder = new NoteViewHolder(RecyclerNoteItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

        viewHolder.binding.deleteNoteImage.setOnClickListener(view -> {
            noteAdapterListener.deleteNoteListener(note_list.get(viewHolder.getAdapterPosition()));
        });
        viewHolder.binding.savedNote.setOnClickListener(view -> {
            noteAdapterListener.navigateSavedNote(note_list.get(viewHolder.getAdapterPosition()));


        });
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.onBind(note_list.get(position));
    }

    @Override
    public int getItemCount() {
        return note_list.size();
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {
        RecyclerNoteItemBinding binding;

        public NoteViewHolder(@NonNull RecyclerNoteItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

        void onBind(Note note) {
            binding.noteTitleTextView.setText(note.title);
            binding.noteContentTextView.setText(note.description);
        }
    }

}