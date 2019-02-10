package at.fh.swengb.homeexercise2_solution

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        val SHARED_PREF_ID_KEY = "SHARED_PREF_ID_KEY"
    }

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var notesDatabase: NotesDatabase
    private var user_id:Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notesDatabase = NotesDatabase.getDatabase(applicationContext)
        sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)

        user_id = sharedPreferences.getLong(SHARED_PREF_ID_KEY,-1)

        if (notesDatabase.userDao.countUserByID(user_id)>0)
        {
            val user = notesDatabase.userDao.getUser(user_id)
            user_age.setText(user.age.toString())
            user_name.setText(user.name)
        }
    }

    fun onSaveButtonClick(v: View) {
        val userName = user_name.text.toString()
        val age = user_age.text.toString().toIntOrNull()

        if (userName.isBlank() || age == null) {
            Toast.makeText(this, "Please enter a username and your age", Toast.LENGTH_LONG).show()
            return
        }
        else
        {
            if(notesDatabase.userDao.countUserByName(userName)==0)
            {
                val user = User(userName, age)
                notesDatabase.userDao.insert(user)
            }
        }
        user_id = notesDatabase.userDao.getUserID(userName)
        sharedPreferences.edit().putLong(SHARED_PREF_ID_KEY, user_id).apply()

        val startNoteListActivityIntent = Intent(this, NoteListActivity::class.java)
        startActivity(startNoteListActivityIntent)
        finish()
    }
}
