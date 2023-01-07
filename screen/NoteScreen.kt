package com.example.noteapp.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.noteapp.components.NoteButton
import com.example.noteapp.components.NoteInputText
import com.example.noteapp.data.NotesDataSource
import com.example.noteapp.model.Note
import java.text.SimpleDateFormat


@Composable
fun NoteScreen(
    notes: List<Note>,
    onAddNote: (Note)-> Unit,
    onRemoveNote: (Note)-> Unit
    ){




    var title =  remember {
        mutableStateOf("")
    }
    var description = remember {
        mutableStateOf("")
    }

    val context= LocalContext.current
    Column(modifier = Modifier.padding(6.dp)){
        TopAppBar(title = {
            Text("JetNote")
        },
        actions = { Icon(imageVector = Icons.Rounded.Notifications, contentDescription = "Icon")},
            backgroundColor = Color(0XFFDADFE3)
            )
        // Content
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            NoteInputText(text = title.value,
                label = "Title",
                onTextChange = {
                               if(it.all{char->char.isLetter() || char.isWhitespace()}) title.value= it
                },
                modifier = Modifier.padding(top=9.dp, bottom = 8.dp))
            NoteInputText(text = description.value,
                label = "Add a note",
                onTextChange = {
                    if(it.all{char->char.isLetter() || char.isWhitespace()}) description.value= it

                },
                modifier = Modifier.padding(top=9.dp, bottom = 8.dp))
            NoteButton(text = "Save",
                onClick = {
                    if(title.value.isNotEmpty() && description.value.isNotEmpty()){
                        // save/add to the list
                        onAddNote(Note(title=title.value,description=description.value))

                        title.value=""
                        description.value=""
                        Toast.makeText(context, "Note Added", Toast.LENGTH_SHORT).show()
                    }
                })
        }

        Divider()
        LazyColumn{
           items(notes){
               note->
               NoteRow(note=note, onNoteClicked ={onRemoveNote(note)} )
           }
        }
    //
    }
}

@Composable
fun NoteRow(
    modifier: Modifier=Modifier,
    note: Note,
    onNoteClicked: (Note)-> Unit
){
    Surface(
        modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp))
            .fillMaxWidth(),
        color = Color(0XFFDFE6EB),
        elevation = 6.dp
    ){
        Column(
            modifier
                .clickable { onNoteClicked(note)}
                .padding(horizontal = 14.dp, vertical = 6.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(note.title, style= MaterialTheme.typography.subtitle2)
            Text(note.description, style= MaterialTheme.typography.subtitle1)
            Text(SimpleDateFormat("dd-MM-yyyy").format(note.entryDate))

        }
    }

}

@Preview(showBackground = true)
@Composable
fun NoteScreenPreview(){
    NoteScreen(notes = NotesDataSource().loadNotes(), onAddNote = {}, onRemoveNote ={} )
}