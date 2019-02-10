package at.fh.swengb.homeexercise2_solution

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_note_list.*

class NoteListActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var notesDatabase: NotesDatabase

    val notesAdapter = NotesAdapter({
            val implicitIntent = Intent(this, AddNoteActivity::class.java)
            implicitIntent.putExtra("NOTE_ID", it.id)
            startActivity(implicitIntent)
    }, {
        val dialogBuilder = AlertDialog.Builder(this)

        dialogBuilder.setTitle("Delete Note")
        dialogBuilder.setMessage("Are you sure you want to delete the Note?")
        dialogBuilder.setPositiveButton("Yes") { _, _ ->
            notesDatabase.notesDao.deleteNote(it.id)
        }
        dialogBuilder.setNegativeButton("No", null)
        dialogBuilder.show()
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)
        sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
        notesDatabase = NotesDatabase.getDatabase(applicationContext)

        notes_recycler_view.adapter = notesAdapter
        notes_recycler_view.layoutManager = LinearLayoutManager(this)

        populateUserInfo()
    }


    override fun onResume() {
        var noteList: List<Note>
        val userId = sharedPreferences.getLong(MainActivity.SHARED_PREF_ID_KEY,-1)

        noteList = notesDatabase.notesDao.findAllNotesFromUser(userId)
        notesAdapter.updateList(noteList)
        super.onResume()
    }

    private fun populateUserInfo() {
        val user_id = sharedPreferences.getLong(MainActivity.SHARED_PREF_ID_KEY, -1)
        val userName = notesDatabase.userDao.findUserName(user_id.toLong())
        val userAge = notesDatabase.userDao.findUserAge(user_id.toLong())

        if (userName.isNullOrBlank() || userAge == -1) {
            user_info.text = "Invalid username or age"
        } else {
            user_info.text = "Notes for ${userName}, ${userAge}"
        }
    }

    fun logout(v:View){
        sharedPreferences.edit().remove(MainActivity.SHARED_PREF_ID_KEY).apply()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    fun addNote(v: View) {
        val addNoteIntent = Intent(this, AddNoteActivity::class.java)
        startActivity(addNoteIntent)
    }
}
