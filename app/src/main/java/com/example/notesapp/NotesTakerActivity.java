package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.notesapp.Models.Notes;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NotesTakerActivity extends AppCompatActivity {
    EditText et_title, et_note;
    ImageView iv_save;
    Notes notes;

    boolean isOldNote = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_taker);

        et_title = findViewById(R.id.et_title);
        et_note = findViewById(R.id.et_note);
        iv_save = findViewById(R.id.iv_save);
        notes = new Notes();
        try {
            notes = (Notes) getIntent().getSerializableExtra("previous_note");
            et_title.setText(notes.getTitle());
            et_note.setText(notes.getNotes());
            isOldNote = true;
        }catch (Exception e){
            e.printStackTrace();
        }

        iv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = et_title.getText().toString();
                String description = et_note.getText().toString();

                if(description.isEmpty()){
                    Toast.makeText(NotesTakerActivity.this, "Please add in a note", Toast.LENGTH_LONG).show();
                    return;
                }
                SimpleDateFormat formatter = new SimpleDateFormat("EEE, d MMM yyyy HH:mm a");
                Date date =  new Date();
                if(!isOldNote){
                    notes = new Notes();
                }

                notes.setTitle(title);
                notes.setNotes(description);
                notes.setDate(formatter.format(date));

                Intent intent = new Intent();

                intent.putExtra("note", notes);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

    }
}