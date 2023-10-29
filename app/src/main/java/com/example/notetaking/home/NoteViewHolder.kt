package com.example.notetaking.home

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.example.notetaking.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class NoteViewHolder(
    itemView: View,
    private val homeViewModel: HomeViewModel?,
    private val updateNote: (note: Note) -> Unit,
) : RecyclerView.ViewHolder(itemView) {

    fun bind(note: Note) {
        val tvContent = itemView.findViewById<TextView>(R.id.content)
        val tvTitle = itemView.findViewById<TextView>(R.id.title)
        val buttonDelete = itemView.findViewById<ImageView>(R.id.button_delete)
        val buttonEdit = itemView.findViewById<ImageView>(R.id.button_edit)
        tvContent.text = note.content
        tvTitle.text = note.title
        itemView.setOnClickListener {
            updateNote(note)
        }
        buttonDelete.setOnClickListener {
            homeViewModel?.deleteNote()
        }
        buttonEdit.setOnClickListener {
            homeViewModel?.editNote()
        }
    }
}