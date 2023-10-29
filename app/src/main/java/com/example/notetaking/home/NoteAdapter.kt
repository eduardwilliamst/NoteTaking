package com.example.notetaking.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.notetaking.R
import com.example.notetaking.databinding.ItemNoteBinding

class NoteAdapter(
    private val homeViewModel: HomeViewModel?,
    private val updateNote: (note: Note) -> Unit,
) : ListAdapter<Note, NoteViewHolder>(
    NoteDiffUtil()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            itemView = LayoutInflater.from(parent.context).inflate(
                R.layout.item_note,
                parent,
                false,
            ),
            homeViewModel = homeViewModel,
            updateNote = updateNote,
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}