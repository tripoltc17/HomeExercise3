package at.fh.swengb.homeexercise2_solution

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_note.*

class AddNoteActivity : AppCompatActivity() {
    private lateinit var notesDatabase: NotesDatabase
    private lateinit var sharedPreferences: SharedPreferences
    companion object {
        val SHARED_PREF_ID_KEY = "SHARED_PREF_ID_KEY"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
        notesDatabase = NotesDatabase.getDatabase(applicationContext)
        sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)


        val note_id = intent.getLongExtra("NOTE_ID", -1)
        if (note_id > -1) {
            val note = notesDatabase.notesDao.getNote(note_id)
            add_note_title.setText(note.title)
            add_note_content.setText(note.content)
        }
    }

    fun saveNote(v: View) {

        val title = add_note_title.text.toString()
        val description = add_note_content.text.toString()

        if (title.isNotBlank() || description.isNotBlank()) {
            val user_id = sharedPreferences.getLong(SHARED_PREF_ID_KEY, -1)
            val note_id = intent.getLongExtra("NOTE_ID", -1)

            if (note_id > -1) {
                val note = Note(title, description, user_id.toInt())
                notesDatabase.notesDao.updateNote(note)
            } else {
                notesDatabase.notesDao.insertNote(Note(title, description, user_id.toInt()))
            }
            } else {
            Toast.makeText(this, "Add Title and Content!", Toast.LENGTH_LONG).show()
            }

        val saveNoteIntent = Intent(this, NoteListActivity::class.java)
        startActivity(saveNoteIntent)
    }

    fun shareNote(v: View) {
        val note_id = intent.getLongExtra("NOTE_ID", -1)
        val note = notesDatabase.notesDao.getNote(note_id)

        add_note_title.setText(note.title)
        add_note_content.setText(note.content)

        val intent = Intent(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_TEXT, note.content)
        intent.type = "text/plain"
        val shareIntent = Intent.createChooser(intent, "Choose an app to share your note.")
        startActivity(shareIntent)
    }
}
