package com.example.noteapp.screen

import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import com.example.noteapp.data.NotesDataSource
import com.example.noteapp.model.Note

class NoteViewModel : ViewModel(){
    private var noteList = mutableListOf<Note>()

    init {
        noteList.addAll(NotesDataSource().loadNotes())
    }
    fun addNote(note: Note){
        noteList.add(note)
    }

    fun removeNote(note:Note){
        noteList.remove(note)
    }

    fun getAllNotes():List<Note>{
        return noteList
    }

}